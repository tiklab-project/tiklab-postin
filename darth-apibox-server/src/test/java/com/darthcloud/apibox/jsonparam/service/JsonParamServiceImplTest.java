package com.darthcloud.apibox.jsonparam.service;

import com.darthcloud.apibox.jsonparam.model.JsonParam;
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
public class JsonParamServiceImplTest {

    private static Logger logger = LoggerFactory.getLogger(JsonParamServiceImplTest.class);

    @Autowired
    JsonParamService jsonParamService;

    static String id;

    @Test
    public void test01ForSaveJsonParam() {
        JsonParam jsonParam = JMockit.mock(JsonParam.class);

        id = jsonParamService.createJsonParam(jsonParam);

        assertNotNull(id);
    }

    @Test
    public void test02ForUpdateJsonParam(){
        JsonParam jsonParam = JMockit.mock(JsonParam.class);
        jsonParam.setId(id);

        jsonParamService.updateJsonParam(jsonParam);
    }

    @Test
    public void test03ForFindJsonParam() {
        JsonParam jsonParam = jsonParamService.findJsonParam(id);

        assertNotNull(jsonParam);
    }

    @Test
    public void test04ForFindAllJsonParam() {
        List<JsonParam> jsonParamList = jsonParamService.findAllJsonParam();

        assertNotNull(jsonParamList);
    }

    @Test
    public void test05ForDeleteJsonParam(){
        jsonParamService.deleteJsonParam(id);
    }
}