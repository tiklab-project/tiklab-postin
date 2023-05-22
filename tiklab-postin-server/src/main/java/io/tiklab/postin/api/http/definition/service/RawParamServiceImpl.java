package io.tiklab.postin.api.http.definition.service;

import io.tiklab.postin.api.http.definition.dao.RawParamDao;
import io.tiklab.postin.api.http.definition.entity.RawParamsEntity;
import io.tiklab.postin.api.http.definition.model.RawParams;
import io.tiklab.postin.api.http.definition.model.RawParamQuery;

import io.tiklab.core.page.Pagination;
import io.tiklab.beans.BeanMapper;
import io.tiklab.core.page.PaginationBuilder;
import io.tiklab.join.JoinTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 定义
 * http协议
 * raw服务
 */
@Service
public class RawParamServiceImpl implements RawParamService {

    @Autowired
    RawParamDao rawParamDao;

    @Autowired
    JoinTemplate joinTemplate;

    @Override
    public String createRawParam(@NotNull @Valid RawParams rawParams) {
        RawParamsEntity rawParamsEntity = BeanMapper.map(rawParams, RawParamsEntity.class);

        return rawParamDao.createRawParam(rawParamsEntity);
    }

    @Override
    public void updateRawParam(@NotNull @Valid RawParams rawParams) {
        RawParamsEntity rawParamsEntity = BeanMapper.map(rawParams, RawParamsEntity.class);

        rawParamDao.updateRawParam(rawParamsEntity);
    }

    @Override
    public void deleteRawParam(@NotNull String id) {
        rawParamDao.deleteRawParam(id);
    }

    @Override
    public RawParams findRawParam(@NotNull String id) {
        RawParamsEntity rawParamsEntity = rawParamDao.findRawParam(id);

        RawParams rawParams = BeanMapper.map(rawParamsEntity, RawParams.class);

        joinTemplate.joinQuery(rawParams);

        return rawParams;
    }

    @Override
    public List<RawParams> findAllRawParam() {
        List<RawParamsEntity> rawParamsEntityList =  rawParamDao.findAllRawParam();

        List<RawParams> rawParamsList =  BeanMapper.mapList(rawParamsEntityList, RawParams.class);

        joinTemplate.joinQuery(rawParamsList);

        return rawParamsList;
    }

    @Override
    public List<RawParams> findRawParamList(RawParamQuery rawParamQuery) {
        List<RawParamsEntity> rawParamsEntityList = rawParamDao.findRawParamList(rawParamQuery);

        List<RawParams> rawParamsList = BeanMapper.mapList(rawParamsEntityList, RawParams.class);

        joinTemplate.joinQuery(rawParamsList);

        return rawParamsList;
    }

    @Override
    public Pagination<RawParams> findRawParamPage(RawParamQuery rawParamQuery) {

        Pagination<RawParamsEntity>  pagination = rawParamDao.findRawParamPage(rawParamQuery);

        List<RawParams> rawParamsList = BeanMapper.mapList(pagination.getDataList(), RawParams.class);

        joinTemplate.joinQuery(rawParamsList);

        return PaginationBuilder.build(pagination, rawParamsList);
    }

}