package com.doublekit.apibox.apidef.service;

import com.doublekit.apibox.apidef.model.RequestHeader;
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
public class RequestHeaderServiceImplTest {

    private static Logger logger = LoggerFactory.getLogger(RequestHeaderServiceImplTest.class);

    @Autowired
    RequestHeaderService requestHeaderService;

    static String id;

    @Test
    public void test01ForSaveRequestHeader() {
        RequestHeader requestHeader = JMockit.mock(RequestHeader.class);

        id = requestHeaderService.createRequestHeader(requestHeader);

        assertNotNull(id);
    }

    @Test
    public void test02ForUpdateRequestHeader(){
        RequestHeader requestHeader = JMockit.mock(RequestHeader.class);
        requestHeader.setId(id);

        requestHeaderService.updateRequestHeader(requestHeader);
    }

    @Test
    public void test03ForFindRequestHeader() {
        RequestHeader requestHeader = requestHeaderService.findRequestHeader(id);

        assertNotNull(requestHeader);
    }

    @Test
    public void test04ForFindAllRequestHeader() {
        List<RequestHeader> requestHeaderList = requestHeaderService.findAllRequestHeader();

        assertNotNull(requestHeaderList);
    }

    @Test
    public void test05ForDeleteRequestHeader(){
        requestHeaderService.deleteRequestHeader(id);
    }
}