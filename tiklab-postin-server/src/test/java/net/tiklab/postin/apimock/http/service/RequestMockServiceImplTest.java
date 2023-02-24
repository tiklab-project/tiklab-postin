package net.tiklab.postin.apimock.http.service;

import net.tiklab.postin.apidef.http.mock.model.RequestMock;
import net.tiklab.postin.apidef.http.mock.service.RequestMockService;
import net.tiklab.postin.client.mock.JMockit;
import net.tiklab.postin.config.TestConfig;
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
public class RequestMockServiceImplTest {

    private static Logger logger = LoggerFactory.getLogger(RequestMockServiceImplTest.class);

    @Autowired
    RequestMockService requestMockService;

    static String id;

    @Test
    public void test01ForSaveRequestMock() {
        RequestMock requestMock = JMockit.mock(RequestMock.class);

        id = requestMockService.createRequestMock(requestMock);

        assertNotNull(id);
    }

    @Test
    public void test02ForUpdateRequestMock(){
        RequestMock requestMock = JMockit.mock(RequestMock.class);
        requestMock.setId(id);

        requestMockService.updateRequestMock(requestMock);
    }

    @Test
    public void test03ForFindRequestMock() {
        RequestMock requestMock = requestMockService.findRequestMock(id);

        assertNotNull(requestMock);
    }

    @Test
    public void test04ForFindAllRequestMock() {
        List<RequestMock> requestMockList = requestMockService.findAllRequestMock();

        assertNotNull(requestMockList);
    }

    @Test
    public void test05ForDeleteRequestMock(){
        requestMockService.deleteRequestMock(id);
    }
}