package net.tiklab.postin.api.http.service;

import net.tiklab.postin.api.http.definition.model.ApiResponse;
import net.tiklab.postin.api.http.definition.service.ApiResponseService;
import net.tiklab.postin.client.mock.JMockit;
import net.tiklab.postin.config.TestConfig;
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
public class ApiResponseServiceImplTest {

    private static Logger logger = LoggerFactory.getLogger(ApiResponseServiceImplTest.class);

    @Autowired
    ApiResponseService apiResponseService;

    static String id;

    @Test
    public void test01ForSaveApiResponse() {
        ApiResponse apiResponse = JMockit.mock(ApiResponse.class);

        id = apiResponseService.createApiResponse(apiResponse);

        assertNotNull(id);
    }

    @Test
    public void test02ForUpdateApiResponse(){
        ApiResponse apiResponse = JMockit.mock(ApiResponse.class);
        apiResponse.setId(id);

        apiResponseService.updateApiResponse(apiResponse);
    }

    @Test
    public void test03ForFindApiResponse() {
        ApiResponse apiResponse = apiResponseService.findApiResponse(id);

        assertNotNull(apiResponse);
    }

    @Test
    public void test04ForFindAllApiResponse() {
        List<ApiResponse> apiResponseList = apiResponseService.findAllApiResponse();

        assertNotNull(apiResponseList);
    }

    @Test
    public void test05ForDeleteApiResponse(){
        apiResponseService.deleteApiResponse(id);
    }
}