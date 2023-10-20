package pl.coderslab.services;

import org.springframework.stereotype.Service;
import pl.coderslab.domain.User;

@Service
public interface UserService {
    User findByUserName(String username);
    void saveUser(User user);
}
