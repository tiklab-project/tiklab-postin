package com.tiklab.postin.workspace.service;

import com.tiklab.postin.workspace.model.Workspace;
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
public class WorkspaceServiceImplTest {

    private static Logger logger = LoggerFactory.getLogger(WorkspaceServiceImplTest.class);

    @Autowired
    WorkspaceService workspaceService;

    static String id;

    @Test
    public void test01ForSaveWorkspace() {
        Workspace workspace = JMockit.mock(Workspace.class);

        id = workspaceService.createWorkspace(workspace);

        assertNotNull(id);
    }

    @Test
    public void test02ForUpdateWorkspace(){
        Workspace workspace = JMockit.mock(Workspace.class);
        workspace.setId(id);

        workspaceService.updateWorkspace(workspace);
    }

    @Test
    public void test03ForFindWorkspace() {
        Workspace workspace = workspaceService.findWorkspace(id);

        assertNotNull(workspace);
    }

    @Test
    public void test04ForFindAllWorkspace() {
        List<Workspace> workspaceList = workspaceService.findAllWorkspace();

        assertNotNull(workspaceList);
    }

    @Test
    public void test05ForDeleteWorkspace(){
        workspaceService.deleteWorkspace(id);
    }
}