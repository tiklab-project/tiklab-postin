package io.tiklab.postin.api.http.definition.service;

import io.tiklab.core.page.Pagination;
import io.tiklab.core.page.PaginationBuilder;
import io.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.DeleteBuilders;
import io.tiklab.postin.api.http.definition.dao.AuthBearerDao;
import io.tiklab.postin.api.http.definition.entity.AuthBearerEntity;
import io.tiklab.postin.api.http.definition.model.AuthBearer;
import io.tiklab.postin.api.http.definition.model.AuthBearerQuery;
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
public class AuthBearerServiceImpl implements AuthBearerService {

    @Autowired
    AuthBearerDao authBearerDao;

    @Autowired
    JoinTemplate joinTemplate;

    @Override
    public String createAuthBearer(@NotNull @Valid AuthBearer authBearer) {
        authBearer.setName("Authorization");
        AuthBearerEntity authBearerEntity = BeanMapper.map(authBearer, AuthBearerEntity.class);

        return authBearerDao.createAuthBearer(authBearerEntity);
    }

    @Override
    public void updateAuthBearer(@NotNull @Valid AuthBearer authBearer) {
        AuthBearerEntity authBearerEntity = BeanMapper.map(authBearer, AuthBearerEntity.class);

        authBearerDao.updateAuthBearer(authBearerEntity);
    }

    @Override
    public void deleteAuthBearer(@NotNull String id) {
        authBearerDao.deleteAuthBearer(id);
    }

    @Override
    public void deleteAllAuthBearer(String id) {
        DeleteCondition deleteCondition = DeleteBuilders.createDelete(AuthBearerEntity.class)
                .eq("apiId", id)
                .get();
        authBearerDao.deleteAuthBearerList(deleteCondition);
    }

    @Override
    public AuthBearer findAuthBearer(@NotNull String id) {
        AuthBearerEntity authBearerEntity = authBearerDao.findAuthBearer(id);

        AuthBearer authBearer = BeanMapper.map(authBearerEntity, AuthBearer.class);

        return authBearer;
    }

    @Override
    public List<AuthBearer> findAllAuthBearer() {
        List<AuthBearerEntity> authBearerEntityList =  authBearerDao.findAllAuthBearer();

        List<AuthBearer> authBearerList = BeanMapper.mapList(authBearerEntityList, AuthBearer.class);

        return authBearerList;
    }

    @Override
    public List<AuthBearer> findAuthBearerList(AuthBearerQuery authBearerQuery) {
        List<AuthBearerEntity> authBearerEntityList = authBearerDao.findAuthBearerList(authBearerQuery);

        List<AuthBearer> authBearerList = BeanMapper.mapList(authBearerEntityList, AuthBearer.class);

        return authBearerList;
    }

    @Override
    public Pagination<AuthBearer> findAuthBearerPage(AuthBearerQuery authBearerQuery) {

        Pagination<AuthBearerEntity>  pagination = authBearerDao.findAuthBearerPage(authBearerQuery);

        List<AuthBearer> authBearerList = BeanMapper.mapList(pagination.getDataList(), AuthBearer.class);

        return PaginationBuilder.build(pagination, authBearerList);
    }
}