package com.doublekit.apibox.apitest.http.httpcase.service;

import com.doublekit.apibox.apitest.http.httpcase.model.HttpTestcase;
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
public class HttpHttpHttpTestcaseServiceImplTest {

    private static Logger logger = LoggerFactory.getLogger(HttpHttpHttpTestcaseServiceImplTest.class);

    @Autowired
    HttpTestcaseService httpTestcaseService;

    static String id;

    @Test
    public void test01ForSaveTestcase() {
        HttpTestcase httpTestcase = JMockit.mock(HttpTestcase.class);

        id = httpTestcaseService.createTestcase(httpTestcase);

        assertNotNull(id);
    }

    @Test
    public void test02ForUpdateTestcase(){
        HttpTestcase httpTestcase = JMockit.mock(HttpTestcase.class);
        httpTestcase.setId(id);

        httpTestcaseService.updateTestcase(httpTestcase);
    }

    @Test
    public void test03ForFindTestcase() {
        HttpTestcase httpTestcase = httpTestcaseService.findTestcase(id);

        assertNotNull(httpTestcase);
    }

    @Test
    public void test04ForFindAllTestcase() {
        List<HttpTestcase> httpTestcaseList = httpTestcaseService.findAllTestcase();

        assertNotNull(httpTestcaseList);
    }

    @Test
    public void test05ForDeleteTestcase(){
        httpTestcaseService.deleteTestcase(id);
    }
}