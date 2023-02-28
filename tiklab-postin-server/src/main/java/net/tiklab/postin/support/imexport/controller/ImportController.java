package net.tiklab.postin.support.imexport.controller;

import net.tiklab.postin.annotation.Api;
import net.tiklab.postin.annotation.ApiMethod;
import net.tiklab.postin.annotation.ApiParam;
import net.tiklab.postin.support.imexport.service.ImportService;
import net.tiklab.core.Result;
import net.tiklab.core.exception.SystemException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.io.InputStream;

/**
 * 导入导出 控制器
 */
@RestController
@RequestMapping("/port")
@Api(name = "PortController",desc = "导入导出管理")
public class ImportController {

    private static Logger logger = LoggerFactory.getLogger(ImportController.class);

    @Autowired
    ImportService importService;

    @RequestMapping(path = "/importPostman",method = RequestMethod.POST)
    @ApiMethod(name = "importPostman",desc = "导入数据")
    @ApiParam(name = "workspaceId")
    public Result<Void> importPostman(@NotNull  String workspaceId,@RequestParam("file") MultipartFile file ){
        try {
            if(file!=null){
                InputStream inputStream = file.getInputStream();
                importService.importPostman(workspaceId,inputStream);
            }
        } catch (IOException e) {
            throw new SystemException(e);
        }

        return Result.ok();
    }

    @RequestMapping(path = "/importReport",method = RequestMethod.POST)
    @ApiMethod(name = "importReport",desc = "导入数据")
    @ApiParam(name = "workspaceId")
    public Result<Void> importReport(@NotNull String workspaceId,@RequestParam("file") MultipartFile file ){
        try {
            if(file!=null){
                InputStream inputStream = file.getInputStream();
                importService.importReport(workspaceId,inputStream);
            }
        } catch (IOException e) {
            throw new SystemException(e);
        }

        return Result.ok();
    }



}
