package com.example.simple.springboot.project.integration;

import com.auth0.exception.Auth0Exception;
import com.auth0.json.auth.UserInfo;
import com.auth0.net.Request;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Collections;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class MainControllerTest {

    @Autowired
    private WebApplicationContext context;
    @Mock
    private Request<UserInfo> userInfoRequest;
    @Mock
    private UserInfo userInfo;
    @Autowired
    private MockMvc mockMvc;

    @Before
    public void setUp() throws Auth0Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).apply(springSecurity()).build();
        MockitoAnnotations.initMocks(this);
        when(userInfoRequest.execute()).thenReturn(userInfo);
        when(userInfo.getValues()).thenReturn(Collections.<String, Object>singletonMap("sub", "google-oauth2|102846717547482980745"));
    }

    @Test
    @WithMockUser
    public void shouldReturnSectors() throws Exception {
        this.mockMvc.perform(get("/api/sectors")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Manufacturing")));
    }

    @Test
    @WithMockUser
    public void shouldReturnFormInfo() throws Exception {
        this.mockMvc.perform(get("/api/forminfo").sessionAttr("userInfo", userInfo)).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Daria")));
    }
}