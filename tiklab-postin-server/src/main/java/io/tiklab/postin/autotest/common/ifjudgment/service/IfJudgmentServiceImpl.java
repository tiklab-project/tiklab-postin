package io.tiklab.postin.autotest.common.ifjudgment.service;

import io.tiklab.postin.autotest.common.ifjudgment.dao.IfJudgmentDao;
import io.tiklab.postin.autotest.common.ifjudgment.entity.IfJudgmentEntity;
import io.tiklab.postin.autotest.common.ifjudgment.model.IfJudgment;
import io.tiklab.postin.autotest.common.ifjudgment.model.IfJudgmentQuery;
import io.tiklab.postin.autotest.common.ifjudgment.model.IfVariable;
import io.tiklab.postin.autotest.common.ifjudgment.model.IfVariableQuery;
import io.tiklab.postin.autotest.common.stepcommon.model.StepCommon;
import io.tiklab.postin.autotest.common.stepcommon.service.StepCommonService;
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
public class IfJudgmentServiceImpl implements IfJudgmentService {

    @Autowired
    IfJudgmentDao ifJudgmentDao;

    @Autowired
    JoinTemplate joinTemplate;

    @Autowired
    StepCommonService stepCommonService;

    @Autowired
    IfVariableService ifVariableService;

    @Override
    public String createIfJudgment(@NotNull @Valid IfJudgment ifJudgment) {

        IfJudgmentEntity ifJudgmentEntity = BeanMapper.map(ifJudgment, IfJudgmentEntity.class);

        String id = ifJudgmentDao.createIfJudgment(ifJudgmentEntity);

        return id;
    }

    @Override
    public void updateIfJudgment(@NotNull @Valid IfJudgment ifJudgment) {
        IfJudgmentEntity ifJudgmentEntity = BeanMapper.map(ifJudgment, IfJudgmentEntity.class);
        ifJudgmentDao.updateIfJudgment(ifJudgmentEntity);

    }

    @Override
    public void deleteIfJudgment(@NotNull String id) {

        ifJudgmentDao.deleteIfJudgment(id);
    }

    @Override
    public IfJudgment findOne(String id) {
        IfJudgmentEntity ifJudgmentEntity = ifJudgmentDao.findIfJudgment(id);

        IfJudgment ifJudgment = BeanMapper.map(ifJudgmentEntity, IfJudgment.class);
        return ifJudgment;
    }

    @Override
    public IfJudgment findIfJudgment(@NotNull String id) {
        IfJudgment ifJudgment = findOne(id);
        joinTemplate.joinQuery(ifJudgment);

        return ifJudgment;
    }

    @Override
    public List<IfJudgment> findIfJudgmentList(IfJudgmentQuery ifJudgmentQuery) {
        List<IfJudgmentEntity> ifJudgmentEntityList = ifJudgmentDao.findIfJudgmentList(ifJudgmentQuery);
        List<IfJudgment> ifJudgmentList = BeanMapper.mapList(ifJudgmentEntityList, IfJudgment.class);
        joinTemplate.joinQuery(ifJudgmentList);

        return ifJudgmentList;
    }

    @Override
    public IfJudgment findIfAddVariable(String id) {
        IfJudgment ifJudgment = findOne(id);

        if(ifJudgment==null){
            return null;
        }

        IfVariableQuery ifVariableQuery = new IfVariableQuery();
        ifVariableQuery.setStepId(id);
        List<IfVariable> ifVariableList = ifVariableService.findIfVariableList(ifVariableQuery);

        ifJudgment.setIfVariableList(ifVariableList);
        return ifJudgment;
    }


}