package io.tiklab.postin.support.environment.service;

import io.tiklab.core.page.Pagination;
import io.tiklab.core.page.PaginationBuilder;
import io.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.DeleteBuilders;
import io.tiklab.postin.support.environment.dao.EnvServerDao;
import io.tiklab.postin.support.environment.entity.EnvServerEntity;
import io.tiklab.postin.support.environment.model.EnvServer;
import io.tiklab.postin.support.environment.model.EnvServerQuery;
import io.tiklab.toolkit.beans.BeanMapper;
import io.tiklab.toolkit.join.JoinTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 环境中服务地址 服务
*/
@Service
public class EnvServerServiceImpl implements EnvServerService {

    @Autowired
    EnvServerDao envServerDao;

    @Autowired
    JoinTemplate joinTemplate;

    @Override
    public String createEnvServer(@NotNull @Valid EnvServer envServer) {
        EnvServerEntity envServerEntity = BeanMapper.map(envServer, EnvServerEntity.class);

        return envServerDao.createEnvServer(envServerEntity);
    }

    @Override
    public void updateEnvServer(@NotNull @Valid EnvServer envServer) {
        EnvServerEntity envServerEntity = BeanMapper.map(envServer, EnvServerEntity.class);

        envServerDao.updateEnvServer(envServerEntity);
    }

    @Override
    public void deleteEnvServer(@NotNull String id) {
        envServerDao.deleteEnvServer(id);
    }

    @Override
    public void deleteAllEnvServer(String workspaceId) {
        DeleteCondition deleteCondition = DeleteBuilders.createDelete(EnvServerEntity.class)
                .eq("envId", workspaceId)
                .get();
        envServerDao.deleteEnvServer(deleteCondition);
    }

    @Override
    public EnvServer findOne(String id) {
        EnvServerEntity envServerEntity = envServerDao.findEnvServer(id);

        EnvServer envServer = BeanMapper.map(envServerEntity, EnvServer.class);
        return envServer;
    }

    @Override
    public List<EnvServer> findList(List<String> idList) {
        List<EnvServerEntity> envServerEntityList =  envServerDao.findEnvServerList(idList);

        List<EnvServer> envServerList =  BeanMapper.mapList(envServerEntityList,EnvServer.class);
        return envServerList;
    }

    @Override
    public EnvServer findEnvServer(@NotNull String id) {
        EnvServer envServer = findOne(id);

        return envServer;
    }

    @Override
    public List<EnvServer> findAllEnvServer() {
        List<EnvServerEntity> envServerEntityList =  envServerDao.findAllEnvServer();

        List<EnvServer> envServerList =  BeanMapper.mapList(envServerEntityList,EnvServer.class);

        return envServerList;
    }

    @Override
    public List<EnvServer> findEnvServerList(EnvServerQuery envServerQuery) {
        List<EnvServerEntity> envServerEntityList = envServerDao.findEnvServerList(envServerQuery);

        List<EnvServer> envServerList = BeanMapper.mapList(envServerEntityList,EnvServer.class);


        return envServerList;
    }

    @Override
    public Pagination<EnvServer> findEnvServerPage(EnvServerQuery envServerQuery) {

        Pagination<EnvServerEntity>  pagination = envServerDao.findEnvServerPage(envServerQuery);

        List<EnvServer> envServerList = BeanMapper.mapList(pagination.getDataList(),EnvServer.class);


        return PaginationBuilder.build(pagination,envServerList);
    }
}