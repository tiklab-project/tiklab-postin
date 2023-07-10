package io.tiklab.postin;


import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({"io.tiklab.postin"})
@ServletComponentScan({"io.tiklab.postin"})
public class PostInServerAutoConfiguration {

}
