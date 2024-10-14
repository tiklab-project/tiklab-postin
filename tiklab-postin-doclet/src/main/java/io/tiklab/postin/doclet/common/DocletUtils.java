package io.tiklab.postin.doclet.common;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class DocletUtils {

    /**
     * 使用md5 获取id
     */
    public static String getIdByMd5(String data){
        String id = null;
        try {
            // 获取 MD5 算法实例
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] hashBytes = md5.digest(data.getBytes("UTF-8"));

            // 将哈希字节数组转换为正数的 BigInteger
            BigInteger bigInt = new BigInteger(1, hashBytes);
            // 将 BigInteger 转换为 32 位十六进制字符串
            String hashString = bigInt.toString(16);

            // 如果哈希字符串长度不足 32 位，则在前面补充零，使其长度为 32 位
            while (hashString.length() < 32) {
                hashString = "0" + hashString;
            }

            // 取哈希字符串的前 12 个字符作为 ID
            id = hashString.substring(0, 12);
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            System.out.println("Error --- 通过MD5获取ID失败");
        }

        return id;
    }


}
