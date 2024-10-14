package io.tiklab.postin;


import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ServletComponentScan({"io.tiklab.postin"})
@ComponentScan({"io.tiklab.postin"})
public class PostInServerAutoConfiguration {

}
