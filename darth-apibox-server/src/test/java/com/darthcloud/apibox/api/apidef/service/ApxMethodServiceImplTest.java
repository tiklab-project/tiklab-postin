package com.darthcloud.apibox.api.apidef.service;

import com.darthcloud.apibox.api.apidef.model.ApxMethod;
import com.darthcloud.apibox.client.mock.JMockit;
import com.darthcloud.apibox.config.TestConfig;
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
public class ApxMethodServiceImplTest {

    private static Logger logger = LoggerFactory.getLogger(ApxMethodServiceImplTest.class);

    @Autowired
    ApxMethodService apxMethodService;

    static String id;

    @Test
    public void test01ForSaveApxMethod() {
        ApxMethod apxMethod = JMockit.mock(ApxMethod.class);

        id = apxMethodService.createApxMethod(apxMethod);

        assertNotNull(id);
    }

    @Test
    public void test02ForUpdateApxMethod(){
        ApxMethod apxMethod = JMockit.mock(ApxMethod.class);
        apxMethod.setId(id);

        apxMethodService.updateApxMethod(apxMethod);
    }

    @Test
    public void test03ForFindApxMethod() {
        ApxMethod apxMethod = apxMethodService.findApxMethod(id);

        assertNotNull(apxMethod);
    }

    @Test
    public void test04ForFindAllApxMethod() {
        List<ApxMethod> apxMethodList = apxMethodService.findAllApxMethod();

        assertNotNull(apxMethodList);
    }

    @Test
    public void test05ForDeleteApxMethod(){
        apxMethodService.deleteApxMethod(id);
    }
}