package net.tiklab.postin.apimock.codegen;

import net.tiklab.postin.api.http.mock.entity.JsonParamMockEntity;
import net.tiklab.codegen.CodeGeneratorTemplate;
import net.tiklab.codegen.config.CodeGeneratorConfig;
import net.tiklab.codegen.config.ProjectGeneratorConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ProjectGeneratorConfig.class)
public class JsonParamMockCodeGeneratorTest extends CodeGeneratorTemplate {

    @Autowired
    ProjectGeneratorConfig projectGeneratorConfig;

    @Override
    protected CodeGeneratorConfig getCodeGeneratorConfig() {
        CodeGeneratorConfig config = new CodeGeneratorConfig();
        config.setProjectGeneratorConfig(projectGeneratorConfig);
        config.setEntity(JsonParamMockEntity.class);
        config.setPkg("net.tiklab.postin.apimock");
        config.setModel("JsonParamMock");
        return config;
    }

    @Test
    @Override
    public void generateForAll() {
        super.generateForAll();
    }

}