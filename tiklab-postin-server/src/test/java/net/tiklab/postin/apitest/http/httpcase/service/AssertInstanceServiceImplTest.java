package net.tiklab.postin.apitest.http.httpcase.service;

import net.tiklab.postin.apitest.http.httpinstance.model.AssertInstance;
import net.tiklab.postin.apitest.http.httpinstance.service.AssertInstanceService;
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
public class AssertInstanceServiceImplTest {

    private static Logger logger = LoggerFactory.getLogger(AssertInstanceServiceImplTest.class);

    @Autowired
    AssertInstanceService assertInstanceService;

    static String id;

    @Test
    public void test01ForSaveAssertInstance() {
        AssertInstance assertInstance = JMockit.mock(AssertInstance.class);

        id = assertInstanceService.createAssertInstance(assertInstance);

        assertNotNull(id);
    }

    @Test
    public void test02ForUpdateAssertInstance(){
        AssertInstance assertInstance = JMockit.mock(AssertInstance.class);
        assertInstance.setId(id);

        assertInstanceService.updateAssertInstance(assertInstance);
    }

    @Test
    public void test03ForFindAssertInstance() {
        AssertInstance assertInstance = assertInstanceService.findAssertInstance(id);

        assertNotNull(assertInstance);
    }

    @Test
    public void test04ForFindAllAssertInstance() {
        List<AssertInstance> assertInstanceList = assertInstanceService.findAllAssertInstance();

        assertNotNull(assertInstanceList);
    }

    @Test
    public void test05ForDeleteAssertInstance(){
        assertInstanceService.deleteAssertInstance(id);
    }
}