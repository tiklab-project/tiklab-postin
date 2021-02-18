package com.darthcloud.apibox.api.apidef.service;

import com.darthcloud.apibox.api.apidef.model.ResponseResult;
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
public class ResponseResultServiceImplTest {

    private static Logger logger = LoggerFactory.getLogger(ResponseResultServiceImplTest.class);

    @Autowired
    ResponseResultService responseResultService;

    static String id;

    @Test
    public void test01ForSaveResponseResult() {
        ResponseResult responseResult = JMockit.mock(ResponseResult.class);

        id = responseResultService.createResponseResult(responseResult);

        assertNotNull(id);
    }

    @Test
    public void test02ForUpdateResponseResult(){
        ResponseResult responseResult = JMockit.mock(ResponseResult.class);
        responseResult.setId(id);

        responseResultService.updateResponseResult(responseResult);
    }

    @Test
    public void test03ForFindResponseResult() {
        ResponseResult responseResult = responseResultService.findResponseResult(id);

        assertNotNull(responseResult);
    }

    @Test
    public void test04ForFindAllResponseResult() {
        List<ResponseResult> responseResultList = responseResultService.findAllResponseResult();

        assertNotNull(responseResultList);
    }

    @Test
    public void test05ForDeleteResponseResult(){
        responseResultService.deleteResponseResult(id);
    }
}