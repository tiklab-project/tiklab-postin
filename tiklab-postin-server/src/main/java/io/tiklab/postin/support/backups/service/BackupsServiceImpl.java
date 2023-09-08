package io.tiklab.postin.support.backups.service;

import io.tiklab.postin.support.backups.dao.BackupsDao;
import io.tiklab.postin.support.backups.model.Backups;
import io.tiklab.postin.support.backups.model.BackupsQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Comparator;
import java.util.List;

@Service
public class BackupsServiceImpl implements BackupsService {

    @Autowired
    BackupsDao backupsDao;


    @Override
    public String createBackups(Backups backups){
       return backupsDao.createBackups(backups);
    }

    @Override
    public void updateBackups(Backups backups){
        backupsDao.updateBackups(backups);
    }

    @Override
    public void deleteBackups(String backupsId){
        backupsDao.deleteBackups(backupsId);
    }


    @Override
    public Backups findBackups(String backupsId){
        return backupsDao.findBackups(backupsId);
    }


    @Override
    public List<Backups> findAllBackups(){
        return backupsDao.findAllBackups();
    }

    @Override
    public Backups findLastBackups(String type){
        BackupsQuery backupsQuery = new BackupsQuery();
        backupsQuery.setType(type);
        List<Backups> allBackups = backupsDao.findBackupsList(backupsQuery);
        if (allBackups.isEmpty()){
            return null;
        }
        allBackups.sort(Comparator.comparing(Backups::getCreateTime).reversed());
        return allBackups.get(0);
    }




}
