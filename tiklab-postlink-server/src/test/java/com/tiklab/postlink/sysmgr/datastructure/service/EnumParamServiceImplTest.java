package com.tiklab.postlink.sysmgr.datastructure.service;

import com.tiklab.postlink.sysmgr.datastructure.model.EnumParam;
import com.tiklab.postlink.client.mock.JMockit;
import com.tiklab.postlink.config.TestConfig;
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
public class EnumParamServiceImplTest {

    private static Logger logger = LoggerFactory.getLogger(EnumParamServiceImplTest.class);

    @Autowired
    EnumParamService enumParamService;

    static String id;

    @Test
    public void test01ForSaveEnumParam() {
        EnumParam enumParam = JMockit.mock(EnumParam.class);

        id = enumParamService.createEnumParam(enumParam);

        assertNotNull(id);
    }

    @Test
    public void test02ForUpdateEnumParam(){
        EnumParam enumParam = JMockit.mock(EnumParam.class);
        enumParam.setId(id);

        enumParamService.updateEnumParam(enumParam);
    }

    @Test
    public void test03ForFindEnumParam() {
        EnumParam enumParam = enumParamService.findEnumParam(id);

        assertNotNull(enumParam);
    }

    @Test
    public void test04ForFindAllEnumParam() {
        List<EnumParam> enumParamList = enumParamService.findAllEnumParam();

        assertNotNull(enumParamList);
    }

    @Test
    public void test05ForDeleteEnumParam(){
        enumParamService.deleteEnumParam(id);
    }
}