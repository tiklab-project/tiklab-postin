package net.tiklab.postin.apidef.http.test.httpinstance.controller;

import net.tiklab.postin.annotation.Api;
import net.tiklab.postin.apidef.http.test.httpinstance.model.ResponseInstance;
import net.tiklab.postin.apidef.http.test.httpinstance.model.ResponseInstanceQuery;
import net.tiklab.postin.apidef.http.test.httpinstance.service.ResponseInstanceService;
import net.tiklab.core.page.Pagination;
import net.tiklab.core.Result;
import net.tiklab.postin.annotation.ApiMethod;
import net.tiklab.postin.annotation.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * ManagerController
 * Created by Zhangzhihua on 2017/9/25.
 */
@RestController
@RequestMapping("/responseInstance")
@Api(name = "ResponseInstanceController",desc = "测试实例-响应管理")
public class ResponseInstanceController {

    private static Logger logger = LoggerFactory.getLogger(ResponseInstanceController.class);

    @Autowired
    private ResponseInstanceService responseInstanceService;

    @RequestMapping(path="/createResponseInstance",method = RequestMethod.POST)
    @ApiMethod(name = "createResponseInstance",desc = "createResponseInstance")
    @ApiParam(name = "responseInstance",desc = "responseInstance",required = true)
    public Result<String> createResponseInstance(@RequestBody @NotNull @Valid ResponseInstance responseInstance){
        String id = responseInstanceService.createResponseInstance(responseInstance);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateResponseInstance",method = RequestMethod.POST)
    @ApiMethod(name = "updateResponseInstance",desc = "updateResponseInstance")
    @ApiParam(name = "responseInstance",desc = "responseInstance",required = true)
    public Result<Void> updateResponseInstance(@RequestBody @NotNull @Valid ResponseInstance responseInstance){
        responseInstanceService.updateResponseInstance(responseInstance);

        return Result.ok();
    }

    @RequestMapping(path="/deleteResponseInstance",method = RequestMethod.POST)
    @ApiMethod(name = "deleteResponseInstance",desc = "deleteResponseInstance")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<Void> deleteResponseInstance(@NotNull String id){
        responseInstanceService.deleteResponseInstance(id);

        return Result.ok();
    }

    @RequestMapping(path="/findResponseInstance",method = RequestMethod.POST)
    @ApiMethod(name = "findResponseInstance",desc = "findResponseInstance")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<ResponseInstance> findResponseInstance(@NotNull String id){
        ResponseInstance responseInstance = responseInstanceService.findResponseInstance(id);

        return Result.ok(responseInstance);
    }

    @RequestMapping(path="/findAllResponseInstance",method = RequestMethod.POST)
    @ApiMethod(name = "findAllResponseInstance",desc = "findAllResponseInstance")
    public Result<List<ResponseInstance>> findAllResponseInstance(){
        List<ResponseInstance> responseInstanceList = responseInstanceService.findAllResponseInstance();

        return Result.ok(responseInstanceList);
    }


    @RequestMapping(path = "/findResponseInstanceList",method = RequestMethod.POST)
    @ApiMethod(name = "findResponseInstanceList",desc = "findResponseInstanceList")
    @ApiParam(name = "responseInstanceQuery",desc = "responseInstanceQuery",required = true)
    public Result<List<ResponseInstance>> findResponseInstanceList(@RequestBody @Valid @NotNull ResponseInstanceQuery responseInstanceQuery){
        List<ResponseInstance> responseInstanceList = responseInstanceService.findResponseInstanceList(responseInstanceQuery);

        return Result.ok(responseInstanceList);
    }


    @RequestMapping(path = "/findResponseInstancePage",method = RequestMethod.POST)
    @ApiMethod(name = "findResponseInstancePage",desc = "findResponseInstancePage")
    @ApiParam(name = "responseInstanceQuery",desc = "responseInstanceQuery",required = true)
    public Result<Pagination<ResponseInstance>> findResponseInstancePage(@RequestBody @Valid @NotNull ResponseInstanceQuery responseInstanceQuery){
        Pagination<ResponseInstance> pagination = responseInstanceService.findResponseInstancePage(responseInstanceQuery);

        return Result.ok(pagination);
    }

}
