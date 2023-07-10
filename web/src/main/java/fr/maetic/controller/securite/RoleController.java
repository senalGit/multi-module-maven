package fr.maetic.controller.securite;

import fr.maetic.model.securite.Role;
import fr.maetic.service.securite.RoleService;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("role")
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping("/save")
    public Role save(@RequestBody Role role){
        return roleService.saveRole(role);
    }

    @GetMapping("/{id}")
    public Role getById(@PathVariable(value = "id") Long id){
        return roleService.findRoleById(id);
    }

    @GetMapping("/liste")
    public List<Role> getAll(){
        return roleService.findAllRoles();
    }

    @DeleteMapping("/supprimer/{id}")
    public void deleteById(@PathVariable(value = "id") Long id){
        roleService.deleteRoleById(id);
    }

    @DeleteMapping("/supprimer/tout")
    public void deleteAll(){
        roleService.deleteAllRoles();
    }

    @GetMapping("/count")
    public long count(){
        return roleService.countRole();
    }
}