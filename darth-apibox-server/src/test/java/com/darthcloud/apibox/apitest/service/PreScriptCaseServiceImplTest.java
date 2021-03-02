package com.darthcloud.apibox.apitest.service;

import com.darthcloud.apibox.apitest.model.PreScriptCase;
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
public class PreScriptCaseServiceImplTest {

    private static Logger logger = LoggerFactory.getLogger(PreScriptCaseServiceImplTest.class);

    @Autowired
    PreScriptCaseService preScriptCaseService;

    static String id;

    @Test
    public void test01ForSavePreScriptCase() {
        PreScriptCase preScriptCase = JMockit.mock(PreScriptCase.class);

        id = preScriptCaseService.createPreScriptCase(preScriptCase);

        assertNotNull(id);
    }

    @Test
    public void test02ForUpdatePreScriptCase(){
        PreScriptCase preScriptCase = JMockit.mock(PreScriptCase.class);
        preScriptCase.setId(id);

        preScriptCaseService.updatePreScriptCase(preScriptCase);
    }

    @Test
    public void test03ForFindPreScriptCase() {
        PreScriptCase preScriptCase = preScriptCaseService.findPreScriptCase(id);

        assertNotNull(preScriptCase);
    }

    @Test
    public void test04ForFindAllPreScriptCase() {
        List<PreScriptCase> preScriptCaseList = preScriptCaseService.findAllPreScriptCase();

        assertNotNull(preScriptCaseList);
    }

    @Test
    public void test05ForDeletePreScriptCase(){
        preScriptCaseService.deletePreScriptCase(id);
    }
}