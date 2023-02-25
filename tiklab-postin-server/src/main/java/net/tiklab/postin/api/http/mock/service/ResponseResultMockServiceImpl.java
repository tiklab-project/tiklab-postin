package net.tiklab.postin.api.http.mock.service;

import net.tiklab.postin.api.http.mock.dao.ResponseResultMockDao;
import net.tiklab.postin.api.http.mock.entity.ResponseResultMockEntity;
import net.tiklab.postin.api.http.mock.model.ResponseResultMock;
import net.tiklab.postin.api.http.mock.model.ResponseResultMockQuery;

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
public class ResponseResultMockServiceImpl implements ResponseResultMockService {

    @Autowired
    ResponseResultMockDao responseResultMockDao;

    @Autowired
    JoinTemplate joinTemplate;

    @Override
    public String createResponseResultMock(@NotNull @Valid ResponseResultMock responseResultMock) {
        ResponseResultMockEntity responseResultMockEntity = BeanMapper.map(responseResultMock, ResponseResultMockEntity.class);

        return responseResultMockDao.createResponseResultMock(responseResultMockEntity);
    }

    @Override
    public void updateResponseResultMock(@NotNull @Valid ResponseResultMock responseResultMock) {
        ResponseResultMockEntity responseResultMockEntity = BeanMapper.map(responseResultMock, ResponseResultMockEntity.class);

        responseResultMockDao.updateResponseResultMock(responseResultMockEntity);
    }

    @Override
    public void deleteResponseResultMock(@NotNull String id) {
        responseResultMockDao.deleteResponseResultMock(id);
    }

    @Override
    public ResponseResultMock findResponseResultMock(@NotNull String id) {
        ResponseResultMockEntity responseResultMockEntity = responseResultMockDao.findResponseResultMock(id);

        ResponseResultMock responseResultMock = BeanMapper.map(responseResultMockEntity, ResponseResultMock.class);

        joinTemplate.joinQuery(responseResultMock);

        return responseResultMock;
    }

    @Override
    public List<ResponseResultMock> findAllResponseResultMock() {
        List<ResponseResultMockEntity> responseResultMockEntityList =  responseResultMockDao.findAllResponseResultMock();

        List<ResponseResultMock> responseResultMockList =  BeanMapper.mapList(responseResultMockEntityList, ResponseResultMock.class);

        joinTemplate.joinQuery(responseResultMockList);

        return responseResultMockList;
    }

    @Override
    public List<ResponseResultMock> findResponseResultMockList(ResponseResultMockQuery responseResultMockQuery) {
        List<ResponseResultMockEntity> responseResultMockEntityList = responseResultMockDao.findResponseResultMockList(responseResultMockQuery);

        List<ResponseResultMock> responseResultMockList = BeanMapper.mapList(responseResultMockEntityList, ResponseResultMock.class);

        joinTemplate.joinQuery(responseResultMockList);

        return responseResultMockList;
    }

    @Override
    public Pagination<ResponseResultMock> findResponseResultMockPage(ResponseResultMockQuery responseResultMockQuery) {

        Pagination<ResponseResultMockEntity>  pagination = responseResultMockDao.findResponseResultMockPage(responseResultMockQuery);

        List<ResponseResultMock> responseResultMockList = BeanMapper.mapList(pagination.getDataList(), ResponseResultMock.class);

        joinTemplate.joinQuery(responseResultMockList);

        return PaginationBuilder.build(pagination, responseResultMockList);
    }
}