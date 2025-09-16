package io.tiklab.postin.sql;


import io.tiklab.dsm.support.DsmProcessTask;
import io.tiklab.postin.support.environment.model.EnvServer;
import io.tiklab.postin.support.environment.model.Environment;
import io.tiklab.postin.support.environment.service.EnvServerService;
import io.tiklab.postin.support.environment.service.EnvironmentService;
import io.tiklab.postin.workspace.model.Workspace;
import io.tiklab.postin.workspace.service.WorkspaceService;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class InitPostIn122 implements DsmProcessTask {

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
        batchUpdateCaseKey();
    }

    public void batchUpdateCaseKey() {
        String sql = "UPDATE autotest_testcase SET case_key = ? WHERE id = ?";

        // 查出所有 id
        List<String> ids = jdbcTemplate.queryForList("SELECT id FROM autotest_testcase", String.class);

        jdbcTemplate.batchUpdate(sql, ids, ids.size(),
                (ps, id) -> {
                    ps.setString(1, RandomStringUtils.random(6, true, true).toUpperCase()); // 6位 大写字母+数字
                    ps.setString(2, id);
                }
        );
    }


}