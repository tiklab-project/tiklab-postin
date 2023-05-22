package io.tiklab.postin.api.http.service;

import io.tiklab.postin.api.http.definition.model.RequestHeaders;
import io.tiklab.postin.api.http.definition.service.RequestHeaderService;
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
public class RequestHeadersServiceImplTest {

    private static Logger logger = LoggerFactory.getLogger(RequestHeadersServiceImplTest.class);

    @Autowired
    RequestHeaderService requestHeaderService;

    static String id;

    @Test
    public void test01ForSaveRequestHeader() {
        RequestHeaders requestHeaders = JMockit.mock(RequestHeaders.class);

        id = requestHeaderService.createRequestHeader(requestHeaders);

        assertNotNull(id);
    }

    @Test
    public void test02ForUpdateRequestHeader(){
        RequestHeaders requestHeaders = JMockit.mock(RequestHeaders.class);
        requestHeaders.setId(id);

        requestHeaderService.updateRequestHeader(requestHeaders);
    }

    @Test
    public void test03ForFindRequestHeader() {
        RequestHeaders requestHeaders = requestHeaderService.findRequestHeader(id);

        assertNotNull(requestHeaders);
    }

    @Test
    public void test04ForFindAllRequestHeader() {
        List<RequestHeaders> requestHeadersList = requestHeaderService.findAllRequestHeader();

        assertNotNull(requestHeadersList);
    }

    @Test
    public void test05ForDeleteRequestHeader(){
        requestHeaderService.deleteRequestHeader(id);
    }
}