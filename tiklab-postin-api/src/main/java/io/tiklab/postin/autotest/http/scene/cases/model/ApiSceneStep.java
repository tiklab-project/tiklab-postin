package io.tiklab.postin.autotest.http.scene.cases.model;

import io.tiklab.postin.autotest.http.unit.cases.model.ApiUnitCase;
import io.tiklab.postin.autotest.http.unit.cases.model.ApiUnitCaseDataConstruction;
import io.tiklab.toolkit.beans.annotation.Mapper;
import io.tiklab.toolkit.beans.annotation.Mapping;
import io.tiklab.toolkit.beans.annotation.Mappings;
import io.tiklab.core.BaseModel;
import io.tiklab.toolkit.join.annotation.Join;
import io.tiklab.toolkit.join.annotation.JoinField;
import io.tiklab.postin.annotation.ApiModel;
import io.tiklab.postin.annotation.ApiProperty;

/**
 * 接口场景下绑定的步骤 模型
 */
@ApiModel
@Mapper
@Join
public class ApiSceneStep extends BaseModel{

    @ApiProperty(name="id",desc="id")
    private String id;

    @ApiProperty(name="apiSceneId",desc="场景")
    private String apiSceneId;

    @ApiProperty(name="apiUnit",desc="绑定的单元用例")
    @Mappings({
            @Mapping(source = "apiUnit.id",target = "apiUnitId")
    })
    @JoinField(key = "id")
    private ApiUnitCase apiUnit;

    private ApiUnitCaseDataConstruction apiUnitCaseDataConstruction;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getApiSceneId() {
        return apiSceneId;
    }

    public void setApiSceneId(String apiSceneId) {
        this.apiSceneId = apiSceneId;
    }

    public ApiUnitCase getApiUnit() {
        return apiUnit;
    }

    public void setApiUnit(ApiUnitCase apiUnit) {
        this.apiUnit = apiUnit;
    }

    public ApiUnitCaseDataConstruction getApiUnitCaseDataConstruction() {
        return apiUnitCaseDataConstruction;
    }

    public void setApiUnitCaseDataConstruction(ApiUnitCaseDataConstruction apiUnitCaseDataConstruction) {
        this.apiUnitCaseDataConstruction = apiUnitCaseDataConstruction;
    }
}
