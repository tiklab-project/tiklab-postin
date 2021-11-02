package com.doublekit.apibox.apidef.service;

import com.doublekit.apibox.apidef.dao.RawParamDao;
import com.doublekit.apibox.apidef.entity.RawParamEntity;
import com.doublekit.apibox.apidef.model.RawParam;
import com.doublekit.apibox.apidef.model.RawParamQuery;

import com.doublekit.common.Pagination;
import com.doublekit.beans.BeanMapper;
import com.doublekit.common.PaginationBuilder;
import com.doublekit.join.JoinTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

import org.springframework.beans.BeanUtils;

/**
* 用户服务业务处理
*/
@Service
public class RawParamServiceImpl implements RawParamService {

    @Autowired
    RawParamDao rawParamDao;

    @Autowired
    JoinTemplate joinQuery;

    @Override
    public String createRawParam(@NotNull @Valid RawParam rawParam) {
        RawParamEntity rawParamEntity = BeanMapper.map(rawParam, RawParamEntity.class);

        return rawParamDao.createRawParam(rawParamEntity);
    }

    @Override
    public void updateRawParam(@NotNull @Valid RawParam rawParam) {
        RawParamEntity rawParamEntity = BeanMapper.map(rawParam, RawParamEntity.class);

        rawParamDao.updateRawParam(rawParamEntity);
    }

    @Override
    public void deleteRawParam(@NotNull String id) {
        rawParamDao.deleteRawParam(id);
    }

    @Override
    public RawParam findRawParam(@NotNull String id) {
        RawParamEntity rawParamEntity = rawParamDao.findRawParam(id);

        RawParam rawParam = BeanMapper.map(rawParamEntity, RawParam.class);

        joinQuery.queryOne(rawParam);

        return rawParam;
    }

    @Override
    public List<RawParam> findAllRawParam() {
        List<RawParamEntity> rawParamEntityList =  rawParamDao.findAllRawParam();

        List<RawParam> rawParamList =  BeanMapper.mapList(rawParamEntityList,RawParam.class);

        joinQuery.queryList(rawParamList);

        return rawParamList;
    }

    @Override
    public List<RawParam> findRawParamList(RawParamQuery rawParamQuery) {
        List<RawParamEntity> rawParamEntityList = rawParamDao.findRawParamList(rawParamQuery);

        List<RawParam> rawParamList = BeanMapper.mapList(rawParamEntityList,RawParam.class);

        joinQuery.queryList(rawParamList);

        return rawParamList;
    }

    @Override
    public Pagination<RawParam> findRawParamPage(RawParamQuery rawParamQuery) {

        Pagination<RawParamEntity>  pagination = rawParamDao.findRawParamPage(rawParamQuery);

        List<RawParam> rawParamList = BeanMapper.mapList(pagination.getDataList(),RawParam.class);

        joinQuery.queryList(rawParamList);

        return PaginationBuilder.build(pagination,rawParamList);
    }
}