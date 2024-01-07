package com.aaa.springsecurity.config.secrity.handle;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * 权限校验异常：
 * 触发此异常的俩种方式：
 * 1：手动抛异常throw new AccessDeniedException("123");
 * 2：实现注解：@PreAuthorize("@ss.hasPermi('system:user:edit')")
 * <p>
 * 注意：@PreAuthorize 可能会失效
 * 1、加注解的方法使用了private修饰导致的
 * 2、未配置：@EnableGlobalMethodSecurity
 *
 * @author liuzhen.tian
 * @version 1.0 MyAccessDeniedHandler.java  2023/11/6 23:16
 */
@Component
public class MyAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
        Map<String, Object> map = new HashMap<>();
        map.put("uri", httpServletRequest.getRequestURI());
        map.put("msg", "鉴权失败");//没有足够的权限访问该资源
        httpServletResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
        httpServletResponse.setCharacterEncoding("utf-8");
        httpServletResponse.setContentType(MediaType.APPLICATION_JSON_VALUE);
        ObjectMapper objectMapper = new ObjectMapper();
        String resBody = objectMapper.writeValueAsString(map);
        PrintWriter printWriter = httpServletResponse.getWriter();
        printWriter.print(resBody);
        printWriter.flush();
        printWriter.close();
    }
}
