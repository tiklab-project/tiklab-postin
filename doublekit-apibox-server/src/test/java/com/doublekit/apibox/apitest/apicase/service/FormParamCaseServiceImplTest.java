package com.doublekit.apibox.apitest.apicase.service;

import com.doublekit.apibox.apitest.apicase.model.FormParamCase;
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
public class FormParamCaseServiceImplTest {

    private static Logger logger = LoggerFactory.getLogger(FormParamCaseServiceImplTest.class);

    @Autowired
    FormParamCaseService formParamCaseService;

    static String id;

    @Test
    public void test01ForSaveFormParamCase() {
        FormParamCase formParamCase = JMockit.mock(FormParamCase.class);

        id = formParamCaseService.createFormParamCase(formParamCase);

        assertNotNull(id);
    }

    @Test
    public void test02ForUpdateFormParamCase(){
        FormParamCase formParamCase = JMockit.mock(FormParamCase.class);
        formParamCase.setId(id);

        formParamCaseService.updateFormParamCase(formParamCase);
    }

    @Test
    public void test03ForFindFormParamCase() {
        FormParamCase formParamCase = formParamCaseService.findFormParamCase(id);

        assertNotNull(formParamCase);
    }

    @Test
    public void test04ForFindAllFormParamCase() {
        List<FormParamCase> formParamCaseList = formParamCaseService.findAllFormParamCase();

        assertNotNull(formParamCaseList);
    }

    @Test
    public void test05ForDeleteFormParamCase(){
        formParamCaseService.deleteFormParamCase(id);
    }
}