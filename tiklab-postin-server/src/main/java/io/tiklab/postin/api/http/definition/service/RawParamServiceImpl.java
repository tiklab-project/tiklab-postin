package io.tiklab.postin.api.http.definition.service;

import io.tiklab.postin.api.http.definition.dao.RawParamDao;
import io.tiklab.postin.api.http.definition.entity.RawParamsEntity;
import io.tiklab.postin.api.http.definition.model.RawParam;
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
    public String createRawParam(@NotNull @Valid RawParam rawParam) {
        RawParamsEntity rawParamsEntity = BeanMapper.map(rawParam, RawParamsEntity.class);

        return rawParamDao.createRawParam(rawParamsEntity);
    }

    @Override
    public void updateRawParam(@NotNull @Valid RawParam rawParam) {
        RawParamsEntity rawParamsEntity = BeanMapper.map(rawParam, RawParamsEntity.class);

        rawParamDao.updateRawParam(rawParamsEntity);
    }

    @Override
    public void deleteRawParam(@NotNull String id) {
        rawParamDao.deleteRawParam(id);
    }

    @Override
    public RawParam findRawParam(@NotNull String id) {
        RawParamsEntity rawParamsEntity = rawParamDao.findRawParam(id);

        RawParam rawParam = BeanMapper.map(rawParamsEntity, RawParam.class);

        joinTemplate.joinQuery(rawParam);

        return rawParam;
    }

    @Override
    public List<RawParam> findAllRawParam() {
        List<RawParamsEntity> rawParamsEntityList =  rawParamDao.findAllRawParam();

        List<RawParam> rawParamList =  BeanMapper.mapList(rawParamsEntityList, RawParam.class);

        joinTemplate.joinQuery(rawParamList);

        return rawParamList;
    }

    @Override
    public List<RawParam> findRawParamList(RawParamQuery rawParamQuery) {
        List<RawParamsEntity> rawParamsEntityList = rawParamDao.findRawParamList(rawParamQuery);

        List<RawParam> rawParamList = BeanMapper.mapList(rawParamsEntityList, RawParam.class);

        joinTemplate.joinQuery(rawParamList);

        return rawParamList;
    }

    @Override
    public Pagination<RawParam> findRawParamPage(RawParamQuery rawParamQuery) {

        Pagination<RawParamsEntity>  pagination = rawParamDao.findRawParamPage(rawParamQuery);

        List<RawParam> rawParamList = BeanMapper.mapList(pagination.getDataList(), RawParam.class);

        joinTemplate.joinQuery(rawParamList);

        return PaginationBuilder.build(pagination, rawParamList);
    }

}