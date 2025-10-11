package io.tiklab.postin.api.http.mock.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.servlet.ServletRequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONPath;

import io.tiklab.core.exception.ApplicationException;
import io.tiklab.postin.api.apix.model.Apix;
import io.tiklab.postin.api.apix.service.ApixService;
import io.tiklab.postin.api.http.definition.service.HttpApiService;
import io.tiklab.postin.api.http.mock.model.FormParamMock;
import io.tiklab.postin.api.http.mock.model.FormParamMockQuery;
import io.tiklab.postin.api.http.mock.model.JsonParamMock;
import io.tiklab.postin.api.http.mock.model.JsonParamMockQuery;
import io.tiklab.postin.api.http.mock.model.Mock;
import io.tiklab.postin.api.http.mock.model.MockQuery;
import io.tiklab.postin.api.http.mock.model.QueryParamMock;
import io.tiklab.postin.api.http.mock.model.QueryParamMockQuery;
import io.tiklab.postin.api.http.mock.model.RequestHeaderMock;
import io.tiklab.postin.api.http.mock.model.RequestHeaderMockQuery;
import io.tiklab.postin.api.http.mock.model.RequestMock;
import io.tiklab.postin.api.http.mock.service.FormParamMockService;
import io.tiklab.postin.api.http.mock.service.JsonParamMockService;
import io.tiklab.postin.api.http.mock.service.MockService;
import io.tiklab.postin.api.http.mock.service.QueryParamMockService;
import io.tiklab.postin.api.http.mock.service.RequestHeaderMockService;
import io.tiklab.postin.api.http.mock.service.RequestMockService;
import io.tiklab.postin.common.ErrorCode;
import io.tiklab.postin.common.MagicValue;
import io.tiklab.postin.node.model.Node;
import io.tiklab.postin.node.model.NodeQuery;
import io.tiklab.postin.node.service.NodeService;

/**
 * mock
 * servlet 请求处理
 */
@Service
public class MockServletRequest {

    private static final Logger logger = LoggerFactory.getLogger(MockServletRequest.class);

    @Autowired
    MockService mockService;

    @Autowired
    ApixService apixService;

    @Autowired
    HttpApiService httpApiService;

    @Autowired
    RequestHeaderMockService requestHeaderMockService;

    @Autowired
    QueryParamMockService queryParamMockService;

    @Autowired
    RequestMockService requestMockService;

    @Autowired
    FormParamMockService formParamMockService;

    @Autowired
    JsonParamMockService jsonParamMockService;

    @Autowired
    MockServletResponse mockServletResponse;

    @Autowired
    NodeService nodeService;


    /**
     * 处理Mock请求
     * @param request HTTP请求
     * @param response HTTP响应
     * @throws IOException IO异常
     */
    public void actRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String apiId = extractApiId(request);
        processMockRequest(request, response, apiId);
    }

    /**
     * 从请求URI中提取API ID
     * @param request HTTP请求
     * @return API ID
     */
    private String extractApiId(HttpServletRequest request) {
        String[] parts = request.getRequestURI().split("/");
        String workspaceId = parts[2];
        
        // 构建接口路径
        StringBuilder pathBuilder = new StringBuilder();
        for (int i = 3; i < parts.length; i++) {
            pathBuilder.append("/").append(parts[i]);
        }
        
        return getMethodId(workspaceId, pathBuilder.toString());
    }

    /**
     * 处理Mock请求逻辑
     * @param request HTTP请求
     * @param response HTTP响应
     * @param apiId API ID
     * @throws IOException IO异常
     */
    private void processMockRequest(HttpServletRequest request, HttpServletResponse response, String apiId) throws IOException {
     
        List<Mock> mockList = findEnabledMocks(apiId);
        
        if (CollectionUtils.isEmpty(mockList)) {
            mockServletResponse.definitionResponse(response, apiId);
            return;
        }
        
        // 缓存请求体数据，避免重复读取流
        RequestBodyCache bodyCache = new RequestBodyCache();
        
        for (Mock mock : mockList) {
            if (isMockMatch(mock, request, bodyCache)) {
                mockServletResponse.actResponse(mock.getId(), response);
                return;
            }
        }

        // 所有Mock都不匹配，返回默认响应
        mockServletResponse.definitionResponse(response, apiId);
    }

    /**
     * 查找启用的Mock列表
     * @param apiId API ID
     * @return Mock列表
     */
    private List<Mock> findEnabledMocks(String apiId) {
        MockQuery mockQuery = new MockQuery().setHttpId(apiId);
        List<Mock> allMocks = mockService.findMockList(mockQuery);
        
        if (CollectionUtils.isEmpty(allMocks)) {
            return new ArrayList<>();
        }
        
        return allMocks.stream()
                .filter(mock -> mock.getEnable() == 1)
                .collect(java.util.stream.Collectors.toList());
    }

    /**
     * 检查Mock是否匹配请求
     * @param mock Mock对象
     * @param request HTTP请求
     * @param bodyCache 请求体缓存
     * @return 是否匹配
     * @throws IOException IO异常
     */
    private boolean isMockMatch(Mock mock, HttpServletRequest request, RequestBodyCache bodyCache) throws IOException {
        String mockId = mock.getId();
        
        // 检查Header匹配
        if (!isHeaderMatch(mockId, request)) {
            return false;
        }
        
        // 检查Query参数匹配
        if (!isQueryMatch(mockId, request)) {
            return false;
        }
        
        // 检查请求体匹配
        return isBodyMatch(mockId, request, bodyCache);
    }

    /**
     * 请求体缓存类
     */
    private class RequestBodyCache {
        private String jsonData;
        private List<Map<String, String>> formData;
        private boolean jsonCached = false;
        private boolean formCached = false;
        
        public String getJsonData(HttpServletRequest request) throws IOException {
            if (!jsonCached) {
                jsonData = readJsonFromRequest(request);
                jsonCached = true;
            }
            return jsonData;
        }
        
        public List<Map<String, String>> getFormData(HttpServletRequest request) {
            if (!formCached) {
                formData = readFormFromRequest(request);
                formCached = true;
            }
            return formData;
        }
    }

    /**
     * 根据工作空间ID和路径获取方法ID
     * @param workspaceId 工作空间ID
     * @param path 接口路径
     * @return 方法ID
     */
    public String getMethodId(String workspaceId, String path) {
        NodeQuery nodeQuery = new NodeQuery();
        nodeQuery.setWorkspaceId(workspaceId);
        nodeQuery.setType(MagicValue.PROTOCOL_TYPE_HTTP);
        
        List<Node> nodeList = nodeService.findNodeList(nodeQuery);
        
        if (CollectionUtils.isEmpty(nodeList)) {
            return null;
        }
        
        for (Node node : nodeList) {
            Apix apix = apixService.findApix(node.getId());
            if (apix != null && path.equals(apix.getPath())) {
                return apix.getId();
            }
        }
        
        return null;
    }

    /**
     * 从请求中读取Form数据
     * @param request HTTP请求
     * @return Form数据列表
     */
    public List<Map<String, String>> readFormFromRequest(HttpServletRequest request) {
        List<Map<String, String>> formDataList = new ArrayList<>();
        
        try {
            DiskFileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory);
            upload.setHeaderEncoding("UTF-8");
            
            List<FileItem> fileItems = upload.parseRequest(new ServletRequestContext(request));
            
            if (CollectionUtils.isNotEmpty(fileItems)) {
                for (FileItem item : fileItems) {
                    if (item.isFormField()) {
                        Map<String, String> formData = new HashMap<>();
                        formData.put(item.getFieldName(), item.getString("utf-8"));
                        formDataList.add(formData);
                    }
                }
            }
        } catch (Exception e) {
            throw new ApplicationException(ErrorCode.NOT_FIND_CODE, "读取Form数据失败: " + e.getMessage(), e);
        }
        
        return formDataList;
    }

    /**
     * 从请求中读取JSON数据
     * @param request HTTP请求
     * @return JSON字符串
     * @throws IOException IO异常
     */
    public String readJsonFromRequest(HttpServletRequest request) throws IOException {
        StringBuilder jsonBuilder = new StringBuilder();
        
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(request.getInputStream(), "utf-8"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                jsonBuilder.append(line);
            }
        }
        
        return jsonBuilder.toString();
    }


    /**
     * 检查Header是否匹配
     * @param mockId Mock ID
     * @param request HTTP请求
     * @return 是否匹配
     */
    public boolean isHeaderMatch(String mockId, HttpServletRequest request) {
        RequestHeaderMockQuery query = new RequestHeaderMockQuery().setMockId(mockId);
        List<RequestHeaderMock> mockHeaders = requestHeaderMockService.findRequestHeaderMockList(query);
        
        if (CollectionUtils.isEmpty(mockHeaders)) {
            return true; // 没有Header要求，默认匹配
        }
        
        for (RequestHeaderMock mockHeader : mockHeaders) {
            String expectedName = mockHeader.getHeaderName();
            String expectedValue = mockHeader.getValue();
            String actualValue = request.getHeader(expectedName);
            
            if (!expectedValue.equals(actualValue)) {
                return false;
            }
        }
        
        return true;
    }

    /**
     * 检查Query参数是否匹配
     * @param mockId Mock ID
     * @param request HTTP请求
     * @return 是否匹配
     */
    public boolean isQueryMatch(String mockId, HttpServletRequest request) {
        QueryParamMockQuery query = new QueryParamMockQuery().setMockId(mockId);
        List<QueryParamMock> mockParams = queryParamMockService.findQueryParamMockList(query);
        
        if (CollectionUtils.isEmpty(mockParams)) {
            return true; // 没有Query参数要求，默认匹配
        }
        
        for (QueryParamMock mockParam : mockParams) {
            String expectedName = mockParam.getParamName();
            String expectedValue = mockParam.getValue();
            String actualValue = request.getParameter(expectedName);
            
            if (!expectedValue.equals(actualValue)) {
                return false;
            }
        }
        
        return true;
    }

    /**
     * 检查请求体是否匹配
     * @param mockId Mock ID
     * @param request HTTP请求
     * @param bodyCache 请求体缓存
     * @return 是否匹配
     * @throws IOException IO异常
     */
    public boolean isBodyMatch(String mockId, HttpServletRequest request, RequestBodyCache bodyCache) throws IOException {
        RequestMock requestMock = requestMockService.findRequestMock(mockId);
        String bodyType = requestMock.getBodyType();
        
        if ("form".equals(bodyType)) {
            return isFormMatch(mockId, request, bodyCache);
        } else {
            return isJsonMatch(mockId, request, bodyCache);
        }
    }

    /**
     * 检查Form数据是否匹配
     * @param mockId Mock ID
     * @param request HTTP请求
     * @param bodyCache 请求体缓存
     * @return 是否匹配
     */
    public boolean isFormMatch(String mockId, HttpServletRequest request, RequestBodyCache bodyCache) {
        List<Map<String, String>> formData = bodyCache.getFormData(request);
        
        FormParamMockQuery query = new FormParamMockQuery().setMockId(mockId);
        List<FormParamMock> mockParams = formParamMockService.findFormParamMockList(query);
        
        if (CollectionUtils.isEmpty(mockParams)) {
            return true; // 没有Form参数要求，默认匹配
        }
        
        for (FormParamMock mockParam : mockParams) {
            String expectedName = mockParam.getParamName();
            String expectedValue = mockParam.getValue();
            
            boolean found = false;
            for (Map<String, String> formItem : formData) {
                if (expectedName.equals(formItem.keySet().iterator().next()) && 
                    expectedValue.equals(formItem.values().iterator().next())) {
                    found = true;
                    break;
                }
            }
            
            if (!found) {
                return false;
            }
        }
        
        return true;
    }

    /**
     * 检查JSON数据是否匹配
     * @param mockId Mock ID
     * @param request HTTP请求
     * @param bodyCache 请求体缓存
     * @return 是否匹配
     * @throws IOException IO异常
     */
    public boolean isJsonMatch(String mockId, HttpServletRequest request, RequestBodyCache bodyCache) throws IOException {
        String jsonData = bodyCache.getJsonData(request);
        
        JsonParamMockQuery query = new JsonParamMockQuery().setMockId(mockId);
        List<JsonParamMock> mockParams = jsonParamMockService.findJsonParamMockList(query);
        
        if (CollectionUtils.isEmpty(mockParams)) {
            return true; // 没有JSON参数要求，默认匹配
        }
        
        for (JsonParamMock mockParam : mockParams) {
            String jsonPath = mockParam.getExp();
            String expectedValue = mockParam.getValue();
            
            Object actualValue = JSONPath.read(jsonData, jsonPath);
            
            if (!expectedValue.equals(String.valueOf(actualValue))) {
                return false;
            }
        }
        
        return true;
    }

}
