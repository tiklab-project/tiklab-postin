package com.doublekit.apibox.apimock.service;

import com.doublekit.apibox.apimock.model.RawResponseMock;
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
public class RawResponseMockServiceImplTest {

    private static Logger logger = LoggerFactory.getLogger(RawResponseMockServiceImplTest.class);

    @Autowired
    RawResponseMockService rawResponseMockService;

    static String id;

    @Test
    public void test01ForSaveRawResponseMock() {
        RawResponseMock rawResponseMock = JMockit.mock(RawResponseMock.class);

        id = rawResponseMockService.createRawResponseMock(rawResponseMock);

        assertNotNull(id);
    }

    @Test
    public void test02ForUpdateRawResponseMock(){
        RawResponseMock rawResponseMock = JMockit.mock(RawResponseMock.class);
        rawResponseMock.setId(id);

        rawResponseMockService.updateRawResponseMock(rawResponseMock);
    }

    @Test
    public void test03ForFindRawResponseMock() {
        RawResponseMock rawResponseMock = rawResponseMockService.findRawResponseMock(id);

        assertNotNull(rawResponseMock);
    }

    @Test
    public void test04ForFindAllRawResponseMock() {
        List<RawResponseMock> rawResponseMockList = rawResponseMockService.findAllRawResponseMock();

        assertNotNull(rawResponseMockList);
    }

    @Test
    public void test05ForDeleteRawResponseMock(){
        rawResponseMockService.deleteRawResponseMock(id);
    }
}