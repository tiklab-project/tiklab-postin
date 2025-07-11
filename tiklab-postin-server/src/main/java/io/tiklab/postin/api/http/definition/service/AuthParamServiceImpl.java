package io.tiklab.postin.api.http.definition.service;

import io.tiklab.core.page.Pagination;
import io.tiklab.core.page.PaginationBuilder;
import io.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.DeleteBuilders;
import io.tiklab.postin.api.http.definition.dao.AuthParamDao;
import io.tiklab.postin.api.http.definition.entity.AuthParamEntity;
import io.tiklab.postin.api.http.definition.model.AuthApiKey;
import io.tiklab.postin.api.http.definition.model.AuthBearer;
import io.tiklab.postin.api.http.definition.model.AuthParam;
import io.tiklab.postin.api.http.definition.model.AuthParamQuery;
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
public class AuthParamServiceImpl implements AuthParamService {

    @Autowired
    AuthParamDao authParamDao;

    @Autowired
    JoinTemplate joinTemplate;

    @Autowired
    AuthApiKeyService authApiKeyService;

    @Autowired
    AuthBearerService authBearerService;

    @Override
    public String createAuthParam(@NotNull @Valid AuthParam authParam) {
        AuthParamEntity authParamEntity = BeanMapper.map(authParam, AuthParamEntity.class);
        String id = authParamDao.createAuthParam(authParamEntity);

        switch (authParam.getType()){
            case MagicValue.AUTHENTICATION_TYPE_APIKEY:
                authApiKeyService.createAuthApiKey(authParam.getApiKey());
                break;
            case MagicValue.AUTHENTICATION_TYPE_BEARER:
                authBearerService.createAuthBearer(authParam.getBearer());
                break;
            default:

        }
        return id;
    }

    @Override
    public void updateAuthParam(@NotNull @Valid AuthParam authParam) {
        AuthParamEntity authParamEntity = BeanMapper.map(authParam, AuthParamEntity.class);

        authParamDao.updateAuthParam(authParamEntity);

        switch (authParam.getType()){
            case MagicValue.AUTHENTICATION_TYPE_APIKEY:
                AuthApiKey authApiKey = authApiKeyService.findAuthApiKey(authParam.getApiId());
                if(authApiKey == null){
                    authApiKeyService.createAuthApiKey(authParam.getApiKey());
                }else {
                    authApiKeyService.updateAuthApiKey(authParam.getApiKey());
                }
                break;
            case MagicValue.AUTHENTICATION_TYPE_BEARER:
                AuthBearer authBearer = authBearerService.findAuthBearer(authParam.getApiId());
                if(authBearer == null){
                    authBearerService.createAuthBearer(authParam.getBearer());
                }else {
                    authBearerService.updateAuthBearer(authParam.getBearer());
                }
                break;
            default:
        }

    }

    @Override
    public void deleteAuthParam(@NotNull String id) {
        authParamDao.deleteAuthParam(id);
    }

    @Override
    public void deleteAllAuthParam(String id) {
        DeleteCondition deleteCondition = DeleteBuilders.createDelete(AuthParamEntity.class)
                .eq("apiId", id)
                .get();
        authParamDao.deleteAuthParamList(deleteCondition);
    }

    @Override
    public AuthParam findAuthParam(@NotNull String id) {
        AuthParamEntity authParamEntity = authParamDao.findAuthParam(id);
        AuthParam authParam = BeanMapper.map(authParamEntity, AuthParam.class);

        if(authParam == null){
            return null;
        }

        switch (authParam.getType()){
            case MagicValue.AUTHENTICATION_TYPE_APIKEY:
                authParam.setApiKey(authApiKeyService.findAuthApiKey(id));
                break;
            case MagicValue.AUTHENTICATION_TYPE_BEARER:
                authParam.setBearer(authBearerService.findAuthBearer(id));
                break;
            default:
        }

        return authParam;
    }

    @Override
    public List<AuthParam> findAllAuthParam() {
        List<AuthParamEntity> authParamEntityList =  authParamDao.findAllAuthParam();

        List<AuthParam> authParamList = BeanMapper.mapList(authParamEntityList, AuthParam.class);

        return authParamList;
    }

    @Override
    public List<AuthParam> findAuthParamList(AuthParamQuery authParamQuery) {
        List<AuthParamEntity> authParamEntityList = authParamDao.findAuthParamList(authParamQuery);

        List<AuthParam> authParamList = BeanMapper.mapList(authParamEntityList, AuthParam.class);

        return authParamList;
    }

    @Override
    public Pagination<AuthParam> findAuthParamPage(AuthParamQuery authParamQuery) {

        Pagination<AuthParamEntity>  pagination = authParamDao.findAuthParamPage(authParamQuery);

        List<AuthParam> authParamList = BeanMapper.mapList(pagination.getDataList(), AuthParam.class);

        return PaginationBuilder.build(pagination, authParamList);
    }
}