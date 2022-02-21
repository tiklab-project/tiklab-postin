package com.doublekit.apibox.apitest.apicase.service;

import com.doublekit.apibox.apitest.apicase.model.Testcase;
import com.doublekit.apibox.client.mock.JMockit;
import com.doublekit.apibox.config.TestConfig;
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
public class TestcaseServiceImplTest {

    private static Logger logger = LoggerFactory.getLogger(TestcaseServiceImplTest.class);

    @Autowired
    TestcaseService testcaseService;

    static String id;

    @Test
    public void test01ForSaveTestcase() {
        Testcase testcase = JMockit.mock(Testcase.class);

        id = testcaseService.createTestcase(testcase);

        assertNotNull(id);
    }

    @Test
    public void test02ForUpdateTestcase(){
        Testcase testcase = JMockit.mock(Testcase.class);
        testcase.setId(id);

        testcaseService.updateTestcase(testcase);
    }

    @Test
    public void test03ForFindTestcase() {
        Testcase testcase = testcaseService.findTestcase(id);

        assertNotNull(testcase);
    }

    @Test
    public void test04ForFindAllTestcase() {
        List<Testcase> testcaseList = testcaseService.findAllTestcase();

        assertNotNull(testcaseList);
    }

    @Test
    public void test05ForDeleteTestcase(){
        testcaseService.deleteTestcase(id);
    }
}