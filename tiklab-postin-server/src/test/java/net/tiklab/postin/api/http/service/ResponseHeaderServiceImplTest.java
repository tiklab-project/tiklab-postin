package net.tiklab.postin.api.http.service;

import net.tiklab.postin.api.http.definition.model.ResponseHeader;
import net.tiklab.postin.api.http.definition.service.ResponseHeaderService;
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
public class ResponseHeaderServiceImplTest {

    private static Logger logger = LoggerFactory.getLogger(ResponseHeaderServiceImplTest.class);

    @Autowired
    ResponseHeaderService responseHeaderService;

    static String id;

    @Test
    public void test01ForSaveResponseHeader() {
        ResponseHeader responseHeader = JMockit.mock(ResponseHeader.class);

        id = responseHeaderService.createResponseHeader(responseHeader);

        assertNotNull(id);
    }

    @Test
    public void test02ForUpdateResponseHeader(){
        ResponseHeader responseHeader = JMockit.mock(ResponseHeader.class);
        responseHeader.setId(id);

        responseHeaderService.updateResponseHeader(responseHeader);
    }

    @Test
    public void test03ForFindResponseHeader() {
        ResponseHeader responseHeader = responseHeaderService.findResponseHeader(id);

        assertNotNull(responseHeader);
    }

    @Test
    public void test04ForFindAllResponseHeader() {
        List<ResponseHeader> responseHeaderList = responseHeaderService.findAllResponseHeader();

        assertNotNull(responseHeaderList);
    }

    @Test
    public void test05ForDeleteResponseHeader(){
        responseHeaderService.deleteResponseHeader(id);
    }
}