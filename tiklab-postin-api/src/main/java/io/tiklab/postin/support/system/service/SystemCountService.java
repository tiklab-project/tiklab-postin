package io.tiklab.postin.support.system.service;

import io.tiklab.postin.support.system.model.SystemCount;
import io.tiklab.toolkit.join.annotation.JoinProvider;

/**
* 获取系统统计信息
*/
@JoinProvider(model = SystemCount.class)
public interface SystemCountService {

    /**
    * 获取系统首页中，需要展示的统计数据
    * @return
    */
    SystemCount getSystemCount();


}