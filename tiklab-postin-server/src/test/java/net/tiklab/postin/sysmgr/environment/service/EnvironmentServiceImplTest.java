package net.tiklab.postin.sysmgr.environment.service;

import net.tiklab.postin.sysmgr.environment.model.Environment;
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
public class EnvironmentServiceImplTest {

    private static Logger logger = LoggerFactory.getLogger(EnvironmentServiceImplTest.class);

    @Autowired
    EnvironmentService environmentService;

    static String id;

    @Test
    public void test01ForSaveEnvironment() {
        Environment environment = JMockit.mock(Environment.class);

        id = environmentService.createEnvironment(environment);

        assertNotNull(id);
    }

    @Test
    public void test02ForUpdateEnvironment(){
        Environment environment = JMockit.mock(Environment.class);
        environment.setId(id);

        environmentService.updateEnvironment(environment);
    }

    @Test
    public void test03ForFindEnvironment() {
        Environment environment = environmentService.findEnvironment(id);

        assertNotNull(environment);
    }

    @Test
    public void test04ForFindAllEnvironment() {
        List<Environment> environmentList = environmentService.findAllEnvironment();

        assertNotNull(environmentList);
    }

    @Test
    public void test05ForDeleteEnvironment(){
        environmentService.deleteEnvironment(id);
    }
}