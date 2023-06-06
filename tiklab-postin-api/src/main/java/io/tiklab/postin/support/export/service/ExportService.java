package io.tiklab.postin.support.export.service;


import java.io.IOException;

public interface ExportService {


    public String generateHtml(String workspaceId) throws IOException;

    public String allJson(String workspaceId);


}
