package com.darthcloud.apibox.apidef.service;

import com.darthcloud.apibox.apidef.dao.ResponseHeaderDao;
import com.darthcloud.apibox.apidef.entity.ResponseHeaderPo;
import com.darthcloud.apibox.apidef.model.ResponseHeader;
import com.darthcloud.apibox.apidef.model.ResponseHeaderQuery;

import com.darthcloud.common.Pagination;
import com.darthcloud.beans.BeanMapper;
import com.darthcloud.join.join.JoinQuery;
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
public class ResponseHeaderServiceImpl implements ResponseHeaderService {

    @Autowired
    ResponseHeaderDao responseHeaderDao;

    @Autowired
    JoinQuery joinQuery;

    @Override
    public String createResponseHeader(@NotNull @Valid ResponseHeader responseHeader) {
        ResponseHeaderPo responseHeaderPo = BeanMapper.map(responseHeader, ResponseHeaderPo.class);

        return responseHeaderDao.createResponseHeader(responseHeaderPo);
    }

    @Override
    public void updateResponseHeader(@NotNull @Valid ResponseHeader responseHeader) {
        ResponseHeaderPo responseHeaderPo = BeanMapper.map(responseHeader, ResponseHeaderPo.class);

        responseHeaderDao.updateResponseHeader(responseHeaderPo);
    }

    @Override
    public void deleteResponseHeader(@NotNull String id) {
        responseHeaderDao.deleteResponseHeader(id);
    }

    @Override
    public ResponseHeader findResponseHeader(@NotNull String id) {
        ResponseHeaderPo responseHeaderPo = responseHeaderDao.findResponseHeader(id);

        ResponseHeader responseHeader = BeanMapper.map(responseHeaderPo, ResponseHeader.class);

        joinQuery.queryOne(responseHeader);

        return responseHeader;
    }

    @Override
    public List<ResponseHeader> findAllResponseHeader() {
        List<ResponseHeaderPo> responseHeaderPoList =  responseHeaderDao.findAllResponseHeader();

        List<ResponseHeader> responseHeaderList = BeanMapper.mapList(responseHeaderPoList,ResponseHeader.class);

        joinQuery.queryList(responseHeaderList);

        return responseHeaderList;
    }

    @Override
    public List<ResponseHeader> findResponseHeaderList(ResponseHeaderQuery responseHeaderQuery) {
        List<ResponseHeaderPo> responseHeaderPoList = responseHeaderDao.findResponseHeaderList(responseHeaderQuery);

        List<ResponseHeader> responseHeaderList = BeanMapper.mapList(responseHeaderPoList,ResponseHeader.class);

        joinQuery.queryList(responseHeaderList);

        return responseHeaderList;
    }

    @Override
    public Pagination<List<ResponseHeader>> findResponseHeaderPage(ResponseHeaderQuery responseHeaderQuery) {
        Pagination<List<ResponseHeader>> pg = new Pagination<>();

        Pagination<List<ResponseHeaderPo>>  pagination = responseHeaderDao.findResponseHeaderPage(responseHeaderQuery);
        BeanUtils.copyProperties(pagination,pg);

        List<ResponseHeader> responseHeaderList = BeanMapper.mapList(pagination.getDataList(),ResponseHeader.class);

        joinQuery.queryList(responseHeaderList);

        pg.setDataList(responseHeaderList);
        return pg;
    }
}