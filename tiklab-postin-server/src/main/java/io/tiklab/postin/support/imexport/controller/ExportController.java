package io.tiklab.postin.support.imexport.controller;


import io.tiklab.postin.support.imexport.service.ExportService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * 导出 控制器
 */
@RestController
@RequestMapping("/port")
public class ExportController {

    private static Logger logger = LoggerFactory.getLogger(ExportController.class);

    @Autowired
    ExportService exportService;

    @RequestMapping(path = "/exportHtml",method = RequestMethod.POST)
    public  ResponseEntity<byte[]>  exportHtml(@NotNull String workspaceId  ) throws IOException {
        // 调用 ExportService 生成 HTML
        String html = exportService.generateHtml( workspaceId);

        // 将 HTML 字符串转换为字节数组
        byte[] htmlBytes = html.getBytes(StandardCharsets.UTF_8);

        // 设置响应头信息
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_HTML);
        headers.setContentDispositionFormData("attachment", "export.html");

        return new ResponseEntity<>(htmlBytes, headers, HttpStatus.OK);

    }

//    @RequestMapping(path = "/exportPdf",method = RequestMethod.POST)
//    public void exportPdf(HttpServletRequest request, HttpServletResponse response, @NotNull String workspaceId) throws IOException {
//        byte[] bytes = exportService.exportPdf(workspaceId);
//        // 设置响应头信息
//        response.setContentType(MediaType.APPLICATION_PDF_VALUE);
//        response.setHeader("Content-Disposition", "attachment; filename=output.pdf");
//        response.setContentLength(bytes.length);
//        // 将字节流写入响应流
//        ServletOutputStream outputStream = response.getOutputStream();
//        outputStream.write(bytes);
//        outputStream.flush();
//    }

}
