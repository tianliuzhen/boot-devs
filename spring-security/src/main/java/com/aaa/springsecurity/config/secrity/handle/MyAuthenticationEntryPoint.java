package com.aaa.springsecurity.config.secrity.handle;

import com.aaa.springsecurity.config.secrity.model.HttpStatus;
import com.aaa.springsecurity.config.secrity.util.ServletUtils;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;

/**
 * 认证失败处理类 返回未授权
 * <p>
 * {
 * "Content-Type": "application/x-www-form-urlencoded",
 * "Authorization": "eyJhbGciOiJIUzUxMiJ9.eyJsb2dpbl91c2VyX2tleSI6IjZmMzM3ODA3LTU0YWEtNDcxZS05ZjFhLTg1YmFiMzRlZjE5ZSJ9.WCOG-b3bvz3bKuaGrNFJ0stwC-UraV14ZvMq55BKVU_0qzgB-E6WOlPIK249tqy70pegWwtaOvsWPrhiLlrYiA"
 * }
 * 意思就是：Authorization；里面的token解析失败过期，错误，丢失，等等...
 *
 * @author ruoyi
 */
@Component
public class MyAuthenticationEntryPoint implements AuthenticationEntryPoint, Serializable {
    private static final long serialVersionUID = -8970718410437077606L;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e)
            throws IOException {
        int code = HttpStatus.UNAUTHORIZED;
        String msg = String.format("请求访问：%s，认证失败，无法访问系统资源", request.getRequestURI());
        ServletUtils.renderString(response, msg);
    }
}
