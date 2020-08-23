package com.github.xuqiu.coswee.springboot;

import com.github.xuqiu.coswee.listener.CallChainAspectListener;
import com.github.xuqiu.coswee.servlet.CallChainServlet;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.extern.slf4j.Slf4j;

/**
 * TODO
 *
 * @author yinzhennan
 * @version V1.0
 * @since 2020-08-23 13:39
 */
@Slf4j
@Configuration
public class CosweeConfiguration {

    /**
     * 拦截器
     */
    @Bean
    public MethodInvocationAdepter cosweeAdvice(){
        return new MethodInvocationAdepter();
    }

    /**
     * servlet
     */
    @Bean
    public ServletRegistrationBean<CallChainServlet> heServletRegistrationBean() {
        return new ServletRegistrationBean<>(new CallChainServlet(),"/coswee");
    }

    /**
     * listener
     */
    @Bean
    public CallChainAspectListener listener(){
        return new CallChainAspectListener();
    }
}
