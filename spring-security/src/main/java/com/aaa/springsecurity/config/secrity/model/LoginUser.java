package com.aaa.springsecurity.config.secrity.model;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * @author liuzhen.tian
 * @version 1.0 LoginUser.java  2023/11/6 22:14
 */
@Data
public class LoginUser implements UserDetails {
    private String userName;
    private String password;
    /**
     * 过期时间
     */
    private Long expireTime;
    /**
     * 登录时间
     */
    private Long loginTime;
    /**
     * 用户唯一标识
     */
    private String token;

    public LoginUser(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
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

    @Override
    public boolean isEnabled() {
        return true;
    }
}
