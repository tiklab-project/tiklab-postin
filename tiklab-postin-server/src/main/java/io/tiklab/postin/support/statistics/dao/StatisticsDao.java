package io.tiklab.postin.support.statistics.dao;

import io.tiklab.dal.jpa.JpaTemplate;
import io.tiklab.postin.support.statistics.model.ApiStatisticsModel;
import io.tiklab.postin.workspace.model.Workspace;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.*;


/**
 * 统计 数据访问
 */
@Repository
public class StatisticsDao {

    private static Logger logger = LoggerFactory.getLogger(StatisticsDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 获取所有状态（去重）
     * @return
     */
    public List<Map<String, Object>> getStatusList(ApiStatisticsModel apiStatisticsModel) {
        // 1. 构建基础 SQL：只统计 postin_apix.version_id 为空的记录
        StringBuilder sql = new StringBuilder()
                .append("SELECT s.id, s.name, COUNT(p.id) AS count ")
                .append("FROM postin_api_status s ")
                // 在 JOIN 条件中增加 version_id IS NULL，确保只连接未发布/无版本的 API
                .append("LEFT JOIN postin_apix p ")
                .append("  ON p.status_id = s.id ")
                .append("  AND p.version_id IS NULL ");

        // 2. 根据是否传入 workspaceId 动态追加条件
        List<Object> params = new ArrayList<>();
        String workspaceId = apiStatisticsModel.getWorkspaceId();
        if (workspaceId != null && !workspaceId.isBlank()) {
            sql.append(" AND p.workspace_id = ? ");
            params.add(workspaceId);
        }

        // 3. 完成分组
        sql.append("GROUP BY s.id, s.name");

        // 4. 执行查询
        return params.isEmpty()
                ? jpaTemplate.getJdbcTemplate().queryForList(sql.toString())
                : jpaTemplate.getJdbcTemplate().queryForList(sql.toString(), params.toArray());
    }


    public List<Map<String, Object>> getApiNewCreateStatistics(ApiStatisticsModel apiStatisticsModel) {
        Date startTime = apiStatisticsModel.getStartTime();
        Date endTime = apiStatisticsModel.getEndTime();

        // 构造 SQL 查询
        String sql =
                " SELECT DATE(create_time) AS date, type, COUNT(*) AS count " +
                " FROM postin_node " +
                " WHERE type IN ('http', 'ws') " +
                " AND create_time BETWEEN ? AND ? " +
                " GROUP BY DATE(create_time), type " +
                " ORDER BY DATE(create_time), type";

        // 执行查询
        List<Map<String, Object>> resultList = jpaTemplate.getJdbcTemplate().queryForList(
                sql,
                startTime,
                endTime
        );

        // 将查询结果转为日期到类型和数量的映射
        Map<Date, Map<String, Integer>> resultMap = new HashMap<>();
        for (Map<String, Object> row : resultList) {
            java.sql.Date date = (java.sql.Date) row.get("date");
            String type = (String) row.get("type");
            Integer count = ((Number) row.get("count")).intValue();
            resultMap.computeIfAbsent(date, k -> new HashMap<>()).put(type, count);
        }

        // 生成日期范围
        List<java.sql.Date> dates = generateDateRange(startTime, endTime);

        List<Map<String, Object>> completeDataList = new ArrayList<>();
        for (java.sql.Date date : dates) {
            Map<String, Object> map = new HashMap<>();
            map.put("date", date.toString());  // 这已经是 YYYY-MM-DD 格式

            Map<String, Integer> typeCounts = resultMap.getOrDefault(date, new HashMap<>());
            map.put("http", typeCounts.getOrDefault("http", 0));
            map.put("ws", typeCounts.getOrDefault("ws", 0));

            completeDataList.add(map);
        }

        return completeDataList;
    }

    // 生成日期范围
    private List<java.sql.Date> generateDateRange(Date startDate, Date endDate) {
        List<java.sql.Date> dates = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        java.sql.Date end = new java.sql.Date(endDate.getTime());
        while (!calendar.getTime().after(end)) {
            dates.add(new java.sql.Date(calendar.getTimeInMillis()));
            calendar.add(Calendar.DAY_OF_MONTH, 1);
        }
        return dates;
    }
}