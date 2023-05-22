package io.tiklab.postin.apitest.http.httpcase.service;

import io.tiklab.postin.api.http.test.cases.model.AssertCases;
import io.tiklab.postin.api.http.test.cases.service.AssertCaseService;
import io.tiklab.postin.client.mock.JMockit;
import io.tiklab.postin.config.TestConfig;
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
public class AssertCasesServiceImplTest {

    private static Logger logger = LoggerFactory.getLogger(AssertCasesServiceImplTest.class);

    @Autowired
    AssertCaseService assertCaseService;

    static String id;

    @Test
    public void test01ForSaveAssertCase() {
        AssertCases assertCases = JMockit.mock(AssertCases.class);

        id = assertCaseService.createAssertCase(assertCases);

        assertNotNull(id);
    }

    @Test
    public void test02ForUpdateAssertCase(){
        AssertCases assertCases = JMockit.mock(AssertCases.class);
        assertCases.setId(id);

        assertCaseService.updateAssertCase(assertCases);
    }

    @Test
    public void test03ForFindAssertCase() {
        AssertCases assertCases = assertCaseService.findAssertCase(id);

        assertNotNull(assertCases);
    }

    @Test
    public void test04ForFindAllAssertCase() {
        List<AssertCases> assertCasesList = assertCaseService.findAllAssertCase();

        assertNotNull(assertCasesList);
    }

    @Test
    public void test05ForDeleteAssertCase(){
        assertCaseService.deleteAssertCase(id);
    }
}