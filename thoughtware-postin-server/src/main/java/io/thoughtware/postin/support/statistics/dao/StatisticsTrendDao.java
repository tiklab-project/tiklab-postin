package io.thoughtware.postin.support.statistics.dao;

import io.thoughtware.dal.jpa.JpaTemplate;
import io.thoughtware.dal.jpa.criterial.condition.DeleteCondition;
import io.thoughtware.postin.support.statistics.entity.StatisticsTrendEntity;
import io.thoughtware.postin.support.statistics.model.StatisticsTrendQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 接口环境 数据访问
 */
@Repository
public class StatisticsTrendDao {

    private static Logger logger = LoggerFactory.getLogger(StatisticsTrendDao.class);

    @Autowired
    JpaTemplate jpaTemplate;


    public String createStatisticsTrend(StatisticsTrendEntity statisticsTrendEntity) {
        String workspaceId = statisticsTrendEntity.getWorkspaceId();
        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder.append("SELECT ")
                .append(" SUM(CASE WHEN status_id='publishid' THEN 1 ELSE 0 END) AS publish, ")
                .append(" SUM(CASE WHEN status_id='designId' THEN 1 ELSE 0 END) AS design, ")
                .append(" SUM(CASE WHEN status_id='developmentid' THEN 1 ELSE 0 END) AS development, ")
                .append(" SUM(CASE WHEN status_id='intergratedid' THEN 1 ELSE 0 END) AS intergrated, ")
                .append(" SUM(CASE WHEN status_id='testid' THEN 1 ELSE 0 END) AS test, ")
                .append(" SUM(CASE WHEN status_id='completeid' THEN 1 ELSE 0 END) AS complete, ")
                .append(" SUM(CASE WHEN status_id='maintainid' THEN 1 ELSE 0 END) AS maintain, ")
                .append(" SUM(CASE WHEN status_id='errorid' THEN 1 ELSE 0 END) AS error, ")
                .append(" SUM(CASE WHEN status_id='obsoleteid' THEN 1 ELSE 0 END) AS obsolete ")
                .append(" FROM postin_apix api")
                .append(" WHERE api.workspace_id = '").append(workspaceId).append("'");

        Map<String, Object> maps = jpaTemplate.getJdbcTemplate().queryForMap(sqlBuilder.toString());
        // 从 Map 中获取值，并转换为 Number 类型
        Object publishObj = maps.getOrDefault("publish", 0);
        Object designObj = maps.getOrDefault("design", 0);
        Object developmentObj = maps.getOrDefault("development", 0);
        Object intergratedObj = maps.getOrDefault("intergrated", 0);
        Object testObj = maps.getOrDefault("test", 0);
        Object completeObj = maps.getOrDefault("complete", 0);
        Object maintainObj = maps.getOrDefault("maintain", 0);
        Object errorObj = maps.getOrDefault("error", 0);
        Object obsoleteObj = maps.getOrDefault("obsolete", 0);

        // 将 Number 转换为 Integer，处理可能的 null 值
        int publish = (publishObj != null) ? ((Number) publishObj).intValue() : 0;
        int design = (designObj != null) ? ((Number) designObj).intValue() : 0;
        int development = (developmentObj != null) ? ((Number) developmentObj).intValue() : 0;
        int intergrated = (intergratedObj != null) ? ((Number) intergratedObj).intValue() : 0;
        int test = (testObj != null) ? ((Number) testObj).intValue() : 0;
        int complete = (completeObj != null) ? ((Number) completeObj).intValue() : 0;
        int maintain = (maintainObj != null) ? ((Number) maintainObj).intValue() : 0;
        int error = (errorObj != null) ? ((Number) errorObj).intValue() : 0;
        int obsolete = (obsoleteObj != null) ? ((Number) obsoleteObj).intValue() : 0;

        // 将转换后的值设置到实体对象中
        statisticsTrendEntity.setPublish(publish);
        statisticsTrendEntity.setDesign(design);
        statisticsTrendEntity.setDevelopment(development);
        statisticsTrendEntity.setIntergrated(intergrated);
        statisticsTrendEntity.setTest(test);
        statisticsTrendEntity.setComplete(complete);
        statisticsTrendEntity.setMaintain(maintain);
        statisticsTrendEntity.setError(error);
        statisticsTrendEntity.setObsolete(obsolete);


        SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
        String format = formater.format(new Date());
        statisticsTrendEntity.setRecordTime(format);

        return jpaTemplate.save(statisticsTrendEntity,String.class);
    }


    public List<Map<String, Object>> getStatisticsTrend(StatisticsTrendQuery statisticsTrendQuery) {
        String startTime = statisticsTrendQuery.getStartTime();
        String endTime = statisticsTrendQuery.getEndTime();
        String repositoryId = statisticsTrendQuery.getWorkspaceId();

        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder.append("SELECT DATE(record_time) AS date,SUM(publish) AS publish, SUM(design) AS design, SUM(development) AS development,SUM(intergrated) AS intergrated,")
                .append(" SUM(test) AS test, SUM(complete) AS complete, SUM(maintain) AS maintain,  SUM(error) AS error, SUM(obsolete) AS obsolete")
                .append(" FROM postin_statistic_trend trend ")
                .append("WHERE record_time BETWEEN ? AND ? ");

        // 添加 repository_id 的条件
        if (repositoryId != null) {
            sqlBuilder.append(" AND trend.workspace_id = ? ");
        }

        sqlBuilder.append("GROUP BY DATE(record_time) ")
                .append("ORDER BY DATE(record_time) ");

        String sql = sqlBuilder.toString();

        // 执行查询
        List<Map<String, Object>> resultList;
        if (repositoryId == null) {
            resultList = jpaTemplate.getJdbcTemplate().queryForList(
                    sql,
                    startTime,
                    endTime
            );
        } else {
            resultList = jpaTemplate.getJdbcTemplate().queryForList(
                    sql,
                    startTime,
                    endTime,
                    repositoryId
            );
        }

        return resultList;
    }


    public void deleteStatisticsTrend(String id){
        jpaTemplate.delete(StatisticsTrendEntity.class,id);
    }


    public List<StatisticsTrendEntity> findAllStatisticsTrend() {
        return jpaTemplate.findAll(StatisticsTrendEntity.class);
    }

    public List<StatisticsTrendEntity> findStatisticsTrendList(List<String> idList) {
        return jpaTemplate.findList(StatisticsTrendEntity.class,idList);
    }


}