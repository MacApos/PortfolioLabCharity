package pl.coderslab.service;

import org.springframework.stereotype.Service;
import pl.coderslab.entity.CurrentUser;
import pl.coderslab.entity.User;

import java.util.List;

@Service
public interface UserService {
    User findByUsername(String username);

    User findByEmail(String email);

    User findByToken(String token);

    User findById(Long id);

    void saveUser(User user);

    void update(User user);

    void deleteById(CurrentUser currentUser, Long id);

    boolean isCurrentUser(CurrentUser currentUser, User user);
    boolean isAdminLogged(User user);

    void generateAndEmailToken(User user);

    void changePassword(User user);

    List<User> findByRole(String role);
}
