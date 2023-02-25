package net.tiklab.postin.api.http.service;

import net.tiklab.postin.api.http.definition.model.RawParam;
import net.tiklab.postin.api.http.definition.service.RawParamService;
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
public class RawParamServiceImplTest {

    private static Logger logger = LoggerFactory.getLogger(RawParamServiceImplTest.class);

    @Autowired
    RawParamService rawParamService;

    static String id;

    @Test
    public void test01ForSaveRawParam() {
        RawParam rawParam = JMockit.mock(RawParam.class);

        id = rawParamService.createRawParam(rawParam);

        assertNotNull(id);
    }

    @Test
    public void test02ForUpdateRawParam(){
        RawParam rawParam = JMockit.mock(RawParam.class);
        rawParam.setId(id);

        rawParamService.updateRawParam(rawParam);
    }

    @Test
    public void test03ForFindRawParam() {
        RawParam rawParam = rawParamService.findRawParam(id);

        assertNotNull(rawParam);
    }

    @Test
    public void test04ForFindAllRawParam() {
        List<RawParam> rawParamList = rawParamService.findAllRawParam();

        assertNotNull(rawParamList);
    }

    @Test
    public void test05ForDeleteRawParam(){
        rawParamService.deleteRawParam(id);
    }
}