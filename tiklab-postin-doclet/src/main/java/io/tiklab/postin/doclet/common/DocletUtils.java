package io.tiklab.postin.doclet.common;

import io.tiklab.postin.doclet.starter.DocletApplication;
import org.apache.commons.codec.binary.Hex;

import java.io.InputStream;
import java.security.MessageDigest;
import java.util.Properties;

public class DocletUtils {
    /**
     *  获取配置文件
     */
    public static Properties loadConfig()  {
        Properties props = new Properties();
        try {
            InputStream input = DocletApplication.class.getClassLoader().getResourceAsStream("docletConfig.properties");
            props.load(input);
            input.close();
        }catch (Exception e){
            System.out.println("读取properties文件失败");

            return null;
        }

        return props;
    }

    /**
     * 使用md5 获取id
     */
    public static String getIdByMd5(String data){
        String id = null;
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] hashBytes = md5.digest(data.getBytes("UTF-8"));

            String hashString = Hex.encodeHexString(hashBytes);
            id = hashString.substring(0, 12);

            if(id.length() < 12) {
                id = "00000000000".substring(0, 12 - id.length()) + id;
            }
        }catch (Exception e){
            System.out.println("通过MD5获取ID失败");
        }

        return id;
    }


}
