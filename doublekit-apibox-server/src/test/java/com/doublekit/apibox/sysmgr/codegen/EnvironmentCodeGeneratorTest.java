package com.doublekit.apibox.sysmgr.codegen;

import com.doublekit.apibox.sysmgr.entity.EnvironmentPo;
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
public class EnvironmentCodeGeneratorTest extends CodeGeneratorTemplate {

    @Autowired
    ProjectGeneratorConfig projectGeneratorConfig;

    @Override
    protected ModuleGeneratorConfig getModuleGeneratorConfig() {
        ModuleGeneratorConfig config = new ModuleGeneratorConfig();
        config.setProjectGeneratorConfig(projectGeneratorConfig);
        config.setPkg("com.doublekit.apibox.sysmgr");
        config.setModel("Environment");
        config.setModelPo(EnvironmentPo.class);
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