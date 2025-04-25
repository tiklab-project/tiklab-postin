package io.tiklab.postin.support.environment.service;

import io.tiklab.core.page.Pagination;
import io.tiklab.postin.support.environment.model.EnvServer;
import io.tiklab.postin.support.environment.model.EnvServerQuery;
import io.tiklab.toolkit.join.annotation.FindList;
import io.tiklab.toolkit.join.annotation.FindOne;
import io.tiklab.toolkit.join.annotation.JoinProvider;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 环境中服务地址 服务接口
*/
@JoinProvider(model = EnvServer.class)
public interface EnvServerService {

    /**
    * 创建环境中服务地址
    * @param envServer
    * @return
    */
    String createEnvServer(@NotNull @Valid EnvServer envServer);

    /**
    * 更新环境中服务地址
    * @param envServer
    */
    void updateEnvServer(@NotNull @Valid EnvServer envServer);

    /**
    * 删除环境中服务地址
    * @param id
    */
    void deleteEnvServer(@NotNull String id);

    /**
     * 通过workspaceId删除
     * @param workspaceId
     */
    void deleteAllEnvServer(@NotNull String workspaceId);


    @FindOne
    EnvServer findOne(@NotNull String id);

    @FindList
    List<EnvServer> findList(List<String> idList);

    /**
    * 查找环境中服务地址
    * @param id
    * @return
    */
    EnvServer findEnvServer(@NotNull String id);

    /**
    * 查找所有环境中服务地址
    * @return
    */
    List<EnvServer> findAllEnvServer();

    /**
    * 查询列表环境中服务地址
    * @param envServerQuery
    * @return
    */
    List<EnvServer> findEnvServerList(EnvServerQuery envServerQuery);

    /**
    * 按分页查询环境中服务地址
    * @param envServerQuery
    * @return
    */
    Pagination<EnvServer> findEnvServerPage(EnvServerQuery envServerQuery);

}