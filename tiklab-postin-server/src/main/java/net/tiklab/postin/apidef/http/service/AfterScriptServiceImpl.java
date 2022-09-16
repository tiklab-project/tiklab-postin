package net.tiklab.postin.apidef.http.service;

import net.tiklab.postin.apidef.http.dao.AfterScriptDao;
import net.tiklab.postin.apidef.http.entity.AfterScriptEntity;
import net.tiklab.postin.apidef.http.model.AfterScript;
import net.tiklab.postin.apidef.http.model.AfterScriptQuery;

import net.tiklab.core.page.Pagination;
import net.tiklab.beans.BeanMapper;
import net.tiklab.core.page.PaginationBuilder;
import net.tiklab.join.JoinTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 用户服务业务处理
*/
@Service
public class AfterScriptServiceImpl implements AfterScriptService {

    @Autowired
    AfterScriptDao afterScriptDao;

    @Autowired
    JoinTemplate joinTemplate;

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

        joinTemplate.joinQuery(afterScript);

        return afterScript;
    }

    @Override
    public List<AfterScript> findAllAfterScript() {
        List<AfterScriptEntity> afterScriptEntityList =  afterScriptDao.findAllAfterScript();

        List<AfterScript> afterScriptList =  BeanMapper.mapList(afterScriptEntityList,AfterScript.class);

        joinTemplate.joinQuery(afterScriptList);

        return afterScriptList;
    }

    @Override
    public List<AfterScript> findAfterScriptList(AfterScriptQuery afterScriptQuery) {
        List<AfterScriptEntity> afterScriptEntityList = afterScriptDao.findAfterScriptList(afterScriptQuery);

        List<AfterScript> afterScriptList = BeanMapper.mapList(afterScriptEntityList,AfterScript.class);

        joinTemplate.joinQuery(afterScriptList);

        return afterScriptList;
    }

    @Override
    public Pagination<AfterScript> findAfterScriptPage(AfterScriptQuery afterScriptQuery) {

        Pagination<AfterScriptEntity>  pagination = afterScriptDao.findAfterScriptPage(afterScriptQuery);

        List<AfterScript> afterScriptList = BeanMapper.mapList(pagination.getDataList(),AfterScript.class);

        joinTemplate.joinQuery(afterScriptList);

        return PaginationBuilder.build(pagination,afterScriptList);
    }
}