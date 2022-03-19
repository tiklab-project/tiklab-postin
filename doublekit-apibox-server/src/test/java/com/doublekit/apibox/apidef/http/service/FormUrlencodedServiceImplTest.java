package com.doublekit.apibox.apidef.http.service;

import com.doublekit.apibox.apidef.http.model.FormUrlencoded;
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
public class FormUrlencodedServiceImplTest {

    private static Logger logger = LoggerFactory.getLogger(FormUrlencodedServiceImplTest.class);

    @Autowired
    FormUrlencodedService formUrlencodedService;

    static String id;

    @Test
    public void test01ForSaveFormUrlencoded() {
        FormUrlencoded formUrlencoded = JMockit.mock(FormUrlencoded.class);

        id = formUrlencodedService.createFormUrlencoded(formUrlencoded);

        assertNotNull(id);
    }

    @Test
    public void test02ForUpdateFormUrlencoded(){
        FormUrlencoded formUrlencoded = JMockit.mock(FormUrlencoded.class);
        formUrlencoded.setId(id);

        formUrlencodedService.updateFormUrlencoded(formUrlencoded);
    }

    @Test
    public void test03ForFindFormUrlencoded() {
        FormUrlencoded formUrlencoded = formUrlencodedService.findFormUrlencoded(id);

        assertNotNull(formUrlencoded);
    }

    @Test
    public void test04ForFindAllFormUrlencoded() {
        List<FormUrlencoded> formUrlencodedList = formUrlencodedService.findAllFormUrlencoded();

        assertNotNull(formUrlencodedList);
    }

    @Test
    public void test05ForDeleteFormUrlencoded(){
        formUrlencodedService.deleteFormUrlencoded(id);
    }
}