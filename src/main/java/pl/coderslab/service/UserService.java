package pl.coderslab.service;

import org.springframework.stereotype.Service;
import pl.coderslab.entity.User;

@Service
public interface UserService {
    User findByUsername(String username);
    User findByEmail(String email);
    void saveUser(User user);
}
