package com.doublekit.apibox.apimock.service;

import com.doublekit.apibox.apimock.dao.JsonParamMockDao;
import com.doublekit.apibox.apimock.entity.JsonParamMockEntity;
import com.doublekit.apibox.apimock.model.JsonParamMock;
import com.doublekit.apibox.apimock.model.JsonParamMockQuery;

import com.doublekit.common.page.Pagination;
import com.doublekit.beans.BeanMapper;
import com.doublekit.common.page.PaginationBuilder;
import com.doublekit.join.JoinTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 用户服务业务处理
*/
@Service
public class JsonParamMockServiceImpl implements JsonParamMockService {

    @Autowired
    JsonParamMockDao jsonParamMockDao;

    @Autowired
    JoinTemplate joinQuery;

    @Override
    public String createJsonParamMock(@NotNull @Valid JsonParamMock jsonParamMock) {
        JsonParamMockEntity jsonParamMockEntity = BeanMapper.map(jsonParamMock, JsonParamMockEntity.class);

        return jsonParamMockDao.createJsonParamMock(jsonParamMockEntity);
    }

    @Override
    public void updateJsonParamMock(@NotNull @Valid JsonParamMock jsonParamMock) {
        JsonParamMockEntity jsonParamMockEntity = BeanMapper.map(jsonParamMock, JsonParamMockEntity.class);

        jsonParamMockDao.updateJsonParamMock(jsonParamMockEntity);
    }

    @Override
    public void deleteJsonParamMock(@NotNull String id) {
        jsonParamMockDao.deleteJsonParamMock(id);
    }

    @Override
    public JsonParamMock findOne(String id) {
        JsonParamMockEntity jsonParamMockEntity = jsonParamMockDao.findJsonParamMock(id);

        JsonParamMock jsonParamMock = BeanMapper.map(jsonParamMockEntity, JsonParamMock.class);
        return jsonParamMock;
    }

    @Override
    public List<JsonParamMock> findList(List<String> idList) {
        List<JsonParamMockEntity> jsonParamMockEntityList =  jsonParamMockDao.findJsonParamMockList(idList);

        List<JsonParamMock> jsonParamMockList =  BeanMapper.mapList(jsonParamMockEntityList,JsonParamMock.class);
        return jsonParamMockList;
    }

    @Override
    public JsonParamMock findJsonParamMock(@NotNull String id) {
        JsonParamMock jsonParamMock = findOne(id);

        joinQuery.queryOne(jsonParamMock);
        return jsonParamMock;
    }

    @Override
    public List<JsonParamMock> findAllJsonParamMock() {
        List<JsonParamMockEntity> jsonParamMockEntityList =  jsonParamMockDao.findAllJsonParamMock();

        List<JsonParamMock> jsonParamMockList =  BeanMapper.mapList(jsonParamMockEntityList,JsonParamMock.class);

        joinQuery.queryList(jsonParamMockList);
        return jsonParamMockList;
    }

    @Override
    public List<JsonParamMock> findJsonParamMockList(JsonParamMockQuery jsonParamMockQuery) {
        List<JsonParamMockEntity> jsonParamMockEntityList = jsonParamMockDao.findJsonParamMockList(jsonParamMockQuery);

        List<JsonParamMock> jsonParamMockList = BeanMapper.mapList(jsonParamMockEntityList,JsonParamMock.class);

        joinQuery.queryList(jsonParamMockList);

        return jsonParamMockList;
    }

    @Override
    public Pagination<JsonParamMock> findJsonParamMockPage(JsonParamMockQuery jsonParamMockQuery) {

        Pagination<JsonParamMockEntity>  pagination = jsonParamMockDao.findJsonParamMockPage(jsonParamMockQuery);

        List<JsonParamMock> jsonParamMockList = BeanMapper.mapList(pagination.getDataList(),JsonParamMock.class);

        joinQuery.queryList(jsonParamMockList);

        return PaginationBuilder.build(pagination,jsonParamMockList);
    }
}