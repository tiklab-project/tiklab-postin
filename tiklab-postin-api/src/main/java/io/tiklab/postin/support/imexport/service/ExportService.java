package io.tiklab.postin.support.imexport.service;


import java.io.IOException;

public interface ExportService {


     String generateHtml(String workspaceId) throws IOException;

     String allJson(String workspaceId);


}
