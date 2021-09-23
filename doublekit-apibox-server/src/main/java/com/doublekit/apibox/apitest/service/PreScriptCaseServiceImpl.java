package com.doublekit.apibox.apitest.service;

import com.doublekit.apibox.apitest.dao.PreScriptCaseDao;
import com.doublekit.apibox.apitest.entity.PreScriptCasePo;
import com.doublekit.apibox.apitest.model.PreScriptCase;
import com.doublekit.apibox.apitest.model.PreScriptCaseQuery;

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
public class PreScriptCaseServiceImpl implements PreScriptCaseService {

    @Autowired
    PreScriptCaseDao preScriptCaseDao;

    @Autowired
    JoinTemplate joinQuery;

    @Override
    public String createPreScriptCase(@NotNull @Valid PreScriptCase preScriptCase) {
        PreScriptCasePo preScriptCasePo = BeanMapper.map(preScriptCase, PreScriptCasePo.class);

        return preScriptCaseDao.createPreScriptCase(preScriptCasePo);
    }

    @Override
    public void updatePreScriptCase(@NotNull @Valid PreScriptCase preScriptCase) {
        PreScriptCasePo preScriptCasePo = BeanMapper.map(preScriptCase, PreScriptCasePo.class);

        preScriptCaseDao.updatePreScriptCase(preScriptCasePo);
    }

    @Override
    public void deletePreScriptCase(@NotNull String id) {
        preScriptCaseDao.deletePreScriptCase(id);
    }

    @Override
    public PreScriptCase findOne(String id) {
        PreScriptCasePo preScriptCasePo = preScriptCaseDao.findPreScriptCase(id);

        PreScriptCase preScriptCase = BeanMapper.map(preScriptCasePo, PreScriptCase.class);
        return preScriptCase;
    }

    @Override
    public List<PreScriptCase> findList(List<String> idList) {
        List<PreScriptCasePo> preScriptCasePoList =  preScriptCaseDao.findPreScriptCaseList(idList);

        List<PreScriptCase> preScriptCaseList =  BeanMapper.mapList(preScriptCasePoList,PreScriptCase.class);
        return preScriptCaseList;
    }

    @Override
    public PreScriptCase findPreScriptCase(@NotNull String id) {
        PreScriptCase preScriptCase = findOne(id);

        joinQuery.queryOne(preScriptCase);
        return preScriptCase;
    }

    @Override
    public List<PreScriptCase> findAllPreScriptCase() {
        List<PreScriptCasePo> preScriptCasePoList =  preScriptCaseDao.findAllPreScriptCase();

        List<PreScriptCase> preScriptCaseList =  BeanMapper.mapList(preScriptCasePoList,PreScriptCase.class);

        joinQuery.queryList(preScriptCaseList);
        return preScriptCaseList;
    }

    @Override
    public List<PreScriptCase> findPreScriptCaseList(PreScriptCaseQuery preScriptCaseQuery) {
        List<PreScriptCasePo> preScriptCasePoList = preScriptCaseDao.findPreScriptCaseList(preScriptCaseQuery);

        List<PreScriptCase> preScriptCaseList = BeanMapper.mapList(preScriptCasePoList,PreScriptCase.class);

        joinQuery.queryList(preScriptCaseList);

        return preScriptCaseList;
    }

    @Override
    public Pagination<PreScriptCase> findPreScriptCasePage(PreScriptCaseQuery preScriptCaseQuery) {
        Pagination<PreScriptCase> pg = new Pagination<>();

        Pagination<PreScriptCasePo>  pagination = preScriptCaseDao.findPreScriptCasePage(preScriptCaseQuery);
        BeanUtils.copyProperties(pagination,pg);

        List<PreScriptCase> preScriptCaseList = BeanMapper.mapList(pagination.getDataList(),PreScriptCase.class);

        joinQuery.queryList(preScriptCaseList);

        pg.setDataList(preScriptCaseList);
        return pg;
    }
}