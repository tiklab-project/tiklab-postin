package io.tiklab.postin.api.http.service;

import io.tiklab.postin.api.http.definition.model.ResponseHeaders;
import io.tiklab.postin.api.http.definition.service.ResponseHeaderService;
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
public class ResponseHeadersServiceImplTest {

    private static Logger logger = LoggerFactory.getLogger(ResponseHeadersServiceImplTest.class);

    @Autowired
    ResponseHeaderService responseHeaderService;

    static String id;

    @Test
    public void test01ForSaveResponseHeader() {
        ResponseHeaders responseHeaders = JMockit.mock(ResponseHeaders.class);

        id = responseHeaderService.createResponseHeader(responseHeaders);

        assertNotNull(id);
    }

    @Test
    public void test02ForUpdateResponseHeader(){
        ResponseHeaders responseHeaders = JMockit.mock(ResponseHeaders.class);
        responseHeaders.setId(id);

        responseHeaderService.updateResponseHeader(responseHeaders);
    }

    @Test
    public void test03ForFindResponseHeader() {
        ResponseHeaders responseHeaders = responseHeaderService.findResponseHeader(id);

        assertNotNull(responseHeaders);
    }

    @Test
    public void test04ForFindAllResponseHeader() {
        List<ResponseHeaders> responseHeadersList = responseHeaderService.findAllResponseHeader();

        assertNotNull(responseHeadersList);
    }

    @Test
    public void test05ForDeleteResponseHeader(){
        responseHeaderService.deleteResponseHeader(id);
    }
}