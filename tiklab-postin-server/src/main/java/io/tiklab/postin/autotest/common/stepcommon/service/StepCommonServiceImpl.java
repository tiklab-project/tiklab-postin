package io.tiklab.postin.autotest.common.stepcommon.service;


import java.sql.Timestamp;
import java.util.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import io.tiklab.postin.api.http.definition.model.OperateDatabase;
import io.tiklab.postin.api.http.definition.model.OperateScript;
import io.tiklab.postin.api.http.definition.service.OperateDatabaseService;
import io.tiklab.postin.api.http.definition.service.OperateScriptService;
import io.tiklab.postin.autotest.common.forstep.model.ForStep;
import io.tiklab.postin.autotest.common.forstep.service.ForStepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.tiklab.postin.autotest.common.ifjudgment.model.IfJudgment;
import io.tiklab.postin.autotest.common.ifjudgment.service.IfJudgmentService;
import io.tiklab.postin.autotest.common.stepcommon.dao.StepCommonDao;
import io.tiklab.postin.autotest.common.stepcommon.entity.StepCommonEntity;
import io.tiklab.postin.autotest.common.stepcommon.model.StepCommon;
import io.tiklab.postin.autotest.common.stepcommon.model.StepCommonQuery;
import io.tiklab.postin.autotest.http.scene.cases.model.ApiSceneStep;
import io.tiklab.postin.autotest.http.scene.cases.service.ApiSceneStepService;
import io.tiklab.postin.autotest.http.unit.cases.model.ApiUnitCaseDataConstruction;
import io.tiklab.postin.autotest.http.unit.cases.service.ApiUnitCaseService;
import io.tiklab.postin.common.MagicValue;
import io.tiklab.toolkit.beans.BeanMapper;
import io.tiklab.toolkit.join.JoinTemplate;
import io.tiklab.postin.autotest.common.stepcommon.model.StepCommonDragSortRequest;

/**
* 功能用例下步骤 服务
*/
@Service
public class StepCommonServiceImpl implements StepCommonService {

    @Autowired
    StepCommonDao stepCommonDao;

    @Autowired
    JoinTemplate joinTemplate;

    @Autowired
    ApiUnitCaseService apiUnitCaseService;
    @Autowired
    ApiSceneStepService apiSceneStepService;
    @Autowired
    IfJudgmentService ifJudgmentService;
    @Autowired
    OperateDatabaseService operateDatabaseService;
    @Autowired
    OperateScriptService operateScriptService;
    @Autowired
    ForStepService forStepService;


    @Override
    public String createStepCommon(@NotNull @Valid StepCommon stepCommon) {
        Integer maxSort = stepCommonDao.biggerSort(stepCommon.getCaseId(), stepCommon.getParentId());
        stepCommon.setSort(maxSort+1);
        StepCommonEntity stepCommonEntity = BeanMapper.map(stepCommon, StepCommonEntity.class);
        stepCommonEntity.setCreateTime(new Timestamp(System.currentTimeMillis()));
        String id = stepCommonDao.createStepCommon(stepCommonEntity);

        String type = stepCommon.getType();
        switch (type){
            case MagicValue.CASE_TYPE_API_SCENE:
                break;
            case MagicValue.TEST_STEP_IF:
                IfJudgment ifJudgment = stepCommon.getIfJudgment();
                ifJudgment.setId(id);
                ifJudgmentService.createIfJudgment(ifJudgment);
                break;
            case MagicValue.TEST_STEP_SCRIPT:
                OperateScript script = stepCommon.getOperateScript();
                script.setId(id);
                script.setOperationId(id);
                operateScriptService.createOperateScript(script);
                break;
            case MagicValue.TEST_STEP_DATABASE:
                OperateDatabase database = stepCommon.getOperateDatabase();
                database.setId(id);
                database.setOperationId(id);
                operateDatabaseService.createOperateDatabase(database);
                break;
            case MagicValue.TEST_STEP_FOR:
                ForStep forStep = stepCommon.getForStep();
                forStep.setId(id);
                forStepService.createForStep(forStep);
                break;
            default:
                break;
        }

        return id;
    }

    @Override
    public void updateStepCommon(@NotNull @Valid StepCommon stepCommon) {
        // 移除原有的排序逻辑，直接更新
        StepCommonEntity entity = BeanMapper.map(stepCommon, StepCommonEntity.class);
        stepCommonDao.updateStepCommon(entity);
    }

    @Override
    public void deleteStepCommon(@NotNull String id) {
        StepCommon stepCommon = findStepCommon(id);
        if(stepCommon== null){return;}
        Integer sort = stepCommon.getSort();
        String parentId = stepCommon.getParentId();

        // 只更新当前层级下的排序
        List<StepCommonEntity> currentLevelSteps = stepCommonDao.findStepCommonListByParentId(stepCommon.getCaseId(), parentId);
        
        for(StepCommonEntity stepCommonEntity: currentLevelSteps){
            if(stepCommonEntity.getSort() > sort){
                stepCommonEntity.setSort(stepCommonEntity.getSort()-1);
                stepCommonDao.updateStepCommon(stepCommonEntity);
            }
        }

        // 删除步骤
        stepCommonDao.deleteStepCommon(id);
        
        // 删除关联的子步骤
        String stepType = stepCommon.getType();
        switch (stepType){
            case MagicValue.CASE_TYPE_API_SCENE:
                apiSceneStepService.deleteApiSceneStep(id);
                break;
            case MagicValue.TEST_STEP_IF:
                ifJudgmentService.deleteIfJudgment(id);
                break;
            case MagicValue.TEST_STEP_SCRIPT:
                operateScriptService.deleteOperateScript(id);
                break;
            case MagicValue.TEST_STEP_DATABASE:
                operateDatabaseService.deleteOperateDatabase(id);
                break;
            case MagicValue.TEST_STEP_FOR:
                forStepService.deleteForStep(id);
                break;
            default:
                break;
        }
    }

    @Override
    public void deleteAllStepCommon(String caseId) {
        StepCommonQuery stepCommonQuery = new StepCommonQuery();
        stepCommonQuery.setCaseId(caseId);
        List<StepCommon> stepCommonList = findStepCommonList(stepCommonQuery);
        for(StepCommon stepCommon : stepCommonList){
            deleteStepCommon(stepCommon.getId());
        }
    }


    @Override
    public StepCommon findOne(String id) {
        StepCommonEntity stepCommonEntity = stepCommonDao.findStepCommon(id);

        StepCommon stepCommon = BeanMapper.map(stepCommonEntity, StepCommon.class);
        return stepCommon;
    }

    @Override
    public StepCommon findStepCommon(@NotNull String id) {
        StepCommon stepCommon = findOne(id);
        joinTemplate.joinQuery(stepCommon);

        return stepCommon;
    }

    @Override
    public int findStepNum(String caseId) {
        int stepNum = stepCommonDao.findStepNum(caseId);
        return stepNum;
    }


    @Override
    public List<StepCommon> findStepCommonList(StepCommonQuery stepCommonQuery) {
        List<StepCommonEntity> stepCommonEntityList = stepCommonDao.findStepCommonList(stepCommonQuery);
        List<StepCommon> stepCommonList = BeanMapper.mapList(stepCommonEntityList, StepCommon.class);

        for(StepCommon stepCommon:stepCommonList){
            String stepType = stepCommon.getType();
            switch (stepType){
                case MagicValue.CASE_TYPE_API_SCENE:
                    ApiSceneStep apiSceneStep = apiSceneStepService.findApiSceneStep(stepCommon.getId());
                    if(apiSceneStep==null){break;}
                    ApiUnitCaseDataConstruction apiUnitCaseDataConstruction = apiUnitCaseService.findApiUnitCaseExt(apiSceneStep.getApiUnit());
                    apiSceneStep.setApiUnitCaseDataConstruction(apiUnitCaseDataConstruction);
                    stepCommon.setApiSceneStep(apiSceneStep);
                    break;
                case MagicValue.TEST_STEP_IF:
                    IfJudgment ifJudgment = ifJudgmentService.findIfAddVariable(stepCommon.getId());
                    stepCommon.setIfJudgment(ifJudgment);
                    break;
                case MagicValue.TEST_STEP_SCRIPT:
                    OperateScript operateScript = operateScriptService.findOperateScript(stepCommon.getId());
                    stepCommon.setOperateScript(operateScript);
                    break;
                case MagicValue.TEST_STEP_DATABASE:
                    OperateDatabase operateDatabase = operateDatabaseService.findOperateDatabase(stepCommon.getId());
                    stepCommon.setOperateDatabase(operateDatabase);
                    break;
                case MagicValue.TEST_STEP_FOR:
                    ForStep forStep = forStepService.findForStep(stepCommon.getId());
                    stepCommon.setForStep(forStep);
                    break;
                default:
                    break;
            }
        }

        // 构建树形结构
        return buildTree(stepCommonList);
    }

    /**
     * 构建树形结构
     * @param stepCommonList 扁平的步骤列表
     * @return 树形结构的根节点列表
     */
    private List<StepCommon> buildTree(List<StepCommon> stepCommonList) {
        // 创建id到对象的映射，便于查找
        Map<String, StepCommon> idMap = new HashMap<>();
        for (StepCommon stepCommon : stepCommonList) {
            stepCommon.setChildren(new ArrayList<>());  // 初始化子节点列表
            idMap.put(stepCommon.getId(), stepCommon);
        }

        // 根节点列表
        List<StepCommon> rootNodes = new ArrayList<>();

        for (StepCommon stepCommon : stepCommonList) {
            String parentId = stepCommon.getParentId();

            // 如果parentId为空或为null，则为根节点
            if (parentId == null || parentId.trim().isEmpty()) {
                rootNodes.add(stepCommon);
            } else {
                // 找到父节点并添加到其children中
                StepCommon parent = idMap.get(parentId);
                if (parent != null) {
                    parent.getChildren().add(stepCommon);
                } else {
                    // 如果找不到父节点，也当作根节点处理（数据异常情况）
                    rootNodes.add(stepCommon);
                }
            }
        }

        // 对所有节点的子节点按sort字段排序
        sortTreeNodes(rootNodes);

        return rootNodes;
    }

    /**
     * 递归排序树节点
     * @param nodes 节点列表
     */
    private void sortTreeNodes(List<StepCommon> nodes) {
        if (nodes == null || nodes.isEmpty()) {
            return;
        }

        // 对当前层级的节点按sort排序
        nodes.sort(Comparator.comparing(StepCommon::getSort, Comparator.nullsLast(Integer::compareTo)));

        // 递归排序子节点
        for (StepCommon node : nodes) {
            if (node.getChildren() != null && !node.getChildren().isEmpty()) {
                sortTreeNodes(node.getChildren());
            }
        }
    }

    /**
     * 获取公共步骤列表
     */
    @Override
    public List<StepCommon> getStepCommonList(String caseId){
        StepCommonQuery stepCommonQuery = new StepCommonQuery();
        stepCommonQuery.setCaseId(caseId);
        List<StepCommon> stepCommonList = findStepCommonList(stepCommonQuery);

        return stepCommonList;
    }

    @Override
    public void dragSortStepCommon(StepCommonDragSortRequest dragSortRequest) {
        String sourceStepId = dragSortRequest.getSourceStepId();
        String targetParentId = dragSortRequest.getTargetParentId();
        Integer targetSort = dragSortRequest.getTargetSort();
        String caseId = dragSortRequest.getCaseId();

        // 获取源步骤信息
        StepCommon sourceStep = findStepCommon(sourceStepId);
        if (sourceStep == null) {
            throw new RuntimeException("源步骤不存在");
        }

        String sourceParentId = sourceStep.getParentId();
        Integer sourceSort = sourceStep.getSort();

        // 判断是否为同级拖拽
        boolean isSameLevel = Objects.equals(sourceParentId, targetParentId);

        if (isSameLevel) {
            handleSameLevelDrag(caseId, sourceParentId, sourceStepId, sourceSort, targetSort);
        } else {
            handleCrossLevelDrag(caseId, sourceStepId, sourceParentId, sourceSort, targetParentId, targetSort);
        }
    }

    /**
     * 处理同级拖拽
     *
     */
    private void handleSameLevelDrag(String caseId, String parentId, String sourceStepId,
                                     Integer sourceSort, Integer targetSort) {
        // 如果位置没有变化，直接返回
        if (sourceSort.equals(targetSort)) {
            return;
        }

        List<StepCommonEntity> currentLevelSteps = stepCommonDao.findStepCommonListByParentId(caseId, parentId);

        if (sourceSort < targetSort) {
            // 向后拖拽：把前面的步骤拖到后面
            for (StepCommonEntity step : currentLevelSteps) {
                if (step.getId().equals(sourceStepId)) {
                    continue;
                }

                Integer stepSort = step.getSort();
                // 原来在源步骤后面、目标位置及之前的步骤，都要往前移
                if (stepSort > sourceSort && stepSort <= targetSort) {
                    step.setSort(stepSort - 1);
                    stepCommonDao.updateStepCommon(step);
                }
            }

            // 源步骤移到目标位置
            updateStepSort(sourceStepId, targetSort);

        } else {
            // 向前拖拽：把后面的步骤拖到前面
            for (StepCommonEntity step : currentLevelSteps) {
                if (step.getId().equals(sourceStepId)) {
                    continue;
                }

                Integer stepSort = step.getSort();
                // 原来在目标位置及之后、源步骤之前的步骤，都要往后移
                if (stepSort >= targetSort && stepSort < sourceSort) {
                    step.setSort(stepSort + 1);
                    stepCommonDao.updateStepCommon(step);
                }
            }

            // 源步骤移到目标位置
            updateStepSort(sourceStepId, targetSort);
        }
    }

    /**
     * 处理跨层级拖拽
     */
    private void handleCrossLevelDrag(String caseId, String sourceStepId, String sourceParentId,
                                      Integer sourceSort, String targetParentId, Integer targetSort) {

        // 调整源层级：移除源步骤后，后面的步骤往前移
        adjustSourceLevel(caseId, sourceParentId, sourceSort);

        // 调整目标层级：在目标位置插入，原位置及之后的步骤往后移
        adjustTargetLevel(caseId, targetParentId, targetSort);

        // 更新源步骤的父级和排序
        updateStepParentAndSort(sourceStepId, targetParentId, targetSort);
    }

    /**
     * 调整源层级排序（移除步骤后的排序调整）
     */
    private void adjustSourceLevel(String caseId, String sourceParentId, Integer sourceSort) {
        List<StepCommonEntity> sourceLevelSteps = stepCommonDao.findStepCommonListByParentId(caseId, sourceParentId);

        for (StepCommonEntity step : sourceLevelSteps) {
            if (step.getSort() > sourceSort) {
                step.setSort(step.getSort() - 1);
                stepCommonDao.updateStepCommon(step);
            }
        }
    }

    /**
     * 调整目标层级排序（插入步骤前的排序调整）
     */
    private void adjustTargetLevel(String caseId, String targetParentId, Integer targetSort) {
        List<StepCommonEntity> targetLevelSteps = stepCommonDao.findStepCommonListByParentId(caseId, targetParentId);

        for (StepCommonEntity step : targetLevelSteps) {
            if (step.getSort() >= targetSort) {
                step.setSort(step.getSort() + 1);
                stepCommonDao.updateStepCommon(step);
            }
        }
    }

    /**
     * 更新步骤排序
     */
    private void updateStepSort(String stepId, Integer targetSort) {
        StepCommonEntity stepEntity = stepCommonDao.findStepCommon(stepId);
        if (stepEntity != null) {
            stepEntity.setSort(targetSort);
            stepCommonDao.updateStepCommon(stepEntity);
        }
    }

    /**
     * 更新步骤父级和排序
     */
    private void updateStepParentAndSort(String stepId, String targetParentId, Integer targetSort) {
        StepCommonEntity stepEntity = stepCommonDao.findStepCommon(stepId);
        if (stepEntity != null) {
            stepEntity.setParentId(targetParentId);
            stepEntity.setSort(targetSort);
            stepCommonDao.updateStepCommon(stepEntity);
        }
    }

}