package com.tiklab.postlink.apitest.http.httpcase.service;

import com.tiklab.postlink.apitest.http.httpcase.model.RequestBodyCase;
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
public class RequestBodyCaseServiceImplTest {

    private static Logger logger = LoggerFactory.getLogger(RequestBodyCaseServiceImplTest.class);

    @Autowired
    RequestBodyCaseService requestBodyCaseService;

    static String id;

    @Test
    public void test01ForSaveRequestBodyCase() {
        RequestBodyCase requestBodyCase = JMockit.mock(RequestBodyCase.class);

        id = requestBodyCaseService.createRequestBodyCase(requestBodyCase);

        assertNotNull(id);
    }

    @Test
    public void test02ForUpdateRequestBodyCase(){
        RequestBodyCase requestBodyCase = JMockit.mock(RequestBodyCase.class);
        requestBodyCase.setId(id);

        requestBodyCaseService.updateRequestBodyCase(requestBodyCase);
    }

    @Test
    public void test03ForFindRequestBodyCase() {
        RequestBodyCase requestBodyCase = requestBodyCaseService.findRequestBodyCase(id);

        assertNotNull(requestBodyCase);
    }

    @Test
    public void test04ForFindAllRequestBodyCase() {
        List<RequestBodyCase> requestBodyCaseList = requestBodyCaseService.findAllRequestBodyCase();

        assertNotNull(requestBodyCaseList);
    }

    @Test
    public void test05ForDeleteRequestBodyCase(){
        requestBodyCaseService.deleteRequestBodyCase(id);
    }
}