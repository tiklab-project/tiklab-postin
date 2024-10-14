package io.tiklab.postin.support.datastructure.service;

import io.tiklab.postin.support.datastructure.dao.JsonParamDSDao;
import io.tiklab.postin.support.datastructure.entity.JsonParamDSEntity;
import io.tiklab.postin.support.datastructure.model.JsonParamDS;
import io.tiklab.postin.support.datastructure.model.JsonParamDSQuery;

import io.tiklab.core.page.Pagination;
import io.tiklab.toolkit.beans.BeanMapper;
import io.tiklab.core.page.PaginationBuilder;
import io.tiklab.toolkit.join.JoinTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

import org.springframework.util.ObjectUtils;

/**
* Json类型 服务
*/
@Service
public class JsonParamDSServiceImpl implements JsonParamDSService {

    @Autowired
    JsonParamDSDao jsonParamDSDao;

    @Autowired
    JoinTemplate joinTemplate;



    @Override
    public String createJsonParamDS(@NotNull @Valid JsonParamDS jsonParamDS) {
        JsonParamDSEntity jsonParamDSEntity = BeanMapper.map(jsonParamDS, JsonParamDSEntity.class);

        return jsonParamDSDao.createJsonParamDS(jsonParamDSEntity);
    }

    @Override
    public void updateJsonParamDS(@NotNull @Valid JsonParamDS jsonParamDS) {
        JsonParamDSEntity jsonParamDSEntity = BeanMapper.map(jsonParamDS, JsonParamDSEntity.class);

        jsonParamDSDao.updateJsonParamDS(jsonParamDSEntity);
    }

    @Override
    public void deleteJsonParamDS(@NotNull String id) {
        //删除相关联的子表

        jsonParamDSDao.deleteJsonParamDS(id);
    }

    @Override
    public JsonParamDS findOne(String id) {
        JsonParamDSEntity jsonParamDSEntity = jsonParamDSDao.findJsonParamDS(id);

        JsonParamDS jsonParamDS = BeanMapper.map(jsonParamDSEntity, JsonParamDS.class);
        return jsonParamDS;
    }

    @Override
    public List<JsonParamDS> findList(List<String> idList) {
        List<JsonParamDSEntity> jsonParamDSEntityList =  jsonParamDSDao.findJsonParamDSList(idList);

        List<JsonParamDS> jsonParamDSList =  BeanMapper.mapList(jsonParamDSEntityList,JsonParamDS.class);
        return jsonParamDSList;
    }

    @Override
    public JsonParamDS findJsonParamDS(@NotNull String id) {
        JsonParamDS jsonParamDS = findOne(id);

        joinTemplate.joinQuery(jsonParamDS);
        return jsonParamDS;
    }

    @Override
    public List<JsonParamDS> findAllJsonParamDS() {
        List<JsonParamDSEntity> jsonParamDSEntityList =  jsonParamDSDao.findAllJsonParamDS();

        List<JsonParamDS> jsonParamDSList =  BeanMapper.mapList(jsonParamDSEntityList,JsonParamDS.class);

        joinTemplate.joinQuery(jsonParamDSList);
        return jsonParamDSList;
    }

    @Override
    public List<JsonParamDS> findJsonParamDSList(JsonParamDSQuery jsonParamDSQuery) {
        List<JsonParamDSEntity> jsonParamDSEntityList = jsonParamDSDao.findJsonParamDSList(jsonParamDSQuery);

        List<JsonParamDS> jsonParamDSList = BeanMapper.mapList(jsonParamDSEntityList,JsonParamDS.class);

        joinTemplate.joinQuery(jsonParamDSList);

        return jsonParamDSList;
    }

    @Override
    public Pagination<JsonParamDS> findJsonParamDSPage(JsonParamDSQuery jsonParamDSQuery) {

        Pagination<JsonParamDSEntity>  pagination = jsonParamDSDao.findJsonParamDSPage(jsonParamDSQuery);

        List<JsonParamDS> jsonParamDSList = BeanMapper.mapList(pagination.getDataList(),JsonParamDS.class);

        joinTemplate.joinQuery(jsonParamDSList);

        return PaginationBuilder.build(pagination,jsonParamDSList);
    }

}

