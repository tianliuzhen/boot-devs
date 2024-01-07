package com.aaa.springsecurity.config.secrity;

import com.aaa.springsecurity.config.secrity.model.LoginUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author liuzhen.tian
 * @version 1.0 UserDetailsServiceImpl.java  2023/11/6 23:00
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if ("admin".equals(username)) {
            // todo 模拟数据库根据用户id查用户详情
            /**
             * 注：authorityList 表示角色和资源
             * 当 ROLE_ 为前缀时，表示角色,如：ROLE_admin，ROlE_wxuser 等等...
             *  有俩种校验方式
             * 【@Secured({"ROlE_wxuser", "ROLE_admin"})】
             * 【@PreAuthorize("hasRole('ROLE_admin,ROlE_wxuser')")】
             *
             * 而 不以ROLE_ 为前缀时，表示普通权限，如：delete，update 等等
             */
            List<GrantedAuthority> authorityList = AuthorityUtils.createAuthorityList(
                    "ROLE_admin",  // admin 角色（角色权限码）
                    "update", // 更新权限（资源权限码）
                    "delete"); // 删除权限（资源权限码）
            return new LoginUser(
                    username,
                    "$2a$10$aF17k6OCsB4.nLXDnWVG/OsceCq6NE7Q4KispESMSpeuxFiKbjAJ.",
                    authorityList);
        }

        throw new UsernameNotFoundException("登录用户：" + username + " 不存在");
    }

}
