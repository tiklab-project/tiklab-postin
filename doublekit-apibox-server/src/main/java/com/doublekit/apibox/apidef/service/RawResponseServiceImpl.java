package com.doublekit.apibox.apidef.service;

import com.doublekit.apibox.apidef.dao.RawResponseDao;
import com.doublekit.apibox.apidef.entity.RawResponsePo;
import com.doublekit.apibox.apidef.model.RawResponse;
import com.doublekit.apibox.apidef.model.RawResponseQuery;

import com.doublekit.common.Pagination;
import com.doublekit.beans.BeanMapper;
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
public class RawResponseServiceImpl implements RawResponseService {

    @Autowired
    RawResponseDao rawResponseDao;

    @Autowired
    JoinTemplate joinQuery;

    @Override
    public String createRawResponse(@NotNull @Valid RawResponse rawResponse) {
        RawResponsePo rawResponsePo = BeanMapper.map(rawResponse, RawResponsePo.class);

        return rawResponseDao.createRawResponse(rawResponsePo);
    }

    @Override
    public void updateRawResponse(@NotNull @Valid RawResponse rawResponse) {
        RawResponsePo rawResponsePo = BeanMapper.map(rawResponse, RawResponsePo.class);

        rawResponseDao.updateRawResponse(rawResponsePo);
    }

    @Override
    public void deleteRawResponse(@NotNull String id) {
        rawResponseDao.deleteRawResponse(id);
    }

    @Override
    public RawResponse findRawResponse(@NotNull String id) {
        RawResponsePo rawResponsePo = rawResponseDao.findRawResponse(id);

        RawResponse rawResponse = BeanMapper.map(rawResponsePo, RawResponse.class);

        joinQuery.queryOne(rawResponse);

        return rawResponse;
    }

    @Override
    public List<RawResponse> findAllRawResponse() {
        List<RawResponsePo> rawResponsePoList =  rawResponseDao.findAllRawResponse();

        List<RawResponse> rawResponseList =  BeanMapper.mapList(rawResponsePoList,RawResponse.class);

        joinQuery.queryList(rawResponseList);

        return rawResponseList;
    }

    @Override
    public List<RawResponse> findRawResponseList(RawResponseQuery rawResponseQuery) {
        List<RawResponsePo> rawResponsePoList = rawResponseDao.findRawResponseList(rawResponseQuery);

        List<RawResponse> rawResponseList = BeanMapper.mapList(rawResponsePoList,RawResponse.class);

        joinQuery.queryList(rawResponseList);

        return rawResponseList;
    }

    @Override
    public Pagination<RawResponse> findRawResponsePage(RawResponseQuery rawResponseQuery) {
        Pagination<RawResponse> pg = new Pagination<>();

        Pagination<RawResponsePo>  pagination = rawResponseDao.findRawResponsePage(rawResponseQuery);
        BeanUtils.copyProperties(pagination,pg);

        List<RawResponse> rawResponseList = BeanMapper.mapList(pagination.getDataList(),RawResponse.class);

        joinQuery.queryList(rawResponseList);

        pg.setDataList(rawResponseList);
        return pg;
    }
}