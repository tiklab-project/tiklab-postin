package com.tiklab.postin.workspace.service;

import com.tiklab.postin.workspace.model.WorkspaceFollow;
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
public class WorkspaceFollowServiceImplTest {

    private static Logger logger = LoggerFactory.getLogger(WorkspaceFollowServiceImplTest.class);

    @Autowired
    WorkspaceFollowService workspaceFollowService;

    static String id;

    @Test
    public void test01ForSaveWorkspaceFollow() {
        WorkspaceFollow workspaceFollow = JMockit.mock(WorkspaceFollow.class);

        id = workspaceFollowService.createWorkspaceFollow(workspaceFollow);

        assertNotNull(id);
    }

    @Test
    public void test02ForUpdateWorkspaceFollow(){
        WorkspaceFollow workspaceFollow = JMockit.mock(WorkspaceFollow.class);
        workspaceFollow.setId(id);

        workspaceFollowService.updateWorkspaceFollow(workspaceFollow);
    }

    @Test
    public void test03ForFindWorkspaceFollow() {
        WorkspaceFollow workspaceFollow = workspaceFollowService.findWorkspaceFollow(id);

        assertNotNull(workspaceFollow);
    }

    @Test
    public void test04ForFindAllWorkspaceFollow() {
        List<WorkspaceFollow> workspaceFollowList = workspaceFollowService.findAllWorkspaceFollow();

        assertNotNull(workspaceFollowList);
    }

    @Test
    public void test05ForDeleteWorkspaceFollow(){
        workspaceFollowService.deleteWorkspaceFollow(id);
    }
}