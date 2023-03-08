package io.tiklab.postin.apimock.http.service;

import io.tiklab.postin.api.http.mock.model.JsonParamMock;
import io.tiklab.postin.api.http.mock.service.JsonParamMockService;
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
public class JsonParamMockServiceImplTest {

    private static Logger logger = LoggerFactory.getLogger(JsonParamMockServiceImplTest.class);

    @Autowired
    JsonParamMockService jsonParamMockService;

    static String id;

    @Test
    public void test01ForSaveJsonParamMock() {
        JsonParamMock jsonParamMock = JMockit.mock(JsonParamMock.class);

        id = jsonParamMockService.createJsonParamMock(jsonParamMock);

        assertNotNull(id);
    }

    @Test
    public void test02ForUpdateJsonParamMock(){
        JsonParamMock jsonParamMock = JMockit.mock(JsonParamMock.class);
        jsonParamMock.setId(id);

        jsonParamMockService.updateJsonParamMock(jsonParamMock);
    }

    @Test
    public void test03ForFindJsonParamMock() {
        JsonParamMock jsonParamMock = jsonParamMockService.findJsonParamMock(id);

        assertNotNull(jsonParamMock);
    }

    @Test
    public void test04ForFindAllJsonParamMock() {
        List<JsonParamMock> jsonParamMockList = jsonParamMockService.findAllJsonParamMock();

        assertNotNull(jsonParamMockList);
    }

    @Test
    public void test05ForDeleteJsonParamMock(){
        jsonParamMockService.deleteJsonParamMock(id);
    }
}