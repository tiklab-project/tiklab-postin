package io.tiklab.postin.sql;


import io.tiklab.dsm.support.DsmProcessTask;
import io.tiklab.postin.support.environment.model.EnvServer;
import io.tiklab.postin.support.environment.model.Environment;
import io.tiklab.postin.support.environment.service.EnvServerService;
import io.tiklab.postin.support.environment.service.EnvironmentService;
import io.tiklab.postin.workspace.model.Workspace;
import io.tiklab.postin.workspace.service.WorkspaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class InitPostIn121 implements DsmProcessTask {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    WorkspaceService workspaceService;

    @Autowired
    EnvironmentService environmentService;

    @Autowired
    EnvServerService envServerService;

    @Value("${external.url:null}")
    String baseUrl;

    @Override
    public void execute() {
        for (Workspace workspace : workspaceService.findAllWorkspace()) {
            String workspaceId = workspace.getId();
            Environment environment = new Environment();
            environment.setWorkspaceId(workspaceId);
            environment.setId(workspaceId);
            environment.setName("Mock环境");
            String envId = environmentService.createEnvironment(environment);
            EnvServer envServer = new EnvServer();
            envServer.setEnvId(envId);
            envServer.setName("默认服务");
            envServer.setUrl(baseUrl+"/mockx/"+workspaceId);
            envServerService.createEnvServer(envServer);
        }
    }

}