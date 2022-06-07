package com.doublekit.apibox.apitest.http.httpinstance.service;

import com.doublekit.apibox.apitest.http.httpinstance.dao.HttpInstanceDao;
import com.doublekit.apibox.apitest.http.httpinstance.entity.HttpInstanceEntity;

import com.doublekit.apibox.apitest.http.httpinstance.model.*;
import com.doublekit.core.page.Pagination;
import com.doublekit.beans.BeanMapper;
import com.doublekit.core.page.PaginationBuilder;
import com.doublekit.join.JoinTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.Comparator;
import java.util.List;

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
            requestInstance.setTestInstance(new HttpInstance().setId(id));
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
            for(AssertInstance assertInstance:assertInstanceList){
                assertInstance.setTestInstance(new HttpInstance().setId(id));
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

        joinTemplate.joinQuery(httpInstanceList);
        return httpInstanceList;
    }

    @Override
    public List<HttpInstance> findTestInstanceList(HttpInstanceQuery httpInstanceQuery) {
        List<HttpInstanceEntity> httpInstanceEntityList = httpInstanceDao.findTestInstanceList(httpInstanceQuery);

        List<HttpInstance> httpInstanceList = BeanMapper.mapList(httpInstanceEntityList, HttpInstance.class);

        httpInstanceList.sort(Comparator.comparing(HttpInstance::getCreateTime,Comparator.reverseOrder()));

        joinTemplate.joinQuery(httpInstanceList);

        return httpInstanceList;
    }

    @Override
    public Pagination<HttpInstance> findTestInstancePage(HttpInstanceQuery httpInstanceQuery) {

        Pagination<HttpInstanceEntity>  pagination = httpInstanceDao.findTestInstancePage(httpInstanceQuery);

        List<HttpInstance> httpInstanceList = BeanMapper.mapList(pagination.getDataList(), HttpInstance.class);

        joinTemplate.joinQuery(httpInstanceList);

        return PaginationBuilder.build(pagination, httpInstanceList);
    }
}