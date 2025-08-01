package io.tiklab.postin.autotest.common.stepcommon.model;

import io.tiklab.core.BaseModel;
import io.tiklab.postin.annotation.ApiModel;
import io.tiklab.postin.autotest.common.ifjudgment.model.IfJudgmentInstance;
import io.tiklab.postin.autotest.http.scene.instance.model.ApiSceneStepInstanceBind;
import io.tiklab.postin.autotest.http.unit.instance.model.ApiUnitInstance;
import io.tiklab.toolkit.beans.annotation.Mapper;
import io.tiklab.toolkit.join.annotation.Join;

/**
 * 公共步骤 模型
 */
@ApiModel
@Mapper
@Join
public class StepCommonInstance extends BaseModel{
    private String id;
    private String instanceId;
    private int result;
    private int sort;
    private String type;

    private ApiUnitInstance apiUnitInstance;
    private ApiSceneStepInstanceBind apiSceneStepInstanceBind;
    private IfJudgmentInstance ifJudgmentInstance;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public IfJudgmentInstance getIfJudgmentInstance() {
        return ifJudgmentInstance;
    }

    public void setIfJudgmentInstance(IfJudgmentInstance ifJudgmentInstance) {
        this.ifJudgmentInstance = ifJudgmentInstance;
    }

    public ApiUnitInstance getApiUnitInstance() {
        return apiUnitInstance;
    }

    public void setApiUnitInstance(ApiUnitInstance apiUnitInstance) {
        this.apiUnitInstance = apiUnitInstance;
    }

    public ApiSceneStepInstanceBind getApiSceneStepInstanceBind() {
        return apiSceneStepInstanceBind;
    }

    public void setApiSceneStepInstanceBind(ApiSceneStepInstanceBind apiSceneStepInstanceBind) {
        this.apiSceneStepInstanceBind = apiSceneStepInstanceBind;
    }
}
