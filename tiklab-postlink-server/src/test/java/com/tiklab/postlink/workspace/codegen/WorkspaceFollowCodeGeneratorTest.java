package com.tiklab.postlink.workspace.codegen;

import com.tiklab.postlink.workspace.entity.WorkspaceFollowEntity;
import com.tiklab.codegen.CodeGeneratorTemplate;
import com.tiklab.codegen.config.CodeGeneratorConfig;
import com.tiklab.codegen.config.ProjectGeneratorConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ProjectGeneratorConfig.class)
public class WorkspaceFollowCodeGeneratorTest extends CodeGeneratorTemplate {

    @Autowired
    ProjectGeneratorConfig projectGeneratorConfig;

    @Override
    protected CodeGeneratorConfig getCodeGeneratorConfig() {
        CodeGeneratorConfig config = new CodeGeneratorConfig();
        config.setProjectGeneratorConfig(projectGeneratorConfig);
        config.setEntity(WorkspaceFollowEntity.class);
        config.setPkg("com.tiklab.postlink.workspace");
        config.setModel("WorkspaceFollow");
        return config;
    }

    @Test
    @Override
    public void generateForAll() {
        super.generateForAll();
    }

}