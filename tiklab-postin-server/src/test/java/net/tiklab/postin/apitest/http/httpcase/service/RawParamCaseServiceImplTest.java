package net.tiklab.postin.apitest.http.httpcase.service;

import net.tiklab.postin.apitest.http.httpcase.model.RawParamCase;
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
public class RawParamCaseServiceImplTest {

    private static Logger logger = LoggerFactory.getLogger(RawParamCaseServiceImplTest.class);

    @Autowired
    RawParamCaseService rawParamCaseService;

    static String id;

    @Test
    public void test01ForSaveRawParamCase() {
        RawParamCase rawParamCase = JMockit.mock(RawParamCase.class);

        id = rawParamCaseService.createRawParamCase(rawParamCase);

        assertNotNull(id);
    }

    @Test
    public void test02ForUpdateRawParamCase(){
        RawParamCase rawParamCase = JMockit.mock(RawParamCase.class);
        rawParamCase.setId(id);

        rawParamCaseService.updateRawParamCase(rawParamCase);
    }

    @Test
    public void test03ForFindRawParamCase() {
        RawParamCase rawParamCase = rawParamCaseService.findRawParamCase(id);

        assertNotNull(rawParamCase);
    }

    @Test
    public void test04ForFindAllRawParamCase() {
        List<RawParamCase> rawParamCaseList = rawParamCaseService.findAllRawParamCase();

        assertNotNull(rawParamCaseList);
    }

    @Test
    public void test05ForDeleteRawParamCase(){
        rawParamCaseService.deleteRawParamCase(id);
    }
}