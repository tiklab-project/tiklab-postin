package io.tiklab.postin.autotest.common.forstep.service;

import io.tiklab.postin.autotest.common.forstep.dao.ForStepDao;
import io.tiklab.postin.autotest.common.forstep.entity.ForStepEntity;
import io.tiklab.postin.autotest.common.forstep.model.ForStep;
import io.tiklab.postin.autotest.common.forstep.model.ForStepQuery;
import io.tiklab.toolkit.beans.BeanMapper;
import io.tiklab.toolkit.join.JoinTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* for循环 服务
*/
@Service
public class ForStepServiceImpl implements ForStepService {

    @Autowired
    ForStepDao forStepDao;

    @Autowired
    JoinTemplate joinTemplate;


    @Override
    public String createForStep(@NotNull @Valid ForStep forStep) {
        ForStepEntity forStepEntity = BeanMapper.map(forStep, ForStepEntity.class);

        String id = forStepDao.createForStep(forStepEntity);

        return id;
    }

    @Override
    public void updateForStep(@NotNull @Valid ForStep forStep) {
        ForStepEntity forStepEntity = BeanMapper.map(forStep, ForStepEntity.class);
        forStepDao.updateForStep(forStepEntity);

    }

    @Override
    public void deleteForStep(@NotNull String id) {

        forStepDao.deleteForStep(id);
    }

    @Override
    public ForStep findOne(String id) {
        ForStepEntity forStepEntity = forStepDao.findForStep(id);

        ForStep forStep = BeanMapper.map(forStepEntity, ForStep.class);
        return forStep;
    }

    @Override
    public ForStep findForStep(@NotNull String id) {
        ForStep forStep = findOne(id);
        joinTemplate.joinQuery(forStep);

        return forStep;
    }

    @Override
    public List<ForStep> findForStepList(ForStepQuery forStepQuery) {
        List<ForStepEntity> forStepEntityList = forStepDao.findForStepList(forStepQuery);
        List<ForStep> forStepList = BeanMapper.mapList(forStepEntityList, ForStep.class);
        joinTemplate.joinQuery(forStepList);

        return forStepList;
    }


}