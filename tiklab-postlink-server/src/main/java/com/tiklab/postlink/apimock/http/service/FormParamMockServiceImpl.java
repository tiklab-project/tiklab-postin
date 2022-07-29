package com.tiklab.postlink.apimock.http.service;

import com.tiklab.postlink.apimock.http.dao.FormParamMockDao;
import com.tiklab.postlink.apimock.http.entity.FormParamMockEntity;
import com.tiklab.postlink.apimock.http.model.FormParamMock;
import com.tiklab.postlink.apimock.http.model.FormParamMockQuery;

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
public class FormParamMockServiceImpl implements FormParamMockService {

    @Autowired
    FormParamMockDao formParamMockDao;

    @Autowired
    JoinTemplate joinTemplate;

    @Override
    public String createFormParamMock(@NotNull @Valid FormParamMock formParamMock) {
        FormParamMockEntity formParamMockEntity = BeanMapper.map(formParamMock, FormParamMockEntity.class);

        return formParamMockDao.createFormParamMock(formParamMockEntity);
    }

    @Override
    public void updateFormParamMock(@NotNull @Valid FormParamMock formParamMock) {
        FormParamMockEntity formParamMockEntity = BeanMapper.map(formParamMock, FormParamMockEntity.class);

        formParamMockDao.updateFormParamMock(formParamMockEntity);
    }

    @Override
    public void deleteFormParamMock(@NotNull String id) {
        formParamMockDao.deleteFormParamMock(id);
    }

    @Override
    public FormParamMock findFormParamMock(@NotNull String id) {
        FormParamMockEntity formParamMockEntity = formParamMockDao.findFormParamMock(id);

        FormParamMock formParamMock = BeanMapper.map(formParamMockEntity, FormParamMock.class);

        joinTemplate.joinQuery(formParamMock);

        return formParamMock;
    }

    @Override
    public List<FormParamMock> findAllFormParamMock() {
        List<FormParamMockEntity> formParamMockEntityList =  formParamMockDao.findAllFormParamMock();

        List<FormParamMock> formParamMockList =  BeanMapper.mapList(formParamMockEntityList,FormParamMock.class);

        joinTemplate.joinQuery(formParamMockList);

        return formParamMockList;
    }

    @Override
    public List<FormParamMock> findFormParamMockList(FormParamMockQuery formParamMockQuery) {
        List<FormParamMockEntity> formParamMockEntityList = formParamMockDao.findFormParamMockList(formParamMockQuery);

        List<FormParamMock> formParamMockList = BeanMapper.mapList(formParamMockEntityList,FormParamMock.class);

        joinTemplate.joinQuery(formParamMockList);

        return formParamMockList;
    }

    @Override
    public Pagination<FormParamMock> findFormParamMockPage(FormParamMockQuery formParamMockQuery) {

        Pagination<FormParamMockEntity>  pagination = formParamMockDao.findFormParamMockPage(formParamMockQuery);

        List<FormParamMock> formParamMockList = BeanMapper.mapList(pagination.getDataList(),FormParamMock.class);

        joinTemplate.joinQuery(formParamMockList);

        return PaginationBuilder.build(pagination,formParamMockList);
    }
}