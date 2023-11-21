package pl.coderslab.service.impl;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.coderslab.entity.CurrentUser;
import pl.coderslab.entity.Role;
import pl.coderslab.entity.TokenAvailability;
import pl.coderslab.entity.User;
import pl.coderslab.mapper.CustomerMapper;
import pl.coderslab.repository.RoleRepository;
import pl.coderslab.repository.UserRepository;
import pl.coderslab.service.EmailService;
import pl.coderslab.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final EmailService emailService;
    private final BCryptPasswordEncoder passwordEncoder;
    private final HttpServletRequest request;
    private final CustomerMapper mapper;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, EmailService emailService,
                           BCryptPasswordEncoder passwordEncoder, HttpServletRequest request, CustomerMapper mapper) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.emailService = emailService;
        this.passwordEncoder = passwordEncoder;
        this.request = request;
        this.mapper = mapper;
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User findByToken(String token) {
        return userRepository.findByToken(token);
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public void saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setEnabled(1);
        user.setDeleted(0);
        Role userRole = roleRepository.findByName("ROLE_USER");
        user.setRoles(new HashSet<>(Arrays.asList(userRole)));
        userRepository.save(user);
    }

    @Override
    public void update(User user) {
        User existingUser = findById(user.getId());
        user.setEnabled(existingUser.getEnabled());
        user.setDeleted(existingUser.getDeleted());
        mapper.updateUser(user, existingUser);
        userRepository.save(existingUser);
    }

    @Override
    public void deleteById(CurrentUser currentUser, Long id) {
        User user = findById(id);
        if (currentUser.getUser().getId().equals(user.getId())) {
            return;
        }
        User exisitngUser = findById(id);
        exisitngUser.setEnabled(0);
        exisitngUser.setDeleted(1);
        userRepository.save(exisitngUser);
    }

    @Override
    public boolean isAdminLogged(User user) {
        if (user.getDeleted() == 1) {
            return false;
        }
        Set<Role> roles = user.getRoles();
        return roles.stream().anyMatch(role -> role.getName().equals("ROLE_ADMIN"));
    }

    @Override
    public void generateAndEmailToken(User user) {
        String email = user.getEmail();
        User existingUser = findByEmail(email);
        String token = UUID.randomUUID().toString();
        String requestURL = request.getRequestURL().toString().replace(request.getRequestURI(),
                String.format("/email-authentication?token=%s", token));
        emailService.sendEmail(email,
                "Odzyskiwanie hasła",
                String.format("Link do zmiany hasła: %s", requestURL));
        existingUser.setToken(token);
        existingUser.setTokenAvailability(TokenAvailability.AVAILABLE);
        existingUser.setTokenDate(LocalDateTime.now());
        userRepository.save(existingUser);
    }

    @Override
    public void changePassword(User user) {
        String token = user.getToken();
        User existingUser = findByToken(token);
        existingUser.setPassword(passwordEncoder.encode(user.getPassword()));
        existingUser.setTokenAvailability(TokenAvailability.UNAVAILABLE);
        userRepository.save(existingUser);
    }

    @Override
    public List<User> findByRole(String role) {
        return userRepository.findAllByRoles(role);
    }
}
