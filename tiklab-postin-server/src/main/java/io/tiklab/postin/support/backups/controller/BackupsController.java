package io.tiklab.postin.support.backups.controller;

import io.tiklab.core.Result;
import io.tiklab.core.exception.SystemException;
import io.tiklab.postin.support.backups.model.Backups;
import io.tiklab.postin.support.backups.service.DbBackupsService;
import io.tiklab.postin.support.backups.service.DbRestoreService;
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
 * 导入数据控制器
 * @author admin
 */

@RestController
@RequestMapping("/postin/backups")
public class BackupsController {

    @Autowired
    DbBackupsService dbBackupsService;

    @Autowired
    DbRestoreService dbRestoreService;

    @RequestMapping(path="/backups",method = RequestMethod.POST)
    public Result<Void> createWorkWidget() {
        dbBackupsService.execBackups();
        return Result.ok();
    }


    @RequestMapping(path="/findBackups",method = RequestMethod.POST)
    public Result<Backups> findBackups() {
        Backups backupsResult = dbBackupsService.findBackupsResult();
        return Result.ok(backupsResult);
    }


    @RequestMapping(path="/updateBackups",method = RequestMethod.POST)
    public Result<Void> updateBackups(@NotNull Boolean scheduled) {
        dbBackupsService.updateBackups(scheduled);
        return Result.ok();
    }


    @RequestMapping(path="/uploadBackups",method = RequestMethod.POST)
    public Result<String> uploadBackups(@RequestParam("uploadFile") MultipartFile uploadFile){
        String string;
        try {
            String fileName = uploadFile.getOriginalFilename();   //获取文件名字
            InputStream inputStream = uploadFile.getInputStream();
            string = dbRestoreService.uploadBackups(fileName, inputStream);
        } catch (IOException e) {
            throw new SystemException(e);
        }
        return Result.ok(string);
    }

    @RequestMapping(path="/restore",method = RequestMethod.POST)
    public Result<Void> execRestore(@NotNull String path) {
        dbRestoreService.execRestore(path);
        return Result.ok();
    }


    @RequestMapping(path="/findRestore",method = RequestMethod.POST)
    public Result<Backups> findRestoreResult() {
        Backups backupsResult = dbRestoreService.findRestoreResult();
        return Result.ok(backupsResult);
    }


}
