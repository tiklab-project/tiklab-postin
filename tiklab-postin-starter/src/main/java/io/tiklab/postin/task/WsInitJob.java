package io.tiklab.postin.task;

import io.tiklab.postin.agent.ws.PostInAgentWsConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class WsInitJob implements ApplicationRunner {

    private static final Logger logger = LoggerFactory.getLogger(WsInitJob.class);

    @Autowired
    private PostInAgentWsConfiguration postinAgentWsConfiguration;

    @Override
    public void run(ApplicationArguments args) {
        postinAgentWsConfiguration.webSocketClient();
    }
}
