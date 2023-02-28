package net.tiklab.postin.api.http.definition.service;

import net.tiklab.postin.api.http.definition.dao.ResponseHeaderDao;
import net.tiklab.postin.api.http.definition.entity.ResponseHeaderEntity;
import net.tiklab.postin.api.http.definition.model.ResponseHeader;
import net.tiklab.postin.api.http.definition.model.ResponseHeaderQuery;

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
 * 定义
 * http协议
 * 响应头服务
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