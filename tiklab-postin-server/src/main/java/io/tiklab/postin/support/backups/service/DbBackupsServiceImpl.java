package io.tiklab.postin.support.backups.service;

import io.tiklab.core.exception.ApplicationException;
import io.tiklab.core.exception.SystemException;
import io.tiklab.postin.support.backups.model.Backups;
import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveOutputStream;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.GZIPOutputStream;

@Service
public class DbBackupsServiceImpl implements DbBackupsService {

    private final static Logger logger = LoggerFactory.getLogger(DbBackupsServiceImpl.class);

    private static final String run = "run";
    private static final String success = "success";
    private static final String error = "error";

    private static final String type = "backups";


    // 备份脚本
    private static final String shScript = "backups.sh";

    // 结果文件
    private static final String logResult = "result.txt";

    // 默认值
    private static final String defaultValues = "default";
    // 是否在执行
    private static final Map<String, Backups> execMap = new HashMap<>();

    @Value("${jdbc.url}")
    String jdbcUrl;

    @Value("${jdbc.username}")
    String username;

    @Value("${jdbc.password}")
    String password;

    @Value("${postin.backups.dir}")
    String backupsDir;

    @Value("${postin.backups.version:10}")
    String version;

    @Value("${app.name}")
    String appName;


    @Autowired
    BackupsService backupsService;


    @Override
    public void execBackups(){
        Backups backups = execMap.get(defaultValues);

        if (Objects.isNull(backups)){
            execMap.put(defaultValues,initBackups());
        }else {
            throw new ApplicationException(10000,"当前系统正在备份中，请勿多次点击");
        }

        ExecutorService executorService = Executors.newCachedThreadPool();
        // 执行备份
        executorService.submit(() -> {
          try {
              writeLog(defaultValues,date(4)+"begin backups......");

              // 脚本执行参数
              StringBuilder parameter = new StringBuilder();

              // 脚本位置
              Map<String, String> dirMap = findScriptDir();
              parameter.append(" ");
              parameter.append( " -d ").append(dirMap.get("dir")).append(" "); // 程序主目录
              parameter.append( " -B ").append(dirMap.get("sqlImportFile")).append(" "); // 备份文件存放地址
              parameter.append( " -t ").append(type).append(" "); //类型为备份
              parameter.append( " -v ").append(version).append(" "); //数据库版本

              parameter.append( " -u ").append(username).append(" "); //用户名
              parameter.append( " -p ").append(password).append(" "); //密码

              // 解析jdbc信息
              Map<String, String> jdbcUrlMap = findJdbcUrl();
              parameter.append( " -i ").append(jdbcUrlMap.get("ip")).append(" "); // 服务器ip
              parameter.append( " -P ").append(jdbcUrlMap.get("port")).append(" "); // 服务器端口
              parameter.append( " -D ").append(jdbcUrlMap.get("db")).append(" "); // 连接的数据库名称
              parameter.append( " -s ").append(jdbcUrlMap.get("schema")).append(" "); // 连接的数据库模式名称

              Runtime rt = Runtime.getRuntime();
              try {
                  String order = "sh " + dirMap.get("backupsScript") + parameter;
                  logger.info("执行备份命令：{}",order);
                  Process process = rt.exec(order);
                  readExecResult(process,defaultValues);
              } catch (Exception e) {
                  execEnd(defaultValues,false,"脚本执行失败："+e.getMessage());
                  throw new SystemException(e);
              }

              writeLog(defaultValues,date(4) + "Start compressing files......");

              String tarBackupsDir = dirMap.get("tarBackupsDir");

              File file = new File(tarBackupsDir);
              // 压缩文件
              try {
                  compress(tarBackupsDir , dirMap.get("tarBackupsFile"));
              } catch (IOException e) {
                  // 删除旧的文件
                  try {
                      FileUtils.deleteDirectory(file);
                  } catch (IOException e1) {
                      execEnd(defaultValues,false,"删除旧文件失败：" + e1.getMessage());
                      throw new SystemException(e1);
                  }
                  execEnd(defaultValues,false,"压缩文件失败：" + e.getMessage());
                  throw new SystemException(e);
              }

              writeLog(defaultValues,date(4)+"File compression completed!");

              writeLog(defaultValues,date(4)+"Clear cache information......");

              // 删除旧的文件
              try {
                  FileUtils.deleteDirectory(file);
              } catch (IOException e) {
                  execEnd(defaultValues,false,"删除旧文件失败：" + e.getMessage());
                  throw new SystemException(e);
              }
              writeLog(defaultValues,date(4)+"Cache information clearing completed!");
              execEnd(defaultValues,true,null);
          }catch (Exception e){
              execEnd(defaultValues,false,"备份失败！");
          }
        });
    }

    @Override
    public Backups findBackupsResult(){
        Backups backups = execMap.get(defaultValues);
        if (Objects.isNull(backups)){
            return backupsService.findLastBackups(type);
        }else {
            return backups;
        }
    }

    @Override
    public void updateBackups(Boolean state){

        // 正在运行
        Backups backups = execMap.get(defaultValues);
        if (!Objects.isNull(backups)){
            backups.setScheduled(state);
            execMap.put(defaultValues,backups);
            return;
        }

        // 存在备份记录
        Backups lastBackups = backupsService.findLastBackups(type);
        if (!Objects.isNull(lastBackups)){
            lastBackups.setScheduled(state);
            backupsService.updateBackups(lastBackups);
            return;
        }

        // 不存在备份记录
        Backups backupss = new Backups();
        backupss.setScheduled(state);
        backupss.setType(type);
        backupsService.createBackups(backupss);
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
        Backups lastBackups = backupsService.findLastBackups(type);
        if (Objects.isNull(lastBackups)){
            backups.setScheduled(false);
        }else {
            backups.setScheduled(lastBackups.getScheduled());
            backups.setId(lastBackups.getId());
            return backups;
        }

        String backupsId = backupsService.createBackups(backups);
        backups.setId(backupsId);
        return backups;
    }

    /**
     * 获取脚本地址
     * @return 脚本地址
     */
    public Map<String,String> findScriptDir(){

        Map<String,String> map = new HashMap<>();
        String fileSeparator = System.getProperty("file.separator");

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


        // 获取脚本地址
        String scriptDir = parentPath + fileSeparator + "bin";

        // 备份脚本
        String backupsScript = scriptDir + fileSeparator + shScript;
        // 日志脚本
        String logFile = scriptDir + fileSeparator + logResult;

        File dirFile = new File(scriptDir);
        if (!dirFile.exists()){
            throw new SystemException("Failed to obtain script information!");
        }

        // 创建备份文件夹
        String tarBackupsDir = backupsDir + fileSeparator + "backups" + fileSeparator;
        File file1 = new File(tarBackupsDir);
        if (!file1.exists()){
            file1.mkdirs();
        }
        String format = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(new Date());
        String tarBackupsFile = tarBackupsDir + appName + "_backups_"+format+".tar.gz";

        // 创建压缩文件夹
        String dbDir = parentPath + fileSeparator + "db";
        File dbDirFile = new File(dbDir);
        if (!dbDirFile.exists()){
            dbDirFile.mkdirs();
        }
        String sqlImportFile = dbDir + fileSeparator  + appName +  "_db_backups.sql";

        map.put("dir",parentPath); // 程序主目录
        map.put("scriptDir",scriptDir); //脚本地址

        map.put("logFile",logFile); //日志文件
        map.put("backupsScript",backupsScript); //备份脚本

        map.put("sqlImportFile",sqlImportFile); // sql导出文件
        map.put("tarBackupsDir",dbDir); // 备份文件夹
        map.put("tarBackupsFile",tarBackupsFile); // tar包保存位置

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
        int findSystemType = 2;
        String property = System.getProperty("os.name");
        if (property.split(" ")[0].equals("Windows")){
            findSystemType  = 1;
        }

        if (findSystemType == 1){
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
            backups.setRunState(success);
            writeLog(defaultValues,date(4)+"Backup successful！");
        }else {
            writeLog(defaultValues,date(4) + "error:"+message);
            writeLog(defaultValues,date(4)+"Backup error！");
            backups.setRunState(error);
        }
        backupsService.updateBackups(backups);
        execMap.remove(key);
    }

    /**
     * 压缩目录
     * @param srcFolder  需要压缩的目录
     * @param zipPath 压缩后的位置
     * @throws IOException 压缩失败
     */
    public static void compress(String srcFolder, String zipPath) throws IOException {

        logger.info("压缩文件夹：{}，压缩源路径：{}",srcFolder,zipPath);

        // 创建压缩包输出流
        FileOutputStream f = new FileOutputStream(zipPath);
        GZIPOutputStream gzip = new GZIPOutputStream(f);
        BufferedOutputStream bos = new BufferedOutputStream(gzip);
        TarArchiveOutputStream taos = new TarArchiveOutputStream(bos);

        // 设置压缩包格式
        taos.setLongFileMode(TarArchiveOutputStream.LONGFILE_GNU);
        taos.setBigNumberMode(TarArchiveOutputStream.BIGNUMBER_STAR);
        taos.setAddPaxHeadersForNonAsciiNames(true);

        // 递归压缩文件夹中的所有文件
        compressFolder(srcFolder, taos);

        // 关闭流
        taos.finish();
        taos.close();
    }

    /**
     * 递归压缩文件夹中的所有文件
     * @param srcFolder 需要压缩的目录
     * @param tags 压缩包格式
     * @throws IOException 压缩失败
     */

    private static void compressFolder(String srcFolder, TarArchiveOutputStream tags) throws IOException {

        File folder = new File(srcFolder);

        // 遍历文件夹下所有文件
        for (File file : folder.listFiles()) {

            // 如果是文件夹,递归压缩
            if (file.isDirectory()) {
                compressFolder(file.getAbsolutePath(), tags);
            } else {
                // 添加文件到压缩包
                TarArchiveEntry tarEntry = new TarArchiveEntry(file, file.getName());
                tags.putArchiveEntry(tarEntry);

                IOUtils.copy(new FileInputStream(file), tags);
                tags.closeArchiveEntry();
            }
        }
    }




}
