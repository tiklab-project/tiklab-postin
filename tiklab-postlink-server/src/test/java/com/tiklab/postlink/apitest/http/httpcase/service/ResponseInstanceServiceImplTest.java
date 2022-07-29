package com.tiklab.postlink.apitest.http.httpcase.service;

import com.tiklab.postlink.apitest.http.httpinstance.model.ResponseInstance;
import com.tiklab.postlink.apitest.http.httpinstance.service.ResponseInstanceService;
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
public class ResponseInstanceServiceImplTest {

    private static Logger logger = LoggerFactory.getLogger(ResponseInstanceServiceImplTest.class);

    @Autowired
    ResponseInstanceService responseInstanceService;

    static String id;

    @Test
    public void test01ForSaveResponseInstance() {
        ResponseInstance responseInstance = JMockit.mock(ResponseInstance.class);

        id = responseInstanceService.createResponseInstance(responseInstance);

        assertNotNull(id);
    }

    @Test
    public void test02ForUpdateResponseInstance(){
        ResponseInstance responseInstance = JMockit.mock(ResponseInstance.class);
        responseInstance.setId(id);

        responseInstanceService.updateResponseInstance(responseInstance);
    }

    @Test
    public void test03ForFindResponseInstance() {
        ResponseInstance responseInstance = responseInstanceService.findResponseInstance(id);

        assertNotNull(responseInstance);
    }

    @Test
    public void test04ForFindAllResponseInstance() {
        List<ResponseInstance> responseInstanceList = responseInstanceService.findAllResponseInstance();

        assertNotNull(responseInstanceList);
    }

    @Test
    public void test05ForDeleteResponseInstance(){
        responseInstanceService.deleteResponseInstance(id);
    }
}