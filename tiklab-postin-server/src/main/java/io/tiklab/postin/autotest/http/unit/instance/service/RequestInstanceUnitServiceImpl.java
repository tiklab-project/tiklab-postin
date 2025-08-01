package io.tiklab.postin.autotest.http.unit.instance.service;

import io.tiklab.postin.autotest.http.unit.instance.dao.RequestInstanceUnitDao;
import io.tiklab.postin.autotest.http.unit.instance.entity.RequestInstanceUnitEntity;
import io.tiklab.postin.autotest.http.unit.instance.model.RequestInstanceUnit;
import io.tiklab.postin.autotest.http.unit.instance.model.RequestInstanceUnitQuery;
import io.tiklab.core.page.PaginationBuilder;

import io.tiklab.core.page.Pagination;
import io.tiklab.toolkit.beans.BeanMapper;
import io.tiklab.toolkit.join.JoinTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 请求数据实例 服务
*/
@Service
public class RequestInstanceUnitServiceImpl implements RequestInstanceUnitService {

    @Autowired
    RequestInstanceUnitDao requestInstanceUnitDao;

    @Autowired
    JoinTemplate joinTemplate;

    @Override
    public String createRequestInstance(@NotNull @Valid RequestInstanceUnit requestInstanceUnit) {
        RequestInstanceUnitEntity requestInstanceUnitEntity = BeanMapper.map(requestInstanceUnit, RequestInstanceUnitEntity.class);
        return requestInstanceUnitDao.createRequestInstance(requestInstanceUnitEntity);
    }

    @Override
    public void updateRequestInstance(@NotNull @Valid RequestInstanceUnit requestInstanceUnit) {
        RequestInstanceUnitEntity requestInstanceUnitEntity = BeanMapper.map(requestInstanceUnit, RequestInstanceUnitEntity.class);

        requestInstanceUnitDao.updateRequestInstance(requestInstanceUnitEntity);
    }

    @Override
    public void deleteRequestInstance(@NotNull String id) {
        requestInstanceUnitDao.deleteRequestInstance(id);
    }

    @Override
    public RequestInstanceUnit findOne(String id) {
        RequestInstanceUnitEntity requestInstanceUnitEntity = requestInstanceUnitDao.findRequestInstance(id);

        RequestInstanceUnit requestInstanceUnit = BeanMapper.map(requestInstanceUnitEntity, RequestInstanceUnit.class);
        return requestInstanceUnit;
    }

    @Override
    public List<RequestInstanceUnit> findList(List<String> idList) {
        List<RequestInstanceUnitEntity> requestInstanceUnitEntityList =  requestInstanceUnitDao.findRequestInstanceList(idList);

        List<RequestInstanceUnit> requestInstanceUnitList =  BeanMapper.mapList(requestInstanceUnitEntityList, RequestInstanceUnit.class);
        return requestInstanceUnitList;
    }

    @Override
    public RequestInstanceUnit findRequestInstance(@NotNull String id) {
        RequestInstanceUnit requestInstanceUnit = findOne(id);

        joinTemplate.joinQuery(requestInstanceUnit);
        return requestInstanceUnit;
    }

    @Override
    public List<RequestInstanceUnit> findAllRequestInstance() {
        List<RequestInstanceUnitEntity> requestInstanceUnitEntityList =  requestInstanceUnitDao.findAllRequestInstance();

        List<RequestInstanceUnit> requestInstanceUnitList =  BeanMapper.mapList(requestInstanceUnitEntityList, RequestInstanceUnit.class);

        joinTemplate.joinQuery(requestInstanceUnitList);
        return requestInstanceUnitList;
    }

    @Override
    public List<RequestInstanceUnit> findRequestInstanceList(RequestInstanceUnitQuery requestInstanceUnitQuery) {
        List<RequestInstanceUnitEntity> requestInstanceUnitEntityList = requestInstanceUnitDao.findRequestInstanceList(requestInstanceUnitQuery);

        List<RequestInstanceUnit> requestInstanceUnitList = BeanMapper.mapList(requestInstanceUnitEntityList, RequestInstanceUnit.class);

        joinTemplate.joinQuery(requestInstanceUnitList);

        return requestInstanceUnitList;
    }

    @Override
    public Pagination<RequestInstanceUnit> findRequestInstancePage(RequestInstanceUnitQuery requestInstanceUnitQuery) {
        Pagination<RequestInstanceUnitEntity>  pagination = requestInstanceUnitDao.findRequestInstancePage(requestInstanceUnitQuery);

        List<RequestInstanceUnit> requestInstanceUnitList = BeanMapper.mapList(pagination.getDataList(), RequestInstanceUnit.class);

        joinTemplate.joinQuery(requestInstanceUnitList);

        return PaginationBuilder.build(pagination, requestInstanceUnitList);
    }
}