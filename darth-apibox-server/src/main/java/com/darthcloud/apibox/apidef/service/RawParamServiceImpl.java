package com.darthcloud.apibox.apidef.service;

import com.darthcloud.apibox.apidef.dao.RawParamDao;
import com.darthcloud.apibox.apidef.entity.RawParamPo;
import com.darthcloud.apibox.apidef.model.RawParam;
import com.darthcloud.apibox.apidef.model.RawParamQuery;

import com.darthcloud.common.Pagination;
import com.darthcloud.beans.BeanMapper;
import com.darthcloud.join.join.JoinQuery;
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
    JoinQuery joinQuery;

    @Override
    public String createRawParam(@NotNull @Valid RawParam rawParam) {
        RawParamPo rawParamPo = BeanMapper.map(rawParam, RawParamPo.class);

        return rawParamDao.createRawParam(rawParamPo);
    }

    @Override
    public void updateRawParam(@NotNull @Valid RawParam rawParam) {
        RawParamPo rawParamPo = BeanMapper.map(rawParam, RawParamPo.class);

        rawParamDao.updateRawParam(rawParamPo);
    }

    @Override
    public void deleteRawParam(@NotNull String id) {
        rawParamDao.deleteRawParam(id);
    }

    @Override
    public RawParam findRawParam(@NotNull String id) {
        RawParamPo rawParamPo = rawParamDao.findRawParam(id);

        RawParam rawParam = BeanMapper.map(rawParamPo, RawParam.class);

        joinQuery.queryOne(rawParam);

        return rawParam;
    }

    @Override
    public List<RawParam> findAllRawParam() {
        List<RawParamPo> rawParamPoList =  rawParamDao.findAllRawParam();

        List<RawParam> rawParamList =  BeanMapper.mapList(rawParamPoList,RawParam.class);

        joinQuery.queryList(rawParamList);

        return rawParamList;
    }

    @Override
    public List<RawParam> findRawParamList(RawParamQuery rawParamQuery) {
        List<RawParamPo> rawParamPoList = rawParamDao.findRawParamList(rawParamQuery);

        List<RawParam> rawParamList = BeanMapper.mapList(rawParamPoList,RawParam.class);

        joinQuery.queryList(rawParamList);

        return rawParamList;
    }

    @Override
    public Pagination<List<RawParam>> findRawParamPage(RawParamQuery rawParamQuery) {
        Pagination<List<RawParam>> pg = new Pagination<>();

        Pagination<List<RawParamPo>>  pagination = rawParamDao.findRawParamPage(rawParamQuery);
        BeanUtils.copyProperties(pagination,pg);

        List<RawParam> rawParamList = BeanMapper.mapList(pagination.getDataList(),RawParam.class);

        joinQuery.queryList(rawParamList);

        pg.setDataList(rawParamList);
        return pg;
    }
}