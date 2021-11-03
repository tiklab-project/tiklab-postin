package com.doublekit.apibox.client.builder;

import com.doublekit.common.exception.ApplicationException;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

public class ApiGeneretorFreemarkerParser {

    private static Logger logger = LoggerFactory.getLogger(ApiGeneretorFreemarkerParser.class);

    /**
     * 解析freemarker sql模板
     * @param tplReader
     * @param models
     * @return
     */
    public static void parse(Reader tplReader, Object models, String targetFile){
        Configuration configuration = new Configuration();
        configuration.setObjectWrapper(new DefaultObjectWrapper());
        try {
            Template template = new Template("default", tplReader, configuration);

            //String projectPath = System.getProperty("user.dir");
            //String projectPath = new File("").getCanonicalPath();
            //String projectPath = FreemarkerGenerator.class.getResource("./").getPath();
            File genFile = new File(targetFile);
            if(!genFile.exists()){
                genFile.createNewFile();
            }
            FileWriter fileWriter = new FileWriter(genFile);
            template.process(models, fileWriter);
        } catch (IOException e) {
            throw new ApplicationException(e);
        } catch (TemplateException e) {
            throw new ApplicationException(e);
        }
    }
}
