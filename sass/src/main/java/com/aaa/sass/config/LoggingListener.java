package com.aaa.sass.config;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;
import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.boot.context.logging.LoggingApplicationListener;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.Ordered;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * 通过 spring 的 监听器(Listener)功能，设置读取到的 application.yml 属性到系统属性。
 *
 * @author liuzhen.tian
 * @version 1.0 LoggingListener.java  2021/9/28 20:33
 */
public class LoggingListener implements ApplicationListener, Ordered {

    /**
     * 提供给日志文件读取配置的key，使用时需要在前面加上 sys:
     */
    private final static String LOG_PATH = "log.path";
    private final static String LOG_NAME = "log.name";

    /**
     * spring 内部设置的日志文件的配置key
     */
    private final static String SPRING_LOG_PATH_PROP = "logging.file.path";
    private final static String SPRING_LOG_NAME_PROP = "spring.application.name";


    @Override
    public void onApplicationEvent(ApplicationEvent applicationEvent) {

        if (applicationEvent instanceof ApplicationEnvironmentPreparedEvent) {
            ConfigurableEnvironment environment = ((ApplicationEnvironmentPreparedEvent) applicationEvent).getEnvironment();

            // 设置上下文变量 - 日志路径
            String filePath = environment.getProperty(SPRING_LOG_PATH_PROP);
            if (StringUtils.isNotBlank(filePath)) {
                // 若采用 System.setProperty方法，则在 log4j2-spring.xml 中用 ${sys:log.path} 获取 yml属性值
                System.setProperty(LOG_PATH, filePath);

                // 若采用 MDC.put           方法，则在 log4j2-spring.xml 中用 ${ctx:log.path} 获取 yml属性值
                MDC.put(LOG_PATH, filePath);
            }

            // 设置上下文变量 - 日志名字
            String fileName = environment.getProperty(SPRING_LOG_NAME_PROP);
            if (StringUtils.isNotBlank(fileName)) {
                // 若采用 System.setProperty方法，则在 log4j2-spring.xml 中用 ${sys:log.name} 获取 yml属性值
                System.setProperty(LOG_NAME, fileName);

                // 若采用 MDC.put           方法，则在 log4j2-spring.xml 中用 ${ctx:log.name} 获取 yml属性值
                MDC.put(LOG_NAME, fileName);
            }

        }
    }

    @Override
    public int getOrder() {
        // 当前监听器的启动顺序需要在日志配置监听器的前面，所以此处减 1
        return LoggingApplicationListener.DEFAULT_ORDER - 1;
    }
}
