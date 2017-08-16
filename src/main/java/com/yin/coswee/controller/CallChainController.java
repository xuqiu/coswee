/*
 * Copyright (c) 2001-2017 GuaHao.com Corporation Limited. All rights reserved. 
 * This software is the confidential and proprietary information of GuaHao Company. 
 * ("Confidential Information"). 
 * You shall not disclose such Confidential Information and shall use it only 
 * in accordance with the terms of the license agreement you entered into with GuaHao.com.
 */
package com.yin.coswee.controller;

import com.alibaba.fastjson.JSON;
import com.yin.coswee.aspect.CallChainAspect;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 获取调用链信息
 *
 * @author yinzhennan
 * @version V1.0
 * @since 2017-08-14 10:55
 */
@Controller
@RequestMapping("/coswee")
public class CallChainController {

    /**
     * 获取调用链信息
     */
    @RequestMapping(value = "/getCallChainInfo")
    @ResponseBody
    public String getCallChainInfo(){
        return JSON.toJSONString(CallChainAspect.getAllCallHis());
    }
}
