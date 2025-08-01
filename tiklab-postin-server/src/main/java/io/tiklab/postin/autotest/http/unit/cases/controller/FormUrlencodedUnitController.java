package io.tiklab.postin.autotest.http.unit.cases.controller;

import io.tiklab.core.Result;
import io.tiklab.core.page.Pagination;
import io.tiklab.postin.autotest.http.unit.cases.model.FormUrlEncodedUnit;
import io.tiklab.postin.autotest.http.unit.cases.model.FormUrlencodedUnitQuery;
import io.tiklab.postin.autotest.http.unit.cases.service.FormUrlencodedUnitService;
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

/**
 * form-urlencoded 控制器
 */
@RestController
@RequestMapping("/formUrlencodedUnit")
//@Api(name = "FormUrlencodedController",desc = "form-urlencoded 参数管理")
public class FormUrlencodedUnitController {

    private static Logger logger = LoggerFactory.getLogger(FormUrlencodedUnitController.class);

    @Autowired
    private FormUrlencodedUnitService formUrlencodedUnitService;

    @RequestMapping(path="/createFormUrlencoded",method = RequestMethod.POST)
//    @ApiMethod(name = "createFormUrlencoded",desc = "创建form-urlencoded ")
//    @ApiParam(name = "formUrlencodedUnit",desc = "formUrlencoded",required = true)
    public Result<String> createFormUrlencoded(@RequestBody @NotNull @Valid FormUrlEncodedUnit formUrlencodedUnit){
        String id = formUrlencodedUnitService.createFormUrlencoded(formUrlencodedUnit);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateFormUrlencoded",method = RequestMethod.POST)
//    @ApiMethod(name = "updateFormUrlencoded",desc = "更新form-urlencoded ")
//    @ApiParam(name = "formUrlencodedUnit",desc = "formUrlencoded",required = true)
    public Result<Void> updateFormUrlencoded(@RequestBody @NotNull @Valid FormUrlEncodedUnit formUrlencodedUnit){
        formUrlencodedUnitService.updateFormUrlencoded(formUrlencodedUnit);

        return Result.ok();
    }

    @RequestMapping(path="/deleteFormUrlencoded",method = RequestMethod.POST)
//    @ApiMethod(name = "deleteFormUrlencoded",desc = "删除form-urlencoded ")
//    @ApiParam(name = "id",desc = "id",required = true)
    public Result<Void> deleteFormUrlencoded(@NotNull String id){
        formUrlencodedUnitService.deleteFormUrlencoded(id);

        return Result.ok();
    }

    @RequestMapping(path="/findFormUrlencoded",method = RequestMethod.POST)
//    @ApiMethod(name = "findFormUrlencoded",desc = "根据id查找form-urlencoded")
//    @ApiParam(name = "id",desc = "id",required = true)
    public Result<FormUrlEncodedUnit> findFormUrlencoded(@NotNull String id){
        FormUrlEncodedUnit formUrlencodedUnit = formUrlencodedUnitService.findFormUrlencoded(id);

        return Result.ok(formUrlencodedUnit);
    }

    @RequestMapping(path="/findAllFormUrlencoded",method = RequestMethod.POST)
//    @ApiMethod(name = "findAllFormUrlencoded",desc = "查找所有form-urlencoded")
    public Result<List<FormUrlEncodedUnit>> findAllFormUrlencoded(){
        List<FormUrlEncodedUnit> formUrlEncodedUnitList = formUrlencodedUnitService.findAllFormUrlencoded();

        return Result.ok(formUrlEncodedUnitList);
    }

    @RequestMapping(path = "/findFormUrlencodedList",method = RequestMethod.POST)
//    @ApiMethod(name = "findFormUrlencodedList",desc = "查询form-urlencoded 列表")
//    @ApiParam(name = "formUrlencodedUnitQuery",desc = "formUrlencodedQuery",required = true)
    public Result<List<FormUrlEncodedUnit>> findFormUrlencodedList(@RequestBody @Valid @NotNull FormUrlencodedUnitQuery formUrlencodedUnitQuery){
        List<FormUrlEncodedUnit> formUrlEncodedUnitList = formUrlencodedUnitService.findFormUrlencodedList(formUrlencodedUnitQuery);

        return Result.ok(formUrlEncodedUnitList);
    }

    @RequestMapping(path = "/findFormUrlencodedPage",method = RequestMethod.POST)
//    @ApiMethod(name = "findFormUrlencodedPage",desc = "按分页查询form-urlencoded ")
//    @ApiParam(name = "formUrlencodedUnitQuery",desc = "formUrlencodedQuery",required = true)
    public Result<Pagination<FormUrlEncodedUnit>> findFormUrlencodedPage(@RequestBody @Valid @NotNull FormUrlencodedUnitQuery formUrlencodedUnitQuery){
        Pagination<FormUrlEncodedUnit> pagination = formUrlencodedUnitService.findFormUrlencodedPage(formUrlencodedUnitQuery);

        return Result.ok(pagination);
    }

}
