package io.tiklab.postin.doclet.handler;

import com.alibaba.fastjson.JSONObject;
import io.tiklab.postin.doclet.common.DocletUtils;
import io.tiklab.postin.doclet.starter.DocletApplication;
import jdk.javadoc.doclet.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.lang.model.SourceVersion;
import javax.lang.model.element.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

public class CustomTagsHandler implements Doclet {

    /**
     * Logger for this class
     */
    private static Logger logger = LoggerFactory.getLogger(CustomTagsHandler.class);


    //用于存储所有的模型
    private HashMap<String, JSONObject> modelMap=new HashMap<String, JSONObject>();

    private Reporter reporter;

    @Override
    public void init(Locale locale, Reporter reporter) {
        this.reporter = reporter;
    }

    @Override
    public String getName() {
        return "CustomTagsHandler";
    }

    @Override
    public Set<? extends Option> getSupportedOptions() {
        return Collections.emptySet();
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latest();
    }

    @Override
    public boolean run(DocletEnvironment environment) {
        Set<? extends Element> elements = environment.getSpecifiedElements();
        for (Element element : elements) {
            if (element instanceof TypeElement) {
                TypeElement type = (TypeElement) element;
                String classComment = environment.getElementUtils().getDocComment(type);

                //获取模型注释
                if(classComment != null && classComment.contains("@pi.model")){
                    parseModel(type,classComment,environment);
                }

                // 判断controller类头上是否有自定义的信息
                if (classComment != null && classComment.contains("@pi.protocol") && classComment.contains("@pi.groupName")) {
                    parseController(type,classComment,environment);

                }
            }
        }

        return true;
    }


    /**
     * 解析controller 信息
     * @param type
     * @param classComment
     * @param environment
     */
    private void parseController(TypeElement type, String classComment, DocletEnvironment environment){
        // 解析类注释
        Map<String, String> classMap = parseClassComment(classComment);

        //通过解析的类注释创建分组
        String categoryId = createCategory(classMap);

        if(categoryId==null){
            return;
        }

        // 获取方法
        List<? extends Element> members = type.getEnclosedElements();
        if(members==null||members.size()==0){
            return;
        }

        for (Element member : members) {
            if (member.getKind() == ElementKind.METHOD) {
                ExecutableElement method = (ExecutableElement) member;
                //获取当前方法的注释
                String methodComment = environment.getElementUtils().getDocComment(method);

                if(methodComment==null){
                    continue;
                }

                analyticalApi(methodComment,classMap,categoryId);
            }
        }
    }

    /**
     * 解析 模型 信息
     */
    private void parseModel(TypeElement type, String classComment, DocletEnvironment environment) {
        List<? extends Element> members = type.getEnclosedElements();
        if(members==null||members.size()==0){
            return;
        }

        //模型下的所有变量
        JSONObject variableJson = new JSONObject();
        for (Element member : members) {
            if (member.getKind() == ElementKind.FIELD) {
                VariableElement  variable  = (VariableElement) member;
                //获取当前变量的注释
                String variableComment = environment.getElementUtils().getDocComment(variable);

                if(variableComment==null){
                    continue;
                }

                //注释处理
                parseVariableComment(variableComment,variableJson);
            }
        }

        //获取模型名称
        String modelName = extractTagValue(classComment,"@pi.model:");
        //获取的模型存在内存中
        modelMap.put(modelName,variableJson);
    }


    /**
     * 通过解析的类注释创建分组
     */
    private String createCategory( Map<String, String> classMap){
        //获取配置文件
        Properties props = DocletUtils.loadConfig();
        JSONObject categoryJson = new JSONObject();
        JSONObject workspaceJson = new JSONObject();
        workspaceJson.put("id",props.getProperty("workspaceId"));
        categoryJson.put("workspace",workspaceJson);
        categoryJson.put("name",classMap.get("groupName"));
        String categoryId = httpCommon("/category/createCategory", categoryJson.toJSONString());

        return categoryId;
    }

    /**
     * 解析方法上的注释创建接口
     */
    private void analyticalApi(String methodComment, Map<String, String> classMap,String categoryId){

        // 解析方法注释
        Map<String, String> methodMap = parseMethodComment(methodComment);

        //创建接口
        String apiId = createApi(methodMap, classMap,categoryId);

        if(apiId==null){
            return;
        }

        switch (methodMap.get("request-type")){
            case "formdata":
            case "formUrlencoded":
                createFormApi(methodMap,apiId);
                break;
            case "json":
            case "raw":
                createRawParam(methodMap,apiId);
                break;
        }
    }

    /**
     * 创建接口
     * @param methodMap
     * @param classMap
     * @return 接口id
     */
    private String createApi(Map<String, String> methodMap, Map<String, String> classMap,String categoryId){

        JSONObject httpApiJson = new JSONObject();
        JSONObject apixJson = new JSONObject();
        JSONObject categoryJson = new JSONObject();
        categoryJson.put("id",categoryId);
        apixJson.put("category",categoryJson);
        apixJson.put("name",methodMap.get("name"));
        apixJson.put("method",methodMap.get("method"));
        apixJson.put("protocol",classMap.get("protocol"));
        httpApiJson.put("apix",apixJson);
        httpApiJson.put("path",methodMap.get("path"));
        httpApiJson.put("methodType",methodMap.get("method"));

        JSONObject saveToApiJson = new JSONObject();
        saveToApiJson.put("httpApi",httpApiJson);

        JSONObject apiRequest = new JSONObject();
        String bodyType = methodMap.get("request-type");
        if(bodyType.equals("json")){
            apiRequest.put("bodyType","raw");
        }else {
            apiRequest.put("bodyType",bodyType);
        }

        saveToApiJson.put("request",apiRequest);


        String apiId = httpCommon("/quickTest/saveToApi", saveToApiJson.toJSONString());

        return apiId;
    }

    /**
     * 创建表单格式
     * @param methodMap
     * @param apiId
     */
    private void createFormApi(Map<String, String> methodMap, String apiId) {
        String param = methodMap.get("param");
        if(param!=null){

            HashMap<String, String> keyValueMap = parseParam(param);
            if(methodMap.get("request-type").equals("formdata")){
                JSONObject formParam = new JSONObject();

                formParam.put("http",new JSONObject().put("id",apiId));
                formParam.put("name",keyValueMap.get("name"));
                formParam.put("dataType",keyValueMap.get("dataType"));
                formParam.put("dataType",keyValueMap.get("value"));

                httpCommon("/formParam/createFormParam", formParam.toJSONString());
            }else {
                JSONObject formUrlencoded = new JSONObject();

                formUrlencoded.put("http",new JSONObject().put("id",apiId));
                formUrlencoded.put("name",keyValueMap.get("name"));
                formUrlencoded.put("dataType",keyValueMap.get("dataType"));
                formUrlencoded.put("dataType",keyValueMap.get("value"));

                httpCommon("/formUrlencoded/createFormUrlencoded", formUrlencoded.toJSONString());
            }
        }
    }

    /**
     * raw参数值
     */
    private void createRawParam(Map<String, String> methodMap, String apiId){
        JSONObject rawParam = new JSONObject();
        JSONObject http = new JSONObject();
        http.put("id",apiId);

        rawParam.put("http",http);
        rawParam.put("id",apiId);
        if(methodMap.get("request-type").equals("json")){
            String model = methodMap.get("model");
            //从内存中获取模型
            JSONObject jsonObject = modelMap.get(model);
            String jsonText = "{}";
            if(jsonObject!=null) {
                jsonText = jsonObject.toJSONString();
            }
            rawParam.put("raw",jsonText);
            rawParam.put("type","application/json");

        }else {
            rawParam.put("raw",methodMap.get("param"));
            rawParam.put("type","text/plain");
        }

        httpCommon("/rawParam/createRawParam", rawParam.toJSONString());

    }

    /**
     * http发送请求公共方法
     */
    private String httpCommon(String path,String jsonBody) {
        try {
            //请求接口
            URL url = new URL("http://192.168.10.3:8090"+path);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            try(OutputStream os = connection.getOutputStream()) {
                byte[] input = jsonBody.getBytes("utf-8");
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
                String Id = jsonObject.getString("data");

                return Id;
            } else {
                logger.info("http code is not 200");
                System.out.println("http code is not 200" );
            }

        }catch (Exception e){
            logger.info("接口调用失败:{}",e);
            System.out.println("接口调用失败 : " + e  );
            return null;
        }

        return null;
    }

    /**
     * 解析类注释
     * @param classComment
     */
    private Map<String, String> parseClassComment(String classComment) {
        String protocol = extractTagValue(classComment, "@pi.protocol:");
        String groupName = extractTagValue(classComment, "@pi.groupName:");
        HashMap<String, String> classMap = new HashMap<>();
        classMap.put("protocol",protocol);
        classMap.put("groupName",groupName);

        return classMap;
    }

    /**
     * 获取类的注释值
     * @param comment
     * @param tagName
     * @return
     */
    private String extractTagValue(String comment, String tagName) {
        // 从注释中提取标签的值
        String tagValue = null;
        int startIndex = comment.indexOf(tagName);
        if (startIndex != -1) {
            int endIndex = comment.indexOf('\n', startIndex);
            if (endIndex != -1) {
                tagValue = comment.substring(startIndex + tagName.length(), endIndex).trim();
            }
        }
        return tagValue;
    }

    /**
     * 获取方法注释
     * @param comment
     */
    private Map<String, String> parseMethodComment(String comment) {
        String[] lines = comment.split("\\R");

        HashMap<String, String> methodMap = new HashMap<>();
        for (String line : lines) {
            if (line.trim().startsWith("@pi")) {
                String[] parts = line.split(":", 2);
                if (parts.length == 2) {
                    String key = parts[0].trim();
                    String value = parts[1].trim();

                    // 处理 key 和 value
                    if (key.equals("@pi.name")) {
                        methodMap.put("name",value);
                    } else if (key.equals("@pi.path")) {
                        methodMap.put("path",value);
                    } else if (key.equals("@pi.method")) {
                        methodMap.put("method",value);
                    } else if (key.equals("@pi.request-type")) {
                        methodMap.put("request-type",value);
                    }else if (key.equals("@pi.param")) {
                        //解析是否param中含有model字段，如果有设置为模型参数
                        HashMap<String, String> paramMap = parseParam(value);
                        if(paramMap.get("model")!=null){
                            methodMap.put("model",paramMap.get("model"));
                        }else {
                            methodMap.put("param",value);
                        }

                    }
                }
            }
        }

        return methodMap;
    }

    /**
     * @pi.param参数解析
     * @param param
     * @return
     */
    private HashMap<String, String> parseParam(String param){
        // 分割字符串
        String[] pairs = param.split(";");

        HashMap<String, String> keyValueMap = new HashMap<>();

        // 遍历键值对
        for (String pair : pairs) {
            // 分割键值对
            String[] keyValue = pair.split("=");

            // 提取键和值
            if (keyValue.length == 2) {
                String key = keyValue[0].trim();
                String value = keyValue[1].trim();

                keyValueMap.put(key,value);
            }
        }

        return keyValueMap;
    }

    /**
     * 获取模型的字段
     * @param comment
     * @param jsonObject
     * @return
     */
    private void parseVariableComment(String comment, JSONObject jsonObject) {
        String[] lines = comment.split("\\R");

        HashMap<String, String> map = new HashMap<>();

        for (String line : lines) {
            if (line.trim().startsWith("@pi")) {
                String[] parts = line.split(":", 2);
                if (parts.length == 2) {
                    String key = parts[0].trim();
                    String value = parts[1].trim();

                    // 处理 key 和 value
                    if (key.equals("@pi.name")) {
                        map.put("name",value);
                    } else if (key.equals("@pi.value")) {
                        map.put("value",value);
                    } else if (key.equals("@pi.model")) {
                        // 例 Workspace, {}

                        if(modelMap.get(value)==null){
                            //把首字母改为小写
                            char[] chars = value.toCharArray();
                            chars[0] = Character.toLowerCase(value.charAt(0));
                            String newValue = new String(chars);

                            jsonObject.put(newValue,"{}");
                        }else {
                            //把首字母改为小写
                            char[] chars = value.toCharArray();
                            chars[0] = Character.toLowerCase(value.charAt(0));
                            String newValue = new String(chars);

                            jsonObject.put(newValue,modelMap.get(value));
                        }
                    }
                }
            }
        }

        jsonObject.put(map.get("name"),map.get("value"));
    }


}
