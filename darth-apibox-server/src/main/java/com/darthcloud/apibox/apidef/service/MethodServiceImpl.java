package com.darthcloud.apibox.apidef.service;

import com.darthcloud.apibox.apidef.dao.MethodDao;
import com.darthcloud.apibox.apidef.entity.MethodPo;
import com.darthcloud.apibox.apidef.model.MethodEx;
import com.darthcloud.apibox.apidef.model.MethodExQuery;

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
public class MethodServiceImpl implements MethodService {

    @Autowired
    MethodDao methodDao;

    @Autowired
    JoinQuery joinQuery;

    @Override
    public String createMethod(@NotNull @Valid MethodEx methodEx) {
        MethodPo methodPo = BeanMapper.map(methodEx, MethodPo.class);

        return methodDao.createMethod(methodPo);
    }

    @Override
    public void updateMethod(@NotNull @Valid MethodEx methodEx) {
        MethodPo methodPo = BeanMapper.map(methodEx, MethodPo.class);

        methodDao.updateMethod(methodPo);
    }

    @Override
    public void deleteMethod(@NotNull String id) {
        methodDao.deleteMethod(id);
    }

    @Override
    public MethodEx findOne(String id) {
        MethodPo methodPo = methodDao.findMethod(id);

        MethodEx methodEx = BeanMapper.map(methodPo, MethodEx.class);
        return methodEx;
    }

    @Override
    public List<MethodEx> findList(List<String> idList) {
        List<MethodPo> methodPoList =  methodDao.findMethodList(idList);

        List<MethodEx> methodExList = BeanMapper.mapList(methodPoList, MethodEx.class);
        return methodExList;
    }

    @Override
    public MethodEx findMethod(@NotNull String id) {
        MethodEx methodEx = findOne(id);

        joinQuery.queryOne(methodEx);
        return methodEx;
    }

    @Override
    public List<MethodEx> findAllMethod() {
        List<MethodPo> methodPoList =  methodDao.findAllMethod();

        List<MethodEx> methodExList = BeanMapper.mapList(methodPoList, MethodEx.class);

        joinQuery.queryList(methodExList);

        return methodExList;
    }

    @Override
    public List<MethodEx> findMethodList(MethodExQuery methodExQuery) {
        List<MethodPo> methodPoList = methodDao.findMethodList(methodExQuery);

        List<MethodEx> methodExList = BeanMapper.mapList(methodPoList, MethodEx.class);

        joinQuery.queryList(methodExList);

        return methodExList;
    }

    @Override
    public Pagination<List<MethodEx>> findMethodPage(MethodExQuery methodExQuery) {
        Pagination<List<MethodEx>> pg = new Pagination<>();

        Pagination<List<MethodPo>>  pagination = methodDao.findMethodPage(methodExQuery);
        BeanUtils.copyProperties(pagination,pg);

        List<MethodEx> methodExList = BeanMapper.mapList(pagination.getDataList(), MethodEx.class);

        joinQuery.queryList(methodExList);

        pg.setDataList(methodExList);
        return pg;
    }
}