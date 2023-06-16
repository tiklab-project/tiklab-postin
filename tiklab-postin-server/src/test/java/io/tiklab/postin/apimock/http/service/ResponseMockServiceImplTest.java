package io.tiklab.postin.apimock.http.service;

import io.tiklab.postin.api.http.mock.model.ResponseMock;
import io.tiklab.postin.api.http.mock.service.ResponseMockService;
import io.tiklab.postin.client.mock.JMockit;
import io.tiklab.postin.config.TestConfig;
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
public class ResponseMockServiceImplTest {

    private static Logger logger = LoggerFactory.getLogger(ResponseMockServiceImplTest.class);

    @Autowired
    ResponseMockService responseMockService;

    static String id;

    @Test
    public void test01ForSaveResponseMock() {
        ResponseMock responseMock = JMockit.mock(ResponseMock.class);

        id = responseMockService.createResponseMock(responseMock);

        assertNotNull(id);
    }

    @Test
    public void test02ForUpdateResponseMock(){
        ResponseMock responseMock = JMockit.mock(ResponseMock.class);
        responseMock.setId(id);

        responseMockService.updateResponseMock(responseMock);
    }

    @Test
    public void test03ForFindResponseMock() {
        ResponseMock responseMock = responseMockService.findResponseMock(id);

        assertNotNull(responseMock);
    }

    @Test
    public void test04ForFindAllResponseMock() {
        List<ResponseMock> responseMockList = responseMockService.findAllResponseMock();

        assertNotNull(responseMockList);
    }

    @Test
    public void test05ForDeleteResponseMock(){
        responseMockService.deleteResponseMock(id);
    }
}