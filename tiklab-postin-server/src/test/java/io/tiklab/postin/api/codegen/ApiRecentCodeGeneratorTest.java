package io.tiklab.postin.api.codegen;

import io.tiklab.codegen.CodeGeneratorTemplate;
import io.tiklab.codegen.config.CodeGeneratorConfig;
import io.tiklab.codegen.config.ProjectGeneratorConfig;
import io.tiklab.postin.api.apix.entity.ApiRecentEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ProjectGeneratorConfig.class)
public class ApiRecentCodeGeneratorTest extends CodeGeneratorTemplate {

    @Autowired
    ProjectGeneratorConfig projectGeneratorConfig;

    @Override
    protected CodeGeneratorConfig getCodeGeneratorConfig() {
        CodeGeneratorConfig config = new CodeGeneratorConfig();
        config.setProjectGeneratorConfig(projectGeneratorConfig);
        config.setEntity(ApiRecentEntity.class);
        config.setPkg("io.tiklab.postin.api.apix");
        config.setModel("ApiRecent");
        return config;
    }

    @Test
    @Override
    public void generateForAll() {
        super.generateForAll();
    }

}