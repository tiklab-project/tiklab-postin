package io.tiklab.postin.doclet.common;

import io.tiklab.postin.doclet.starter.DocletApplication;

import java.io.InputStream;
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
        }

        return props;
    }

}
