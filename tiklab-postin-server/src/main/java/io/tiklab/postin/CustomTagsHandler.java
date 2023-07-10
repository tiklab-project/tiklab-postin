package io.tiklab.postin;

import io.tiklab.postin.api.apix.model.Apix;
import io.tiklab.postin.api.http.definition.model.ApiRequest;
import io.tiklab.postin.api.http.definition.model.HttpApi;
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

                // 判断类头上是否有自定义的信息
                if (classComment != null && classComment.contains("@pi.protocol") && classComment.contains("@pi.groupName")) {
                    // 解析类注释
                    Map<String, String> classMap = parseClassComment(classComment);

                    //创建分组
                    Category category = new Category();
                    Workspace workspace = new Workspace();
                    workspace.setId("73c78f9e2b1c");
                    category.setWorkspace(workspace);
                    category.setName(classMap.get("groupName"));
//                    String categoryId = categoryServiceRPC().createCategory(category);

                    // 获取方法
                    List<? extends Element> members = type.getEnclosedElements();
                    for (Element member : members) {
                        if (member.getKind() == ElementKind.METHOD) {
                            ExecutableElement method = (ExecutableElement) member;
                            String methodComment = environment.getElementUtils().getDocComment(method);

                            if(methodComment!=null){

                                // 解析方法注释
                                Map<String, String> methodMap = parseMethodComment(methodComment);

                                HttpApi httpApi = new HttpApi();
                                Apix apix = new Apix();
                                Category categorySetId = new Category();
//                                categorySetId.setId(categoryId);
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
//                                saveToApiServiceRPC().saveToApi(saveToApi);
                            }
                        }
                    }
                }
            }
        }
        return true;
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
                    }
                }
            }
        }

        return methodMap;
    }

    /**
     * 递归获取目录下的java文件
     * @param directory
     * @param javaFiles
     */
    private static void findJavaFiles(File directory, List<File> javaFiles) {
        if (directory.isDirectory()) {
            File[] files = directory.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isDirectory()) {
                        findJavaFiles(file, javaFiles);

                        //获取controller层的文件
                    } else if (file.getName().endsWith("Controller.java")) {
                        javaFiles.add(file);
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {

        // 工程目录路径
        String projectDirectory = "tiklab-postin-server/src/main/java/io/tiklab/postin";

        // 创建 DocumentationTool 实例
        DocumentationTool documentationTool = ToolProvider.getSystemDocumentationTool();
        if (documentationTool == null) {
            System.out.println("未找到系统文档工具");
            return;
        }

        // 创建标准文件管理器
        StandardJavaFileManager fileManager = documentationTool.getStandardFileManager(null, null, null);

        // 创建 Doclet 实例
        CustomTagsHandler doclet = new CustomTagsHandler();

        // 获取工程下的所有 Java 文件
        List<File> javaFiles = new ArrayList<>();
        findJavaFiles(new File(projectDirectory), javaFiles);

        // 获取要处理的编译单元
        List<JavaFileObject> compilationUnits = new ArrayList<>();
        for (File javaFile : javaFiles) {
            Iterable<? extends JavaFileObject> fileObjects = fileManager.getJavaFileObjects(javaFile);
            compilationUnits.addAll((Collection<? extends JavaFileObject>) fileObjects);
        }

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
