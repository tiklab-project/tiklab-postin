package com.doublekit.apibox.integration.imexport.service;

import com.doublekit.apibox.integration.imexport.type.PostmanImport;
import com.doublekit.apibox.integration.imexport.type.ReportImport;
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
        postmanImport.postmanAnalysisData(workspaceId,stream);
    }

    @Override
    public void importReport( String workspaceId, InputStream stream) throws IOException {
        reportImport.reportAnalysisData(workspaceId,stream);
    }
}
