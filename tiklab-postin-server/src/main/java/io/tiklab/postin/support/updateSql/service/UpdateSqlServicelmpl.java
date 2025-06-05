package io.tiklab.postin.support.updateSql.service;

import io.tiklab.toolkit.beans.BeanMapper;
import io.tiklab.toolkit.beans.annotation.Mapper;
import io.tiklab.toolkit.beans.annotation.Mapping;
import io.tiklab.toolkit.beans.annotation.Mappings;
import io.tiklab.core.exception.ApplicationException;
import io.tiklab.dal.jpa.JpaTemplate;
import io.tiklab.dal.jpa.annotation.Column;
import io.tiklab.dal.jpa.annotation.Entity;
import io.tiklab.dal.jpa.annotation.Id;
import io.tiklab.dal.jpa.annotation.Table;
import io.tiklab.postin.support.updateSql.UpdateSqlService;
import io.tiklab.toolkit.resource.AnnotationResourceResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class UpdateSqlServicelmpl implements UpdateSqlService {

    @Autowired
    private JpaTemplate jpaTemplate;

    private static final Logger logger = LoggerFactory.getLogger(UpdateSqlServicelmpl.class);

    private final ExecutorService executorService = Executors.newCachedThreadPool();

    private final String basePackage ="io.tiklab";

    private final Map<String,Integer> idMap = new HashMap<>();

    private final Map<String,Map<String ,Integer>> idLinkMap = new HashMap<>();

    private final Map<String,Map<String ,Integer>> idOtherMap = new HashMap<>();


    @Override
    public void updateSql(){
        findSetClass();
        findSql();
        //处理不是model中不是map映射的id
        Map<String,Integer> othermap = new HashMap<>();
        othermap.put("domainId",12);
        idOtherMap.put("DmRoleEntity",othermap);

        Map<String,Integer> othermaps = new HashMap<>();
        othermaps.put("domainId",12);
        othermaps.put("userId",12);
        idOtherMap.put("DmRoleUserEntity",othermaps);

        Map<String,Integer> othermap1 = new HashMap<>();
        othermap1.put("domainId",12);
        idOtherMap.put("DmUserEntity",othermap1);

        Map<String,Integer> environmenMap = new HashMap<>();
        environmenMap.put("workspaceId",12);
        idOtherMap.put("EnvironmentEntity",environmenMap);

        Map<String,Integer> mockReqMap = new HashMap<>();
        mockReqMap.put("mockId",12);
        idOtherMap.put("RequestMockEntity",mockReqMap);

        Map<String,Integer> mockResMap = new HashMap<>();
        mockResMap.put("mockId",12);
        idOtherMap.put("ResponseMockEntity",mockResMap);

        Map<String,Integer> apiStatusMap = new HashMap<>();
        apiStatusMap.put("workspaceId",12);
        idOtherMap.put("ApiStatusEntity",apiStatusMap);

        Map<String,Integer> jsonParamDSMap = new HashMap<>();
        jsonParamDSMap.put("dataStructureId",12);
        idOtherMap.put("JsonParamDSEntity",jsonParamDSMap);

        Map<String,Integer> apiRequestMap = new HashMap<>();
        apiRequestMap.put("httpId",12);
        idOtherMap.put("ApiRequestEntity",apiRequestMap);

        Map<String,Integer> apiResponseMap = new HashMap<>();
        apiResponseMap.put("httpId",12);
        idOtherMap.put("ApiResponseEntity",apiResponseMap);

        Map<String,Integer> jsonParamMap = new HashMap<>();
        jsonParamMap.put("httpId",12);
        idOtherMap.put("JsonParamEntity",jsonParamMap);

        Map<String,Integer> requestHeaderMap = new HashMap<>();
        requestHeaderMap.put("workspaceId",12);
        idOtherMap.put("RequestHeaderEntity",requestHeaderMap);

        Map<String,Integer> HttpInstanceMap = new HashMap<>();
        HttpInstanceMap.put("workspaceId",12);
        idOtherMap.put("HttpInstanceEntity",HttpInstanceMap);


        executorService.submit(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                try {
                    logger.info("数据库开始更新。。。");
//                    execOtherSql();
//                  idMap.put("UserEntity",6);

//                  Map<String,Integer> linkmap = new HashMap<>();
//                  linkmap.put("","")
                    //
                    //
                    // Map<String,Integer> othermap = new HashMap<>();

                    updateAllEntity();
                } catch (Exception e) {
                    logger.error("数据库更新失败：" + e.getMessage());
                    return false;
                }
                logger.info("数据库更新完成！");
                return true;
            }
        });
    }


    private void updateAllEntity(){
        Set<Class> classSet = AnnotationResourceResolver.resolve(basePackage, Mapper.class);
        if(classSet.size() > 0){
            for(Class<?> cls:classSet){
                Mapper mapper = cls.getAnnotation(Mapper.class);
                String entityClassName = mapper.targetAlias();

                // Class<?> entityClass = findEntity(basePackage,entityClassName);
                Class<?> entityClass = classMap.get(entityClassName);
                if (Objects.isNull(entityClass)){continue;}


                Table table = entityClass.getAnnotation(Table.class);
                String name = table.name();

                if (!findDatabase(name)){
                    logger.info("表"+name+"在数据库不存在，跳过！");
                    continue;
                }

                // 查询到的数据库实体信息
                List<?> entitylist = jpaTemplate.findAll(entityClass);
                if (entitylist == null || entitylist.isEmpty()){
                    continue;
                }

                logger.info("更新模型：" + entityClass);

                int idLength = LENGTH_12;
                for (String s : idMap.keySet()) {
                    // Class<?> entitys = findEntity(basePackage,s);
                    Class<?> entitys = classMap.get(s);
                    if (Objects.isNull(entitys)){
                        logger.info(s+"没有找到,跳过！");
                        continue;
                    }
                    if (!entitys.equals(entityClass)){
                        continue;
                    }
                    idLength = idMap.get(s);
                }

                Map<String, Integer> linkMap = null;
                for (String s : idLinkMap.keySet()) {
                    // Class<?> entitys = findEntity(basePackage,s);
                    Class<?> entitys = classMap.get(s);
                    if (Objects.isNull(entitys)){
                        logger.info("Entity"+ s + "没有找到,跳过！");
                        continue;
                    }
                    if (!entitys.equals(entityClass)){
                        continue;
                    }
                    linkMap = idLinkMap.get(s);
                }

                Map<String, Integer> otherMap = null;
                for (String s : idOtherMap.keySet()) {
                    // Class<?> entitys = findEntity(basePackage,s);
                    Class<?> entitys = classMap.get(s);;
                    if (Objects.isNull(entitys)){
                        continue;
                    }
                    if (!entitys.equals(entityClass)){
                        continue;
                    }
                    otherMap = idOtherMap.get(s);
                }

                // 转换成模型信息
                List<?> modelList = BeanMapper.mapList(entitylist, cls);
                for (Object object : modelList) {

                    Object entity = BeanMapper.map(object, entityClass);
                    String entityId = (String)findEntityId(entity);
                    if (Objects.isNull(entityId)){
                        throw new ApplicationException("获取"+cls+"对应Id的value为空,更改失败！");
                    }
                    if (!Objects.isNull(otherMap) ){
                        for (String s : otherMap.keySet()) {
                            Integer integer = otherMap.get(s);
                            updateOtherId(object,s,integer);
                        }
                    }
                    updateIdLink(object,linkMap);
                    updateId(object,entityId,idLength);
                }
            }
        }
    }

    private Object findEntityId(Object object){
        Class<?> aClass = object.getClass();
        Field[] fields = aClass.getDeclaredFields();
        Object o = null;
        for (Field field : fields) {
            field.setAccessible(true);
            boolean present = field.isAnnotationPresent(Id.class);
            if (!present){
                continue;
            }
            try {
                o = field.get(object);
            } catch (IllegalAccessException e) {
                throw new ApplicationException("获取"+aClass+"对应Id的value失败！"+e.getMessage());
            }
        }
        return o;
    }

    /**
     * 更新id
     * @param object 模型
     * @param sourceId 源id
     */
    private void updateId(Object object, String sourceId,int length){
        String targetId = convertTo8Bit(sourceId,length);

        if (Objects.isNull(targetId)){
            return;
        }

        Mapper mapper = object.getClass().getAnnotation(Mapper.class);
        String entityClassName = mapper.targetAlias();
        Class<?> tclass = classMap.get(entityClassName);
        if (Objects.isNull(tclass)){
            throw new ApplicationException("实体Entity没有找到："+ entityClassName);
        }
        Table table = tclass.getAnnotation(Table.class);
        String entityName = table.name();
        Field[] declaredFields = tclass.getDeclaredFields();
        // 字段名称
        String name = null;
        // 数据库字段名称
        String idName = null;
        for (Field field : declaredFields) {
            boolean idAnnotation = field.isAnnotationPresent(Id.class);
            if (!idAnnotation){
                continue;
            }
            Column column = field.getAnnotation(Column.class);
            idName = column.name();
        }

        if (Objects.isNull(entityName) ){
            throw new ApplicationException("获取"+tclass+"对应数据库表错误。");
        }

        if (Objects.isNull(idName) ){
            throw new ApplicationException("获取"+tclass+"对应数据库Id错误。");
        }

        String sql = "update " +" "+ entityName + " set" + " "+idName +" = '" + targetId +"' where " + idName +" = '"+ sourceId + "'";
        if (!findDatabase(entityName)){
            logger.info("表："+entityName+"在数据库不存在,跳过！");
            return;
        }
        execUpdateSql(sql);
    }

    /**
     * 更新映射字段
     * @param object 对象
     */
    private void updateIdLink(Object object,Map<String, Integer> integerMap){
        Class<?> aClass = object.getClass();
        Field[] fields = aClass.getDeclaredFields();

        boolean present = aClass.isAnnotationPresent(Mapper.class);
        if (!present){
            throw new ApplicationException("找不到对应的映射关系：" + aClass);
        }

        Mapper mapper = aClass.getAnnotation(Mapper.class);
        String entityClassName = mapper.targetAlias();
        Class<?> entityClass = classMap.get(entityClassName);
        if (Objects.isNull(entityClass)){
            throw new ApplicationException("找不到对应的Entity模型!");
        }

        int length = LENGTH_12;

        for (Field field : fields) {
            field.setAccessible(true);
            String name = field.getName();

            // 判断字段类型上是否存在模型映射关系
            boolean present1 = field.isAnnotationPresent(Mappings.class);
            if (!present1){
                continue;
            }

            Mappings mappings = field.getAnnotation(Mappings.class);
            Mapping[] mappingValued = mappings.value();
            String source = mappingValued[0].source();
            String target = mappingValued[0].target();
            if (source.contains(".")){
                String[] split = source.split("\\.");
                source =split[split.length-1];
            }
            if (Objects.isNull(source)){
                throw new ApplicationException("获取源映射数据失败:" + object.getClass()+" 字段名称：" + name);
            }

            if (!Objects.isNull(integerMap)){
                for (String s : integerMap.keySet()) {
                    if (!s.equals(name)){
                        continue;
                    }
                    length = integerMap.get(s);
                }
            }

            Object entityValue = BeanMapper.map(object,entityClass);

            Field[] declaredFields = entityValue.getClass().getDeclaredFields();
            String idName = null;
            Object o = null;
            for (Field declaredField : declaredFields) {
                declaredField.setAccessible(true);
                boolean annotationPresent = declaredField.isAnnotationPresent(Id.class);
                if (!annotationPresent){
                    continue;
                }

                Column column = declaredField.getAnnotation(Column.class);
                idName = column.name();
                try {
                    o = declaredField.get(entityValue);
                } catch (IllegalAccessException e) {
                    throw new ApplicationException("获取"+entityValue.getClass()+"对应数据库Id错误。");
                }
            }


            for (Field entityField : declaredFields) {
                entityField.setAccessible(true);
                String fieldName = entityField.getName();

                if (!fieldName.equals(target)){
                    continue;
                }

                Object o1;
                try {
                    o1 = entityField.get(entityValue);
                } catch (IllegalAccessException e) {
                    throw new ApplicationException("获取映射的id错误");
                }

                Table table = entityClass.getAnnotation(Table.class);
                String entityName = table.name();

                Column column = entityField.getAnnotation(Column.class);
                String targetFieldName = column.name();

                if (Objects.isNull(o1)){
                    logger.info("表：" + entityName +" 中 "+idName+" 为: "+ o +" 的 " + targetFieldName + " 字段为空！跳过");
                    continue;
                }

                String mappingTargetId = convertTo8Bit((String) o1, length);
                if (Objects.isNull(mappingTargetId)){
                    logger.info(entityName+"中的" + idName + " 为： "+ o1 + "字段小于32位，不需要转换！");
                    continue;
                }

                if (Objects.isNull(o) ){
                    throw new ApplicationException("获取"+entityValue.getClass()+"对应数据库表错误。");
                }

                if ( Objects.isNull(idName) ){
                    throw new ApplicationException("获取"+entityValue.getClass()+"对应数据库Id错误。");
                }

                String sql = "update " +" "+ entityName + " set" + " " + targetFieldName +" = '" + mappingTargetId +"' where " + idName +" = '"+ o +"'";
                if (!findDatabase(entityName)){
                    logger.info("表："+entityName+"在数据库不存在,跳过！");
                    return;
                }
                execUpdateSql(sql);
            }
        }
    }

    /**
     * 更新模型中的非映射字段
     * @param object 模型
     * @param fieldName 字段名称
     */
    private void updateOtherId(Object object,String fieldName,int length){
        Class<?> aClass = object.getClass();
        boolean present = aClass.isAnnotationPresent(Mapper.class);
        if (!present){
            return;
        }
        Mapper mapper = aClass.getAnnotation(Mapper.class);
        String entityClassName = mapper.targetAlias();
        Class<?> entityClass = classMap.get(entityClassName);

        if (Objects.isNull(entityClass)){
            throw new ApplicationException("找不到Entity"+entityClassName);
        }

        if (Objects.isNull(fieldName)){
            return;
        }

        Object entityValue = BeanMapper.map(object,entityClass);
        Field[] declaredFields = entityValue.getClass().getDeclaredFields();

        Table table = entityValue.getClass().getAnnotation(Table.class);
        String name1 = table.name();

        String idName = null;
        Object o = null;
        for (Field declaredField : declaredFields) {
            declaredField.setAccessible(true);
            boolean annotationPresent = declaredField.isAnnotationPresent(Id.class);
            if (!annotationPresent){
                continue;
            }

            Column column = declaredField.getAnnotation(Column.class);
            idName = column.name();
            try {
                o = declaredField.get(entityValue);
            } catch (IllegalAccessException e) {
                throw new ApplicationException("获取"+entityValue.getClass()+"对应数据库Id错误。");
            }
        }

        Field field;
        try {
            field = entityClass.getDeclaredField(fieldName);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
        field.setAccessible(true);
        Column column = field.getAnnotation(Column.class);
        String name = column.name();

        Object o1;
        try {
            o1 = field.get(entityValue);
        } catch (IllegalAccessException e) {
            throw new ApplicationException("获取其他id值错误！");
        }

        if (Objects.isNull(o1)){
            logger.info(name+"对象的值为空，跳过！");
            return;
        }

        String mappingTargetId = convertTo8Bit((String) o1, length);
        if (Objects.isNull(mappingTargetId)){
            logger.info("表："+name1+"中的字段："+name+"对象的值为："+ o1 +" 长度不符合，跳过！");
            return;
        }

        String sql = "update " +" "+ name1 + " set" + " " + name +" = '" + mappingTargetId +"' where " + idName +" = '"+ o +"'";

        if (!findDatabase(name1)){
            logger.info("表："+name1+"在数据库不存在,跳过！");
            return;
        }
        execUpdateSql(sql);

    }

    /**
     * 执行sql语句
     * @param sql 语句
     */
    private void execUpdateSql(String sql){
        logger.info("执行SQL:"+sql);
        jpaTemplate.getJdbcTemplate().execute(sql);
    }

    /**
     * 判断表是否存在
     * @param database 表
     * @return true 存在
     */
    private boolean findDatabase(String database){

        String sql = "SHOW TABLES";
        List<String> list = jpaTemplate.getJdbcTemplate().queryForList(sql, String.class);
        for (String s : list) {
            if (!s.equals(database)){
                continue;
            }
            if (database.equals("pts_dsm_schema_history")){
                continue;
            }
            if (database.equals("pts_dsm_schema_history_command")){
                continue;
            }
            return true;
        }
        return false;
    }


    public static final int LENGTH_8 = 8;

    public static final int LENGTH_12 = 12;

    public static String convertTo8Bit(String uuid,int length) {
        int lengthed = uuid.length();
        if (lengthed != 32){
            return null;
        }


        return uuid.substring(0, length);
    }

    private final List<String> list = new ArrayList<>();
    private void findSql(){
        String sql = "SHOW TABLES";
        List<String> stringList = jpaTemplate.getJdbcTemplate().queryForList(sql, String.class);
        list.addAll(stringList);
    }



    private final Map<String,Class<?>> classMap = new HashMap<>();

    private void findSetClass(){
        Set<Class> classSets = AnnotationResourceResolver.resolve(basePackage, Entity.class);
        if(classSets.size() > 0){
            for(Class<?> cls:classSets){
                String name = cls.getName();
                String[] split = name.split("\\.");
                String s = split[split.length - 1];
                classMap.put(s,cls);
            }
        }
    }

}
































