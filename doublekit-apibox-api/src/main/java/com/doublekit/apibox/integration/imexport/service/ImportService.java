package com.doublekit.apibox.integration.imexport.service;


import java.io.IOException;
import java.io.InputStream;

public interface ImportService {

    /**
     * 导入
     * @return
     */
    void importPostman( String workspaceId, InputStream stream) throws IOException;

    void importReport( String workspaceId, InputStream stream) throws IOException;
}
