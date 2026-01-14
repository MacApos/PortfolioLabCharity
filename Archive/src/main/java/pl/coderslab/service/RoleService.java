package pl.main.service;

import pl.main.entity.Category;
import pl.main.entity.Role;

import java.util.List;

public interface RoleService {
    Role findByName(String name);
    void saveUserRole();
}
