package io.tiklab.postin.api.http.service;

import io.tiklab.postin.api.http.definition.model.ApiRequest;
import io.tiklab.postin.api.http.definition.service.ApiRequestService;
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
public class ApiRequestServiceImplTest {

    private static Logger logger = LoggerFactory.getLogger(ApiRequestServiceImplTest.class);

    @Autowired
    ApiRequestService apiRequestService;

    static String id;

    @Test
    public void test01ForSaveApiRequest() {
        ApiRequest apiRequest = JMockit.mock(ApiRequest.class);

        id = apiRequestService.createApiRequest(apiRequest);

        assertNotNull(id);
    }

    @Test
    public void test02ForUpdateApiRequest(){
        ApiRequest apiRequest = JMockit.mock(ApiRequest.class);
        apiRequest.setId(id);

        apiRequestService.updateApiRequest(apiRequest);
    }

    @Test
    public void test03ForFindApiRequest() {
        ApiRequest apiRequest = apiRequestService.findApiRequest(id);

        assertNotNull(apiRequest);
    }

    @Test
    public void test04ForFindAllApiRequest() {
        List<ApiRequest> apiRequestList = apiRequestService.findAllApiRequest();

        assertNotNull(apiRequestList);
    }

    @Test
    public void test05ForDeleteApiRequest(){
        apiRequestService.deleteApiRequest(id);
    }
}