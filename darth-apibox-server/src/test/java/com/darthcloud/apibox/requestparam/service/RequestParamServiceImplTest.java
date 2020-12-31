package com.darthcloud.apibox.requestparam.service;

import com.darthcloud.apibox.requestparam.model.RequestParam;
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
public class RequestParamServiceImplTest {

    private static Logger logger = LoggerFactory.getLogger(RequestParamServiceImplTest.class);

    @Autowired
    RequestParamService requestParamService;

    static String id;

    @Test
    public void test01ForSaveRequestParam() {
        RequestParam requestParam = JMockit.mock(RequestParam.class);

        id = requestParamService.createRequestParam(requestParam);

        assertNotNull(id);
    }

    @Test
    public void test02ForUpdateRequestParam(){
        RequestParam requestParam = JMockit.mock(RequestParam.class);
        requestParam.setId(id);

        requestParamService.updateRequestParam(requestParam);
    }

    @Test
    public void test03ForFindRequestParam() {
        RequestParam requestParam = requestParamService.findRequestParam(id);

        assertNotNull(requestParam);
    }

    @Test
    public void test04ForFindAllRequestParam() {
        List<RequestParam> requestParamList = requestParamService.findAllRequestParam();

        assertNotNull(requestParamList);
    }

    @Test
    public void test05ForDeleteRequestParam(){
        requestParamService.deleteRequestParam(id);
    }
}