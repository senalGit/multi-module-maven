package fr.maetic.service.securite;

import fr.maetic.model.securite.Role;

import java.util.List;

public interface RoleService {

    Role saveRole(Role role);
    Role findRoleById(Long id);
    List<Role> findAllRoles();

    void deleteRoleById(Long id);
    void deleteRole(Role role);

    void deleteAllRoles();

    long countRole();

}