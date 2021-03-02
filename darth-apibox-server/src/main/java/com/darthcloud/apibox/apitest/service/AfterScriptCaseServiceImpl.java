package com.darthcloud.apibox.apitest.service;

import com.darthcloud.apibox.apitest.dao.AfterScriptCaseDao;
import com.darthcloud.apibox.apitest.entity.AfterScriptCasePo;
import com.darthcloud.apibox.apitest.model.AfterScriptCase;
import com.darthcloud.apibox.apitest.model.AfterScriptCaseQuery;

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
public class AfterScriptCaseServiceImpl implements AfterScriptCaseService {

    @Autowired
    AfterScriptCaseDao afterScriptCaseDao;

    @Autowired
    JoinQuery joinQuery;

    @Override
    public String createAfterScriptCase(@NotNull @Valid AfterScriptCase afterScriptCase) {
        AfterScriptCasePo afterScriptCasePo = BeanMapper.map(afterScriptCase, AfterScriptCasePo.class);

        return afterScriptCaseDao.createAfterScriptCase(afterScriptCasePo);
    }

    @Override
    public void updateAfterScriptCase(@NotNull @Valid AfterScriptCase afterScriptCase) {
        AfterScriptCasePo afterScriptCasePo = BeanMapper.map(afterScriptCase, AfterScriptCasePo.class);

        afterScriptCaseDao.updateAfterScriptCase(afterScriptCasePo);
    }

    @Override
    public void deleteAfterScriptCase(@NotNull String id) {
        afterScriptCaseDao.deleteAfterScriptCase(id);
    }

    @Override
    public AfterScriptCase findOne(String id) {
        AfterScriptCasePo afterScriptCasePo = afterScriptCaseDao.findAfterScriptCase(id);

        AfterScriptCase afterScriptCase = BeanMapper.map(afterScriptCasePo, AfterScriptCase.class);
        return afterScriptCase;
    }

    @Override
    public List<AfterScriptCase> findList(List<String> idList) {
        List<AfterScriptCasePo> afterScriptCasePoList =  afterScriptCaseDao.findAfterScriptCaseList(idList);

        List<AfterScriptCase> afterScriptCaseList =  BeanMapper.mapList(afterScriptCasePoList,AfterScriptCase.class);
        return afterScriptCaseList;
    }

    @Override
    public AfterScriptCase findAfterScriptCase(@NotNull String id) {
        AfterScriptCase afterScriptCase = findOne(id);

        joinQuery.queryOne(afterScriptCase);
        return afterScriptCase;
    }

    @Override
    public List<AfterScriptCase> findAllAfterScriptCase() {
        List<AfterScriptCasePo> afterScriptCasePoList =  afterScriptCaseDao.findAllAfterScriptCase();

        List<AfterScriptCase> afterScriptCaseList =  BeanMapper.mapList(afterScriptCasePoList,AfterScriptCase.class);

        joinQuery.queryList(afterScriptCaseList);
        return afterScriptCaseList;
    }

    @Override
    public List<AfterScriptCase> findAfterScriptCaseList(AfterScriptCaseQuery afterScriptCaseQuery) {
        List<AfterScriptCasePo> afterScriptCasePoList = afterScriptCaseDao.findAfterScriptCaseList(afterScriptCaseQuery);

        List<AfterScriptCase> afterScriptCaseList = BeanMapper.mapList(afterScriptCasePoList,AfterScriptCase.class);

        joinQuery.queryList(afterScriptCaseList);

        return afterScriptCaseList;
    }

    @Override
    public Pagination<List<AfterScriptCase>> findAfterScriptCasePage(AfterScriptCaseQuery afterScriptCaseQuery) {
        Pagination<List<AfterScriptCase>> pg = new Pagination<>();

        Pagination<List<AfterScriptCasePo>>  pagination = afterScriptCaseDao.findAfterScriptCasePage(afterScriptCaseQuery);
        BeanUtils.copyProperties(pagination,pg);

        List<AfterScriptCase> afterScriptCaseList = BeanMapper.mapList(pagination.getDataList(),AfterScriptCase.class);

        joinQuery.queryList(afterScriptCaseList);

        pg.setDataList(afterScriptCaseList);
        return pg;
    }
}