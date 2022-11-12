package net.tiklab.postin;


import net.tiklab.dsm.annotation.SQL;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@SQL(modules = {
        "postin",
        "postineg",
        "postininit"
}, order = 100)
@ComponentScan({"net.tiklab.postin"})
@ServletComponentScan({"net.tiklab.postin"})
public class PostInServerAutoConfiguration {

}
