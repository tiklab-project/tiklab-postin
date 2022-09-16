package net.tiklab.postin.apimock.http.service;

import net.tiklab.postin.apimock.http.dao.JsonResponseMockDao;
import net.tiklab.postin.apimock.http.entity.JsonResponseMockEntity;
import net.tiklab.postin.apimock.http.model.JsonResponseMock;
import net.tiklab.postin.apimock.http.model.JsonResponseMockQuery;

import net.tiklab.core.page.Pagination;
import net.tiklab.beans.BeanMapper;
import net.tiklab.core.page.PaginationBuilder;
import net.tiklab.join.JoinTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 用户服务业务处理
*/
@Service
public class JsonResponseMockServiceImpl implements JsonResponseMockService {

    @Autowired
    JsonResponseMockDao jsonResponseMockDao;

    @Autowired
    JoinTemplate joinTemplate;

    @Override
    public String createJsonResponseMock(@NotNull @Valid JsonResponseMock jsonResponseMock) {
        JsonResponseMockEntity jsonResponseMockEntity = BeanMapper.map(jsonResponseMock, JsonResponseMockEntity.class);

        return jsonResponseMockDao.createJsonResponseMock(jsonResponseMockEntity);
    }

    @Override
    public void updateJsonResponseMock(@NotNull @Valid JsonResponseMock jsonResponseMock) {
        JsonResponseMockEntity jsonResponseMockEntity = BeanMapper.map(jsonResponseMock, JsonResponseMockEntity.class);

        jsonResponseMockDao.updateJsonResponseMock(jsonResponseMockEntity);
    }

    @Override
    public void deleteJsonResponseMock(@NotNull String id) {
        jsonResponseMockDao.deleteJsonResponseMock(id);
    }

    @Override
    public JsonResponseMock findJsonResponseMock(@NotNull String id) {
        JsonResponseMockEntity jsonResponseMockEntity = jsonResponseMockDao.findJsonResponseMock(id);

        JsonResponseMock jsonResponseMock = BeanMapper.map(jsonResponseMockEntity, JsonResponseMock.class);

        joinTemplate.joinQuery(jsonResponseMock);

        return jsonResponseMock;
    }

    @Override
    public List<JsonResponseMock> findAllJsonResponseMock() {
        List<JsonResponseMockEntity> jsonResponseMockEntityList =  jsonResponseMockDao.findAllJsonResponseMock();

        List<JsonResponseMock> jsonResponseMockList =  BeanMapper.mapList(jsonResponseMockEntityList,JsonResponseMock.class);

        joinTemplate.joinQuery(jsonResponseMockList);

        return jsonResponseMockList;
    }

    @Override
    public List<JsonResponseMock> findJsonResponseMockList(JsonResponseMockQuery jsonResponseMockQuery) {
        List<JsonResponseMockEntity> jsonResponseMockEntityList = jsonResponseMockDao.findJsonResponseMockList(jsonResponseMockQuery);

        List<JsonResponseMock> jsonResponseMockList = BeanMapper.mapList(jsonResponseMockEntityList,JsonResponseMock.class);

        joinTemplate.joinQuery(jsonResponseMockList);

        return jsonResponseMockList;
    }

    @Override
    public Pagination<JsonResponseMock> findJsonResponseMockPage(JsonResponseMockQuery jsonResponseMockQuery) {

        Pagination<JsonResponseMockEntity>  pagination = jsonResponseMockDao.findJsonResponseMockPage(jsonResponseMockQuery);

        List<JsonResponseMock> jsonResponseMockList = BeanMapper.mapList(pagination.getDataList(),JsonResponseMock.class);

        joinTemplate.joinQuery(jsonResponseMockList);

        return PaginationBuilder.build(pagination,jsonResponseMockList);
    }
}