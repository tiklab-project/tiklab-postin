package com.doublekit.apibox.client.document.controller;

import com.alibaba.fastjson.JSON;
import com.doublekit.apibox.annotation.Api;
import com.doublekit.apibox.annotation.ApiMethod;
import com.doublekit.apibox.client.builder.ApiMetaContext;
import com.doublekit.apibox.client.model.ApiMeta;
import com.doublekit.common.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.util.List;

@RestController
@RequestMapping("/report")
@Api(name = "report",desc = "report")
public class ReportTestController {

    @RequestMapping(path = "/reportTest",method = RequestMethod.POST)
    @ApiMethod(name = "report",desc = "report")
    public Result<Void> report(String test) throws FileNotFoundException {
        List<ApiMeta> apiMetaList =  ApiMetaContext.getApiMetaList();
        String data = JSON.toJSONString(apiMetaList);
        File file=new File("C:\\Users\\JacksonSun\\Desktop\\report.json");

        try{

            FileOutputStream out=new FileOutputStream(file,false);

            out.write(data.getBytes());

        } catch (FileNotFoundException e) {

            System.out.println("文件不存在或者文件不可读或者文件是目录");
        } catch (IOException e) {
            System.out.println("读取过程存在异常");
        }

        return Result.ok();
    }



}
