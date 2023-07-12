package io.tiklab.postin.workspace.controller;

import io.tiklab.core.utils.MapUtils;
import io.tiklab.core.Result;
import io.tiklab.postin.client.mock.JMockit;
import io.tiklab.postin.workspace.model.Workspace;
import org.aspectj.lang.annotation.Before;

  //import org.junit.FixMethodOrder;
//import org.junit.runners.MethodSorters;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;

//import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


//@SpringBootTest(classes = {TestConfig.class})
@WebAppConfiguration
@Transactional
@Rollback(false)
//@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class WorkspaceControllerTest {

    private static Logger logger = LoggerFactory.getLogger(WorkspaceControllerTest.class);

    static String id;

    protected MockMvc mockMvc;

    @Autowired
    protected WebApplicationContext wac;

    @Before("")
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();  //初始化MockMvc对象
    }

    @Test
    public void test01ForSaveWorkspace() {
        Workspace workspace = JMockit.mock(Workspace.class);

        MultiValueMap<String, String> multiValueMap = MapUtils.toMultiMap(workspace);

        try {
            MvcResult mvcResult = mockMvc.perform(
                                post("/workspace/createWorkspace")
                                .params(multiValueMap)
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                    )
                    .andExpect(status().isOk())
                    .andDo(print())
                    .andReturn();

            Result result = (Result) mvcResult.getModelAndView().getModel().get("result");
            //assertNotNull(result);
  // assertEquals(result.getCode(),0);
            id = String.valueOf(result.getData());
        } catch (Exception e) {
              //fail(e.getMessage());
        }
    }

    @Test
    public void test02ForUpdateWorkspace(){
        Workspace workspace = JMockit.mock(Workspace.class);
        workspace.setId(id);

        MultiValueMap<String, String> multiValueMap = MapUtils.toMultiMap(workspace);

        try {
            MvcResult mvcResult = mockMvc.perform(
                                post("/workspace/updateWorkspace")
                                .params(multiValueMap)
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                    )
                    .andExpect(status().isOk())
                    .andDo(print())
                    .andReturn();

            Result result = (Result) mvcResult.getModelAndView().getModel().get("result");
            //assertNotNull(result);
  // assertEquals(result.getCode(),0);
        } catch (Exception e) {
              //fail(e.getMessage());
        }
    }

    @Test
    public void test03ForFindWorkspace() {
        try {
            MvcResult mvcResult = mockMvc.perform(
                    post("/workspace/findWorkspace")
                            .param("id",id)
                            .accept(MediaType.APPLICATION_JSON)
                            .contentType(MediaType.APPLICATION_JSON)
            )
                    .andExpect(status().isOk())
                    .andDo(print())
                    .andReturn();

            Result result = (Result) mvcResult.getModelAndView().getModel().get("result");
            //assertNotNull(result);
  // assertEquals(result.getCode(),0);
        } catch (Exception e) {
              //fail(e.getMessage());
        }
    }

    @Test
    public void test04ForFindAllWorkspace() {
        try {
            MvcResult mvcResult = mockMvc.perform(
                    post("/workspace/findAllWorkspace")
                            .accept(MediaType.APPLICATION_JSON)
                            .contentType(MediaType.APPLICATION_JSON)
            )
                    .andExpect(status().isOk())
                    .andDo(print())
                    .andReturn();

            Result result = (Result) mvcResult.getModelAndView().getModel().get("result");
            //assertNotNull(result);
  // assertEquals(result.getCode(),0);
        } catch (Exception e) {
              //fail(e.getMessage());
        }
    }

    @Test
    public void test05ForDeleteWorkspace(){
        try {
            MvcResult mvcResult = mockMvc.perform(
                    post("/workspace/deleteWorkspace")
                            .param("id",id)
                            .accept(MediaType.APPLICATION_JSON)
                            .contentType(MediaType.APPLICATION_JSON)
            )
                    .andExpect(status().isOk())
                    .andDo(print())
                    .andReturn();

            Result result = (Result) mvcResult.getModelAndView().getModel().get("result");
            //assertNotNull(result);
  // assertEquals(result.getCode(),0);
        } catch (Exception e) {
              //fail(e.getMessage());
        }
    }
}