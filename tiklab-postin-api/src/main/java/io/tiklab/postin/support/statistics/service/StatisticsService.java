package io.tiklab.postin.support.statistics.service;

import io.tiklab.postin.support.statistics.model.ApiStatisticsModel;

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
    Map<String, Object> getApiStatusStatistics(ApiStatisticsModel apiStatisticsModel);

    /**
     * 接口新增的统计信息
     * @return
     */
    List<Map<String, Object>>  getApiNewCreateStatistics(ApiStatisticsModel apiStatisticsModel);

}