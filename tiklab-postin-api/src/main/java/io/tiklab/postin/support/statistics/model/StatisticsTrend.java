package io.tiklab.postin.support.statistics.model;

import io.tiklab.core.BaseModel;
import io.tiklab.postin.annotation.ApiModel;
import io.tiklab.toolkit.beans.annotation.Mapper;

/**
 * 趋势统计 模型
 */
@ApiModel
@Mapper(targetName = "io.tiklab.postin.support.statistics.entity.StatisticsTrendEntity")
public class StatisticsTrend extends BaseModel {

    private String id;
    private String workspaceId;
    private String recordTime;

    private Integer publish;
    private Integer design;
    private Integer development;
    private Integer intergrated;
    private Integer test;
    private Integer complete;
    private Integer maintain;
    private Integer error;
    private Integer obsolete;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWorkspaceId() {
        return workspaceId;
    }

    public void setWorkspaceId(String workspaceId) {
        this.workspaceId = workspaceId;
    }

    public String getRecordTime() {
        return recordTime;
    }

    public void setRecordTime(String recordTime) {
        this.recordTime = recordTime;
    }

    public Integer getPublish() {
        return publish;
    }

    public void setPublish(Integer publish) {
        this.publish = publish;
    }

    public Integer getDesign() {
        return design;
    }

    public void setDesign(Integer design) {
        this.design = design;
    }

    public Integer getDevelopment() {
        return development;
    }

    public void setDevelopment(Integer development) {
        this.development = development;
    }

    public Integer getIntergrated() {
        return intergrated;
    }

    public void setIntergrated(Integer intergrated) {
        this.intergrated = intergrated;
    }

    public Integer getTest() {
        return test;
    }

    public void setTest(Integer test) {
        this.test = test;
    }

    public Integer getComplete() {
        return complete;
    }

    public void setComplete(Integer complete) {
        this.complete = complete;
    }

    public Integer getMaintain() {
        return maintain;
    }

    public void setMaintain(Integer maintain) {
        this.maintain = maintain;
    }

    public Integer getError() {
        return error;
    }

    public void setError(Integer error) {
        this.error = error;
    }

    public Integer getObsolete() {
        return obsolete;
    }

    public void setObsolete(Integer obsolete) {
        this.obsolete = obsolete;
    }
}


