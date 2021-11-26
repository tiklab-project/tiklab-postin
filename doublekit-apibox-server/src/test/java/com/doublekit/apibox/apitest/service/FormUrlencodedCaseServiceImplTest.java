package com.doublekit.apibox.apitest.service;

import com.doublekit.apibox.apitest.model.FormUrlencodedCase;
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
public class FormUrlencodedCaseServiceImplTest {

    private static Logger logger = LoggerFactory.getLogger(FormUrlencodedCaseServiceImplTest.class);

    @Autowired
    FormUrlencodedCaseService formUrlencodedCaseService;

    static String id;

    @Test
    public void test01ForSaveFormUrlencodedCase() {
        FormUrlencodedCase formUrlencodedCase = JMockit.mock(FormUrlencodedCase.class);

        id = formUrlencodedCaseService.createFormUrlencodedCase(formUrlencodedCase);

        assertNotNull(id);
    }

    @Test
    public void test02ForUpdateFormUrlencodedCase(){
        FormUrlencodedCase formUrlencodedCase = JMockit.mock(FormUrlencodedCase.class);
        formUrlencodedCase.setId(id);

        formUrlencodedCaseService.updateFormUrlencodedCase(formUrlencodedCase);
    }

    @Test
    public void test03ForFindFormUrlencodedCase() {
        FormUrlencodedCase formUrlencodedCase = formUrlencodedCaseService.findFormUrlencodedCase(id);

        assertNotNull(formUrlencodedCase);
    }

    @Test
    public void test04ForFindAllFormUrlencodedCase() {
        List<FormUrlencodedCase> formUrlencodedCaseList = formUrlencodedCaseService.findAllFormUrlencodedCase();

        assertNotNull(formUrlencodedCaseList);
    }

    @Test
    public void test05ForDeleteFormUrlencodedCase(){
        formUrlencodedCaseService.deleteFormUrlencodedCase(id);
    }
}