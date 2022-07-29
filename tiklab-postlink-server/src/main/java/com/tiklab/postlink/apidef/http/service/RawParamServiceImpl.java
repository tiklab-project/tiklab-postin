package com.tiklab.postlink.apidef.http.service;

import com.tiklab.postlink.apidef.http.dao.RawParamDao;
import com.tiklab.postlink.apidef.http.entity.RawParamEntity;
import com.tiklab.postlink.apidef.http.model.RawParam;
import com.tiklab.postlink.apidef.http.model.RawParamQuery;

import com.tiklab.core.page.Pagination;
import com.tiklab.beans.BeanMapper;
import com.tiklab.core.page.PaginationBuilder;
import com.tiklab.join.JoinTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 用户服务业务处理
*/
@Service
public class RawParamServiceImpl implements RawParamService {

    @Autowired
    RawParamDao rawParamDao;

    @Autowired
    JoinTemplate joinTemplate;

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

        joinTemplate.joinQuery(rawParam);

        return rawParam;
    }

    @Override
    public List<RawParam> findAllRawParam() {
        List<RawParamEntity> rawParamEntityList =  rawParamDao.findAllRawParam();

        List<RawParam> rawParamList =  BeanMapper.mapList(rawParamEntityList,RawParam.class);

        joinTemplate.joinQuery(rawParamList);

        return rawParamList;
    }

    @Override
    public List<RawParam> findRawParamList(RawParamQuery rawParamQuery) {
        List<RawParamEntity> rawParamEntityList = rawParamDao.findRawParamList(rawParamQuery);

        List<RawParam> rawParamList = BeanMapper.mapList(rawParamEntityList,RawParam.class);

        joinTemplate.joinQuery(rawParamList);

        return rawParamList;
    }

    @Override
    public Pagination<RawParam> findRawParamPage(RawParamQuery rawParamQuery) {

        Pagination<RawParamEntity>  pagination = rawParamDao.findRawParamPage(rawParamQuery);

        List<RawParam> rawParamList = BeanMapper.mapList(pagination.getDataList(),RawParam.class);

        joinTemplate.joinQuery(rawParamList);

        return PaginationBuilder.build(pagination,rawParamList);
    }

}