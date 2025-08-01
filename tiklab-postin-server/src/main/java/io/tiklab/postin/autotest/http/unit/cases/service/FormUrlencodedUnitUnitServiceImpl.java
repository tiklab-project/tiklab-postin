package io.tiklab.postin.autotest.http.unit.cases.service;



import io.tiklab.core.page.Pagination;
import io.tiklab.core.page.PaginationBuilder;
import io.tiklab.postin.autotest.http.unit.cases.dao.FormUrlencodedUnitDao;
import io.tiklab.postin.autotest.http.unit.cases.entity.FormUrlEncodedUnitEntity;
import io.tiklab.postin.autotest.http.unit.cases.model.FormUrlEncodedUnit;
import io.tiklab.postin.autotest.http.unit.cases.model.FormUrlencodedUnitQuery;
import io.tiklab.toolkit.beans.BeanMapper;
import io.tiklab.toolkit.join.JoinTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* form-urlencoded 服务
*/
@Service
public class FormUrlencodedUnitUnitServiceImpl implements FormUrlencodedUnitService {

    @Autowired
    FormUrlencodedUnitDao formUrlencodedUnitDao;

    @Autowired
    JoinTemplate joinTemplate;

    @Override
    public String createFormUrlencoded(@NotNull @Valid FormUrlEncodedUnit formUrlencodedUnit) {
        FormUrlEncodedUnitEntity formUrlencodedUnitEntity = BeanMapper.map(formUrlencodedUnit, FormUrlEncodedUnitEntity.class);

        return formUrlencodedUnitDao.createFormUrlencoded(formUrlencodedUnitEntity);
    }

    @Override
    public void updateFormUrlencoded(@NotNull @Valid FormUrlEncodedUnit formUrlencodedUnit) {
        FormUrlEncodedUnitEntity formUrlencodedUnitEntity = BeanMapper.map(formUrlencodedUnit, FormUrlEncodedUnitEntity.class);

        formUrlencodedUnitDao.updateFormUrlencoded(formUrlencodedUnitEntity);
    }

    @Override
    public void deleteFormUrlencoded(@NotNull String id) {
        formUrlencodedUnitDao.deleteFormUrlencoded(id);
    }

    @Override
    public void deleteAllFormUrlencoded( String caseId) {
        FormUrlencodedUnitQuery formUrlencodedUnitQuery = new FormUrlencodedUnitQuery();
        formUrlencodedUnitQuery.setApiUnitId(caseId);
        List<FormUrlEncodedUnit> formUrlencodedList = findFormUrlencodedList(formUrlencodedUnitQuery);
        for(FormUrlEncodedUnit formUrlencodedUnit : formUrlencodedList){
            formUrlencodedUnitDao.deleteFormUrlencoded(formUrlencodedUnit.getId());
        }
    }

    @Override
    public FormUrlEncodedUnit findOne(String id) {
        FormUrlEncodedUnitEntity formUrlencodedUnitEntity = formUrlencodedUnitDao.findFormUrlencoded(id);

        FormUrlEncodedUnit formUrlencodedUnit = BeanMapper.map(formUrlencodedUnitEntity, FormUrlEncodedUnit.class);
        return formUrlencodedUnit;
    }

    @Override
    public List<FormUrlEncodedUnit> findList(List<String> idList) {
        List<FormUrlEncodedUnitEntity> formUrlEncodedUnitEntityList =  formUrlencodedUnitDao.findFormUrlencodedList(idList);

        List<FormUrlEncodedUnit> formUrlEncodedUnitList =  BeanMapper.mapList(formUrlEncodedUnitEntityList, FormUrlEncodedUnit.class);
        return formUrlEncodedUnitList;
    }

    @Override
    public FormUrlEncodedUnit findFormUrlencoded(@NotNull String id) {
        FormUrlEncodedUnit formUrlencodedUnit = findOne(id);

        joinTemplate.joinQuery(formUrlencodedUnit);

        return formUrlencodedUnit;
    }

    @Override
    public List<FormUrlEncodedUnit> findAllFormUrlencoded() {
        List<FormUrlEncodedUnitEntity> formUrlEncodedUnitEntityList =  formUrlencodedUnitDao.findAllFormUrlencoded();

        List<FormUrlEncodedUnit> formUrlEncodedUnitList =  BeanMapper.mapList(formUrlEncodedUnitEntityList, FormUrlEncodedUnit.class);

        joinTemplate.joinQuery(formUrlEncodedUnitList);

        return formUrlEncodedUnitList;
    }

    @Override
    public List<FormUrlEncodedUnit> findFormUrlencodedList(FormUrlencodedUnitQuery formUrlencodedUnitQuery) {
        List<FormUrlEncodedUnitEntity> formUrlEncodedUnitEntityList = formUrlencodedUnitDao.findFormUrlencodedList(formUrlencodedUnitQuery);

        List<FormUrlEncodedUnit> formUrlEncodedUnitList = BeanMapper.mapList(formUrlEncodedUnitEntityList, FormUrlEncodedUnit.class);

        joinTemplate.joinQuery(formUrlEncodedUnitList);

        return formUrlEncodedUnitList;
    }

    @Override
    public Pagination<FormUrlEncodedUnit> findFormUrlencodedPage(FormUrlencodedUnitQuery formUrlencodedUnitQuery) {
        Pagination<FormUrlEncodedUnitEntity>  pagination = formUrlencodedUnitDao.findFormUrlencodedPage(formUrlencodedUnitQuery);

        List<FormUrlEncodedUnit> formUrlEncodedUnitList = BeanMapper.mapList(pagination.getDataList(), FormUrlEncodedUnit.class);

        joinTemplate.joinQuery(formUrlEncodedUnitList);

        return PaginationBuilder.build(pagination, formUrlEncodedUnitList);
    }
}