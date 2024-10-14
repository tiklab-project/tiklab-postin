package io.tiklab.postin.support.statistics.service;

import io.tiklab.postin.support.statistics.model.StatisticsTrend;
import io.tiklab.postin.support.statistics.model.StatisticsTrendQuery;
import io.tiklab.toolkit.join.annotation.FindAll;
import io.tiklab.toolkit.join.annotation.FindList;
import io.tiklab.toolkit.join.annotation.JoinProvider;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

/**
* 接口环境 服务接口
*/
@JoinProvider(model = StatisticsTrend.class)
public interface StatisticsTrendService {


    /**
    * 创建
    * @param statisticsTrend
    * @return
    */
    String createStatisticsTrend(@NotNull @Valid StatisticsTrend statisticsTrend);

    /**
    * 删除
    * @param id
    */
    void deleteStatisticsTrend(@NotNull String id);

    @FindList
    List<StatisticsTrend> findList(List<String> idList);

    /**
    * 查找所有接口环境
    * @return
    */
    @FindAll
    List<StatisticsTrend> findAllStatisticsTrend();

    /**
     * 查看用例趋势
     */
    List<Map<String, Object>> getStatisticsTrend(StatisticsTrendQuery statisticsTrendQuery);

}