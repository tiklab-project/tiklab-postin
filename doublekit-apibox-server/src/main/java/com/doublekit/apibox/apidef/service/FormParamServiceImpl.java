package com.doublekit.apibox.apidef.service;

import com.doublekit.apibox.apidef.dao.FormParamDao;
import com.doublekit.apibox.apidef.entity.FormParamEntity;
import com.doublekit.apibox.apidef.model.FormParam;
import com.doublekit.apibox.apidef.model.FormParamQuery;

import com.doublekit.common.page.Pagination;
import com.doublekit.beans.BeanMapper;
import com.doublekit.common.page.PaginationBuilder;
import com.doublekit.join.JoinTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 用户服务业务处理
*/
@Service
public class FormParamServiceImpl implements FormParamService {

    @Autowired
    FormParamDao formParamDao;

    @Autowired
    JoinTemplate joinTemplate;

    @Override
    public String createFormParam(@NotNull @Valid FormParam formParam) {
        FormParamEntity formParamEntity = BeanMapper.map(formParam, FormParamEntity.class);

        return formParamDao.createFormParam(formParamEntity);
    }

    @Override
    public void updateFormParam(@NotNull @Valid FormParam formParam) {
        FormParamEntity formParamEntity = BeanMapper.map(formParam, FormParamEntity.class);

        formParamDao.updateFormParam(formParamEntity);
    }

    @Override
    public void deleteFormParam(@NotNull String id) {
        formParamDao.deleteFormParam(id);
    }

    @Override
    public FormParam findFormParam(@NotNull String id) {
        FormParamEntity formParamEntity = formParamDao.findFormParam(id);

        FormParam formParam = BeanMapper.map(formParamEntity, FormParam.class);

        joinTemplate.joinQuery(formParam);

        return formParam;
    }

    @Override
    public List<FormParam> findAllFormParam() {
        List<FormParamEntity> formParamEntityList =  formParamDao.findAllFormParam();

        List<FormParam> formParamList = BeanMapper.mapList(formParamEntityList,FormParam.class);

        joinTemplate.joinQuery(formParamList);

        return formParamList;
    }

    @Override
    public List<FormParam> findFormParamList(FormParamQuery formParamQuery) {
        List<FormParamEntity> formParamEntityList = formParamDao.findFormParamList(formParamQuery);

        List<FormParam> formParamList = BeanMapper.mapList(formParamEntityList,FormParam.class);

        joinTemplate.joinQuery(formParamList);

        return formParamList;
    }

    @Override
    public Pagination<FormParam> findFormParamPage(FormParamQuery formParamQuery) {

        Pagination<FormParamEntity>  pagination = formParamDao.findFormParamPage(formParamQuery);

        List<FormParam> formParamList = BeanMapper.mapList(pagination.getDataList(),FormParam.class);

        joinTemplate.joinQuery(formParamList);

        return PaginationBuilder.build(pagination,formParamList);
    }
}