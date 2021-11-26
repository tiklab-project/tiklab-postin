package com.doublekit.apibox.imexport.service;


import java.io.IOException;
import java.io.InputStream;

public interface ImportService {

    /**
     * 导入
     * @return
     */
    String importData(String type, String workspaceId, InputStream stream) throws IOException;
}
