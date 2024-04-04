package io.thoughtware.postin.config;

import io.thoughtware.dsm.config.model.DsmConfig;
import io.thoughtware.dsm.support.DsmConfigBuilder;
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
                "oplog_1.0.1",
                "message_1.0.1",
                "apply-auth_1.0.1",
                "privilege_1.0.1",

                "postin_1.0.1",
                "postin-platform_1.0.1",

        });
        dsmConfig.newVersion("1.0.2", new String[]{
                "postin_1.0.2",
                "postin-platform_1.0.2",

                "oplog_1.0.2",
                "message_1.0.2",
        });
        dsmConfig.newVersion("1.0.3", new String[]{
                "postin_1.0.3",
                "postin-platform_1.0.3",

                "message_1.0.3",
        });
        dsmConfig.newVersion("1.0.4", new String[]{
                "message_1.0.4",
                "oplog_1.0.4"

        });
        dsmConfig.newVersion("1.0.5", new String[]{
                "message_1.0.5",
        });
        dsmConfig.newVersion("1.0.6", new String[]{
                "message_1.0.6",
        });
        dsmConfig.newVersion("1.0.7", new String[]{
                "message_1.0.7",
        });
        return dsmConfig;
    }
}
