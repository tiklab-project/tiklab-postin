package io.tiklab.postin.support.datastructure.service;

import io.tiklab.postin.support.datastructure.dao.DataStructureDao;
import io.tiklab.postin.support.datastructure.dao.EnumParamDao;
import io.tiklab.postin.support.datastructure.dao.JsonParamDSDao;
import io.tiklab.postin.support.datastructure.entity.DataStructureEntity;
import io.tiklab.postin.support.datastructure.entity.EnumParamEntity;
import io.tiklab.postin.support.datastructure.entity.JsonParamDSEntity;
import io.tiklab.postin.support.datastructure.model.DataStructure;
import io.tiklab.postin.support.datastructure.model.DataStructureQuery;
import io.tiklab.toolkit.beans.BeanMapper;
import io.tiklab.core.page.Pagination;
import io.tiklab.core.page.PaginationBuilder;
import io.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.DeleteBuilders;
import io.tiklab.toolkit.join.JoinTemplate;
import io.tiklab.eam.common.context.LoginContext;
import io.tiklab.postin.support.datastructure.model.JsonParamDS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

/**
* 数据结构 服务
*/
@Service
public class DataStructureServiceImpl implements DataStructureService {

    @Autowired
    DataStructureDao dataStructureDao;

    @Autowired
    JsonParamDSService jsonParamDSService;

    @Autowired
    JoinTemplate joinTemplate;

    @Autowired
    EnumParamDao enumParamDao;

    @Autowired
    JsonParamDSDao jsonParamDSDao;

    @Override
    public String createDataStructure(@NotNull @Valid DataStructure dataStructure) {
        DataStructureEntity dataStructureEntity = BeanMapper.map(dataStructure, DataStructureEntity.class);
        //添加创建人
        dataStructureEntity.setCreateUser(LoginContext.getLoginId());
        dataStructureEntity.setCreateTime( new Timestamp(System.currentTimeMillis()));

        String dataStructureId = dataStructureDao.createDataStructure(dataStructureEntity);

        if(Objects.equals(dataStructure.getDataType(), "json")){
            JsonParamDS jsonParamDS = new JsonParamDS();
            jsonParamDS.setDataStructureId(dataStructureId);
            jsonParamDS.setId(dataStructureId);
            jsonParamDS.setJsonText(
                "{\n" +
                "    \"type\": \"object\",\n" +
                "    \"title\": \"title\",\n" +
                "    \"properties\": {}\n" +
                "}"
            );

            jsonParamDSService.createJsonParamDS(jsonParamDS);
        }


        return dataStructureId;
    }

    @Override
    public void updateDataStructure(@NotNull @Valid DataStructure dataStructure) {
        DataStructureEntity dataStructureEntity = BeanMapper.map(dataStructure, DataStructureEntity.class);

        dataStructureDao.updateDataStructure(dataStructureEntity);
    }

    @Override
    public void deleteDataStructure(@NotNull String id) {

        //删除相关联的子表
        DeleteCondition deleteCondition = DeleteBuilders.createDelete(JsonParamDSEntity.class)
                .eq("dataStructureId", id)
                .get();
        jsonParamDSDao.deleteJsonParamDS(deleteCondition);

        deleteCondition = DeleteBuilders.createDelete(EnumParamEntity.class)
                .eq("dataStructureId", id)
                .get();
        enumParamDao.deleteEnumParam(deleteCondition);

        dataStructureDao.deleteDataStructure(id);
    }

    @Override
    public void deleteAllDataStructure(String workspaceId) {
        DeleteCondition deleteCondition = DeleteBuilders.createDelete(DataStructureEntity.class)
                .eq("workspaceId", workspaceId)
                .get();
        dataStructureDao.deleteDataStructure(deleteCondition);
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
    public int findModelNum(String workspaceId) {
        int modelNum = dataStructureDao.findModelNum(workspaceId);
        return modelNum;
    }

    @Override
    public DataStructure findDataStructure(@NotNull String id) {
        DataStructure dataStructure = findOne(id);

        joinTemplate.joinQuery(dataStructure,new String[]{
                "workspace",
                "createUser"
        });
        return dataStructure;
    }

    @Override
    public List<DataStructure> findAllDataStructure() {
        List<DataStructureEntity> dataStructureEntityList =  dataStructureDao.findAllDataStructure();

        List<DataStructure> dataStructureList =  BeanMapper.mapList(dataStructureEntityList,DataStructure.class);

        joinTemplate.joinQuery(dataStructureList,new String[]{
                "workspace",
                "createUser"
        });
        return dataStructureList;
    }

    @Override
    public List<DataStructure> findDataStructureList(DataStructureQuery dataStructureQuery) {
        List<DataStructureEntity> dataStructureEntityList = dataStructureDao.findDataStructureList(dataStructureQuery);
        List<DataStructure> dataStructureList = BeanMapper.mapList(dataStructureEntityList,DataStructure.class);
        joinTemplate.joinQuery(dataStructureList,new String[]{
                "workspace",
                "createUser"
        });

        if(dataStructureQuery.getIsNotIncludeId()!=null){
            dataStructureList.removeIf(dataStructure -> dataStructure.getId().equals(dataStructureQuery.getIsNotIncludeId()));
        }

        return dataStructureList;
    }

    @Override
    public Pagination<DataStructure> findDataStructurePage(DataStructureQuery dataStructureQuery) {

        Pagination<DataStructureEntity>  pagination = dataStructureDao.findDataStructurePage(dataStructureQuery);

        List<DataStructure> dataStructureList = BeanMapper.mapList(pagination.getDataList(),DataStructure.class);

        joinTemplate.joinQuery(dataStructureList,new String[]{
                "workspace",
                "createUser"
        });

        return PaginationBuilder.build(pagination,dataStructureList);
    }

}