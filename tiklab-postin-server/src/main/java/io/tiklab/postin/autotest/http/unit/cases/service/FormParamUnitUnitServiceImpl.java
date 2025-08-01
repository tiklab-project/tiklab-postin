package io.tiklab.postin.autotest.http.unit.cases.service;

import io.tiklab.core.page.Pagination;
import io.tiklab.core.page.PaginationBuilder;
import io.tiklab.postin.autotest.http.unit.cases.dao.FormParamUnitDao;
import io.tiklab.postin.autotest.http.unit.cases.entity.FormParamsUnitEntity;
import io.tiklab.postin.autotest.http.unit.cases.model.FormParamUnit;
import io.tiklab.postin.autotest.http.unit.cases.model.FormParamUnitQuery;
import io.tiklab.toolkit.beans.BeanMapper;
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
public class FormParamUnitUnitServiceImpl implements FormParamUnitService {

    @Autowired
    FormParamUnitDao formParamUnitDao;

    @Autowired
    JoinTemplate joinTemplate;

    @Override
    public String createFormParam(@NotNull @Valid FormParamUnit formParamUnit) {
        FormParamsUnitEntity formParamsUnitEntity = BeanMapper.map(formParamUnit, FormParamsUnitEntity.class);

        return formParamUnitDao.createFormParam(formParamsUnitEntity);
    }

    @Override
    public void updateFormParam(@NotNull @Valid FormParamUnit formParamUnit) {
        FormParamsUnitEntity formParamsUnitEntity = BeanMapper.map(formParamUnit, FormParamsUnitEntity.class);

        formParamUnitDao.updateFormParam(formParamsUnitEntity);
    }

    @Override
    public void deleteFormParam(@NotNull String id) {
        formParamUnitDao.deleteFormParam(id);
    }

    @Override
    public void deleteAllFormParam( String caseId) {
        FormParamUnitQuery formParamUnitQuery = new FormParamUnitQuery();
        formParamUnitQuery.setApiUnitId(caseId);
        List<FormParamUnit> formParamList = findFormParamList(formParamUnitQuery);
        for (FormParamUnit formParamUnit : formParamList) {
            formParamUnitDao.deleteFormParam(formParamUnit.getId());
        }

    }

    @Override
    public FormParamUnit findOne(String id) {
        FormParamsUnitEntity formParamsUnitEntity = formParamUnitDao.findFormParam(id);

        FormParamUnit formParamUnit = BeanMapper.map(formParamsUnitEntity, FormParamUnit.class);
        return formParamUnit;
    }

    @Override
    public List<FormParamUnit> findList(List<String> idList) {
        List<FormParamsUnitEntity> formParamsUnitEntityList =  formParamUnitDao.findFormParamList(idList);

        List<FormParamUnit> formParamUnitList =  BeanMapper.mapList(formParamsUnitEntityList, FormParamUnit.class);
        return formParamUnitList;
    }

    @Override
    public FormParamUnit findFormParam(@NotNull String id) {
        FormParamUnit formParamUnit = findOne(id);

        joinTemplate.joinQuery(formParamUnit);
        return formParamUnit;
    }

    @Override
    public List<FormParamUnit> findAllFormParam() {
        List<FormParamsUnitEntity> formParamsUnitEntityList =  formParamUnitDao.findAllFormParam();

        List<FormParamUnit> formParamUnitList =  BeanMapper.mapList(formParamsUnitEntityList, FormParamUnit.class);

        joinTemplate.joinQuery(formParamUnitList);
        return formParamUnitList;
    }

    @Override
    public List<FormParamUnit> findFormParamList(FormParamUnitQuery formParamUnitQuery) {
        List<FormParamsUnitEntity> formParamsUnitEntityList = formParamUnitDao.findFormParamList(formParamUnitQuery);

        List<FormParamUnit> formParamUnitList = BeanMapper.mapList(formParamsUnitEntityList, FormParamUnit.class);

        joinTemplate.joinQuery(formParamUnitList);

        return formParamUnitList;
    }

    @Override
    public Pagination<FormParamUnit> findFormParamPage(FormParamUnitQuery formParamUnitQuery) {

        Pagination<FormParamsUnitEntity>  pagination = formParamUnitDao.findFormParamPage(formParamUnitQuery);

        List<FormParamUnit> formParamUnitList = BeanMapper.mapList(pagination.getDataList(), FormParamUnit.class);

        joinTemplate.joinQuery(formParamUnitList);

        return PaginationBuilder.build(pagination, formParamUnitList);
    }
}