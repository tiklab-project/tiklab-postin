package com.doublekit.apibox.apimock.service;

import com.doublekit.apibox.apimock.dao.RawResponseMockDao;
import com.doublekit.apibox.apimock.entity.RawResponseMockEntity;
import com.doublekit.apibox.apimock.model.RawResponseMock;
import com.doublekit.apibox.apimock.model.RawResponseMockQuery;

import com.doublekit.common.Pagination;
import com.doublekit.beans.BeanMapper;
import com.doublekit.common.PaginationBuilder;
import com.doublekit.join.JoinTemplate;
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
public class RawResponseMockServiceImpl implements RawResponseMockService {

    @Autowired
    RawResponseMockDao rawResponseMockDao;

    @Autowired
    JoinTemplate joinQuery;

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

        joinQuery.queryOne(rawResponseMock);

        return rawResponseMock;
    }

    @Override
    public List<RawResponseMock> findAllRawResponseMock() {
        List<RawResponseMockEntity> rawResponseMockEntityList =  rawResponseMockDao.findAllRawResponseMock();

        List<RawResponseMock> rawResponseMockList =  BeanMapper.mapList(rawResponseMockEntityList,RawResponseMock.class);

        joinQuery.queryList(rawResponseMockList);

        return rawResponseMockList;
    }

    @Override
    public List<RawResponseMock> findRawResponseMockList(RawResponseMockQuery rawResponseMockQuery) {
        List<RawResponseMockEntity> rawResponseMockEntityList = rawResponseMockDao.findRawResponseMockList(rawResponseMockQuery);

        List<RawResponseMock> rawResponseMockList = BeanMapper.mapList(rawResponseMockEntityList,RawResponseMock.class);

        joinQuery.queryList(rawResponseMockList);

        return rawResponseMockList;
    }

    @Override
    public Pagination<RawResponseMock> findRawResponseMockPage(RawResponseMockQuery rawResponseMockQuery) {

        Pagination<RawResponseMockEntity>  pagination = rawResponseMockDao.findRawResponseMockPage(rawResponseMockQuery);

        List<RawResponseMock> rawResponseMockList = BeanMapper.mapList(pagination.getDataList(),RawResponseMock.class);

        joinQuery.queryList(rawResponseMockList);

        return PaginationBuilder.build(pagination,rawResponseMockList);
    }
}