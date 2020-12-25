package com.darthcloud.apibox.apxmethod.service;

import com.darthcloud.apibox.apxmethod.dao.ApxMethodDao;
import com.darthcloud.apibox.apxmethod.entity.ApxMethodPo;
import com.darthcloud.apibox.apxmethod.model.ApxMethod;
import com.darthcloud.apibox.apxmethod.model.ApxMethodQuery;

import com.darthcloud.common.Pagination;
import com.darthcloud.beans.BeanMapper;
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

        return BeanMapper.map(apxMethodPo, ApxMethod.class);
    }

    @Override
    public List<ApxMethod> findAllApxMethod() {
        List<ApxMethodPo> apxMethodPoList =  apxMethodDao.findAllApxMethod();

        return BeanMapper.mapList(apxMethodPoList,ApxMethod.class);
    }

    @Override
    public List<ApxMethod> findApxMethodList(ApxMethodQuery apxMethodQuery) {
        List<ApxMethodPo> apxMethodPoList = apxMethodDao.findApxMethodList(apxMethodQuery);

        return BeanMapper.mapList(apxMethodPoList,ApxMethod.class);
    }

    @Override
    public Pagination<List<ApxMethod>> findApxMethodPage(ApxMethodQuery apxMethodQuery) {
        Pagination<List<ApxMethod>> pg = new Pagination<>();

        Pagination<List<ApxMethodPo>>  pagination = apxMethodDao.findApxMethodPage(apxMethodQuery);
        BeanUtils.copyProperties(pagination,pg);

        List<ApxMethod> apxMethodList = BeanMapper.mapList(pagination.getDataList(),ApxMethod.class);
        pg.setDataList(apxMethodList);
        return pg;
    }
}