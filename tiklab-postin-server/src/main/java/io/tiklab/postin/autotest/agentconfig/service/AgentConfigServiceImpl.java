package io.tiklab.postin.autotest.agentconfig.service;

import com.alibaba.fastjson.JSONObject;
import io.tiklab.core.Result;
import io.tiklab.core.exception.ApplicationException;
import io.tiklab.postin.autotest.agentconfig.model.AgentConfig;
import io.tiklab.postin.autotest.agentconfig.model.AgentConfigQuery;
import io.tiklab.postin.autotest.agentconfig.entity.AgentConfigEntity;
import io.tiklab.core.page.Pagination;
import io.tiklab.core.page.PaginationBuilder;
import io.tiklab.postin.autotest.agentconfig.dao.AgentConfigDao;

import io.tiklab.postin.autotest.common.wsTest.service.WebSocketServiceImpl;
import io.tiklab.postin.autotest.common.wsTest.service.WsTestCommonFn;
import io.tiklab.postin.common.ErrorCode;
import io.tiklab.postin.common.MagicValue;
import io.tiklab.toolkit.beans.BeanMapper;
import io.tiklab.toolkit.join.JoinTemplate;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.WebSocketSession;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;


/**
* agent配置 服务
*/
@Service
public class AgentConfigServiceImpl implements AgentConfigService {

    @Autowired
    AgentConfigDao agentConfigDao;

    @Autowired
    JoinTemplate joinTemplate;

    @Autowired
    WsTestCommonFn wsTestCommonFn;
    @Override
    public String createAgentConfig(@NotNull @Valid AgentConfig agentConfig) {
        AgentConfigEntity agentConfigEntity = BeanMapper.map(agentConfig, AgentConfigEntity.class);
        agentConfigEntity.setUpdateTime(new Timestamp(System.currentTimeMillis()));

        return agentConfigDao.createAgentConfig(agentConfigEntity);
    }

    @Override
    public void updateAgentConfig(@NotNull @Valid AgentConfig agentConfig) {
        AgentConfigEntity agentConfigEntity = BeanMapper.map(agentConfig, AgentConfigEntity.class);
        agentConfigEntity.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        agentConfigDao.updateAgentConfig(agentConfigEntity);
    }

    @Override
    public void deleteAgentConfig(@NotNull String id) {
        agentConfigDao.deleteAgentConfig(id);
    }

    @Override
    public AgentConfig findOne(String id) {
        AgentConfigEntity agentConfigEntity = agentConfigDao.findAgentConfig(id);

        AgentConfig agentConfig = BeanMapper.map(agentConfigEntity, AgentConfig.class);
        return agentConfig;
    }

    @Override
    public List<AgentConfig> findList(List<String> idList) {
        List<AgentConfigEntity> agentConfigEntityList =  agentConfigDao.findAgentConfigList(idList);

        List<AgentConfig> agentConfigList =  BeanMapper.mapList(agentConfigEntityList,AgentConfig.class);
        return agentConfigList;
    }

    @Override
    public AgentConfig findAgentConfig(@NotNull String id) {
        AgentConfig agentConfig = findOne(id);

        joinTemplate.joinQuery(agentConfig);

        return agentConfig;
    }

    @Override
    public List<AgentConfig> findAllAgentConfig() {
        List<AgentConfigEntity> agentConfigEntityList =  agentConfigDao.findAllAgentConfig();

        List<AgentConfig> agentConfigList =  BeanMapper.mapList(agentConfigEntityList,AgentConfig.class);

        joinTemplate.joinQuery(agentConfigList);

        return agentConfigList;
    }

    @Override
    public List<AgentConfig> findAgentConfigList(AgentConfigQuery agentConfigQuery) {
        List<AgentConfigEntity> agentConfigEntityList = agentConfigDao.findAgentConfigList(agentConfigQuery);

        List<AgentConfig> agentConfigList = BeanMapper.mapList(agentConfigEntityList,AgentConfig.class);

        joinTemplate.joinQuery(agentConfigList);

        return agentConfigList;
    }

    @Override
    public Pagination<AgentConfig> findAgentConfigPage(AgentConfigQuery agentConfigQuery) {
        Pagination<AgentConfigEntity>  pagination = agentConfigDao.findAgentConfigPage(agentConfigQuery);

        List<AgentConfig> agentConfigList = BeanMapper.mapList(pagination.getDataList(),AgentConfig.class);

        joinTemplate.joinQuery(agentConfigList);

        return PaginationBuilder.build(pagination,agentConfigList);
    }

    @Override
    public String getAgent(){
        Pagination<AgentConfig> agentConfigPage = findAgentConfigPage(new AgentConfigQuery());
        if(CollectionUtils.isNotEmpty(agentConfigPage.getDataList())){
            List<AgentConfig> agentConfigList = agentConfigPage.getDataList();
            AgentConfig agentConfig = agentConfigList.get(0);
            String url = agentConfig.getAddress();
            return url;
        }

        return null;
    }

    @Override
    public List<AgentConfig> getAgentList(){
        AgentConfigQuery agentConfigQuery = new AgentConfigQuery();
        agentConfigQuery.setStatus("online");
        agentConfigQuery.setEnable(1);
        List<AgentConfig> agentConfigList = findAgentConfigList(agentConfigQuery);

        if( CollectionUtils.isNotEmpty(agentConfigList)) {
            return agentConfigList;
        }else {
            throw new ApplicationException(ErrorCode.NOT_FIND_CODE,"Agent is not found. Unable to connect to the WebSocket server.");
        }
    }

    @Override
    public List<Object> getConnectList() {
        List<AgentConfig> agentConfigList = findAgentConfigList(new AgentConfigQuery());
        Set<String> agentIdSet = agentConfigList.stream()
                .map(AgentConfig::getId)
                .collect(Collectors.toSet());

        Map<String, WebSocketSession> agentSessionMap = WebSocketServiceImpl.agentSessionMap;
        List<Object> resultList = new ArrayList<>();

        for (Map.Entry<String, WebSocketSession> entry : agentSessionMap.entrySet()) {
            String agentId = entry.getKey();
            WebSocketSession session = entry.getValue();

            if (MagicValue.AGENT_DEFAULT.equals(agentId)) {
                continue;
            }

            // 列表中已经有了，就不显示了
            if (!agentIdSet.contains(agentId)) {
                JSONObject agentInfo = wsTestCommonFn.getAgentInfoFromQuery(session);
                resultList.add(agentInfo);
            }
        }

        return resultList;
    }


    @Override
    public String createConnect(String agentId) {
        Map<String, WebSocketSession> agentSessionMap = WebSocketServiceImpl.agentSessionMap;
        WebSocketSession webSocketSession = agentSessionMap.get(agentId);

        JSONObject agentInfo = wsTestCommonFn.getAgentInfoFromQuery(webSocketSession);

        if(!agentId.equals(MagicValue.AGENT_DEFAULT)){
            String name = agentInfo.getString("name");
            String status = agentInfo.getString("status");
            String address = agentInfo.getString("address");

            AgentConfig agent = findAgentConfig(agentId);

            AgentConfig agentConfig = new AgentConfig();
            agentConfig.setId(agentId);
            agentConfig.setStatus(status);
            if(agent==null){
                agentConfig.setName(name);
                agentConfig.setAddress(address);
                createAgentConfig(agentConfig);
            }else {
                updateAgentConfig(agentConfig);
            }
        }

        return agentId;
    }

}