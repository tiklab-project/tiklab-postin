package io.tiklab.postin.support.environment.service;

import io.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.DeleteBuilders;
import io.tiklab.postin.support.environment.dao.EnvironmentDao;
import io.tiklab.postin.support.environment.entity.EnvironmentEntity;
import io.tiklab.postin.support.environment.model.Environment;
import io.tiklab.postin.support.environment.model.EnvironmentQuery;

import io.tiklab.core.page.Pagination;
import io.tiklab.toolkit.beans.BeanMapper;
import io.tiklab.core.page.PaginationBuilder;
import io.tiklab.toolkit.join.JoinTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
* 接口环境 服务
*/
@Service
public class EnvironmentServiceImpl implements EnvironmentService {

    @Autowired
    EnvironmentDao environmentDao;

    @Autowired
    JoinTemplate joinTemplate;

    @Override
    public String createEnvironment(@NotNull @Valid Environment environment) {
        environment.setCreateTime(new Date());

        //如果没有id，则生成一个随机的id
        if(environment.getId()== null){
            String randomKey = UUID.randomUUID().toString().replaceAll("", "").substring(0, 12).toUpperCase();
            environment.setId(randomKey);
        }

        EnvironmentEntity environmentEntity = BeanMapper.map(environment, EnvironmentEntity.class);

        return environmentDao.createEnvironment(environmentEntity);
    }

    @Override
    public void updateEnvironment(@NotNull @Valid Environment environment) {
        environment.setUpdateTime(new Date());
        EnvironmentEntity environmentEntity = BeanMapper.map(environment, EnvironmentEntity.class);

        environmentDao.updateEnvironment(environmentEntity);
    }

    @Override
    public void deleteEnvironment(@NotNull String id) {
        environmentDao.deleteEnvironment(id);
    }

    @Override
    public void deleteAllEnvironment(String workspaceId) {
        DeleteCondition deleteCondition = DeleteBuilders.createDelete(EnvironmentEntity.class)
                .eq("workspaceId", workspaceId)
                .get();
        environmentDao.deleteEnvironment(deleteCondition);
    }

    @Override
    public Environment findOne(String id) {
        EnvironmentEntity environmentEntity = environmentDao.findEnvironment(id);

        Environment environment = BeanMapper.map(environmentEntity, Environment.class);
        return environment;
    }

    @Override
    public List<Environment> findList(List<String> idList) {
        List<EnvironmentEntity> environmentEntityList =  environmentDao.findEnvironmentList(idList);

        List<Environment> environmentList =  BeanMapper.mapList(environmentEntityList,Environment.class);
        return environmentList;
    }

    @Override
    public Environment findEnvironment(@NotNull String id) {
        Environment environment = findOne(id);

        return environment;
    }

    @Override
    public List<Environment> findAllEnvironment() {
        List<EnvironmentEntity> environmentEntityList =  environmentDao.findAllEnvironment();

        List<Environment> environmentList =  BeanMapper.mapList(environmentEntityList,Environment.class);

        return environmentList;
    }

    @Override
    public List<Environment> findEnvironmentList(EnvironmentQuery environmentQuery) {
        List<EnvironmentEntity> environmentEntityList = environmentDao.findEnvironmentList(environmentQuery);

        List<Environment> environmentList = BeanMapper.mapList(environmentEntityList,Environment.class);


        return environmentList;
    }

    @Override
    public Pagination<Environment> findEnvironmentPage(EnvironmentQuery environmentQuery) {

        Pagination<EnvironmentEntity>  pagination = environmentDao.findEnvironmentPage(environmentQuery);

        List<Environment> environmentList = BeanMapper.mapList(pagination.getDataList(),Environment.class);


        return PaginationBuilder.build(pagination,environmentList);
    }
}