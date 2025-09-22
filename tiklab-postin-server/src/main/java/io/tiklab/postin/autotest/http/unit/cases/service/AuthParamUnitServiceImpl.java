package io.tiklab.postin.autotest.http.unit.cases.service;

import io.tiklab.core.page.Pagination;
import io.tiklab.core.page.PaginationBuilder;
import io.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.DeleteBuilders;
import io.tiklab.postin.autotest.http.unit.cases.dao.AuthParamUnitDao;
import io.tiklab.postin.autotest.http.unit.cases.entity.AuthParamUnitEntity;
import io.tiklab.postin.autotest.http.unit.cases.model.AuthApiKeyUnit;
import io.tiklab.postin.autotest.http.unit.cases.model.AuthBearerUnit;
import io.tiklab.postin.autotest.http.unit.cases.model.AuthParamUnit;
import io.tiklab.postin.autotest.http.unit.cases.model.AuthParamUnitQuery;
import io.tiklab.postin.common.MagicValue;
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
public class AuthParamUnitServiceImpl implements AuthParamUnitService {

    @Autowired
    AuthParamUnitDao authParamUnitDao;

    @Autowired
    JoinTemplate joinTemplate;

    @Autowired
    AuthApiKeyUnitService authApiKeyUnitService;

    @Autowired
    AuthBearerUnitService authBearerUnitService;

    @Override
    public String createAuthParamUnit(@NotNull @Valid AuthParamUnit authParamUnit) {
        AuthParamUnitEntity authParamUnitEntity = BeanMapper.map(authParamUnit, AuthParamUnitEntity.class);
        String id = authParamUnitDao.createAuthParamUnit(authParamUnitEntity);

        switch (authParamUnit.getType()){
            case MagicValue.AUTHENTICATION_TYPE_APIKEY:
                AuthApiKeyUnit apiKey = authParamUnit.getApiKey();
                apiKey.setApiUnitId(id);
                apiKey.setId(id);
                authApiKeyUnitService.createAuthApiKeyUnit(apiKey);
                break;
            case MagicValue.AUTHENTICATION_TYPE_BEARER:
                AuthBearerUnit bearer = authParamUnit.getBearer();
                bearer.setApiUnitId(id);
                bearer.setId(id);
                authBearerUnitService.createAuthBearerUnit(bearer);
                break;
            default:

        }
        return id;
    }

    @Override
    public void updateAuthParamUnit(@NotNull @Valid AuthParamUnit authParamUnit) {
        AuthParamUnitEntity authParamUnitEntity = BeanMapper.map(authParamUnit, AuthParamUnitEntity.class);

        authParamUnitDao.updateAuthParamUnit(authParamUnitEntity);

        switch (authParamUnit.getType()){
            case MagicValue.AUTHENTICATION_TYPE_APIKEY:
                AuthApiKeyUnit authApiKeyUnit = authApiKeyUnitService.findAuthApiKeyUnit(authParamUnit.getApiUnitId());
                if(authApiKeyUnit == null){
                    authApiKeyUnitService.createAuthApiKeyUnit(authParamUnit.getApiKey());

                    //另一个就需要删除
                    AuthBearerUnit authBearer = authBearerUnitService.findAuthBearerUnit(authParamUnit.getApiUnitId());
                    if(authBearer != null){
                        authBearerUnitService.deleteAuthBearerUnit(authParamUnit.getApiUnitId());
                    }
                }else {
                    authApiKeyUnitService.updateAuthApiKeyUnit(authParamUnit.getApiKey());
                }
                break;
            case MagicValue.AUTHENTICATION_TYPE_BEARER:
                AuthBearerUnit authBearerUnit = authBearerUnitService.findAuthBearerUnit(authParamUnit.getApiUnitId());
                if(authBearerUnit == null){
                    authBearerUnitService.createAuthBearerUnit(authParamUnit.getBearer());

                    //另一个就需要删除
                    AuthApiKeyUnit authApiKey= authApiKeyUnitService.findAuthApiKeyUnit(authParamUnit.getApiUnitId());
                    if(authApiKey != null){
                        authApiKeyUnitService.deleteAuthApiKeyUnit(authParamUnit.getApiUnitId());
                    }
                }else {
                    authBearerUnitService.updateAuthBearerUnit(authParamUnit.getBearer());
                }
                break;
            default:
        }

    }

    @Override
    public void deleteAuthParamUnit(@NotNull String id) {
        authParamUnitDao.deleteAuthParamUnit(id);
    }

    @Override
    public void deleteAllAuthParamUnit(String id) {
        DeleteCondition deleteCondition = DeleteBuilders.createDelete(AuthParamUnitEntity.class)
                .eq("apiId", id)
                .get();
        authParamUnitDao.deleteAuthParamUnitList(deleteCondition);
    }

    @Override
    public AuthParamUnit findAuthParamUnit(@NotNull String id) {
        AuthParamUnitEntity authParamUnitEntity = authParamUnitDao.findAuthParamUnit(id);
        AuthParamUnit authParamUnit = BeanMapper.map(authParamUnitEntity, AuthParamUnit.class);

        if(authParamUnit == null){
            return null;
        }

        switch (authParamUnit.getType()){
            case MagicValue.AUTHENTICATION_TYPE_APIKEY:
                authParamUnit.setApiKey(authApiKeyUnitService.findAuthApiKeyUnit(id));
                break;
            case MagicValue.AUTHENTICATION_TYPE_BEARER:
                authParamUnit.setBearer(authBearerUnitService.findAuthBearerUnit(id));
                break;
            default:
        }

        return authParamUnit;
    }

    @Override
    public List<AuthParamUnit> findAllAuthParamUnit() {
        List<AuthParamUnitEntity> authParamUnitEntityList =  authParamUnitDao.findAllAuthParamUnit();

        List<AuthParamUnit> authParamUnitList = BeanMapper.mapList(authParamUnitEntityList, AuthParamUnit.class);

        return authParamUnitList;
    }

    @Override
    public List<AuthParamUnit> findAuthParamUnitList(AuthParamUnitQuery authParamUnitQuery) {
        List<AuthParamUnitEntity> authParamUnitEntityList = authParamUnitDao.findAuthParamUnitList(authParamUnitQuery);

        List<AuthParamUnit> authParamUnitList = BeanMapper.mapList(authParamUnitEntityList, AuthParamUnit.class);

        return authParamUnitList;
    }

    @Override
    public Pagination<AuthParamUnit> findAuthParamUnitPage(AuthParamUnitQuery authParamUnitQuery) {

        Pagination<AuthParamUnitEntity>  pagination = authParamUnitDao.findAuthParamUnitPage(authParamUnitQuery);

        List<AuthParamUnit> authParamUnitList = BeanMapper.mapList(pagination.getDataList(), AuthParamUnit.class);

        return PaginationBuilder.build(pagination, authParamUnitList);
    }
}