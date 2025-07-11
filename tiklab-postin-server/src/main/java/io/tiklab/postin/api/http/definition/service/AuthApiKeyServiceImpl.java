package io.tiklab.postin.api.http.definition.service;

import io.tiklab.core.page.Pagination;
import io.tiklab.core.page.PaginationBuilder;
import io.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.DeleteBuilders;
import io.tiklab.postin.api.http.definition.dao.AuthApiKeyDao;
import io.tiklab.postin.api.http.definition.entity.AuthApiKeyEntity;
import io.tiklab.postin.api.http.definition.model.AuthApiKey;
import io.tiklab.postin.api.http.definition.model.AuthApiKeyQuery;
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
public class AuthApiKeyServiceImpl implements AuthApiKeyService {

    @Autowired
    AuthApiKeyDao authApiKeyDao;

    @Autowired
    JoinTemplate joinTemplate;

    @Override
    public String createAuthApiKey(@NotNull @Valid AuthApiKey authApiKey) {
        AuthApiKeyEntity authApiKeyEntity = BeanMapper.map(authApiKey, AuthApiKeyEntity.class);

        return authApiKeyDao.createAuthApiKey(authApiKeyEntity);
    }

    @Override
    public void updateAuthApiKey(@NotNull @Valid AuthApiKey authApiKey) {
        AuthApiKeyEntity authApiKeyEntity = BeanMapper.map(authApiKey, AuthApiKeyEntity.class);

        authApiKeyDao.updateAuthApiKey(authApiKeyEntity);
    }

    @Override
    public void deleteAuthApiKey(@NotNull String id) {
        authApiKeyDao.deleteAuthApiKey(id);
    }

    @Override
    public void deleteAllAuthApiKey(String id) {
        DeleteCondition deleteCondition = DeleteBuilders.createDelete(AuthApiKeyEntity.class)
                .eq("apiId", id)
                .get();
        authApiKeyDao.deleteAuthApiKeyList(deleteCondition);
    }

    @Override
    public AuthApiKey findAuthApiKey(@NotNull String id) {
        AuthApiKeyEntity authApiKeyEntity = authApiKeyDao.findAuthApiKey(id);

        AuthApiKey authApiKey = BeanMapper.map(authApiKeyEntity, AuthApiKey.class);

        return authApiKey;
    }

    @Override
    public List<AuthApiKey> findAllAuthApiKey() {
        List<AuthApiKeyEntity> authApiKeyEntityList =  authApiKeyDao.findAllAuthApiKey();

        List<AuthApiKey> authApiKeyList = BeanMapper.mapList(authApiKeyEntityList, AuthApiKey.class);

        return authApiKeyList;
    }

    @Override
    public List<AuthApiKey> findAuthApiKeyList(AuthApiKeyQuery authApiKeyQuery) {
        List<AuthApiKeyEntity> authApiKeyEntityList = authApiKeyDao.findAuthApiKeyList(authApiKeyQuery);

        List<AuthApiKey> authApiKeyList = BeanMapper.mapList(authApiKeyEntityList, AuthApiKey.class);

        return authApiKeyList;
    }

    @Override
    public Pagination<AuthApiKey> findAuthApiKeyPage(AuthApiKeyQuery authApiKeyQuery) {

        Pagination<AuthApiKeyEntity>  pagination = authApiKeyDao.findAuthApiKeyPage(authApiKeyQuery);

        List<AuthApiKey> authApiKeyList = BeanMapper.mapList(pagination.getDataList(), AuthApiKey.class);

        return PaginationBuilder.build(pagination, authApiKeyList);
    }
}