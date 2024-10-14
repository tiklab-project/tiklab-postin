package io.tiklab.postin.support.imexport.service;


import java.io.IOException;
import java.io.InputStream;

public interface ImportService {

    /**
     * 数据导入
     * @param workspaceId
     * @param stream
     * @param type
     * @throws IOException
     */
    void importData(String workspaceId, InputStream stream, String type) throws IOException;


    /**
     * report导入
     * @param workspaceId
     * @param stream
     * @throws IOException
     */
    void importReport( String workspaceId, InputStream stream) throws IOException;


    void getDoc();
}
