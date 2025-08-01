package io.tiklab.postin.autotest.testplan.quartz.service;

import io.tiklab.core.page.Pagination;
import io.tiklab.core.page.PaginationBuilder;
import io.tiklab.postin.autotest.testplan.quartz.utils.QuartzUtils;
import io.tiklab.rpc.annotation.Exporter;
import io.tiklab.postin.autotest.testplan.quartz.config.SchedulerConfig;
import io.tiklab.postin.autotest.testplan.quartz.dao.QuartzTimePlanDao;
import io.tiklab.postin.autotest.testplan.quartz.entity.QuartzTimePlanEntity;
import io.tiklab.postin.autotest.testplan.quartz.model.QuartzTimePlan;
import io.tiklab.postin.autotest.testplan.quartz.model.QuartzTimePlanQuery;
import io.tiklab.toolkit.beans.BeanMapper;
import io.tiklab.toolkit.join.JoinTemplate;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

/**
* 定时任务 服务
*/
@Service
@Exporter
public class QuartzTimePlanServiceImpl implements QuartzTimePlanService {

    @Autowired
    QuartzTimePlanDao quartzTimePlanDao;

    @Autowired
    JoinTemplate joinTemplate;

    @Autowired
    SchedulerConfig schedulerConfig;


    @Override
    public String createQuartzTimePlan(@NotNull @Valid QuartzTimePlan quartzTimePlan) {
        String cron;
        if(quartzTimePlan.getExeType() == 1){
             cron = QuartzUtils.weekCron(quartzTimePlan.getTime(), quartzTimePlan.getWeek());
        }else {
             cron = QuartzUtils.loopCron(quartzTimePlan.getTime());
        }

        quartzTimePlan.setCron(cron);
        QuartzTimePlanEntity quartzTimePlanEntity = BeanMapper.map(quartzTimePlan, QuartzTimePlanEntity.class);

        schedulerConfig.scheduler(
                "test",
                quartzTimePlan.getTestPlanId(),
                quartzTimePlan.getQuartzPlanId(),
                cron,
                quartzTimePlan.getExeType()
        );

        return quartzTimePlanDao.createQuartzTimePlan(quartzTimePlanEntity);
    }

    @Override
    public void updateQuartzTimePlan(@NotNull @Valid QuartzTimePlan quartzTimePlan) {
        QuartzTimePlanEntity quartzTimePlanEntity = BeanMapper.map(quartzTimePlan, QuartzTimePlanEntity.class);

        quartzTimePlanDao.updateQuartzTimePlan(quartzTimePlanEntity);
    }

    @Override
    public void deleteAllQuartzTimePlan(String quartzPlanId,String testPlanId){
        QuartzTimePlanQuery quartzTimePlanQuery = new QuartzTimePlanQuery();
        quartzTimePlanQuery.setQuartzPlanId(quartzPlanId);
        for (QuartzTimePlan quartzTimePlan : findQuartzTimePlanList(quartzTimePlanQuery)) {

            schedulerConfig.removeJob(
                    "test",
                    testPlanId,
                    quartzTimePlan.getCron(),
                    quartzTimePlan.getQuartzPlanId()
            );

            deleteQuartzTimePlan(quartzTimePlan.getId());
        }

//        DeleteCondition deleteCondition = DeleteBuilders.createDelete(QuartzTimePlanEntity.class)
//                .eq("quartzPlanId",quartzPlanId)
//                .get();
//        quartzTimePlanDao.deleteQuartzTimePlan(deleteCondition);
    }

    @Override
    public void deleteQuartzTimePlan(@NotNull String id) {
        quartzTimePlanDao.deleteQuartzTimePlan(id);
    }


    @Override
    public QuartzTimePlan findOne(String id) {
        QuartzTimePlanEntity quartzTimePlanEntity = quartzTimePlanDao.findQuartzTimePlan(id);

        QuartzTimePlan quartzTimePlan = BeanMapper.map(quartzTimePlanEntity, QuartzTimePlan.class);
        return quartzTimePlan;
    }


    @Override
    public List<QuartzTimePlan> findList(List<String> idList) {
        List<QuartzTimePlanEntity> quartzTimePlanEntityList =  quartzTimePlanDao.findQuartzTimePlanList(idList);

        List<QuartzTimePlan> quartzTimePlanList =  BeanMapper.mapList(quartzTimePlanEntityList,QuartzTimePlan.class);
        return quartzTimePlanList;
    }

    @Override
    public QuartzTimePlan findQuartzTimePlan(@NotNull String id) {
        QuartzTimePlan quartzTimePlan = findOne(id);

        joinTemplate.joinQuery(quartzTimePlan);
        return quartzTimePlan;
    }

    @Override
    public QuartzTimePlan findQuartzTimePlanByQuartzPlanId(String quartzPlanId) {
        QuartzTimePlanQuery quartzTimePlanQuery = new QuartzTimePlanQuery();
        quartzTimePlanQuery.setQuartzPlanId(quartzPlanId);
        List<QuartzTimePlan> quartzTimePlanList = findQuartzTimePlanList(quartzTimePlanQuery);

        if(CollectionUtils.isNotEmpty(quartzTimePlanList)){
            QuartzTimePlan quartzTimePlan = new QuartzTimePlan();
            StringBuilder showTime = new StringBuilder();

            for(QuartzTimePlan quartzTime : quartzTimePlanList){
                if(quartzTime.getExeType()==1){
                    Map<String, String> map = QuartzUtils.cronWeek(quartzTime.getCron());
                    showTime.append(map.get("cron")).append(" | ");
                }else {
                    showTime.append(quartzTime.getTime()).append(" 分钟 ");
                }
            }
            QuartzTimePlan quartzTimePlan1 = quartzTimePlanList.get(0);
            if(quartzTimePlan1.getExeType()==1){
                showTime.append(quartzTimePlan1.getTime());
            }
            quartzTimePlan.setShowTime(showTime.toString());

            return quartzTimePlan;
        }

        return null;
    }

    @Override
    public List<QuartzTimePlan> findAllQuartzTimePlan() {
        List<QuartzTimePlanEntity> quartzTimePlanEntityList =  quartzTimePlanDao.findAllQuartzTimePlan();

        List<QuartzTimePlan> quartzTimePlanList =  BeanMapper.mapList(quartzTimePlanEntityList,QuartzTimePlan.class);

        joinTemplate.joinQuery(quartzTimePlanList);
        return quartzTimePlanList;
    }

    @Override
    public List<QuartzTimePlan> findQuartzTimePlanList(QuartzTimePlanQuery quartzTimePlanQuery) {
        List<QuartzTimePlanEntity> quartzTimePlanEntityList = quartzTimePlanDao.findQuartzTimePlanList(quartzTimePlanQuery);

        List<QuartzTimePlan> quartzTimePlanList = BeanMapper.mapList(quartzTimePlanEntityList,QuartzTimePlan.class);

        joinTemplate.joinQuery(quartzTimePlanList);

        return quartzTimePlanList;
    }

    @Override
    public Pagination<QuartzTimePlan> findQuartzTimePlanPage(QuartzTimePlanQuery quartzTimePlanQuery) {
        Pagination<QuartzTimePlanEntity>  pagination = quartzTimePlanDao.findQuartzTimePlanPage(quartzTimePlanQuery);

        List<QuartzTimePlan> quartzTimePlanList = BeanMapper.mapList(pagination.getDataList(),QuartzTimePlan.class);

        joinTemplate.joinQuery(quartzTimePlanList);

        return PaginationBuilder.build(pagination,quartzTimePlanList);
    }


}