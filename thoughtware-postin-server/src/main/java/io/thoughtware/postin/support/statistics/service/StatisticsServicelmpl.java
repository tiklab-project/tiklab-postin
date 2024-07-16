package io.thoughtware.postin.support.statistics.service;

import io.thoughtware.postin.support.statistics.dao.StatisticsDao;
import io.thoughtware.postin.support.statistics.model.ApiNewCreateStatisticsModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class StatisticsServicelmpl implements StatisticsService {

    @Autowired
    StatisticsDao statisticsDao;


    @Override
    public List<Map<String, Object>> getApiStatusStatistics() {
        List<Map<String, Object>> statusList = statisticsDao.getStatusList();
        return statusList;
    }

    @Override
    public List<Map<String, Object>>  getApiNewCreateStatistics(ApiNewCreateStatisticsModel apiNewCreateStatisticsModel) {
        List<Map<String, Object>> apiNewCreateStatistics = statisticsDao.getApiNewCreateStatistics(apiNewCreateStatisticsModel);

        return apiNewCreateStatistics;
    }
}
































