package com.tiklab.postlink.apidef.codegen;

import com.tiklab.postlink.apidef.http.entity.JsonResponseEntity;
import com.tiklab.codegen.CodeGeneratorTemplate;
import com.tiklab.codegen.config.ProjectGeneratorConfig;
import com.tiklab.codegen.config.CodeGeneratorConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ProjectGeneratorConfig.class)
public class JsonResponseCodeGeneratorTest extends CodeGeneratorTemplate {

    @Autowired
    ProjectGeneratorConfig projectGeneratorConfig;

    @Override
    protected CodeGeneratorConfig getCodeGeneratorConfig() {
        CodeGeneratorConfig config = new CodeGeneratorConfig();
        config.setProjectGeneratorConfig(projectGeneratorConfig);
        config.setEntity(JsonResponseEntity.class);
        config.setPkg("com.tiklab.postlink.responseresult");
        config.setModel("JsonResponse");
        return config;
    }

    @Test
    @Override
    public void generateForAll() {
        super.generateForAll();
    }

}