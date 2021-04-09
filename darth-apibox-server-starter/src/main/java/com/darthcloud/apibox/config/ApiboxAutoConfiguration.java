package com.darthcloud.apibox.config;


import com.darthcloud.apibox.apidef.entity.*;
import com.darthcloud.apibox.apidef.model.*;
import com.darthcloud.apibox.apimock.entity.*;
import com.darthcloud.apibox.apimock.model.*;
import com.darthcloud.apibox.apitest.entity.*;
import com.darthcloud.apibox.apitest.model.*;
import com.darthcloud.apibox.category.entity.CategoryPo;
import com.darthcloud.apibox.category.model.Category;
import com.darthcloud.apibox.client.builder.ApiboxBuilder;
import com.darthcloud.apibox.sysmgr.entity.EnvironmentPo;
import com.darthcloud.apibox.sysmgr.model.Environment;
import com.darthcloud.apibox.workspace.entity.WorkspacePo;
import com.darthcloud.apibox.workspace.model.Workspace;
import com.darthcloud.beans.register.BeanMapperRegister;
import com.darthcloud.dal.datainiter.DataInitializer;
import com.darthcloud.message.annotation.EnableMessageServer;
import com.darthcloud.orga.annotation.EnableOrgaServer;
import com.darthcloud.privilege.annotation.EnablePrivilegeServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.*;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

@Configuration
@ConditionalOnProperty(name="apibox.server.enabled",havingValue = "true")
@EnableOrgaServer
@EnablePrivilegeServer
@EnableMessageServer
@PropertySource(value = "classpath:application-${env:local}.properties")
@ComponentScan({"com.darthcloud.apibox"})
public class ApiboxAutoConfiguration {

    private static Logger logger = LoggerFactory.getLogger(ApiboxAutoConfiguration.class);

    @Value("${apibox.scan.package}")
    private String scanPackage;

    @Value("${apibox.doc.path}")
    private String docPath;

    @PostConstruct
    public void init(){
        BeanMapperRegister.instance()
                .map(Workspace.class, WorkspacePo.class)
                .map(Category.class, CategoryPo.class)
                //接口定义
                .map(MethodEx.class, MethodPo.class)
                .map(RequestHeader.class, RequestHeaderPo.class)
                .map(QueryParam.class, QueryParamPo.class)
                .map(RequestBodyEx.class, RequestBodyPo.class)
                .map(FormParam.class, FormParamPo.class)
                .map(JsonParam.class, JsonParamPo.class)
                .map(RawParam.class, RawParamPo.class)
                .map(PreScript.class, PreScriptPo.class)
                .map(AfterScript.class, AfterScriptPo.class)
                .map(ResponseHeader.class, ResponseHeaderPo.class)
                .map(ResponseResult.class, ResponseResultPo.class)
                .map(JsonResponse.class, JsonResponsePo.class)
                .map(RawResponse.class, RawResponsePo.class)
                //接口测试
                .map(Testcase.class, TestcasePo.class)
                .map(RequestHeaderCase.class, RequestHeaderCasePo.class)
                .map(QueryParamCase.class, QueryParamCasePo.class)
                .map(RequestBodyCase.class, RequestBodyCasePo.class)
                .map(FormParamCase.class, FormParamCasePo.class)
                .map(JsonParamCase.class, JsonParamCasePo.class)
                .map(RawParamCase.class, RawParamCasePo.class)
                .map(PreScriptCase.class, PreScriptCasePo.class)
                .map(AfterScriptCase.class, AfterScriptCasePo.class)
                .map(AssertCase.class,AssertCasePo.class)
                .map(TestInstance.class,TestInstancePo.class)
                .map(RequestInstance.class,RequestInstancePo.class)
                .map(ResponseInstance.class,ResponseInstancePo.class)
                .map(AssertInstance.class,AssertInstancePo.class)
                //接口mock
                .map(Mock.class, MockPo.class)
                .map(RequestHeaderMock.class, RequestHeaderMockPo.class)
                .map(QueryParamMock.class, QueryParamMockPo.class)
                .map(RequestBodyMock.class, RequestBodyMockPo.class)
                .map(FormParamMock.class, FormParamMockPo.class)
                .map(JsonParamMock.class, JsonParamMockPo.class)
                .map(ResponseMock.class, ResponseMockPo.class)
                .map(ResponseHeaderMock.class, ResponseHeaderMockPo.class)
                .map(ResponseResultMock.class, ResponseResultMockPo.class)
                .map(JsonResponseMock.class, JsonResponseMockPo.class)
                .map(RawResponseMock.class, RawResponseMockPo.class)
                //其它
                .map(Environment.class, EnvironmentPo.class)
        ;
    }

    @Bean("dataInitializerForApibox")
    public DataInitializer dataInitializerForApibox(DataSource dataSource) {
        return new DataInitializer(dataSource,new String[]{
                "scripts/apibase.sql",
                "scripts/apidef.sql",
                "scripts/apitest.sql",
                "scripts/apimock.sql"
        });
    }

    @Bean
    @DependsOn({"joinQuery","dataInitializerForApibox"})
    @Profile("local")
    public ApiboxIniter apiboxIniter(){
        new ApiboxBuilder()
                .scan(scanPackage)
                .docPath(docPath)
                .build();
        return new ApiboxIniter();
    }

    class ApiboxIniter {}

}
