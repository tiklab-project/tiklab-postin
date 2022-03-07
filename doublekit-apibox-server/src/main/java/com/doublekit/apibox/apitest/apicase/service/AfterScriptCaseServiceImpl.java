package com.doublekit.apibox.apitest.apicase.service;

import com.doublekit.apibox.apitest.apicase.dao.AfterScriptCaseDao;
import com.doublekit.apibox.apitest.apicase.entity.AfterScriptCaseEntity;
import com.doublekit.apibox.apitest.apicase.model.AfterScriptCase;
import com.doublekit.apibox.apitest.apicase.model.AfterScriptCaseQuery;

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
public class AfterScriptCaseServiceImpl implements AfterScriptCaseService {

    @Autowired
    AfterScriptCaseDao afterScriptCaseDao;

    @Autowired
    JoinTemplate joinTemplate;

    @Override
    public String createAfterScriptCase(@NotNull @Valid AfterScriptCase afterScriptCase) {
        AfterScriptCaseEntity afterScriptCaseEntity = BeanMapper.map(afterScriptCase, AfterScriptCaseEntity.class);

        return afterScriptCaseDao.createAfterScriptCase(afterScriptCaseEntity);
    }

    @Override
    public void updateAfterScriptCase(@NotNull @Valid AfterScriptCase afterScriptCase) {
        AfterScriptCaseEntity afterScriptCaseEntity = BeanMapper.map(afterScriptCase, AfterScriptCaseEntity.class);

        afterScriptCaseDao.updateAfterScriptCase(afterScriptCaseEntity);
    }

    @Override
    public void deleteAfterScriptCase(@NotNull String id) {
        afterScriptCaseDao.deleteAfterScriptCase(id);
    }

    @Override
    public AfterScriptCase findOne(String id) {
        AfterScriptCaseEntity afterScriptCaseEntity = afterScriptCaseDao.findAfterScriptCase(id);

        AfterScriptCase afterScriptCase = BeanMapper.map(afterScriptCaseEntity, AfterScriptCase.class);
        return afterScriptCase;
    }

    @Override
    public List<AfterScriptCase> findList(List<String> idList) {
        List<AfterScriptCaseEntity> afterScriptCaseEntityList =  afterScriptCaseDao.findAfterScriptCaseList(idList);

        List<AfterScriptCase> afterScriptCaseList =  BeanMapper.mapList(afterScriptCaseEntityList,AfterScriptCase.class);
        return afterScriptCaseList;
    }

    @Override
    public AfterScriptCase findAfterScriptCase(@NotNull String id) {
        AfterScriptCase afterScriptCase = findOne(id);

        joinTemplate.joinQuery(afterScriptCase);
        return afterScriptCase;
    }

    @Override
    public List<AfterScriptCase> findAllAfterScriptCase() {
        List<AfterScriptCaseEntity> afterScriptCaseEntityList =  afterScriptCaseDao.findAllAfterScriptCase();

        List<AfterScriptCase> afterScriptCaseList =  BeanMapper.mapList(afterScriptCaseEntityList,AfterScriptCase.class);

        joinTemplate.joinQuery(afterScriptCaseList);
        return afterScriptCaseList;
    }

    @Override
    public List<AfterScriptCase> findAfterScriptCaseList(AfterScriptCaseQuery afterScriptCaseQuery) {
        List<AfterScriptCaseEntity> afterScriptCaseEntityList = afterScriptCaseDao.findAfterScriptCaseList(afterScriptCaseQuery);

        List<AfterScriptCase> afterScriptCaseList = BeanMapper.mapList(afterScriptCaseEntityList,AfterScriptCase.class);

        joinTemplate.joinQuery(afterScriptCaseList);

        return afterScriptCaseList;
    }

    @Override
    public Pagination<AfterScriptCase> findAfterScriptCasePage(AfterScriptCaseQuery afterScriptCaseQuery) {

        Pagination<AfterScriptCaseEntity>  pagination = afterScriptCaseDao.findAfterScriptCasePage(afterScriptCaseQuery);

        List<AfterScriptCase> afterScriptCaseList = BeanMapper.mapList(pagination.getDataList(),AfterScriptCase.class);

        joinTemplate.joinQuery(afterScriptCaseList);

        return PaginationBuilder.build(pagination,afterScriptCaseList);
    }
}