package io.tiklab.postin.support.statistics.service;

import io.tiklab.postin.support.statistics.model.StatisticsTrend;
import io.tiklab.postin.support.statistics.model.StatisticsTrendQuery;
import io.tiklab.postin.support.statistics.model.StatisticsUserContribution;
import io.tiklab.postin.support.statistics.model.StatisticsUserContributionQuery;
import io.tiklab.toolkit.join.annotation.FindAll;
import io.tiklab.toolkit.join.annotation.FindList;
import io.tiklab.toolkit.join.annotation.JoinProvider;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

/**
* 成员贡献 服务接口
*/
@JoinProvider(model = StatisticsUserContribution.class)
public interface StatisticsUserContributionService {



    /***
     * 查询所有成员贡献
     * @return
     */
    List<StatisticsUserContribution> findStatisticsUserContribution(StatisticsUserContributionQuery statisticsUserContributionQuery);

}