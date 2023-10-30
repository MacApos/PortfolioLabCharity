package pl.coderslab.entity;

import pl.coderslab.validator.EmailAlreadyExists;
import pl.coderslab.validator.EmailNotFound;
import pl.coderslab.validator.PasswordConfirmed;
import pl.coderslab.validator.Token;
import pl.coderslab.validator.groups.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@PasswordConfirmed(groups = {Registration.class, NewPassword.class})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 60)
    @Size(min = 3, groups = Registration.class)
    @NotBlank(groups = Registration.class)
    private String username;

    @Column(nullable = false, unique = true, length = 60)
    @Email(groups = Registration.class)
    @EmailAlreadyExists(groups = Registration.class)
    @EmailNotFound(groups = PasswordReset.class)
    private String email;

    @NotNull(groups = {Registration.class, Login.class})
    @NotBlank(groups = {Registration.class, Login.class})
    @Size(min = 3, groups = {Registration.class, Login.class})
    private String password;

    @Transient
    @NotNull(groups = Registration.class)
    @NotBlank(groups = Registration.class)
    @Size(min = 3, groups = Registration.class)
    private String passwordConfirmation;

    private int enabled;

    @Transient
    @NotNull(groups = Authentication.class)
    @NotBlank(groups = Authentication.class)
    @Token(groups = Authentication.class)
    private String token;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    public String getUsername() {
        return username;
    }

    public void setUsername(String userName) {
        this.username = userName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String username) {
        this.email = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordConfirmation() {
        return passwordConfirmation;
    }

    public void setPasswordConfirmation(String passwordConfirmation) {
        this.passwordConfirmation = passwordConfirmation;
    }

    public int getEnabled() {
        return enabled;
    }

    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
