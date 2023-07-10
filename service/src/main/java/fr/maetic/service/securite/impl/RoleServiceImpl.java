package fr.maetic.service.securite.impl;

import fr.maetic.dao.securite.RoleRepository;
import fr.maetic.exception.RoleNotFoundException;
import fr.maetic.model.securite.Role;
import fr.maetic.service.securite.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Role saveRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public Role findRoleById(Long id) {
        return roleRepository.findById(id).orElseThrow(()-> new RoleNotFoundException("Le role n'existe pas"));
    }
    @Override
    public List<Role> findAllRoles() {
        return roleRepository.findAll();
    }
    @Override
    public void deleteRoleById(Long id) {
    roleRepository.deleteRoleById(id);
    }
    @Override
    public void deleteRole(Role role) {
        roleRepository.delete(role);
    }

    @Override
    public void deleteAllRoles() {
        roleRepository.deleteAll();
    }

    @Override
    public long countRole() {
        return roleRepository.count();
    }

}