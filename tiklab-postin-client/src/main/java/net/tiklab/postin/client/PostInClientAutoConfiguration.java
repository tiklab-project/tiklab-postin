package net.tiklab.postin.client;


import net.tiklab.postin.client.builder.PostInBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;

@Profile({"local","dev"})
@Configuration
@ComponentScan({"net.tiklab.postin"})
public class PostInClientAutoConfiguration {

    private static Logger logger = LoggerFactory.getLogger(PostInClientAutoConfiguration.class);

    @Value("${postin.scan.package:net.tiklab}")
    private String scanPackage;

    //@Bean
    @DependsOn({"joinQuery"})
    public PostInIniter postinIniter(){
        new PostInBuilder()
                .scan(scanPackage)
                .build();
        return new PostInIniter();
    }

    class PostInIniter {}

}
