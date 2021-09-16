package com.doublekit.apibox.apimock.service;

import com.doublekit.apibox.apimock.dao.RawResponseMockDao;
import com.doublekit.apibox.apimock.entity.RawResponseMockPo;
import com.doublekit.apibox.apimock.model.RawResponseMock;
import com.doublekit.apibox.apimock.model.RawResponseMockQuery;

import com.doublekit.common.Pagination;
import com.doublekit.beans.BeanMapper;
import com.doublekit.join.join.JoinQuery;
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
    JoinQuery joinQuery;

    @Override
    public String createRawResponseMock(@NotNull @Valid RawResponseMock rawResponseMock) {
        RawResponseMockPo rawResponseMockPo = BeanMapper.map(rawResponseMock, RawResponseMockPo.class);

        return rawResponseMockDao.createRawResponseMock(rawResponseMockPo);
    }

    @Override
    public void updateRawResponseMock(@NotNull @Valid RawResponseMock rawResponseMock) {
        RawResponseMockPo rawResponseMockPo = BeanMapper.map(rawResponseMock, RawResponseMockPo.class);

        rawResponseMockDao.updateRawResponseMock(rawResponseMockPo);
    }

    @Override
    public void deleteRawResponseMock(@NotNull String id) {
        rawResponseMockDao.deleteRawResponseMock(id);
    }

    @Override
    public RawResponseMock findRawResponseMock(@NotNull String id) {
        RawResponseMockPo rawResponseMockPo = rawResponseMockDao.findRawResponseMock(id);

        RawResponseMock rawResponseMock = BeanMapper.map(rawResponseMockPo, RawResponseMock.class);

        joinQuery.queryOne(rawResponseMock);

        return rawResponseMock;
    }

    @Override
    public List<RawResponseMock> findAllRawResponseMock() {
        List<RawResponseMockPo> rawResponseMockPoList =  rawResponseMockDao.findAllRawResponseMock();

        List<RawResponseMock> rawResponseMockList =  BeanMapper.mapList(rawResponseMockPoList,RawResponseMock.class);

        joinQuery.queryList(rawResponseMockList);

        return rawResponseMockList;
    }

    @Override
    public List<RawResponseMock> findRawResponseMockList(RawResponseMockQuery rawResponseMockQuery) {
        List<RawResponseMockPo> rawResponseMockPoList = rawResponseMockDao.findRawResponseMockList(rawResponseMockQuery);

        List<RawResponseMock> rawResponseMockList = BeanMapper.mapList(rawResponseMockPoList,RawResponseMock.class);

        joinQuery.queryList(rawResponseMockList);

        return rawResponseMockList;
    }

    @Override
    public Pagination<RawResponseMock> findRawResponseMockPage(RawResponseMockQuery rawResponseMockQuery) {
        Pagination<RawResponseMock> pg = new Pagination<>();

        Pagination<RawResponseMockPo>  pagination = rawResponseMockDao.findRawResponseMockPage(rawResponseMockQuery);
        BeanUtils.copyProperties(pagination,pg);

        List<RawResponseMock> rawResponseMockList = BeanMapper.mapList(pagination.getDataList(),RawResponseMock.class);

        joinQuery.queryList(rawResponseMockList);

        pg.setDataList(rawResponseMockList);
        return pg;
    }
}