/*
 * Project: wcg
 * 
 * File Created at 2016-12-14
 * 
 * Copyright 2012-2015 Greenline.com Corporation Limited.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * Greenline Company. ("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Greenline.com.
 */
package com.yin.coswee.util;

import java.io.File;
import java.io.IOException;
import java.util.Locale;

import freemarker.template.Configuration;
import freemarker.template.Template;

/**
 *
 * @author yinzhennan
 * @version V1.0
 * @since 2016-12-14 16:34
 */
public class FreeMakerUtil {
    public static Template createTemplate(String templatePath){
        //为了兼容 这里不加参数
        Configuration cfg = new Configuration();
        try {
            //cfg.setClassForTemplateLoading(FreeMakerUtil.class,"/template");

            cfg.setDirectoryForTemplateLoading(new File("/Users/yinzhennan/work/github/coswee/src/main/resources/template"));
            cfg.setEncoding(Locale.CHINA,"utf-8");
            return cfg.getTemplate(templatePath);
        } catch (IOException e) {
            throw new RuntimeException("模板配置路径有误!!!",e);
        }
    }
}
