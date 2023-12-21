package io.thoughtware.postin;


import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({"io.thoughtware.postin"})
@ServletComponentScan({"io.thoughtware.postin"})
public class PostInServerAutoConfiguration {

}
