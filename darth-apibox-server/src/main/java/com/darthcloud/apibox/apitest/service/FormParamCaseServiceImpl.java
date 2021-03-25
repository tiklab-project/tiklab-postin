package com.darthcloud.apibox.apitest.service;

import com.darthcloud.apibox.apitest.dao.FormParamCaseDao;
import com.darthcloud.apibox.apitest.entity.FormParamCasePo;
import com.darthcloud.apibox.apitest.model.FormParamCase;
import com.darthcloud.apibox.apitest.model.FormParamCaseQuery;

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
public class FormParamCaseServiceImpl implements FormParamCaseService {

    @Autowired
    FormParamCaseDao formParamCaseDao;

    @Autowired
    JoinQuery joinQuery;

    @Override
    public String createFormParamCase(@NotNull @Valid FormParamCase formParamCase) {
        FormParamCasePo formParamCasePo = BeanMapper.map(formParamCase, FormParamCasePo.class);

        return formParamCaseDao.createFormParamCase(formParamCasePo);
    }

    @Override
    public void updateFormParamCase(@NotNull @Valid FormParamCase formParamCase) {
        FormParamCasePo formParamCasePo = BeanMapper.map(formParamCase, FormParamCasePo.class);

        formParamCaseDao.updateFormParamCase(formParamCasePo);
    }

    @Override
    public void deleteFormParamCase(@NotNull String id) {
        formParamCaseDao.deleteFormParamCase(id);
    }

    @Override
    public FormParamCase findOne(String id) {
        FormParamCasePo formParamCasePo = formParamCaseDao.findFormParamCase(id);

        FormParamCase formParamCase = BeanMapper.map(formParamCasePo, FormParamCase.class);
        return formParamCase;
    }

    @Override
    public List<FormParamCase> findList(List<String> idList) {
        List<FormParamCasePo> formParamCasePoList =  formParamCaseDao.findFormParamCaseList(idList);

        List<FormParamCase> formParamCaseList =  BeanMapper.mapList(formParamCasePoList,FormParamCase.class);
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
        List<FormParamCasePo> formParamCasePoList =  formParamCaseDao.findAllFormParamCase();

        List<FormParamCase> formParamCaseList =  BeanMapper.mapList(formParamCasePoList,FormParamCase.class);

        joinQuery.queryList(formParamCaseList);
        return formParamCaseList;
    }

    @Override
    public List<FormParamCase> findFormParamCaseList(FormParamCaseQuery formParamCaseQuery) {
        List<FormParamCasePo> formParamCasePoList = formParamCaseDao.findFormParamCaseList(formParamCaseQuery);

        List<FormParamCase> formParamCaseList = BeanMapper.mapList(formParamCasePoList,FormParamCase.class);

        joinQuery.queryList(formParamCaseList);

        return formParamCaseList;
    }

    @Override
    public Pagination<FormParamCase> findFormParamCasePage(FormParamCaseQuery formParamCaseQuery) {
        Pagination<FormParamCase> pg = new Pagination<>();

        Pagination<FormParamCasePo>  pagination = formParamCaseDao.findFormParamCasePage(formParamCaseQuery);
        BeanUtils.copyProperties(pagination,pg);

        List<FormParamCase> formParamCaseList = BeanMapper.mapList(pagination.getDataList(),FormParamCase.class);

        joinQuery.queryList(formParamCaseList);

        pg.setDataList(formParamCaseList);
        return pg;
    }
}