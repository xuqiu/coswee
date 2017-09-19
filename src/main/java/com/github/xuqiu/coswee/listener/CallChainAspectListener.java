/*
 * Copyright (c) 2001-2017 Github.com Corporation Limited. All rights reserved.
 * This software is the confidential and proprietary information of GuaHao Company. 
 * ("Confidential Information"). 
 * You shall not disclose such Confidential Information and shall use it only 
 * in accordance with the terms of the license agreement you entered into with Github.com.
 */
package com.github.xuqiu.coswee.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * web容器是否启动完成
 *
 * @author yinzhennan
 * @version V1.0
 * @since 2017-08-11 16:41
 */
public class CallChainAspectListener implements ServletContextListener {
    public static boolean CONFIGURED = false;
    public CallChainAspectListener(){
        CONFIGURED =true;
    }
    public static boolean WEB_STARTED = false;
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        WEB_STARTED = true;
    }

    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
