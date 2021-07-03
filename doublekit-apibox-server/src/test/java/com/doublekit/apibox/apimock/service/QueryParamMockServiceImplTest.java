package com.doublekit.apibox.apimock.service;

import com.doublekit.apibox.apimock.model.QueryParamMock;
import com.doublekit.apibox.client.mock.JMockit;
import com.doublekit.apibox.config.TestConfig;
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
public class QueryParamMockServiceImplTest {

    private static Logger logger = LoggerFactory.getLogger(QueryParamMockServiceImplTest.class);

    @Autowired
    QueryParamMockService queryParamMockService;

    static String id;

    @Test
    public void test01ForSaveQueryParamMock() {
        QueryParamMock queryParamMock = JMockit.mock(QueryParamMock.class);

        id = queryParamMockService.createQueryParamMock(queryParamMock);

        assertNotNull(id);
    }

    @Test
    public void test02ForUpdateQueryParamMock(){
        QueryParamMock queryParamMock = JMockit.mock(QueryParamMock.class);
        queryParamMock.setId(id);

        queryParamMockService.updateQueryParamMock(queryParamMock);
    }

    @Test
    public void test03ForFindQueryParamMock() {
        QueryParamMock queryParamMock = queryParamMockService.findQueryParamMock(id);

        assertNotNull(queryParamMock);
    }

    @Test
    public void test04ForFindAllQueryParamMock() {
        List<QueryParamMock> queryParamMockList = queryParamMockService.findAllQueryParamMock();

        assertNotNull(queryParamMockList);
    }

    @Test
    public void test05ForDeleteQueryParamMock(){
        queryParamMockService.deleteQueryParamMock(id);
    }
}