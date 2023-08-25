package com.zti.partpicker.model;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * This class implements UserDetails interface that is being used in Spring
 * Security for authentication and endpoint security purposes.
 */
public class AccountDetails implements UserDetails {

    private final Account user;

    /**
     * Constructor of the Account details class
     * @param user Account object
     */
    public AccountDetails(Account user) {
        this.user = user;
    }

    /**
     * @return ID of current user
     */
    public Long getId() {
        return this.user.getId();
    }

    /**
     * @return Collection of granted authorities
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(user.getRole());
        System.out.println(Arrays.asList(authority));
        return Arrays.asList(authority);
    }

    /**
     * @return password of current user
     */
    @Override
    public String getPassword() {
        return user.getPassword();
    }

    /**
     * @return username of current user
     */
    @Override
    public String getUsername() {
        return user.getLogin();
    }

    /**
     * @return isAccountNonExpired = true
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * @return isAccountNonLocked = true
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * @return isCredentialsNonExpired = true
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * @return isEnabled = true
     */
    @Override
    public boolean isEnabled() {
        return true;
    }

}
