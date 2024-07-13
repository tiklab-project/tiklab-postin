package io.thoughtware.postin.api.ws.ws.service;

import io.thoughtware.postin.api.apix.model.*;
import io.thoughtware.postin.api.apix.service.*;
import io.thoughtware.postin.api.ws.ws.model.WSApi;
import io.thoughtware.postin.common.MagicValue;
import io.thoughtware.postin.node.model.Node;
import io.thoughtware.postin.node.service.NodeService;
import io.thoughtware.postin.support.apistatus.service.ApiStatusService;
import io.thoughtware.postin.workspace.model.Workspace;
import io.thoughtware.rpc.annotation.Exporter;
import io.thoughtware.user.user.service.UserService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 定义
 * ws协议服务
*/
@Service
@Exporter
public class WSApiServiceImpl implements WSApiService {

    @Autowired
    ApixService apixService;

    @Autowired
    RequestHeaderService requestHeaderService;

    @Autowired
    QueryParamService queryParamService;

    @Autowired
    ApiRequestService apiRequestService;

    @Autowired
    JsonParamService jsonParamService;

    @Autowired
    RawParamService rawParamService;

    @Autowired
    NodeService nodeService;

    @Override
    public String createWSApi(@NotNull @Valid WSApi wsApi) {
        Node node = wsApi.getNode();
        Apix apix = wsApi.getApix();
        apix.setProtocolType(MagicValue.PROTOCOL_TYPE_WS);
        apix.setWorkspaceId(node.getWorkspace().getId());
        String apiId = apixService.createApix(apix);

        node.setId(apiId);
        node.setType(MagicValue.PROTOCOL_TYPE_WS);
        nodeService.createNode(node);

        //初始化请求响应中的类型
        ApiRequest apiRequest = new ApiRequest();
        apiRequest.setId(apiId);
        apiRequest.setApiId(apiId);
        apiRequest.setBodyType("raw");
        apiRequestService.createApiRequest(apiRequest);

        //初始化raw
        RawParam rawParam = new RawParam();
        rawParam.setApiId(apiId);
        rawParam.setId(apiId);
        rawParam.setType("text/plain");
        rawParam.setRaw("");
        rawParamService.createRawParam(rawParam);


        return  apiId;
    }


    @Override
    public void updateWSApi(@NotNull @Valid WSApi wsApi) {

        Apix apix = wsApi.getApix();
        apixService.updateApix(apix);

        nodeService.updateNode(wsApi.getNode());
    }

    @Override
    public void deleteWSApi(@NotNull String wsId) {
        //删除

        //删除请求头
        requestHeaderService.deleteAllRequestHeader(wsId);

        //删除query
        queryParamService.deleteAllQueryParam(wsId);

        //删除请求
        apiRequestService.deleteApiRequest(wsId);

        //删除raw
        rawParamService.deleteRawParam(wsId);

        jsonParamService.deleteJsonParam(wsId);

    }


    @Override
    public WSApi findWSApi(@NotNull String id) {
        WSApi wsApi = new WSApi();

        Apix apix = apixService.findApix(id);
        wsApi.setApix(apix);

        Node node = nodeService.findNode(id);
        wsApi.setNode(node);

        //获取请求头中的数据
        List<RequestHeader> requestHeaderList = requestHeaderService.findRequestHeaderList(new RequestHeaderQuery().setApiId(id));
        if(CollectionUtils.isNotEmpty(requestHeaderList)){
            wsApi.setHeaderList(requestHeaderList);
        }

        //获取查询参数的数据
        List<QueryParam> queryParamList = queryParamService.findQueryParamList(new QueryParamQuery().setApiId(id));
        if(CollectionUtils.isNotEmpty(queryParamList)){
            wsApi.setQueryList(queryParamList);
        }

        //获取请求体的类型
        ApiRequest apiRequest = apiRequestService.findApiRequest(id);
        wsApi.setRequest(apiRequest);
        String bodyType = apiRequest.getBodyType();

        if(!ObjectUtils.isEmpty(bodyType)){
            if(bodyType.equals(MagicValue.REQUEST_BODY_TYPE_JSON)){
                //获取json数据
                JsonParam jsonParam = jsonParamService.findJsonParam(id);
                wsApi.setJsonParam(jsonParam);
            } else if(bodyType.equals(MagicValue.REQUEST_BODY_TYPE_RAW)){
                //获取raw数据
                RawParam rawParam = rawParamService.findRawParam(id);
                if(!ObjectUtils.isEmpty(rawParam)){
                    wsApi.setRawParam(rawParam);
                }
            }
        }

        return wsApi;
    }




}