package mk.ukim.finki.wpproekt.model;

import org.springframework.security.core.GrantedAuthority;

public enum RoleEnumeration implements GrantedAuthority {

    MANAGER, EMPLOYEE, GUEST;

    @Override
    public String getAuthority() {
        return name();
    }
}
