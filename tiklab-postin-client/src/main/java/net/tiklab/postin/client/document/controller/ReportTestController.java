package net.tiklab.postin.client.document.controller;

import com.alibaba.fastjson.JSON;
import net.tiklab.core.Result;
import net.tiklab.core.exception.ApplicationException;
import net.tiklab.postin.annotation.Api;
import net.tiklab.postin.annotation.ApiMethod;
import net.tiklab.postin.client.builder.ApiMetaContext;
import net.tiklab.postin.client.model.ApiMeta;
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

            throw new ApplicationException(e);
        } catch (IOException e) {
            throw new ApplicationException(e);
        }

        return Result.ok();
    }



}
