package net.tiklab.postin.apidef.http.service;

import net.tiklab.postin.apidef.http.definition.model.QueryParam;
import net.tiklab.postin.apidef.http.definition.service.QueryParamService;
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
public class QueryParamServiceImplTest {

    private static Logger logger = LoggerFactory.getLogger(QueryParamServiceImplTest.class);

    @Autowired
    QueryParamService queryParamService;

    static String id;

    @Test
    public void test01ForSaveQueryParam() {
        QueryParam queryParam = JMockit.mock(QueryParam.class);

        id = queryParamService.createQueryParam(queryParam);

        assertNotNull(id);
    }

    @Test
    public void test02ForUpdateQueryParam(){
        QueryParam queryParam = JMockit.mock(QueryParam.class);
        queryParam.setId(id);

        queryParamService.updateQueryParam(queryParam);
    }

    @Test
    public void test03ForFindQueryParam() {
        QueryParam queryParam = queryParamService.findQueryParam(id);

        assertNotNull(queryParam);
    }

    @Test
    public void test04ForFindAllQueryParam() {
        List<QueryParam> queryParamList = queryParamService.findAllQueryParam();

        assertNotNull(queryParamList);
    }

    @Test
    public void test05ForDeleteQueryParam(){
        queryParamService.deleteQueryParam(id);
    }
}