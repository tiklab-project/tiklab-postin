package net.tiklab.postin.support.imexport.service;

import net.tiklab.postin.support.imexport.type.PostmanImport;
import net.tiklab.postin.support.imexport.type.ReportImport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;

/**
 * 导入导出 服务
 */
@Service
public class ImportServiceImpl implements ImportService {

    @Autowired
    PostmanImport postmanImport;

    @Autowired
    ReportImport reportImport;

    @Override
    public void importPostman( String workspaceId, InputStream stream) throws IOException {
        postmanImport.analysisPostmanData(workspaceId,stream);
    }

    @Override
    public void importReport( String workspaceId, InputStream stream) throws IOException {
        reportImport.analysisReportData(workspaceId,stream);
    }
}
