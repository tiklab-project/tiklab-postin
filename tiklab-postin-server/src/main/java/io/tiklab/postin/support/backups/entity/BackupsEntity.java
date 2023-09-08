package io.tiklab.postin.support.backups.entity;

import io.tiklab.dal.jpa.annotation.*;

@Entity
@Table(name="postin_db_backups")
public class BackupsEntity {

    @Id
    @GeneratorValue(length = 12)
    @Column(name = "id",length = 12)
    private String id;

    @Column(name = "type")
    private String type;

    @Column(name = "run_state")
    private String runState;

    @Column(name = "log")
    private String log;

    @Column(name = "scheduled")
    private String scheduled;

    @Column(name = "dir")
    private String dir;

    @Column(name = "create_time")
    private String createTime;


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

    public String getLog() {
        return log;
    }

    public void setLog(String log) {
        this.log = log;
    }

    public String getScheduled() {
        return scheduled;
    }

    public void setScheduled(String scheduled) {
        this.scheduled = scheduled;
    }

    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
