package io.tiklab.postin.support.statistics.service;

import io.tiklab.core.exception.ApplicationException;
import io.tiklab.postin.support.statistics.dao.StatisticsTrendDao;
import io.tiklab.postin.support.statistics.entity.StatisticsTrendEntity;
import io.tiklab.postin.support.statistics.model.StatisticsTrend;
import io.tiklab.postin.support.statistics.model.StatisticsTrendQuery;
import io.tiklab.postin.workspace.model.Workspace;
import io.tiklab.postin.workspace.service.WorkspaceService;
import io.tiklab.toolkit.beans.BeanMapper;
import io.tiklab.toolkit.join.JoinTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.text.SimpleDateFormat;
import java.util.*;


/**
* app环境 服务
*/
@Service
public class StatisticsTrendServiceImpl implements StatisticsTrendService {

    @Autowired
    StatisticsTrendDao statisticsTrendDao;

    @Autowired
    JoinTemplate joinTemplate;

    @Autowired
    WorkspaceService workspaceService;

    //    @Scheduled(cron = "*/10 * * * * ?")
    @Scheduled(cron = "0 59 23 * * ?")
    public void recordTrendEveryday() {
        List<Workspace> allRepository = workspaceService.findAllWorkspace();

        for(int i=0;i<allRepository.size();i++) {
            String id = allRepository.get(i).getId();
            StatisticsTrend statisticsCaseTrend = new StatisticsTrend();
            statisticsCaseTrend.setWorkspaceId(id);
            createStatisticsTrend(statisticsCaseTrend);
        }
    }


    @Override
    public String createStatisticsTrend(@NotNull @Valid StatisticsTrend statisticsCaseTrend) {
        StatisticsTrendEntity statisticsCaseTrendEntity = BeanMapper.map(statisticsCaseTrend, StatisticsTrendEntity.class);

        return statisticsTrendDao.createStatisticsTrend(statisticsCaseTrendEntity);
    }



    @Override
    public void deleteStatisticsTrend(@NotNull String id) {
        statisticsTrendDao.deleteStatisticsTrend(id);
    }

    @Override
    public List<StatisticsTrend> findList(List<String> idList) {
        List<StatisticsTrendEntity> statisticsCaseTrendEntityList =  statisticsTrendDao.findStatisticsTrendList(idList);

        List<StatisticsTrend> statisticsCaseTrendList =  BeanMapper.mapList(statisticsCaseTrendEntityList,StatisticsTrend.class);
        return statisticsCaseTrendList;
    }


    @Override
    public List<StatisticsTrend> findAllStatisticsTrend() {
        List<StatisticsTrendEntity> statisticsCaseTrendEntityList =  statisticsTrendDao.findAllStatisticsTrend();

        List<StatisticsTrend> statisticsCaseTrendList =  BeanMapper.mapList(statisticsCaseTrendEntityList,StatisticsTrend.class);

        joinTemplate.joinQuery(statisticsCaseTrendList);

        return statisticsCaseTrendList;
    }

    @Override
    public List<Map<String, Object>> getStatisticsTrend(StatisticsTrendQuery statisticsCaseTrend){
        List<Map<String, Object>> statisticsCaseTrend1 = statisticsTrendDao.getStatisticsTrend(statisticsCaseTrend);
        Map<Date, Map<String, Integer>> resultMap = new HashMap<>();
        for (Map<String, Object> row : statisticsCaseTrend1) {
            java.sql.Date date = (java.sql.Date) row.get("date");

            // 从 Map 中获取值，并转换为 Number 类型
            Object publishObj = row.getOrDefault("publish", 0);
            Object designObj = row.getOrDefault("design", 0);
            Object developmentObj = row.getOrDefault("development", 0);
            Object intergratedObj = row.getOrDefault("intergrated", 0);
            Object testObj = row.getOrDefault("test", 0);
            Object completeObj = row.getOrDefault("complete", 0);
            Object maintainObj = row.getOrDefault("maintain", 0);
            Object errorObj = row.getOrDefault("error", 0);
            Object obsoleteObj = row.getOrDefault("obsolete", 0);

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

            HashMap<String, Integer> newMap = new HashMap<>();
            newMap.put("publish",publish);
            newMap.put("design",design);
            newMap.put("development",development);
            newMap.put("intergrated",intergrated);
            newMap.put("test",test);
            newMap.put("complete",complete);
            newMap.put("maintain",maintain);
            newMap.put("error",error);
            newMap.put("obsolete",obsolete);

            resultMap.put(date, newMap);
        }


        String startDateStr = statisticsCaseTrend.getStartTime();
        String endDateStr = statisticsCaseTrend.getEndTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date startDate = dateFormat.parse(startDateStr);
            Date endDate = dateFormat.parse(endDateStr);
            // 生成日期范围
            List<java.sql.Date> dates = generateDateRange(startDate, endDate);

            List<Map<String, Object>> fullList = new ArrayList<>();

            for (java.sql.Date date : dates) {
                Map<String, Object> map = new HashMap<>();
                map.put("date", date.toString());
                Map<String, Integer> typeCounts = resultMap.getOrDefault(date, new HashMap<>());

                map.put("publish",typeCounts.getOrDefault("publish",0));
                map.put("design",typeCounts.getOrDefault("design",0));
                map.put("development",typeCounts.getOrDefault("development",0));
                map.put("intergrated",typeCounts.getOrDefault("intergrated",0));
                map.put("test",typeCounts.getOrDefault("test",0));
                map.put("complete",typeCounts.getOrDefault("complete",0));
                map.put("maintain",typeCounts.getOrDefault("maintain",0));
                map.put("error",typeCounts.getOrDefault("error",0));
                map.put("obsolete",typeCounts.getOrDefault("obsolete",0));
                map.put("total",typeCounts.getOrDefault("total",0));

                fullList.add(map);
            }

            return fullList;
        } catch (Exception e){
            throw new ApplicationException(e);
        }
    }


    /**
     * 生成日期范围
     */
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