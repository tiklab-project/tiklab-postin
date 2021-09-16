package com.doublekit.apibox.node.service;

import com.doublekit.apibox.node.model.Node;
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
public class NodeServiceImplTest {

    private static Logger logger = LoggerFactory.getLogger(NodeServiceImplTest.class);

    @Autowired
    NodeService nodeService;

    static String id;

    @Test
    public void test01ForSaveNode() {
        Node node = JMockit.mock(Node.class);

        id = nodeService.createNode(node);

        assertNotNull(id);
    }

    @Test
    public void test02ForUpdateNode(){
        Node node = JMockit.mock(Node.class);
        node.setId(id);

        nodeService.updateNode(node);
    }

    @Test
    public void test03ForFindNode() {
        Node node = nodeService.findNode(id);

        assertNotNull(node);
    }

    @Test
    public void test04ForFindAllNode() {
        List<Node> nodeList = nodeService.findAllNode();

        assertNotNull(nodeList);
    }

    @Test
    public void test05ForDeleteNode(){
        nodeService.deleteNode(id);
    }
}