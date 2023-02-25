package net.tiklab.postin.api.http.service;

import net.tiklab.postin.api.http.definition.model.JsonResponse;
import net.tiklab.postin.api.http.definition.service.JsonResponseService;
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
public class JsonResponseServiceImplTest {

    private static Logger logger = LoggerFactory.getLogger(JsonResponseServiceImplTest.class);

    @Autowired
    JsonResponseService jsonResponseService;

    static String id;

    @Test
    public void test01ForSaveJsonResponse() {
        JsonResponse jsonResponse = JMockit.mock(JsonResponse.class);

        id = jsonResponseService.createJsonResponse(jsonResponse);

        assertNotNull(id);
    }

    @Test
    public void test02ForUpdateJsonResponse(){
        JsonResponse jsonResponse = JMockit.mock(JsonResponse.class);
        jsonResponse.setId(id);

        jsonResponseService.updateJsonResponse(jsonResponse);
    }

    @Test
    public void test03ForFindJsonResponse() {
        JsonResponse jsonResponse = jsonResponseService.findJsonResponse(id);

        assertNotNull(jsonResponse);
    }

    @Test
    public void test04ForFindAllJsonResponse() {
        List<JsonResponse> jsonResponseList = jsonResponseService.findAllJsonResponse();

        assertNotNull(jsonResponseList);
    }

    @Test
    public void test05ForDeleteJsonResponse(){
        jsonResponseService.deleteJsonResponse(id);
    }
}