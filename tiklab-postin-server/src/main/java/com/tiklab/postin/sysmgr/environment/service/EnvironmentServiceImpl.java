package com.tiklab.postin.sysmgr.environment.service;

import com.tiklab.postin.sysmgr.environment.dao.EnvironmentDao;
import com.tiklab.postin.sysmgr.environment.entity.EnvironmentEntity;
import com.tiklab.postin.sysmgr.environment.model.Environment;
import com.tiklab.postin.sysmgr.environment.model.EnvironmentQuery;

import com.tiklab.core.page.Pagination;
import com.tiklab.beans.BeanMapper;
import com.tiklab.core.page.PaginationBuilder;
import com.tiklab.join.JoinTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

/**
* 用户服务业务处理
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

        joinTemplate.joinQuery(environment);
        return environment;
    }

    @Override
    public List<Environment> findAllEnvironment() {
        List<EnvironmentEntity> environmentEntityList =  environmentDao.findAllEnvironment();

        List<Environment> environmentList =  BeanMapper.mapList(environmentEntityList,Environment.class);

        joinTemplate.joinQuery(environmentList);
        return environmentList;
    }

    @Override
    public List<Environment> findEnvironmentList(EnvironmentQuery environmentQuery) {
        List<EnvironmentEntity> environmentEntityList = environmentDao.findEnvironmentList(environmentQuery);

        List<Environment> environmentList = BeanMapper.mapList(environmentEntityList,Environment.class);

        joinTemplate.joinQuery(environmentList);

        return environmentList;
    }

    @Override
    public Pagination<Environment> findEnvironmentPage(EnvironmentQuery environmentQuery) {

        Pagination<EnvironmentEntity>  pagination = environmentDao.findEnvironmentPage(environmentQuery);

        List<Environment> environmentList = BeanMapper.mapList(pagination.getDataList(),Environment.class);

        joinTemplate.joinQuery(environmentList);

        return PaginationBuilder.build(pagination,environmentList);
    }
}