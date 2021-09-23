package com.doublekit.apibox.apidef.service;

import com.doublekit.apibox.apidef.dao.FormParamDao;
import com.doublekit.apibox.apidef.entity.FormParamPo;
import com.doublekit.apibox.apidef.model.FormParam;
import com.doublekit.apibox.apidef.model.FormParamQuery;

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
public class FormParamServiceImpl implements FormParamService {

    @Autowired
    FormParamDao formParamDao;

    @Autowired
    JoinTemplate joinQuery;

    @Override
    public String createFormParam(@NotNull @Valid FormParam formParam) {
        FormParamPo formParamPo = BeanMapper.map(formParam, FormParamPo.class);

        return formParamDao.createFormParam(formParamPo);
    }

    @Override
    public void updateFormParam(@NotNull @Valid FormParam formParam) {
        FormParamPo formParamPo = BeanMapper.map(formParam, FormParamPo.class);

        formParamDao.updateFormParam(formParamPo);
    }

    @Override
    public void deleteFormParam(@NotNull String id) {
        formParamDao.deleteFormParam(id);
    }

    @Override
    public FormParam findFormParam(@NotNull String id) {
        FormParamPo formParamPo = formParamDao.findFormParam(id);

        FormParam formParam = BeanMapper.map(formParamPo, FormParam.class);

        joinQuery.queryOne(formParam);

        return formParam;
    }

    @Override
    public List<FormParam> findAllFormParam() {
        List<FormParamPo> formParamPoList =  formParamDao.findAllFormParam();

        List<FormParam> formParamList = BeanMapper.mapList(formParamPoList,FormParam.class);

        joinQuery.queryList(formParamList);

        return formParamList;
    }

    @Override
    public List<FormParam> findFormParamList(FormParamQuery formParamQuery) {
        List<FormParamPo> formParamPoList = formParamDao.findFormParamList(formParamQuery);

        List<FormParam> formParamList = BeanMapper.mapList(formParamPoList,FormParam.class);

        joinQuery.queryList(formParamList);

        return formParamList;
    }

    @Override
    public Pagination<FormParam> findFormParamPage(FormParamQuery formParamQuery) {
        Pagination<FormParam> pg = new Pagination<>();

        Pagination<FormParamPo>  pagination = formParamDao.findFormParamPage(formParamQuery);
        BeanUtils.copyProperties(pagination,pg);

        List<FormParam> formParamList = BeanMapper.mapList(pagination.getDataList(),FormParam.class);

        joinQuery.queryList(formParamList);

        pg.setDataList(formParamList);
        return pg;
    }
}