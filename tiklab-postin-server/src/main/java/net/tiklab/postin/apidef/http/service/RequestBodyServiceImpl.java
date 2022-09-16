package net.tiklab.postin.apidef.http.service;

import net.tiklab.postin.apidef.http.dao.RequestBodyDao;
import net.tiklab.postin.apidef.http.entity.RequestBodyEntity;
import net.tiklab.postin.apidef.http.model.RequestBodyEx;
import net.tiklab.postin.apidef.http.model.RequestBodyExQuery;

import net.tiklab.core.page.Pagination;
import net.tiklab.beans.BeanMapper;
import net.tiklab.core.page.PaginationBuilder;
import net.tiklab.join.JoinTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 用户服务业务处理
*/
@Service
public class RequestBodyServiceImpl implements RequestBodyService {
    private static Logger logger = LoggerFactory.getLogger(RequestBodyServiceImpl.class);
    @Autowired
    RequestBodyDao requestBodyDao;

    @Autowired
    JoinTemplate joinTemplate;

    @Override
    public String createRequestBody(@NotNull @Valid RequestBodyEx requestBody) {
        RequestBodyEntity requestBodyEntity = BeanMapper.map(requestBody, RequestBodyEntity.class);

        return requestBodyDao.createRequestBody(requestBodyEntity);
    }

    @Override
    public void updateRequestBody(@NotNull @Valid RequestBodyEx requestBody) {
        RequestBodyEntity requestBodyEntity = BeanMapper.map(requestBody, RequestBodyEntity.class);

        requestBodyDao.updateRequestBody(requestBodyEntity);
    }

    @Override
    public void deleteRequestBody(@NotNull String id) {
        requestBodyDao.deleteRequestBody(id);
    }

    @Override
    public RequestBodyEx findRequestBody(@NotNull String id) {
        RequestBodyEntity requestBodyEntity = requestBodyDao.findRequestBody(id);

        RequestBodyEx requestBody = BeanMapper.map(requestBodyEntity, RequestBodyEx.class);

        joinTemplate.joinQuery(requestBody);

        return requestBody;
    }

    @Override
    public List<RequestBodyEx> findAllRequestBody() {
        List<RequestBodyEntity> requestBodyEntityList =  requestBodyDao.findAllRequestBody();

        List<RequestBodyEx> requestBodyList =  BeanMapper.mapList(requestBodyEntityList, RequestBodyEx.class);

        joinTemplate.joinQuery(requestBodyList);

        return requestBodyList;
    }

    @Override
    public List<RequestBodyEx> findRequestBodyList(RequestBodyExQuery requestBodyQuery) {
        List<RequestBodyEntity> requestBodyEntityList = requestBodyDao.findRequestBodyList(requestBodyQuery);

        List<RequestBodyEx> requestBodyList = BeanMapper.mapList(requestBodyEntityList, RequestBodyEx.class);

        joinTemplate.joinQuery(requestBodyList);

        return requestBodyList;
    }

    @Override
    public Pagination<RequestBodyEx> findRequestBodyPage(RequestBodyExQuery requestBodyQuery) {

        Pagination<RequestBodyEntity>  pagination = requestBodyDao.findRequestBodyPage(requestBodyQuery);

        List<RequestBodyEx> requestBodyList = BeanMapper.mapList(pagination.getDataList(), RequestBodyEx.class);

        joinTemplate.joinQuery(requestBodyList);

        return PaginationBuilder.build(pagination,requestBodyList);
    }
}