package com.darthcloud.apibox.generator;

import com.darthcloud.apibox.generator.config.CodeGeneratorTestConfig;
import com.darthcloud.code.generator.CodeGeneratorTemplate;
import com.darthcloud.code.generator.config.CodeGeneratorConfig;
import com.darthcloud.apibox.workspace.entity.WorkspacePo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = CodeGeneratorTestConfig.class)
public class WorkspaceCodeGeneratorTest extends CodeGeneratorTemplate {

    @Autowired
    CodeGeneratorTestConfig codeGeneratorTestConfig;

    @Override
    protected CodeGeneratorConfig getCodeGeneratorConfig() {
        CodeGeneratorConfig config = new CodeGeneratorConfig();
        config.setApiMoudlePath(codeGeneratorTestConfig.getApiMoudlePath());
        config.setServerMoudlePath(codeGeneratorTestConfig.getServerMoudlePath());
        config.setSubsystem(codeGeneratorTestConfig.getSubsystem());
        config.setMoudle("workspace");
        config.setModel("Workspace");
        config.setModelPo(WorkspacePo.class);
        return config;
    }

    @Test
    @Override
    public void generateForAll() {
        super.generateForAll();
    }

}