package io.thoughtware.postin.api.http.test.instance.service;

import io.thoughtware.postin.api.http.test.instance.dao.HttpInstanceDao;
import io.thoughtware.postin.api.http.test.instance.entity.HttpInstanceEntity;

import io.thoughtware.postin.api.http.test.instance.model.*;
import io.thoughtware.core.page.Pagination;
import io.thoughtware.toolkit.beans.BeanMapper;
import io.thoughtware.core.page.PaginationBuilder;
import io.thoughtware.dal.jpa.criterial.condition.DeleteCondition;
import io.thoughtware.dal.jpa.criterial.conditionbuilder.DeleteBuilders;
import io.thoughtware.toolkit.join.JoinTemplate;
import io.thoughtware.eam.common.context.LoginContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
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
//        httpInstanceList.sort(Comparator.comparing(HttpInstance::getCreateTime,Comparator.reverseOrder()));
        joinTemplate.joinQuery(httpInstanceList);

        List<HttpInstance> httpInstances = httpInstanceList.stream().map(httpInstance -> {
            RequestInstance requestInstance = requestInstanceService.findRequestInstance(httpInstance.getId());
            httpInstance.setRequestInstance(requestInstance);
            return httpInstance;
        }).collect(Collectors.toList());


        return httpInstances;
    }



    @Override
    public HashMap<String, List<HttpInstance>> findTestInstanceGroupByCreateTime(HttpInstanceQuery httpInstanceQuery) {
        List<HttpInstance> testInstanceList = findTestInstanceList(httpInstanceQuery);

        // 创建一个 HashMap 用于将相同创建时间的实例分组
        HashMap<String, List<HttpInstance>> groupedByCreateTime = new HashMap<>();

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        for (HttpInstance httpInstance : testInstanceList) {
            // 将 Timestamp 转换为 LocalDateTime，然后获取 LocalDate
            LocalDate createDate = httpInstance.getCreateTime().toLocalDateTime().toLocalDate();
            String createTimeKey = createDate.format(dateFormatter); // 格式化为字符串

            // 如果 HashMap 中不存在该键，初始化一个新的列表
            if (!groupedByCreateTime.containsKey(createTimeKey)) {
                groupedByCreateTime.put(createTimeKey, new ArrayList<>());
            }

            // 将当前 HttpInstance 添加到对应的列表中
            groupedByCreateTime.get(createTimeKey).add(httpInstance);
        }

        // 对 HashMap 按日期键降序排序
        LinkedHashMap<String, List<HttpInstance>> sortedGroupedByCreateTime = groupedByCreateTime.entrySet().stream()
                .sorted(Map.Entry.<String, List<HttpInstance>>comparingByKey().reversed())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1, // 合并函数（在这里不需要）
                        LinkedHashMap::new // 保持插入顺序
                ));

        return sortedGroupedByCreateTime;
    }



}