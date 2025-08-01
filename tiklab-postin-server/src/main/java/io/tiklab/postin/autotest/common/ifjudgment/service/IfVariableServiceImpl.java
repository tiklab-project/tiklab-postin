package io.tiklab.postin.autotest.common.ifjudgment.service;

import io.tiklab.postin.autotest.common.ifjudgment.dao.IfVariableDao;
import io.tiklab.postin.autotest.common.ifjudgment.entity.IfVariableEntity;
import io.tiklab.postin.autotest.common.ifjudgment.model.IfVariable;
import io.tiklab.postin.autotest.common.ifjudgment.model.IfVariableQuery;
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
public class IfVariableServiceImpl implements IfVariableService {

    @Autowired
    IfVariableDao ifVariableDao;

    @Autowired
    JoinTemplate joinTemplate;


    @Override
    public String createIfVariable(@NotNull @Valid IfVariable ifVariable) {
        IfVariableEntity ifVariableEntity = BeanMapper.map(ifVariable, IfVariableEntity.class);

        String id = ifVariableDao.createIfVariable(ifVariableEntity);

        return id;
    }

    @Override
    public void updateIfVariable(@NotNull @Valid IfVariable ifVariable) {
        IfVariableEntity ifVariableEntity = BeanMapper.map(ifVariable, IfVariableEntity.class);
        ifVariableDao.updateIfVariable(ifVariableEntity);

    }

    @Override
    public void deleteIfVariable(@NotNull String id) {

        ifVariableDao.deleteIfVariable(id);
    }

    @Override
    public IfVariable findOne(String id) {
        IfVariableEntity ifVariableEntity = ifVariableDao.findIfVariable(id);

        IfVariable ifVariable = BeanMapper.map(ifVariableEntity, IfVariable.class);
        return ifVariable;
    }

    @Override
    public IfVariable findIfVariable(@NotNull String id) {
        IfVariable ifVariable = findOne(id);
        joinTemplate.joinQuery(ifVariable);

        return ifVariable;
    }

    @Override
    public List<IfVariable> findIfVariableList(IfVariableQuery ifVariableQuery) {
        List<IfVariableEntity> ifVariableEntityList = ifVariableDao.findIfVariableList(ifVariableQuery);
        List<IfVariable> ifVariableList = BeanMapper.mapList(ifVariableEntityList, IfVariable.class);
        joinTemplate.joinQuery(ifVariableList);

        return ifVariableList;
    }


}