package io.tiklab.postin.autotest.http.unit.cases.service;

import io.tiklab.core.page.Pagination;
import io.tiklab.core.page.PaginationBuilder;
import io.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.DeleteBuilders;
import io.tiklab.postin.autotest.http.unit.cases.dao.AuthBearerUnitDao;
import io.tiklab.postin.autotest.http.unit.cases.entity.AuthBearerUnitEntity;
import io.tiklab.postin.autotest.http.unit.cases.model.AuthBearerUnit;
import io.tiklab.postin.autotest.http.unit.cases.model.AuthBearerUnitQuery;
import io.tiklab.toolkit.beans.BeanMapper;
import io.tiklab.toolkit.join.JoinTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 认证 服务
 */
@Service
public class AuthBearerUnitServiceImpl implements AuthBearerUnitService {

    @Autowired
    AuthBearerUnitDao authBearerUnitDao;

    @Autowired
    JoinTemplate joinTemplate;

    @Override
    public String createAuthBearerUnit(@NotNull @Valid AuthBearerUnit authBearerUnit) {
        authBearerUnit.setName("Authorization");
        AuthBearerUnitEntity authBearerUnitEntity = BeanMapper.map(authBearerUnit, AuthBearerUnitEntity.class);

        return authBearerUnitDao.createAuthBearerUnit(authBearerUnitEntity);
    }

    @Override
    public void updateAuthBearerUnit(@NotNull @Valid AuthBearerUnit authBearerUnit) {
        AuthBearerUnitEntity authBearerUnitEntity = BeanMapper.map(authBearerUnit, AuthBearerUnitEntity.class);

        authBearerUnitDao.updateAuthBearerUnit(authBearerUnitEntity);
    }

    @Override
    public void deleteAuthBearerUnit(@NotNull String id) {
        authBearerUnitDao.deleteAuthBearerUnit(id);
    }

    @Override
    public void deleteAllAuthBearerUnit(String id) {
        DeleteCondition deleteCondition = DeleteBuilders.createDelete(AuthBearerUnitEntity.class)
                .eq("apiUnitId", id)
                .get();
        authBearerUnitDao.deleteAuthBearerUnitList(deleteCondition);
    }

    @Override
    public AuthBearerUnit findAuthBearerUnit(@NotNull String id) {
        AuthBearerUnitEntity authBearerUnitEntity = authBearerUnitDao.findAuthBearerUnit(id);

        AuthBearerUnit authBearerUnit = BeanMapper.map(authBearerUnitEntity, AuthBearerUnit.class);

        return authBearerUnit;
    }

    @Override
    public List<AuthBearerUnit> findAllAuthBearerUnit() {
        List<AuthBearerUnitEntity> authBearerUnitEntityList =  authBearerUnitDao.findAllAuthBearerUnit();

        List<AuthBearerUnit> authBearerUnitList = BeanMapper.mapList(authBearerUnitEntityList, AuthBearerUnit.class);

        return authBearerUnitList;
    }

    @Override
    public List<AuthBearerUnit> findAuthBearerUnitList(AuthBearerUnitQuery authBearerUnitQuery) {
        List<AuthBearerUnitEntity> authBearerUnitEntityList = authBearerUnitDao.findAuthBearerUnitList(authBearerUnitQuery);

        List<AuthBearerUnit> authBearerUnitList = BeanMapper.mapList(authBearerUnitEntityList, AuthBearerUnit.class);

        return authBearerUnitList;
    }

    @Override
    public Pagination<AuthBearerUnit> findAuthBearerUnitPage(AuthBearerUnitQuery authBearerUnitQuery) {

        Pagination<AuthBearerUnitEntity>  pagination = authBearerUnitDao.findAuthBearerUnitPage(authBearerUnitQuery);

        List<AuthBearerUnit> authBearerUnitList = BeanMapper.mapList(pagination.getDataList(), AuthBearerUnit.class);

        return PaginationBuilder.build(pagination, authBearerUnitList);
    }
}