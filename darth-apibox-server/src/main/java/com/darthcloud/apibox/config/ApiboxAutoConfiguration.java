package com.darthcloud.apibox.config;


import com.darthcloud.apibox.apidef.apxmethod.entity.ApxMethodPo;
import com.darthcloud.apibox.apidef.apxmethod.model.ApxMethod;
import com.darthcloud.apibox.apidef.formparam.entity.FormParamPo;
import com.darthcloud.apibox.apidef.formparam.model.FormParam;
import com.darthcloud.apibox.apidef.jsonparam.entity.JsonParamPo;
import com.darthcloud.apibox.apidef.jsonparam.model.JsonParam;
import com.darthcloud.apibox.apidef.jsonresponse.entity.JsonResponsePo;
import com.darthcloud.apibox.apidef.jsonresponse.model.JsonResponse;
import com.darthcloud.apibox.apidef.queryparam.entity.QueryParamPo;
import com.darthcloud.apibox.apidef.queryparam.model.QueryParam;
import com.darthcloud.apibox.apidef.requestheader.entity.RequestHeaderPo;
import com.darthcloud.apibox.apidef.requestheader.model.RequestHeader;
import com.darthcloud.apibox.apidef.responseheader.entity.ResponseHeaderPo;
import com.darthcloud.apibox.apidef.responseheader.model.ResponseHeader;
import com.darthcloud.apibox.apimock.formparammock.entity.FormParamMockPo;
import com.darthcloud.apibox.apimock.formparammock.model.FormParamMock;
import com.darthcloud.apibox.apimock.jsonresponsemock.entity.JsonResponseMockPo;
import com.darthcloud.apibox.apimock.jsonresponsemock.model.JsonResponseMock;
import com.darthcloud.apibox.apimock.mock.entity.MockPo;
import com.darthcloud.apibox.apimock.mock.model.Mock;
import com.darthcloud.apibox.apimock.queryparammock.entity.QueryParamMockPo;
import com.darthcloud.apibox.apimock.queryparammock.model.QueryParamMock;
import com.darthcloud.apibox.apimock.requestheadermock.entity.RequestHeaderMockPo;
import com.darthcloud.apibox.apimock.requestheadermock.model.RequestHeaderMock;
import com.darthcloud.apibox.apimock.responseheadermock.entity.ResponseHeaderMockPo;
import com.darthcloud.apibox.apimock.responseheadermock.model.ResponseHeaderMock;
import com.darthcloud.apibox.category.entity.CategoryPo;
import com.darthcloud.apibox.category.model.Category;
import com.darthcloud.apibox.workspace.entity.WorkspacePo;
import com.darthcloud.apibox.workspace.model.Workspace;
import com.darthcloud.beans.register.BeanMapperMetaRegister;
import com.darthcloud.dal.datainiter.DataInitializer;
import com.darthcloud.dal.annotation.EnableDal;
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

import javax.sql.DataSource;

@Configuration
@ConditionalOnProperty(name="apibox.server.enabled",havingValue = "true")
//基础组件
@EnableWeb
@EnableService
@EnableRpcClient
@EnableRpcServer
@EnableDal
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
                "scripts/FormParam.sql",
                "scripts/JsonParam.sql",
                "scripts/ResponseHeader.sql",
                "scripts/JsonResponse.sql",
                "scripts/Mock.sql",
                "scripts/RequestHeaderMock.sql",
                "scripts/QueryParamMock.sql",
                "scripts/FormParamMock.sql",
                "scripts/ResponseHeaderMock.sql",
                "scripts/JsonResponseMock.sql"

        });
    }

    @Bean("beanIniterForApibox")
    public BeanIniter beanIniterForApibox(){
        BeanMapperMetaRegister.getInstance()
                .add(Workspace.class, WorkspacePo.class)
                .add(Category.class, CategoryPo.class)
                .add(ApxMethod.class, ApxMethodPo.class)
                .add(RequestHeader.class, RequestHeaderPo.class)
                .add(QueryParam.class, QueryParamPo.class)
                .add(FormParam.class, FormParamPo.class)
                .add(JsonParam.class, JsonParamPo.class)
                .add(ResponseHeader.class, ResponseHeaderPo.class)
                .add(JsonResponse.class, JsonResponsePo.class)
                .add(Mock.class, MockPo.class)
                .add(RequestHeaderMock.class, RequestHeaderMockPo.class)
                .add(QueryParamMock.class, QueryParamMockPo.class)
                .add(FormParamMock.class, FormParamMockPo.class)
                .add(ResponseHeaderMock.class, ResponseHeaderMockPo.class)
                .add(JsonResponseMock.class, JsonResponseMockPo.class)
        ;
        return new BeanIniter();
    }

    class BeanIniter{}
}
