package com.darthcloud.apibox.api.apimock.service;

import com.darthcloud.apibox.api.apimock.model.ResponseResultMock;
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
public class ResponseResultMockServiceImplTest {

    private static Logger logger = LoggerFactory.getLogger(ResponseResultMockServiceImplTest.class);

    @Autowired
    ResponseResultMockService responseResultMockService;

    static String id;

    @Test
    public void test01ForSaveResponseResultMock() {
        ResponseResultMock responseResultMock = JMockit.mock(ResponseResultMock.class);

        id = responseResultMockService.createResponseResultMock(responseResultMock);

        assertNotNull(id);
    }

    @Test
    public void test02ForUpdateResponseResultMock(){
        ResponseResultMock responseResultMock = JMockit.mock(ResponseResultMock.class);
        responseResultMock.setId(id);

        responseResultMockService.updateResponseResultMock(responseResultMock);
    }

    @Test
    public void test03ForFindResponseResultMock() {
        ResponseResultMock responseResultMock = responseResultMockService.findResponseResultMock(id);

        assertNotNull(responseResultMock);
    }

    @Test
    public void test04ForFindAllResponseResultMock() {
        List<ResponseResultMock> responseResultMockList = responseResultMockService.findAllResponseResultMock();

        assertNotNull(responseResultMockList);
    }

    @Test
    public void test05ForDeleteResponseResultMock(){
        responseResultMockService.deleteResponseResultMock(id);
    }
}