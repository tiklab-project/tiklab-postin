package io.tiklab.postin.api.http.mock.service;

import io.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.DeleteBuilders;
import io.tiklab.postin.api.http.mock.dao.JsonParamMockDao;
import io.tiklab.postin.api.http.mock.entity.JsonParamMockEntity;
import io.tiklab.postin.api.http.mock.model.JsonParamMock;
import io.tiklab.postin.api.http.mock.model.JsonParamMockQuery;

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
 * 请求中json 服务
 */
@Service
public class JsonParamMockServiceImpl implements JsonParamMockService {

    @Autowired
    JsonParamMockDao jsonParamMockDao;

    @Autowired
    JoinTemplate joinTemplate;

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
    public void deleteAllJsonParamMock(String mockId) {
        DeleteCondition deleteCondition = DeleteBuilders.createDelete(JsonParamMockEntity.class)
                .eq("mockId", mockId)
                .get();
        jsonParamMockDao.deleteJsonParamMockList(deleteCondition);
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

        joinTemplate.joinQuery(jsonParamMock);
        return jsonParamMock;
    }

    @Override
    public List<JsonParamMock> findAllJsonParamMock() {
        List<JsonParamMockEntity> jsonParamMockEntityList =  jsonParamMockDao.findAllJsonParamMock();

        List<JsonParamMock> jsonParamMockList =  BeanMapper.mapList(jsonParamMockEntityList,JsonParamMock.class);

        joinTemplate.joinQuery(jsonParamMockList);
        return jsonParamMockList;
    }

    @Override
    public List<JsonParamMock> findJsonParamMockList(JsonParamMockQuery jsonParamMockQuery) {
        List<JsonParamMockEntity> jsonParamMockEntityList = jsonParamMockDao.findJsonParamMockList(jsonParamMockQuery);

        List<JsonParamMock> jsonParamMockList = BeanMapper.mapList(jsonParamMockEntityList,JsonParamMock.class);

        joinTemplate.joinQuery(jsonParamMockList);

        return jsonParamMockList;
    }

    @Override
    public Pagination<JsonParamMock> findJsonParamMockPage(JsonParamMockQuery jsonParamMockQuery) {

        Pagination<JsonParamMockEntity>  pagination = jsonParamMockDao.findJsonParamMockPage(jsonParamMockQuery);

        List<JsonParamMock> jsonParamMockList = BeanMapper.mapList(pagination.getDataList(),JsonParamMock.class);

        joinTemplate.joinQuery(jsonParamMockList);

        return PaginationBuilder.build(pagination,jsonParamMockList);
    }
}