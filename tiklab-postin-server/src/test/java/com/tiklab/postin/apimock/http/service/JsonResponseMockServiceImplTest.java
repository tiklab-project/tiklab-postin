package com.tiklab.postin.apimock.http.service;

import com.tiklab.postin.apimock.http.model.JsonResponseMock;
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
public class JsonResponseMockServiceImplTest {

    private static Logger logger = LoggerFactory.getLogger(JsonResponseMockServiceImplTest.class);

    @Autowired
    JsonResponseMockService jsonResponseMockService;

    static String id;

    @Test
    public void test01ForSaveJsonResponseMock() {
        JsonResponseMock jsonResponseMock = JMockit.mock(JsonResponseMock.class);

        id = jsonResponseMockService.createJsonResponseMock(jsonResponseMock);

        assertNotNull(id);
    }

    @Test
    public void test02ForUpdateJsonResponseMock(){
        JsonResponseMock jsonResponseMock = JMockit.mock(JsonResponseMock.class);
        jsonResponseMock.setId(id);

        jsonResponseMockService.updateJsonResponseMock(jsonResponseMock);
    }

    @Test
    public void test03ForFindJsonResponseMock() {
        JsonResponseMock jsonResponseMock = jsonResponseMockService.findJsonResponseMock(id);

        assertNotNull(jsonResponseMock);
    }

    @Test
    public void test04ForFindAllJsonResponseMock() {
        List<JsonResponseMock> jsonResponseMockList = jsonResponseMockService.findAllJsonResponseMock();

        assertNotNull(jsonResponseMockList);
    }

    @Test
    public void test05ForDeleteJsonResponseMock(){
        jsonResponseMockService.deleteJsonResponseMock(id);
    }
}