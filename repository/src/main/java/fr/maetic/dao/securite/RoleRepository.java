package fr.maetic.dao.securite;


import fr.maetic.enumeration.EnumRole;
import fr.maetic.model.securite.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByRoleName(EnumRole roleName);
    Role queryByRoleName(EnumRole roleName);
    boolean existsByRoleName(EnumRole roleName);
    void deleteRoleById(Long id);
}