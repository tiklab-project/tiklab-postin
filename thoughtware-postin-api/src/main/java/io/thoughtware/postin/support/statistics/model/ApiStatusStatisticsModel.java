package io.thoughtware.postin.support.statistics.model;

import io.thoughtware.core.BaseModel;
import io.thoughtware.postin.annotation.ApiModel;
import io.thoughtware.toolkit.beans.annotation.Mapper;
import io.thoughtware.toolkit.join.annotation.Join;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 获取系统统计信息 模型
 */
@ApiModel
@Join
public class ApiStatusStatisticsModel extends BaseModel {

    private List<Map<String, Object>>  statusMapList;

    public List<Map<String, Object>> getStatusMapList() {
        return statusMapList;
    }

    public void setStatusMapList(List<Map<String, Object>> statusMapList) {
        this.statusMapList = statusMapList;
    }
}
