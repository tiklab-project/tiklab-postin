package com.darthcloud.apibox.api.apidef.service;

import com.darthcloud.apibox.api.apidef.model.AfterScript;
import com.darthcloud.apibox.client.mock.JMockit;
import com.darthcloud.apibox.config.TestConfig;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {TestConfig.class})
@Transactional
@Rollback(false)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AfterScriptServiceImplTest {

    private static Logger logger = LoggerFactory.getLogger(AfterScriptServiceImplTest.class);

    @Autowired
    AfterScriptService afterScriptService;

    static String id;

    @Test
    public void test01ForSaveAfterScript() {
        AfterScript afterScript = JMockit.mock(AfterScript.class);

        id = afterScriptService.createAfterScript(afterScript);

        assertNotNull(id);
    }

    @Test
    public void test02ForUpdateAfterScript(){
        AfterScript afterScript = JMockit.mock(AfterScript.class);
        afterScript.setId(id);

        afterScriptService.updateAfterScript(afterScript);
    }

    @Test
    public void test03ForFindAfterScript() {
        AfterScript afterScript = afterScriptService.findAfterScript(id);

        assertNotNull(afterScript);
    }

    @Test
    public void test04ForFindAllAfterScript() {
        List<AfterScript> afterScriptList = afterScriptService.findAllAfterScript();

        assertNotNull(afterScriptList);
    }

    @Test
    public void test05ForDeleteAfterScript(){
        afterScriptService.deleteAfterScript(id);
    }
}