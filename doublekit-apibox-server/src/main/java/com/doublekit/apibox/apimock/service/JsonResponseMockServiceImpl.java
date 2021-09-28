package com.doublekit.apibox.apimock.service;

import com.doublekit.apibox.apimock.dao.JsonResponseMockDao;
import com.doublekit.apibox.apimock.entity.JsonResponseMockPo;
import com.doublekit.apibox.apimock.model.JsonResponseMock;
import com.doublekit.apibox.apimock.model.JsonResponseMockQuery;

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
public class JsonResponseMockServiceImpl implements JsonResponseMockService {

    @Autowired
    JsonResponseMockDao jsonResponseMockDao;

    @Autowired
    JoinTemplate joinQuery;

    @Override
    public String createJsonResponseMock(@NotNull @Valid JsonResponseMock jsonResponseMock) {
        JsonResponseMockPo jsonResponseMockPo = BeanMapper.map(jsonResponseMock, JsonResponseMockPo.class);

        return jsonResponseMockDao.createJsonResponseMock(jsonResponseMockPo);
    }

    @Override
    public void updateJsonResponseMock(@NotNull @Valid JsonResponseMock jsonResponseMock) {
        JsonResponseMockPo jsonResponseMockPo = BeanMapper.map(jsonResponseMock, JsonResponseMockPo.class);

        jsonResponseMockDao.updateJsonResponseMock(jsonResponseMockPo);
    }

    @Override
    public void deleteJsonResponseMock(@NotNull String id) {
        jsonResponseMockDao.deleteJsonResponseMock(id);
    }

    @Override
    public JsonResponseMock findJsonResponseMock(@NotNull String id) {
        JsonResponseMockPo jsonResponseMockPo = jsonResponseMockDao.findJsonResponseMock(id);

        JsonResponseMock jsonResponseMock = BeanMapper.map(jsonResponseMockPo, JsonResponseMock.class);

        joinQuery.queryOne(jsonResponseMock);

        return jsonResponseMock;
    }

    @Override
    public List<JsonResponseMock> findAllJsonResponseMock() {
        List<JsonResponseMockPo> jsonResponseMockPoList =  jsonResponseMockDao.findAllJsonResponseMock();

        List<JsonResponseMock> jsonResponseMockList =  BeanMapper.mapList(jsonResponseMockPoList,JsonResponseMock.class);

        joinQuery.queryList(jsonResponseMockList);

        return jsonResponseMockList;
    }

    @Override
    public List<JsonResponseMock> findJsonResponseMockList(JsonResponseMockQuery jsonResponseMockQuery) {
        List<JsonResponseMockPo> jsonResponseMockPoList = jsonResponseMockDao.findJsonResponseMockList(jsonResponseMockQuery);

        List<JsonResponseMock> jsonResponseMockList = BeanMapper.mapList(jsonResponseMockPoList,JsonResponseMock.class);

        joinQuery.queryList(jsonResponseMockList);

        return jsonResponseMockList;
    }

    @Override
    public Pagination<JsonResponseMock> findJsonResponseMockPage(JsonResponseMockQuery jsonResponseMockQuery) {
        Pagination<JsonResponseMock> pg = new Pagination<>();

        Pagination<JsonResponseMockPo>  pagination = jsonResponseMockDao.findJsonResponseMockPage(jsonResponseMockQuery);
        BeanUtils.copyProperties(pagination,pg);

        List<JsonResponseMock> jsonResponseMockList = BeanMapper.mapList(pagination.getDataList(),JsonResponseMock.class);

        joinQuery.queryList(jsonResponseMockList);

        pg.setDataList(jsonResponseMockList);
        return pg;
    }
}