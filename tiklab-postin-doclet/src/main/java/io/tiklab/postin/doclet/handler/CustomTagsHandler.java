package io.tiklab.postin.doclet.handler;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import io.tiklab.postin.doclet.common.DocletUtils;
import jdk.javadoc.doclet.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.lang.model.SourceVersion;
import javax.lang.model.element.*;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
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
     */
    private void parseController(TypeElement type, String classComment, DocletEnvironment environment){
        // 解析类注释
        Map<String, String> classMap = parseClassComment(classComment);

        //通过解析的类注释创建分组
        String categoryId = categoryReport(classMap);

        if(categoryId==null){
            System.out.println("Error --- categoryId返回为null");
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

                List<? extends VariableElement> parameters = method.getParameters();
                for (VariableElement parameter : parameters) {
                    String parameterType = parameter.asType().toString();
                    try {

                        Class<?> paramClass = Class.forName(parameterType);
                        Field[] declaredFields = paramClass.getDeclaredFields();


                    } catch (ClassNotFoundException e) {
                        System.out.println("获取Class失败----:"+e);
                    }
                }


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
    private String categoryReport( Map<String, String> classMap){
        //获取配置文件
        Properties props = DocletUtils.loadConfig();
        JSONObject categoryJson = new JSONObject();
        JSONObject workspaceJson = new JSONObject();
        workspaceJson.put("id",props.getProperty("workspaceId"));
        categoryJson.put("workspace",workspaceJson);
        categoryJson.put("name",classMap.get("groupName"));
        String categoryId = httpCommon("/docletReport/category", categoryJson.toJSONString());

        return categoryId;
    }

    /**
     * 解析方法上的注释创建接口
     */
    private void analyticalApi(String methodComment, Map<String, String> classMap,String categoryId){

        // 解析方法注释
        JSONObject methodJson = parseMethodComment(methodComment);

        JSONObject apiJson = new JSONObject();

        JSONObject httpApiJson = ReportData.getHttpApiJson(methodJson, classMap, categoryId);
        apiJson.put("httpApi",httpApiJson);

        JSONObject apiRequest = ReportData.getApiRequest(methodJson);
        apiJson.put("request",apiRequest);

        //通过路径md5 生成一个 id
        String path = httpApiJson.getString("path");
        String apiId = DocletUtils.getIdByMd5(path);
        apiJson.put("apiId",apiId);

        if(apiId==null){return;}

        switch (methodJson.getString("request-type")){
            case "formdata":
                ArrayList<Object> formDataJsonList = ReportData.getFormDataJson(methodJson, apiId);
                apiJson.put("formList",formDataJsonList);
                break;
            case "formUrlencoded":
                ArrayList<Object> formUrlList = ReportData.getFormUrlList(methodJson, apiId);
                apiJson.put("formUrlList",formUrlList);
                break;
            case "json":
            case "raw":
                JSONObject rawJson = ReportData.getRawJson(methodJson, apiId,modelMap);
                apiJson.put("raw",rawJson);
                break;
            default:
                break;
        }

        //上报接口
        httpCommon("/docletReport/api", apiJson.toJSONString());
    }

    /**
     * http发送请求公共方法
     */
    private String httpCommon(String path,String jsonBody) {
        try {
            String serverUrl = DocletUtils.loadConfig().getProperty("server") + path;
            //请求接口
            URL url = new URL(serverUrl);
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
     * @return
     */
    private JSONObject parseMethodComment(String comment) {
        String[] lines = comment.split("\\R");

        JSONObject methodJson = new JSONObject();

        JSONArray paramList = new JSONArray();
        for (String line : lines) {
            if (line.trim().startsWith("@pi")) {
                String[] parts = line.split(":", 2);
                if (parts.length == 2) {
                    String key = parts[0].trim();
                    String value = parts[1].trim();

                    // 处理 key 和 value
                    if ("@pi.name".equals(key)) {
                        methodJson.put("name",value);
                    } else if ("@pi.path".equals(key)) {
                        methodJson.put("path",value);
                    } else if ("@pi.method".equals(key)) {
                        methodJson.put("method",value);
                    } else if ("@pi.request-type".equals(key)) {
                        methodJson.put("request-type",value);
                    }else if ("@pi.param".equals(key)) {
                        if(value.startsWith("model=")) {
                            // 获取model名
                            String model = value.split("=")[1];
                            methodJson.put("model",model);
                        } else {
                            // 解析name/value参数
                            JSONObject jsonObject = parseParam(value);
                            paramList.add(jsonObject);
                        }
                    }
                }
            }
        }

        if(paramList.size()>0){
            // 将参数列表添加到返回值中
            methodJson.put("params", paramList);
        }


        return methodJson;
    }

    /**
     * @pi.param参数解析
     * @param param
     * @return
     */
    private JSONObject parseParam(String param){
        // 分割字符串
        String[] pairs = param.split(";");

        JSONObject jsonObject = new JSONObject();

        // 遍历键值对
        for (String pair : pairs) {
            // 分割键值对
            String[] keyValue = pair.split("=");

            // 提取键和值
            if (keyValue.length == 2) {
                String key = keyValue[0].trim();
                String value = keyValue[1].trim();

                jsonObject.put(key,value);
            }
        }

        return jsonObject;
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
