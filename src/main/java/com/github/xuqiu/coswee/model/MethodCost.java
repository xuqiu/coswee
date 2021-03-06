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
package com.github.xuqiu.coswee.model;

/**
 * 方法消耗
 *
 * @author yinzhennan
 * @version V1.0
 * @since 2016-11-24 10:09
 */
public class MethodCost implements java.io.Serializable {

    String fullName;
    String fatherKey;
    String rootKey;
    String methodName;
    String typeName;
    String key;
    String sql;
    int index;
    int deep;
    int costAll;
    int costOwn;
    int costChild;

    String threadName;


    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getCostAll() {
        return costAll;
    }

    public void setCostAll(int costAll) {
        this.costAll = costAll;
    }

    public String getFatherKey() {
        return fatherKey;
    }

    public void setFatherKey(String fatherKey) {
        this.fatherKey = fatherKey;
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

    public int getCostOwn() {
        return costOwn;
    }

    public void setCostOwn(int costOwn) {
        this.costOwn = costOwn;
    }

    public String getThreadName() {
        return threadName;
    }

    public void setThreadName(String threadName) {
        this.threadName = threadName;
    }

    public String getRootKey() {
        return rootKey;
    }

    public void setRootKey(String rootKey) {
        this.rootKey = rootKey;
    }

    public int getCostChild() {
        return costChild;
    }

    public void setCostChild(int costChild) {
        this.costChild = costChild;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }
}
