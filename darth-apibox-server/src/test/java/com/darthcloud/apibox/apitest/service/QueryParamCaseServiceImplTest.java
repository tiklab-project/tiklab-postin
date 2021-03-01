package com.darthcloud.apibox.apitest.service;

import com.darthcloud.apibox.apitest.model.QueryParamCase;
import com.darthcloud.apibox.client.mock.JMockit;
import com.darthcloud.apibox.config.TestConfig;
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
public class QueryParamCaseServiceImplTest {

    private static Logger logger = LoggerFactory.getLogger(QueryParamCaseServiceImplTest.class);

    @Autowired
    QueryParamCaseService queryParamCaseService;

    static String id;

    @Test
    public void test01ForSaveQueryParamCase() {
        QueryParamCase queryParamCase = JMockit.mock(QueryParamCase.class);

        id = queryParamCaseService.createQueryParamCase(queryParamCase);

        assertNotNull(id);
    }

    @Test
    public void test02ForUpdateQueryParamCase(){
        QueryParamCase queryParamCase = JMockit.mock(QueryParamCase.class);
        queryParamCase.setId(id);

        queryParamCaseService.updateQueryParamCase(queryParamCase);
    }

    @Test
    public void test03ForFindQueryParamCase() {
        QueryParamCase queryParamCase = queryParamCaseService.findQueryParamCase(id);

        assertNotNull(queryParamCase);
    }

    @Test
    public void test04ForFindAllQueryParamCase() {
        List<QueryParamCase> queryParamCaseList = queryParamCaseService.findAllQueryParamCase();

        assertNotNull(queryParamCaseList);
    }

    @Test
    public void test05ForDeleteQueryParamCase(){
        queryParamCaseService.deleteQueryParamCase(id);
    }
}