package io.tiklab.postin.api.http.service;

import io.tiklab.postin.api.http.definition.model.JsonResponses;
import io.tiklab.postin.api.http.definition.service.JsonResponseService;
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
public class JsonResponsesServiceImplTest {

    private static Logger logger = LoggerFactory.getLogger(JsonResponsesServiceImplTest.class);

    @Autowired
    JsonResponseService jsonResponseService;

    static String id;

    @Test
    public void test01ForSaveJsonResponse() {
        JsonResponses jsonResponses = JMockit.mock(JsonResponses.class);

        id = jsonResponseService.createJsonResponse(jsonResponses);

        assertNotNull(id);
    }

    @Test
    public void test02ForUpdateJsonResponse(){
        JsonResponses jsonResponses = JMockit.mock(JsonResponses.class);
        jsonResponses.setId(id);

        jsonResponseService.updateJsonResponse(jsonResponses);
    }

    @Test
    public void test03ForFindJsonResponse() {
        JsonResponses jsonResponses = jsonResponseService.findJsonResponse(id);

        assertNotNull(jsonResponses);
    }

    @Test
    public void test04ForFindAllJsonResponse() {
        List<JsonResponses> jsonResponsesList = jsonResponseService.findAllJsonResponse();

        assertNotNull(jsonResponsesList);
    }

    @Test
    public void test05ForDeleteJsonResponse(){
        jsonResponseService.deleteJsonResponse(id);
    }
}