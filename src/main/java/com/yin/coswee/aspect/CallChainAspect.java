/*
 * Copyright (c) 2001-2017 Github.com Corporation Limited. All rights reserved.
 * This software is the confidential and proprietary information of GuaHao Company. 
 * ("Confidential Information"). 
 * You shall not disclose such Confidential Information and shall use it only 
 * in accordance with the terms of the license agreement you entered into with Github.com.
 */
package com.yin.coswee.aspect;

import com.yin.coswee.listener.CallChainAspectListener;
import com.yin.coswee.model.CallStatistics;
import com.yin.coswee.model.MethodCost;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.UUID;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * 调用连切片
 *
 * @author yinzhennan
 * @version V1.0
 * @since 2017-08-10 16:08
 */
public class CallChainAspect implements MethodInterceptor {
    private static final String SQL_METHOD = "com.ibatis.common.jdbc.logging.PreparedStatementLogProxy.invoke";
    private static final Set<String> EXECUTE_METHODS = new HashSet<String>();
    static{
        EXECUTE_METHODS.add("execute");
        EXECUTE_METHODS.add("executeUpdate");
        EXECUTE_METHODS.add("executeQuery");
    }

    private boolean logAble = true;
    private boolean onlyStatistics = false;

    public void setLogAble(boolean logAble) {
        this.logAble = logAble;
    }

    public void setOnlyStatistics(boolean onlyStatistics) {
        this.onlyStatistics = onlyStatistics;
    }

    private static Map<String,CallStatistics> callStatisticsMap = Collections
        .synchronizedMap(new HashMap<String, CallStatistics>());

    private static List<Map<String,MethodCost>> allCallHis= Collections
        .synchronizedList(new ArrayList<Map<String,MethodCost>>());
    public static List<Map<String,MethodCost>> getAllCallHis(){
        return allCallHis;
    }
    public static Map<String,CallStatistics> getStatistics(){
        return callStatisticsMap;
    }
    public static void clear(){
        allCallHis= Collections.synchronizedList(new ArrayList<Map<String,MethodCost>>());
        callStatisticsMap = Collections.synchronizedMap(new HashMap<String, CallStatistics>());
    }


    private static ThreadLocal<Map<String,MethodCost>> methodCostMapTL = new ThreadLocal<Map<String,MethodCost>>();
    private static ThreadLocal<Integer> indexTL = new ThreadLocal<Integer>(){
        protected synchronized Integer initialValue() {
            return 0;
        }
    };
    private static ThreadLocal<String> threadKey = new ThreadLocal<String>(){
        protected synchronized String initialValue() {
            return "";
        }
    };
    private static ThreadLocal<Stack<String>> methodStackTL = new ThreadLocal<Stack<String>>(){
        protected synchronized Stack<String> initialValue() {
            return new Stack<String>();
        }
    };
    public Object invoke(MethodInvocation method) throws Throwable {
        if(CallChainAspectListener.CONFIGURED && ! CallChainAspectListener.WEB_STARTED){
            return method.proceed();
        }
        //方法基本信息获取
        final String typeName = method.getMethod().getDeclaringClass().getName();
        final String methodName = method.getMethod().getName();
        final String methodFullName = typeName + "." + methodName;
        final String key = methodFullName+"#"+ UUID.randomUUID();
        final String sql = getSql(method);

        //将方法信息保存到threadLocal,后面格式化输出
        //先获取线程信息
        Integer index = indexTL.get();
        indexTL.set(index+1);
        Stack<String> methodStack = methodStackTL.get();

        String fatherKey = "";
        String rootKey = "";
        int deep = methodStack.size();
        if(deep==0){
            final Map<String, MethodCost> methodCostMap = new LinkedHashMap<String, MethodCost>();
            methodCostMapTL.set(methodCostMap);
            if(! onlyStatistics) {
                allCallHis.add(methodCostMap);
            }
            //线程会被重用,所以线程key里要加uuid
            threadKey.set(Thread.currentThread().getName()+"#"+new SimpleDateFormat("HH:mm:ss.SSS").format(new Date()));
        }else if(deep>0){
            fatherKey = methodStack.peek();
            rootKey = methodStack.get(0);
        }
        methodStack.push(key);



        Map<String,MethodCost> methodCostMap = methodCostMapTL.get();
        MethodCost methodCost = new MethodCost();
        methodCost.setKey(key);
        methodCost.setTypeName(typeName);
        methodCost.setMethodName(methodName);
        methodCost.setFullName(methodFullName);
        methodCost.setFatherKey(fatherKey);
        methodCost.setDeep(deep);
        methodCost.setIndex(index);
        methodCost.setThreadName(threadKey.get());
        methodCost.setRootKey(rootKey);
        methodCostMap.put(key,methodCost);


        String treeSpace = getTreeSpace(deep);
        if(logAble) {
            System.out.println(treeSpace + methodFullName + " 方法开始");
        }



        final long n1 = System.nanoTime();
        final Object result = method.proceed();
        final long n2 = System.nanoTime();



        long methodCostTime = n2 - n1;

        methodCostTime=methodCostTime/1000000;

        methodCost.setCostAll((int)methodCostTime);
        methodCost.setCostOwn(methodCost.getCostAll()-methodCost.getCostChild());
        if(fatherKey != null && fatherKey.trim().length() > 0) {
            final MethodCost fatherMethod = methodCostMap.get(fatherKey);
            fatherMethod.setCostChild(fatherMethod.getCostChild()+(int)methodCostTime);
        }


        methodStack.pop();

        if(logAble) {
            System.out.println(treeSpace + typeName + "." + methodName + " 方法结束 执行时间:  " + methodCostTime);
        }

        //记录统计信息
        statistics(methodCost);

        return result;
    }

    private String getSql(MethodInvocation method) {

        try {
            final String typeName = method.getMethod().getDeclaringClass().getName();
            final String methodName = method.getMethod().getName();
            final String methodFullName = typeName + "." + methodName;
            if(! SQL_METHOD.equals(methodFullName)){
                return null;
            }
            final Method methodSql = (Method) method.getArguments()[1];
            if(!EXECUTE_METHODS.contains(methodSql.getName())) {
                return null;
            }
            return "sql查询语句";
        } catch (Exception e) {
            return null;
        }

    }

    String getTreeSpace(int deep){
        StringBuilder result = new StringBuilder();
        for(int i = 0;i<deep;i++){
            result.append("    ");
        }
        return result.toString();
    }
    private static void statistics(MethodCost methodCost){
        final String fullName = methodCost.getFullName();
        CallStatistics callStatistics = callStatisticsMap.get(fullName);
        if(callStatistics == null){
            callStatistics = new CallStatistics(fullName);
            callStatistics.setRootInfo(new HashMap<String, CallStatistics>());
            callStatisticsMap.put(fullName, callStatistics);
        }
        final int cost = methodCost.getCostOwn();
        callStatistics.addCost(cost);
        //设置根方法信息
        final Map<String, CallStatistics> rootInfoMap = callStatistics.getRootInfo();
        final String rootName = methodCost.getRootKey().split("#")[0];
        CallStatistics rootStatistics = rootInfoMap.get(rootName);
        if(rootStatistics == null){
            rootStatistics = new CallStatistics(rootName);
            rootInfoMap.put(rootName, rootStatistics);
        }
        rootStatistics.addCost(cost);
    }
}
