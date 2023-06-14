package io.tiklab.postin.support.imexport.service;


import java.io.IOException;

public interface ExportService {


    public String generateHtml(String workspaceId) throws IOException;

    public String allJson(String workspaceId);


}
