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

    List<User> findByRole(String roleName);

    void addAdmin(User user);

    void update(User user);

    void saveUser(User user);

    void deleteById(CurrentUser currentUser, Long id);
    void blockById(Long id);

    void deleteAdmin(CurrentUser currentUser, Long id);

    void generateAndEmailToken(User user);

    void changePassword(User user);
}
