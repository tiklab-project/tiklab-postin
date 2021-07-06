package com.doublekit.apibox.category.codegen;

import com.doublekit.apibox.node.entity.NodePo;
import com.doublekit.codegen.CodeGeneratorTemplate;
import com.doublekit.codegen.config.ProjectGeneratorConfig;
import com.doublekit.codegen.config.ModuleGeneratorConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ProjectGeneratorConfig.class)
public class NodeCodeGeneratorTest extends CodeGeneratorTemplate {

    @Autowired
    ProjectGeneratorConfig projectGeneratorConfig;

    @Override
    protected ModuleGeneratorConfig getModuleGeneratorConfig() {
        ModuleGeneratorConfig config = new ModuleGeneratorConfig();
        config.setProjectGeneratorConfig(projectGeneratorConfig);
        config.setModelPo(NodePo.class);
        config.setPkg("com.doublekit.apibox.node");
        config.setModel("Node");
        return config;
    }

    @Test
    @Override
    public void generateForAll() {
        super.generateForAll();
    }

}