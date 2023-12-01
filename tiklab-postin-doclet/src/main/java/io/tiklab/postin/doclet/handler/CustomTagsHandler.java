package io.tiklab.postin.doclet.handler;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import io.tiklab.postin.doclet.common.DocletGetModel;
import io.tiklab.postin.doclet.common.DocletUtils;
import io.tiklab.postin.doclet.starter.DocletApplication;
import jdk.javadoc.doclet.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.*;
import javax.lang.model.type.DeclaredType;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class CustomTagsHandler implements Doclet {

    //用于存储所有的模型
    public static HashMap<String, JSONObject> modelMap=new HashMap<String, JSONObject>();

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
            if (element instanceof TypeElement type) {

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

        JSONObject moduleJson = new JSONObject();

        String groupName = classMap.get("groupName");
        //获取分组id
        String categoryId = DocletUtils.getIdByMd5(groupName);
        //通过解析的类注释获取分组信息
        JSONObject categoryJson = getCategoryJson(classMap, categoryId);
        moduleJson.put("category",categoryJson);

        // 获取方法
        List<? extends Element> members = type.getEnclosedElements();
        if(members==null||members.size()==0){
            return;
        }

        JSONArray moduleMethodList = new JSONArray();
        for (Element member : members) {
            if (member.getKind() == ElementKind.METHOD) {
                ExecutableElement method = (ExecutableElement) member;

                //获取当前方法的注释
                String methodComment = environment.getElementUtils().getDocComment(method);

                if(methodComment==null){
                    continue;
                }

                //获取接口信息
                JSONObject apiJson = analyticalApi(methodComment, classMap, categoryId, method);

                moduleMethodList.add(apiJson);
            }
        }

        moduleJson.put("moduleMethodList",moduleMethodList);

        httpCommon(groupName,moduleJson.toJSONString());
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
                parseVariableComment(variableComment,variableJson,variable);
            }
        }

        //获取模型名称
        String modelName = extractTagValue(classComment,"@pi.model:");
        //获取的模型存在内存中
        modelMap.put(modelName,variableJson);
    }


    /**
     * 通过解析的类注释信息
     */
    private JSONObject getCategoryJson(Map<String, String> classMap, String categoryId){

        JSONObject categoryJson = new JSONObject();
        JSONObject workspaceJson = new JSONObject();
        workspaceJson.put("id", DocletApplication.workspaceId);
        categoryJson.put("workspace",workspaceJson);
        categoryJson.put("name",classMap.get("groupName"));
        categoryJson.put("id",categoryId);

        return categoryJson;
    }

    /**
     * 解析方法上的注释
     * @return
     */
    private JSONObject analyticalApi(String methodComment, Map<String, String> classMap, String categoryId, ExecutableElement method){

        // 解析方法注释
        JSONObject methodJson = parseMethodComment(methodComment);

        JSONObject apiJson = new JSONObject();

        JSONObject httpApiJson = ReportData.getHttpApiJson(methodJson, classMap, categoryId);
        apiJson.put("apiBase",httpApiJson);

        JSONObject apiRequest = ReportData.getApiRequest(methodJson);
        apiJson.put("request",apiRequest);

        //通过路径md5 生成一个 id
        String path = httpApiJson.getJSONObject("apix").getString("path");
        String apiId = DocletUtils.getIdByMd5(path);
        apiJson.put("apiId",apiId);

        if(apiId==null){
            return methodJson;
        }

        if(methodJson.getString("request-type")==null){
            System.out.println(methodJson.getString("path")+"--- maybe annotation definition error");
        }else {
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
                    JSONObject rawJson = ReportData.getRawJson(methodJson, apiId,method);
                    apiJson.put("raw",rawJson);
                    break;
                default:
                    break;
            }
        }


        //响应信息
        JSONObject responseJson = getResponseJson(method);
        apiJson.put("response",responseJson);

       return apiJson;
    }

    /**
     * 获取响应信息
     * @param method
     * @return
     */
    private JSONObject getResponseJson(ExecutableElement method) {
        DeclaredType returnType = (DeclaredType) method.getReturnType();
        TypeElement simpleName = (TypeElement) returnType.asElement();
        JSONObject jsonData = DocletGetModel.loopModel(simpleName.toString(),0);

        JSONObject jsonText = jsonToSchema(jsonData);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name","成功");
        jsonObject.put("httpCode",200);
        jsonObject.put("dataType","json");
        jsonObject.put("jsonText",jsonText.toJSONString());

        return jsonObject;
    }


    public JSONObject jsonToSchema(JSONObject json) {
        JSONObject schema = new JSONObject();
//        schema.put("$schema", "http://json-schema.org/draft-04/schema#");
        schema.put("type", "object");

        JSONObject properties = new JSONObject();
        for(String key : json.keySet()) {
            Object value = json.get(key);

            if(value instanceof JSONObject) {
                // 对象类型,递归转换
                properties.put(key, jsonToSchema((JSONObject)value));

            } else {
                // 基本类型
                JSONObject propSchema = new JSONObject();
                propSchema.put("type", getType(value));
                properties.put(key, propSchema);
            }
        }

        schema.put("properties", properties);

        return schema;
    }

    private String getType(Object value) {

        if (JSONObject.class.equals(value.getClass())) {
            return "object";
        } else if (JSONArray.class.equals(value.getClass())) {
            return "array";
        } else if (Integer.class.equals(value.getClass()) || Long.class.equals(value.getClass())) {
            return "integer";
        } else if (Double.class.equals(value.getClass())) {
            return "number";
        } else if (Boolean.class.equals(value.getClass())) {
            return "boolean";
        }
        return "string";

    }

    /**
     * http发送请求公共方法
     */
    private void httpCommon(String categoryName,String jsonBody) {
        try {
            String serverUrl = DocletApplication.server+ "/docletReport/moduleReport";
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

                System.out.println(categoryName+" --- report :"+data );
            } else {
                System.out.println("Error --- http code is not 200 : "+responseCode );
            }

        }catch (Exception e){
            System.out.println("Error --- 接口调用失败 : " + e  );
        }
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
                    } else if ("@pi.methodType".equals(key)) {
                        methodJson.put("methodType",value);
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
     * @param variable
     * @return
     */
    private void parseVariableComment(String comment, JSONObject jsonObject, VariableElement variable) {
        //全路径模型名称
        String fullModelName = variable.asType().toString();

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

                        if(modelMap.get(fullModelName)==null){
                            JSONObject jsonObject1 = DocletGetModel.loopModel(fullModelName,0);

                            jsonObject.put(value,jsonObject1);
                        }else {
                            jsonObject.put(value,modelMap.get(fullModelName));
                        }
                    }
                }
            }
        }

        jsonObject.put(map.get("name"),map.get("value"));
    }




}
