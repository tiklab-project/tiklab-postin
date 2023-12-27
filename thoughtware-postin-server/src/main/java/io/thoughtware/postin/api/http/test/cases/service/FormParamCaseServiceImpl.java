package io.thoughtware.postin.api.http.test.cases.service;

import io.thoughtware.postin.api.http.test.cases.dao.FormParamCaseDao;
import io.thoughtware.postin.api.http.test.cases.entity.FormParamCaseEntity;
import io.thoughtware.postin.api.http.test.cases.model.FormParamCase;
import io.thoughtware.postin.api.http.test.cases.model.FormParamCaseQuery;

import io.thoughtware.core.page.Pagination;
import io.thoughtware.toolkit.beans.BeanMapper;
import io.thoughtware.core.page.PaginationBuilder;
import io.thoughtware.toolkit.join.JoinTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 用户服务业务处理
*/
@Service
public class FormParamCaseServiceImpl implements FormParamCaseService {

    @Autowired
    FormParamCaseDao formParamCaseDao;

    @Autowired
    JoinTemplate joinTemplate;

    @Override
    public String createFormParamCase(@NotNull @Valid FormParamCase formParamCase) {
        FormParamCaseEntity formParamCaseEntity = BeanMapper.map(formParamCase, FormParamCaseEntity.class);

        return formParamCaseDao.createFormParamCase(formParamCaseEntity);
    }

    @Override
    public void updateFormParamCase(@NotNull @Valid FormParamCase formParamCase) {
        FormParamCaseEntity formParamCaseEntity = BeanMapper.map(formParamCase, FormParamCaseEntity.class);

        formParamCaseDao.updateFormParamCase(formParamCaseEntity);
    }

    @Override
    public void deleteFormParamCase(@NotNull String id) {
        formParamCaseDao.deleteFormParamCase(id);
    }

    @Override
    public FormParamCase findOne(String id) {
        FormParamCaseEntity formParamCaseEntity = formParamCaseDao.findFormParamCase(id);

        FormParamCase formParamCase = BeanMapper.map(formParamCaseEntity, FormParamCase.class);
        return formParamCase;
    }

    @Override
    public List<FormParamCase> findList(List<String> idList) {
        List<FormParamCaseEntity> formParamCaseEntityList =  formParamCaseDao.findFormParamCaseList(idList);

        List<FormParamCase> formParamCaseList =  BeanMapper.mapList(formParamCaseEntityList,FormParamCase.class);
        return formParamCaseList;
    }

    @Override
    public FormParamCase findFormParamCase(@NotNull String id) {
        FormParamCase formParamCase = findOne(id);

        joinTemplate.joinQuery(formParamCase);
        return formParamCase;
    }

    @Override
    public List<FormParamCase> findAllFormParamCase() {
        List<FormParamCaseEntity> formParamCaseEntityList =  formParamCaseDao.findAllFormParamCase();

        List<FormParamCase> formParamCaseList =  BeanMapper.mapList(formParamCaseEntityList,FormParamCase.class);

        joinTemplate.joinQuery(formParamCaseList);
        return formParamCaseList;
    }

    @Override
    public List<FormParamCase> findFormParamCaseList(FormParamCaseQuery formParamCaseQuery) {
        List<FormParamCaseEntity> formParamCaseEntityList = formParamCaseDao.findFormParamCaseList(formParamCaseQuery);

        List<FormParamCase> formParamCaseList = BeanMapper.mapList(formParamCaseEntityList,FormParamCase.class);

        joinTemplate.joinQuery(formParamCaseList);

        return formParamCaseList;
    }

    @Override
    public Pagination<FormParamCase> findFormParamCasePage(FormParamCaseQuery formParamCaseQuery) {

        Pagination<FormParamCaseEntity>  pagination = formParamCaseDao.findFormParamCasePage(formParamCaseQuery);

        List<FormParamCase> formParamCaseList = BeanMapper.mapList(pagination.getDataList(),FormParamCase.class);

        joinTemplate.joinQuery(formParamCaseList);

        return PaginationBuilder.build(pagination,formParamCaseList);
    }
}