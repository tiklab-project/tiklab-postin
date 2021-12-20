package com.doublekit.apibox.imexport.controller;

import com.doublekit.apibox.annotation.Api;
import com.doublekit.apibox.annotation.ApiMethod;
import com.doublekit.apibox.annotation.ApiParam;
import com.doublekit.apibox.imexport.service.ImportService;
import com.doublekit.common.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping("/port")
@Api(name = "PortController",desc = "导入导出管理")
public class ImportController {

    @Autowired
    ImportService importService;

    @RequestMapping(path = "/importData",method = RequestMethod.POST)
    @ApiMethod(name = "importData",desc = "导入数据")
    @ApiParam(name = "type")
    public Result<Void> importData(@NotNull String type, String workspaceId,@RequestParam("file") MultipartFile file ){
        try {
            if(file!=null){
                InputStream inputStream = file.getInputStream();
                importService.importData(type,workspaceId,inputStream);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return Result.ok();
    }


}
