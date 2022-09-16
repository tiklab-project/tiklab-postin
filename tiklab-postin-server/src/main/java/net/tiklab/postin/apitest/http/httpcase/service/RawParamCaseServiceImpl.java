package net.tiklab.postin.apitest.http.httpcase.service;

import net.tiklab.postin.apitest.http.httpcase.dao.RawParamCaseDao;
import net.tiklab.postin.apitest.http.httpcase.entity.RawParamCaseEntity;
import net.tiklab.postin.apitest.http.httpcase.model.RawParamCase;
import net.tiklab.postin.apitest.http.httpcase.model.RawParamCaseQuery;

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
public class RawParamCaseServiceImpl implements RawParamCaseService {

    @Autowired
    RawParamCaseDao rawParamCaseDao;

    @Autowired
    JoinTemplate joinTemplate;

    @Override
    public String createRawParamCase(@NotNull @Valid RawParamCase rawParamCase) {
        RawParamCaseEntity rawParamCaseEntity = BeanMapper.map(rawParamCase, RawParamCaseEntity.class);

        return rawParamCaseDao.createRawParamCase(rawParamCaseEntity);
    }

    @Override
    public void updateRawParamCase(@NotNull @Valid RawParamCase rawParamCase) {
        RawParamCaseEntity rawParamCaseEntity = BeanMapper.map(rawParamCase, RawParamCaseEntity.class);

        rawParamCaseDao.updateRawParamCase(rawParamCaseEntity);
    }

    @Override
    public void deleteRawParamCase(@NotNull String id) {
        rawParamCaseDao.deleteRawParamCase(id);
    }

    @Override
    public RawParamCase findOne(String id) {
        RawParamCaseEntity rawParamCaseEntity = rawParamCaseDao.findRawParamCase(id);

        RawParamCase rawParamCase = BeanMapper.map(rawParamCaseEntity, RawParamCase.class);
        return rawParamCase;
    }

    @Override
    public List<RawParamCase> findList(List<String> idList) {
        List<RawParamCaseEntity> rawParamCaseEntityList =  rawParamCaseDao.findRawParamCaseList(idList);

        List<RawParamCase> rawParamCaseList =  BeanMapper.mapList(rawParamCaseEntityList,RawParamCase.class);
        return rawParamCaseList;
    }

    @Override
    public RawParamCase findRawParamCase(@NotNull String id) {
        RawParamCase rawParamCase = findOne(id);

        joinTemplate.joinQuery(rawParamCase);
        return rawParamCase;
    }

    @Override
    public List<RawParamCase> findAllRawParamCase() {
        List<RawParamCaseEntity> rawParamCaseEntityList =  rawParamCaseDao.findAllRawParamCase();

        List<RawParamCase> rawParamCaseList =  BeanMapper.mapList(rawParamCaseEntityList,RawParamCase.class);

        joinTemplate.joinQuery(rawParamCaseList);
        return rawParamCaseList;
    }

    @Override
    public List<RawParamCase> findRawParamCaseList(RawParamCaseQuery rawParamCaseQuery) {
        List<RawParamCaseEntity> rawParamCaseEntityList = rawParamCaseDao.findRawParamCaseList(rawParamCaseQuery);

        List<RawParamCase> rawParamCaseList = BeanMapper.mapList(rawParamCaseEntityList,RawParamCase.class);

        joinTemplate.joinQuery(rawParamCaseList);

        return rawParamCaseList;
    }

    @Override
    public Pagination<RawParamCase> findRawParamCasePage(RawParamCaseQuery rawParamCaseQuery) {

        Pagination<RawParamCaseEntity>  pagination = rawParamCaseDao.findRawParamCasePage(rawParamCaseQuery);

        List<RawParamCase> rawParamCaseList = BeanMapper.mapList(pagination.getDataList(),RawParamCase.class);

        joinTemplate.joinQuery(rawParamCaseList);

        return PaginationBuilder.build(pagination,rawParamCaseList);
    }
}