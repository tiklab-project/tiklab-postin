package com.darthcloud.apibox.sysmgr.service;

import com.darthcloud.apibox.sysmgr.dao.EnvironmentDao;
import com.darthcloud.apibox.sysmgr.entity.EnvironmentPo;
import com.darthcloud.apibox.sysmgr.model.Environment;
import com.darthcloud.apibox.sysmgr.model.EnvironmentQuery;

import com.darthcloud.common.Pagination;
import com.darthcloud.beans.BeanMapper;
import com.darthcloud.join.join.JoinQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;

/**
* 用户服务业务处理
*/
@Service
public class EnvironmentServiceImpl implements EnvironmentService {

    @Autowired
    EnvironmentDao environmentDao;

    @Autowired
    JoinQuery joinQuery;

    @Override
    public String createEnvironment(@NotNull @Valid Environment environment) {
        environment.setCreateTime(new Date());
        EnvironmentPo environmentPo = BeanMapper.map(environment, EnvironmentPo.class);

        return environmentDao.createEnvironment(environmentPo);
    }

    @Override
    public void updateEnvironment(@NotNull @Valid Environment environment) {
        environment.setUpdateTime(new Date());
        EnvironmentPo environmentPo = BeanMapper.map(environment, EnvironmentPo.class);

        environmentDao.updateEnvironment(environmentPo);
    }

    @Override
    public void deleteEnvironment(@NotNull String id) {
        environmentDao.deleteEnvironment(id);
    }

    @Override
    public Environment findOne(String id) {
        EnvironmentPo environmentPo = environmentDao.findEnvironment(id);

        Environment environment = BeanMapper.map(environmentPo, Environment.class);
        return environment;
    }

    @Override
    public List<Environment> findList(List<String> idList) {
        List<EnvironmentPo> environmentPoList =  environmentDao.findEnvironmentList(idList);

        List<Environment> environmentList =  BeanMapper.mapList(environmentPoList,Environment.class);
        return environmentList;
    }

    @Override
    public Environment findEnvironment(@NotNull String id) {
        Environment environment = findOne(id);

        joinQuery.queryOne(environment);
        return environment;
    }

    @Override
    public List<Environment> findAllEnvironment() {
        List<EnvironmentPo> environmentPoList =  environmentDao.findAllEnvironment();

        List<Environment> environmentList =  BeanMapper.mapList(environmentPoList,Environment.class);

        joinQuery.queryList(environmentList);
        return environmentList;
    }

    @Override
    public List<Environment> findEnvironmentList(EnvironmentQuery environmentQuery) {
        List<EnvironmentPo> environmentPoList = environmentDao.findEnvironmentList(environmentQuery);

        List<Environment> environmentList = BeanMapper.mapList(environmentPoList,Environment.class);

        joinQuery.queryList(environmentList);

        return environmentList;
    }

    @Override
    public Pagination<List<Environment>> findEnvironmentPage(EnvironmentQuery environmentQuery) {
        Pagination<List<Environment>> pg = new Pagination<>();

        Pagination<List<EnvironmentPo>>  pagination = environmentDao.findEnvironmentPage(environmentQuery);
        BeanUtils.copyProperties(pagination,pg);

        List<Environment> environmentList = BeanMapper.mapList(pagination.getDataList(),Environment.class);

        joinQuery.queryList(environmentList);

        pg.setDataList(environmentList);
        return pg;
    }
}