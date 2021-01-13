package com.darthcloud.apibox.generator;

import com.darthcloud.apibox.formparam.entity.FormParamPo;
import com.darthcloud.apibox.generator.config.CodeGeneratorTestConfig;
import com.darthcloud.apibox.jsonparam.entity.JsonParamPo;
import com.darthcloud.code.generator.CodeGeneratorTemplate;
import com.darthcloud.code.generator.config.CodeGeneratorConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = CodeGeneratorTestConfig.class)
public class FormParamCodeGeneratorTest extends CodeGeneratorTemplate {

    @Autowired
    CodeGeneratorTestConfig codeGeneratorTestConfig;

    @Override
    protected CodeGeneratorConfig getCodeGeneratorConfig() {
        CodeGeneratorConfig config = new CodeGeneratorConfig();
        config.setApiMoudlePath(codeGeneratorTestConfig.getApiMoudlePath());
        config.setServerMoudlePath(codeGeneratorTestConfig.getServerMoudlePath());
        config.setSubsystem(codeGeneratorTestConfig.getSubsystem());
        config.setMoudle("formparam");
        config.setModel("FormParam");
        config.setModelPo(FormParamPo.class);
        return config;
    }

    @Test
    @Override
    public void generateForAll() {
        super.generateForAll();
    }

}