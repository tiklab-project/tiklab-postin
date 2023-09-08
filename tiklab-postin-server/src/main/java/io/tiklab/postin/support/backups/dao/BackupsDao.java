package io.tiklab.postin.support.backups.dao;

import io.tiklab.beans.BeanMapper;
import io.tiklab.dal.jpa.JpaTemplate;
import io.tiklab.dal.jpa.criterial.condition.QueryCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import io.tiklab.postin.support.backups.entity.BackupsEntity;
import io.tiklab.postin.support.backups.model.Backups;
import io.tiklab.postin.support.backups.model.BackupsQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Repository
public class BackupsDao {

    @Autowired
    JpaTemplate jpaTemplate;

    public String createBackups(Backups backups){
        BackupsEntity backupsEntity = BeanMapper.map(backups, BackupsEntity.class);
        return jpaTemplate.save(backupsEntity, String.class);
    }

    public void updateBackups(Backups backups){
        BackupsEntity backupsEntity = BeanMapper.map(backups, BackupsEntity.class);
         jpaTemplate.update(backupsEntity);
    }

    public void deleteBackups(String backupsId){
        jpaTemplate.delete(BackupsEntity.class,backupsId);
    }


    public Backups findBackups(String backupsId){
        BackupsEntity backupsEntity = jpaTemplate.findOne(BackupsEntity.class, backupsId);
        return BeanMapper.map(backupsEntity, Backups.class);
    }


    public List<Backups> findAllBackups(){
        List<BackupsEntity> backupsEntityList = jpaTemplate.findAll(BackupsEntity.class);
        if (backupsEntityList == null || backupsEntityList.isEmpty()){
            return Collections.emptyList();
        }
        return BeanMapper.mapList(backupsEntityList, Backups.class);
    }


    public List<Backups> findBackupsList (BackupsQuery backupsQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(BackupsEntity.class)
                .eq("type", backupsQuery.getType())
                .orders(backupsQuery.getOrderParams())
                .get();
        List<BackupsEntity> easBackupsEntities = jpaTemplate.findList(queryCondition, BackupsEntity.class);
        if (Objects.isNull(easBackupsEntities) || easBackupsEntities.isEmpty()){
            return Collections.emptyList();
        }
        return BeanMapper.mapList(easBackupsEntities, Backups.class);
    }

}




















