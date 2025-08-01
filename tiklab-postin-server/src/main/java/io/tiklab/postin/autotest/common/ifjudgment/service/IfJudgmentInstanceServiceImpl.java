package io.tiklab.postin.autotest.common.ifjudgment.service;

import io.tiklab.postin.autotest.common.ifjudgment.dao.IfJudgmentInstanceDao;
import io.tiklab.postin.autotest.common.ifjudgment.entity.IfJudgmentInstanceEntity;
import io.tiklab.postin.autotest.common.ifjudgment.model.IfJudgmentInstance;
import io.tiklab.postin.autotest.common.ifjudgment.model.IfJudgmentInstanceQuery;
import io.tiklab.postin.autotest.common.ifjudgment.model.IfVariableInstance;
import io.tiklab.postin.autotest.common.ifjudgment.model.IfVariableInstanceQuery;
import io.tiklab.toolkit.beans.BeanMapper;
import io.tiklab.toolkit.join.JoinTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 功能用例下步骤 服务
*/
@Service
public class IfJudgmentInstanceServiceImpl implements IfJudgmentInstanceService {

    @Autowired
    IfJudgmentInstanceDao ifJudgmentInstanceDao;

    @Autowired
    JoinTemplate joinTemplate;


    @Autowired
    IfVariableInstanceService ifVariableInstanceService;


    @Override
    public String createIfJudgmentInstance(@NotNull @Valid IfJudgmentInstance ifJudgmentInstance) {
        IfJudgmentInstanceEntity ifJudgmentInstanceEntity = BeanMapper.map(ifJudgmentInstance, IfJudgmentInstanceEntity.class);
        String id = ifJudgmentInstanceDao.createIfJudgmentInstance(ifJudgmentInstanceEntity);

        if(ifJudgmentInstance.getIfVariableInstanceList() != null){
            for (IfVariableInstance ifVariableInstance : ifJudgmentInstance.getIfVariableInstanceList()) {
                ifVariableInstance.setStepInstanceId(id);
                ifVariableInstanceService.createIfVariableInstance(ifVariableInstance);
            }
        }

        return id;
    }

    @Override
    public void updateIfJudgmentInstance(@NotNull @Valid IfJudgmentInstance ifJudgmentInstance) {
        IfJudgmentInstanceEntity ifJudgmentInstanceEntity = BeanMapper.map(ifJudgmentInstance, IfJudgmentInstanceEntity.class);
        ifJudgmentInstanceDao.updateIfJudgmentInstance(ifJudgmentInstanceEntity);

    }

    @Override
    public void deleteIfJudgmentInstance(@NotNull String id) {
        ifJudgmentInstanceDao.deleteIfJudgmentInstance(id);

        ifVariableInstanceService.deleteAllIfVariableInstance(id);
    }

    @Override
    public IfJudgmentInstance findOne(String id) {
        IfJudgmentInstanceEntity ifJudgmentInstanceEntity = ifJudgmentInstanceDao.findIfJudgmentInstance(id);

        IfJudgmentInstance ifJudgmentInstance = BeanMapper.map(ifJudgmentInstanceEntity, IfJudgmentInstance.class);
        return ifJudgmentInstance;
    }

    @Override
    public IfJudgmentInstance findIfJudgmentInstance(@NotNull String id) {
        IfJudgmentInstance ifJudgmentInstance = findOne(id);
        if(ifJudgmentInstance==null){
            return null;
        }

        joinTemplate.joinQuery(ifJudgmentInstance);

        IfVariableInstanceQuery ifVariableInstanceQuery = new IfVariableInstanceQuery();
        ifVariableInstanceQuery.setStepInstanceId(id);
        List<IfVariableInstance> ifVariableInstanceList = ifVariableInstanceService.findIfVariableInstanceList(ifVariableInstanceQuery);
        if(ifVariableInstanceList!=null){
            ifJudgmentInstance.setIfVariableInstanceList(ifVariableInstanceList);
        }
        return ifJudgmentInstance;
    }

    @Override
    public List<IfJudgmentInstance> findIfJudgmentInstanceList(IfJudgmentInstanceQuery ifJudgmentInstanceQuery) {
        List<IfJudgmentInstanceEntity> ifJudgmentInstanceEntityList = ifJudgmentInstanceDao.findIfJudgmentInstanceList(ifJudgmentInstanceQuery);
        List<IfJudgmentInstance> ifJudgmentInstanceList = BeanMapper.mapList(ifJudgmentInstanceEntityList, IfJudgmentInstance.class);
        joinTemplate.joinQuery(ifJudgmentInstanceList);

        return ifJudgmentInstanceList;
    }

}