package com.darthcloud.apibox.apidef.dao;

import com.darthcloud.common.Pagination;
import com.darthcloud.apibox.apidef.entity.RawParamPo;
import com.darthcloud.apibox.apidef.model.RawParamQuery;
import com.darthcloud.dal.jpa.JpaTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 用户数据操作
 */
@Repository
public class RawParamDao{

    private static Logger logger = LoggerFactory.getLogger(RawParamDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建用户
     * @param rawParamPo
     * @return
     */
    public String createRawParam(RawParamPo rawParamPo) {
        return jpaTemplate.save(rawParamPo,String.class);
    }

    /**
     * 更新用户
     * @param rawParamPo
     */
    public void updateRawParam(RawParamPo rawParamPo){
        jpaTemplate.update(rawParamPo);
    }

    /**
     * 删除用户
     * @param id
     */
    public void deleteRawParam(String id){
        jpaTemplate.delete(RawParamPo.class,id);
    }

    /**
     * 查找用户
     * @param id
     * @return
     */
    public RawParamPo findRawParam(String id){
        return jpaTemplate.findOne(RawParamPo.class,id);
    }

    /**
    * findAllRawParam
    * @return
    */
    public List<RawParamPo> findAllRawParam() {
        return jpaTemplate.findAll(RawParamPo.class);
    }

    public List<RawParamPo> findRawParamList(RawParamQuery rawParamQuery) {
        return jpaTemplate.findList(RawParamPo.class,rawParamQuery);
    }

    public Pagination<List<RawParamPo>> findRawParamPage(RawParamQuery rawParamQuery) { 
        return jpaTemplate.findPage(RawParamPo.class,rawParamQuery);
    }
}