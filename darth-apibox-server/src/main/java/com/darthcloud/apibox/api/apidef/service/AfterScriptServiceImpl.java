package com.darthcloud.apibox.api.apidef.service;

import com.darthcloud.apibox.api.apidef.dao.AfterScriptDao;
import com.darthcloud.apibox.api.apidef.entity.AfterScriptPo;
import com.darthcloud.apibox.api.apidef.model.AfterScript;
import com.darthcloud.apibox.api.apidef.model.AfterScriptQuery;

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
public class AfterScriptServiceImpl implements AfterScriptService {

    @Autowired
    AfterScriptDao afterScriptDao;

    @Autowired
    JoinQuery joinQuery;

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
    public Pagination<List<AfterScript>> findAfterScriptPage(AfterScriptQuery afterScriptQuery) {
        Pagination<List<AfterScript>> pg = new Pagination<>();

        Pagination<List<AfterScriptPo>>  pagination = afterScriptDao.findAfterScriptPage(afterScriptQuery);
        BeanUtils.copyProperties(pagination,pg);

        List<AfterScript> afterScriptList = BeanMapper.mapList(pagination.getDataList(),AfterScript.class);

        joinQuery.queryList(afterScriptList);

        pg.setDataList(afterScriptList);
        return pg;
    }
}