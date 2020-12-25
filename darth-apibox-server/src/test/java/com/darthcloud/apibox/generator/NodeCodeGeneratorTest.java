package com.darthcloud.apibox.generator;

import com.darthcloud.apibox.category.entity.CategoryPo;
import com.darthcloud.apibox.generator.config.CodeGeneratorTestConfig;
import com.darthcloud.apibox.node.entity.NodePo;
import com.darthcloud.code.generator.CodeGeneratorTemplate;
import com.darthcloud.code.generator.config.CodeGeneratorConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = CodeGeneratorTestConfig.class)
public class NodeCodeGeneratorTest extends CodeGeneratorTemplate {

    @Autowired
    CodeGeneratorTestConfig codeGeneratorTestConfig;

    @Override
    protected CodeGeneratorConfig getCodeGeneratorConfig() {
        CodeGeneratorConfig config = new CodeGeneratorConfig();
        config.setApiMoudlePath(codeGeneratorTestConfig.getApiMoudlePath());
        config.setServerMoudlePath(codeGeneratorTestConfig.getServerMoudlePath());
        config.setSubsystem(codeGeneratorTestConfig.getSubsystem());
        config.setMoudle("node");
        config.setModel("Node");
        config.setModelPo(NodePo.class);
        return config;
    }

    @Test
    @Override
    public void generateForAll() {
        super.generateForAll();
    }

}