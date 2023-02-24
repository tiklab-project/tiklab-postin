package net.tiklab.postin.apimock.http.service;

import net.tiklab.postin.apidef.http.mock.model.RequestHeaderMock;
import net.tiklab.postin.apidef.http.mock.service.RequestHeaderMockService;
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
public class RequestHeaderMockServiceImplTest {

    private static Logger logger = LoggerFactory.getLogger(RequestHeaderMockServiceImplTest.class);

    @Autowired
    RequestHeaderMockService requestHeaderMockService;

    static String id;

    @Test
    public void test01ForSaveRequestHeaderMock() {
        RequestHeaderMock requestHeaderMock = JMockit.mock(RequestHeaderMock.class);

        id = requestHeaderMockService.createRequestHeaderMock(requestHeaderMock);

        assertNotNull(id);
    }

    @Test
    public void test02ForUpdateRequestHeaderMock(){
        RequestHeaderMock requestHeaderMock = JMockit.mock(RequestHeaderMock.class);
        requestHeaderMock.setId(id);

        requestHeaderMockService.updateRequestHeaderMock(requestHeaderMock);
    }

    @Test
    public void test03ForFindRequestHeaderMock() {
        RequestHeaderMock requestHeaderMock = requestHeaderMockService.findRequestHeaderMock(id);

        assertNotNull(requestHeaderMock);
    }

    @Test
    public void test04ForFindAllRequestHeaderMock() {
        List<RequestHeaderMock> requestHeaderMockList = requestHeaderMockService.findAllRequestHeaderMock();

        assertNotNull(requestHeaderMockList);
    }

    @Test
    public void test05ForDeleteRequestHeaderMock(){
        requestHeaderMockService.deleteRequestHeaderMock(id);
    }
}