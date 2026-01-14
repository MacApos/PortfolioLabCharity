package pl.main.service.impl;

import org.springframework.stereotype.Service;
import pl.main.entity.Role;
import pl.main.repository.InstitutionRepository;
import pl.main.repository.RoleRepository;
import pl.main.service.RoleService;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role findByName(String name) {
        return roleRepository.findByName(name);
    }

    @Override
    public void saveUserRole() {
        Role userRole = roleRepository.findByName("ROLE_USER");
        if (userRole == null) {
            Role role = new Role();
            role.setName("ROLE_USER");
            roleRepository.save(role);
        }
    }
}
