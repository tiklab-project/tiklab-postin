package com.doublekit.apibox.apimock.controller;

import com.doublekit.apibox.apimock.model.RequestBodyMock;
import com.doublekit.apibox.client.mock.JMockit;
import com.doublekit.apibox.config.TestConfig;
import com.doublekit.common.Result;
import com.doublekit.utils.MapUtils;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
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

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {TestConfig.class})
@WebAppConfiguration
@Transactional
@Rollback(false)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RequestBodyMockControllerTest {

    private static Logger logger = LoggerFactory.getLogger(RequestBodyMockControllerTest.class);

    static String id;

    protected MockMvc mockMvc;

    @Autowired
    protected WebApplicationContext wac;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();  //初始化MockMvc对象
    }

    @Test
    public void test01ForSaveRequestBodyMock() {
        RequestBodyMock requestBodyMock = JMockit.mock(RequestBodyMock.class);

        MultiValueMap<String, String> multiValueMap = MapUtils.toMultiMap(requestBodyMock);

        try {
            MvcResult mvcResult = mockMvc.perform(
                                post("/requestBodyMock/createRequestBodyMock")
                                .params(multiValueMap)
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                    )
                    .andExpect(status().isOk())
                    .andDo(print())
                    .andReturn();

            Result result = (Result) mvcResult.getModelAndView().getModel().get("result");
            assertNotNull(result);
            assertEquals(result.getCode(),0);
            id = String.valueOf(result.getData());
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void test02ForUpdateRequestBodyMock(){
        RequestBodyMock requestBodyMock = JMockit.mock(RequestBodyMock.class);
        requestBodyMock.setId(id);

        MultiValueMap<String, String> multiValueMap = MapUtils.toMultiMap(requestBodyMock);

        try {
            MvcResult mvcResult = mockMvc.perform(
                                post("/requestBodyMock/updateRequestBodyMock")
                                .params(multiValueMap)
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                    )
                    .andExpect(status().isOk())
                    .andDo(print())
                    .andReturn();

            Result result = (Result) mvcResult.getModelAndView().getModel().get("result");
            assertNotNull(result);
            assertEquals(result.getCode(),0);
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void test03ForFindRequestBodyMock() {
        try {
            MvcResult mvcResult = mockMvc.perform(
                    post("/requestBodyMock/findRequestBodyMock")
                            .param("id",id)
                            .accept(MediaType.APPLICATION_JSON)
                            .contentType(MediaType.APPLICATION_JSON)
            )
                    .andExpect(status().isOk())
                    .andDo(print())
                    .andReturn();

            Result result = (Result) mvcResult.getModelAndView().getModel().get("result");
            assertNotNull(result);
            assertEquals(result.getCode(),0);
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void test04ForFindAllRequestBodyMock() {
        try {
            MvcResult mvcResult = mockMvc.perform(
                    post("/requestBodyMock/findAllRequestBodyMock")
                            .accept(MediaType.APPLICATION_JSON)
                            .contentType(MediaType.APPLICATION_JSON)
            )
                    .andExpect(status().isOk())
                    .andDo(print())
                    .andReturn();

            Result result = (Result) mvcResult.getModelAndView().getModel().get("result");
            assertNotNull(result);
            assertEquals(result.getCode(),0);
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void test05ForDeleteRequestBodyMock(){
        try {
            MvcResult mvcResult = mockMvc.perform(
                    post("/requestBodyMock/deleteRequestBodyMock")
                            .param("id",id)
                            .accept(MediaType.APPLICATION_JSON)
                            .contentType(MediaType.APPLICATION_JSON)
            )
                    .andExpect(status().isOk())
                    .andDo(print())
                    .andReturn();

            Result result = (Result) mvcResult.getModelAndView().getModel().get("result");
            assertNotNull(result);
            assertEquals(result.getCode(),0);
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }
}