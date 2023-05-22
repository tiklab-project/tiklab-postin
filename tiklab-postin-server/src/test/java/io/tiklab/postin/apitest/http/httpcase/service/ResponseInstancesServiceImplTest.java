package io.tiklab.postin.apitest.http.httpcase.service;

import io.tiklab.postin.api.http.test.instance.model.ResponseInstances;
import io.tiklab.postin.api.http.test.instance.service.ResponseInstanceService;
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
public class ResponseInstancesServiceImplTest {

    private static Logger logger = LoggerFactory.getLogger(ResponseInstancesServiceImplTest.class);

    @Autowired
    ResponseInstanceService responseInstanceService;

    static String id;

    @Test
    public void test01ForSaveResponseInstance() {
        ResponseInstances responseInstances = JMockit.mock(ResponseInstances.class);

        id = responseInstanceService.createResponseInstance(responseInstances);

        assertNotNull(id);
    }

    @Test
    public void test02ForUpdateResponseInstance(){
        ResponseInstances responseInstances = JMockit.mock(ResponseInstances.class);
        responseInstances.setId(id);

        responseInstanceService.updateResponseInstance(responseInstances);
    }

    @Test
    public void test03ForFindResponseInstance() {
        ResponseInstances responseInstances = responseInstanceService.findResponseInstance(id);

        assertNotNull(responseInstances);
    }

    @Test
    public void test04ForFindAllResponseInstance() {
        List<ResponseInstances> responseInstancesList = responseInstanceService.findAllResponseInstance();

        assertNotNull(responseInstancesList);
    }

    @Test
    public void test05ForDeleteResponseInstance(){
        responseInstanceService.deleteResponseInstance(id);
    }
}