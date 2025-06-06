package io.tiklab.postin.support.imexport.service;

import io.tiklab.postin.support.imexport.type.openapi.OpenApi;
import io.tiklab.postin.support.imexport.type.postman.PostmanImport;
import io.tiklab.postin.support.imexport.type.ReportImport;
import io.tiklab.postin.support.imexport.type.swagger.Swagger2Import;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
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

    @Autowired
    Swagger2Import swagger2Import;

    @Autowired
    OpenApi api3Import;

    @Override
    public void importData(String workspaceId, InputStream stream, String type) throws IOException {
        switch (type){
            case "postman":
                postmanImport.analysisPostmanData(workspaceId,stream);
                break;
            case "swagger2":
                swagger2Import.analysisSwagger2(workspaceId,stream);
                break;
            case "openapi3":
                api3Import.analysisOpenApi(workspaceId,stream);
                break;
            default:
                break;

        }
    }


    @Override
    public void importReport( String workspaceId, InputStream stream) throws IOException {
        reportImport.analysisReportData(workspaceId,stream);
    }

    @Override
    public void getDoc(){

    }


}
