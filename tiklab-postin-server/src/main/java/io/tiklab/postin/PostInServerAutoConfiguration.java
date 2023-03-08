package io.tiklab.postin;


import io.tiklab.dsm.model.SQL;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({"io.tiklab.postin"})
public class PostInServerAutoConfiguration {

    @Bean
    SQL initSQL(){
        return new SQL(new String[]{
                "postin",
                "postineg",
                "postininit"
        }, 20);
    }


}
