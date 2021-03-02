package com.darthcloud.apibox.config;


import com.darthcloud.apibox.apidef.entity.*;
import com.darthcloud.apibox.apidef.model.*;
import com.darthcloud.apibox.apimock.entity.*;
import com.darthcloud.apibox.apimock.model.*;
import com.darthcloud.apibox.apitest.entity.*;
import com.darthcloud.apibox.apitest.model.*;
import com.darthcloud.apibox.category.entity.CategoryPo;
import com.darthcloud.apibox.category.model.Category;
import com.darthcloud.apibox.workspace.entity.WorkspacePo;
import com.darthcloud.apibox.workspace.model.Workspace;
import com.darthcloud.beans.register.BeanMapperMetaRegister;
import com.darthcloud.dal.annotation.EnableDal;
import com.darthcloud.dal.datainiter.DataInitializer;
import com.darthcloud.dcs.client.annotation.EnableDcsClient;
import com.darthcloud.dcs.server.annotation.EnableDcsServer;
import com.darthcloud.dfs.client.annotation.EnableDfsClient;
import com.darthcloud.dfs.server.annotation.EnableDfsServer;
import com.darthcloud.dss.client.annotation.EnableDssClient;
import com.darthcloud.dss.server.annotation.EnableDssServer;
import com.darthcloud.orga.config.annotation.EnableOrgaServer;
import com.darthcloud.rpc.client.annotation.EnableRpcClient;
import com.darthcloud.rpc.server.annotation.EnableRpcServer;
import com.darthcloud.service.annotation.EnableService;
import com.darthcloud.web.annotation.EnableWeb;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestBody;

import javax.sql.DataSource;

@Configuration
@ConditionalOnProperty(name="apibox.server.enabled",havingValue = "true")
//基础组件
@EnableWeb
@EnableService
@EnableDal
@EnableRpcClient
@EnableRpcServer
@EnableDfsClient
@EnableDfsServer
@EnableDcsClient
@EnableDcsServer
@EnableDssClient
@EnableDssServer
//业务组件
@EnableOrgaServer
@ComponentScan({"com.darthcloud.apibox"})
public class ApiboxAutoConfiguration {

    private static Logger logger = LoggerFactory.getLogger(ApiboxAutoConfiguration.class);

    @Bean("dataInitializerForApibox")
    public DataInitializer dataInitializerForApibox(DataSource dataSource) {
        return new DataInitializer(dataSource,new String[]{
                "scripts/Workspace.sql",
                "scripts/Node.sql",
                "scripts/Category.sql",
                "scripts/ApxMethod.sql",
                "scripts/RequestHeader.sql",
                "scripts/QueryParam.sql",
                "scripts/RequestBody.sql",
                "scripts/FormParam.sql",
                "scripts/JsonParam.sql",
                "scripts/RawParam.sql",
                "scripts/PreScript.sql",
                "scripts/AfterScript.sql",
                "scripts/ResponseHeader.sql",
                "scripts/ResponseResult.sql",
                "scripts/JsonResponse.sql",
                "scripts/RawResponse.sql",
                "scripts/Mock.sql",
                "scripts/RequestHeaderMock.sql",
                "scripts/QueryParamMock.sql",
                "scripts/FormParamMock.sql",
                "scripts/ResponseMock.sql",
                "scripts/ResponseHeaderMock.sql",
                "scripts/ResponseResultMock.sql",
                "scripts/JsonResponseMock.sql",
                "scripts/RawResponseMock.sql",
                "scripts/Testcase.sql",
                "scripts/RequestHeaderCase.sql",
                "scripts/QueryParamCase.sql",
                "scripts/RequestBodyCase.sql",
                "scripts/FormParamCase.sql",
                "scripts/JsonParamCase.sql",
                "scripts/RawParamCase.sql",
                "scripts/PreScriptCase.sql",
                "scripts/AfterScriptCase.sql",
                "scripts/AssertCase.sql"

        });
    }

    @Bean("beanIniterForApibox")
    public BeanIniter beanIniterForApibox(){
        BeanMapperMetaRegister.getInstance()
                .add(Workspace.class, WorkspacePo.class)
                .add(Category.class, CategoryPo.class)
                .add(MethodEx.class, MethodPo.class)
                .add(RequestHeader.class, RequestHeaderPo.class)
                .add(QueryParam.class, QueryParamPo.class)
                .add(RequestBodyEx.class, RequestBodyPo.class)
                .add(FormParam.class, FormParamPo.class)
                .add(JsonParam.class, JsonParamPo.class)
                .add(RawParam.class, RawParamPo.class)
                .add(PreScript.class, PreScriptPo.class)
                .add(AfterScript.class, AfterScriptPo.class)
                .add(ResponseHeader.class, ResponseHeaderPo.class)
                .add(ResponseResult.class, ResponseResultPo.class)
                .add(JsonResponse.class, JsonResponsePo.class)
                .add(RawResponse.class, RawResponsePo.class)
                .add(Mock.class, MockPo.class)
                .add(RequestHeaderMock.class, RequestHeaderMockPo.class)
                .add(QueryParamMock.class, QueryParamMockPo.class)
                .add(FormParamMock.class, FormParamMockPo.class)
                .add(ResponseMock.class, ResponseMockPo.class)
                .add(ResponseHeaderMock.class, ResponseHeaderMockPo.class)
                .add(ResponseResultMock.class, ResponseResultMockPo.class)
                .add(JsonResponseMock.class, JsonResponseMockPo.class)
                .add(RawResponseMock.class, RawResponseMockPo.class)
                .add(Testcase.class, TestcasePo.class)
                .add(RequestHeaderCase.class, RequestHeaderCasePo.class)
                .add(QueryParamCase.class, QueryParamCasePo.class)
                .add(RequestBodyCase.class, RequestBodyCasePo.class)
                .add(FormParamCase.class, FormParamCasePo.class)
                .add(JsonParamCase.class, JsonParamCasePo.class)
                .add(RawParamCase.class, RawParamCasePo.class)
                .add(PreScriptCase.class, PreScriptCasePo.class)
                .add(AfterScriptCase.class, AfterScriptCasePo.class)
                .add(AssertCase.class,AssertCasePo.class)
        ;
        return new BeanIniter();
    }

    class BeanIniter{}
}
