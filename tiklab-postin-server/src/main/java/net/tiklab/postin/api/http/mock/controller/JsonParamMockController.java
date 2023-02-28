package net.tiklab.postin.api.http.mock.controller;

import net.tiklab.postin.annotation.Api;
import net.tiklab.postin.api.http.mock.model.JsonParamMock;
import net.tiklab.postin.api.http.mock.model.JsonParamMockQuery;
import net.tiklab.postin.api.http.mock.service.JsonParamMockService;
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
 * mock
 * 请求中json 控制器
 */
@RestController
@RequestMapping("/jsonParamMock")
@Api(name = "JsonParamMockController",desc = "接口mock-json参数管理")
public class JsonParamMockController {

    private static Logger logger = LoggerFactory.getLogger(JsonParamMockController.class);

    @Autowired
    private JsonParamMockService jsonParamMockService;

    @RequestMapping(path="/createJsonParamMock",method = RequestMethod.POST)
    @ApiMethod(name = "createJsonParamMock",desc = "创建json")
    @ApiParam(name = "jsonParamMock",desc = "jsonParamMock",required = true)
    public Result<String> createJsonParamMock(@RequestBody @NotNull @Valid JsonParamMock jsonParamMock){
        String id = jsonParamMockService.createJsonParamMock(jsonParamMock);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateJsonParamMock",method = RequestMethod.POST)
    @ApiMethod(name = "updateJsonParamMock",desc = "更新json")
    @ApiParam(name = "jsonParamMock",desc = "jsonParamMock",required = true)
    public Result<Void> updateJsonParamMock(@RequestBody @NotNull @Valid JsonParamMock jsonParamMock){
        jsonParamMockService.updateJsonParamMock(jsonParamMock);

        return Result.ok();
    }

    @RequestMapping(path="/deleteJsonParamMock",method = RequestMethod.POST)
    @ApiMethod(name = "deleteJsonParamMock",desc = "删除json")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<Void> deleteJsonParamMock(@NotNull String id){
        jsonParamMockService.deleteJsonParamMock(id);

        return Result.ok();
    }

    @RequestMapping(path="/findJsonParamMock",method = RequestMethod.POST)
    @ApiMethod(name = "findJsonParamMock",desc = "通过id查找json")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<JsonParamMock> findJsonParamMock(@NotNull String id){
        JsonParamMock jsonParamMock = jsonParamMockService.findJsonParamMock(id);

        return Result.ok(jsonParamMock);
    }

    @RequestMapping(path="/findAllJsonParamMock",method = RequestMethod.POST)
    @ApiMethod(name = "findAllJsonParamMock",desc = "查找所有json")
    public Result<List<JsonParamMock>> findAllJsonParamMock(){
        List<JsonParamMock> jsonParamMockList = jsonParamMockService.findAllJsonParamMock();

        return Result.ok(jsonParamMockList);
    }


    @RequestMapping(path = "/findJsonParamMockList",method = RequestMethod.POST)
    @ApiMethod(name = "findJsonParamMockList",desc = "根据查询参数查找json列表")
    @ApiParam(name = "jsonParamMockQuery",desc = "jsonParamMockQuery",required = true)
    public Result<List<JsonParamMock>> findJsonParamMockList(@RequestBody @Valid @NotNull JsonParamMockQuery jsonParamMockQuery){
        List<JsonParamMock> jsonParamMockList = jsonParamMockService.findJsonParamMockList(jsonParamMockQuery);

        return Result.ok(jsonParamMockList);
    }


    @RequestMapping(path = "/findJsonParamMockPage",method = RequestMethod.POST)
    @ApiMethod(name = "findJsonParamMockPage",desc = "根据查询参数按分页查找json列表")
    @ApiParam(name = "jsonParamMockQuery",desc = "jsonParamMockQuery",required = true)
    public Result<Pagination<JsonParamMock>> findJsonParamMockPage(@RequestBody @Valid @NotNull JsonParamMockQuery jsonParamMockQuery){
        Pagination<JsonParamMock> pagination = jsonParamMockService.findJsonParamMockPage(jsonParamMockQuery);

        return Result.ok(pagination);
    }

}
