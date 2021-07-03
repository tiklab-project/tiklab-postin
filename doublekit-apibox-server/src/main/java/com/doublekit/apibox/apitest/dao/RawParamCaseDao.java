package com.doublekit.apibox.apitest.dao;

import com.doublekit.apibox.apitest.entity.RawParamCasePo;
import com.doublekit.apibox.apitest.model.RawParamCaseQuery;
import com.doublekit.common.Pagination;
import com.doublekit.dal.jpa.JpaTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 用户数据操作
 */
@Repository
public class RawParamCaseDao{

    private static Logger logger = LoggerFactory.getLogger(RawParamCaseDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建用户
     * @param rawParamCasePo
     * @return
     */
    public String createRawParamCase(RawParamCasePo rawParamCasePo) {
        return jpaTemplate.save(rawParamCasePo,String.class);
    }

    /**
     * 更新用户
     * @param rawParamCasePo
     */
    public void updateRawParamCase(RawParamCasePo rawParamCasePo){
        jpaTemplate.update(rawParamCasePo);
    }

    /**
     * 删除用户
     * @param id
     */
    public void deleteRawParamCase(String id){
        jpaTemplate.delete(RawParamCasePo.class,id);
    }

    /**
     * 查找用户
     * @param id
     * @return
     */
    public RawParamCasePo findRawParamCase(String id){
        return jpaTemplate.findOne(RawParamCasePo.class,id);
    }

    /**
    * findAllRawParamCase
    * @return
    */
    public List<RawParamCasePo> findAllRawParamCase() {
        return jpaTemplate.findAll(RawParamCasePo.class);
    }

    public List<RawParamCasePo> findRawParamCaseList(List<String> idList) {
        return jpaTemplate.findList(RawParamCasePo.class,idList);
    }

    public List<RawParamCasePo> findRawParamCaseList(RawParamCaseQuery rawParamCaseQuery) {
        return jpaTemplate.findList(RawParamCasePo.class,rawParamCaseQuery);
    }

    public Pagination<RawParamCasePo> findRawParamCasePage(RawParamCaseQuery rawParamCaseQuery) {
        return jpaTemplate.findPage(RawParamCasePo.class,rawParamCaseQuery);
    }
}