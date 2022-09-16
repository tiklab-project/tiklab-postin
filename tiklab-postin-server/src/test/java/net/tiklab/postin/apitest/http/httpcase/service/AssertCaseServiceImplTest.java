package net.tiklab.postin.apitest.http.httpcase.service;

import net.tiklab.postin.apitest.http.httpcase.model.AssertCase;
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
public class AssertCaseServiceImplTest {

    private static Logger logger = LoggerFactory.getLogger(AssertCaseServiceImplTest.class);

    @Autowired
    AssertCaseService assertCaseService;

    static String id;

    @Test
    public void test01ForSaveAssertCase() {
        AssertCase assertCase = JMockit.mock(AssertCase.class);

        id = assertCaseService.createAssertCase(assertCase);

        assertNotNull(id);
    }

    @Test
    public void test02ForUpdateAssertCase(){
        AssertCase assertCase = JMockit.mock(AssertCase.class);
        assertCase.setId(id);

        assertCaseService.updateAssertCase(assertCase);
    }

    @Test
    public void test03ForFindAssertCase() {
        AssertCase assertCase = assertCaseService.findAssertCase(id);

        assertNotNull(assertCase);
    }

    @Test
    public void test04ForFindAllAssertCase() {
        List<AssertCase> assertCaseList = assertCaseService.findAllAssertCase();

        assertNotNull(assertCaseList);
    }

    @Test
    public void test05ForDeleteAssertCase(){
        assertCaseService.deleteAssertCase(id);
    }
}