package com.doublekit.apibox.datastructure.service;

import com.doublekit.apibox.datastructure.dao.DataStructureDao;
import com.doublekit.apibox.datastructure.dao.EnumParamDao;
import com.doublekit.apibox.datastructure.dao.JsonParamDSDao;
import com.doublekit.apibox.datastructure.entity.DataStructureEntity;
import com.doublekit.apibox.datastructure.model.DataStructure;
import com.doublekit.apibox.datastructure.model.DataStructureQuery;
import com.doublekit.beans.BeanMapper;
import com.doublekit.common.Pagination;
import com.doublekit.common.PaginationBuilder;
import com.doublekit.dal.jpa.builder.deletelist.condition.DeleteCondition;
import com.doublekit.dal.jpa.builder.deletelist.conditionbuilder.DeleteBuilders;
import com.doublekit.eam.common.Ticket;
import com.doublekit.eam.common.TicketContext;
import com.doublekit.eam.common.TicketHolder;
import com.doublekit.join.JoinTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

/**
* DataStructureServiceImpl
*/
@Service
public class DataStructureServiceImpl implements DataStructureService {

    @Autowired
    DataStructureDao dataStructureDao;

    @Autowired
    JoinTemplate joinQuery;

    @Autowired
    EnumParamDao enumParamDao;

    @Autowired
    JsonParamDSDao jsonParamDSDao;

    @Override
    public String createDataStructure(@NotNull @Valid DataStructure dataStructure) {
        DataStructureEntity dataStructureEntity = BeanMapper.map(dataStructure, DataStructureEntity.class);
        //添加创建人
        String creatUserId = findCreatUser();
        dataStructureEntity.setCreateUser(creatUserId);
        dataStructureEntity.setCreateTime( new Date());

        return dataStructureDao.createDataStructure(dataStructureEntity);
    }

    @Override
    public void updateDataStructure(@NotNull @Valid DataStructure dataStructure) {
        DataStructureEntity dataStructureEntity = BeanMapper.map(dataStructure, DataStructureEntity.class);

        dataStructureDao.updateDataStructure(dataStructureEntity);
    }

    @Override
    public void deleteDataStructure(@NotNull String id) {

        //删除相关联的子表
        DeleteCondition deleteCondition = DeleteBuilders.instance().eq("subjectId", id).get();
        jsonParamDSDao.deleteJsonParamDS(deleteCondition);
        enumParamDao.deleteEnumParam(deleteCondition);
        dataStructureDao.deleteDataStructure(id);
    }

    @Override
    public DataStructure findOne(String id) {
        DataStructureEntity dataStructureEntity = dataStructureDao.findDataStructure(id);

        DataStructure dataStructure = BeanMapper.map(dataStructureEntity, DataStructure.class);
        return dataStructure;
    }

    @Override
    public List<DataStructure> findList(List<String> idList) {
        List<DataStructureEntity> dataStructureEntityList =  dataStructureDao.findDataStructureList(idList);

        List<DataStructure> dataStructureList =  BeanMapper.mapList(dataStructureEntityList,DataStructure.class);
        return dataStructureList;
    }

    @Override
    public DataStructure findDataStructure(@NotNull String id) {
        DataStructure dataStructure = findOne(id);

        joinQuery.queryOne(dataStructure);
        return dataStructure;
    }

    @Override
    public List<DataStructure> findAllDataStructure() {
        List<DataStructureEntity> dataStructureEntityList =  dataStructureDao.findAllDataStructure();

        List<DataStructure> dataStructureList =  BeanMapper.mapList(dataStructureEntityList,DataStructure.class);

        joinQuery.queryList(dataStructureList);
        return dataStructureList;
    }

    @Override
    public List<DataStructure> findDataStructureList(DataStructureQuery dataStructureQuery) {
        List<DataStructureEntity> dataStructureEntityList = dataStructureDao.findDataStructureList(dataStructureQuery);

        List<DataStructure> dataStructureList = BeanMapper.mapList(dataStructureEntityList,DataStructure.class);

        joinQuery.queryList(dataStructureList);

        return dataStructureList;
    }

    @Override
    public Pagination<DataStructure> findDataStructurePage(DataStructureQuery dataStructureQuery) {

        Pagination<DataStructureEntity>  pagination = dataStructureDao.findDataStructurePage(dataStructureQuery);

        List<DataStructure> dataStructureList = BeanMapper.mapList(pagination.getDataList(),DataStructure.class);

        joinQuery.queryList(dataStructureList);

        return PaginationBuilder.build(pagination,dataStructureList);
    }

    /**
     * 查询用户（创建人）id
     * @param
     */
    public String findCreatUser(){
        String ticketId = TicketHolder.get();
        Ticket ticket = TicketContext.get(ticketId);
        return ticket.getUserId();
    }
}