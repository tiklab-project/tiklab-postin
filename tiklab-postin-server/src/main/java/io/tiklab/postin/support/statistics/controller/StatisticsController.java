package io.tiklab.postin.support.statistics.controller;

import io.tiklab.core.Result;
import io.tiklab.postin.support.statistics.model.ApiStatisticsModel;
import io.tiklab.postin.support.statistics.model.StatisticsTrendQuery;
import io.tiklab.postin.support.statistics.service.StatisticsService;
import io.tiklab.postin.support.statistics.service.StatisticsTrendService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

/**
 * 统计 控制器
 */
@RestController
@RequestMapping("/statistics")
public class StatisticsController {

    private static Logger logger = LoggerFactory.getLogger(StatisticsController.class);

    @Autowired
    private StatisticsService statisticsService;

    @Autowired
    private StatisticsTrendService statisticsTrendService;

    @RequestMapping(path="/getApiStatusStatistics",method = RequestMethod.POST)
    public Result<Map<String, Object>> getApiStatusStatistics(@RequestBody @NotNull @Valid ApiStatisticsModel apiStatisticsModel){
        Map<String, Object> apiStatusStatistics = statisticsService.getApiStatusStatistics(apiStatisticsModel);

        return Result.ok(apiStatusStatistics);
    }

    @RequestMapping(path="/getApiNewCreateStatistics",method = RequestMethod.POST)
    public Result<List<Map<String, Object>>> getApiNewCreateStatistics(@RequestBody @NotNull @Valid ApiStatisticsModel apiStatisticsModel){
        List<Map<String, Object>> apiStatusStatistics = statisticsService.getApiNewCreateStatistics(apiStatisticsModel);

        return Result.ok(apiStatusStatistics);
    }

    @RequestMapping(path="/getStatisticsTrend",method = RequestMethod.POST)
    public Result<List<Map<String, Object>>> getStatisticsTrend(@RequestBody @NotNull @Valid StatisticsTrendQuery statisticsTrendQuery){
        List<Map<String, Object>> statisticsCaseTrend = statisticsTrendService.getStatisticsTrend(statisticsTrendQuery);

        return Result.ok(statisticsCaseTrend);
    }

}
