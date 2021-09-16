package com.doublekit.apibox.datastructure.service;

import com.doublekit.apibox.datastructure.model.JsonParamDS;
import com.doublekit.apibox.client.mock.JMockit;
import com.doublekit.apibox.config.TestConfig;
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
public class JsonParamDSServiceImplTest {

    private static Logger logger = LoggerFactory.getLogger(JsonParamDSServiceImplTest.class);

    @Autowired
    JsonParamDSService jsonParamDSService;

    static String id;

    @Test
    public void test01ForSaveJsonParamDS() {
        JsonParamDS jsonParamDS = JMockit.mock(JsonParamDS.class);

        id = jsonParamDSService.createJsonParamDS(jsonParamDS);

        assertNotNull(id);
    }

    @Test
    public void test02ForUpdateJsonParamDS(){
        JsonParamDS jsonParamDS = JMockit.mock(JsonParamDS.class);
        jsonParamDS.setId(id);

        jsonParamDSService.updateJsonParamDS(jsonParamDS);
    }

    @Test
    public void test03ForFindJsonParamDS() {
        JsonParamDS jsonParamDS = jsonParamDSService.findJsonParamDS(id);

        assertNotNull(jsonParamDS);
    }

    @Test
    public void test04ForFindAllJsonParamDS() {
        List<JsonParamDS> jsonParamDSList = jsonParamDSService.findAllJsonParamDS();

        assertNotNull(jsonParamDSList);
    }

    @Test
    public void test05ForDeleteJsonParamDS(){
        jsonParamDSService.deleteJsonParamDS(id);
    }
}