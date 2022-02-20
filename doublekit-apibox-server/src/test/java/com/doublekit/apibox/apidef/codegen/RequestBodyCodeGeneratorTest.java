package com.doublekit.apibox.apidef.codegen;

import com.doublekit.apibox.apidef.entity.RequestBodyEntity;
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
public class RequestBodyCodeGeneratorTest extends CodeGeneratorTemplate {

    @Autowired
    ProjectGeneratorConfig projectGeneratorConfig;

    @Override
    protected CodeGeneratorConfig getCodeGeneratorConfig() {
        CodeGeneratorConfig config = new CodeGeneratorConfig();
        config.setProjectGeneratorConfig(projectGeneratorConfig);
        config.setEntity(RequestBodyEntity.class);
        config.setPkg("com.doublekit.apibox.apidef");
        config.setModel("RequestBody");
        return config;
    }

    @Test
    @Override
    public void generateForAll() {
        super.generateForAll();
    }

}