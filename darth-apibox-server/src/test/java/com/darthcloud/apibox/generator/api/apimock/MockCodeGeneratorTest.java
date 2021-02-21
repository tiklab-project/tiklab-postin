package com.darthcloud.apibox.generator.api.apimock;

import com.darthcloud.apibox.apimock.entity.MockPo;
import com.darthcloud.code.generator.CodeGeneratorTemplate;
import com.darthcloud.code.generator.config.ProjectGeneratorConfig;
import com.darthcloud.code.generator.config.ModuleGeneratorConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ProjectGeneratorConfig.class)
public class MockCodeGeneratorTest extends CodeGeneratorTemplate {

    @Autowired
    ProjectGeneratorConfig projectGeneratorConfig;

    @Override
    protected ModuleGeneratorConfig getModuleGeneratorConfig() {
        ModuleGeneratorConfig config = new ModuleGeneratorConfig();
        config.setProjectGeneratorConfig(projectGeneratorConfig);
        config.setPkg("com.darthcloud.apibox.mock");
        config.setModel("Mock");
        config.setModelPo(MockPo.class);
        return config;
    }

    @Test
    @Override
    public void generateForAll() {
        super.generateForAll();
    }

}