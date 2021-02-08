package com.darthcloud.apibox.api.apidef.service;

import com.darthcloud.apibox.api.apidef.dao.ApxMethodDao;
import com.darthcloud.apibox.api.apidef.entity.ApxMethodPo;
import com.darthcloud.apibox.api.apidef.model.ApxMethod;
import com.darthcloud.apibox.api.apidef.model.ApxMethodQuery;

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
public class ApxMethodServiceImpl implements ApxMethodService {

    @Autowired
    ApxMethodDao apxMethodDao;

    @Autowired
    JoinQuery joinQuery;

    @Override
    public String createApxMethod(@NotNull @Valid ApxMethod apxMethod) {
        ApxMethodPo apxMethodPo = BeanMapper.map(apxMethod, ApxMethodPo.class);

        return apxMethodDao.createApxMethod(apxMethodPo);
    }

    @Override
    public void updateApxMethod(@NotNull @Valid ApxMethod apxMethod) {
        ApxMethodPo apxMethodPo = BeanMapper.map(apxMethod, ApxMethodPo.class);

        apxMethodDao.updateApxMethod(apxMethodPo);
    }

    @Override
    public void deleteApxMethod(@NotNull String id) {
        apxMethodDao.deleteApxMethod(id);
    }

    @Override
    public ApxMethod findApxMethod(@NotNull String id) {
        ApxMethodPo apxMethodPo = apxMethodDao.findApxMethod(id);

        ApxMethod apxMethod = BeanMapper.map(apxMethodPo, ApxMethod.class);

        joinQuery.queryOne(apxMethod);

        return apxMethod;
    }

    @Override
    public List<ApxMethod> findAllApxMethod() {
        List<ApxMethodPo> apxMethodPoList =  apxMethodDao.findAllApxMethod();

        List<ApxMethod> apxMethodList = BeanMapper.mapList(apxMethodPoList,ApxMethod.class);

        joinQuery.queryList(apxMethodList);

        return apxMethodList;
    }

    @Override
    public List<ApxMethod> findApxMethodList(ApxMethodQuery apxMethodQuery) {
        List<ApxMethodPo> apxMethodPoList = apxMethodDao.findApxMethodList(apxMethodQuery);

        List<ApxMethod> apxMethodList = BeanMapper.mapList(apxMethodPoList,ApxMethod.class);

        joinQuery.queryList(apxMethodList);

        return apxMethodList;
    }

    @Override
    public Pagination<List<ApxMethod>> findApxMethodPage(ApxMethodQuery apxMethodQuery) {
        Pagination<List<ApxMethod>> pg = new Pagination<>();

        Pagination<List<ApxMethodPo>>  pagination = apxMethodDao.findApxMethodPage(apxMethodQuery);
        BeanUtils.copyProperties(pagination,pg);

        List<ApxMethod> apxMethodList = BeanMapper.mapList(pagination.getDataList(),ApxMethod.class);

        joinQuery.queryList(apxMethodList);

        pg.setDataList(apxMethodList);
        return pg;
    }
}