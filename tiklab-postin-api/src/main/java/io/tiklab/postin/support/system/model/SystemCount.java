package io.tiklab.postin.support.system.model;

import io.tiklab.core.BaseModel;
import io.tiklab.postin.annotation.ApiModel;
import io.tiklab.toolkit.beans.annotation.Mapper;
import io.tiklab.toolkit.join.annotation.Join;

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

}
