package pl.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.main.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findByName(String name);
}
