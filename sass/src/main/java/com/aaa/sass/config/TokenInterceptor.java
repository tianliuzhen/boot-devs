package com.aaa.sass.config;

/**
 * @author liuzhen.tian
 * @version 1.0 TokenInterceptor.java  2020/7/30 21:22
 */

import com.aaa.sass.annotation.NonLogin;
import com.aaa.sass.config.configGlobalResponse.DemoException;
import com.aaa.sass.config.configGlobalResponse.Shift;
import com.aaa.sass.domain.enums.ResultCode;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.SignatureException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@Component
public class TokenInterceptor extends HandlerInterceptorAdapter {

    @Resource
    private JwtConfig jwtConfig ;
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws SignatureException {
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
        String uri = request.getRequestURI() ;
        if (uri.contains("/login")){
            return true ;
        }
        /** Token 验证 */
        String token = request.getHeader(jwtConfig.getHeader());
        if(StringUtils.isEmpty(token)){
            token = request.getParameter(jwtConfig.getHeader());
        }
        Claims claims = null;
        if (nonLoginAnnotation == null) {
            if(StringUtils.isEmpty(token)){
                throw new SignatureException(jwtConfig.getHeader() +" 不能为空");
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
        }else {
            // 无需操作登录token的一些操作
        }


        return true;
    }
}
