/*
 * Copyright (c) 2001-2017 Github.com Corporation Limited. All rights reserved.
 * This software is the confidential and proprietary information of GuaHao Company. 
 * ("Confidential Information"). 
 * You shall not disclose such Confidential Information and shall use it only 
 * in accordance with the terms of the license agreement you entered into with Github.com.
 */
package com.yin.coswee.model;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;

import java.util.ArrayList;
import java.util.List;

/**
 * 树节点,用于页面展示
 *
 * @author yinzhennan
 * @version V1.0
 * @since 2017-08-15 16:53
 */
@SuppressWarnings("unused")
public class TreeNode {
    private String text;
    private String icon;
    private String color;
    private String backColor;
    private String href;
    private String sql;
    private List<String> tags;
    private List<TreeNode> nodes = new ArrayList<TreeNode>();

    @JSONField(serialize=false)
    private TreeNode parent;
    @JSONField(serialize=false)
    private String threadName;
    @JSONField(serialize=false)
    private List<CallTimes> callTimesList;

    public TreeNode(){}
    public TreeNode(String text){
        this.text = text;
    }

    public void addChild(TreeNode child){
        nodes.add(child);
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getBackColor() {
        return backColor;
    }

    public void setBackColor(String backColor) {
        this.backColor = backColor;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public List<TreeNode> getNodes() {
        return nodes;
    }

    public void setNodes(List<TreeNode> nodes) {
        this.nodes = nodes;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public TreeNode getParent() {
        return parent;
    }

    public void setParent(TreeNode parent) {
        this.parent = parent;
    }

    public String getThreadName() {
        return threadName;
    }

    public void setThreadName(String threadName) {
        this.threadName = threadName;
    }

    public List<CallTimes> getCallTimesList() {
        return callTimesList;
    }

    public void setCallTimesList(List<CallTimes> callTimesList) {
        this.callTimesList = callTimesList;
    }

    @Override
    public String toString(){
        return JSON.toJSONString(this);
    }
}
