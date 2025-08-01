package io.tiklab.postin.autotest.common.stepassert.service;

import io.tiklab.postin.autotest.common.stepassert.dao.StepAssertCommonDao;
import io.tiklab.postin.autotest.common.stepassert.entity.StepAssertCommonEntity;
import io.tiklab.postin.autotest.common.stepassert.model.StepAssertCommon;
import io.tiklab.postin.autotest.common.stepassert.model.StepAssertCommonQuery;
import io.tiklab.postin.autotest.common.stepassert.model.VariableAssert;
import io.tiklab.postin.common.MagicValue;
import io.tiklab.toolkit.beans.BeanMapper;
import io.tiklab.toolkit.join.JoinTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

/**
* 功能用例下步骤 服务
*/
@Service
public class StepAssertCommonServiceImpl implements StepAssertCommonService {

    @Autowired
    StepAssertCommonDao stepAssertCommonDao;

    @Autowired
    JoinTemplate joinTemplate;

    @Autowired
    VariableAssertService variableAssertService;


    @Override
    public String createStepAssertCommon(@NotNull @Valid StepAssertCommon stepAssertCommon) {
        StepAssertCommonEntity stepAssertCommonEntity = BeanMapper.map(stepAssertCommon, StepAssertCommonEntity.class);
        stepAssertCommonEntity.setCreateTime(new Timestamp(System.currentTimeMillis()));
        String id = stepAssertCommonDao.createStepAssertCommon(stepAssertCommonEntity);

        if(Objects.equals(stepAssertCommon.getType(), MagicValue.ASSERT_TYPE_VARIABLE)){
            VariableAssert variableAssert = stepAssertCommon.getVariableAssert();
            variableAssert.setAssertId(id);
            variableAssert.setId(id);
            variableAssertService.createVariableAssert(variableAssert);
        }

        return id;
    }

    @Override
    public void updateStepAssertCommon(@NotNull @Valid StepAssertCommon stepAssertCommon) {
        StepAssertCommonEntity stepAssertCommonEntity = BeanMapper.map(stepAssertCommon, StepAssertCommonEntity.class);
        stepAssertCommonDao.updateStepAssertCommon(stepAssertCommonEntity);

        if(Objects.equals(stepAssertCommon.getType(), MagicValue.ASSERT_TYPE_VARIABLE)){
            VariableAssert variableAssert = stepAssertCommon.getVariableAssert();
            variableAssertService.updateVariableAssert(variableAssert);
        }


    }

    @Override
    public void deleteStepAssertCommon(@NotNull String id) {
        StepAssertCommonEntity stepAssertCommon = stepAssertCommonDao.findStepAssertCommon(id);

        if(Objects.equals(stepAssertCommon.getType(), MagicValue.ASSERT_TYPE_VARIABLE)){
            variableAssertService.deleteVariableAssert(id);
        }


        stepAssertCommonDao.deleteStepAssertCommon(id);
    }

    @Override
    public StepAssertCommon findOne(String id) {
        StepAssertCommonEntity stepAssertCommonEntity = stepAssertCommonDao.findStepAssertCommon(id);

        StepAssertCommon stepAssertCommon = BeanMapper.map(stepAssertCommonEntity, StepAssertCommon.class);
        return stepAssertCommon;
    }

    @Override
    public StepAssertCommon findStepAssertCommon(@NotNull String id) {
        StepAssertCommon stepAssertCommon = findOne(id);
        joinTemplate.joinQuery(stepAssertCommon);

        if(Objects.equals(stepAssertCommon.getType(), MagicValue.ASSERT_TYPE_VARIABLE)){
            VariableAssert variableAssert = variableAssertService.findVariableAssert(id);
            stepAssertCommon.setVariableAssert(variableAssert);
        }


        return stepAssertCommon;
    }

    @Override
    public List<StepAssertCommon> findStepAssertCommonList(StepAssertCommonQuery stepAssertCommonQuery) {
        List<StepAssertCommonEntity> stepAssertCommonEntityList = stepAssertCommonDao.findStepAssertCommonList(stepAssertCommonQuery);
        List<StepAssertCommon> stepAssertCommonList = BeanMapper.mapList(stepAssertCommonEntityList, StepAssertCommon.class);
        joinTemplate.joinQuery(stepAssertCommonList);

        for (StepAssertCommon stepAssertCommon:stepAssertCommonList){
            if(Objects.equals(stepAssertCommon.getType(), MagicValue.ASSERT_TYPE_VARIABLE)){
                VariableAssert variableAssert = variableAssertService.findVariableAssert(stepAssertCommon.getId());
                stepAssertCommon.setVariableAssert(variableAssert);
            }

        }

        return stepAssertCommonList;
    }


}