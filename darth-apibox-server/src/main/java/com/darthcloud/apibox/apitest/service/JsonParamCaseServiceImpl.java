package com.darthcloud.apibox.apitest.service;

import com.darthcloud.apibox.apitest.dao.JsonParamCaseDao;
import com.darthcloud.apibox.apitest.entity.JsonParamCasePo;
import com.darthcloud.apibox.apitest.model.JsonParamCase;
import com.darthcloud.apibox.apitest.model.JsonParamCaseQuery;

import com.darthcloud.common.Pagination;
import com.darthcloud.beans.BeanMapper;
import com.darthcloud.join.join.JoinQuery;
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
public class JsonParamCaseServiceImpl implements JsonParamCaseService {

    @Autowired
    JsonParamCaseDao jsonParamCaseDao;

    @Autowired
    JoinQuery joinQuery;

    @Override
    public String createJsonParamCase(@NotNull @Valid JsonParamCase jsonParamCase) {
        JsonParamCasePo jsonParamCasePo = BeanMapper.map(jsonParamCase, JsonParamCasePo.class);

        return jsonParamCaseDao.createJsonParamCase(jsonParamCasePo);
    }

    @Override
    public void updateJsonParamCase(@NotNull @Valid JsonParamCase jsonParamCase) {
        JsonParamCasePo jsonParamCasePo = BeanMapper.map(jsonParamCase, JsonParamCasePo.class);

        jsonParamCaseDao.updateJsonParamCase(jsonParamCasePo);
    }

    @Override
    public void deleteJsonParamCase(@NotNull String id) {
        jsonParamCaseDao.deleteJsonParamCase(id);
    }

    @Override
    public JsonParamCase findOne(String id) {
        JsonParamCasePo jsonParamCasePo = jsonParamCaseDao.findJsonParamCase(id);

        JsonParamCase jsonParamCase = BeanMapper.map(jsonParamCasePo, JsonParamCase.class);
        return jsonParamCase;
    }

    @Override
    public List<JsonParamCase> findList(List<String> idList) {
        List<JsonParamCasePo> jsonParamCasePoList =  jsonParamCaseDao.findJsonParamCaseList(idList);

        List<JsonParamCase> jsonParamCaseList =  BeanMapper.mapList(jsonParamCasePoList,JsonParamCase.class);
        return jsonParamCaseList;
    }

    @Override
    public JsonParamCase findJsonParamCase(@NotNull String id) {
        JsonParamCase jsonParamCase = findOne(id);

        joinQuery.queryOne(jsonParamCase);
        return jsonParamCase;
    }

    @Override
    public List<JsonParamCase> findAllJsonParamCase() {
        List<JsonParamCasePo> jsonParamCasePoList =  jsonParamCaseDao.findAllJsonParamCase();

        List<JsonParamCase> jsonParamCaseList =  BeanMapper.mapList(jsonParamCasePoList,JsonParamCase.class);

        joinQuery.queryList(jsonParamCaseList);
        return jsonParamCaseList;
    }

    @Override
    public List<JsonParamCase> findJsonParamCaseList(JsonParamCaseQuery jsonParamCaseQuery) {
        List<JsonParamCasePo> jsonParamCasePoList = jsonParamCaseDao.findJsonParamCaseList(jsonParamCaseQuery);

        List<JsonParamCase> jsonParamCaseList = BeanMapper.mapList(jsonParamCasePoList,JsonParamCase.class);

        joinQuery.queryList(jsonParamCaseList);

        return jsonParamCaseList;
    }

    @Override
    public Pagination<List<JsonParamCase>> findJsonParamCasePage(JsonParamCaseQuery jsonParamCaseQuery) {
        Pagination<List<JsonParamCase>> pg = new Pagination<>();

        Pagination<List<JsonParamCasePo>>  pagination = jsonParamCaseDao.findJsonParamCasePage(jsonParamCaseQuery);
        BeanUtils.copyProperties(pagination,pg);

        List<JsonParamCase> jsonParamCaseList = BeanMapper.mapList(pagination.getDataList(),JsonParamCase.class);

        joinQuery.queryList(jsonParamCaseList);

        pg.setDataList(jsonParamCaseList);
        return pg;
    }
}