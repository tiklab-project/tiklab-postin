package io.tiklab.postin.config;

import io.tiklab.dsm.model.DsmConfig;
import io.tiklab.dsm.model.DsmVersion;
import io.tiklab.dsm.support.DsmVersionBuilder;
import io.tiklab.postin.sql.InitAuthority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class PostInDsmAutoConfiguration {

    @Autowired
    InitAuthority initAuthority;

    @Bean
    DsmConfig dsmConfig(){
        DsmConfig dsmConfig = new DsmConfig();

        dsmConfig.setVersionList(versionList());
        return dsmConfig;
    }


    /**
     * 初始化Dsm版本列表
     * @return
     */
    List<DsmVersion> versionList(){
        List<DsmVersion> versionList = new ArrayList<>();

        //1.0.0
        DsmVersion dsmVersion = DsmVersionBuilder.instance()
                .version("1.0.0")
                .db(new String[]{
                        "user_1.0.0",
                        "privilege_1.0.0",
                        "app-authorization_1.0.0",
                        "message_1.0.0",
                        "oplog_1.0.0",
                        "openapi_1.0.0",

                        //postin
                        "postin_1.0.0",
                        "postin-init_1.0.0",

                        "postin-platform_1.0.0",
                })
                //.task(processTask) 自定义处理逻辑
                .get();
        versionList.add(dsmVersion);

        //1.0.1
        dsmVersion = DsmVersionBuilder.instance()
                .version("1.0.1")
                .db(new String[]{
                        "postin_1.0.1",
                        "postin-platform_1.0.1",
                })
                .get();
        versionList.add(dsmVersion);

        //1.0.2
        dsmVersion = DsmVersionBuilder.instance()
                .version("1.0.2")
                .db(new String[]{
                        "postin_1.0.2",
                        "postin-platform_1.0.2",
                })
                .get();
        versionList.add(dsmVersion);

        //1.0.3
        dsmVersion = DsmVersionBuilder.instance()
                .version("1.0.3")
                .db(new String[]{
                        "postin_1.0.3",
                        "postin-platform_1.0.3",

                })
                .get();
        versionList.add(dsmVersion);

        //1.0.4
        dsmVersion = DsmVersionBuilder.instance()
                .version("1.0.4")
                .db(new String[]{
                        "postin-platform_1.0.4",
                        "postin_1.0.4",
                })
                .get();
        versionList.add(dsmVersion);

        //1.0.5
        dsmVersion = DsmVersionBuilder.instance()
                .version("1.0.5")
                .db(new String[]{
                        "postin_1.0.5",
                })
                .get();
        versionList.add(dsmVersion);

        //1.0.6
        dsmVersion = DsmVersionBuilder.instance()
                .version("1.0.6")
                .db(new String[]{
                        "postin_1.0.6",
                })
                .get();
        versionList.add(dsmVersion);

        //1.0.7
        dsmVersion = DsmVersionBuilder.instance()
                .version("1.0.7")
                .db(new String[]{
                        "postin_1.0.7",
                })
                .get();
        versionList.add(dsmVersion);

        //1.0.8
        dsmVersion = DsmVersionBuilder.instance()
                .version("1.0.8")
                .db(new String[]{
                        "postin_1.0.8",
                })
                .get();
        versionList.add(dsmVersion);

        //1.0.9
        dsmVersion = DsmVersionBuilder.instance()
                .version("1.0.9")
                .db(new String[]{
                        "postin_1.0.9",

                        "postin-init_1.0.9"
                })
                .get();
        versionList.add(dsmVersion);

        //1.1.0
        dsmVersion = DsmVersionBuilder.instance()
                .version("1.1.0")
                .db(new String[]{
                        "postin_1.1.0",
                        "postin-init_1.1.0",

                })
                .get();
        versionList.add(dsmVersion);

        //1.1.1
        dsmVersion = DsmVersionBuilder.instance()
                .version("1.1.1")
                .db(new String[]{
                        "postin_1.1.1",
                })
                .get();
        versionList.add(dsmVersion);

        //1.1.2
        dsmVersion = DsmVersionBuilder.instance()
                .version("1.1.2")
                .db(new String[]{
                        "postin_1.1.2"
                })
                .get();
        versionList.add(dsmVersion);

        //1.1.3
        dsmVersion = DsmVersionBuilder.instance()
                .version("1.1.3")
                .db(new String[]{
                        "postin_1.1.3"
                })
                .get();
        versionList.add(dsmVersion);


        DsmVersion message_109 = DsmVersionBuilder.instance()
                .version("message_1.0.9")
                .db(new String[]{
                        "message_1.0.9",
                }).get();
        versionList.add(message_109);

        dsmVersion = DsmVersionBuilder.instance()
                .version("1.1.4")
                .db(new String[]{
                        "postin_1.1.4"
                })
                .get();
        versionList.add(dsmVersion);
        dsmVersion = DsmVersionBuilder.instance()
                .version("1.1.5")
                .db(new String[]{
                        "postin_1.1.5"
                })
                .get();
        versionList.add(dsmVersion);

        dsmVersion = DsmVersionBuilder.instance()
                .version("1.1.6")
                .db(new String[]{
                        "postin_1.1.6"
                })
                .get();
        versionList.add(dsmVersion);

        dsmVersion = DsmVersionBuilder.instance()
                .version("1.1.7")
                .db(new String[]{
                        "postin_1.1.7"
                })
                .get();
        versionList.add(dsmVersion);

        dsmVersion = DsmVersionBuilder.instance()
                .version("1.1.8")
                .db(new String[]{
                        "postin_1.1.8"
                })
                .get();
        versionList.add(dsmVersion);

        DsmVersion privilege110 = DsmVersionBuilder.instance()
                .version("privilegeGroup110")
                .db(new String[]{
                        "privilege-gorup_1.0.0",
                }).get();
        versionList.add(privilege110);

        DsmVersion privilegePlatform110 = DsmVersionBuilder.instance()
                .version("privilegePlatform110")
                .db(new String[]{
                        "privilege-platform_1.0.0",
                }).get();
        versionList.add(privilegePlatform110);

        DsmVersion InitAuthority = DsmVersionBuilder.instance()
                .version("InitAuthority110")
                .task(initAuthority)
                .get();
        versionList.add(InitAuthority);

        dsmVersion = DsmVersionBuilder.instance()
                .version("1.1.9")
                .db(new String[]{
                        "postin_1.1.9"
                })
                .get();
        versionList.add(dsmVersion);

        dsmVersion = DsmVersionBuilder.instance()
                .version("1.2.0")
                .db(new String[]{
                        "postin_1.2.0"
                })
                .get();
        versionList.add(dsmVersion);



        return versionList;
    }
}
