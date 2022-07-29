package com.tiklab.postlink.apimock.http.service;

import com.tiklab.postlink.apimock.http.dao.ResponseHeaderMockDao;
import com.tiklab.postlink.apimock.http.entity.ResponseHeaderMockEntity;
import com.tiklab.postlink.apimock.http.model.ResponseHeaderMock;
import com.tiklab.postlink.apimock.http.model.ResponseHeaderMockQuery;

import com.tiklab.core.page.Pagination;
import com.tiklab.beans.BeanMapper;
import com.tiklab.core.page.PaginationBuilder;
import com.tiklab.join.JoinTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 用户服务业务处理
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