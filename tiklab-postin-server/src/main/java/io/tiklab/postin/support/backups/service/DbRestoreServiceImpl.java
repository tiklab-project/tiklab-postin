package io.tiklab.postin.support.backups.service;

import io.tiklab.core.exception.ApplicationException;
import io.tiklab.core.exception.SystemException;
import io.tiklab.postin.support.backups.model.Backups;
import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveInputStream;
import org.apache.commons.compress.compressors.gzip.GzipCompressorInputStream;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class DbRestoreServiceImpl implements DbRestoreService {

    private final static Logger logger = LoggerFactory.getLogger(DbRestoreServiceImpl.class);


    public static final String run = "run";
    public static final String success = "success";
    public static final String error = "error";

    public static final String type = "restore";

    // 备份脚本
    public static final String shScript = "backups.sh";

    // 结果文件
    public static final String logResult = "restore.txt";

    // 默认值
    public static final String defaultValues = "default";

    // 是否在执行
    public static final Map<String,Backups> execMap = new HashMap<>();

    public static final Map<String,Backups> backupsMap = new HashMap<>();


    @Value("${jdbc.url}")
    String jdbcUrl;

    @Value("${jdbc.username}")
    String username;

    @Value("${jdbc.password}")
    String password;

    @Value("${postin.backups.dir}")
    String backupsDir;

    @Value("${postin.backups.time}")
    String scheduled;

    @Value("${postin.backups.version:10}")
    String version;

    @Value("${app.name}")
    String appName;

    @Autowired
    BackupsService backupsService;

    @Override
    public void execRestore(String filePath){
        Backups backups = execMap.get(defaultValues);
        if (Objects.isNull(backups)){
            execMap.put(defaultValues,initBackups());
        }else {
            throw new ApplicationException(10000,"当前系统正在恢复中，请勿多次点击");
        }

        ExecutorService executorService = Executors.newCachedThreadPool();

        executorService.submit(() -> {
            try {
                writeLog(defaultValues,date(4)+"begin  restore......");

                writeLog(defaultValues,date(4)+"Obtain recovery files");
                File file = new File(filePath);
                if (!file.exists()){
                    throw new ApplicationException("没有找到备份文件");
                }

                // 脚本位置
                Map<String, String> dirMap = findScriptDir();
                String unzipFileDir = dirMap.get("unzipFileDir");
                String sqlFile = dirMap.get("sqlFile");

                writeLog(defaultValues,date(4)+"Create temporary directory......");

                writeLog(defaultValues,date(4)+"Unzip backup files......");
                // 解压文件
                try {
                    decompress(filePath,unzipFileDir);
                }catch (Exception e){
                    throw new ApplicationException("备份文件解压失败,message:"+e.getMessage());
                }
                writeLog(defaultValues,date(4)+"Backup file decompression completed!");

                Map<String, String> jdbcUrlMap = findJdbcUrl();

                StringBuilder parameter = new StringBuilder();
                parameter.append(" ");
                parameter.append( " -t ").append(type).append(" "); //类型为备份
                parameter.append( " -v ").append(version).append(" "); //数据库版本

                // 地址
                parameter.append( " -d ").append(dirMap.get("dir")).append(" "); //脚本地址
                parameter.append( " -B ").append(sqlFile).append(" "); // 备份文件存放地址

                // 认证信息
                parameter.append( " -u ").append(username).append(" "); //用户名
                parameter.append( " -p ").append(password).append(" "); //密码
                parameter.append( " -D ").append(jdbcUrlMap.get("db")).append(" "); // 连接的数据库名称
                parameter.append( " -s ").append(jdbcUrlMap.get("schema")).append(" "); // 连接的数据库模式名称
                parameter.append( " -i ").append(jdbcUrlMap.get("ip")).append(" "); // 服务器ip
                parameter.append( " -P ").append(jdbcUrlMap.get("port")).append(" "); // 服务器端口

                writeLog(defaultValues,date(4)+"Starting database recovery......");
                Runtime rt = Runtime.getRuntime();
                try {
                    String order = "sh " + dirMap.get("backupsScript") + parameter;
                    logger.info("执行恢复命令：{}",order);
                    Process process = rt.exec(order);
                    readExecResult(process,defaultValues);
                } catch (Exception e) {
                    throw new SystemException(e);
                }
                writeLog(defaultValues,date(4)+"Starting database recovery completed!");

                writeLog(defaultValues,date(4)+"Clean cache files.....");

                // 删除原备份文件
                File unzipFile = new File(unzipFileDir);
                if (unzipFile.exists()){
                    try {
                        FileUtils.deleteDirectory(unzipFile);
                    } catch (IOException e) {
                        writeLog(defaultValues,date(4)+"删除恢复文件失败,message:"+e.getMessage());
                        throw new SystemException(e);
                    }
                }
                writeLog(defaultValues,date(4) + "Clean cache files completed!");

                execEnd(defaultValues,true,null);
            }catch (Exception e){
                logger.info("异常：{}",e.getMessage());
                execEnd(defaultValues,false,"备份失败！");
                throw new SystemException(e);
            }
        });
    }

    @Override
    public Backups findRestoreResult(){
        Backups backups = execMap.get(defaultValues);
        if (Objects.isNull(backups)){
            return backupsService.findLastBackups(type);
        }else {
            return backups;
        }
    }

    @Override
    public String uploadBackups(String fileName, InputStream inputStream) {
        Map<String, String> dirMap = findScriptDir();
        String tempFileDir = dirMap.get("tempFileDir");

        File tempFile = new File(tempFileDir);

        try {
            FileUtils.deleteDirectory(tempFile);
        } catch (IOException e) {
            throw new ApplicationException(e);
        }

        if (!tempFile.exists()){
            tempFile.mkdirs();
        }

        File file = new File(tempFileDir + "/" + fileName);
        if (file.exists()){
            file.delete();
        }

        try {
            Files.copy(inputStream, Paths.get(file.getAbsolutePath()));
        }catch (Exception e){
            logger.error("文件写入失败，{}",e.getMessage());
            throw new ApplicationException(e);
        }
        return file.getAbsolutePath();
    }

    /**
     * 初始化备份信息
     * @return 备份信息
     */
    public Backups initBackups(){
        Backups backups = new Backups();
        backups.setRunState(run);
        backups.setCreateTime(date(0));
        backups.setDir(backupsDir);
        backups.setType(type);
        // 备份状态
        Backups lastRestore = backupsService.findLastBackups(type);
        Backups lastBackups = backupsService.findLastBackups("backups");

        backupsMap.put(defaultValues+"backups",lastBackups);

        if (!Objects.isNull(lastRestore)){
            backups.setId(lastRestore.getId());
            return backups;
        }
        logger.info("创建备份记录！");
        String backupsId = backupsService.createBackups(backups);
        backups.setId(backupsId);
        return backups;
    }

    /**
     * 获取脚本地址
     * @return 脚本地址
     */
    public Map<String,String> findScriptDir(){

        // 获取启动文件地址
        String appHome = System.getProperty("APP_HOME");
        if (Objects.isNull(appHome)){
            throw new SystemException("Failed to obtain application location!");
        }

        //判断文件夹是否存在
        File file = new File(appHome);
        if (!file.exists()){
            throw new SystemException("application address not found!");
        }
        String parentPath = file.getParentFile().getParent();

        Map<String,String> map = new HashMap<>();

        // 系统文件分隔符
        String separator = System.getProperty("file.separator");

        // 获取脚本地址
        String scriptDir = parentPath + separator + "bin";

        // 文件上传路径
        String tempFileDir = parentPath + separator + "temp";
        File tempFile = new File(tempFileDir);
        if (!tempFile.exists()){
            tempFile.mkdirs();
        }

        // 解压后sql文件地址
        String sqlFile = parentPath + separator + "temp" + separator + appName + "_db_backups.sql";

        // 备份脚本
        String backupsScript = scriptDir + separator + shScript;

        // 日志脚本
        String logFile = scriptDir + separator + logResult;

        File dirFile = new File(scriptDir);
        if (!dirFile.exists()){
            throw new SystemException("Failed to obtain script information!");
        }

        map.put("dir",parentPath); // 程序主目录
        map.put("scriptDir",scriptDir); //可执行脚本目录

        map.put("logFile",logFile); //日志文件地址
        map.put("backupsScript",backupsScript); //备份脚本文件地址
        map.put("unzipFileDir",tempFileDir); //文件解压目录
        map.put("sqlFile",sqlFile); //解压后sql文件地址
        map.put("tempFileDir",tempFileDir); //文件解压目录

        return map;
    }

    /**
     * 解析jdbcUrl
     * @return 解析后信息
     */
    public Map<String,String> findJdbcUrl(){

        Map<String,String> map = new HashMap<>();

        Pattern pattern = Pattern.compile("jdbc:postgresql://(.*):(.*)/(.*)\\?");
        Matcher matcher = pattern.matcher(jdbcUrl);

        if (matcher.find()) {
            String ip = matcher.group(1);
            String port = matcher.group(2);
            String database = matcher.group(3);

            map.put("ip",ip);
            map.put("port",port);
            map.put("db",database);

            // 从参数中获取currentSchema
            String schema;
            if(jdbcUrl.contains("currentSchema=")) {
                schema = jdbcUrl.substring(jdbcUrl.indexOf("currentSchema=") + "currentSchema=".length());
                schema = schema.substring(0, schema.indexOf("&"));
            }else {
                schema = "public";
            }
            map.put("schema",schema);
        }
        return map;
    }

    /**
     * 读取执行结果
     * @param process 执行实例
     */
    public void readExecResult(Process process,String values) {

        //转换流
        InputStream inputStream = process.getInputStream();
        InputStream errInputStream = process.getErrorStream();

        InputStreamReader inputStreamReader ;
        BufferedReader bufferedReader ;
        if (inputStream == null){
            inputStreamReader = encode(errInputStream);
        }else {
            inputStreamReader = encode(inputStream);
        }

        String s;
        bufferedReader = new BufferedReader(inputStreamReader);

        try {

            //读取执行信息
            while ((s = bufferedReader.readLine()) != null) {
                writeLog(values, date(4)+s);
            }

            //读取err执行信息
            inputStreamReader = encode(errInputStream);
            bufferedReader = new BufferedReader(inputStreamReader);

            while ((s = bufferedReader.readLine()) != null) {
                writeLog(values, date(4)+s);
            }
            // 关闭
            inputStreamReader.close();
            bufferedReader.close();

        } catch (Exception e){
            logger.error("读取执行信息失败！{}",e.getMessage());
            writeLog(values, "读取执行信息失败！");
            process.destroy();
            throw new SystemException(e);
        }
        process.destroy();
    }

    /**
     * 格式化输出流
     * @param inputStream 流  GBK,US-ASCII,ISO-8859-1,ISO-8859-1,UTF-16BE ,UTF-16LE, UTF-16,UTF-8
     * @return 输出流
     */
    public InputStreamReader encode(InputStream inputStream){
        String type = "2";
        String property = System.getProperty("os.name");
        String[] s1 = property.split(" ");
        if (s1[0].equals("Windows")){
            type = "1";
        }

        if (type.equals("1")){
            return new InputStreamReader(inputStream, Charset.forName("GBK"));
        }else {
            return new InputStreamReader(inputStream, StandardCharsets.UTF_8);
        }
    }

    /**
     * 返回系统时间
     * @param type 时间类型 1.(yyyy-MM-dd HH:mm:ss) 2.(yyyy-MM-dd) 3.(HH:mm:ss) 4.([format]) 5.(HH:mm)
     * @return 时间
     */
    public String date(int type){
        switch (type) {
            case 1 -> {
                return new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(new Date());
            }
            case 4 -> {
                String format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
                return "[" + format + "]" + "  ";
            }
            default -> {
                return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
            }
        }
    }

    /**
     * 写入日志
     * @param key key
     * @param values 日志内容
     */
    public void writeLog(String key,String values){
        Backups backups = execMap.get(key);
        String log = backups.getLog();
        if (Objects.isNull(log)){
            backups.setLog(values);
        } else {
            backups.setLog(log +"\n" + values);
        }
        execMap.put(key, backups);
    }

    /**
     * 执行结束
     * @param key key
     */
    public void execEnd(String key,boolean state,String message){
        Backups backups = execMap.get(key);
        if (state){
            logger.info("运行成功！");
            backups.setRunState(success);
            writeLog(defaultValues,date(4)+"Restore successful！");
        }else {
            logger.info("运行失败！");
            writeLog(defaultValues,date(4) + "error:" + message);
            writeLog(defaultValues,date(4)+"Restore error！");
            backups.setRunState(error);
        }

        // 创建新的备份记录
        Backups backups1 = backupsMap.get(defaultValues + "backups");
        logger.info("创建新的备份记录:{}",backups1.getId());
        backupsService.deleteBackups(backups1.getId());
        backupsService.createBackups(backups1);

        // 创建信息恢复记录
        String backupsId = backupsService.createBackups(backups);
        logger.info("创建信息恢复记录:{}",backupsId);
        execMap.remove(key);
        backupsMap.remove(defaultValues + "backups");
    }


    /**
     * 解压文件
     * @param zipFile 需要解压的文件
     * @param destDir 解压地址
     * @throws Exception 解压失败
     */
    public static void decompress(String zipFile, String destDir) throws Exception {

        logger.info("解压文件：{}，解压地址：{}",zipFile,destDir);

        try (FileInputStream fis = new FileInputStream(zipFile);
             GzipCompressorInputStream gzip = new GzipCompressorInputStream(fis);
             TarArchiveInputStream tais = new TarArchiveInputStream(gzip)) {

            TarArchiveEntry entry;

            // 迭代压缩包中的每个条目
            while ((entry = tais.getNextTarEntry()) != null) {

                // 如果是目录,创建目录
                if (entry.isDirectory()) {
                    new File(destDir + "/" + entry.getName()).mkdirs();
                } else {

                    // 是文件,写入文件数据
                    int count;
                    byte data[] = new byte[2048];

                    FileOutputStream fos = new FileOutputStream(destDir + "/" + entry.getName());
                    BufferedOutputStream dest = new BufferedOutputStream(fos);

                    while ((count = tais.read(data)) != -1) {
                        dest.write(data, 0, count);
                    }
                    dest.close();
                }
            }
        } catch (Exception e) {
            throw new Exception(e);
        }
    }



}
