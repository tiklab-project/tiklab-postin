package io.thoughtware.postin.support.statistics.service;

import io.thoughtware.postin.support.statistics.dao.StatisticsDao;
import io.thoughtware.postin.support.statistics.model.ApiStatisticsModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StatisticsServicelmpl implements StatisticsService {

    @Autowired
    StatisticsDao statisticsDao;


    @Override
    public Map<String, Object> getApiStatusStatistics(ApiStatisticsModel apiStatisticsModel) {
        List<Map<String, Object>> statusList = statisticsDao.getStatusList(apiStatisticsModel);


        List<Map<String, Object>> resultList = new ArrayList<>();
        Map<String, Object> resultMap = new HashMap<>();
        int total = 0;

        for (Map<String, Object> status : statusList) {
            String originalName = (String) status.get("id");
            String newName = originalName.substring(0, originalName.length() - 2);

            Object countObj = status.getOrDefault("count", 0);
            int count = (countObj != null) ? ((Number) countObj).intValue() : 0;
            total += count;

            resultMap.put(newName, count);

            Map<String, Object> map = new HashMap<>();
            map.put("name", status.get("name"));
            map.put("count", count);
            resultList.add(map);
        }

        resultMap.put("total",total);

        Map<String, Object> allMap = new HashMap<>();
        allMap.put("number",resultMap);
        allMap.put("list",resultList);

        return allMap;
    }

    @Override
    public List<Map<String, Object>>  getApiNewCreateStatistics(ApiStatisticsModel apiStatisticsModel) {
        List<Map<String, Object>> apiNewCreateStatistics = statisticsDao.getApiNewCreateStatistics(apiStatisticsModel);

        return apiNewCreateStatistics;
    }
}
































