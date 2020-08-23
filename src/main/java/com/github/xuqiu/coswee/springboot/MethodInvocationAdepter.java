package com.github.xuqiu.coswee.springboot;

import com.github.xuqiu.coswee.aspect.CallChainAspect;

import java.lang.annotation.Annotation;

import org.aopalliance.intercept.MethodInterceptor;
import org.springframework.aop.Pointcut;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.framework.autoproxy.AbstractBeanFactoryAwareAdvisingPostProcessor;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.annotation.AnnotationMatchingPointcut;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * TODO
 *
 * @author yinzhennan
 * @version V1.0
 * @since 2020-08-23 17:13
 */
@Slf4j
public class MethodInvocationAdepter extends AbstractBeanFactoryAwareAdvisingPostProcessor
    implements InitializingBean {

    @Value("${coswee.pointcut}")
    String pointcutExp;
    @Override
    public void afterPropertiesSet() throws Exception {
        //对带有myLogAnno注解的方法进行拦截
        //声明一个aspectj切点
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        //设置需要拦截的方法-用切点语言来写
        pointcut.setExpression(pointcutExp);
        this.advisor = new DefaultPointcutAdvisor(pointcut, new CallChainAspect());
        this.setProxyTargetClass(true);
    }
}