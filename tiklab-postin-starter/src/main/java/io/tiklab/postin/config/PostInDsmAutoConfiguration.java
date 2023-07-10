package io.tiklab.postin.config;

import io.tiklab.dsm.config.model.DsmConfig;
import io.tiklab.dsm.support.DsmConfigBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PostInDsmAutoConfiguration {
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
                //TodoTaskDsm
                "todotask_1.0.0",


                //postin
                "postin_1.0.0",
                "postin-init_1.0.0",

                "postin-platform_1.0.0",

        });
//        dsmConfig.newVersion("1.0.1", new String[]{
//
//        });


        return dsmConfig;
    }
}
