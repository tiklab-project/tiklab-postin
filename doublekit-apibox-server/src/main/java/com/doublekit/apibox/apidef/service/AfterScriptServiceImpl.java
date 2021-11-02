package com.doublekit.apibox.apidef.service;

import com.doublekit.apibox.apidef.dao.AfterScriptDao;
import com.doublekit.apibox.apidef.entity.AfterScriptEntity;
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
        AfterScriptEntity afterScriptEntity = BeanMapper.map(afterScript, AfterScriptEntity.class);

        return afterScriptDao.createAfterScript(afterScriptEntity);
    }

    @Override
    public void updateAfterScript(@NotNull @Valid AfterScript afterScript) {
        AfterScriptEntity afterScriptEntity = BeanMapper.map(afterScript, AfterScriptEntity.class);

        afterScriptDao.updateAfterScript(afterScriptEntity);
    }

    @Override
    public void deleteAfterScript(@NotNull String id) {
        afterScriptDao.deleteAfterScript(id);
    }

    @Override
    public AfterScript findAfterScript(@NotNull String id) {
        AfterScriptEntity afterScriptEntity = afterScriptDao.findAfterScript(id);

        AfterScript afterScript = BeanMapper.map(afterScriptEntity, AfterScript.class);

        joinQuery.queryOne(afterScript);

        return afterScript;
    }

    @Override
    public List<AfterScript> findAllAfterScript() {
        List<AfterScriptEntity> afterScriptEntityList =  afterScriptDao.findAllAfterScript();

        List<AfterScript> afterScriptList =  BeanMapper.mapList(afterScriptEntityList,AfterScript.class);

        joinQuery.queryList(afterScriptList);

        return afterScriptList;
    }

    @Override
    public List<AfterScript> findAfterScriptList(AfterScriptQuery afterScriptQuery) {
        List<AfterScriptEntity> afterScriptEntityList = afterScriptDao.findAfterScriptList(afterScriptQuery);

        List<AfterScript> afterScriptList = BeanMapper.mapList(afterScriptEntityList,AfterScript.class);

        joinQuery.queryList(afterScriptList);

        return afterScriptList;
    }

    @Override
    public Pagination<AfterScript> findAfterScriptPage(AfterScriptQuery afterScriptQuery) {
        Pagination<AfterScript> pg = new Pagination<>();

        Pagination<AfterScriptEntity>  pagination = afterScriptDao.findAfterScriptPage(afterScriptQuery);
        BeanUtils.copyProperties(pagination,pg);

        List<AfterScript> afterScriptList = BeanMapper.mapList(pagination.getDataList(),AfterScript.class);

        joinQuery.queryList(afterScriptList);

        pg.setDataList(afterScriptList);
        return pg;
    }
}