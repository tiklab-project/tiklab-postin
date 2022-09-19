package net.tiklab.postin.apitest.http.httpcase.service;

import net.tiklab.postin.apitest.http.httpcase.model.RequestCase;
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
public class RequestCaseServiceImplTest {

    private static Logger logger = LoggerFactory.getLogger(RequestCaseServiceImplTest.class);

    @Autowired
    RequestCaseService requestCaseService;

    static String id;

    @Test
    public void test01ForSaveRequestCase() {
        RequestCase requestCase = JMockit.mock(RequestCase.class);

        id = requestCaseService.createRequestCase(requestCase);

        assertNotNull(id);
    }

    @Test
    public void test02ForUpdateRequestCase(){
        RequestCase requestCase = JMockit.mock(RequestCase.class);
        requestCase.setId(id);

        requestCaseService.updateRequestCase(requestCase);
    }

    @Test
    public void test03ForFindRequestCase() {
        RequestCase requestCase = requestCaseService.findRequestCase(id);

        assertNotNull(requestCase);
    }

    @Test
    public void test04ForFindAllRequestCase() {
        List<RequestCase> requestCaseList = requestCaseService.findAllRequestCase();

        assertNotNull(requestCaseList);
    }

    @Test
    public void test05ForDeleteRequestCase(){
        requestCaseService.deleteRequestCase(id);
    }
}