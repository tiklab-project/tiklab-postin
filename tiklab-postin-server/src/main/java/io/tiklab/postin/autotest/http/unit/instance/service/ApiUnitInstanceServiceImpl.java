package io.tiklab.postin.autotest.http.unit.instance.service;

import java.sql.Timestamp;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.tiklab.core.page.Pagination;
import io.tiklab.core.page.PaginationBuilder;
import io.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.DeleteBuilders;
import io.tiklab.eam.common.context.LoginContext;
import io.tiklab.postin.autotest.http.unit.instance.dao.ApiUnitInstanceDao;
import io.tiklab.postin.autotest.http.unit.instance.entity.ApiUnitInstanceEntity;
import io.tiklab.postin.autotest.http.unit.instance.model.ApiUnitInstance;
import io.tiklab.postin.autotest.http.unit.instance.model.ApiUnitInstanceQuery;
import io.tiklab.postin.autotest.http.unit.instance.model.AssertInstanceUnit;
import io.tiklab.postin.autotest.http.unit.instance.model.AssertInstanceUnitQuery;
import io.tiklab.postin.autotest.http.unit.instance.model.RequestInstanceUnit;
import io.tiklab.postin.autotest.http.unit.instance.model.ResponseInstanceUnit;
import io.tiklab.toolkit.beans.BeanMapper;
import io.tiklab.toolkit.join.JoinTemplate;

/**
 * 接口单元实例 服务
 */
@Service
public class ApiUnitInstanceServiceImpl implements ApiUnitInstanceService {

    @Autowired
    ApiUnitInstanceDao apiUnitInstanceDao;

    @Autowired
    JoinTemplate joinTemplate;

    @Autowired
    RequestInstanceUnitService requestInstanceUnitService;

    @Autowired
    ResponseInstanceUnitService responseInstanceUnitService;

    @Autowired
    AssertInstanceUnitService assertInstanceUnitService;




    @Override
    public String createApiUnitInstance(@NotNull @Valid ApiUnitInstance apiUnitInstance) {
        ApiUnitInstanceEntity apiUnitInstanceEntity = BeanMapper.map(apiUnitInstance, ApiUnitInstanceEntity.class);

        if(apiUnitInstance.getId() == null){
            String uid= UUID.randomUUID().toString();
            String id = uid.trim().replaceAll("-", "");
            apiUnitInstanceEntity.setId(id);
        }

        apiUnitInstanceEntity.setCreateTime(new Timestamp(System.currentTimeMillis()));
        String userId = LoginContext.getLoginId();
        apiUnitInstanceEntity.setCreateUser(userId);

        return apiUnitInstanceDao.createApiUnitInstance(apiUnitInstanceEntity);
    }

    @Override
    public String createApiUnitInstanceWithNest(@NotNull @Valid List<ApiUnitInstance> apiUnitInstances) {
        String id = null;

        for (ApiUnitInstance apiUnitInstance : apiUnitInstances) {
            id = createApiUnitInstance(apiUnitInstance);
        }

        return id;
    }

    @Override
    public void updateApiUnitInstance(@NotNull @Valid ApiUnitInstance apiUnitInstance) {
        ApiUnitInstanceEntity apiUnitInstanceEntity = BeanMapper.map(apiUnitInstance, ApiUnitInstanceEntity.class);

        apiUnitInstanceDao.updateApiUnitInstance(apiUnitInstanceEntity);
    }

    @Override
    public void deleteApiUnitInstance(@NotNull String id) {


        //删除测试步骤的实例 要删除相关联的表数据
        //删除测试结果的请求数据
        requestInstanceUnitService.deleteRequestInstance(id);

        //删除测试返回数据
        responseInstanceUnitService.deleteResponseInstance(id);

        //删除 断言
        assertInstanceUnitService.deleteAssertInstance(id);


        apiUnitInstanceDao.deleteApiUnitInstance(id);

    }




    @Override
    public void deleteApiUnitInstanceByApiUnitId(String apiUnitId){
        //删除相关联的子表
        DeleteCondition deleteCondition = DeleteBuilders.createDelete(ApiUnitInstanceEntity.class)
                .eq("apiUnitId", apiUnitId)
                .get();
        apiUnitInstanceDao.deleteApiUnitInstance(deleteCondition);
    }

    @Override
    public ApiUnitInstance findOne(String id) {
        ApiUnitInstanceEntity apiUnitInstanceEntity = apiUnitInstanceDao.findApiUnitInstance(id);

        ApiUnitInstance apiUnitInstance = BeanMapper.map(apiUnitInstanceEntity, ApiUnitInstance.class);
        return apiUnitInstance;
    }

    @Override
    public List<ApiUnitInstance> findList(List<String> idList) {
        List<ApiUnitInstanceEntity> apiUnitInstanceList = apiUnitInstanceDao.findApiUnitInstanceList(idList);

        List<ApiUnitInstance> apiUnitInstances = BeanMapper.mapList(apiUnitInstanceList, ApiUnitInstance.class);
        return apiUnitInstances;
    }

    @Override
    public ApiUnitInstance findApiUnitInstance(@NotNull String id) {
        ApiUnitInstance apiUnitInstance = findOne(id);

        joinTemplate.joinQuery(apiUnitInstance,new String[]{"apiUnit"});
        return apiUnitInstance;
    }

    @Override
    public ApiUnitInstance findApiUnitInstanceWithNest(String id) {
        ApiUnitInstance apiUnitInstance = findApiUnitInstance(id);

        RequestInstanceUnit requestInstanceUnit = requestInstanceUnitService.findRequestInstance(id);
        if (requestInstanceUnit != null) {
            apiUnitInstance.setRequestInstance(requestInstanceUnit);
        }

        ResponseInstanceUnit responseInstanceUnit = responseInstanceUnitService.findResponseInstance(id);
        if (responseInstanceUnit != null) {
            AssertInstanceUnitQuery assertInstanceUnitQuery = new AssertInstanceUnitQuery();
            assertInstanceUnitQuery.setInstanceId(id);
            List<AssertInstanceUnit> assertInstanceUnitList = assertInstanceUnitService.findAssertInstanceList(assertInstanceUnitQuery);
            responseInstanceUnit.setAssertInstanceList(assertInstanceUnitList);

            apiUnitInstance.setResponseInstance(responseInstanceUnit);
        }


        return apiUnitInstance;
    }


    @Override
    public List<ApiUnitInstance> findAllApiUnitInstance() {
        List<ApiUnitInstanceEntity> apiUnitInstanceEntities = apiUnitInstanceDao.findAllApiUnitInstance();

        List<ApiUnitInstance> apiUnitInstances = BeanMapper.mapList(apiUnitInstanceEntities, ApiUnitInstance.class);

        joinTemplate.joinQuery(apiUnitInstances);
        return apiUnitInstances;
    }

    @Override
    public List<ApiUnitInstance> findApiUnitInstanceList(ApiUnitInstanceQuery apiUnitInstanceQuery) {
        List<ApiUnitInstanceEntity> apiUnitInstanceEntities = apiUnitInstanceDao.findApiUnitInstanceList(apiUnitInstanceQuery);

        List<ApiUnitInstance> httpInstanceList = BeanMapper.mapList(apiUnitInstanceEntities, ApiUnitInstance.class);

        httpInstanceList.sort(Comparator.comparing(ApiUnitInstance::getCreateTime,Comparator.reverseOrder()));

        joinTemplate.joinQuery(httpInstanceList);

        List<ApiUnitInstance> httpInstances = httpInstanceList.stream().map(httpInstance -> {
            RequestInstanceUnit requestInstanceUnit = requestInstanceUnitService.findRequestInstance(httpInstance.getId());
            httpInstance.setRequestInstance(requestInstanceUnit);

            return httpInstance;
        }).collect(Collectors.toList());

        return httpInstances;
    }

    @Override
    public Pagination<ApiUnitInstance> findApiUnitInstancePage(ApiUnitInstanceQuery apiUnitInstanceQuery) {
        Pagination<ApiUnitInstanceEntity> pagination = apiUnitInstanceDao.findApiUnitInstancePage(apiUnitInstanceQuery);

        List<ApiUnitInstance> httpInstanceList = BeanMapper.mapList(pagination.getDataList(), ApiUnitInstance.class);

        httpInstanceList.sort(Comparator.comparing(ApiUnitInstance::getCreateTime,Comparator.reverseOrder()));

        joinTemplate.joinQuery(httpInstanceList);

        List<ApiUnitInstance> httpInstances = httpInstanceList.stream().map(httpInstance -> {
            RequestInstanceUnit requestInstanceUnit = requestInstanceUnitService.findRequestInstance(httpInstance.getId());
            httpInstance.setRequestInstance(requestInstanceUnit);

            return httpInstance;
        }).collect(Collectors.toList());

        return PaginationBuilder.build(pagination, httpInstances);
    }

    @Override
    public String saveApiUnitInstanceToSql(ApiUnitInstance apiUnitInstance) {
        String apiUnitInstanceId = createApiUnitInstance(apiUnitInstance);
        apiUnitInstance.setId(apiUnitInstanceId);

        //添加请求实例
        RequestInstanceUnit requestInstanceUnit = apiUnitInstance.getRequestInstance();
        if (requestInstanceUnit != null) {
            requestInstanceUnit.setId(apiUnitInstanceId);
            requestInstanceUnit.setApiUnitInstance(new ApiUnitInstance().setId(apiUnitInstanceId));
            requestInstanceUnitService.createRequestInstance(requestInstanceUnit);
        }

        //添加响应
        ResponseInstanceUnit responseInstanceUnit = apiUnitInstance.getResponseInstance();
        if (responseInstanceUnit != null) {
            responseInstanceUnit.setId(apiUnitInstanceId);
            responseInstanceUnit.setApiUnitInstance(new ApiUnitInstance().setId(apiUnitInstanceId));
            responseInstanceUnitService.createResponseInstance(responseInstanceUnit);


            List<AssertInstanceUnit> assertInstanceUnitList = responseInstanceUnit.getAssertInstanceList();
            if(assertInstanceUnitList !=null&&!assertInstanceUnitList.isEmpty()){
                for(AssertInstanceUnit assertInstanceUnit : assertInstanceUnitList){
                    assertInstanceUnit.setInstanceId(apiUnitInstanceId);
                    assertInstanceUnitService.createAssertInstance(assertInstanceUnit);
                }
            }
        }


        return apiUnitInstanceId;
    }

    @Override
    public void updateApiUnitInstanceToSql(ApiUnitInstance apiUnitInstance) {
        String apiUnitInstanceId = apiUnitInstance.getId();
        
        // 更新API单元实例本身
        updateApiUnitInstance(apiUnitInstance);

        // 更新请求实例
        RequestInstanceUnit requestInstanceUnit = apiUnitInstance.getRequestInstance();
        if (requestInstanceUnit != null) {
            requestInstanceUnit.setId(apiUnitInstanceId);
            requestInstanceUnit.setApiUnitInstance(new ApiUnitInstance().setId(apiUnitInstanceId));
            requestInstanceUnitService.updateRequestInstance(requestInstanceUnit);
        }

        // 更新响应实例
        ResponseInstanceUnit responseInstanceUnit = apiUnitInstance.getResponseInstance();
        if (responseInstanceUnit != null) {
            responseInstanceUnit.setId(apiUnitInstanceId);
            responseInstanceUnit.setApiUnitInstance(new ApiUnitInstance().setId(apiUnitInstanceId));
            responseInstanceUnitService.updateResponseInstance(responseInstanceUnit);

            // 处理断言实例 - 先删除所有现有断言，再创建新的
            List<AssertInstanceUnit> assertInstanceUnitList = responseInstanceUnit.getAssertInstanceList();
            if(assertInstanceUnitList != null && !assertInstanceUnitList.isEmpty()){
                // 删除现有断言实例
                assertInstanceUnitService.deleteAllAssertInstanceByInstanceId(apiUnitInstanceId);
                
                // 创建新的断言实例
                for(AssertInstanceUnit assertInstanceUnit : assertInstanceUnitList){
                    assertInstanceUnit.setInstanceId(apiUnitInstanceId);
                    assertInstanceUnitService.createAssertInstance(assertInstanceUnit);
                }
            }
        }
    }


}