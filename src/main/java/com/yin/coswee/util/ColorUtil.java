/*
 * Copyright (c) 2001-2017 GuaHao.com Corporation Limited. All rights reserved. 
 * This software is the confidential and proprietary information of GuaHao Company. 
 * ("Confidential Information"). 
 * You shall not disclose such Confidential Information and shall use it only 
 * in accordance with the terms of the license agreement you entered into with GuaHao.com.
 */
package com.yin.coswee.util;

import java.math.BigInteger;

/**
 * 主要用于根据严重等级获取警示颜色,比如严重显示红色,轻度显示绿色
 *
 * @author yinzhennan
 * @version V1.0
 * @since 2017-08-16 17:04
 */
public class ColorUtil {
    public static void main(String[] args) {
        System.out.println("background-f: "+getTimeCostColor(0)+";");
        System.out.println("background-f: "+getTimeCostColor(10)+";");
        System.out.println("background-f: "+getTimeCostColor(50)+";");
        System.out.println("background-f: "+getTimeCostColor(100)+";");
        System.out.println("background-f: "+getTimeCostColor(200)+";");
        System.out.println("background-f: "+getTimeCostColor(400)+";");
        System.out.println("background-f: "+getTimeCostColor(800)+";");
        System.out.println("background-f: "+getTimeCostColor(1500)+";");
        System.out.println("background-f: "+getTimeCostColor(5000)+";");
        System.out.println("background-f: "+getTimeCostColor(10000)+";");
        System.out.println("background-f: "+getTimeCostColor(60000)+";");
        System.out.println("background-f: "+getTimeCostColor(1000000)+";");
//        System.out.println(Math.log(5));             //        1.6094379124341003
//        System.out.println(Math.log(50));            //        3.912023005428146
//        System.out.println(Math.log(100));           //        4.605170185988092
//        System.out.println(Math.log(200));           //        5.298317366548036
//        System.out.println(Math.log(500));           //        6.214608098422191
//        System.out.println(Math.log(1000));          //        6.907755278982137
//        System.out.println(Math.log(5000));          //        8.517193191416238
//        System.out.println(Math.log(10000));         //        9.210340371976184
//        System.out.println(Math.log(5000000));       //        15.424948470398375
//        System.out.println(Math.log(Long.MAX_VALUE));//        43.66827237527655
    }

    /**
     *
     * @param costTime 消耗时间 毫秒
     * @return 红色严重 绿色OK
     */
    public static String getTimeCostColor(int costTime){
        if(costTime <= 1) costTime = 1;
        final Double lvl = Math.log(Double.valueOf(costTime + ""));
        final String levelColor = getLevelColor("#61C400", "#FF0000", lvl.intValue() - 1);
        return levelColor;//getLevelColor(levelColor, "#FFFF55", 5);
    }

    public static String getLevelColor(String color1, String color2, int level) {
        if(level<=0) return color1;
        if(level>=10) return color2;
        final String colorPattern = "#[0-9a-fA-F]{6}";
        if(!color1.matches(colorPattern) || !color2.matches(colorPattern)){
            throw new IllegalArgumentException("color arg must like #00CAFE! but args are:" + color1 + " " + color2);
        }

        String tempColor1 = color1.replace("#", "");
        String tempColor2 = color2.replace("#", "");
        StringBuilder stringBuffer = new StringBuilder();
        stringBuffer.append("#");
        for (int i = 0; i < 3; i++) {
            final int colorInt1 = changeHex2Int(tempColor1.substring(i*2,i*2+2));
            final int colorInt2 = changeHex2Int(tempColor2.substring(i*2,i*2+2));
            int diff = (colorInt2 - colorInt1);
            int levelColorInt = colorInt1 + diff * level / 10;
            stringBuffer.append(changeInt2Hex(String.valueOf(levelColorInt)));
        }
        return stringBuffer.toString();
    }
    private static int changeHex2Int(String temp) {
        BigInteger bigInteger = new BigInteger(temp, 16);
        return Integer.valueOf(bigInteger.toString());
    }

    private static String changeInt2Hex(String temp) {
        BigInteger bigInteger = new BigInteger(temp, 10);
        String hexString = Integer.toHexString(Integer.parseInt(bigInteger.toString()));
        if(hexString.length() == 1){
            hexString = "0" + hexString;
        }
        return hexString;
    }
}
