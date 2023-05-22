package io.tiklab.postin.apitest.http.httpcase.service;

import io.tiklab.postin.api.http.test.cases.model.JsonParamCases;
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
public class JsonParamCasesServiceImplTest {

    private static Logger logger = LoggerFactory.getLogger(JsonParamCasesServiceImplTest.class);

    @Autowired
    JsonParamCaseService jsonParamCaseService;

    static String id;

    @Test
    public void test01ForSaveJsonParamCase() {
        JsonParamCases jsonParamCases = JMockit.mock(JsonParamCases.class);

        id = jsonParamCaseService.createJsonParamCase(jsonParamCases);

        assertNotNull(id);
    }

    @Test
    public void test02ForUpdateJsonParamCase(){
        JsonParamCases jsonParamCases = JMockit.mock(JsonParamCases.class);
        jsonParamCases.setId(id);

        jsonParamCaseService.updateJsonParamCase(jsonParamCases);
    }

    @Test
    public void test03ForFindJsonParamCase() {
        JsonParamCases jsonParamCases = jsonParamCaseService.findJsonParamCase(id);

        assertNotNull(jsonParamCases);
    }

    @Test
    public void test04ForFindAllJsonParamCase() {
        List<JsonParamCases> jsonParamCasesList = jsonParamCaseService.findAllJsonParamCase();

        assertNotNull(jsonParamCasesList);
    }

    @Test
    public void test05ForDeleteJsonParamCase(){
        jsonParamCaseService.deleteJsonParamCase(id);
    }
}