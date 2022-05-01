package com.doublekit.apibox.client.builder;

import com.alibaba.fastjson.JSON;
import com.doublekit.apibox.client.model.ApiMeta;
import com.doublekit.apibox.client.model.ApiMethodMeta;
import com.doublekit.apibox.client.parser.ApiParser;
import com.doublekit.core.exception.ApplicationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ApiboxBuilder {

    private static Logger logger = LoggerFactory.getLogger(ApiboxBuilder.class);

    ApiParser apiParser = new ApiParser();

    private String scanPackage;

    private String docPath;

    private int formater = 2;//1:json;2:html

    public ApiboxBuilder scan(String scanPackage) {
        this.scanPackage = scanPackage;
        return this;
    }

    public ApiboxBuilder docPath(String docPath) {
        this.docPath = docPath;
        return this;
    }

    public void build(){
        List<ApiMeta> apiMetaList = scanPackage(scanPackage);

        ApiMetaContext.setApiMetaList(apiMetaList);

        Map<String,ApiMeta> apiMetaMap = new HashMap<>();

        if(apiMetaList != null){
            for(ApiMeta apiMeta:apiMetaList){
                apiMetaMap.put(apiMeta.getName(),apiMeta);
            }
            ApiMetaContext.setApiMetaMap(apiMetaMap);
        }

        //写入文本
        createReportFile(apiMetaMap);

        /*
        if(formater == 1){
            String data = JSON.toJSONString(apiMetaList);
            logger.info("json:{}",data);
            saveDocToJson(docPath,data);
        }else if(formater == 2){
            ApiData apiData = new ApiData();
            apiData.setApis(apiMetaList);
            saveDocToHtml(docPath,apiData);
        }
         */
    }

    private List<ApiMeta> scanPackage(String basePackage){
        List<ApiMeta> apiMetaList =  apiParser.parseApiMeta(basePackage);

        return apiMetaList;
    }

    private void saveDocToHtml(String docPath, ApiData apiData) {
        //如果目录不存在，则创建
        File docDir = new File(docPath);
        if(!docDir.exists()){
            docDir.mkdir();
        }

        //生成列表页
        saveDocToHtmlForApiList(docPath, apiData);

        //生成详情页
        saveDocToHtmlForApiDetail(docPath, apiData);
    }

    /**
     * 生成API列表页面
     * @param docPath
     * @param apiData
     */
    void saveDocToHtmlForApiList(String docPath, ApiData apiData){
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("apiData", apiData);

        //输出api列表页
        InputStream ips = this.getClass().getResourceAsStream("/templates/ApiList.ftl");
        BufferedReader tplReader = new BufferedReader(new InputStreamReader(ips));

        //资源文件目录及文件路径
        String resourceFilePath = String.format("%s/%s", docPath, "apis.html");
        ApiGeneretorFreemarkerParser.parse(tplReader, paramMap, resourceFilePath);
    }

    void saveDocToHtmlForApiDetail(String docPath, ApiData apiData){
        //输出api详情页
        for(ApiMeta api:apiData.getApis()){
            for(ApiMethodMeta method :api.getApiMethodMetaList()){
                Map<String, Object> paramMap = new HashMap<>();
                paramMap.put("api", api);
                paramMap.put("method", method);

                //输出api列表页
                //模板文件路径
                //String tplFilePath = FeniksBuilder.class.getResource("/template/apiDetail.ftl").getPath();
                InputStream ips = this.getClass().getResourceAsStream("/templates/ApiDetail.ftl");
                BufferedReader tplReader = new BufferedReader(new InputStreamReader(ips));

                //资源文件目录及文件路径
                String fileName = api.getName() + "-" + method.getName() + ".html";
                String resourceFilePath = String.format("%s/%s", docPath, fileName);
                ApiGeneretorFreemarkerParser.parse(tplReader, paramMap, resourceFilePath);
            }
        }

    }

    private void saveDocToJson(String filePath, String data) {
        BufferedWriter writer = null;
        File file = new File(filePath);
        //如果文件不存在，则新建一个
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException("create json file failed.",e);
            }
        }
        //写入
        try {
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file,false), "UTF-8"));
            writer.write(data);
        } catch (IOException e) {
            throw new RuntimeException("write json file failed.",e);
        }finally {
            try {
                if(writer != null){
                    writer.close();
                }
            } catch (IOException e) {
                throw new RuntimeException("close write stream failed.",e);
            }
        }
    }

    private void createReportFile(Map<String, ApiMeta> apiMetaMap){
        String data = JSON.toJSONString(apiMetaMap);
        File file=new File(System.getProperty("user.dir")+"\\report.json");
        try{
            FileOutputStream out=new FileOutputStream(file,false);
            out.write(data.getBytes());
        } catch (FileNotFoundException e) {
            throw new ApplicationException(e);
        } catch (IOException e) {
            throw new ApplicationException(e);
        }
    }

}
