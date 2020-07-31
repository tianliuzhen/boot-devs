
package com.zhibi.doushuextract.config.resp;


/**
 * description: 描述
 *
 * @author 田留振(liuzhen.tian @ haoxiaec.com)
 * @version 1.0
 * @date 2020/1/14
 */


import com.alibaba.fastjson.JSON;
import com.zhibi.doushuextract.domain.enums.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.servlet.http.HttpServletRequest;


/**
 *  统一异常处理及返回对象封装
 * @author liuzhen.tian
 * @version 1.0 HelloWordController.java  2020/7/31 11:34
 */

@Slf4j
@ControllerAdvice
public class ResponseAdvice implements ResponseBodyAdvice<Object> {

    public static final String HTTP_RESULT = "com.zhibi.doushuextract.config.configGlobalResponse.HttpResult";
    @Autowired
    HttpServletRequest httpServletRequest;


    /**
     这个方法表示对于哪些请求要执行beforeBodyWrite，返回true执行，返回false不执行
     过滤是否是json的数据
     */

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> aClass) {
        final String returnName = returnType.getParameterType().getName();

        return !HTTP_RESULT.equals(returnName)
                && !"org.springframework.http.ResponseEntity".equals(returnName);
    }

    /**
     对于返回的对象如果不是最终对象ResponseResult，则选包装一下
     */
    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType,
                                  MediaType selectedContentType,
                                  Class selectedConverterType,
                                  ServerHttpRequest request,
                                  ServerHttpResponse response) {

        /**
         *  基本基本数据类型也可以直接返回，唯独String 类型
         * 如果检测到是字符串直接返回字符串，否则会报错
         * 具体原因是，内部没有做判断，字符串还是走的 对象的转换接口，所以就会异常
         */

        if(body instanceof String) {
            return JSON.toJSONString(HttpResult.ok(body));
        }else{
            //返回对象封装
            return HttpResult.ok(body);
        }
    }



   /**
     * 异常日志记录
     */

    private void logErrorRequest(Exception e) {
        log.error("报错API URL:{}", httpServletRequest.getRequestURL().toString());
        log.error("异常:{}", e.getMessage());
    }


   /**
     * 参数未通过@Valid验证异常，
     */

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    private HttpResult methodArgumentNotValid(MethodArgumentNotValidException exception) {
        logErrorRequest(exception);
        return HttpResult.fail(ResultCode.INVALID_PARAM);
    }


   /**
     * 参数格式有误
     */

    @ExceptionHandler({MethodArgumentTypeMismatchException.class, HttpMessageNotReadableException.class})
    @ResponseBody
    private HttpResult typeMismatch(Exception exception) {
        logErrorRequest(exception);
        return HttpResult.fail(ResultCode.MISTYPE_PARAM);
    }


    /**
      * 缺少参数
      */

    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseBody
    private HttpResult missingServletRequestParameter(MissingServletRequestParameterException exception) {
        logErrorRequest(exception);
        return HttpResult.fail(ResultCode.MISSING_PARAM);
    }


   /**
     * 不支持的请求类型
     */

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseBody
    private HttpResult httpRequestMethodNotSupported(HttpRequestMethodNotSupportedException exception) {
        logErrorRequest(exception);
        return HttpResult.fail(ResultCode.UNSUPPORTED_METHOD);
    }


   /**
     * 业务层异常
     */

    @ExceptionHandler(DemoException.class)
    @ResponseBody
    private HttpResult serviceExceptionHandler(DemoException exception) {
        logErrorRequest(exception);
        return HttpResult.fail(exception.getErrorCode(), exception.getErrorMsg());
    }

}

