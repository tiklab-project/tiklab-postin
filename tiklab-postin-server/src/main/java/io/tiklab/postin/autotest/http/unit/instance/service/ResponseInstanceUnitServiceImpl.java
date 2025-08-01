package io.tiklab.postin.autotest.http.unit.instance.service;

import io.tiklab.core.page.PaginationBuilder;

import io.tiklab.core.page.Pagination;
import io.tiklab.postin.autotest.http.unit.instance.dao.ResponseInstanceUnitDao;
import io.tiklab.postin.autotest.http.unit.instance.entity.ResponseInstanceUnitEntity;
import io.tiklab.postin.autotest.http.unit.instance.model.ResponseInstanceUnit;
import io.tiklab.postin.autotest.http.unit.instance.model.ResponseInstanceUnitQuery;
import io.tiklab.toolkit.beans.BeanMapper;
import io.tiklab.toolkit.join.JoinTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 响应数据实例 服务
*/
@Service
public class ResponseInstanceUnitServiceImpl implements ResponseInstanceUnitService {

    @Autowired
    ResponseInstanceUnitDao responseInstanceUnitDao;

    @Autowired
    JoinTemplate joinTemplate;

    @Override
    public String createResponseInstance(@NotNull @Valid ResponseInstanceUnit responseInstanceUnit) {
        ResponseInstanceUnitEntity responseInstanceUnitEntity = BeanMapper.map(responseInstanceUnit, ResponseInstanceUnitEntity.class);

        return responseInstanceUnitDao.createResponseInstance(responseInstanceUnitEntity);
    }

    @Override
    public void updateResponseInstance(@NotNull @Valid ResponseInstanceUnit responseInstanceUnit) {
        ResponseInstanceUnitEntity responseInstanceUnitEntity = BeanMapper.map(responseInstanceUnit, ResponseInstanceUnitEntity.class);

        responseInstanceUnitDao.updateResponseInstance(responseInstanceUnitEntity);
    }

    @Override
    public void deleteResponseInstance(@NotNull String id) {
        responseInstanceUnitDao.deleteResponseInstance(id);
    }

    @Override
    public ResponseInstanceUnit findOne(String id) {
        ResponseInstanceUnitEntity responseInstanceUnitEntity = responseInstanceUnitDao.findResponseInstance(id);

        ResponseInstanceUnit responseInstanceUnit = BeanMapper.map(responseInstanceUnitEntity, ResponseInstanceUnit.class);
        return responseInstanceUnit;
    }

    @Override
    public List<ResponseInstanceUnit> findList(List<String> idList) {
        List<ResponseInstanceUnitEntity> responseInstanceUnitEntityList =  responseInstanceUnitDao.findResponseInstanceList(idList);

        List<ResponseInstanceUnit> responseInstanceUnitList =  BeanMapper.mapList(responseInstanceUnitEntityList, ResponseInstanceUnit.class);
        return responseInstanceUnitList;
    }

    @Override
    public ResponseInstanceUnit findResponseInstance(@NotNull String id) {
        ResponseInstanceUnit responseInstanceUnit = findOne(id);

        joinTemplate.joinQuery(responseInstanceUnit);
        return responseInstanceUnit;
    }

    @Override
    public List<ResponseInstanceUnit> findAllResponseInstance() {
        List<ResponseInstanceUnitEntity> responseInstanceUnitEntityList =  responseInstanceUnitDao.findAllResponseInstance();

        List<ResponseInstanceUnit> responseInstanceUnitList =  BeanMapper.mapList(responseInstanceUnitEntityList, ResponseInstanceUnit.class);

        joinTemplate.joinQuery(responseInstanceUnitList);
        return responseInstanceUnitList;
    }

    @Override
    public List<ResponseInstanceUnit> findResponseInstanceList(ResponseInstanceUnitQuery responseInstanceUnitQuery) {
        List<ResponseInstanceUnitEntity> responseInstanceUnitEntityList = responseInstanceUnitDao.findResponseInstanceList(responseInstanceUnitQuery);

        List<ResponseInstanceUnit> responseInstanceUnitList = BeanMapper.mapList(responseInstanceUnitEntityList, ResponseInstanceUnit.class);

        joinTemplate.joinQuery(responseInstanceUnitList);

        return responseInstanceUnitList;
    }

    @Override
    public Pagination<ResponseInstanceUnit> findResponseInstancePage(ResponseInstanceUnitQuery responseInstanceUnitQuery) {

        Pagination<ResponseInstanceUnitEntity>  pagination = responseInstanceUnitDao.findResponseInstancePage(responseInstanceUnitQuery);

        List<ResponseInstanceUnit> responseInstanceUnitList = BeanMapper.mapList(pagination.getDataList(), ResponseInstanceUnit.class);

        joinTemplate.joinQuery(responseInstanceUnitList);

        return PaginationBuilder.build(pagination, responseInstanceUnitList);
    }
}