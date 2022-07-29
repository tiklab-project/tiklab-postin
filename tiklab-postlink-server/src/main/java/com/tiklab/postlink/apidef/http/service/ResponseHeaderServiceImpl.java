package com.tiklab.postlink.apidef.http.service;

import com.tiklab.postlink.apidef.http.dao.ResponseHeaderDao;
import com.tiklab.postlink.apidef.http.entity.ResponseHeaderEntity;
import com.tiklab.postlink.apidef.http.model.ResponseHeader;
import com.tiklab.postlink.apidef.http.model.ResponseHeaderQuery;

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
public class ResponseHeaderServiceImpl implements ResponseHeaderService {

    @Autowired
    ResponseHeaderDao responseHeaderDao;

    @Autowired
    JoinTemplate joinTemplate;

    @Override
    public String createResponseHeader(@NotNull @Valid ResponseHeader responseHeader) {
        ResponseHeaderEntity responseHeaderEntity = BeanMapper.map(responseHeader, ResponseHeaderEntity.class);

        return responseHeaderDao.createResponseHeader(responseHeaderEntity);
    }

    @Override
    public void updateResponseHeader(@NotNull @Valid ResponseHeader responseHeader) {
        ResponseHeaderEntity responseHeaderEntity = BeanMapper.map(responseHeader, ResponseHeaderEntity.class);

        responseHeaderDao.updateResponseHeader(responseHeaderEntity);
    }

    @Override
    public void deleteResponseHeader(@NotNull String id) {
        responseHeaderDao.deleteResponseHeader(id);
    }

    @Override
    public ResponseHeader findResponseHeader(@NotNull String id) {
        ResponseHeaderEntity responseHeaderEntity = responseHeaderDao.findResponseHeader(id);

        ResponseHeader responseHeader = BeanMapper.map(responseHeaderEntity, ResponseHeader.class);

        joinTemplate.joinQuery(responseHeader);

        return responseHeader;
    }

    @Override
    public List<ResponseHeader> findAllResponseHeader() {
        List<ResponseHeaderEntity> responseHeaderEntityList =  responseHeaderDao.findAllResponseHeader();

        List<ResponseHeader> responseHeaderList = BeanMapper.mapList(responseHeaderEntityList,ResponseHeader.class);

        joinTemplate.joinQuery(responseHeaderList);

        return responseHeaderList;
    }

    @Override
    public List<ResponseHeader> findResponseHeaderList(ResponseHeaderQuery responseHeaderQuery) {
        List<ResponseHeaderEntity> responseHeaderEntityList = responseHeaderDao.findResponseHeaderList(responseHeaderQuery);

        List<ResponseHeader> responseHeaderList = BeanMapper.mapList(responseHeaderEntityList,ResponseHeader.class);

        joinTemplate.joinQuery(responseHeaderList);

        return responseHeaderList;
    }

    @Override
    public Pagination<ResponseHeader> findResponseHeaderPage(ResponseHeaderQuery responseHeaderQuery) {

        Pagination<ResponseHeaderEntity>  pagination = responseHeaderDao.findResponseHeaderPage(responseHeaderQuery);

        List<ResponseHeader> responseHeaderList = BeanMapper.mapList(pagination.getDataList(),ResponseHeader.class);

        joinTemplate.joinQuery(responseHeaderList);

        return PaginationBuilder.build(pagination,responseHeaderList);
    }
}