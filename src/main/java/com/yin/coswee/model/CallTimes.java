/*
 * Copyright (c) 2001-2017 Github.com Corporation Limited. All rights reserved.
 * This software is the confidential and proprietary information of GuaHao Company. 
 * ("Confidential Information"). 
 * You shall not disclose such Confidential Information and shall use it only 
 * in accordance with the terms of the license agreement you entered into with Github.com.
 */
package com.yin.coswee.model;

import com.yin.coswee.util.StringUtil;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 调用次数统计类
 *
 * @author yinzhennan
 * @version V1.0
 * @since 2017-08-18 15:52
 */
@SuppressWarnings("unused")
public class CallTimes {

    private String name;
    private int times=0;
    private int cost=0;

    public CallTimes(){}
    public CallTimes(String name){
        this.name = name;
    }

    public void addTimes(){
        this.times++;
    }
    public void addCost(int cost){
        this.cost += cost;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTimes() {
        return times;
    }

    public void setTimes(int times) {
        this.times = times;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public String getShortName() {
        return StringUtil.getMethodShortName(name);
    }
}
