package io.tiklab.postin.config;

import io.tiklab.postin.support.backups.model.Backups;
import io.tiklab.postin.support.backups.service.DbBackupsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class BackupsTask {

    private static final Logger logger = LoggerFactory.getLogger(BackupsTask.class);

    @Autowired
    DbBackupsService dbBackupsService;

    @Scheduled(cron = "${postin.backups.time}")
    public void scheduledBackups(){
        logger.info("Eas备份定时任务被触发......");

        Backups backupsResult = dbBackupsService.findBackupsResult();
        Boolean scheduled = backupsResult.getScheduled();

        if (!Objects.isNull(scheduled) && scheduled ){
            logger.info("应用开启定时备份......");
            dbBackupsService.execBackups();
        }else {
            logger.info("应用未开启定时备份......");
        }
    }
}
