package io.tiklab.postin.api.apix.controller;

import io.tiklab.core.Result;
import io.tiklab.core.page.Pagination;
import io.tiklab.postin.annotation.Api;
import io.tiklab.postin.annotation.ApiMethod;
import io.tiklab.postin.annotation.ApiParam;
import io.tiklab.postin.api.apix.model.ApiRecent;
import io.tiklab.postin.api.apix.model.ApiRecentQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * 最近访问接口 控制器
 */
@RestController
@RequestMapping("/demo")
@Api(name = "ApiRecentController",desc = "ApiRecentController")
public class ApiDemoController {

    private static Logger logger = LoggerFactory.getLogger(ApiDemoController.class);



    @RequestMapping(path="/createApiRecent",method = RequestMethod.POST)
    @ApiMethod(name = "createApiRecent",desc = "创建最近访问接口")
    @ApiParam(name = "apiRecent",desc = "apiRecent",required = true)
    public Result<String> createApiRecent(@RequestBody @NotNull @Valid ApiRecent apiRecent){


        return Result.ok();
    }

    @RequestMapping(path="/updateApiRecent",method = RequestMethod.POST)
    @ApiMethod(name = "updateApiRecent",desc = "更新最近访问接口")
    @ApiParam(name = "apiRecent",desc = "apiRecent",required = true)
    public Result<Void> updateApiRecent(@RequestBody @NotNull @Valid ApiRecent apiRecent){


        return Result.ok();
    }

    @RequestMapping(path = "/img", method = RequestMethod.POST)
    public Result<Void> uploadImage(@RequestParam("file") MultipartFile file) {
        try (InputStream inputStream = file.getInputStream()) {
            byte[] bytes = inputStream.readAllBytes();

            String result = "File size:" + bytes.length + " bytes";

            return Result.ok(result);
        } catch (IOException e) {
            logger.error("Failed to process the uploaded file", e);
            return Result.ok("File upload failed");
        }
    }


    @RequestMapping(path="/findApiRecent",method = RequestMethod.POST)
    @ApiMethod(name = "findApiRecent",desc = "通过id查找最近访问接口")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<ApiRecent> findApiRecent(@NotNull String id){


        return Result.ok();
    }

    @RequestMapping(path="/findAllApiRecent",method = RequestMethod.POST)
    @ApiMethod(name = "findAllApiRecent",desc = "查找所有最近访问接口")
    public Result<List<ApiRecent>> findAllApiRecent(){


        return Result.ok();
    }

    @RequestMapping(path = "/findApiRecentList",method = RequestMethod.POST)
    @ApiMethod(name = "findApiRecentList",desc = "根据查询参数查找最近访问接口")
    @ApiParam(name = "apiRecentQuery",desc = "apiRecentQuery",required = true)
    public Result<List<ApiRecent>> findApiRecentList(@RequestBody @Valid @NotNull ApiRecentQuery apiRecentQuery){


        return Result.ok();
    }

    @RequestMapping(path = "/findApiRecentPage",method = RequestMethod.POST)
    @ApiMethod(name = "findApiRecentPage",desc = "根据查询参数按分页查找最近访问接口")
    @ApiParam(name = "apiRecentQuery",desc = "apiRecentQuery",required = true)
    public Result<Pagination<ApiRecent>> findApiRecentPage(@RequestBody @Valid @NotNull ApiRecentQuery apiRecentQuery){


        return Result.ok();
    }

}
