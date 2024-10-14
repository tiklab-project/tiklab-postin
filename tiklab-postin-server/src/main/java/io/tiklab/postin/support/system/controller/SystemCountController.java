package io.tiklab.postin.support.system.controller;

import io.tiklab.core.Result;
import io.tiklab.postin.support.system.model.SystemCount;
import io.tiklab.postin.support.system.service.SystemCountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


/**
 * 获取系统统计信息
 */
@RestController
@RequestMapping("/system")
public class SystemCountController {

    @Autowired
    SystemCountService systemService;

    @RequestMapping(path="/count",method = RequestMethod.POST)
    public Result<SystemCount> system(){
        SystemCount systemCount = systemService.getSystemCount();

        return Result.ok(systemCount);
    }
}
