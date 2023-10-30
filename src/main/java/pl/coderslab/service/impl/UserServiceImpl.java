package pl.coderslab.service.impl;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.SessionAttributes;
import pl.coderslab.entity.Role;
import pl.coderslab.entity.User;
import pl.coderslab.repository.RoleRepository;
import pl.coderslab.repository.UserRepository;
import pl.coderslab.service.EmailService;
import pl.coderslab.service.UserService;

import javax.servlet.http.HttpSession;
import java.util.*;

@Service
@SessionAttributes("token")
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final EmailService emailService;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, EmailService emailService,
                           BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.emailService = emailService;
        this.passwordEncoder = passwordEncoder;
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
    public void generateAndEmailToken(Model model, User user) {
        String email = user.getEmail();
        User existingUser = userRepository.findByEmail(email);
        String token = UUID.randomUUID().toString();
        model.addAttribute("token", token);
        emailService.sendEmail(email,
                "Odzyskiwanie hasła",
                String.format("Jednorazowy kod niezbędny do zmiany hasła: %s", token));
        existingUser.setToken(token);
        userRepository.save(existingUser);
    }
}
