package io.thoughtware.postin.api.http.mock.service;

import io.thoughtware.dal.jpa.criterial.condition.DeleteCondition;
import io.thoughtware.dal.jpa.criterial.conditionbuilder.DeleteBuilders;
import io.thoughtware.postin.api.http.mock.dao.MockDao;
import io.thoughtware.postin.api.http.mock.entity.MockEntity;
import io.thoughtware.postin.api.http.mock.model.Mock;
import io.thoughtware.postin.api.http.mock.model.MockQuery;
import io.thoughtware.beans.BeanMapper;
import io.thoughtware.core.page.Pagination;
import io.thoughtware.core.page.PaginationBuilder;
import io.thoughtware.join.JoinTemplate;
import io.thoughtware.postin.api.http.mock.model.RequestMock;
import io.thoughtware.postin.api.http.mock.model.ResponseMock;
import io.thoughtware.eam.common.context.LoginContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.List;

/**
* mock 服务
*/
@Service
public class MockServiceImpl implements MockService {

    @Autowired
    MockDao mockDao;

    @Autowired
    RequestHeaderMockService requestHeaderMockService;

    @Autowired
    QueryParamMockService queryParamMockService;

    @Autowired
    FormParamMockService formParamMockService;

    @Autowired
    JsonParamMockService jsonParamMockService;

    @Autowired
    RequestMockService requestMockService;

    @Autowired
    ResponseMockService responseMockService;

    @Autowired
    ResponseHeaderMockService responseHeaderMockService;

    @Autowired
    ResponseResultMockService responseResultMockService;

    @Autowired
    JoinTemplate joinTemplate;

    @Override
    public String createMock(@NotNull @Valid Mock mock) {
        MockEntity mockEntity = BeanMapper.map(mock, MockEntity.class);
        mockEntity.setCreateUser(LoginContext.getLoginId());
        mockEntity.setCreateTime(new Timestamp(System.currentTimeMillis()));
        String id = mockDao.createMock(mockEntity);

        //初始化，请求中的bodyType
        RequestMock requestMock = new RequestMock();
        requestMock.setId(id);
        requestMock.setMockId(id);
        requestMock.setBodyType("form");
        requestMockService.createRequestMock(requestMock);

        //初始化，响应中的参数
        ResponseMock responseMock = new ResponseMock();
        responseMock.setId(id);
        responseMock.setMockId(id);
        responseMock.setBodyType("application/json");
        responseMock.setHttpCode("200");
        responseMock.setTime(0);
        responseMockService.createResponseMock(responseMock);

        return id;
    }

    @Override
    public void updateMock(@NotNull @Valid Mock mock) {
        MockEntity mockEntity = BeanMapper.map(mock, MockEntity.class);

        mockDao.updateMock(mockEntity);
    }

    @Override
    public void deleteMock(@NotNull String id) {
        requestHeaderMockService.deleteAllRequestHeaderMock(id);

        queryParamMockService.deleteAllQueryParamMock(id);

        formParamMockService.deleteAllFormParamMock(id);

        jsonParamMockService.deleteAllJsonParamMock(id);

        requestMockService.deleteRequestMock(id);

        responseMockService.deleteResponseMock(id);

        responseHeaderMockService.deleteAllResponseHeaderMock(id);

        responseResultMockService.deleteResponseResultMock(id);

        mockDao.deleteMock(id);
    }

    @Override
    public void deleteAllMock(String httpId) {
        DeleteCondition deleteCondition = DeleteBuilders.createDelete(MockEntity.class)
                .eq("httpId", httpId)
                .get();
        mockDao.deleteMockList(deleteCondition);
    }

    @Override
    public Mock findOne(String id) {
        MockEntity mockEntity = mockDao.findMock(id);

        Mock mock = BeanMapper.map(mockEntity, Mock.class);
        return mock;
    }

    @Override
    public List<Mock> findList(List<String> idList) {
        List<MockEntity> mockEntityList =  mockDao.findMockList(idList);

        List<Mock> mockList =  BeanMapper.mapList(mockEntityList,Mock.class);
        return mockList;
    }

    @Override
    public Mock findMock(@NotNull String id) {
        Mock mock = findOne(id);

        joinTemplate.joinQuery(mock);
        return mock;
    }

    @Override
    public List<Mock> findAllMock() {
        List<MockEntity> mockEntityList =  mockDao.findAllMock();

        List<Mock> mockList =  BeanMapper.mapList(mockEntityList,Mock.class);

        joinTemplate.joinQuery(mockList);

        return mockList;
    }

    @Override
    public List<Mock> findMockList(MockQuery mockQuery) {
        List<MockEntity> mockEntityList = mockDao.findMockList(mockQuery);

        List<Mock> mockList = BeanMapper.mapList(mockEntityList,Mock.class);

        joinTemplate.joinQuery(mockList);

        return mockList;
    }

    @Override
    public Pagination<Mock> findMockPage(MockQuery mockQuery) {

        Pagination<MockEntity>  pagination = mockDao.findMockPage(mockQuery);

        List<Mock> mockList = BeanMapper.mapList(pagination.getDataList(),Mock.class);

        joinTemplate.joinQuery(mockList);

        return PaginationBuilder.build(pagination,mockList);
    }
}