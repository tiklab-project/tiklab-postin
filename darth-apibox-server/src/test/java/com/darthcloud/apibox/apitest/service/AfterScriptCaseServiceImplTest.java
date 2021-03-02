package com.darthcloud.apibox.apitest.service;

import com.darthcloud.apibox.apitest.model.AfterScriptCase;
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
public class AfterScriptCaseServiceImplTest {

    private static Logger logger = LoggerFactory.getLogger(AfterScriptCaseServiceImplTest.class);

    @Autowired
    AfterScriptCaseService afterScriptCaseService;

    static String id;

    @Test
    public void test01ForSaveAfterScriptCase() {
        AfterScriptCase afterScriptCase = JMockit.mock(AfterScriptCase.class);

        id = afterScriptCaseService.createAfterScriptCase(afterScriptCase);

        assertNotNull(id);
    }

    @Test
    public void test02ForUpdateAfterScriptCase(){
        AfterScriptCase afterScriptCase = JMockit.mock(AfterScriptCase.class);
        afterScriptCase.setId(id);

        afterScriptCaseService.updateAfterScriptCase(afterScriptCase);
    }

    @Test
    public void test03ForFindAfterScriptCase() {
        AfterScriptCase afterScriptCase = afterScriptCaseService.findAfterScriptCase(id);

        assertNotNull(afterScriptCase);
    }

    @Test
    public void test04ForFindAllAfterScriptCase() {
        List<AfterScriptCase> afterScriptCaseList = afterScriptCaseService.findAllAfterScriptCase();

        assertNotNull(afterScriptCaseList);
    }

    @Test
    public void test05ForDeleteAfterScriptCase(){
        afterScriptCaseService.deleteAfterScriptCase(id);
    }
}