package com.tiklab.postin.apidef.http.service;

import com.tiklab.postin.apidef.http.model.PreScript;
import com.tiklab.postin.client.mock.JMockit;
import com.tiklab.postin.config.TestConfig;
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
public class PreScriptServiceImplTest {

    private static Logger logger = LoggerFactory.getLogger(PreScriptServiceImplTest.class);

    @Autowired
    PreScriptService preScriptService;

    static String id;

    @Test
    public void test01ForSavePreScript() {
        PreScript preScript = JMockit.mock(PreScript.class);

        id = preScriptService.createPreScript(preScript);

        assertNotNull(id);
    }

    @Test
    public void test02ForUpdatePreScript(){
        PreScript preScript = JMockit.mock(PreScript.class);
        preScript.setId(id);

        preScriptService.updatePreScript(preScript);
    }

    @Test
    public void test03ForFindPreScript() {
        PreScript preScript = preScriptService.findPreScript(id);

        assertNotNull(preScript);
    }

    @Test
    public void test04ForFindAllPreScript() {
        List<PreScript> preScriptList = preScriptService.findAllPreScript();

        assertNotNull(preScriptList);
    }

    @Test
    public void test05ForDeletePreScript(){
        preScriptService.deletePreScript(id);
    }
}