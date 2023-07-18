package io.tiklab.postin.doclet.starter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.*;

/**
 * main函数执行
 */
public class DocletApplication  {
    /**
     * mvn 命令执行 logger 失效， 使用system.out.println 替代
     */
    private static Logger logger = LoggerFactory.getLogger(DocletApplication.class);

    public static void main(String[] args){
        StaterCommon staterCommon = new StaterCommon();
        try {
            staterCommon.staterFunction();
        } catch (IOException e) {
            logger.info("执行错误",e);
            System.out.println("执行错误");
        }

    }

}