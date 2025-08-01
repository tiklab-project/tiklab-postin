package io.tiklab.postin.autotest.testplan.quartz.entity;


import io.tiklab.dal.jpa.annotation.*;

import java.io.Serializable;

/**
 * 定时任务 实体
 */
@Entity @Table(name="autotest_plan_quartz_time")
public class QuartzTimePlanEntity implements Serializable {

    @Id
    @GeneratorValue(length = 12)
    @Column(name = "id")
    private String id;

    @Column(name = "quartz_plan_id")
    private String quartzPlanId;

    @Column(name = "cron")
    private String cron;

    @Column(name = "exe_type")
    private Integer exeType;

    @Column(name = "week")
    private Integer week;

    @Column(name = "time")
    private String time;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getQuartzPlanId() {
        return quartzPlanId;
    }

    public void setQuartzPlanId(String quartzPlanId) {
        this.quartzPlanId = quartzPlanId;
    }

    public String getCron() {
        return cron;
    }

    public void setCron(String cron) {
        this.cron = cron;
    }

    public Integer getExeType() {
        return exeType;
    }

    public void setExeType(Integer exeType) {
        this.exeType = exeType;
    }

    public Integer getWeek() {
        return week;
    }

    public void setWeek(Integer week) {
        this.week = week;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
