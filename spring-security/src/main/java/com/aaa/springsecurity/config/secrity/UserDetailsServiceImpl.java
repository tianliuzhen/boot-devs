package com.aaa.springsecurity.config.secrity;

import com.aaa.springsecurity.config.secrity.model.LoginUser;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author liuzhen.tian
 * @version 1.0 UserDetailsServiceImpl.java  2023/11/6 23:00
 */
@Service
public class UserDetailsServiceImpl  implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        if ("admin".equals(username)){
            // todo 模拟数据库根据用户id查用户详情
            return new LoginUser(username,"$2a$10$aF17k6OCsB4.nLXDnWVG/OsceCq6NE7Q4KispESMSpeuxFiKbjAJ.");
        }

        throw new UsernameNotFoundException("登录用户：" + username + " 不存在");
    }

}
