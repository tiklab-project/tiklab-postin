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

    // 获取方法头部的注释
    private static String getJavadocComment(TypeElement typeElement, ExecutableElement method) {
//        Elements elementUtils = // 获取 Elements 的方法，请自行实现
//                Types typeUtils = // 获取 Types 的方法，请自行实现
//                SourceVersion sourceVersion = // 获取 SourceVersion 的方法，请自行实现

        // 获取方法的起始位置和结束位置
//        long startPosition = method.getStartPosition();
//        long endPosition = startPosition + elementUtils.getDocComment(method);

        // 获取方法头部的注释内容
//        String source = typeElement.getEnclosingElement().toString();
//        String javadocComment = source.substring((int) startPosition, (int) endPosition);

        return null;
    }


}
