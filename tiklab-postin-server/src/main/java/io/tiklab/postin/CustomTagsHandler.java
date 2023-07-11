package io.tiklab.postin;

import com.alibaba.fastjson.JSONObject;
import io.tiklab.postin.api.apix.model.Apix;
import io.tiklab.postin.api.http.definition.model.*;
import io.tiklab.postin.api.http.definition.service.FormParamService;
import io.tiklab.postin.api.http.definition.service.FormUrlencodedService;
import io.tiklab.postin.api.http.definition.service.RawParamService;
import io.tiklab.postin.category.model.Category;
import io.tiklab.postin.category.service.CategoryService;
import io.tiklab.postin.quicktest.model.SaveToApi;
import io.tiklab.postin.quicktest.service.SaveToApiService;
import io.tiklab.postin.workspace.model.Workspace;
import io.tiklab.rpc.client.RpcClient;
import io.tiklab.rpc.client.config.RpcClientConfig;
import io.tiklab.rpc.client.router.lookup.FixedLookup;
import jdk.javadoc.doclet.*;

import javax.lang.model.SourceVersion;
import javax.lang.model.element.*;
import javax.tools.*;
import java.io.File;
import java.io.IOException;
import java.util.*;

public class CustomTagsHandler implements Doclet {

    /**
     * rpc client
     * @return
     */
    private RpcClient rpcClient(){
        RpcClientConfig rpcClientConfig = RpcClientConfig.instance();
        RpcClient rpcClient = new RpcClient(rpcClientConfig);
        return rpcClient;
    }

    /**
     * rpc 远程调用category
     */
    CategoryService categoryServiceRPC(){
        return rpcClient().getBean(CategoryService.class, new FixedLookup("http://192.168.10.3:8090"));
    }

    /**
     * rpc 远程调用saveToApiService
     */
    SaveToApiService saveToApiServiceRPC(){
        return rpcClient().getBean(SaveToApiService.class, new FixedLookup("http://192.168.10.3:8090"));
    }

    /**
     * rpc 远程调用formParamService
     */
    FormParamService formParamServiceRPC(){
        return rpcClient().getBean(FormParamService.class, new FixedLookup("http://192.168.10.3:8090"));
    }

    /**
     * rpc 远程调用formUrlencodedService
     */
    FormUrlencodedService formUrlencodedServiceRPC(){
        return rpcClient().getBean(FormUrlencodedService.class, new FixedLookup("http://192.168.10.3:8090"));
    }

    /**
     * rpc 远程调用RawParamService
     */
    RawParamService rawParamServiceRPC(){
        return rpcClient().getBean(RawParamService.class, new FixedLookup("http://192.168.10.3:8090"));
    }

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
                    //
                    List<? extends Element> members = type.getEnclosedElements();
                    if(members==null||members.size()==0){
                        continue;
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
                    modelMap.put(modelName,variableJson);
                }

                // 判断类头上是否有自定义的信息
                if (classComment != null && classComment.contains("@pi.protocol") && classComment.contains("@pi.groupName")) {
                    // 解析类注释
                    Map<String, String> classMap = parseClassComment(classComment);

                    //通过解析的类注释创建分组
                    String categoryId = createCategory(classMap);

                    // 获取方法
                    List<? extends Element> members = type.getEnclosedElements();
                    if(members==null||members.size()==0){
                        continue;
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
            }
        }

        return true;
    }

    /**
     * 通过解析的类注释创建分组
     */
    private String createCategory( Map<String, String> classMap){
        Category category = new Category();
        Workspace workspace = new Workspace();
        workspace.setId("73c78f9e2b1c");
        category.setWorkspace(workspace);
        category.setName(classMap.get("groupName"));
        String categoryId = categoryServiceRPC().createCategory(category);

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
        HttpApi httpApi = new HttpApi();
        Apix apix = new Apix();
        Category categorySetId = new Category();
        categorySetId.setId(categoryId);
        apix.setCategory(categorySetId);
        apix.setName(methodMap.get("name"));
        apix.setMethodType(methodMap.get("method"));
        apix.setProtocolType(classMap.get("protocol"));
        httpApi.setApix(apix);
        httpApi.setPath(methodMap.get("path"));
        httpApi.setMethodType(methodMap.get("method"));
        SaveToApi saveToApi = new SaveToApi();
        saveToApi.setHttpApi(httpApi);

        ApiRequest apiRequest = new ApiRequest();
        String bodyType = methodMap.get("request-type");
        if(bodyType.equals("json")){
            apiRequest.setBodyType("raw");
        }else {
            apiRequest.setBodyType(bodyType);
        }

        saveToApi.setRequest(apiRequest);


        System.out.println("name:----"+methodMap.get("name"));
        System.out.println("path:----"+methodMap.get("path"));
        System.out.println("method:----"+methodMap.get("method"));
        System.out.println("request-type:----"+methodMap.get("request-type"));

        return saveToApiServiceRPC().saveToApi(saveToApi);
//        return null;
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
                FormParam formParam = new FormParam();
                formParam.setHttp(new HttpApi().setId(apiId));
                formParam.setParamName(keyValueMap.get("name"));
                formParam.setDataType(keyValueMap.get("dataType"));
                formParam.setValue(keyValueMap.get("value"));
                formParamServiceRPC().createFormParam(formParam);
            }else {
                FormUrlencoded formUrlencoded = new FormUrlencoded();
                formUrlencoded.setHttp(new HttpApi().setId(apiId));
                formUrlencoded.setParamName(keyValueMap.get("name"));
                formUrlencoded.setDataType(keyValueMap.get("dataType"));
                formUrlencoded.setValue(keyValueMap.get("value"));
                formUrlencodedServiceRPC().createFormUrlencoded(formUrlencoded);
            }

        }
    }

    /**
     * raw参数值
     */
    private void createRawParam(Map<String, String> methodMap, String apiId){
        RawParam rawParam = new RawParam();
        rawParam.setId(apiId);
        rawParam.setHttp(new HttpApi().setId(apiId));
        if(methodMap.get("request-type").equals("json")){
            rawParam.setType("application/json");
            String model = methodMap.get("model");
            JSONObject jsonObject = modelMap.get(model);
            String jsonText = "{}";
            if(jsonObject!=null){
                jsonText = jsonObject.toJSONString();
            }
            rawParam.setRaw(jsonText);
        }else {
            rawParam.setRaw(methodMap.get("param"));
            rawParam.setType("text/plain");
        }

        rawParamServiceRPC().createRawParam(rawParam);
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

    /**
     * 递归获取指定目录下的java文件
     * @param directory
     * @param javaFiles
     */
    private static void findJavaFiles(File directory, List<File> javaFiles) {
        if (directory.isDirectory()) {
            File[] files = directory.listFiles();
            if (files != null) {
                for (File file : files) {
                    String name = file.getName();

                    if (file.isDirectory() && file.getName().equals("model")) {
                        // 获取名为"model"的目录下的所有文件添加到列表中
                        getAllFilesInDirectory(file, javaFiles);
                    } else if (file.getName().endsWith("Controller.java")) {
                        // 如果文件后缀为"Controller.java"，则添加到列表中
                        javaFiles.add(file);
                    } else if(file.isDirectory()){
                        findJavaFiles(file, javaFiles);
                    }
                }
            }
        }
    }

    private static void getAllFilesInDirectory(File directory, List<File> files) {
        File[] fileList = directory.listFiles();
        if (fileList != null) {
            for (File file : fileList) {
                files.add(file);
            }
        }
    }


    public static void main(String[] args) throws IOException {

        // 创建 DocumentationTool 实例
        DocumentationTool documentationTool = ToolProvider.getSystemDocumentationTool();
        if (documentationTool == null) {
            System.out.println("未找到文档");
            return;
        }

        // 创建标准文件管理器
        StandardJavaFileManager fileManager = documentationTool.getStandardFileManager(null, null, null);

        // 获取工程下的所有 Java 文件
        List<File> javaFiles = new ArrayList<>();

        // 获取模型
        String directory = "tiklab-postin-api/src/main/java/io/tiklab/postin";
        findJavaFiles(new File(directory), javaFiles);

        // 获取controller
        String projectDirectory = "tiklab-postin-server/src/main/java/io/tiklab/postin";
        findJavaFiles(new File(projectDirectory), javaFiles);


        // 获取要处理的编译单元
        List<JavaFileObject> compilationUnits = new ArrayList<>();
        for (File javaFile : javaFiles) {
            Iterable<? extends JavaFileObject> fileObjects = fileManager.getJavaFileObjects(javaFile);
            compilationUnits.addAll((Collection<? extends JavaFileObject>) fileObjects);
        }

        // 创建 Doclet 实例
        CustomTagsHandler doclet = new CustomTagsHandler();

        // 创建 DocumentationTask 对象
        DocumentationTool.DocumentationTask task = documentationTool.getTask(null, fileManager, null,
                doclet.getClass(), null, compilationUnits);

        Boolean success = task.call();
        if (success) {
            System.out.println("执行文档成功");
        } else {
            System.out.println("执行文档失败");
        }

        // 关闭文件管理器
        fileManager.close();
    }

}
