package io.tiklab.postin.api.http.test.instance.service;

import io.tiklab.postin.api.http.test.instance.dao.HttpInstanceDao;
import io.tiklab.postin.api.http.test.instance.entity.HttpInstanceEntity;

import io.tiklab.postin.api.http.test.instance.model.*;
import io.tiklab.core.page.Pagination;
import io.tiklab.beans.BeanMapper;
import io.tiklab.core.page.PaginationBuilder;
import io.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.DeleteBuilders;
import io.tiklab.join.JoinTemplate;
import io.tiklab.eam.common.context.LoginContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
* 用户服务业务处理
*/
@Service
public class HttpInstanceServiceImpl implements TestInstanceService {

    @Autowired
    HttpInstanceDao httpInstanceDao;

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
        RequestInstances requestInstances = httpInstance.getRequestInstance();
        if(requestInstances != null){
            requestInstances.setId(id);
            requestInstances.setHttpInstance(new HttpInstance().setId(id));
            requestInstanceService.createRequestInstance(requestInstances);
        }

        //保存实例-响应从表
        ResponseInstances responseInstances = httpInstance.getResponseInstance();
        if(responseInstances != null){
            responseInstances.setId(id);
            responseInstances.setHttpInstance(new HttpInstance().setId(id));
            responseInstanceService.createResponseInstance(responseInstances);
        }

        //保存实例-断言子表
        List<AssertInstances> assertInstancesList = httpInstance.getAssertInstanceList();
        if(assertInstancesList != null && assertInstancesList.size() > 0){
            for(AssertInstances assertInstances : assertInstancesList){
                assertInstances.setHttpInstance(new HttpInstance().setId(id));
                assertInstanceService.createAssertInstance(assertInstances);
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
    public void deleteAllTestInstance(String userId) {
        DeleteCondition deleteCondition = DeleteBuilders.createDelete(HttpInstanceEntity.class)
                .eq("userId", userId)
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

        joinTemplate.joinQuery(httpInstance);
        return httpInstance;
    }

    @Override
    public HttpInstance findTestInstanceWithNest(String id) {
        //查找实例-主表
        HttpInstance httpInstance = findTestInstance(id);

        //查找实例-请求从表
        RequestInstances requestInstances = requestInstanceService.findRequestInstance(id);
        if(requestInstances != null){
            httpInstance.setRequestInstance(requestInstances);
        }

        //查找实例-响应从表
        ResponseInstances responseInstances = responseInstanceService.findResponseInstance(id);
        if(responseInstances != null){
            httpInstance.setResponseInstance(responseInstances);
        }

        //查找实例-断言子表
        AssertInstanceQuery assertInstanceQuery = new AssertInstanceQuery();
        assertInstanceQuery.setHttpInstanceId(id);
        List<AssertInstances> assertInstancesList = assertInstanceService.findAssertInstanceList(assertInstanceQuery);
        if(assertInstancesList != null && assertInstancesList.size() > 0){
            httpInstance.setAssertInstanceList(assertInstancesList);
        }

        return httpInstance;
    }

    @Override
    public List<HttpInstance> findAllTestInstance() {
        List<HttpInstanceEntity> httpInstanceEntityList =  httpInstanceDao.findAllTestInstance();

        List<HttpInstance> httpInstanceList =  BeanMapper.mapList(httpInstanceEntityList, HttpInstance.class);

        joinTemplate.joinQuery(httpInstanceList);
        return httpInstanceList;
    }

    @Override
    public List<HttpInstance> findTestInstanceList(HttpInstanceQuery httpInstanceQuery) {
        List<HttpInstanceEntity> httpInstanceEntityList = httpInstanceDao.findTestInstanceList(httpInstanceQuery);

        List<HttpInstance> httpInstanceList = BeanMapper.mapList(httpInstanceEntityList, HttpInstance.class);

        httpInstanceList.sort(Comparator.comparing(HttpInstance::getCreateTime,Comparator.reverseOrder()));

        joinTemplate.joinQuery(httpInstanceList);

        List<HttpInstance> httpInstances = httpInstanceList.stream().map(httpInstance -> {
            RequestInstances requestInstances = requestInstanceService.findRequestInstance(httpInstance.getId());
            httpInstance.setRequestInstance(requestInstances);

            return httpInstance;
        }).collect(Collectors.toList());

        return httpInstances;
    }

    @Override
    public Pagination<HttpInstance> findTestInstancePage(HttpInstanceQuery httpInstanceQuery) {

        Pagination<HttpInstanceEntity>  pagination = httpInstanceDao.findTestInstancePage(httpInstanceQuery);

        List<HttpInstance> httpInstanceList = BeanMapper.mapList(pagination.getDataList(), HttpInstance.class);

        joinTemplate.joinQuery(httpInstanceList);

        return PaginationBuilder.build(pagination, httpInstanceList);
    }



}