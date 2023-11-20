package pl.coderslab.service.impl;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.coderslab.entity.Role;
import pl.coderslab.entity.TokenAvailability;
import pl.coderslab.entity.User;
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

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, EmailService emailService,
                           BCryptPasswordEncoder passwordEncoder, HttpServletRequest request) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.emailService = emailService;
        this.passwordEncoder = passwordEncoder;
        this.request = request;
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
    public boolean isAdminLogged(User user) {
        Set<Role> roles = user.getRoles();
        Optional<Role> roleAdmin = roles
                .stream()
                .filter(role -> role.getName().equals("ROLE_ADMIN"))
                .findFirst();
        return roleAdmin.isPresent();
    }

    @Override
    public void saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setEnabled(1);
        Role userRole = roleRepository.findByName("ROLE_USER");
        user.setRoles(new HashSet<>(Arrays.asList(userRole)));
        userRepository.save(user);
    }

    @Override
    public void generateAndEmailToken(User user) {
        String email = user.getEmail();
        User existingUser = userRepository.findByEmail(email);
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
        User existingUser = userRepository.findByToken(token);
        existingUser.setPassword(passwordEncoder.encode(user.getPassword()));
        existingUser.setTokenAvailability(TokenAvailability.UNAVAILABLE);
        userRepository.save(existingUser);
    }

    @Override
    public List<User> findByRole(String role) {
        Role exisitingRole = roleRepository.findByName(role);
        return userRepository.findAllByRoles(exisitingRole);
    }
}
