package com.tiklab.postlink.integration.imexport.service;

import com.tiklab.postlink.integration.imexport.type.PostmanImport;
import com.tiklab.postlink.integration.imexport.type.ReportImport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;

@Service
public class ImportServiceImpl implements ImportService{

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
