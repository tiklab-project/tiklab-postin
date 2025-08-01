package io.tiklab.postin.autotest.common.utils;

import org.springframework.stereotype.Component;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 测试中的公共方法
 */
@Component
public class TestUtil {

    /**
     * 百分比计算
     * */
    public String processRate(Integer num, Integer totalNum) {
        if (totalNum == 0) {
            return "0.00%";
        }
        double rate = (double) num / (double) totalNum;
        if (Double.isNaN(rate)) {
            return "0.00%";
        }
        DecimalFormat df = new DecimalFormat("0.00%");
        return df.format(rate);
    }

    /**
     * 循环分配
     * @param totalNum
     * @param agent
     * @return
     */
    public List<Integer> loop(int totalNum,int agent){
        List<Integer> arrList = new ArrayList<>();

        int avg = (int) Math.floor(totalNum / agent);
        int remainder = totalNum % agent;

        for(int i =1;i<=remainder;i++){
            arrList.add(avg+1);
        }

        for(int i =1;i<=agent-remainder;i++){
            arrList.add(avg);
        }

        return  arrList;
    }

    /**
     * 随机分配
     * 把一个正整数随机拆分成count个正整数
     * @param totalNum
     * @param agent
     * @return
     */
    public List<Integer> random(int totalNum, int agent) {
        // 创建一个长度的数组
        List<Integer> arrList = new ArrayList<>();

        int totalCount = (totalNum);

        /*if (totalCount < agent || totalCount < 1) {
            return arrList; // 返回空的集合
        }*/

        //2. 进行随机分配
        Random rand = new Random();

        // 剩余数量
        int leftCount = totalCount;
        // 剩余agent
        int leftAgent = agent;

        // 随机分配公式：1 + rand.nextInt(leftCount / leftAgent * 2)
        for (int i = 0; i < agent - 1; i++) {
            int count_ = 0;
            if (leftCount > 0) {
                if ((leftCount / leftAgent * 2) < 1) {
                    count_ = leftCount;
                } else {
                    count_ = 1 + rand.nextInt(leftCount / leftAgent * 2);
                }

            } else {
                count_ = 0;
            }
            arrList.add(count_);
            if (count_ > 0) {
                leftCount -= count_;
                leftAgent--;
            }

        }
        // 把剩余的最后一个放到最后一个包里
        arrList.add(leftCount);
        return arrList;
    }


}
