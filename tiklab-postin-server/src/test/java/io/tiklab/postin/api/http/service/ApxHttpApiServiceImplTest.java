package io.tiklab.postin.api.http.service;

import io.tiklab.postin.api.http.definition.model.HttpApi;
import io.tiklab.postin.api.http.definition.service.HttpApiService;
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
public class ApxHttpApiServiceImplTest {

    private static Logger logger = LoggerFactory.getLogger(ApxHttpApiServiceImplTest.class);

    @Autowired
    HttpApiService apxHttpApiService;

    static String id;

    @Test
    public void test01ForSaveApxMethod() {
        HttpApi apxMethod = JMockit.mock(HttpApi.class);

        id = apxHttpApiService.createHttpApi(apxMethod);

        assertNotNull(id);
    }

    @Test
    public void test02ForUpdateApxMethod(){
        HttpApi apxMethod = JMockit.mock(HttpApi.class);
        apxMethod.setId(id);

        apxHttpApiService.updateHttpApi(apxMethod);
    }

    @Test
    public void test03ForFindApxMethod() {
        HttpApi apxMethod = apxHttpApiService.findHttpApi(id);

        assertNotNull(apxMethod);
    }

    @Test
    public void test04ForFindAllApxMethod() {
        List<HttpApi> apxMethodList = apxHttpApiService.findAllHttpApi();

        assertNotNull(apxMethodList);
    }

    @Test
    public void test05ForDeleteApxMethod(){
        apxHttpApiService.deleteHttpApi(id);
    }
}