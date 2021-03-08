package com.darthcloud.apibox.apitest.dao;

import com.darthcloud.common.Pagination;
import com.darthcloud.apibox.apitest.entity.ResponseInstancePo;
import com.darthcloud.apibox.apitest.model.ResponseInstanceQuery;
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
public class ResponseInstanceDao{

    private static Logger logger = LoggerFactory.getLogger(ResponseInstanceDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建用户
     * @param responseInstancePo
     * @return
     */
    public String createResponseInstance(ResponseInstancePo responseInstancePo) {
        return jpaTemplate.save(responseInstancePo,String.class);
    }

    /**
     * 更新用户
     * @param responseInstancePo
     */
    public void updateResponseInstance(ResponseInstancePo responseInstancePo){
        jpaTemplate.update(responseInstancePo);
    }

    /**
     * 删除用户
     * @param id
     */
    public void deleteResponseInstance(String id){
        jpaTemplate.delete(ResponseInstancePo.class,id);
    }

    /**
     * 查找用户
     * @param id
     * @return
     */
    public ResponseInstancePo findResponseInstance(String id){
        return jpaTemplate.findOne(ResponseInstancePo.class,id);
    }

    /**
    * findAllResponseInstance
    * @return
    */
    public List<ResponseInstancePo> findAllResponseInstance() {
        return jpaTemplate.findAll(ResponseInstancePo.class);
    }

    public List<ResponseInstancePo> findResponseInstanceList(List<String> idList) {
        return jpaTemplate.findList(ResponseInstancePo.class,idList);
    }

    public List<ResponseInstancePo> findResponseInstanceList(ResponseInstanceQuery responseInstanceQuery) {
        return jpaTemplate.createCriteria(ResponseInstancePo.class).params(responseInstanceQuery).list();
    }

    public Pagination<List<ResponseInstancePo>> findResponseInstancePage(ResponseInstanceQuery responseInstanceQuery) { 
        return jpaTemplate.createCriteria(ResponseInstancePo.class).params(responseInstanceQuery).page();
    }
}