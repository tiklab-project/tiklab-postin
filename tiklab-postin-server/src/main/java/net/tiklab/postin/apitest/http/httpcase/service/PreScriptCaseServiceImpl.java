package net.tiklab.postin.apitest.http.httpcase.service;

import net.tiklab.postin.apitest.http.httpcase.dao.PreScriptCaseDao;
import net.tiklab.postin.apitest.http.httpcase.entity.PreScriptCaseEntity;
import net.tiklab.postin.apitest.http.httpcase.model.PreScriptCase;
import net.tiklab.postin.apitest.http.httpcase.model.PreScriptCaseQuery;

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
public class PreScriptCaseServiceImpl implements PreScriptCaseService {

    @Autowired
    PreScriptCaseDao preScriptCaseDao;

    @Autowired
    JoinTemplate joinTemplate;

    @Override
    public String createPreScriptCase(@NotNull @Valid PreScriptCase preScriptCase) {
        PreScriptCaseEntity preScriptCaseEntity = BeanMapper.map(preScriptCase, PreScriptCaseEntity.class);

        return preScriptCaseDao.createPreScriptCase(preScriptCaseEntity);
    }

    @Override
    public void updatePreScriptCase(@NotNull @Valid PreScriptCase preScriptCase) {
        PreScriptCaseEntity preScriptCaseEntity = BeanMapper.map(preScriptCase, PreScriptCaseEntity.class);

        preScriptCaseDao.updatePreScriptCase(preScriptCaseEntity);
    }

    @Override
    public void deletePreScriptCase(@NotNull String id) {
        preScriptCaseDao.deletePreScriptCase(id);
    }

    @Override
    public PreScriptCase findOne(String id) {
        PreScriptCaseEntity preScriptCaseEntity = preScriptCaseDao.findPreScriptCase(id);

        PreScriptCase preScriptCase = BeanMapper.map(preScriptCaseEntity, PreScriptCase.class);
        return preScriptCase;
    }

    @Override
    public List<PreScriptCase> findList(List<String> idList) {
        List<PreScriptCaseEntity> preScriptCaseEntityList =  preScriptCaseDao.findPreScriptCaseList(idList);

        List<PreScriptCase> preScriptCaseList =  BeanMapper.mapList(preScriptCaseEntityList,PreScriptCase.class);
        return preScriptCaseList;
    }

    @Override
    public PreScriptCase findPreScriptCase(@NotNull String id) {
        PreScriptCase preScriptCase = findOne(id);

        joinTemplate.joinQuery(preScriptCase);
        return preScriptCase;
    }

    @Override
    public List<PreScriptCase> findAllPreScriptCase() {
        List<PreScriptCaseEntity> preScriptCaseEntityList =  preScriptCaseDao.findAllPreScriptCase();

        List<PreScriptCase> preScriptCaseList =  BeanMapper.mapList(preScriptCaseEntityList,PreScriptCase.class);

        joinTemplate.joinQuery(preScriptCaseList);
        return preScriptCaseList;
    }

    @Override
    public List<PreScriptCase> findPreScriptCaseList(PreScriptCaseQuery preScriptCaseQuery) {
        List<PreScriptCaseEntity> preScriptCaseEntityList = preScriptCaseDao.findPreScriptCaseList(preScriptCaseQuery);

        List<PreScriptCase> preScriptCaseList = BeanMapper.mapList(preScriptCaseEntityList,PreScriptCase.class);

        joinTemplate.joinQuery(preScriptCaseList);

        return preScriptCaseList;
    }

    @Override
    public Pagination<PreScriptCase> findPreScriptCasePage(PreScriptCaseQuery preScriptCaseQuery) {

        Pagination<PreScriptCaseEntity>  pagination = preScriptCaseDao.findPreScriptCasePage(preScriptCaseQuery);

        List<PreScriptCase> preScriptCaseList = BeanMapper.mapList(pagination.getDataList(),PreScriptCase.class);

        joinTemplate.joinQuery(preScriptCaseList);

        return PaginationBuilder.build(pagination,preScriptCaseList);
    }
}