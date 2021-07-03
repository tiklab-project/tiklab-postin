package com.doublekit.apibox.apidef.service;

import com.doublekit.apibox.apidef.dao.PreScriptDao;
import com.doublekit.apibox.apidef.entity.PreScriptPo;
import com.doublekit.apibox.apidef.model.PreScript;
import com.doublekit.apibox.apidef.model.PreScriptQuery;

import com.doublekit.common.Pagination;
import com.doublekit.beans.BeanMapper;
import com.doublekit.join.join.JoinQuery;
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
public class PreScriptServiceImpl implements PreScriptService {

    @Autowired
    PreScriptDao preScriptDao;

    @Autowired
    JoinQuery joinQuery;

    @Override
    public String createPreScript(@NotNull @Valid PreScript preScript) {
        PreScriptPo preScriptPo = BeanMapper.map(preScript, PreScriptPo.class);

        return preScriptDao.createPreScript(preScriptPo);
    }

    @Override
    public void updatePreScript(@NotNull @Valid PreScript preScript) {
        PreScriptPo preScriptPo = BeanMapper.map(preScript, PreScriptPo.class);

        preScriptDao.updatePreScript(preScriptPo);
    }

    @Override
    public void deletePreScript(@NotNull String id) {
        preScriptDao.deletePreScript(id);
    }

    @Override
    public PreScript findPreScript(@NotNull String id) {
        PreScriptPo preScriptPo = preScriptDao.findPreScript(id);

        PreScript preScript = BeanMapper.map(preScriptPo, PreScript.class);

        joinQuery.queryOne(preScript);

        return preScript;
    }

    @Override
    public List<PreScript> findAllPreScript() {
        List<PreScriptPo> preScriptPoList =  preScriptDao.findAllPreScript();

        List<PreScript> preScriptList =  BeanMapper.mapList(preScriptPoList,PreScript.class);

        joinQuery.queryList(preScriptList);

        return preScriptList;
    }

    @Override
    public List<PreScript> findPreScriptList(PreScriptQuery preScriptQuery) {
        List<PreScriptPo> preScriptPoList = preScriptDao.findPreScriptList(preScriptQuery);

        List<PreScript> preScriptList = BeanMapper.mapList(preScriptPoList,PreScript.class);

        joinQuery.queryList(preScriptList);

        return preScriptList;
    }

    @Override
    public Pagination<PreScript> findPreScriptPage(PreScriptQuery preScriptQuery) {
        Pagination<PreScript> pg = new Pagination<>();

        Pagination<PreScriptPo>  pagination = preScriptDao.findPreScriptPage(preScriptQuery);
        BeanUtils.copyProperties(pagination,pg);

        List<PreScript> preScriptList = BeanMapper.mapList(pagination.getDataList(),PreScript.class);

        joinQuery.queryList(preScriptList);

        pg.setDataList(preScriptList);
        return pg;
    }
}