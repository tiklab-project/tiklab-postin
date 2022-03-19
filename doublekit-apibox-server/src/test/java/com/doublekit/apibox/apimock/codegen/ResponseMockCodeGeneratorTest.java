package com.doublekit.apibox.apimock.codegen;

import com.doublekit.apibox.apimock.http.entity.ResponseMockEntity;
import com.doublekit.codegen.CodeGeneratorTemplate;
import com.doublekit.codegen.config.CodeGeneratorConfig;
import com.doublekit.codegen.config.ProjectGeneratorConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ProjectGeneratorConfig.class)
public class ResponseMockCodeGeneratorTest extends CodeGeneratorTemplate {

    @Autowired
    ProjectGeneratorConfig projectGeneratorConfig;

    @Override
    protected CodeGeneratorConfig getCodeGeneratorConfig() {
        CodeGeneratorConfig config = new CodeGeneratorConfig();
        config.setProjectGeneratorConfig(projectGeneratorConfig);
        config.setEntity(ResponseMockEntity.class);
        config.setPkg("com.doublekit.apibox.apimock");
        config.setModel("ResponseMock");
        return config;
    }

    @Test
    @Override
    public void generateForAll() {
        super.generateForAll();
    }

}