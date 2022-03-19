package com.doublekit.apibox.apitest.http.httpcase.service;

import com.doublekit.apibox.apitest.http.httpinstance.model.TestInstance;
import com.doublekit.apibox.apitest.http.httpinstance.service.TestInstanceService;
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
public class TestInstanceServiceImplTest {

    private static Logger logger = LoggerFactory.getLogger(TestInstanceServiceImplTest.class);

    @Autowired
    TestInstanceService testInstanceService;

    static String id;

    @Test
    public void test01ForSaveTestInstance() {
        TestInstance testInstance = JMockit.mock(TestInstance.class);

        id = testInstanceService.createTestInstance(testInstance);

        assertNotNull(id);
    }

    @Test
    public void test02ForUpdateTestInstance(){
        TestInstance testInstance = JMockit.mock(TestInstance.class);
        testInstance.setId(id);

        testInstanceService.updateTestInstance(testInstance);
    }

    @Test
    public void test03ForFindTestInstance() {
        TestInstance testInstance = testInstanceService.findTestInstance(id);

        assertNotNull(testInstance);
    }

    @Test
    public void test04ForFindAllTestInstance() {
        List<TestInstance> testInstanceList = testInstanceService.findAllTestInstance();

        assertNotNull(testInstanceList);
    }

    @Test
    public void test05ForDeleteTestInstance(){
        testInstanceService.deleteTestInstance(id);
    }
}