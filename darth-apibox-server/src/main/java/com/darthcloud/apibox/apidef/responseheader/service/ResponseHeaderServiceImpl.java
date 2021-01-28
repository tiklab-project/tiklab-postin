package com.darthcloud.apibox.apidef.responseheader.service;

import com.darthcloud.apibox.apidef.responseheader.dao.ResponseHeaderDao;
import com.darthcloud.apibox.apidef.responseheader.entity.ResponseHeaderPo;
import com.darthcloud.apibox.apidef.responseheader.model.ResponseHeader;
import com.darthcloud.apibox.apidef.responseheader.model.ResponseHeaderQuery;

import com.darthcloud.common.Pagination;
import com.darthcloud.beans.BeanMapper;
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

        return BeanMapper.map(responseHeaderPo, ResponseHeader.class);
    }

    @Override
    public List<ResponseHeader> findAllResponseHeader() {
        List<ResponseHeaderPo> responseHeaderPoList =  responseHeaderDao.findAllResponseHeader();

        return BeanMapper.mapList(responseHeaderPoList,ResponseHeader.class);
    }

    @Override
    public List<ResponseHeader> findResponseHeaderList(ResponseHeaderQuery responseHeaderQuery) {
        List<ResponseHeaderPo> responseHeaderPoList = responseHeaderDao.findResponseHeaderList(responseHeaderQuery);

        return BeanMapper.mapList(responseHeaderPoList,ResponseHeader.class);
    }

    @Override
    public Pagination<List<ResponseHeader>> findResponseHeaderPage(ResponseHeaderQuery responseHeaderQuery) {
        Pagination<List<ResponseHeader>> pg = new Pagination<>();

        Pagination<List<ResponseHeaderPo>>  pagination = responseHeaderDao.findResponseHeaderPage(responseHeaderQuery);
        BeanUtils.copyProperties(pagination,pg);

        List<ResponseHeader> responseHeaderList = BeanMapper.mapList(pagination.getDataList(),ResponseHeader.class);
        pg.setDataList(responseHeaderList);
        return pg;
    }
}