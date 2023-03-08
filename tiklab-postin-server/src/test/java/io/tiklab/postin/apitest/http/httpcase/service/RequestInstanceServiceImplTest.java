package io.tiklab.postin.apitest.http.httpcase.service;

import io.tiklab.postin.api.http.test.instance.model.RequestInstance;
import io.tiklab.postin.api.http.test.instance.service.RequestInstanceService;
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
public class RequestInstanceServiceImplTest {

    private static Logger logger = LoggerFactory.getLogger(RequestInstanceServiceImplTest.class);

    @Autowired
    RequestInstanceService requestInstanceService;

    static String id;

    @Test
    public void test01ForSaveRequestInstance() {
        RequestInstance requestInstance = JMockit.mock(RequestInstance.class);

        id = requestInstanceService.createRequestInstance(requestInstance);

        assertNotNull(id);
    }

    @Test
    public void test02ForUpdateRequestInstance(){
        RequestInstance requestInstance = JMockit.mock(RequestInstance.class);
        requestInstance.setId(id);

        requestInstanceService.updateRequestInstance(requestInstance);
    }

    @Test
    public void test03ForFindRequestInstance() {
        RequestInstance requestInstance = requestInstanceService.findRequestInstance(id);

        assertNotNull(requestInstance);
    }

    @Test
    public void test04ForFindAllRequestInstance() {
        List<RequestInstance> requestInstanceList = requestInstanceService.findAllRequestInstance();

        assertNotNull(requestInstanceList);
    }

    @Test
    public void test05ForDeleteRequestInstance(){
        requestInstanceService.deleteRequestInstance(id);
    }
}