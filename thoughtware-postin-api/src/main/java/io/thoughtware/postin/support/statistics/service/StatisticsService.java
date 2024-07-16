package io.thoughtware.postin.support.statistics.service;

import io.thoughtware.postin.support.statistics.model.ApiNewCreateStatisticsModel;
import io.thoughtware.postin.support.statistics.model.ApiStatusStatisticsModel;

import java.util.List;
import java.util.Map;

/**
* 获取系统统计信息
*/
public interface StatisticsService {
    /**
     * 接口状态的统计信息
     * @return
     */
    List<Map<String, Object>> getApiStatusStatistics();

    /**
     * 接口新增的统计信息
     * @return
     */
    List<Map<String, Object>>  getApiNewCreateStatistics(ApiNewCreateStatisticsModel apiNewCreateStatisticsModel);

}