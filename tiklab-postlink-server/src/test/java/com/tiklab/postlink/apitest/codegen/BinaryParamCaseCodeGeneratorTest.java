package com.tiklab.postlink.apitest.codegen;

import com.tiklab.postlink.apitest.http.httpcase.entity.BinaryParamCaseEntity;
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
public class BinaryParamCaseCodeGeneratorTest extends CodeGeneratorTemplate {

    @Autowired
    ProjectGeneratorConfig projectGeneratorConfig;

    @Override
    protected CodeGeneratorConfig getCodeGeneratorConfig() {
        CodeGeneratorConfig config = new CodeGeneratorConfig();
        config.setProjectGeneratorConfig(projectGeneratorConfig);
        config.setEntity(BinaryParamCaseEntity.class);
        config.setPkg("com.tiklab.postlink.apitest");
        config.setModel("BinaryParamCase");
        return config;
    }

    @Test
    @Override
    public void generateForAll() {
        super.generateForAll();
    }

}