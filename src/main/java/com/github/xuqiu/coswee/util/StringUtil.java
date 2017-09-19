/*
 * Copyright (c) 2001-2017 Github.com Corporation Limited. All rights reserved.
 * This software is the confidential and proprietary information of GuaHao Company. 
 * ("Confidential Information"). 
 * You shall not disclose such Confidential Information and shall use it only 
 * in accordance with the terms of the license agreement you entered into with Github.com.
 */
package com.github.xuqiu.coswee.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串处理
 *
 * @author yinzhennan
 * @version V1.0
 * @since 2017-08-21 14:34
 */
public class StringUtil {
    public static String getMethodShortName(String methodName) {
        Pattern pattern = Pattern.compile(".*\\.([^.]*\\.[^.]*)");
        Matcher matcher = pattern.matcher(methodName);
        if (matcher.matches()) {
            return matcher.group(1);
        }
        return methodName;
    }
}
