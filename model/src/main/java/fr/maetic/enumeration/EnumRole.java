package fr.maetic.enumeration;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;


@Getter
@AllArgsConstructor
public enum EnumRole implements GrantedAuthority {
    ROLE_ADMIN("ROLE_ADMIN", "Admin"),
    ROLE_USER("ROLE_USER", "simple utilisateur");

    private final String role;
    private final String descriptionRole;
    @Override
    public String toString() {
        return this.role;
    }

    @Override
    public String getAuthority() {
        return this.role;
    }
}
