package com.darthcloud.apibox.jsonparam.service;

import com.darthcloud.apibox.jsonparam.dao.JsonParamDao;
import com.darthcloud.apibox.jsonparam.entity.JsonParamPo;
import com.darthcloud.apibox.jsonparam.model.JsonParam;
import com.darthcloud.apibox.jsonparam.model.JsonParamQuery;

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
public class JsonParamServiceImpl implements JsonParamService {

    @Autowired
    JsonParamDao jsonParamDao;

    @Autowired
    JoinQuery joinQuery;

    @Override
    public String createJsonParam(@NotNull @Valid JsonParam jsonParam) {
        JsonParamPo jsonParamPo = BeanMapper.map(jsonParam, JsonParamPo.class);

        return jsonParamDao.createJsonParam(jsonParamPo);
    }

    @Override
    public void updateJsonParam(@NotNull @Valid JsonParam jsonParam) {
        JsonParamPo jsonParamPo = BeanMapper.map(jsonParam, JsonParamPo.class);

        jsonParamDao.updateJsonParam(jsonParamPo);
    }

    @Override
    public void deleteJsonParam(@NotNull String id) {
        jsonParamDao.deleteJsonParam(id);
    }

    @Override
    public JsonParam findJsonParam(@NotNull String id) {
        JsonParamPo jsonParamPo = jsonParamDao.findJsonParam(id);

        JsonParam jsonParam = BeanMapper.map(jsonParamPo, JsonParam.class);

        joinQuery.queryOne(jsonParam);

        return jsonParam;
    }

    @Override
    public List<JsonParam> findAllJsonParam() {
        List<JsonParamPo> jsonParamPoList =  jsonParamDao.findAllJsonParam();

        List<JsonParam> jsonParamList = BeanMapper.mapList(jsonParamPoList,JsonParam.class);

        joinQuery.queryList(jsonParamList);

        return jsonParamList;
    }

    @Override
    public List<JsonParam> findJsonParamList(JsonParamQuery jsonParamQuery) {
        List<JsonParamPo> jsonParamPoList = jsonParamDao.findJsonParamList(jsonParamQuery);

        List<JsonParam> jsonParamList = BeanMapper.mapList(jsonParamPoList,JsonParam.class);

        joinQuery.queryList(jsonParamList);

        return jsonParamList;
    }

    @Override
    public Pagination<List<JsonParam>> findJsonParamPage(JsonParamQuery jsonParamQuery) {
        Pagination<List<JsonParam>> pg = new Pagination<>();

        Pagination<List<JsonParamPo>>  pagination = jsonParamDao.findJsonParamPage(jsonParamQuery);
        BeanUtils.copyProperties(pagination,pg);

        List<JsonParam> jsonParamList = BeanMapper.mapList(pagination.getDataList(),JsonParam.class);

        joinQuery.queryList(jsonParamList);

        pg.setDataList(jsonParamList);
        return pg;
    }
}