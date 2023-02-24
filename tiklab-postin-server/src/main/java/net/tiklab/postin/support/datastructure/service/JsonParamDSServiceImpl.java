package net.tiklab.postin.support.datastructure.service;

import net.tiklab.postin.support.datastructure.dao.JsonParamDSDao;
import net.tiklab.postin.support.datastructure.entity.JsonParamDSEntity;
import net.tiklab.postin.support.datastructure.model.JsonParamDS;
import net.tiklab.postin.support.datastructure.model.JsonParamDSQuery;

import net.tiklab.core.page.Pagination;
import net.tiklab.beans.BeanMapper;
import net.tiklab.core.page.PaginationBuilder;
import net.tiklab.join.JoinTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.util.ObjectUtils;

/**
* JsonParamDSServiceImpl
*/
@Service
public class JsonParamDSServiceImpl implements JsonParamDSService {

    @Autowired
    JsonParamDSDao jsonParamDSDao;

    @Autowired
    JoinTemplate joinTemplate;



    @Override
    public String createJsonParamDS(@NotNull @Valid JsonParamDS jsonParamDS) {
        JsonParamDSEntity jsonParamDSEntity = BeanMapper.map(jsonParamDS, JsonParamDSEntity.class);

        return jsonParamDSDao.createJsonParamDS(jsonParamDSEntity);
    }

    @Override
    public void updateJsonParamDS(@NotNull @Valid JsonParamDS jsonParamDS) {
        JsonParamDSEntity jsonParamDSEntity = BeanMapper.map(jsonParamDS, JsonParamDSEntity.class);

        jsonParamDSDao.updateJsonParamDS(jsonParamDSEntity);
    }

    @Override
    public void deleteJsonParamDS(@NotNull String id) {
        //删除相关联的子表

        jsonParamDSDao.deleteJsonParamDS(id);
    }

    @Override
    public JsonParamDS findOne(String id) {
        JsonParamDSEntity jsonParamDSEntity = jsonParamDSDao.findJsonParamDS(id);

        JsonParamDS jsonParamDS = BeanMapper.map(jsonParamDSEntity, JsonParamDS.class);
        return jsonParamDS;
    }

    @Override
    public List<JsonParamDS> findList(List<String> idList) {
        List<JsonParamDSEntity> jsonParamDSEntityList =  jsonParamDSDao.findJsonParamDSList(idList);

        List<JsonParamDS> jsonParamDSList =  BeanMapper.mapList(jsonParamDSEntityList,JsonParamDS.class);
        return jsonParamDSList;
    }

    @Override
    public JsonParamDS findJsonParamDS(@NotNull String id) {
        JsonParamDS jsonParamDS = findOne(id);

        joinTemplate.joinQuery(jsonParamDS);
        return jsonParamDS;
    }

    @Override
    public List<JsonParamDS> findAllJsonParamDS() {
        List<JsonParamDSEntity> jsonParamDSEntityList =  jsonParamDSDao.findAllJsonParamDS();

        List<JsonParamDS> jsonParamDSList =  BeanMapper.mapList(jsonParamDSEntityList,JsonParamDS.class);

        joinTemplate.joinQuery(jsonParamDSList);
        return jsonParamDSList;
    }

    @Override
    public List<JsonParamDS> findJsonParamDSList(JsonParamDSQuery jsonParamDSQuery) {
        List<JsonParamDSEntity> jsonParamDSEntityList = jsonParamDSDao.findJsonParamDSList(jsonParamDSQuery);

        List<JsonParamDS> jsonParamDSList = BeanMapper.mapList(jsonParamDSEntityList,JsonParamDS.class);

        joinTemplate.joinQuery(jsonParamDSList);

        return jsonParamDSList;
    }

    @Override
    public Pagination<JsonParamDS> findJsonParamDSPage(JsonParamDSQuery jsonParamDSQuery) {

        Pagination<JsonParamDSEntity>  pagination = jsonParamDSDao.findJsonParamDSPage(jsonParamDSQuery);

        List<JsonParamDS> jsonParamDSList = BeanMapper.mapList(pagination.getDataList(),JsonParamDS.class);

        joinTemplate.joinQuery(jsonParamDSList);

        return PaginationBuilder.build(pagination,jsonParamDSList);
    }

    @Override
    public List<JsonParamDS> findJsonParamDSListTree(JsonParamDSQuery jsonParamDSQuery) {

        List<JsonParamDS> jsonParamDSList = this.findJsonParamDSList(jsonParamDSQuery);

        //查寻一级数据
        List<JsonParamDS> topJsonParamList = findTopJsonParamList(jsonParamDSList);

        //设置下级列表
        //设置下级节点列表
        if(topJsonParamList != null && topJsonParamList.size() > 0){
            for(JsonParamDS topJsonParam:topJsonParamList){
                setChildren(jsonParamDSList,topJsonParam);
            }
        }
        return topJsonParamList;
    }

    /**
     * 查找第一级参数列表
     * @param jsonParamDSList
     * @return
     */
    List<JsonParamDS> findTopJsonParamList(List<JsonParamDS> jsonParamDSList) {
        List<JsonParamDS> jsonParamList = jsonParamDSList.stream()
                .filter(jsonParam -> (ObjectUtils.isEmpty(jsonParam.getParent()) || ObjectUtils.isEmpty(jsonParam.getParent().getId()) ))
                .collect(Collectors.toList());
        return jsonParamList;
    }

    /**
     * 设置下级节点列表
     * @param matchJsonParamList
     * @param parentJsonParam
     */
    void setChildren(List<JsonParamDS> matchJsonParamList,JsonParamDS parentJsonParam){
        List<JsonParamDS> childList = matchJsonParamList.stream()
                .filter(jsonParam -> (jsonParam.getParent() != null && jsonParam.getParent().getId() != null && jsonParam.getParent().getId().equals(parentJsonParam.getId())))
                .collect(Collectors.toList());

        if(childList != null && childList.size() > 0){
            parentJsonParam.setChildren(childList);

            //设置下级节点列表
            for(JsonParamDS childJsonParam:childList){
                setChildren(matchJsonParamList,childJsonParam);
            }
        }
    }
}