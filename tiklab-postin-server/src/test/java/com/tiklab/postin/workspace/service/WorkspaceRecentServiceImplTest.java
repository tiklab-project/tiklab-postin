package com.tiklab.postin.workspace.service;

import com.tiklab.postin.workspace.model.WorkspaceRecent;
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
public class WorkspaceRecentServiceImplTest {

    private static Logger logger = LoggerFactory.getLogger(WorkspaceRecentServiceImplTest.class);

    @Autowired
    WorkspaceRecentService workspaceRecentService;

    static String id;

    @Test
    public void test01ForSaveWorkspaceRecent() {
        WorkspaceRecent workspaceRecent = JMockit.mock(WorkspaceRecent.class);

        id = workspaceRecentService.createWorkspaceRecent(workspaceRecent);

        assertNotNull(id);
    }

    @Test
    public void test02ForUpdateWorkspaceRecent(){
        WorkspaceRecent workspaceRecent = JMockit.mock(WorkspaceRecent.class);
        workspaceRecent.setId(id);

        workspaceRecentService.updateWorkspaceRecent(workspaceRecent);
    }

    @Test
    public void test03ForFindWorkspaceRecent() {
        WorkspaceRecent workspaceRecent = workspaceRecentService.findWorkspaceRecent(id);

        assertNotNull(workspaceRecent);
    }

    @Test
    public void test04ForFindAllWorkspaceRecent() {
        List<WorkspaceRecent> workspaceRecentList = workspaceRecentService.findAllWorkspaceRecent();

        assertNotNull(workspaceRecentList);
    }

    @Test
    public void test05ForDeleteWorkspaceRecent(){
        workspaceRecentService.deleteWorkspaceRecent(id);
    }
}