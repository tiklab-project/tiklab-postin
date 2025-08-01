package io.tiklab.postin.autotest.common.utils;

public interface PercentileEstimator {
    /**
     * 添加一个新的数据点到估算器中
     * @param value 要添加的数据点
     */
    void add(double value);

    /**
     * 获取当前的百分位数估计值
     * @return 估计的百分位数值
     */
    double estimate();

}