package com.tiklab.postlink.apitest.http.httpcase.service;

import com.tiklab.postlink.apitest.http.httpcase.dao.AfterScriptCaseDao;
import com.tiklab.postlink.apitest.http.httpcase.entity.AfterScriptCaseEntity;
import com.tiklab.postlink.apitest.http.httpcase.model.AfterScriptCase;
import com.tiklab.postlink.apitest.http.httpcase.model.AfterScriptCaseQuery;

import com.tiklab.core.page.Pagination;
import com.tiklab.beans.BeanMapper;
import com.tiklab.core.page.PaginationBuilder;
import com.tiklab.join.JoinTemplate;
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