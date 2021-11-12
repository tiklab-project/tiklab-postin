package com.doublekit.apibox.apidef.service;

import com.doublekit.apibox.apidef.dao.PreScriptDao;
import com.doublekit.apibox.apidef.entity.PreScriptEntity;
import com.doublekit.apibox.apidef.model.PreScript;
import com.doublekit.apibox.apidef.model.PreScriptQuery;

import com.doublekit.common.page.Pagination;
import com.doublekit.beans.BeanMapper;
import com.doublekit.common.page.PaginationBuilder;
import com.doublekit.join.JoinTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 用户服务业务处理
*/
@Service
public class PreScriptServiceImpl implements PreScriptService {

    @Autowired
    PreScriptDao preScriptDao;

    @Autowired
    JoinTemplate joinQuery;

    @Override
    public String createPreScript(@NotNull @Valid PreScript preScript) {
        PreScriptEntity preScriptEntity = BeanMapper.map(preScript, PreScriptEntity.class);

        return preScriptDao.createPreScript(preScriptEntity);
    }

    @Override
    public void updatePreScript(@NotNull @Valid PreScript preScript) {
        PreScriptEntity preScriptEntity = BeanMapper.map(preScript, PreScriptEntity.class);

        preScriptDao.updatePreScript(preScriptEntity);
    }

    @Override
    public void deletePreScript(@NotNull String id) {
        preScriptDao.deletePreScript(id);
    }

    @Override
    public PreScript findPreScript(@NotNull String id) {
        PreScriptEntity preScriptEntity = preScriptDao.findPreScript(id);

        PreScript preScript = BeanMapper.map(preScriptEntity, PreScript.class);

        joinQuery.queryOne(preScript);

        return preScript;
    }

    @Override
    public List<PreScript> findAllPreScript() {
        List<PreScriptEntity> preScriptEntityList =  preScriptDao.findAllPreScript();

        List<PreScript> preScriptList =  BeanMapper.mapList(preScriptEntityList,PreScript.class);

        joinQuery.queryList(preScriptList);

        return preScriptList;
    }

    @Override
    public List<PreScript> findPreScriptList(PreScriptQuery preScriptQuery) {
        List<PreScriptEntity> preScriptEntityList = preScriptDao.findPreScriptList(preScriptQuery);

        List<PreScript> preScriptList = BeanMapper.mapList(preScriptEntityList,PreScript.class);

        joinQuery.queryList(preScriptList);

        return preScriptList;
    }

    @Override
    public Pagination<PreScript> findPreScriptPage(PreScriptQuery preScriptQuery) {

        Pagination<PreScriptEntity>  pagination = preScriptDao.findPreScriptPage(preScriptQuery);

        List<PreScript> preScriptList = BeanMapper.mapList(pagination.getDataList(),PreScript.class);

        joinQuery.queryList(preScriptList);

        return PaginationBuilder.build(pagination,preScriptList);
    }
}