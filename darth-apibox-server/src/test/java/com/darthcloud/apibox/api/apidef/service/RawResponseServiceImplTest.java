package com.darthcloud.apibox.api.apidef.service;

import com.darthcloud.apibox.api.apidef.model.RawResponse;
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
public class RawResponseServiceImplTest {

    private static Logger logger = LoggerFactory.getLogger(RawResponseServiceImplTest.class);

    @Autowired
    RawResponseService rawResponseService;

    static String id;

    @Test
    public void test01ForSaveRawResponse() {
        RawResponse rawResponse = JMockit.mock(RawResponse.class);

        id = rawResponseService.createRawResponse(rawResponse);

        assertNotNull(id);
    }

    @Test
    public void test02ForUpdateRawResponse(){
        RawResponse rawResponse = JMockit.mock(RawResponse.class);
        rawResponse.setId(id);

        rawResponseService.updateRawResponse(rawResponse);
    }

    @Test
    public void test03ForFindRawResponse() {
        RawResponse rawResponse = rawResponseService.findRawResponse(id);

        assertNotNull(rawResponse);
    }

    @Test
    public void test04ForFindAllRawResponse() {
        List<RawResponse> rawResponseList = rawResponseService.findAllRawResponse();

        assertNotNull(rawResponseList);
    }

    @Test
    public void test05ForDeleteRawResponse(){
        rawResponseService.deleteRawResponse(id);
    }
}