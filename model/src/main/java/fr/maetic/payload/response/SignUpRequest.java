package fr.maetic.payload.response;

/**
 * @author sarrenne - Thione SARR
 * @date 03/02/2023 14:37
 */
import java.util.Set;

public class SignUpRequest {

    private String username;


    private String email;

    private String password;
    private Set<String> roles;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }
}