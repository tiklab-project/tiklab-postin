package com.doublekit.apibox.client.mock.mocker;

import com.doublekit.apibox.client.mock.support.MockUtils;

import java.util.Random;

public class StringMocker {

    public static String mock(String egValue){
        if(!MockUtils.isMockExpress(egValue)){
            return egValue;
        }else{
            egValue = egValue.replaceAll("@","");
            //不同表达式解析
            if(egValue.equals("text32")){
                return text(32);
            }else{
                return null;
            }
        }
    }

    static String text(int length){
        String str="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random=new Random();
        //指定字符串长度，拼接字符并toString
        StringBuffer sb=new StringBuffer();
        for (int i = 0; i < length; i++) {
            //获取指定长度的字符串中任意一个字符的索引值
            int number=random.nextInt(str.length());
            //根据索引值获取对应的字符
            char charAt = str.charAt(number);
            sb.append(charAt);
        }
        String text = sb.toString();
        return text;
    }
}
