package io.tiklab.postin.apitest.http.httpcase.service;

import io.tiklab.postin.api.http.test.instance.model.RequestInstances;
import io.tiklab.postin.api.http.test.instance.service.RequestInstanceService;
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
public class RequestInstancesServiceImplTest {

    private static Logger logger = LoggerFactory.getLogger(RequestInstancesServiceImplTest.class);

    @Autowired
    RequestInstanceService requestInstanceService;

    static String id;

    @Test
    public void test01ForSaveRequestInstance() {
        RequestInstances requestInstances = JMockit.mock(RequestInstances.class);

        id = requestInstanceService.createRequestInstance(requestInstances);

        assertNotNull(id);
    }

    @Test
    public void test02ForUpdateRequestInstance(){
        RequestInstances requestInstances = JMockit.mock(RequestInstances.class);
        requestInstances.setId(id);

        requestInstanceService.updateRequestInstance(requestInstances);
    }

    @Test
    public void test03ForFindRequestInstance() {
        RequestInstances requestInstances = requestInstanceService.findRequestInstance(id);

        assertNotNull(requestInstances);
    }

    @Test
    public void test04ForFindAllRequestInstance() {
        List<RequestInstances> requestInstancesList = requestInstanceService.findAllRequestInstance();

        assertNotNull(requestInstancesList);
    }

    @Test
    public void test05ForDeleteRequestInstance(){
        requestInstanceService.deleteRequestInstance(id);
    }
}