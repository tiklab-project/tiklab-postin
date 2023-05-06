package io.tiklab.postin.api.http.mock.service;

import io.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.DeleteBuilders;
import io.tiklab.postin.api.http.mock.dao.ResponseHeaderMockDao;
import io.tiklab.postin.api.http.mock.entity.QueryParamMockEntity;
import io.tiklab.postin.api.http.mock.entity.ResponseHeaderMockEntity;
import io.tiklab.postin.api.http.mock.model.ResponseHeaderMock;
import io.tiklab.postin.api.http.mock.model.ResponseHeaderMockQuery;

import io.tiklab.core.page.Pagination;
import io.tiklab.beans.BeanMapper;
import io.tiklab.core.page.PaginationBuilder;
import io.tiklab.join.JoinTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * mock
 * 响应头 服务
 */
@Service
public class ResponseHeaderMockServiceImpl implements ResponseHeaderMockService {

    @Autowired
    ResponseHeaderMockDao responseHeaderMockDao;

    @Autowired
    JoinTemplate joinTemplate;

    @Override
    public String createResponseHeaderMock(@NotNull @Valid ResponseHeaderMock responseHeaderMock) {
        ResponseHeaderMockEntity responseHeaderMockEntity = BeanMapper.map(responseHeaderMock, ResponseHeaderMockEntity.class);

        return responseHeaderMockDao.createResponseHeaderMock(responseHeaderMockEntity);
    }

    @Override
    public void updateResponseHeaderMock(@NotNull @Valid ResponseHeaderMock responseHeaderMock) {
        ResponseHeaderMockEntity responseHeaderMockEntity = BeanMapper.map(responseHeaderMock, ResponseHeaderMockEntity.class);

        responseHeaderMockDao.updateResponseHeaderMock(responseHeaderMockEntity);
    }

    @Override
    public void deleteResponseHeaderMock(@NotNull String id) {
        responseHeaderMockDao.deleteResponseHeaderMock(id);
    }

    @Override
    public void deleteAllResponseHeaderMock(String mockId) {
        DeleteCondition deleteCondition = DeleteBuilders.createDelete(ResponseHeaderMockEntity.class)
                .eq("mockId", mockId)
                .get();
        responseHeaderMockDao.deleteResponseHeaderMockList(deleteCondition);
    }

    @Override
    public ResponseHeaderMock findResponseHeaderMock(@NotNull String id) {
        ResponseHeaderMockEntity responseHeaderMockEntity = responseHeaderMockDao.findResponseHeaderMock(id);

        ResponseHeaderMock responseHeaderMock = BeanMapper.map(responseHeaderMockEntity, ResponseHeaderMock.class);

        joinTemplate.joinQuery(responseHeaderMock);

        return responseHeaderMock;
    }

    @Override
    public List<ResponseHeaderMock> findAllResponseHeaderMock() {
        List<ResponseHeaderMockEntity> responseHeaderMockEntityList =  responseHeaderMockDao.findAllResponseHeaderMock();

        List<ResponseHeaderMock> responseHeaderMockList =  BeanMapper.mapList(responseHeaderMockEntityList,ResponseHeaderMock.class);

        joinTemplate.joinQuery(responseHeaderMockList);

        return responseHeaderMockList;
    }

    @Override
    public List<ResponseHeaderMock> findResponseHeaderMockList(ResponseHeaderMockQuery responseHeaderMockQuery) {
        List<ResponseHeaderMockEntity> responseHeaderMockEntityList = responseHeaderMockDao.findResponseHeaderMockList(responseHeaderMockQuery);

        List<ResponseHeaderMock> responseHeaderMockList = BeanMapper.mapList(responseHeaderMockEntityList,ResponseHeaderMock.class);

        joinTemplate.joinQuery(responseHeaderMockList);

        return responseHeaderMockList;
    }

    @Override
    public Pagination<ResponseHeaderMock> findResponseHeaderMockPage(ResponseHeaderMockQuery responseHeaderMockQuery) {

        Pagination<ResponseHeaderMockEntity>  pagination = responseHeaderMockDao.findResponseHeaderMockPage(responseHeaderMockQuery);

        List<ResponseHeaderMock> responseHeaderMockList = BeanMapper.mapList(pagination.getDataList(),ResponseHeaderMock.class);

        joinTemplate.joinQuery(responseHeaderMockList);

        return PaginationBuilder.build(pagination,responseHeaderMockList);
    }
}