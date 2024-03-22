package io.thoughtware.postin;


import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ServletComponentScan({"io.thoughtware.postin"})
@ComponentScan({"io.thoughtware.postin"})
public class PostInServerAutoConfiguration {

}
