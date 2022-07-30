package com.tiklab.postin.sysmgr.apistatus.service;

import com.tiklab.postin.sysmgr.apistatus.model.ApiStatus;
import com.tiklab.postin.client.mock.JMockit;
import com.tiklab.postin.config.TestConfig;
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
public class ApiStatusServiceImplTest {

    private static Logger logger = LoggerFactory.getLogger(ApiStatusServiceImplTest.class);

    @Autowired
    ApiStatusService apiStatusService;

    static String id;

    @Test
    public void test01ForSaveApiStatus() {
        ApiStatus apiStatus = JMockit.mock(ApiStatus.class);

        id = apiStatusService.createApiStatus(apiStatus);

        assertNotNull(id);
    }

    @Test
    public void test02ForUpdateApiStatus(){
        ApiStatus apiStatus = JMockit.mock(ApiStatus.class);
        apiStatus.setId(id);

        apiStatusService.updateApiStatus(apiStatus);
    }

    @Test
    public void test03ForFindApiStatus() {
        ApiStatus apiStatus = apiStatusService.findApiStatus(id);

        assertNotNull(apiStatus);
    }

    @Test
    public void test04ForFindAllApiStatus() {
        List<ApiStatus> apiStatusList = apiStatusService.findAllApiStatus();

        assertNotNull(apiStatusList);
    }

    @Test
    public void test05ForDeleteApiStatus(){
        apiStatusService.deleteApiStatus(id);
    }
}