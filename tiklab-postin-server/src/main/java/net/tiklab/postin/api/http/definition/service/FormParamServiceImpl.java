package net.tiklab.postin.api.http.definition.service;

import net.tiklab.postin.api.http.definition.dao.FormParamDao;
import net.tiklab.postin.api.http.definition.entity.FormParamEntity;
import net.tiklab.postin.api.http.definition.model.FormParam;
import net.tiklab.postin.api.http.definition.model.FormParamQuery;

import net.tiklab.core.page.Pagination;
import net.tiklab.beans.BeanMapper;
import net.tiklab.core.page.PaginationBuilder;
import net.tiklab.join.JoinTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 定义
 * http协议
 * form-data服务
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