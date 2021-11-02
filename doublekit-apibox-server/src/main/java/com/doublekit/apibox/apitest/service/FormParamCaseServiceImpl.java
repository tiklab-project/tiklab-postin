package com.doublekit.apibox.apitest.service;

import com.doublekit.apibox.apitest.dao.FormParamCaseDao;
import com.doublekit.apibox.apitest.entity.FormParamCaseEntity;
import com.doublekit.apibox.apitest.model.FormParamCase;
import com.doublekit.apibox.apitest.model.FormParamCaseQuery;

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
public class FormParamCaseServiceImpl implements FormParamCaseService {

    @Autowired
    FormParamCaseDao formParamCaseDao;

    @Autowired
    JoinTemplate joinQuery;

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

        joinQuery.queryOne(formParamCase);
        return formParamCase;
    }

    @Override
    public List<FormParamCase> findAllFormParamCase() {
        List<FormParamCaseEntity> formParamCaseEntityList =  formParamCaseDao.findAllFormParamCase();

        List<FormParamCase> formParamCaseList =  BeanMapper.mapList(formParamCaseEntityList,FormParamCase.class);

        joinQuery.queryList(formParamCaseList);
        return formParamCaseList;
    }

    @Override
    public List<FormParamCase> findFormParamCaseList(FormParamCaseQuery formParamCaseQuery) {
        List<FormParamCaseEntity> formParamCaseEntityList = formParamCaseDao.findFormParamCaseList(formParamCaseQuery);

        List<FormParamCase> formParamCaseList = BeanMapper.mapList(formParamCaseEntityList,FormParamCase.class);

        joinQuery.queryList(formParamCaseList);

        return formParamCaseList;
    }

    @Override
    public Pagination<FormParamCase> findFormParamCasePage(FormParamCaseQuery formParamCaseQuery) {
        Pagination<FormParamCase> pg = new Pagination<>();

        Pagination<FormParamCaseEntity>  pagination = formParamCaseDao.findFormParamCasePage(formParamCaseQuery);
        BeanUtils.copyProperties(pagination,pg);

        List<FormParamCase> formParamCaseList = BeanMapper.mapList(pagination.getDataList(),FormParamCase.class);

        joinQuery.queryList(formParamCaseList);

        pg.setDataList(formParamCaseList);
        return pg;
    }
}