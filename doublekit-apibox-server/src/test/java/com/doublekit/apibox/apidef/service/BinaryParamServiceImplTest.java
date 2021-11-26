package com.doublekit.apibox.apidef.service;

import com.doublekit.apibox.apidef.model.BinaryParam;
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
public class BinaryParamServiceImplTest {

    private static Logger logger = LoggerFactory.getLogger(BinaryParamServiceImplTest.class);

    @Autowired
    BinaryParamService binaryParamService;

    static String id;

    @Test
    public void test01ForSaveBinaryParam() {
        BinaryParam binaryParam = JMockit.mock(BinaryParam.class);

        id = binaryParamService.createBinaryParam(binaryParam);

        assertNotNull(id);
    }

    @Test
    public void test02ForUpdateBinaryParam(){
        BinaryParam binaryParam = JMockit.mock(BinaryParam.class);
        binaryParam.setId(id);

        binaryParamService.updateBinaryParam(binaryParam);
    }

    @Test
    public void test03ForFindBinaryParam() {
        BinaryParam binaryParam = binaryParamService.findBinaryParam(id);

        assertNotNull(binaryParam);
    }

    @Test
    public void test04ForFindAllBinaryParam() {
        List<BinaryParam> binaryParamList = binaryParamService.findAllBinaryParam();

        assertNotNull(binaryParamList);
    }

    @Test
    public void test05ForDeleteBinaryParam(){
        binaryParamService.deleteBinaryParam(id);
    }
}