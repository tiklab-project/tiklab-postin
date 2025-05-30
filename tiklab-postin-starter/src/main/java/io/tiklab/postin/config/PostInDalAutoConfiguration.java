package io.tiklab.postin.config;

import io.tiklab.dsm.model.DsmConfig;
import io.tiklab.dsm.support.DsmConfigBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PostInDalAutoConfiguration {
    /**
     * 初始化dsm
     */
    @Bean
    DsmConfig initDsmConfig() {
        DsmConfig dsmConfig = DsmConfigBuilder.instance();
        //1.0.0
        dsmConfig.newVersion("1.0.0", new String[]{
                "openapi_1.0.0",
                //PrivilegeDsm
                "privilege_1.0.0",
                //UserDsm
                "user_1.0.0",
                "userCe_1.0.0",
                //IntegrationDsm
                "tool_1.0.0",
                //LicenceDsm
                "app-authorization_1.0.0",
                //MessageDsm
                "message_1.0.0",
                //SecurityDsm
                "oplog_1.0.0",

                "backups_1.0.0",

                //postin
                "postin_1.0.0",
                "postin-init_1.0.0",

                "postin-platform_1.0.0",

        });
        dsmConfig.newVersion("1.0.1", new String[]{
                "privilege_1.0.1",
                "oplog_1.0.1",
                "message_1.0.1",
                "apply-auth_1.0.1",

                "postin_1.0.1",
                "postin-platform_1.0.1",
        });
        dsmConfig.newVersion("1.0.2", new String[]{
                "postin_1.0.2",
                "postin-platform_1.0.2",

                "apply-auth_1.0.2",
                "privilege_1.0.2",
                "oplog_1.0.2",
                "message_1.0.2",
        });
        dsmConfig.newVersion("1.0.3", new String[]{
                "postin_1.0.3",
                "postin-platform_1.0.3",

                "oplog_1.0.3",
                "apply-auth_1.0.3",
                "message_1.0.3",
                "privilege_1.0.3"
        });
        dsmConfig.newVersion("1.0.4", new String[]{
                "privilege_1.0.4" ,
                "message_1.0.4",
                "oplog_1.0.4",
                "apply-auth_1.0.4",

                "postin-platform_1.0.4",
                "postin_1.0.4",


        });
        dsmConfig.newVersion("1.0.5", new String[]{
                "postin_1.0.5",

                "message_1.0.5",
        });
        dsmConfig.newVersion("1.0.6", new String[]{
                "postin_1.0.6",

                "message_1.0.6",
        });
        dsmConfig.newVersion("1.0.7", new String[]{
                "postin_1.0.7",

                "message_1.0.7",
        });
        dsmConfig.newVersion("1.0.8", new String[]{
                "postin_1.0.8",

                "message_1.0.8",
        });
        dsmConfig.newVersion("1.0.9", new String[]{
                "postin_1.0.9",

                "postin-init_1.0.9"
        });
        dsmConfig.newVersion("1.1.0", new String[]{
                "postin_1.1.0",
                "postin-init_1.1.0",

                "user_1.1.0"
        });
        dsmConfig.newVersion("1.1.1", new String[]{
                "postin_1.1.1",
                "user_1.1.1",
        });
        dsmConfig.newVersion("1.1.2", new String[]{
                "postin_1.1.2"
        });
        dsmConfig.newVersion("1.1.3", new String[]{
                "postin_1.1.3"
        });
        return dsmConfig;
    }
}
