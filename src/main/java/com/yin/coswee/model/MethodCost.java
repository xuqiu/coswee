/*
 * Project: hrs-trunk
 * 
 * File Created at 2016-11-24
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
package com.yin.coswee.model;

/**
 * TODO
 *
 * @author yinzhennan
 * @version V1.0
 * @since 2016-11-24 10:09
 */
public class MethodCost implements java.io.Serializable {

    String name;
    int times;

    String fatherName;
    String rootName;
    int index;
    int deep;
    long costAll;
    long costOwn;
    long costChild;

    String threadName;


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

    public long getCostAll() {
        return costAll;
    }

    public void setCostAll(long costAll) {
        this.costAll = costAll;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getDeep() {
        return deep;
    }

    public void setDeep(int deep) {
        this.deep = deep;
    }

    public long getCostOwn() {
        return costOwn;
    }

    public void setCostOwn(long costOwn) {
        this.costOwn = costOwn;
    }

    public String getThreadName() {
        return threadName;
    }

    public void setThreadName(String threadName) {
        this.threadName = threadName;
    }

    public String getRootName() {
        return rootName;
    }

    public void setRootName(String rootName) {
        this.rootName = rootName;
    }

    public long getCostChild() {
        return costChild;
    }

    public void setCostChild(long costChild) {
        this.costChild = costChild;
    }
}
