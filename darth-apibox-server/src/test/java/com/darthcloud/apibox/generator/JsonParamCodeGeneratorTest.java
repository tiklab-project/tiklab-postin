package com.darthcloud.apibox.generator;

import com.darthcloud.apibox.apidef.jsonparam.entity.JsonParamPo;
import com.darthcloud.code.generator.CodeGeneratorTemplate;
import com.darthcloud.code.generator.config.GeneratorConfigEnv;
import com.darthcloud.code.generator.config.ModuleGeneratorConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = GeneratorConfigEnv.class)
public class JsonParamCodeGeneratorTest extends CodeGeneratorTemplate {

    @Autowired
    GeneratorConfigEnv generatorConfigEnv;

    @Override
    protected ModuleGeneratorConfig getModuleGeneratorConfig() {
        ModuleGeneratorConfig config = new ModuleGeneratorConfig();
        config.setGeneratorConfigEnv(generatorConfigEnv);
        config.setMoudle("requestparam");
        config.setModel("JsonParam");
        config.setModelPo(JsonParamPo.class);
        return config;
    }

    @Test
    @Override
    public void generateForAll() {
        super.generateForAll();
    }

}