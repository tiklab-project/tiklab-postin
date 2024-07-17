package io.thoughtware.postin.support.statistics.controller;

import io.thoughtware.core.Result;
import io.thoughtware.core.page.Pagination;
import io.thoughtware.postin.annotation.Api;
import io.thoughtware.postin.annotation.ApiMethod;
import io.thoughtware.postin.annotation.ApiParam;
import io.thoughtware.postin.support.environment.model.Environment;
import io.thoughtware.postin.support.environment.model.EnvironmentQuery;
import io.thoughtware.postin.support.environment.service.EnvironmentService;
import io.thoughtware.postin.support.statistics.model.ApiNewCreateStatisticsModel;
import io.thoughtware.postin.support.statistics.service.StatisticsService;
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
 * 接口环境 控制器
 */
@RestController
@RequestMapping("/statistics")
public class StatisticsController {

    private static Logger logger = LoggerFactory.getLogger(StatisticsController.class);

    @Autowired
    private StatisticsService statisticsService;

    @RequestMapping(path="/getApiStatusStatistics",method = RequestMethod.POST)
    public Result<List<Map<String, Object>>> getApiStatusStatistics(){
        List<Map<String, Object>> apiStatusStatistics = statisticsService.getApiStatusStatistics();

        return Result.ok(apiStatusStatistics);
    }

    @RequestMapping(path="/getApiNewCreateStatistics",method = RequestMethod.POST)
    public Result<List<Map<String, Object>>> getApiNewCreateStatistics(@RequestBody @NotNull @Valid ApiNewCreateStatisticsModel apiNewCreateStatisticsModel){
        List<Map<String, Object>> apiStatusStatistics = statisticsService.getApiNewCreateStatistics(apiNewCreateStatisticsModel);

        return Result.ok(apiStatusStatistics);
    }



}
