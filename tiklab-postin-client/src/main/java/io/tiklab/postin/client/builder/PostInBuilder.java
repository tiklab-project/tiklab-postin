package io.tiklab.postin.client.builder;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import io.tiklab.postin.client.model.ApiMeta;
import io.tiklab.postin.client.model.ApiParamMeta;
import io.tiklab.postin.client.parser.ApiParser;
import io.tiklab.postin.client.model.ApiMethodMeta;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.io.*;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

@Service
public class PostInBuilder {

    @Value("${postin.report.workspaceId:bd26c6ec5c6e}")
    private String workspaceId;

    @Value("${postin.report.server:http://192.168.10.17:8090}")
    private String server;

    private static Logger logger = LoggerFactory.getLogger(PostInBuilder.class);

    ApiParser apiParser = new ApiParser();

    private String scanPackage;

    private String docPath;

    private int formater = 2;//1:json;2:html

    public PostInBuilder scan(String scanPackage) {
        this.scanPackage = scanPackage;
        return this;
    }

    public PostInBuilder docPath(String docPath) {
        this.docPath = docPath;
        return this;
    }

    private List<ApiMeta> scanPackage(String basePackage){
        List<ApiMeta> apiMetaList =  apiParser.parseApiMeta(basePackage);

        return apiMetaList;
    }

    public JSONArray build(){
        List<ApiMeta> apiMetaList = scanPackage(scanPackage);

        ApiMetaContext.setApiMetaList(apiMetaList);

       //通过注解获取元数据信息
        Map<String,ApiMeta> apiMetaMap = new HashMap<>();
        if(apiMetaList != null){
            for(ApiMeta apiMeta:apiMetaList){
                apiMetaMap.put(apiMeta.getName(),apiMeta);
            }
            ApiMetaContext.setApiMetaMap(apiMetaMap);
        }


        GenerateOpenApi generateOpenApi = new GenerateOpenApi();
        JSONArray allModule = generateOpenApi.generateTree(apiMetaMap);
        return allModule;


//        //写入文本
//        createReportFile(apiMetaMap);
//
//
//        if(formater == 1){
//            String data = JSON.toJSONString(apiMetaList);
//            logger.info("json:{}",data);
//            saveDocToJson(docPath,data);
//        }else if(formater == 2){
//            ApiData apiData = new ApiData();
//            apiData.setApis(apiMetaList);
//            saveDocToHtml(docPath,apiData);
//        }
    }




    /**
     * 上报信息
     * @param apiMetaMap
     */
    private HashMap reportData(Map<String, ApiMeta> apiMetaMap){
        HashMap<Object, Object> allModule = new HashMap<>();

        // 使用entrySet()方法遍历HashMap的键值对
        for (Map.Entry<String, ApiMeta> entry : apiMetaMap.entrySet()) {
            JSONObject moduleJson = new JSONObject();

            ApiMeta controllerMap = entry.getValue();
            String categoryName = controllerMap.getName();
            //获取分组id
            String categoryId = getIdByMd5(categoryName);
            JSONObject categoryJson = getCategoryJson(controllerMap, categoryId);
            moduleJson.put("category",categoryJson);

            JSONArray moduleMethodList = new JSONArray();

            //接口
            List<ApiMethodMeta> apiMethodMetaList = controllerMap.getApiMethodMetaList();
            for(ApiMethodMeta apiMethodMeta:apiMethodMetaList){
                //通过路径md5 生成一个 id
                String path = apiMethodMeta.getPath();
                String apiId = getIdByMd5(path);

                if(apiId==null){continue;}

                JSONObject apiJson = new JSONObject();
                apiJson.put("apiId",apiId);

                JSONObject httpApiJson = getHttpApiJson(apiMethodMeta, categoryId);
                apiJson.put("apiBase",httpApiJson);

                JSONObject apiRequest = getApiRequest(apiMethodMeta);
                apiJson.put("request",apiRequest);

                switch (apiMethodMeta.getParamDataType()){
                    case "form-data":
                        ArrayList<Object> formDataJsonList = getFormDataJson(apiMethodMeta.getApiParamMetaList(), apiId);
                        apiJson.put("formList",formDataJsonList);
                        break;
                    case "json":
                        JSONObject rawJson = getJson(apiMethodMeta,apiId);
                        apiJson.put("raw",rawJson);
                        break;
                    default:
                        break;

                }
                moduleMethodList.add(apiJson);
            }

            moduleJson.put("moduleMethodList",moduleMethodList);

            String aPackage = controllerMap.getCls().getPackage().getName();
            allModule.put(aPackage,moduleJson);

//            httpCommon(categoryName,moduleJson.toJSONString());

        }

        return allModule;
    }



    /**
     * 创建分组
     * @param controllerMap
     * @param categoryId
     */
    private JSONObject getCategoryJson(ApiMeta controllerMap, String categoryId){

        String name = controllerMap.getName();
        String desc = controllerMap.getDesc();

        JSONObject categoryJson = new JSONObject();
        JSONObject workspaceJson = new JSONObject();
        workspaceJson.put("id", workspaceId);
        categoryJson.put("workspace",workspaceJson);
        categoryJson.put("name",name);
        categoryJson.put("id",categoryId);

        return categoryJson;
    }

    /**
     * 接口基础信息
     * @param apiMethodMeta
     * @param categoryId
     */
    public static JSONObject getHttpApiJson(ApiMethodMeta apiMethodMeta, String categoryId){

        JSONObject httpApiJson = new JSONObject();
        JSONObject apixJson = new JSONObject();
        JSONObject categoryJson = new JSONObject();
        categoryJson.put("id",categoryId);
        apixJson.put("category",categoryJson);
        apixJson.put("name",apiMethodMeta.getName());
        apixJson.put("method",apiMethodMeta.getRequestType());
        apixJson.put("protocol","http");
        httpApiJson.put("apix",apixJson);
        httpApiJson.put("path",apiMethodMeta.getPath());
        httpApiJson.put("methodType",apiMethodMeta.getRequestType());

        return httpApiJson;
    }

    /**
     * 接口请求体基础信息
     */
    public static JSONObject getApiRequest(ApiMethodMeta apiMethodMeta) {
        JSONObject apiRequest = new JSONObject();
        String bodyType = apiMethodMeta.getParamDataType();

        if("json".equals(bodyType)){
            apiRequest.put("bodyType","raw");

        }

        //这里获取的bodyTyp为form-data字符串，postin为formdata
        if("form-data".equals(bodyType)) {
            apiRequest.put("bodyType","formdata");
        }

        return apiRequest;
    }

    /**
     * 请求体
     * formdata类型
     * @param apiParamMetaList
     * @param apiId
     */
    public static ArrayList<Object> getFormDataJson(List<ApiParamMeta> apiParamMetaList, String apiId) {

        ArrayList<Object> arrayList = new ArrayList<>();

        for(ApiParamMeta apiParamMeta: apiParamMetaList){

            JSONObject formParam = new JSONObject();
            JSONObject http = new JSONObject();
            http.put("id",apiId);
            formParam.put("http",http);
            formParam.put("paramName",apiParamMeta.getName());
            String dataType = getDataType(apiParamMeta.getDataType());
            formParam.put("dataType",dataType);
            formParam.put("value",apiParamMeta.getEg());
            formParam.put("desc",apiParamMeta.getDesc());

            arrayList.add(formParam);
        }
        return arrayList;
    }

    /**
     * 处理获取的dataType
     * @param dataTypeSource
     * @return
     */
    private static String getDataType(String dataTypeSource){
        String dataTypeLow = dataTypeSource.toLowerCase();
        if (dataTypeLow.contains("int")) {
            return "int";
        } else if (dataTypeLow.contains("double")) {
            return "double";
        } else if (dataTypeLow.contains("float")) {
            return "float";
        } else if (dataTypeLow.contains("long")) {
            return "long";
        } else if (dataTypeLow.contains("char")) {
            return "char";
        } else if (dataTypeLow.contains("boolean")) {
            return "boolean";
        } else if (dataTypeLow.contains("byte")) {
            return "byte";
        }else if (dataTypeLow.contains("string")){
            return "string";
        } else if (dataTypeLow.contains("list")){
            return "array";
        }

        return "string";
    }

    /**
     * 获取json
     * @param apiMethodMeta
     * @param apiId
     * @return
     */
    private JSONObject getJson(ApiMethodMeta apiMethodMeta, String apiId) {
        JSONObject rawJson = new JSONObject();
        rawJson.put("id",apiId);

        JSONObject http = new JSONObject();
        http.put("id",apiId);
        rawJson.put("http",http);

        ApiParamMeta apiParamMeta = apiMethodMeta.getApiParamMetaList().get(0);
        String textDef = apiParamMeta.getTextDef();

        JSONArray jsonArray = JSONArray.parseArray(textDef);

        JSONObject jsonObject = loopModel(jsonArray);
        rawJson.put("raw",jsonObject.toJSONString());
        rawJson.put("type","application/json");

        return rawJson;
    }

    private JSONObject loopModel(JSONArray modelData){
        JSONObject jsonModel = new JSONObject();

        if(modelData==null){return jsonModel;}

        for (Object jsonObject:modelData){
            JSONObject modelJson = JSONObject.parseObject(jsonObject.toString());
            String name = modelJson.getString("name");
            String dataType = modelJson.getString("dataType").toLowerCase();


            if (dataType.contains("int")) {
                jsonModel.put(name,0);
            } else if (dataType.contains("double")) {
                jsonModel.put(name,0.0);
            } else if (dataType.contains("float")) {
                jsonModel.put(name,0.0f);
            } else if (dataType.contains("long")) {
                jsonModel.put(name,0L);
            } else if (dataType.contains("char")) {
                jsonModel.put(name,'\u0000');
            } else if (dataType.contains("boolean")) {
                jsonModel.put(name,false);
            } else if (dataType.contains("byte")) {
                jsonModel.put(name, "");
            }else if (dataType.contains("string")){
                jsonModel.put(name, name);
            } else if (dataType.contains("list")){
                JSONArray jsonArray1 = new JSONArray();

                if(modelJson.getString("io.tiklab.postin.test.model")!=null){
                    String string = modelJson.getString("io.tiklab.postin.test.model");
                    JSONArray jsonArray = JSONArray.parseArray(string);
                    JSONObject jsonObject1 = loopModel(jsonArray);
                    jsonArray1.add(jsonObject1);
                }

                jsonModel.put(name, jsonArray1);

            }else {

            }

            if(modelJson.getString("io.tiklab.postin.test.model")!=null){
                String string = modelJson.getString("io.tiklab.postin.test.model");
                JSONArray jsonArray = JSONArray.parseArray(string);

                JSONObject jsonObject1 = loopModel(jsonArray);

                jsonModel.put(name, jsonObject1);
            }
        }
        return jsonModel;
    }

    /**
     * http发送请求公共方法
     */
    private void httpCommon(String categoryName,String jsonBody) {
        try {
            String serverUrl = server+ "/docletReport/moduleReport";
            //请求接口
            URL url = new URL(serverUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            try(OutputStream os = connection.getOutputStream()) {
                byte[] input = jsonBody.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }

            connection.connect();

            int responseCode = connection.getResponseCode();
            if (responseCode == 200) {

                InputStream inputStream = connection.getInputStream();
                // 从输入流中读取响应
                StringBuilder response = new StringBuilder();

                Scanner scanner = new Scanner(inputStream);
                while (scanner.hasNextLine()) {
                    response.append(scanner.nextLine());
                }

                //获取创建过后返回的id
                JSONObject jsonObject = JSONObject.parseObject(response.toString());
                String data = jsonObject.getString("data");

                logger.info(categoryName+" --- report :"+data );
            } else {
                logger.info("Error --- http code is not 200 : "+responseCode );
            }

        }catch (Exception e){
            logger.info("Error --- 接口调用失败 : " + e  );
        }
    }

    /**
     * 使用md5 获取id
     */
    public static String getIdByMd5(String data){
        String id = null;
        try {
            // 获取 MD5 算法实例
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] hashBytes = md5.digest(data.getBytes(StandardCharsets.UTF_8));

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
        } catch (NoSuchAlgorithmException e) {
            logger.info("Error --- 通过MD5获取ID失败{}",e);
        }

        return id;
    }




//    private void saveDocToHtml(String docPath, ApiData apiData) {
//        //如果目录不存在，则创建
//        File docDir = new File(docPath);
//        if(!docDir.exists()){
//            docDir.mkdir();
//        }
//
//        //生成列表页
//        saveDocToHtmlForApiList(docPath, apiData);
//
//        //生成详情页
//        saveDocToHtmlForApiDetail(docPath, apiData);
//    }
//
//    /**
//     * 生成API列表页面
//     * @param docPath
//     * @param apiData
//     */
//    void saveDocToHtmlForApiList(String docPath, ApiData apiData){
//        Map<String, Object> paramMap = new HashMap<>();
//        paramMap.put("apiData", apiData);
//
//        //输出api列表页
//        InputStream ips = this.getClass().getResourceAsStream("/templates/ApiList.ftl");
//        BufferedReader tplReader = new BufferedReader(new InputStreamReader(ips));
//
//        //资源文件目录及文件路径
//        String resourceFilePath = String.format("%s/%s", docPath, "apis.html");
//        ApiGeneretorFreemarkerParser.parse(tplReader, paramMap, resourceFilePath);
//    }
//
//    void saveDocToHtmlForApiDetail(String docPath, ApiData apiData){
//        //输出api详情页
//        for(ApiMeta api:apiData.getApis()){
//            for(ApiMethodMeta method :api.getApiMethodMetaList()){
//                Map<String, Object> paramMap = new HashMap<>();
//                paramMap.put("api", api);
//                paramMap.put("method", method);
//
//                //输出api列表页
//                //模板文件路径
//                //String tplFilePath = FeniksBuilder.class.getResource("/template/apiDetail.ftl").getPath();
//                InputStream ips = this.getClass().getResourceAsStream("/templates/ApiDetail.ftl");
//                BufferedReader tplReader = new BufferedReader(new InputStreamReader(ips));
//
//                //资源文件目录及文件路径
//                String fileName = api.getName() + "-" + method.getName() + ".html";
//                String resourceFilePath = String.format("%s/%s", docPath, fileName);
//                ApiGeneretorFreemarkerParser.parse(tplReader, paramMap, resourceFilePath);
//            }
//        }
//
//    }
//
//    private void saveDocToJson(String filePath, String data) {
//        BufferedWriter writer = null;
//        File file = new File(filePath);
//        //如果文件不存在，则新建一个
//        if(!file.exists()){
//            try {
//                file.createNewFile();
//            } catch (IOException e) {
//                throw new RuntimeException("create json file failed.",e);
//            }
//        }
//        //写入
//        try {
//            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file,false), "UTF-8"));
//            writer.write(data);
//        } catch (IOException e) {
//            throw new RuntimeException("write json file failed.",e);
//        }finally {
//            try {
//                if(writer != null){
//                    writer.close();
//                }
//            } catch (IOException e) {
//                throw new RuntimeException("close write stream failed.",e);
//            }
//        }
//    }
//
//    private void createReportFile(Map<String, ApiMeta> apiMetaMap){
//        String data = JSON.toJSONString(apiMetaMap);
//        File file=new File(System.getProperty("user.dir")+"\\report.json");
//        try{
//            FileOutputStream out=new FileOutputStream(file,false);
//            out.write(data.getBytes());
//        } catch (FileNotFoundException e) {
//            throw new ApplicationException(e);
//        } catch (IOException e) {
//            throw new ApplicationException(e);
//        }
//    }

}
