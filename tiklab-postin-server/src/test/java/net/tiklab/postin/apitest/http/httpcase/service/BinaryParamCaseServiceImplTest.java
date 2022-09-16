package net.tiklab.postin.apitest.http.httpcase.service;

import net.tiklab.postin.apitest.http.httpcase.model.BinaryParamCase;
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
public class BinaryParamCaseServiceImplTest {

    private static Logger logger = LoggerFactory.getLogger(BinaryParamCaseServiceImplTest.class);

    @Autowired
    BinaryParamCaseService binaryParamCaseService;

    static String id;

    @Test
    public void test01ForSaveBinaryParamCase() {
        BinaryParamCase binaryParamCase = JMockit.mock(BinaryParamCase.class);

        id = binaryParamCaseService.createBinaryParamCase(binaryParamCase);

        assertNotNull(id);
    }

    @Test
    public void test02ForUpdateBinaryParamCase(){
        BinaryParamCase binaryParamCase = JMockit.mock(BinaryParamCase.class);
        binaryParamCase.setId(id);

        binaryParamCaseService.updateBinaryParamCase(binaryParamCase);
    }

    @Test
    public void test03ForFindBinaryParamCase() {
        BinaryParamCase binaryParamCase = binaryParamCaseService.findBinaryParamCase(id);

        assertNotNull(binaryParamCase);
    }

    @Test
    public void test04ForFindAllBinaryParamCase() {
        List<BinaryParamCase> binaryParamCaseList = binaryParamCaseService.findAllBinaryParamCase();

        assertNotNull(binaryParamCaseList);
    }

    @Test
    public void test05ForDeleteBinaryParamCase(){
        binaryParamCaseService.deleteBinaryParamCase(id);
    }
}