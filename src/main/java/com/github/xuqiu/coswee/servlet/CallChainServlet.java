/*
 * Copyright (c) 2001-2017 Github.com Corporation Limited. All rights reserved.
 * This software is the confidential and proprietary information of GuaHao Company. 
 * ("Confidential Information"). 
 * You shall not disclose such Confidential Information and shall use it only 
 * in accordance with the terms of the license agreement you entered into with Github.com.
 */
package com.github.xuqiu.coswee.servlet;

import com.alibaba.fastjson.JSON;
import com.github.xuqiu.coswee.aspect.CallChainAspect;
import com.github.xuqiu.coswee.model.TreeNode;
import com.github.xuqiu.coswee.model.transform.TreeNodeTransformer;
import com.github.xuqiu.coswee.model.CallStatistics;
import com.github.xuqiu.coswee.util.FileUtil;
import com.github.xuqiu.coswee.util.FreeMakerUtil;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * servlet
 *
 * @author yinzhennan
 * @version V1.0
 * @since 2017-08-15 11:24
 */
public class CallChainServlet  extends HttpServlet {
    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        final String action = request.getParameter("action");
        if("getMethodCostInfo".equals(action)){
            getMethodCostInfo(response);
        }else if("getTreeNode".equals(action)){
            getTreeNode(response);
        }else if("getStatistics".equals(action)){
            getStatistics(response);
        }else if("clear".equals(action)){
            clear();
        }else{
            getPage(response);
        }
    }

    private void getMethodCostInfo(HttpServletResponse response) throws IOException {
        response.setHeader("Content-Type", "text/json; charset=utf-8");

        final PrintWriter writer = response.getWriter();
        writer.print(JSON.toJSONString(CallChainAspect.getAllCallHis()));
        writer.flush();
        writer.close();
    }
    private void getTreeNode(HttpServletResponse response) throws IOException {
        response.setHeader("Content-Type", "text/json; charset=utf-8");
        final PrintWriter writer = response.getWriter();
        writer.print(JSON.toJSONString(TreeNodeTransformer.transMethodCost(CallChainAspect.getAllCallHis())));
        writer.flush();
        writer.close();
    }
    private void getStatistics(HttpServletResponse response) throws IOException {
        response.setHeader("Content-Type", "text/json; charset=utf-8");
        final PrintWriter writer = response.getWriter();
        writer.print(JSON.toJSONString(CallChainAspect.getStatistics()));
        writer.flush();
        writer.close();
    }
    private void getPage(HttpServletResponse response) throws IOException {
        response.setHeader("Content-Type", "text/html; charset=utf-8");

        Template t = FreeMakerUtil.createTemplate("callChainTable.ftl");
        try {
            Map<String, Object> map = getMethodInfoMap();
            t.process(map, response.getWriter());
        } catch (TemplateException e) {
            e.printStackTrace();
        }
    }

    public static void writePage(String fullPath){
        FileUtil.checkPath(fullPath);
        Writer out = null;
        try {
            out = new OutputStreamWriter(new FileOutputStream(fullPath), "UTF-8");
            Template t = FreeMakerUtil.createTemplate("callChainTable.ftl");
            //输出文件
            Map<String, Object> map = getMethodInfoMap();
            t.process(map, out);
        } catch (UnsupportedEncodingException e) {
            //never happened
        } catch (Exception e) {
            throw new IllegalArgumentException("路径不正确,创建路径或文件失败:", e);
        }

    }

    private static Map<String, Object> getMethodInfoMap() {
        Map<String, Object> map = new HashMap<String, Object>();
        //调用连信息
        final List<TreeNode> treeNodeList = TreeNodeTransformer.transMethodCost(CallChainAspect.getAllCallHis());
        Map<String, TreeNode> treeNodeJsonMap = new LinkedHashMap<String, TreeNode>();
        for (int i = 0; i < treeNodeList.size() && i < 50; i++) {
            TreeNode treeNode = treeNodeList.get(i);
            treeNodeJsonMap.put(treeNode.getThreadName(), treeNode);
        }
        map.put("treeNodeJsonMap", treeNodeJsonMap);

        //统计信息
        final Map<String, CallStatistics> statistics = CallChainAspect.getStatistics();
        map.put("statistics", statistics.values());
        return map;
    }

    private void clear(){
        CallChainAspect.clear();
    }

}
