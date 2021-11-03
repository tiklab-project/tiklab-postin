package com.doublekit.apibox.sysmgr.datastructure.service;

import com.doublekit.apibox.sysmgr.datastructure.model.DataStructure;
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
public class DataStructureServiceImplTest {

    private static Logger logger = LoggerFactory.getLogger(DataStructureServiceImplTest.class);

    @Autowired
    DataStructureService dataStructureService;

    static String id;

    @Test
    public void test01ForSaveDataStructure() {
        DataStructure dataStructure = JMockit.mock(DataStructure.class);

        id = dataStructureService.createDataStructure(dataStructure);

        assertNotNull(id);
    }

    @Test
    public void test02ForUpdateDataStructure(){
        DataStructure dataStructure = JMockit.mock(DataStructure.class);
        dataStructure.setId(id);

        dataStructureService.updateDataStructure(dataStructure);
    }

    @Test
    public void test03ForFindDataStructure() {
        DataStructure dataStructure = dataStructureService.findDataStructure(id);

        assertNotNull(dataStructure);
    }

    @Test
    public void test04ForFindAllDataStructure() {
        List<DataStructure> dataStructureList = dataStructureService.findAllDataStructure();

        assertNotNull(dataStructureList);
    }

    @Test
    public void test05ForDeleteDataStructure(){
        dataStructureService.deleteDataStructure(id);
    }
}