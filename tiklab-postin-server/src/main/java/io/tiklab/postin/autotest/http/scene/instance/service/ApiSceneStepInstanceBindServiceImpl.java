package io.tiklab.postin.autotest.http.scene.instance.service;

import io.tiklab.postin.autotest.http.scene.instance.model.ApiSceneStepInstanceBind;
import io.tiklab.postin.autotest.http.scene.instance.model.ApiSceneStepInstanceBindQuery;
import io.tiklab.toolkit.beans.BeanMapper;
import io.tiklab.core.page.Pagination;
import io.tiklab.core.page.PaginationBuilder;
import io.tiklab.toolkit.join.JoinTemplate;

import io.tiklab.postin.autotest.http.scene.instance.entity.ApiSceneStepInstanceBindEntity;
import io.tiklab.postin.autotest.http.scene.instance.dao.ApiSceneStepInstanceBindDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 接口场景下步骤的公共实例 服务
*/
@Service
public class ApiSceneStepInstanceBindServiceImpl implements ApiSceneStepInstanceBindService {

    @Autowired
    ApiSceneStepInstanceBindDao apiSceneStepInstanceBindDao;

    @Autowired
    JoinTemplate joinTemplate;

    @Override
    public String createApiSceneStepInstanceBind(@NotNull @Valid ApiSceneStepInstanceBind apiSceneStepInstanceBind) {
        ApiSceneStepInstanceBindEntity apiSceneStepInstanceBindEntity = BeanMapper.map(apiSceneStepInstanceBind, ApiSceneStepInstanceBindEntity.class);

        return apiSceneStepInstanceBindDao.createApiSceneStepInstanceBind(apiSceneStepInstanceBindEntity);
    }

    @Override
    public void updateApiSceneStepInstanceBind(@NotNull @Valid ApiSceneStepInstanceBind apiSceneStepInstanceBind) {
        ApiSceneStepInstanceBindEntity apiSceneStepInstanceBindEntity = BeanMapper.map(apiSceneStepInstanceBind, ApiSceneStepInstanceBindEntity.class);

        apiSceneStepInstanceBindDao.updateApiSceneStepInstanceBind(apiSceneStepInstanceBindEntity);
    }

    @Override
    public void deleteApiSceneStepInstanceBind(@NotNull String id) {
        apiSceneStepInstanceBindDao.deleteApiSceneStepInstanceBind(id);
    }

    @Override
    public ApiSceneStepInstanceBind findOne(String id) {
        ApiSceneStepInstanceBindEntity apiSceneStepInstanceBindEntity = apiSceneStepInstanceBindDao.findApiSceneStepInstanceBind(id);

        ApiSceneStepInstanceBind apiSceneStepInstanceBind = BeanMapper.map(apiSceneStepInstanceBindEntity, ApiSceneStepInstanceBind.class);
        return apiSceneStepInstanceBind;
    }

    @Override
    public List<ApiSceneStepInstanceBind> findList(List<String> idList) {
        List<ApiSceneStepInstanceBindEntity> apiSceneStepInstanceBindEntityList =  apiSceneStepInstanceBindDao.findApiSceneStepInstanceBindList(idList);

        List<ApiSceneStepInstanceBind> apiSceneStepInstanceBindList =  BeanMapper.mapList(apiSceneStepInstanceBindEntityList,ApiSceneStepInstanceBind.class);
        return apiSceneStepInstanceBindList;
    }

    @Override
    public ApiSceneStepInstanceBind findApiSceneStepInstanceBind(@NotNull String id) {
        ApiSceneStepInstanceBind apiSceneStepInstanceBind = findOne(id);

        joinTemplate.joinQuery(apiSceneStepInstanceBind);

        return apiSceneStepInstanceBind;
    }

    @Override
    public List<ApiSceneStepInstanceBind> findAllApiSceneStepInstanceBind() {
        List<ApiSceneStepInstanceBindEntity> apiSceneStepInstanceBindEntityList =  apiSceneStepInstanceBindDao.findAllApiSceneStepInstanceBind();

        List<ApiSceneStepInstanceBind> apiSceneStepInstanceBindList =  BeanMapper.mapList(apiSceneStepInstanceBindEntityList,ApiSceneStepInstanceBind.class);

        joinTemplate.joinQuery(apiSceneStepInstanceBindList);

        return apiSceneStepInstanceBindList;
    }

    @Override
    public List<ApiSceneStepInstanceBind> findApiSceneStepInstanceBindList(ApiSceneStepInstanceBindQuery apiSceneStepInstanceBindQuery) {
        List<ApiSceneStepInstanceBindEntity> apiSceneStepInstanceBindEntityList = apiSceneStepInstanceBindDao.findApiSceneStepInstanceBindList(apiSceneStepInstanceBindQuery);

        List<ApiSceneStepInstanceBind> apiSceneStepInstanceBindList = BeanMapper.mapList(apiSceneStepInstanceBindEntityList,ApiSceneStepInstanceBind.class);

        joinTemplate.joinQuery(apiSceneStepInstanceBindList);

        return apiSceneStepInstanceBindList;
    }

    @Override
    public Pagination<ApiSceneStepInstanceBind> findApiSceneStepInstanceBindPage(ApiSceneStepInstanceBindQuery apiSceneStepInstanceBindQuery) {
        Pagination<ApiSceneStepInstanceBindEntity>  pagination = apiSceneStepInstanceBindDao.findApiSceneStepInstanceBindPage(apiSceneStepInstanceBindQuery);

        List<ApiSceneStepInstanceBind> apiSceneStepInstanceBindList = BeanMapper.mapList(pagination.getDataList(),ApiSceneStepInstanceBind.class);

        joinTemplate.joinQuery(apiSceneStepInstanceBindList);

        return PaginationBuilder.build(pagination,apiSceneStepInstanceBindList);
    }
}