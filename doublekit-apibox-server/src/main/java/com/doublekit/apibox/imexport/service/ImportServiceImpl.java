package com.doublekit.apibox.imexport.service;

import com.doublekit.apibox.imexport.type.PostmanImport;
import com.doublekit.apibox.imexport.type.ReportImport;
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
    public void importData(String type, String workspaceId, InputStream stream) throws IOException {
        switch (type){
            case "postman":
                postmanImport.importData(workspaceId,stream);
                break;
            case "report":
                reportImport.importData(workspaceId,stream);
                break;
        }
    }
}
