package com.doublekit.apibox.apimock.service;

import com.doublekit.apibox.apimock.dao.JsonParamMockDao;
import com.doublekit.apibox.apimock.entity.JsonParamMockPo;
import com.doublekit.apibox.apimock.model.JsonParamMock;
import com.doublekit.apibox.apimock.model.JsonParamMockQuery;

import com.doublekit.common.Pagination;
import com.doublekit.beans.BeanMapper;
import com.doublekit.join.JoinTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

import org.springframework.beans.BeanUtils;

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
        JsonParamMockPo jsonParamMockPo = BeanMapper.map(jsonParamMock, JsonParamMockPo.class);

        return jsonParamMockDao.createJsonParamMock(jsonParamMockPo);
    }

    @Override
    public void updateJsonParamMock(@NotNull @Valid JsonParamMock jsonParamMock) {
        JsonParamMockPo jsonParamMockPo = BeanMapper.map(jsonParamMock, JsonParamMockPo.class);

        jsonParamMockDao.updateJsonParamMock(jsonParamMockPo);
    }

    @Override
    public void deleteJsonParamMock(@NotNull String id) {
        jsonParamMockDao.deleteJsonParamMock(id);
    }

    @Override
    public JsonParamMock findOne(String id) {
        JsonParamMockPo jsonParamMockPo = jsonParamMockDao.findJsonParamMock(id);

        JsonParamMock jsonParamMock = BeanMapper.map(jsonParamMockPo, JsonParamMock.class);
        return jsonParamMock;
    }

    @Override
    public List<JsonParamMock> findList(List<String> idList) {
        List<JsonParamMockPo> jsonParamMockPoList =  jsonParamMockDao.findJsonParamMockList(idList);

        List<JsonParamMock> jsonParamMockList =  BeanMapper.mapList(jsonParamMockPoList,JsonParamMock.class);
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
        List<JsonParamMockPo> jsonParamMockPoList =  jsonParamMockDao.findAllJsonParamMock();

        List<JsonParamMock> jsonParamMockList =  BeanMapper.mapList(jsonParamMockPoList,JsonParamMock.class);

        joinQuery.queryList(jsonParamMockList);
        return jsonParamMockList;
    }

    @Override
    public List<JsonParamMock> findJsonParamMockList(JsonParamMockQuery jsonParamMockQuery) {
        List<JsonParamMockPo> jsonParamMockPoList = jsonParamMockDao.findJsonParamMockList(jsonParamMockQuery);

        List<JsonParamMock> jsonParamMockList = BeanMapper.mapList(jsonParamMockPoList,JsonParamMock.class);

        joinQuery.queryList(jsonParamMockList);

        return jsonParamMockList;
    }

    @Override
    public Pagination<JsonParamMock> findJsonParamMockPage(JsonParamMockQuery jsonParamMockQuery) {
        Pagination<JsonParamMock> pg = new Pagination<>();

        Pagination<JsonParamMockPo>  pagination = jsonParamMockDao.findJsonParamMockPage(jsonParamMockQuery);
        BeanUtils.copyProperties(pagination,pg);

        List<JsonParamMock> jsonParamMockList = BeanMapper.mapList(pagination.getDataList(),JsonParamMock.class);

        joinQuery.queryList(jsonParamMockList);

        pg.setDataList(jsonParamMockList);
        return pg;
    }
}