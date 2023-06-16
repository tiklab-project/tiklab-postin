package io.tiklab.postin.support.environment.codegen;

import io.tiklab.postin.support.environment.entity.EnvironmentEntity;
import io.tiklab.codegen.CodeGeneratorTemplate;
import io.tiklab.codegen.config.CodeGeneratorConfig;
import io.tiklab.codegen.config.ProjectGeneratorConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ProjectGeneratorConfig.class)
public class EnvironmentCodeGeneratorTest extends CodeGeneratorTemplate {

    @Autowired
    ProjectGeneratorConfig projectGeneratorConfig;

    @Override
    protected CodeGeneratorConfig getCodeGeneratorConfig() {
        CodeGeneratorConfig config = new CodeGeneratorConfig();
        config.setProjectGeneratorConfig(projectGeneratorConfig);
        config.setEntity(EnvironmentEntity.class);
        config.setPkg("io.tiklab.postin.sysmgr");
        config.setModel("Environment");
        return config;
    }

    @Test
    @Override
    public void generateForSQL() {
        super.generateForSQL();
    }

    //@Test
    @Override
    public void generateForAll() {
        super.generateForAll();
    }

}