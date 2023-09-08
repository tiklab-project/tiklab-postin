package io.tiklab.postin.support.backups.service;

import io.tiklab.postin.support.backups.model.Backups;
import java.io.InputStream;

public interface DbRestoreService {

    String uploadBackups(String fileName, InputStream inputStream);

    void execRestore(String filePath);

    Backups findRestoreResult();

}
