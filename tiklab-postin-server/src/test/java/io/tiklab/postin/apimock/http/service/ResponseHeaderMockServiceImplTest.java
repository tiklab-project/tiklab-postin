package io.tiklab.postin.apimock.http.service;

import io.tiklab.postin.api.http.mock.model.ResponseHeaderMock;
import io.tiklab.postin.api.http.mock.service.ResponseHeaderMockService;
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
public class ResponseHeaderMockServiceImplTest {

    private static Logger logger = LoggerFactory.getLogger(ResponseHeaderMockServiceImplTest.class);

    @Autowired
    ResponseHeaderMockService responseHeaderMockService;

    static String id;

    @Test
    public void test01ForSaveResponseHeaderMock() {
        ResponseHeaderMock responseHeaderMock = JMockit.mock(ResponseHeaderMock.class);

        id = responseHeaderMockService.createResponseHeaderMock(responseHeaderMock);

        assertNotNull(id);
    }

    @Test
    public void test02ForUpdateResponseHeaderMock(){
        ResponseHeaderMock responseHeaderMock = JMockit.mock(ResponseHeaderMock.class);
        responseHeaderMock.setId(id);

        responseHeaderMockService.updateResponseHeaderMock(responseHeaderMock);
    }

    @Test
    public void test03ForFindResponseHeaderMock() {
        ResponseHeaderMock responseHeaderMock = responseHeaderMockService.findResponseHeaderMock(id);

        assertNotNull(responseHeaderMock);
    }

    @Test
    public void test04ForFindAllResponseHeaderMock() {
        List<ResponseHeaderMock> responseHeaderMockList = responseHeaderMockService.findAllResponseHeaderMock();

        assertNotNull(responseHeaderMockList);
    }

    @Test
    public void test05ForDeleteResponseHeaderMock(){
        responseHeaderMockService.deleteResponseHeaderMock(id);
    }
}