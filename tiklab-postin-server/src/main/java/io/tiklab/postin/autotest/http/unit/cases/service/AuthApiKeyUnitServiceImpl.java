package io.tiklab.postin.autotest.http.unit.cases.service;

import io.tiklab.core.page.Pagination;
import io.tiklab.core.page.PaginationBuilder;
import io.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.DeleteBuilders;
import io.tiklab.postin.autotest.http.unit.cases.dao.AuthApiKeyUnitDao;
import io.tiklab.postin.autotest.http.unit.cases.entity.AuthApiKeyUnitEntity;
import io.tiklab.postin.autotest.http.unit.cases.model.AuthApiKeyUnit;
import io.tiklab.postin.autotest.http.unit.cases.model.AuthApiKeyUnitQuery;
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
public class AuthApiKeyUnitServiceImpl implements AuthApiKeyUnitService {

    @Autowired
    AuthApiKeyUnitDao authApiKeyUnitDao;

    @Autowired
    JoinTemplate joinTemplate;

    @Override
    public String createAuthApiKeyUnit(@NotNull @Valid AuthApiKeyUnit authApiKeyUnit) {
        AuthApiKeyUnitEntity authApiKeyUnitEntity = BeanMapper.map(authApiKeyUnit, AuthApiKeyUnitEntity.class);

        return authApiKeyUnitDao.createAuthApiKeyUnit(authApiKeyUnitEntity);
    }

    @Override
    public void updateAuthApiKeyUnit(@NotNull @Valid AuthApiKeyUnit authApiKeyUnit) {
        AuthApiKeyUnitEntity authApiKeyUnitEntity = BeanMapper.map(authApiKeyUnit, AuthApiKeyUnitEntity.class);

        authApiKeyUnitDao.updateAuthApiKeyUnit(authApiKeyUnitEntity);
    }

    @Override
    public void deleteAuthApiKeyUnit(@NotNull String id) {
        authApiKeyUnitDao.deleteAuthApiKeyUnit(id);
    }

    @Override
    public void deleteAllAuthApiKeyUnit(String id) {
        DeleteCondition deleteCondition = DeleteBuilders.createDelete(AuthApiKeyUnitEntity.class)
                .eq("apiId", id)
                .get();
        authApiKeyUnitDao.deleteAuthApiKeyUnitList(deleteCondition);
    }

    @Override
    public AuthApiKeyUnit findAuthApiKeyUnit(@NotNull String id) {
        AuthApiKeyUnitEntity authApiKeyUnitEntity = authApiKeyUnitDao.findAuthApiKeyUnit(id);

        AuthApiKeyUnit authApiKeyUnit = BeanMapper.map(authApiKeyUnitEntity, AuthApiKeyUnit.class);

        return authApiKeyUnit;
    }

    @Override
    public List<AuthApiKeyUnit> findAllAuthApiKeyUnit() {
        List<AuthApiKeyUnitEntity> authApiKeyUnitEntityList =  authApiKeyUnitDao.findAllAuthApiKeyUnit();

        List<AuthApiKeyUnit> authApiKeyUnitList = BeanMapper.mapList(authApiKeyUnitEntityList, AuthApiKeyUnit.class);

        return authApiKeyUnitList;
    }

    @Override
    public List<AuthApiKeyUnit> findAuthApiKeyUnitList(AuthApiKeyUnitQuery authApiKeyUnitQuery) {
        List<AuthApiKeyUnitEntity> authApiKeyUnitEntityList = authApiKeyUnitDao.findAuthApiKeyUnitList(authApiKeyUnitQuery);

        List<AuthApiKeyUnit> authApiKeyUnitList = BeanMapper.mapList(authApiKeyUnitEntityList, AuthApiKeyUnit.class);

        return authApiKeyUnitList;
    }

    @Override
    public Pagination<AuthApiKeyUnit> findAuthApiKeyUnitPage(AuthApiKeyUnitQuery authApiKeyUnitQuery) {

        Pagination<AuthApiKeyUnitEntity>  pagination = authApiKeyUnitDao.findAuthApiKeyUnitPage(authApiKeyUnitQuery);

        List<AuthApiKeyUnit> authApiKeyUnitList = BeanMapper.mapList(pagination.getDataList(), AuthApiKeyUnit.class);

        return PaginationBuilder.build(pagination, authApiKeyUnitList);
    }
}