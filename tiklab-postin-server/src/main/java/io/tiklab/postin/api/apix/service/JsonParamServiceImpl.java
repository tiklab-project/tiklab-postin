package io.tiklab.postin.api.apix.service;

import io.tiklab.postin.api.apix.service.JsonParamService;
import io.tiklab.postin.api.apix.dao.JsonParamDao;
import io.tiklab.postin.api.apix.entity.JsonParamEntity;
import io.tiklab.postin.api.apix.model.JsonParam;
import io.tiklab.postin.api.apix.model.JsonParamQuery;

import io.tiklab.core.page.Pagination;
import io.tiklab.toolkit.beans.BeanMapper;
import io.tiklab.core.page.PaginationBuilder;
import io.tiklab.toolkit.join.JoinTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 请求中json服务
*/
@Service
public class JsonParamServiceImpl implements JsonParamService {

    @Autowired
    JsonParamDao jsonParamDao;

    @Autowired
    JoinTemplate joinTemplate;

    @Override
    public String createJsonParam(@NotNull @Valid JsonParam jsonParam) {
        JsonParamEntity jsonParamPo = BeanMapper.map(jsonParam, JsonParamEntity.class);

        return jsonParamDao.createJsonParam(jsonParamPo);
    }

    @Override
    public void updateJsonParam(@NotNull @Valid JsonParam jsonParam) {
        JsonParamEntity jsonParamPo = BeanMapper.map(jsonParam, JsonParamEntity.class);

        jsonParamDao.updateJsonParam(jsonParamPo);
    }

    @Override
    public void deleteJsonParam(@NotNull String id) {
        jsonParamDao.deleteJsonParam(id);
    }

    @Override
    public JsonParam findOne(String id) {
        JsonParamEntity jsonParamPo = jsonParamDao.findJsonParam(id);

        JsonParam jsonParam = BeanMapper.map(jsonParamPo, JsonParam.class);
        return jsonParam;
    }

    @Override
    public List<JsonParam> findList(List<String> idList) {
        List<JsonParamEntity> jsonParamPoList =  jsonParamDao.findJsonParamList(idList);

        List<JsonParam> jsonParamList = BeanMapper.mapList(jsonParamPoList,JsonParam.class);

        joinTemplate.joinQuery(jsonParamList);
        return jsonParamList;
    }

    @Override
    public JsonParam findJsonParam(@NotNull String id) {
        JsonParam jsonParam = findOne(id);

        joinTemplate.joinQuery(jsonParam);
        return jsonParam;
    }

    @Override
    public List<JsonParam> findAllJsonParam() {
        List<JsonParamEntity> jsonParamPoList =  jsonParamDao.findAllJsonParam();

        List<JsonParam> jsonParamList = BeanMapper.mapList(jsonParamPoList,JsonParam.class);

        joinTemplate.joinQuery(jsonParamList);
        return jsonParamList;
    }

    @Override
    public List<JsonParam> findJsonParamList(JsonParamQuery jsonParamQuery) {
        List<JsonParamEntity> jsonParamPoList = jsonParamDao.findJsonParamList(jsonParamQuery);

        List<JsonParam> jsonParamList = BeanMapper.mapList(jsonParamPoList,JsonParam.class);

        joinTemplate.joinQuery(jsonParamList);

        return jsonParamList;
    }



    @Override
    public Pagination<JsonParam> findJsonParamPage(JsonParamQuery jsonParamQuery) {

        Pagination<JsonParamEntity>  pagination = jsonParamDao.findJsonParamPage(jsonParamQuery);

        List<JsonParam> jsonParamList = BeanMapper.mapList(pagination.getDataList(),JsonParam.class);

        joinTemplate.joinQuery(jsonParamList);

        return PaginationBuilder.build(pagination,jsonParamList);
    }
}