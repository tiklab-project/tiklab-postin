package com.doublekit.apibox.apimock.http.service;

import com.doublekit.apibox.apimock.http.dao.RawResponseMockDao;
import com.doublekit.apibox.apimock.http.entity.RawResponseMockEntity;
import com.doublekit.apibox.apimock.http.model.RawResponseMock;
import com.doublekit.apibox.apimock.http.model.RawResponseMockQuery;

import com.doublekit.common.page.Pagination;
import com.doublekit.beans.BeanMapper;
import com.doublekit.common.page.PaginationBuilder;
import com.doublekit.join.JoinTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 用户服务业务处理
*/
@Service
public class RawResponseMockServiceImpl implements RawResponseMockService {

    @Autowired
    RawResponseMockDao rawResponseMockDao;

    @Autowired
    JoinTemplate joinTemplate;

    @Override
    public String createRawResponseMock(@NotNull @Valid RawResponseMock rawResponseMock) {
        RawResponseMockEntity rawResponseMockEntity = BeanMapper.map(rawResponseMock, RawResponseMockEntity.class);

        return rawResponseMockDao.createRawResponseMock(rawResponseMockEntity);
    }

    @Override
    public void updateRawResponseMock(@NotNull @Valid RawResponseMock rawResponseMock) {
        RawResponseMockEntity rawResponseMockEntity = BeanMapper.map(rawResponseMock, RawResponseMockEntity.class);

        rawResponseMockDao.updateRawResponseMock(rawResponseMockEntity);
    }

    @Override
    public void deleteRawResponseMock(@NotNull String id) {
        rawResponseMockDao.deleteRawResponseMock(id);
    }

    @Override
    public RawResponseMock findRawResponseMock(@NotNull String id) {
        RawResponseMockEntity rawResponseMockEntity = rawResponseMockDao.findRawResponseMock(id);

        RawResponseMock rawResponseMock = BeanMapper.map(rawResponseMockEntity, RawResponseMock.class);

        joinTemplate.joinQuery(rawResponseMock);

        return rawResponseMock;
    }

    @Override
    public List<RawResponseMock> findAllRawResponseMock() {
        List<RawResponseMockEntity> rawResponseMockEntityList =  rawResponseMockDao.findAllRawResponseMock();

        List<RawResponseMock> rawResponseMockList =  BeanMapper.mapList(rawResponseMockEntityList,RawResponseMock.class);

        joinTemplate.joinQuery(rawResponseMockList);

        return rawResponseMockList;
    }

    @Override
    public List<RawResponseMock> findRawResponseMockList(RawResponseMockQuery rawResponseMockQuery) {
        List<RawResponseMockEntity> rawResponseMockEntityList = rawResponseMockDao.findRawResponseMockList(rawResponseMockQuery);

        List<RawResponseMock> rawResponseMockList = BeanMapper.mapList(rawResponseMockEntityList,RawResponseMock.class);

        joinTemplate.joinQuery(rawResponseMockList);

        return rawResponseMockList;
    }

    @Override
    public Pagination<RawResponseMock> findRawResponseMockPage(RawResponseMockQuery rawResponseMockQuery) {

        Pagination<RawResponseMockEntity>  pagination = rawResponseMockDao.findRawResponseMockPage(rawResponseMockQuery);

        List<RawResponseMock> rawResponseMockList = BeanMapper.mapList(pagination.getDataList(),RawResponseMock.class);

        joinTemplate.joinQuery(rawResponseMockList);

        return PaginationBuilder.build(pagination,rawResponseMockList);
    }
}