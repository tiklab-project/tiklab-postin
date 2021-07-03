package com.doublekit.apibox.apimock.codegen;

import com.doublekit.apibox.apimock.entity.RequestBodyMockPo;
import com.doublekit.codegen.CodeGeneratorTemplate;
import com.doublekit.codegen.config.ModuleGeneratorConfig;
import com.doublekit.codegen.config.ProjectGeneratorConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ProjectGeneratorConfig.class)
public class RequestBodyMockCodeGeneratorTest extends CodeGeneratorTemplate {

    @Autowired
    ProjectGeneratorConfig projectGeneratorConfig;

    @Override
    protected ModuleGeneratorConfig getModuleGeneratorConfig() {
        ModuleGeneratorConfig config = new ModuleGeneratorConfig();
        config.setProjectGeneratorConfig(projectGeneratorConfig);
        config.setPkg("com.doublekit.apibox.apimock");
        config.setModel("RequestBodyMock");
        config.setModelPo(RequestBodyMockPo.class);
        return config;
    }

    @Test
    @Override
    public void generateForAll() {
        super.generateForAll();
    }

}