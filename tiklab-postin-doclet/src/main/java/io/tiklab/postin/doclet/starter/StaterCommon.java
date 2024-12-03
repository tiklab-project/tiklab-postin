package io.tiklab.postin.doclet.starter;

import io.tiklab.postin.doclet.handler.CustomTagsHandler;


import javax.tools.DocumentationTool;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.*;

public class StaterCommon {

    /**
     * 执行
     * @throws IOException
     */
    public void staterFunction() throws IOException {

        // 创建 DocumentationTool 实例
        DocumentationTool documentationTool = ToolProvider.getSystemDocumentationTool();
        if (documentationTool == null) {return;}

        // 创建标准文件管理器
        StandardJavaFileManager fileManager = documentationTool.getStandardFileManager(
                null,
                null,
                Charset.forName("UTF-8")
        );

        // 获取要处理的编译单元
        List<JavaFileObject> compilationUnitsList = new ArrayList<>();

        // 获取工程下的所有 Java 文件
        List<File> javaFiles = new ArrayList<>();
        if(DocletApplication.modulesPathArray!=null) {
            for (String path : DocletApplication.modulesPathArray) {
                // 获取目录配置
                String dir = path+"/src/main/java";
                System.out.println(dir);
                // 加载目录中的文件
                findJavaFiles(new File(dir), javaFiles);
            }
        }

        try {
            if(javaFiles.size() > 0){
                for (File javaFile : javaFiles) {
                    Iterable<? extends JavaFileObject> fileObjects = fileManager.getJavaFileObjects(javaFile);
                    compilationUnitsList.addAll((Collection<? extends JavaFileObject>) fileObjects);
                }
            }else {
                System.out.println("javaFile为空");
                return;
            }
        }catch (Exception e){
            System.out.println("------Error javaFile------"+e);
        }


        //编译文件不为空
        if(compilationUnitsList.size() == 0){
            System.out.println("Error --- 编译的文件为空");
            return;
        }

        // 创建 classpath 字符串
        StringBuilder classpathBuilder = new StringBuilder();
        getClassPath(classpathBuilder);

        if(classpathBuilder.isEmpty()) {
            System.out.println("Error --- 依赖文件构造的classpath为空");
            return;
        }


        System.out.println("-----Doclet-DocumentationTask------");
        try {
            //option配置 classpath
            List<String> options = new ArrayList<>();
            options.add("-classpath");
            options.add(classpathBuilder.toString());

            // 创建 Doclet 实例
            CustomTagsHandler doclet = new CustomTagsHandler();
            // 创建 DocumentationTask 对象
            DocumentationTool.DocumentationTask task = documentationTool.getTask(
                    null,
                    fileManager,
                    null,
                    doclet.getClass(),
                    options,
                    compilationUnitsList
            );


            Boolean success = task.call();
            if (success) {
                System.out.println("执行成功");
            } else {
                System.out.println("Error --- 执行失败");
            }
            // 关闭文件管理器
            fileManager.close();
        }catch (Exception e){
            System.out.println("Error --- 执行错误: " + e);
        }
    }

    /**
     * 递归获取指定目录下的java文件
     * @param directory
     * @param javaFiles
     */
    private void findJavaFiles(File directory, List<File> javaFiles) {
        try {
            boolean isDir = directory.isDirectory();
            if (!isDir) {return;}

            File[] files = directory.listFiles();

            if (files == null) {
                System.out.println("is not file ");
                return;
            }

            for (File file : files) {
                if (file.isDirectory() && "model".equals(file.getName())) {
                    // 获取名为"model"的目录下的所有文件添加到列表中
                    getAllFilesInDirectory(file, javaFiles);
                }

                if (file.getName().endsWith(".java")) {
                    // 如果文件后缀为".java"，则添加到列表中
                    javaFiles.add(file);
                }

                if(file.isDirectory()){
                    findJavaFiles(file, javaFiles);
                }
            }
        }catch (Exception e){
            System.out.println("file Error "+e);
        }
    }

    private void getAllFilesInDirectory(File directory, List<File> files) {
        File[] fileList = directory.listFiles();
        if (fileList != null) {
            Collections.addAll(files, fileList);
        }

    }


    /**
     * 获取所有pox中的依赖包名称拼成字符串
     * @param classpathBuilder
     */
    private void getClassPath( StringBuilder classpathBuilder){
        for (String path : DocletApplication.modulesPathArray) {
            //自身的class
            classpathBuilder.append(path+"/target/classes;");
        }


        for (String path : DocletApplication.modulesPathArray) {
            // 获取目录中的所有 JAR 文件路径
            File directory = new File(path+"/target/dependency");
            File[] jarFiles = directory.listFiles((dir, name) -> name.endsWith(".jar"));

            if(jarFiles==null){return;}

            for (File jarFile : jarFiles) {
                classpathBuilder.append(jarFile.getAbsolutePath()).append(File.pathSeparator);
            }
        }
    }


}
