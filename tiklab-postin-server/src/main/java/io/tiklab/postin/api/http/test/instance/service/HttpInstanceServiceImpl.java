package io.tiklab.postin.api.http.test.instance.service;

import io.tiklab.postin.api.http.test.instance.dao.HttpInstanceDao;
import io.tiklab.postin.api.http.test.instance.dao.RequestInstanceDao;
import io.tiklab.postin.api.http.test.instance.entity.HttpInstanceEntity;
import io.tiklab.postin.api.http.test.instance.entity.RequestInstanceEntity;

import io.tiklab.postin.api.http.test.instance.model.*;
import io.tiklab.toolkit.beans.BeanMapper;
import io.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.DeleteBuilders;
import io.tiklab.toolkit.join.JoinTemplate;
import io.tiklab.eam.common.context.LoginContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
* 用户服务业务处理
*/
@Service
public class HttpInstanceServiceImpl implements TestInstanceService {

    @Autowired
    HttpInstanceDao httpInstanceDao;

    @Autowired
    RequestInstanceDao requestInstanceDao;

    @Autowired
    JoinTemplate joinTemplate;

    @Autowired
    RequestInstanceService requestInstanceService;

    @Autowired
    ResponseInstanceService responseInstanceService;

    @Autowired
    AssertInstanceService assertInstanceService;

    @Override
    public String createTestInstance(@NotNull @Valid HttpInstance httpInstance) {
        HttpInstanceEntity httpInstanceEntity = BeanMapper.map(httpInstance, HttpInstanceEntity.class);
        httpInstanceEntity.setCreateTime(new Timestamp(System.currentTimeMillis()));
        httpInstanceEntity.setUserId(LoginContext.getLoginId());
        return httpInstanceDao.createTestInstance(httpInstanceEntity);
    }

    @Override
    public String createTestInstanceWithNest(HttpInstance httpInstance) {
        //保存实例-主表
        String id = createTestInstance(httpInstance);

        //保存实例-请求从表
        RequestInstance requestInstance = httpInstance.getRequestInstance();
        if(requestInstance != null){
            requestInstance.setId(id);
            requestInstance.setHttpInstance(new HttpInstance().setId(id));
            requestInstanceService.createRequestInstance(requestInstance);
        }

        //保存实例-响应从表
        ResponseInstance responseInstance = httpInstance.getResponseInstance();
        if(responseInstance != null){
            responseInstance.setId(id);
            responseInstance.setHttpInstance(new HttpInstance().setId(id));
            responseInstanceService.createResponseInstance(responseInstance);
        }

        //保存实例-断言子表
        List<AssertInstance> assertInstanceList = httpInstance.getAssertInstanceList();
        if(assertInstanceList != null && assertInstanceList.size() > 0){
            for(AssertInstance assertInstance : assertInstanceList){
                assertInstance.setHttpInstance(new HttpInstance().setId(id));
                assertInstanceService.createAssertInstance(assertInstance);
            }
        }
        return id;
    }

    @Override
    public void updateTestInstance(@NotNull @Valid HttpInstance httpInstance) {
        HttpInstanceEntity httpInstanceEntity = BeanMapper.map(httpInstance, HttpInstanceEntity.class);

        httpInstanceDao.updateTestInstance(httpInstanceEntity);
    }

    @Override
    public void deleteTestInstance(@NotNull String id) {
        httpInstanceDao.deleteTestInstance(id);
    }

    @Override
    public void deleteAllTestInstance(String workspaceId) {
        DeleteCondition deleteCondition = DeleteBuilders.createDelete(HttpInstanceEntity.class)
                .eq("workspaceId", workspaceId)
                .eq("userId", LoginContext.getLoginId())
                .get();
        httpInstanceDao.deleteAllTestInstance(deleteCondition);
    }

    @Override
    public HttpInstance findOne(String id) {
        HttpInstanceEntity httpInstanceEntity = httpInstanceDao.findTestInstance(id);

        HttpInstance httpInstance = BeanMapper.map(httpInstanceEntity, HttpInstance.class);
        return httpInstance;
    }

    @Override
    public List<HttpInstance> findList(List<String> idList) {
        List<HttpInstanceEntity> httpInstanceEntityList =  httpInstanceDao.findTestInstanceList(idList);

        List<HttpInstance> httpInstanceList =  BeanMapper.mapList(httpInstanceEntityList, HttpInstance.class);
        return httpInstanceList;
    }

    @Override
    public HttpInstance findTestInstance(@NotNull String id) {
        HttpInstance httpInstance = findOne(id);

        joinTemplate.joinQuery(httpInstance,new String[]{
                "httpCase",
                "user"
        });
        return httpInstance;
    }

    @Override
    public HttpInstance findTestInstanceWithNest(String id) {
        //查找实例-主表
        HttpInstance httpInstance = findTestInstance(id);

        //查找实例-请求从表
        RequestInstance requestInstance = requestInstanceService.findRequestInstance(id);
        if(requestInstance != null){
            httpInstance.setRequestInstance(requestInstance);
        }

        //查找实例-响应从表
        ResponseInstance responseInstance = responseInstanceService.findResponseInstance(id);
        if(responseInstance != null){
            httpInstance.setResponseInstance(responseInstance);
        }

        //查找实例-断言子表
        AssertInstanceQuery assertInstanceQuery = new AssertInstanceQuery();
        assertInstanceQuery.setHttpInstanceId(id);
        List<AssertInstance> assertInstanceList = assertInstanceService.findAssertInstanceList(assertInstanceQuery);
        if(assertInstanceList != null && assertInstanceList.size() > 0){
            httpInstance.setAssertInstanceList(assertInstanceList);
        }

        return httpInstance;
    }

    @Override
    public List<HttpInstance> findAllTestInstance() {
        List<HttpInstanceEntity> httpInstanceEntityList =  httpInstanceDao.findAllTestInstance();

        List<HttpInstance> httpInstanceList =  BeanMapper.mapList(httpInstanceEntityList, HttpInstance.class);

        joinTemplate.joinQuery(httpInstanceList,new String[]{
                "httpCase",
                "user"
        });
        return httpInstanceList;
    }

    @Override
    public List<HttpInstance> findTestInstanceList(HttpInstanceQuery httpInstanceQuery) {
        List<HttpInstanceEntity> httpInstanceEntityList = httpInstanceDao.findTestInstanceList(httpInstanceQuery);
        List<HttpInstance> httpInstanceList = BeanMapper.mapList(httpInstanceEntityList, HttpInstance.class);
        
        // 关联查询
        joinTemplate.joinQuery(httpInstanceList,new String[]{
                "httpCase",
                "user"
        });

        // 批量查询RequestInstance
        if (!httpInstanceList.isEmpty()) {
            List<String> instanceIds = httpInstanceList.stream()
                    .map(HttpInstance::getId)
                    .collect(Collectors.toList());
            
            List<RequestInstanceEntity> requestInstanceEntities = requestInstanceDao.findRequestInstanceList(instanceIds);
            List<RequestInstance> requestInstances = BeanMapper.mapList(requestInstanceEntities, RequestInstance.class);
            
            // 创建Map便于快速查找，使用httpInstance.id作为key
            Map<String, RequestInstance> requestInstanceMap = requestInstances.stream()
                    .collect(Collectors.toMap(
                            requestInstance -> requestInstance.getHttpInstance().getId(),
                            Function.identity(),
                            (existing, replacement) -> existing
                    ));
            
            // 设置RequestInstance
            httpInstanceList.forEach(httpInstance -> {
                RequestInstance requestInstance = requestInstanceMap.get(httpInstance.getId());
                if (requestInstance != null) {
                    httpInstance.setRequestInstance(requestInstance);
                }
            });
        }

        return httpInstanceList;
    }


    @Override
    public HashMap<String, List<HttpInstance>> findTestInstanceGroupByCreateTime(HttpInstanceQuery httpInstanceQuery) {
        List<HttpInstance> testInstanceList = findTestInstanceList(httpInstanceQuery);

        DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        
        // 按创建日期分组
        Map<String, List<HttpInstance>> groupedByDate = testInstanceList.stream()
                .collect(Collectors.groupingBy(
                        instance -> instance.getCreateTime().toLocalDateTime().toLocalDate().format(DATE_FORMATTER)
                ));

        // 对每个分组内的实例按创建时间倒序排序
        groupedByDate.forEach((dateKey, instanceList) -> {
            instanceList.sort((i1, i2) -> i2.getCreateTime().compareTo(i1.getCreateTime()));
        });

        // 按日期键倒序排序并返回结果
        return groupedByDate.entrySet().stream()
                .sorted(Map.Entry.<String, List<HttpInstance>>comparingByKey().reversed())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new
                ));
    }



}