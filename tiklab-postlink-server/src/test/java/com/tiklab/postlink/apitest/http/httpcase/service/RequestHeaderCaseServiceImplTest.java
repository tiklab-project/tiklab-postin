package com.tiklab.postlink.apitest.http.httpcase.service;

import com.tiklab.postlink.apitest.http.httpcase.model.RequestHeaderCase;
import com.tiklab.postlink.client.mock.JMockit;
import com.tiklab.postlink.config.TestConfig;
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
public class RequestHeaderCaseServiceImplTest {

    private static Logger logger = LoggerFactory.getLogger(RequestHeaderCaseServiceImplTest.class);

    @Autowired
    RequestHeaderCaseService requestHeaderCaseService;

    static String id;

    @Test
    public void test01ForSaveRequestHeaderCase() {
        RequestHeaderCase requestHeaderCase = JMockit.mock(RequestHeaderCase.class);

        id = requestHeaderCaseService.createRequestHeaderCase(requestHeaderCase);

        assertNotNull(id);
    }

    @Test
    public void test02ForUpdateRequestHeaderCase(){
        RequestHeaderCase requestHeaderCase = JMockit.mock(RequestHeaderCase.class);
        requestHeaderCase.setId(id);

        requestHeaderCaseService.updateRequestHeaderCase(requestHeaderCase);
    }

    @Test
    public void test03ForFindRequestHeaderCase() {
        RequestHeaderCase requestHeaderCase = requestHeaderCaseService.findRequestHeaderCase(id);

        assertNotNull(requestHeaderCase);
    }

    @Test
    public void test04ForFindAllRequestHeaderCase() {
        List<RequestHeaderCase> requestHeaderCaseList = requestHeaderCaseService.findAllRequestHeaderCase();

        assertNotNull(requestHeaderCaseList);
    }

    @Test
    public void test05ForDeleteRequestHeaderCase(){
        requestHeaderCaseService.deleteRequestHeaderCase(id);
    }
}