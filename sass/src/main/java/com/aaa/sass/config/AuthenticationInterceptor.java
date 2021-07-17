package com.aaa.sass.config;

import com.aaa.sass.annotation.NonLogin;
import com.aaa.sass.domain.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.SignatureException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author liuzhen.tian
 * @version 1.0 AuthenticationInterceptor.java  2020/12/16 13:08
 */
@Component
public class AuthenticationInterceptor implements HandlerInterceptor {


    @Resource
    private JwtConfig jwtConfig;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse httpServletResponse, Object handler) throws Exception {
        /**
         * 无需登录的接口
         */
        NonLogin nonLoginAnnotation;
        if (handler instanceof HandlerMethod) {
            //检测类注解
            nonLoginAnnotation = ((HandlerMethod) handler).getBeanType().getAnnotation(NonLogin.class);
            if (nonLoginAnnotation == null) {
                //检测方法注解
                nonLoginAnnotation = ((HandlerMethod) handler).getMethodAnnotation(NonLogin.class);
            }
        } else {
            return true;
        }

        /** 地址过滤 */
        String uri = request.getRequestURI();
        if (uri.contains("/login")) {
            return true;
        }
        /** Token 验证 */
        String token = request.getHeader(jwtConfig.getHeader());
        if (StringUtils.isEmpty(token)) {
            token = request.getParameter(jwtConfig.getHeader());
        }
        Claims claims = null;
        if (nonLoginAnnotation == null) {
            if (StringUtils.isEmpty(token)) {
                throw new SignatureException(jwtConfig.getHeader() + " 不能为空");
                // Shift.fatal(ResultCode.SYSTEM_ERROR);
            }
            try {
                claims = jwtConfig.getTokenClaim(token);
                if (claims == null || jwtConfig.isTokenExpired(claims.getExpiration())) {
                    throw new SignatureException(jwtConfig.getHeader() + "失效，请重新登录。");
                }
            } catch (Exception e) {
                throw new SignatureException(jwtConfig.getHeader() + "失效，请重新登录。");
            }
            /** 设置 identityId 用户身份ID */
            request.setAttribute("identityId", claims.getSubject());
            UserContext.setUser(new User(claims.getSubject()));
        } else {
            // 无需操作登录token的一些操作
        }


        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest,
                           HttpServletResponse httpServletResponse,
                           Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest,
                                HttpServletResponse httpServletResponse,
                                Object o, Exception e) throws Exception {
        UserContext.remove();
    }
}
