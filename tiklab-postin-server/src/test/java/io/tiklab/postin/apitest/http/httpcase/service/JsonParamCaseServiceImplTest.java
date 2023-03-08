package io.tiklab.postin.apitest.http.httpcase.service;

import io.tiklab.postin.api.http.test.cases.model.JsonParamCase;
import io.tiklab.postin.api.http.test.cases.service.JsonParamCaseService;
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
public class JsonParamCaseServiceImplTest {

    private static Logger logger = LoggerFactory.getLogger(JsonParamCaseServiceImplTest.class);

    @Autowired
    JsonParamCaseService jsonParamCaseService;

    static String id;

    @Test
    public void test01ForSaveJsonParamCase() {
        JsonParamCase jsonParamCase = JMockit.mock(JsonParamCase.class);

        id = jsonParamCaseService.createJsonParamCase(jsonParamCase);

        assertNotNull(id);
    }

    @Test
    public void test02ForUpdateJsonParamCase(){
        JsonParamCase jsonParamCase = JMockit.mock(JsonParamCase.class);
        jsonParamCase.setId(id);

        jsonParamCaseService.updateJsonParamCase(jsonParamCase);
    }

    @Test
    public void test03ForFindJsonParamCase() {
        JsonParamCase jsonParamCase = jsonParamCaseService.findJsonParamCase(id);

        assertNotNull(jsonParamCase);
    }

    @Test
    public void test04ForFindAllJsonParamCase() {
        List<JsonParamCase> jsonParamCaseList = jsonParamCaseService.findAllJsonParamCase();

        assertNotNull(jsonParamCaseList);
    }

    @Test
    public void test05ForDeleteJsonParamCase(){
        jsonParamCaseService.deleteJsonParamCase(id);
    }
}