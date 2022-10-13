package net.tiklab.postin.sysmgr.datastructure.service;

import net.tiklab.postin.sysmgr.datastructure.dao.EnumParamDao;
import net.tiklab.postin.sysmgr.datastructure.entity.EnumParamEntity;
import net.tiklab.postin.sysmgr.datastructure.model.EnumParam;
import net.tiklab.postin.sysmgr.datastructure.model.EnumParamQuery;

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
* EnumParamServiceImpl
*/

@Service
public class EnumParamServiceImpl implements EnumParamService {

    @Autowired
    EnumParamDao enumParamDao;

    @Autowired
    JoinTemplate joinTemplate;

    @Override
    public String createEnumParam(@NotNull @Valid EnumParam enumParam) {
        EnumParamEntity enumParamEntity = BeanMapper.map(enumParam, EnumParamEntity.class);

        return enumParamDao.createEnumParam(enumParamEntity);
    }

    @Override
    public void updateEnumParam(@NotNull @Valid EnumParam enumParam) {
        EnumParamEntity enumParamEntity = BeanMapper.map(enumParam, EnumParamEntity.class);

        enumParamDao.updateEnumParam(enumParamEntity);
    }

    @Override
    public void deleteEnumParam(@NotNull String id) {
        enumParamDao.deleteEnumParam(id);
    }

    @Override
    public EnumParam findOne(String id) {
        EnumParamEntity enumParamEntity = enumParamDao.findEnumParam(id);

        EnumParam enumParam = BeanMapper.map(enumParamEntity, EnumParam.class);
        return enumParam;
    }

    @Override
    public List<EnumParam> findList(List<String> idList) {
        List<EnumParamEntity> enumParamEntityList =  enumParamDao.findEnumParamList(idList);

        List<EnumParam> enumParamList =  BeanMapper.mapList(enumParamEntityList,EnumParam.class);
        return enumParamList;
    }

    @Override
    public EnumParam findEnumParam(@NotNull String id) {
        EnumParam enumParam = findOne(id);

        joinTemplate.joinQuery(enumParam);
        return enumParam;
    }

    @Override
    public List<EnumParam> findAllEnumParam() {
        List<EnumParamEntity> enumParamEntityList =  enumParamDao.findAllEnumParam();

        List<EnumParam> enumParamList =  BeanMapper.mapList(enumParamEntityList,EnumParam.class);

        joinTemplate.joinQuery(enumParamList);
        return enumParamList;
    }

    @Override
    public List<EnumParam> findEnumParamList(EnumParamQuery enumParamQuery) {
        List<EnumParamEntity> enumParamEntityList = enumParamDao.findEnumParamList(enumParamQuery);

        List<EnumParam> enumParamList = BeanMapper.mapList(enumParamEntityList,EnumParam.class);

        joinTemplate.joinQuery(enumParamList);

        return enumParamList;
    }

    @Override
    public Pagination<EnumParam> findEnumParamPage(EnumParamQuery enumParamQuery) {

        Pagination<EnumParamEntity>  pagination = enumParamDao.findEnumParamPage(enumParamQuery);

        List<EnumParam> enumParamList = BeanMapper.mapList(pagination.getDataList(),EnumParam.class);

        joinTemplate.joinQuery(enumParamList);

        return PaginationBuilder.build(pagination,enumParamList);
    }
}