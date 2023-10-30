package pl.coderslab.service;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import pl.coderslab.entity.User;

@Service
public interface UserService {
    User findByUsername(String username);
    User findByEmail(String email);
    boolean isAdminLogged(User user);
    void saveUser(User user);
    void generateAndEmailToken(Model model, User user);
}
