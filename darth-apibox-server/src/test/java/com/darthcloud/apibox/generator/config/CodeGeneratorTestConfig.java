package com.darthcloud.apibox.generator.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application.properties")
public class CodeGeneratorTestConfig {

    @Value("${generator.api.moudle.path}")
    private String apiMoudlePath;

    @Value("${generator.server.moudle.path}")
    private String serverMoudlePath;

    @Value("${generator.subsystem}")
    private String subsystem;

    public String getApiMoudlePath() {
        return apiMoudlePath;
    }

    public void setApiMoudlePath(String apiMoudlePath) {
        this.apiMoudlePath = apiMoudlePath;
    }

    public String getServerMoudlePath() {
        return serverMoudlePath;
    }

    public void setServerMoudlePath(String serverMoudlePath) {
        this.serverMoudlePath = serverMoudlePath;
    }

    public String getSubsystem() {
        return subsystem;
    }

    public void setSubsystem(String subsystem) {
        this.subsystem = subsystem;
    }
}
