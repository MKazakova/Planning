package com.planning.plan.domain;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

@Entity // This tells Hibernate to make a table out of this class
public class Auteur implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer code_autor;

    private String login;
    private String pass;
    private boolean enabled=true;

    @ElementCollection(targetClass=Role.class, fetch=FetchType.EAGER)
    @CollectionTable(name="auteur_role", joinColumns = @JoinColumn(name="code_autor"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getPassword() {
        return pass;
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Integer getCode_autor() {
        return code_autor;
    }

    public void setCode_autor(Integer code_autor) {
        this.code_autor = code_autor;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
