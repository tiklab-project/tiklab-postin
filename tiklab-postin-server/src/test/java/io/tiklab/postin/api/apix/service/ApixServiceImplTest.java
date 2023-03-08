package io.tiklab.postin.api.apix.service;

import io.tiklab.postin.api.apix.model.Apix;
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
public class ApixServiceImplTest {

    private static Logger logger = LoggerFactory.getLogger(ApixServiceImplTest.class);

    @Autowired
    ApixService apixService;

    static String id;

    @Test
    public void test01ForSaveBasedef() {
        Apix apix = JMockit.mock(Apix.class);

        id = apixService.createApix(apix);

        assertNotNull(id);
    }

    @Test
    public void test02ForupdateApix(){
        Apix apix = JMockit.mock(Apix.class);
        apix.setId(id);

        apixService.updateApix(apix);
    }

    @Test
    public void test03ForfindApix() {
        Apix apix = apixService.findApix(id);

        assertNotNull(apix);
    }

    @Test
    public void test04ForfindAllApix() {
        List<Apix> apixList = apixService.findAllApix();

        assertNotNull(apixList);
    }

    @Test
    public void test05FordeleteApix(){
        apixService.deleteApix(id);
    }
}