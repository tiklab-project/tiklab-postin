package io.thoughtware.postin.support.system.model;

import io.thoughtware.core.BaseModel;
import io.thoughtware.postin.annotation.ApiModel;
import io.thoughtware.toolkit.beans.annotation.Mapper;
import io.thoughtware.toolkit.join.annotation.Join;

/**
 * 获取系统统计信息 模型
 */
@ApiModel
@Join
@Mapper
public class SystemCount extends BaseModel {

    private Integer userCount;
    private Integer orgaCount;
    private Integer userGroupCount;
    private Integer roleCount;
    private Integer userDirCount;
    private Integer msgNoticeCount;
    private Integer msgSendTypeCount;
    private Integer installPluginCount;
    private String lastBackupTime;


    public Integer getUserCount() {
        return userCount;
    }

    public void setUserCount(Integer userCount) {
        this.userCount = userCount;
    }

    public Integer getOrgaCount() {
        return orgaCount;
    }

    public void setOrgaCount(Integer orgaCount) {
        this.orgaCount = orgaCount;
    }

    public Integer getUserGroupCount() {
        return userGroupCount;
    }

    public void setUserGroupCount(Integer userGroupCount) {
        this.userGroupCount = userGroupCount;
    }

    public Integer getRoleCount() {
        return roleCount;
    }

    public void setRoleCount(Integer roleCount) {
        this.roleCount = roleCount;
    }

    public Integer getUserDirCount() {
        return userDirCount;
    }

    public void setUserDirCount(Integer userDirCount) {
        this.userDirCount = userDirCount;
    }

    public Integer getMsgNoticeCount() {
        return msgNoticeCount;
    }

    public void setMsgNoticeCount(Integer msgNoticeCount) {
        this.msgNoticeCount = msgNoticeCount;
    }

    public Integer getMsgSendTypeCount() {
        return msgSendTypeCount;
    }

    public void setMsgSendTypeCount(Integer msgSendTypeCount) {
        this.msgSendTypeCount = msgSendTypeCount;
    }

    public Integer getInstallPluginCount() {
        return installPluginCount;
    }

    public void setInstallPluginCount(Integer installPluginCount) {
        this.installPluginCount = installPluginCount;
    }

    public String getLastBackupTime() {
        return lastBackupTime;
    }

    public void setLastBackupTime(String lastBackupTime) {
        this.lastBackupTime = lastBackupTime;
    }
}
