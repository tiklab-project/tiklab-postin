package io.tiklab.postin.support.backups.model;

import io.tiklab.beans.annotation.Mapper;
import io.tiklab.join.annotation.Join;

@Join
@Mapper
public class Backups {

    private String id;

    // 类型：backups --> 备份 ，restore --> 恢复
    private String type;

    // 运行状态 run success error
    private String runState;

    // 创建时间
    private String createTime;


    private String dir;

    // 日志
    private String log;

    // 定时同步
    private Boolean scheduled;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRunState() {
        return runState;
    }

    public void setRunState(String runState) {
        this.runState = runState;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }

    public String getLog() {
        return log;
    }

    public void setLog(String log) {
        this.log = log;
    }

    public Boolean getScheduled() {
        return scheduled;
    }

    public void setScheduled(Boolean scheduled) {
        this.scheduled = scheduled;
    }

    @Override
    public String toString() {
        return "Backups{" +
                "id='" + id + '\'' +
                ", type='" + type + '\'' +
                ", runState='" + runState + '\'' +
                ", createTime='" + createTime + '\'' +
                ", dir='" + dir + '\'' +
                ", log='" + log + '\'' +
                ", scheduled=" + scheduled +
                '}';
    }
}
