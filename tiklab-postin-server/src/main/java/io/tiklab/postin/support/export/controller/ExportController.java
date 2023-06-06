package io.tiklab.postin.support.export.controller;


import io.tiklab.core.Result;
import io.tiklab.postin.annotation.ApiMethod;
import io.tiklab.postin.annotation.ApiParam;
import io.tiklab.postin.support.export.service.ExportService;
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



}
