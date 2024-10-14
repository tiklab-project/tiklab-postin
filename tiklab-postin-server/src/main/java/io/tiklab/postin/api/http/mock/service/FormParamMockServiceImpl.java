package io.tiklab.postin.api.http.mock.service;

import io.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.DeleteBuilders;
import io.tiklab.postin.api.http.mock.dao.FormParamMockDao;
import io.tiklab.postin.api.http.mock.entity.FormParamMockEntity;
import io.tiklab.postin.api.http.mock.model.FormParamMock;
import io.tiklab.postin.api.http.mock.model.FormParamMockQuery;

import io.tiklab.core.page.Pagination;
import io.tiklab.toolkit.beans.BeanMapper;
import io.tiklab.core.page.PaginationBuilder;
import io.tiklab.toolkit.join.JoinTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* form-data 服务
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
    public void deleteAllFormParamMock(String mockId) {
        DeleteCondition deleteCondition = DeleteBuilders.createDelete(FormParamMockEntity.class)
                .eq("mockId", mockId)
                .get();
        formParamMockDao.deleteFormParamMockList(deleteCondition);
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