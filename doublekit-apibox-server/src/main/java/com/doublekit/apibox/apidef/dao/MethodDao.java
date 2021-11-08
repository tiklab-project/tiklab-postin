package com.doublekit.apibox.apidef.dao;

import com.doublekit.apibox.apidef.entity.MethodEntity;
import com.doublekit.apibox.apidef.model.MethodExQuery;
import com.doublekit.common.Pagination;
import com.doublekit.dal.jpa.JpaTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 用户数据操作
 */
@Repository
public class MethodDao {

    private static Logger logger = LoggerFactory.getLogger(MethodDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建用户
     * @param methodExPo
     * @return
     */
    public String createMethod(MethodEntity methodExPo) {
        return jpaTemplate.save(methodExPo,String.class);
    }

    /**
     * 更新用户
     * @param methodExPo
     */
    public void updateMethod(MethodEntity methodExPo){
        jpaTemplate.update(methodExPo);
    }

    /**
     * 删除用户
     * @param id
     */
    public void deleteMethod(String id){
        jpaTemplate.delete(MethodEntity.class,id);
    }

    /**
     * 查找用户
     * @param id
     * @return
     */
    public MethodEntity findMethod(String id){
        return jpaTemplate.findOne(MethodEntity.class,id);
    }

    /**
    * findAllApxMethod
    * @return
    */
    public List<MethodEntity> findAllMethod() {
        return filterMethod(jpaTemplate.findAll(MethodEntity.class));
    }

    public List<MethodEntity> findMethodList(List<String> idList) {
        return jpaTemplate.findList(MethodEntity.class,idList);
    }

    public List<MethodEntity> findMethodList(MethodExQuery methodExQuery) {

        return  filterMethod(jpaTemplate.findList(methodExQuery, MethodEntity.class));
    }

    public Pagination<MethodEntity> findMethodPage(MethodExQuery methodExQuery) {

        return jpaTemplate.findPage(methodExQuery, MethodEntity.class);
    }

    //过滤查询最新版本的方法
    List<MethodEntity> filterMethod (List<MethodEntity> methodExList){
        List<MethodEntity> collect = methodExList.stream().filter(a -> "current".equals(a.getVersionCode())).collect(Collectors.toList());
        return collect;
    }
}