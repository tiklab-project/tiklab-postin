package com.doublekit.apibox.apimock.service;

import com.doublekit.apibox.apimock.model.RequestBodyMock;
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
public class RequestBodyMockServiceImplTest {

    private static Logger logger = LoggerFactory.getLogger(RequestBodyMockServiceImplTest.class);

    @Autowired
    RequestBodyMockService requestBodyMockService;

    static String id;

    @Test
    public void test01ForSaveRequestBodyMock() {
        RequestBodyMock requestBodyMock = JMockit.mock(RequestBodyMock.class);

        id = requestBodyMockService.createRequestBodyMock(requestBodyMock);

        assertNotNull(id);
    }

    @Test
    public void test02ForUpdateRequestBodyMock(){
        RequestBodyMock requestBodyMock = JMockit.mock(RequestBodyMock.class);
        requestBodyMock.setId(id);

        requestBodyMockService.updateRequestBodyMock(requestBodyMock);
    }

    @Test
    public void test03ForFindRequestBodyMock() {
        RequestBodyMock requestBodyMock = requestBodyMockService.findRequestBodyMock(id);

        assertNotNull(requestBodyMock);
    }

    @Test
    public void test04ForFindAllRequestBodyMock() {
        List<RequestBodyMock> requestBodyMockList = requestBodyMockService.findAllRequestBodyMock();

        assertNotNull(requestBodyMockList);
    }

    @Test
    public void test05ForDeleteRequestBodyMock(){
        requestBodyMockService.deleteRequestBodyMock(id);
    }
}