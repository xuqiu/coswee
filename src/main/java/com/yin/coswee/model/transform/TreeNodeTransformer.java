/*
 * Copyright (c) 2001-2017 GuaHao.com Corporation Limited. All rights reserved. 
 * This software is the confidential and proprietary information of GuaHao Company. 
 * ("Confidential Information"). 
 * You shall not disclose such Confidential Information and shall use it only 
 * in accordance with the terms of the license agreement you entered into with GuaHao.com.
 */
package com.yin.coswee.model.transform;

import com.yin.coswee.model.MethodCost;
import com.yin.coswee.model.TreeNode;
import com.yin.coswee.util.ColorUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 将数据转换为页面的树元素
 *
 * @author yinzhennan
 * @version V1.0
 * @since 2017-08-15 17:02
 */
public class TreeNodeTransformer {
    public static TreeNode transMethodCost(MethodCost methodCost){
        TreeNode treeNode = new TreeNode();
        treeNode.setThreadName(methodCost.getThreadName());
        treeNode.setText(methodCost.getFullName());
        treeNode.setTags(Arrays.asList(String.valueOf(methodCost.getCostAll()),String.valueOf(methodCost.getCostOwn())));
        treeNode.setBackColor(ColorUtil.getTimeCostColor(methodCost.getCostOwn()));
        return treeNode;
    }

    public static TreeNode transMethodCost(Map<String,MethodCost> methodCostMap){
        TreeNode root = null;
        Map<String, TreeNode> treeNodeMap = new HashMap<String, TreeNode>();
        for (String key : methodCostMap.keySet()) {
            MethodCost methodCost = methodCostMap.get(key);
            TreeNode treeNode = transMethodCost(methodCost);
            treeNodeMap.put(methodCost.getKey(),treeNode);
            if(root == null){
                root = treeNode;
            }else{
                TreeNode parent = treeNodeMap.get(methodCost.getFatherKey());
                parent.addChild(treeNode);
            }
        }
        return root;
    }
    public static List<TreeNode> transMethodCost(List<Map<String,MethodCost>> methodCostMapList){
        if (methodCostMapList == null || methodCostMapList.isEmpty()) {
            return Collections.emptyList();
        }
        List<TreeNode> treeNodeList = new ArrayList<TreeNode>();
        for (Map<String,MethodCost> methodCostMap : methodCostMapList) {
            TreeNode treeNode = transMethodCost(methodCostMap);
            treeNodeList.add(treeNode);
        }
        return treeNodeList;

    }



}
