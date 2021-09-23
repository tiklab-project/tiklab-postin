package com.doublekit.apibox.datastructure.service;

import com.doublekit.apibox.datastructure.dao.DataStructureDao;
import com.doublekit.apibox.datastructure.dao.EnumParamDao;
import com.doublekit.apibox.datastructure.dao.JsonParamDSDao;
import com.doublekit.apibox.datastructure.entity.DataStructurePo;
import com.doublekit.apibox.datastructure.model.DataStructure;
import com.doublekit.apibox.datastructure.model.DataStructureQuery;
import com.doublekit.beans.BeanMapper;
import com.doublekit.common.Pagination;
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
        DataStructurePo dataStructurePo = BeanMapper.map(dataStructure, DataStructurePo.class);
        //添加创建人
        String creatUserId = findCreatUser();
        dataStructurePo.setCreateUser(creatUserId);
        dataStructurePo.setCreateTime( new Date());

        return dataStructureDao.createDataStructure(dataStructurePo);
    }

    @Override
    public void updateDataStructure(@NotNull @Valid DataStructure dataStructure) {
        DataStructurePo dataStructurePo = BeanMapper.map(dataStructure, DataStructurePo.class);

        dataStructureDao.updateDataStructure(dataStructurePo);
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
        DataStructurePo dataStructurePo = dataStructureDao.findDataStructure(id);

        DataStructure dataStructure = BeanMapper.map(dataStructurePo, DataStructure.class);
        return dataStructure;
    }

    @Override
    public List<DataStructure> findList(List<String> idList) {
        List<DataStructurePo> dataStructurePoList =  dataStructureDao.findDataStructureList(idList);

        List<DataStructure> dataStructureList =  BeanMapper.mapList(dataStructurePoList,DataStructure.class);
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
        List<DataStructurePo> dataStructurePoList =  dataStructureDao.findAllDataStructure();

        List<DataStructure> dataStructureList =  BeanMapper.mapList(dataStructurePoList,DataStructure.class);

        joinQuery.queryList(dataStructureList);
        return dataStructureList;
    }

    @Override
    public List<DataStructure> findDataStructureList(DataStructureQuery dataStructureQuery) {
        List<DataStructurePo> dataStructurePoList = dataStructureDao.findDataStructureList(dataStructureQuery);

        List<DataStructure> dataStructureList = BeanMapper.mapList(dataStructurePoList,DataStructure.class);

        joinQuery.queryList(dataStructureList);

        return dataStructureList;
    }

    @Override
    public Pagination<DataStructure> findDataStructurePage(DataStructureQuery dataStructureQuery) {
        Pagination<DataStructure> pg = new Pagination<>();

        Pagination<DataStructurePo>  pagination = dataStructureDao.findDataStructurePage(dataStructureQuery);
        BeanUtils.copyProperties(pagination,pg);

        List<DataStructure> dataStructureList = BeanMapper.mapList(pagination.getDataList(),DataStructure.class);

        joinQuery.queryList(dataStructureList);

        pg.setDataList(dataStructureList);
        return pg;
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