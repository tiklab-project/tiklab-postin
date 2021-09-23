package com.doublekit.apibox.apidef.service;

import com.doublekit.apibox.apidef.dao.AfterScriptDao;
import com.doublekit.apibox.apidef.entity.AfterScriptPo;
import com.doublekit.apibox.apidef.model.AfterScript;
import com.doublekit.apibox.apidef.model.AfterScriptQuery;

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
public class AfterScriptServiceImpl implements AfterScriptService {

    @Autowired
    AfterScriptDao afterScriptDao;

    @Autowired
    JoinTemplate joinQuery;

    @Override
    public String createAfterScript(@NotNull @Valid AfterScript afterScript) {
        AfterScriptPo afterScriptPo = BeanMapper.map(afterScript, AfterScriptPo.class);

        return afterScriptDao.createAfterScript(afterScriptPo);
    }

    @Override
    public void updateAfterScript(@NotNull @Valid AfterScript afterScript) {
        AfterScriptPo afterScriptPo = BeanMapper.map(afterScript, AfterScriptPo.class);

        afterScriptDao.updateAfterScript(afterScriptPo);
    }

    @Override
    public void deleteAfterScript(@NotNull String id) {
        afterScriptDao.deleteAfterScript(id);
    }

    @Override
    public AfterScript findAfterScript(@NotNull String id) {
        AfterScriptPo afterScriptPo = afterScriptDao.findAfterScript(id);

        AfterScript afterScript = BeanMapper.map(afterScriptPo, AfterScript.class);

        joinQuery.queryOne(afterScript);

        return afterScript;
    }

    @Override
    public List<AfterScript> findAllAfterScript() {
        List<AfterScriptPo> afterScriptPoList =  afterScriptDao.findAllAfterScript();

        List<AfterScript> afterScriptList =  BeanMapper.mapList(afterScriptPoList,AfterScript.class);

        joinQuery.queryList(afterScriptList);

        return afterScriptList;
    }

    @Override
    public List<AfterScript> findAfterScriptList(AfterScriptQuery afterScriptQuery) {
        List<AfterScriptPo> afterScriptPoList = afterScriptDao.findAfterScriptList(afterScriptQuery);

        List<AfterScript> afterScriptList = BeanMapper.mapList(afterScriptPoList,AfterScript.class);

        joinQuery.queryList(afterScriptList);

        return afterScriptList;
    }

    @Override
    public Pagination<AfterScript> findAfterScriptPage(AfterScriptQuery afterScriptQuery) {
        Pagination<AfterScript> pg = new Pagination<>();

        Pagination<AfterScriptPo>  pagination = afterScriptDao.findAfterScriptPage(afterScriptQuery);
        BeanUtils.copyProperties(pagination,pg);

        List<AfterScript> afterScriptList = BeanMapper.mapList(pagination.getDataList(),AfterScript.class);

        joinQuery.queryList(afterScriptList);

        pg.setDataList(afterScriptList);
        return pg;
    }
}