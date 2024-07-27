package io.thoughtware.postin.support.statistics.entity;


import io.thoughtware.dal.jpa.annotation.*;

import java.io.Serializable;

/**
 * 趋势统计 实体
 */
@Entity
@Table(name="postin_statistic_trend")
public class StatisticsTrendEntity implements Serializable {

    @Id
    @GeneratorValue(length = 12)
    @Column(name = "id")
    private String id;

    @Column(name = "workspace_id")
    private String workspaceId;

    @Column(name = "record_time")
    private String recordTime;

    @Column(name = "publish")
    private Integer publish;

    @Column(name = "design")
    private Integer design;

    @Column(name = "development")
    private Integer development;

    @Column(name = "intergrated")
    private Integer intergrated;

    @Column(name = "test")
    private Integer test;

    @Column(name = "complete")
    private Integer complete;

    @Column(name = "maintain")
    private Integer maintain;

    @Column(name = "error")
    private Integer error;

    @Column(name = "obsolete")
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
