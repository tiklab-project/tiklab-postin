package com.doublekit.apibox.datastructure.service;

import com.doublekit.apibox.datastructure.dao.EnumParamDao;
import com.doublekit.apibox.datastructure.entity.EnumParamPo;
import com.doublekit.apibox.datastructure.model.EnumParam;
import com.doublekit.apibox.datastructure.model.EnumParamQuery;

import com.doublekit.common.Pagination;
import com.doublekit.beans.BeanMapper;
import com.doublekit.join.JoinTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

import org.springframework.beans.BeanUtils;

/**
* EnumParamServiceImpl
*/

@Service
public class EnumParamServiceImpl implements EnumParamService {

    @Autowired
    EnumParamDao enumParamDao;

    @Autowired
    JoinTemplate joinQuery;

    @Override
    public String createEnumParam(@NotNull @Valid EnumParam enumParam) {
        EnumParamPo enumParamPo = BeanMapper.map(enumParam, EnumParamPo.class);

        return enumParamDao.createEnumParam(enumParamPo);
    }

    @Override
    public void updateEnumParam(@NotNull @Valid EnumParam enumParam) {
        EnumParamPo enumParamPo = BeanMapper.map(enumParam, EnumParamPo.class);

        enumParamDao.updateEnumParam(enumParamPo);
    }

    @Override
    public void deleteEnumParam(@NotNull String id) {
        enumParamDao.deleteEnumParam(id);
    }

    @Override
    public EnumParam findOne(String id) {
        EnumParamPo enumParamPo = enumParamDao.findEnumParam(id);

        EnumParam enumParam = BeanMapper.map(enumParamPo, EnumParam.class);
        return enumParam;
    }

    @Override
    public List<EnumParam> findList(List<String> idList) {
        List<EnumParamPo> enumParamPoList =  enumParamDao.findEnumParamList(idList);

        List<EnumParam> enumParamList =  BeanMapper.mapList(enumParamPoList,EnumParam.class);
        return enumParamList;
    }

    @Override
    public EnumParam findEnumParam(@NotNull String id) {
        EnumParam enumParam = findOne(id);

        joinQuery.queryOne(enumParam);
        return enumParam;
    }

    @Override
    public List<EnumParam> findAllEnumParam() {
        List<EnumParamPo> enumParamPoList =  enumParamDao.findAllEnumParam();

        List<EnumParam> enumParamList =  BeanMapper.mapList(enumParamPoList,EnumParam.class);

        joinQuery.queryList(enumParamList);
        return enumParamList;
    }

    @Override
    public List<EnumParam> findEnumParamList(EnumParamQuery enumParamQuery) {
        List<EnumParamPo> enumParamPoList = enumParamDao.findEnumParamList(enumParamQuery);

        List<EnumParam> enumParamList = BeanMapper.mapList(enumParamPoList,EnumParam.class);

        joinQuery.queryList(enumParamList);

        return enumParamList;
    }

    @Override
    public Pagination<EnumParam> findEnumParamPage(EnumParamQuery enumParamQuery) {
        Pagination<EnumParam> pg = new Pagination<>();

        Pagination<EnumParamPo>  pagination = enumParamDao.findEnumParamPage(enumParamQuery);
        BeanUtils.copyProperties(pagination,pg);

        List<EnumParam> enumParamList = BeanMapper.mapList(pagination.getDataList(),EnumParam.class);

        joinQuery.queryList(enumParamList);

        pg.setDataList(enumParamList);
        return pg;
    }
}