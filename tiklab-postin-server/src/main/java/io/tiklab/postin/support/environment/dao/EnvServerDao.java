package io.tiklab.postin.support.environment.dao;

import io.tiklab.core.page.Pagination;
import io.tiklab.dal.jpa.JpaTemplate;
import io.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import io.tiklab.dal.jpa.criterial.condition.QueryCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import io.tiklab.postin.support.environment.entity.EnvServerEntity;
import io.tiklab.postin.support.environment.model.EnvServerQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 环境中服务地址 数据访问
 */
@Repository
public class EnvServerDao {

    private static Logger logger = LoggerFactory.getLogger(EnvServerDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建环境中服务地址
     * @param envServerEntity
     * @return
     */
    public String createEnvServer(EnvServerEntity envServerEntity) {
        return jpaTemplate.save(envServerEntity,String.class);
    }

    /**
     * 更新环境中服务地址
     * @param envServerEntity
     */
    public void updateEnvServer(EnvServerEntity envServerEntity){
        jpaTemplate.update(envServerEntity);
    }

    /**
     * 删除环境中服务地址
     * @param id
     */
    public void deleteEnvServer(String id){
        jpaTemplate.delete(EnvServerEntity.class,id);
    }

    public void deleteEnvServer(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 查找环境中服务地址
     * @param id
     * @return
     */
    public EnvServerEntity findEnvServer(String id){
        return jpaTemplate.findOne(EnvServerEntity.class,id);
    }

    /**
    * 查找所有环境中服务地址
    * @return
    */
    public List<EnvServerEntity> findAllEnvServer() {
        return jpaTemplate.findAll(EnvServerEntity.class);
    }

    public List<EnvServerEntity> findEnvServerList(List<String> idList) {
        return jpaTemplate.findList(EnvServerEntity.class,idList);
    }

    /**
     * 根据查询参数查找环境中服务地址
     * @param envServerQuery
     * @return
     */
    public List<EnvServerEntity> findEnvServerList(EnvServerQuery envServerQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(EnvServerEntity.class)
                .eq("envId",envServerQuery.getEnvId())
                .orders(envServerQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition, EnvServerEntity.class);
    }

    /**
     * 根据查询参数按分页查找环境中服务地址
     * @param envServerQuery
     * @return
     */
    public Pagination<EnvServerEntity> findEnvServerPage(EnvServerQuery envServerQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(EnvServerEntity.class)
                .eq("envId",envServerQuery.getEnvId())
                .pagination(envServerQuery.getPageParam())
                .orders(envServerQuery.getOrderParams())
                .get();
        return jpaTemplate.findPage(queryCondition, EnvServerEntity.class);
    }
}