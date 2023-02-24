package net.tiklab.postin.apitest.http.httpcase.service;

import net.tiklab.postin.apidef.http.test.httpinstance.model.HttpInstance;
import net.tiklab.postin.apidef.http.test.httpinstance.service.TestInstanceService;
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
public class HttpInstanceServiceImplHttp {

    private static Logger logger = LoggerFactory.getLogger(HttpInstanceServiceImplHttp.class);

    @Autowired
    TestInstanceService testInstanceService;

    static String id;

    @Test
    public void test01ForSaveTestInstance() {
        HttpInstance httpInstance = JMockit.mock(HttpInstance.class);

        id = testInstanceService.createTestInstance(httpInstance);

        assertNotNull(id);
    }

    @Test
    public void test02ForUpdateTestInstance(){
        HttpInstance httpInstance = JMockit.mock(HttpInstance.class);
        httpInstance.setId(id);

        testInstanceService.updateTestInstance(httpInstance);
    }

    @Test
    public void test03ForFindTestInstance() {
        HttpInstance httpInstance = testInstanceService.findTestInstance(id);

        assertNotNull(httpInstance);
    }

    @Test
    public void test04ForFindAllTestInstance() {
        List<HttpInstance> httpInstanceList = testInstanceService.findAllTestInstance();

        assertNotNull(httpInstanceList);
    }

    @Test
    public void test05ForDeleteTestInstance(){
        testInstanceService.deleteTestInstance(id);
    }
}