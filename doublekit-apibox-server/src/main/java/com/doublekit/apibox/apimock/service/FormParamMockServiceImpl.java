package com.doublekit.apibox.apimock.service;

import com.doublekit.apibox.apimock.dao.FormParamMockDao;
import com.doublekit.apibox.apimock.entity.FormParamMockPo;
import com.doublekit.apibox.apimock.model.FormParamMock;
import com.doublekit.apibox.apimock.model.FormParamMockQuery;

import com.doublekit.common.Pagination;
import com.doublekit.beans.BeanMapper;
import com.doublekit.join.join.JoinQuery;
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
public class FormParamMockServiceImpl implements FormParamMockService {

    @Autowired
    FormParamMockDao formParamMockDao;

    @Autowired
    JoinQuery joinQuery;

    @Override
    public String createFormParamMock(@NotNull @Valid FormParamMock formParamMock) {
        FormParamMockPo formParamMockPo = BeanMapper.map(formParamMock, FormParamMockPo.class);

        return formParamMockDao.createFormParamMock(formParamMockPo);
    }

    @Override
    public void updateFormParamMock(@NotNull @Valid FormParamMock formParamMock) {
        FormParamMockPo formParamMockPo = BeanMapper.map(formParamMock, FormParamMockPo.class);

        formParamMockDao.updateFormParamMock(formParamMockPo);
    }

    @Override
    public void deleteFormParamMock(@NotNull String id) {
        formParamMockDao.deleteFormParamMock(id);
    }

    @Override
    public FormParamMock findFormParamMock(@NotNull String id) {
        FormParamMockPo formParamMockPo = formParamMockDao.findFormParamMock(id);

        FormParamMock formParamMock = BeanMapper.map(formParamMockPo, FormParamMock.class);

        joinQuery.queryOne(formParamMock);

        return formParamMock;
    }

    @Override
    public List<FormParamMock> findAllFormParamMock() {
        List<FormParamMockPo> formParamMockPoList =  formParamMockDao.findAllFormParamMock();

        List<FormParamMock> formParamMockList =  BeanMapper.mapList(formParamMockPoList,FormParamMock.class);

        joinQuery.queryList(formParamMockList);

        return formParamMockList;
    }

    @Override
    public List<FormParamMock> findFormParamMockList(FormParamMockQuery formParamMockQuery) {
        List<FormParamMockPo> formParamMockPoList = formParamMockDao.findFormParamMockList(formParamMockQuery);

        List<FormParamMock> formParamMockList = BeanMapper.mapList(formParamMockPoList,FormParamMock.class);

        joinQuery.queryList(formParamMockList);

        return formParamMockList;
    }

    @Override
    public Pagination<FormParamMock> findFormParamMockPage(FormParamMockQuery formParamMockQuery) {
        Pagination<FormParamMock> pg = new Pagination<>();

        Pagination<FormParamMockPo>  pagination = formParamMockDao.findFormParamMockPage(formParamMockQuery);
        BeanUtils.copyProperties(pagination,pg);

        List<FormParamMock> formParamMockList = BeanMapper.mapList(pagination.getDataList(),FormParamMock.class);

        joinQuery.queryList(formParamMockList);

        pg.setDataList(formParamMockList);
        return pg;
    }
}