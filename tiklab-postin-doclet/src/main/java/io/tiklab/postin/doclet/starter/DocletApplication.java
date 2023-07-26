package io.tiklab.postin.doclet.starter;
import org.apache.maven.shared.invoker.*;
import java.io.*;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.*;

/**
 * main函数执行
 */
public class DocletApplication  {
    /**
     * mvn 命令执行 logger 失效， 使用system.out.println 替代
     */


    /**
     * 设置类加载器，加载所需要依赖的包
     */
    public static URLClassLoader urlClassLoader;

    public static String workspaceId;
    public static String server;
    public static String[] modulesPathArray;

    public static void main(String[] args){

        String modulesPath="D:/a-dk-sever/tiklab-postin/tiklab-postin-api;D:/a-dk-sever/tiklab-postin/tiklab-postin-server";
        modulesPathArray = modulesPath.split(";");
        workspaceId="73c78f9e2b1c";
        server="http://192.168.10.3:8090";

        try {
//            if(args.length < 3){
//                System.out.println("Error --- pom传入参数错误");
//                return;
//            }else {
//                String modulesPath=args[0];
//                modulesPathArray = modulesPath.split(";");
//                workspaceId=args[1];
//                server=args[2];
//            }


            //执行maven命令生成dependence依赖包
//            exeMaven();

            //获取URLClassLoader，用于全局引用
            urlClassLoader = getURLClassLoader();

            StaterCommon staterCommon = new StaterCommon();

            staterCommon.staterFunction();
        } catch (IOException e) {
            System.out.println("Error --- 执行错误 : "+e);
        }
    }

    /**
     * 动态引入类
     * @return
     */
    public static URLClassLoader getURLClassLoader()  {
        URL[] urls = new URL[0];
        try {
            List<URL> urlsList = new ArrayList<>();

            //获取所有依赖包
            for (String path : modulesPathArray) {
                File directory = new File(path+"/target/dependency");
                File[] jarFiles = directory.listFiles((dir, name) -> name.endsWith(".jar"));

                if (jarFiles != null) {
                    for (File jarFile : jarFiles) {
                        urlsList.add(jarFile.toURI().toURL());
                    }
                }

                File classesDirectory = new File(path+"/target/classes");
                urlsList.add(classesDirectory.toURI().toURL());
            }

            // 将 URL 列表转换为数组
            urls = urlsList.toArray(new URL[0]);
        }catch (Exception e){
            System.out.println("Error --- EXE URLClassLoader Error");
        }


        // 构造自定义类加载器
        return new URLClassLoader(urls, DocletApplication.class.getClassLoader());
    }

    /**
     * 执行maven，properties中配的modules
     */
    private static void exeMaven(){
        for (String path : modulesPathArray) {
            if(path!=null) {
                InvocationRequest request = new DefaultInvocationRequest();

                // 指定项目的 pom.xml 文件路径
                // 截取路径  debug需要改为截取
//            int lastIndex = modulesPath.lastIndexOf("/");
//            String module = modulesPath.substring(lastIndex + 1);
//            String filePath = module + "/pom.xml";

                String filePath = path + "/pom.xml";
                request.setPomFile( new File(filePath ));
                System.out.println("-------filePath-----: "+filePath);

                //执行maven的命令
                request.setGoals( Collections.singletonList( "dependency:copy-dependencies"));

                try {
                    Invoker invoker = new DefaultInvoker();
                    // 设置要执行的 MavenHome
//                invoker.setMavenHome(new File(System.getProperty("maven.home")));
                    invoker.setMavenHome(new File("D:/application/apache-maven-3.8.3"));

                    invoker.execute( request );
                } catch (MavenInvocationException e) {
                    System.out.println("Error --- 执行maven失败"+e);
                }
            }
        }
    }


}