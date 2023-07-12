package io.tiklab.postin;
import org.apache.tools.ant.BuildException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import xdoclet.DocletTask;

import javax.tools.DocumentationTool;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class DemoDoclet extends DocletTask {
    /**
     * Logger for this class
     */
    private static Logger logger = LoggerFactory.getLogger(DemoDoclet.class);


    @Override
    protected void start() throws BuildException {

        logger.info("DemoDoclet start...");

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
            System.out.println("执行成功");
        } else {
            System.out.println("执行失败");
        }

        // 关闭文件管理器
        try {
            fileManager.close();
        } catch (IOException e) {
            logger.info("DemoDoclet error...");
        }
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


}