/*
 * Copyright (c) 2001-2017 GuaHao.com Corporation Limited. All rights reserved. 
 * This software is the confidential and proprietary information of GuaHao Company. 
 * ("Confidential Information"). 
 * You shall not disclose such Confidential Information and shall use it only 
 * in accordance with the terms of the license agreement you entered into with GuaHao.com.
 */
package com.yin.coswee.aspect;

import com.yin.coswee.aspect.model.MethodCost;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

/**
 * 调用连切片
 *
 * @author yinzhennan
 * @version V1.0
 * @since 2017-08-10 16:08
 */
@Aspect
public class CallChainAspect {

    private static List<Map<String,MethodCost>> allCallHis= Collections
        .synchronizedList(new ArrayList<Map<String,MethodCost>>());
    public static List<Map<String,MethodCost>> getAllCallHis(){
        return allCallHis;
    }

    private static ThreadLocal<Map<String,MethodCost>> methodCostMapTL = new ThreadLocal<Map<String,MethodCost>>();
    private static ThreadLocal<Integer> indexTL = new ThreadLocal<Integer>(){
        protected synchronized Integer initialValue() {
            return 0;
        }
    };
    private static ThreadLocal<Stack<String>> methodStackTL = new ThreadLocal<Stack<String>>(){
        protected synchronized Stack<String> initialValue() {
            return new Stack<String>();
        }
    };
    @Around("within(com.greenline..*) "
        + "and !within(com.greenline.hrs.biz.external..*)"
        + "and !within(com.greenline.hrs.biz.manager.external..*)"

        + "or within(org.slf4j.impl..*)"
    )
    public Object aspectAll(ProceedingJoinPoint proceed) throws Throwable{
        //方法基本信息获取
        MethodSignature sign = (MethodSignature)proceed.getSignature();
        final String typeName = sign.getDeclaringTypeName();
        final String methodName = sign.getMethod().getName();
        final String methodFullName = typeName + "." + methodName;

        //将方法信息保存到threadLocal,后面格式化输出
        //先获取线程信息
        Integer index = indexTL.get();
        indexTL.set(index+1);
        Stack<String> methodStack = methodStackTL.get();
        String fatherName = "";
        String rootName = "";
        int deep = methodStack.size();
        if(deep==0){
            final Map<String, MethodCost> methodCostMap = new LinkedHashMap<String, MethodCost>();
            methodCostMapTL.set(methodCostMap);
            allCallHis.add(methodCostMap);
        }else if(deep>0){
            fatherName = methodStack.peek();
        }else if(deep>1){
            rootName = methodStack.get(1);
        }
        methodStack.push(methodFullName);


        Map<String,MethodCost> methodCostMap = methodCostMapTL.get();
        MethodCost methodCost = methodCostMap.get(methodFullName);
        if(methodCost == null) {
            methodCost = new MethodCost();
            methodCostMap.put(methodFullName,methodCost);
        }

        methodCost.setName(methodFullName);
        methodCost.setFatherName(fatherName);
        methodCost.setTimes(methodCost.getTimes()+1);
        methodCost.setDeep(deep);
        methodCost.setIndex(index);
        methodCost.setThreadName(Thread.currentThread().getName());
        methodCost.setRootName(rootName);
        methodCostMap.put(methodFullName,methodCost);


        String treeSpace = getTreeSpace(deep);
        System.out.println(treeSpace + methodFullName + " 方法开始");

        Object[] args = proceed.getArgs();


        final long n1 = System.nanoTime();
        final Object result = proceed.proceed(args);
        final long n2 = System.nanoTime();



        long methodCostTime = n2 - n1;

        methodCostTime=methodCostTime/1000000;

        methodCost.setCostAll(methodCost.getCostAll()+methodCostTime);
        methodCost.setCostOwn(methodCost.getCostAll()-methodCost.getCostChild());
        if(fatherName != null && fatherName.trim().length() > 0) {
            final MethodCost fatherMethod = methodCostMap.get(fatherName);
            fatherMethod.setCostChild(fatherMethod.getCostChild()+methodCostTime);
        }


        methodStack.pop();


        System.out.println(treeSpace+typeName+"."+methodName + " 方法结束 执行时间:  "+ methodCostTime);


        return result;
    }
    String getTreeSpace(int deep){
        StringBuilder result = new StringBuilder();
        for(int i = 0;i<deep;i++){
            result.append("    ");
        }
        return result.toString();
    }
}
