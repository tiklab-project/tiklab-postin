package com.darthcloud.apibox.apimock.mock.service;

import com.darthcloud.apibox.apimock.mock.model.Mock;
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
public class MockServiceImplTest {

    private static Logger logger = LoggerFactory.getLogger(MockServiceImplTest.class);

    @Autowired
    MockService mockService;

    static String id;

    @Test
    public void test01ForSaveMock() {
        Mock mock = JMockit.mock(Mock.class);

        id = mockService.createMock(mock);

        assertNotNull(id);
    }

    @Test
    public void test02ForUpdateMock(){
        Mock mock = JMockit.mock(Mock.class);
        mock.setId(id);

        mockService.updateMock(mock);
    }

    @Test
    public void test03ForFindMock() {
        Mock mock = mockService.findMock(id);

        assertNotNull(mock);
    }

    @Test
    public void test04ForFindAllMock() {
        List<Mock> mockList = mockService.findAllMock();

        assertNotNull(mockList);
    }

    @Test
    public void test05ForDeleteMock(){
        mockService.deleteMock(id);
    }
}