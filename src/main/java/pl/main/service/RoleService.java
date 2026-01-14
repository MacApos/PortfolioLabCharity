package pl.main.service;

import pl.main.entity.Role;

public interface RoleService {
    Role findByName(String name);
    void saveUserRole();
}
