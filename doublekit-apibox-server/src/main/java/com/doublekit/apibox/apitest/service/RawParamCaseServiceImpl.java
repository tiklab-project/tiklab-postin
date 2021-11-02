package com.doublekit.apibox.apitest.service;

import com.doublekit.apibox.apitest.dao.RawParamCaseDao;
import com.doublekit.apibox.apitest.entity.RawParamCaseEntity;
import com.doublekit.apibox.apitest.model.RawParamCase;
import com.doublekit.apibox.apitest.model.RawParamCaseQuery;

import com.doublekit.common.Pagination;
import com.doublekit.beans.BeanMapper;
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
public class RawParamCaseServiceImpl implements RawParamCaseService {

    @Autowired
    RawParamCaseDao rawParamCaseDao;

    @Autowired
    JoinTemplate joinQuery;

    @Override
    public String createRawParamCase(@NotNull @Valid RawParamCase rawParamCase) {
        RawParamCaseEntity rawParamCaseEntity = BeanMapper.map(rawParamCase, RawParamCaseEntity.class);

        return rawParamCaseDao.createRawParamCase(rawParamCaseEntity);
    }

    @Override
    public void updateRawParamCase(@NotNull @Valid RawParamCase rawParamCase) {
        RawParamCaseEntity rawParamCaseEntity = BeanMapper.map(rawParamCase, RawParamCaseEntity.class);

        rawParamCaseDao.updateRawParamCase(rawParamCaseEntity);
    }

    @Override
    public void deleteRawParamCase(@NotNull String id) {
        rawParamCaseDao.deleteRawParamCase(id);
    }

    @Override
    public RawParamCase findOne(String id) {
        RawParamCaseEntity rawParamCaseEntity = rawParamCaseDao.findRawParamCase(id);

        RawParamCase rawParamCase = BeanMapper.map(rawParamCaseEntity, RawParamCase.class);
        return rawParamCase;
    }

    @Override
    public List<RawParamCase> findList(List<String> idList) {
        List<RawParamCaseEntity> rawParamCaseEntityList =  rawParamCaseDao.findRawParamCaseList(idList);

        List<RawParamCase> rawParamCaseList =  BeanMapper.mapList(rawParamCaseEntityList,RawParamCase.class);
        return rawParamCaseList;
    }

    @Override
    public RawParamCase findRawParamCase(@NotNull String id) {
        RawParamCase rawParamCase = findOne(id);

        joinQuery.queryOne(rawParamCase);
        return rawParamCase;
    }

    @Override
    public List<RawParamCase> findAllRawParamCase() {
        List<RawParamCaseEntity> rawParamCaseEntityList =  rawParamCaseDao.findAllRawParamCase();

        List<RawParamCase> rawParamCaseList =  BeanMapper.mapList(rawParamCaseEntityList,RawParamCase.class);

        joinQuery.queryList(rawParamCaseList);
        return rawParamCaseList;
    }

    @Override
    public List<RawParamCase> findRawParamCaseList(RawParamCaseQuery rawParamCaseQuery) {
        List<RawParamCaseEntity> rawParamCaseEntityList = rawParamCaseDao.findRawParamCaseList(rawParamCaseQuery);

        List<RawParamCase> rawParamCaseList = BeanMapper.mapList(rawParamCaseEntityList,RawParamCase.class);

        joinQuery.queryList(rawParamCaseList);

        return rawParamCaseList;
    }

    @Override
    public Pagination<RawParamCase> findRawParamCasePage(RawParamCaseQuery rawParamCaseQuery) {
        Pagination<RawParamCase> pg = new Pagination<>();

        Pagination<RawParamCaseEntity>  pagination = rawParamCaseDao.findRawParamCasePage(rawParamCaseQuery);
        BeanUtils.copyProperties(pagination,pg);

        List<RawParamCase> rawParamCaseList = BeanMapper.mapList(pagination.getDataList(),RawParamCase.class);

        joinQuery.queryList(rawParamCaseList);

        pg.setDataList(rawParamCaseList);
        return pg;
    }
}