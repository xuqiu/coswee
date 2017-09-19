/*
 * Copyright (c) 2001-2017 Github.com Corporation Limited. All rights reserved.
 * This software is the confidential and proprietary information of GuaHao Company. 
 * ("Confidential Information"). 
 * You shall not disclose such Confidential Information and shall use it only 
 * in accordance with the terms of the license agreement you entered into with Github.com.
 */
package com.github.xuqiu.coswee.model;

import com.github.xuqiu.coswee.util.StringUtil;

import java.util.Map;

/**
 * 方法调用统计
 *
 * @author yinzhennan
 * @version V1.0
 * @since 2017-08-21 14:10
 */
@SuppressWarnings("unused")
public class CallStatistics {
    private String name;
    private int times;
    private int minCost = 1000000;
    private int maxCost;
    private int allCost;
    //根信息,key和name为调用此方法的入口方法,统计数字为此方法的消耗情况
    private Map<String,CallStatistics> rootInfo;

    public CallStatistics(){}
    public CallStatistics(String name){
        this.name = name;
    }

    public void addCost(int cost){
        times += 1;
        minCost = cost < minCost ? cost : minCost;
        maxCost = cost > maxCost ? cost : maxCost;
        allCost += cost;
    }

    public int getAverageCost() {
        return allCost / times;
    }

    public String getShortName() {
        return StringUtil.getMethodShortName(name);
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

    public int getMinCost() {
        return minCost;
    }

    public void setMinCost(int minCost) {
        this.minCost = minCost;
    }

    public int getMaxCost() {
        return maxCost;
    }

    public void setMaxCost(int maxCost) {
        this.maxCost = maxCost;
    }

    public int getAllCost() {
        return allCost;
    }

    public void setAllCost(int allCost) {
        this.allCost = allCost;
    }

    public Map<String, CallStatistics> getRootInfo() {
        return rootInfo;
    }

    public void setRootInfo(Map<String, CallStatistics> rootInfo) {
        this.rootInfo = rootInfo;
    }
}
