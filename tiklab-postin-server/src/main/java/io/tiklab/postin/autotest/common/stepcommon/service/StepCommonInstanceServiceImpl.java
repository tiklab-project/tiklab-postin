package io.tiklab.postin.autotest.common.stepcommon.service;


import io.tiklab.postin.autotest.common.ifjudgment.model.IfJudgmentInstance;
import io.tiklab.postin.autotest.common.ifjudgment.service.IfJudgmentInstanceService;
import io.tiklab.postin.autotest.common.stepcommon.dao.StepCommonInstanceDao;
import io.tiklab.postin.autotest.common.stepcommon.entity.StepCommonInstanceEntity;
import io.tiklab.postin.autotest.common.stepcommon.model.StepCommonInstance;
import io.tiklab.postin.autotest.common.stepcommon.model.StepCommonInstanceQuery;
import io.tiklab.postin.autotest.http.unit.instance.model.ApiUnitInstance;
import io.tiklab.postin.autotest.http.unit.instance.service.ApiUnitInstanceService;
import io.tiklab.postin.common.MagicValue;
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
public class StepCommonInstanceServiceImpl implements StepCommonInstanceService {

    @Autowired
    StepCommonInstanceDao stepCommonInstanceDao;

    @Autowired
    JoinTemplate joinTemplate;

    @Autowired
    ApiUnitInstanceService apiUnitInstanceService;

    @Autowired
    IfJudgmentInstanceService ifJudgmentInstanceService;

    @Override
    public String createStepCommonInstance(@NotNull @Valid StepCommonInstance stepCommonInstance) {

        StepCommonInstanceEntity stepCommonInstanceEntity = BeanMapper.map(stepCommonInstance, StepCommonInstanceEntity.class);

        String id = stepCommonInstanceDao.createStepCommonInstance(stepCommonInstanceEntity);

        return id;
    }

    @Override
    public void updateStepCommonInstance(@NotNull @Valid StepCommonInstance stepCommonInstance) {

        StepCommonInstanceEntity entity = BeanMapper.map(stepCommonInstance, StepCommonInstanceEntity.class);
        stepCommonInstanceDao.updateStepCommonInstance(entity);

    }

    @Override
    public void deleteStepCommonInstance(@NotNull String id,String caseType) {
        stepCommonInstanceDao.deleteStepCommonInstance(id);

        switch (caseType){
            case MagicValue.CASE_TYPE_API_SCENE :
                apiUnitInstanceService.deleteApiUnitInstance(id);
                break;

            case MagicValue.CASE_TYPE_IF:
                ifJudgmentInstanceService.deleteIfJudgmentInstance(id);
        }

    }

    @Override
    public void deleteAllStepCommonInstance( String instanceId) {
        StepCommonInstanceQuery stepCommonInstanceQuery = new StepCommonInstanceQuery();
        stepCommonInstanceQuery.setInstanceId(instanceId);
        List<StepCommonInstance> stepCommonInstanceList = findStepCommonInstanceList(stepCommonInstanceQuery);
        for(StepCommonInstance stepCommonInstance:stepCommonInstanceList){
            deleteStepCommonInstance(stepCommonInstance.getId(),stepCommonInstance.getType());
        }
    }


    @Override
    public StepCommonInstance findOne(String id) {
        StepCommonInstanceEntity stepCommonInstanceEntity = stepCommonInstanceDao.findStepCommonInstance(id);

        StepCommonInstance stepCommonInstance = BeanMapper.map(stepCommonInstanceEntity, StepCommonInstance.class);
        return stepCommonInstance;
    }

    @Override
    public StepCommonInstance findStepCommonInstance(@NotNull String id) {
        StepCommonInstance stepCommonInstance = findOne(id);
        joinTemplate.joinQuery(stepCommonInstance);

        return stepCommonInstance;
    }

    @Override
    public List<StepCommonInstance> findStepCommonInstanceList(StepCommonInstanceQuery stepCommonInstanceQuery) {
        List<StepCommonInstanceEntity> stepCommonInstanceEntityList = stepCommonInstanceDao.findStepCommonInstanceList(stepCommonInstanceQuery);
        List<StepCommonInstance> stepCommonInstanceList = BeanMapper.mapList(stepCommonInstanceEntityList, StepCommonInstance.class);
//        joinTemplate.joinQuery(stepCommonInstanceList);

        String caseType = stepCommonInstanceQuery.getCaseType();
        if(caseType!=null){
            for(StepCommonInstance stepCommonInstance:stepCommonInstanceList){
                switch (caseType){
                    case MagicValue.CASE_TYPE_API_SCENE:
                        ApiUnitInstance apiUnitInstance = apiUnitInstanceService.findApiUnitInstance(stepCommonInstance.getId());
                        stepCommonInstance.setApiUnitInstance(apiUnitInstance);
                        break;

                }

                IfJudgmentInstance ifJudgmentInstance = ifJudgmentInstanceService.findIfJudgmentInstance(stepCommonInstance.getId());
                stepCommonInstance.setIfJudgmentInstance(ifJudgmentInstance);
            }
        }

        return stepCommonInstanceList;
    }


}