package com.tiklab.postlink.apimock.http.service;

import com.tiklab.postlink.apimock.http.model.FormParamMock;
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
public class FormParamMockServiceImplTest {

    private static Logger logger = LoggerFactory.getLogger(FormParamMockServiceImplTest.class);

    @Autowired
    FormParamMockService formParamMockService;

    static String id;

    @Test
    public void test01ForSaveFormParamMock() {
        FormParamMock formParamMock = JMockit.mock(FormParamMock.class);

        id = formParamMockService.createFormParamMock(formParamMock);

        assertNotNull(id);
    }

    @Test
    public void test02ForUpdateFormParamMock(){
        FormParamMock formParamMock = JMockit.mock(FormParamMock.class);
        formParamMock.setId(id);

        formParamMockService.updateFormParamMock(formParamMock);
    }

    @Test
    public void test03ForFindFormParamMock() {
        FormParamMock formParamMock = formParamMockService.findFormParamMock(id);

        assertNotNull(formParamMock);
    }

    @Test
    public void test04ForFindAllFormParamMock() {
        List<FormParamMock> formParamMockList = formParamMockService.findAllFormParamMock();

        assertNotNull(formParamMockList);
    }

    @Test
    public void test05ForDeleteFormParamMock(){
        formParamMockService.deleteFormParamMock(id);
    }
}