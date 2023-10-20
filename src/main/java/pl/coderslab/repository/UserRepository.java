package pl.coderslab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}