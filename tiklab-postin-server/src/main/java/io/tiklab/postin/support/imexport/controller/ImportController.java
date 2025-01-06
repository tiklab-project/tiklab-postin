package io.tiklab.postin.support.imexport.controller;

import io.tiklab.core.exception.ApplicationException;
import io.tiklab.postin.common.ErrorCode;
import io.tiklab.postin.support.imexport.service.ImportService;
import io.tiklab.core.Result;
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
public class ImportController {

    private static Logger logger = LoggerFactory.getLogger(ImportController.class);

    @Autowired
    ImportService importService;

    @RequestMapping(path = "/importData",method = RequestMethod.POST)
    public Result<Void> importData(@NotNull String workspaceId,@RequestParam("file") MultipartFile file,@NotNull String type ){
        try {
            if(file!=null){
                InputStream inputStream = file.getInputStream();
                importService.importData(workspaceId,inputStream,type);
            }
        } catch (IOException e) {
            throw new ApplicationException(ErrorCode.IMPORT_ERROR,e.getMessage());
        }

        return Result.ok();
    }

    @RequestMapping(path = "/importReport",method = RequestMethod.POST)
    public Result<Void> importReport(@NotNull String workspaceId,@RequestParam("file") MultipartFile file ){
        try {
            if(file!=null){
                InputStream inputStream = file.getInputStream();
                importService.importReport(workspaceId,inputStream);
            }
        } catch (IOException e) {
            throw new ApplicationException(ErrorCode.IMPORT_ERROR,e.getMessage());
        }

        return Result.ok();
    }

    @RequestMapping(path = "/importSwagger2",method = RequestMethod.POST)
    public Result<Void> importSwagger2(@NotNull  String workspaceId,@RequestParam("file") MultipartFile file ){
//        try {
//            if(file!=null){
//                InputStream inputStream = file.getInputStream();
//                importService.importSwagger2(workspaceId,inputStream);
//            }
//        } catch (IOException e) {
//            throw new ApplicationException(e);
//        }

        return Result.ok();
    }



    @RequestMapping(path = "/getDoc",method = RequestMethod.POST)
    public Result<Void> getDoc(@NotNull String workspaceId){
        importService.getDoc();
        return Result.ok();
    }

}
