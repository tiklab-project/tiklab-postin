package com.darthcloud.apibox.apidef.formparam.service;

import com.darthcloud.apibox.apidef.formparam.dao.FormParamDao;
import com.darthcloud.apibox.apidef.formparam.entity.FormParamPo;
import com.darthcloud.apibox.apidef.formparam.model.FormParam;
import com.darthcloud.apibox.apidef.formparam.model.FormParamQuery;

import com.darthcloud.common.Pagination;
import com.darthcloud.beans.BeanMapper;
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

        return BeanMapper.map(formParamPo, FormParam.class);
    }

    @Override
    public List<FormParam> findAllFormParam() {
        List<FormParamPo> formParamPoList =  formParamDao.findAllFormParam();

        return BeanMapper.mapList(formParamPoList,FormParam.class);
    }

    @Override
    public List<FormParam> findFormParamList(FormParamQuery formParamQuery) {
        List<FormParamPo> formParamPoList = formParamDao.findFormParamList(formParamQuery);

        return BeanMapper.mapList(formParamPoList,FormParam.class);
    }

    @Override
    public Pagination<List<FormParam>> findFormParamPage(FormParamQuery formParamQuery) {
        Pagination<List<FormParam>> pg = new Pagination<>();

        Pagination<List<FormParamPo>>  pagination = formParamDao.findFormParamPage(formParamQuery);
        BeanUtils.copyProperties(pagination,pg);

        List<FormParam> formParamList = BeanMapper.mapList(pagination.getDataList(),FormParam.class);
        pg.setDataList(formParamList);
        return pg;
    }
}