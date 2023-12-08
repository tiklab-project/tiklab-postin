package io.thoughtware.postin.api.http.test.cases.service;

import io.thoughtware.postin.api.http.test.cases.dao.FormUrlencodedCaseDao;
import io.thoughtware.postin.api.http.test.cases.entity.FormUrlencodedCaseEntity;
import io.thoughtware.postin.api.http.test.cases.model.FormUrlencodedCase;
import io.thoughtware.postin.api.http.test.cases.model.FormUrlencodedCaseQuery;

import io.thoughtware.core.page.Pagination;
import io.thoughtware.core.page.PaginationBuilder;
import io.thoughtware.beans.BeanMapper;
import io.thoughtware.join.JoinTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* FormUrlencodedCaseServiceImpl
*/
@Service
public class FormUrlencodedCaseServiceImpl implements FormUrlencodedCaseService {

    @Autowired
    FormUrlencodedCaseDao formUrlencodedCaseDao;

    @Autowired
    JoinTemplate joinTemplate;

    @Override
    public String createFormUrlencodedCase(@NotNull @Valid FormUrlencodedCase formUrlencodedCase) {
        FormUrlencodedCaseEntity formUrlencodedCaseEntity = BeanMapper.map(formUrlencodedCase, FormUrlencodedCaseEntity.class);

        return formUrlencodedCaseDao.createFormUrlencodedCase(formUrlencodedCaseEntity);
    }

    @Override
    public void updateFormUrlencodedCase(@NotNull @Valid FormUrlencodedCase formUrlencodedCase) {
        FormUrlencodedCaseEntity formUrlencodedCaseEntity = BeanMapper.map(formUrlencodedCase, FormUrlencodedCaseEntity.class);

        formUrlencodedCaseDao.updateFormUrlencodedCase(formUrlencodedCaseEntity);
    }

    @Override
    public void deleteFormUrlencodedCase(@NotNull String id) {
        formUrlencodedCaseDao.deleteFormUrlencodedCase(id);
    }

    @Override
    public FormUrlencodedCase findOne(String id) {
        FormUrlencodedCaseEntity formUrlencodedCaseEntity = formUrlencodedCaseDao.findFormUrlencodedCase(id);

        FormUrlencodedCase formUrlencodedCase = BeanMapper.map(formUrlencodedCaseEntity, FormUrlencodedCase.class);
        return formUrlencodedCase;
    }

    @Override
    public List<FormUrlencodedCase> findList(List<String> idList) {
        List<FormUrlencodedCaseEntity> formUrlencodedCaseEntityList =  formUrlencodedCaseDao.findFormUrlencodedCaseList(idList);

        List<FormUrlencodedCase> formUrlencodedCaseList =  BeanMapper.mapList(formUrlencodedCaseEntityList,FormUrlencodedCase.class);
        return formUrlencodedCaseList;
    }

    @Override
    public FormUrlencodedCase findFormUrlencodedCase(@NotNull String id) {
        FormUrlencodedCase formUrlencodedCase = findOne(id);

        joinTemplate.joinQuery(formUrlencodedCase);

        return formUrlencodedCase;
    }

    @Override
    public List<FormUrlencodedCase> findAllFormUrlencodedCase() {
        List<FormUrlencodedCaseEntity> formUrlencodedCaseEntityList =  formUrlencodedCaseDao.findAllFormUrlencodedCase();

        List<FormUrlencodedCase> formUrlencodedCaseList =  BeanMapper.mapList(formUrlencodedCaseEntityList,FormUrlencodedCase.class);

        joinTemplate.joinQuery(formUrlencodedCaseList);

        return formUrlencodedCaseList;
    }

    @Override
    public List<FormUrlencodedCase> findFormUrlencodedCaseList(FormUrlencodedCaseQuery formUrlencodedCaseQuery) {
        List<FormUrlencodedCaseEntity> formUrlencodedCaseEntityList = formUrlencodedCaseDao.findFormUrlencodedCaseList(formUrlencodedCaseQuery);

        List<FormUrlencodedCase> formUrlencodedCaseList = BeanMapper.mapList(formUrlencodedCaseEntityList,FormUrlencodedCase.class);

        joinTemplate.joinQuery(formUrlencodedCaseList);

        return formUrlencodedCaseList;
    }

    @Override
    public Pagination<FormUrlencodedCase> findFormUrlencodedCasePage(FormUrlencodedCaseQuery formUrlencodedCaseQuery) {
        Pagination<FormUrlencodedCaseEntity>  pagination = formUrlencodedCaseDao.findFormUrlencodedCasePage(formUrlencodedCaseQuery);

        List<FormUrlencodedCase> formUrlencodedCaseList = BeanMapper.mapList(pagination.getDataList(),FormUrlencodedCase.class);

        joinTemplate.joinQuery(formUrlencodedCaseList);

        return PaginationBuilder.build(pagination,formUrlencodedCaseList);
    }
}