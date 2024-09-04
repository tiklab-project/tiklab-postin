package io.thoughtware.postin.support.imexport.service;


import java.io.IOException;
import java.io.InputStream;

public interface ImportService {

    /**
     * postman导入
     * @param workspaceId
     * @param stream
     * @throws IOException
     */
    void importPostman( String workspaceId, InputStream stream) throws IOException;


    /**
     * postman导入
     * @param workspaceId
     * @param stream
     * @throws IOException
     */
    void importSwagger2( String workspaceId, InputStream stream) throws IOException;

    /**
     * report导入
     * @param workspaceId
     * @param stream
     * @throws IOException
     */
    void importReport( String workspaceId, InputStream stream) throws IOException;


    void getDoc();
}
