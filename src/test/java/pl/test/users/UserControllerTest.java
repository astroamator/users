/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.test.users;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.junit.jupiter.api.Assertions.*;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import pl.test.users.clients.GitHubUserResponse;
import pl.test.users.controllers.UserController;
import pl.test.users.entities.RequestLog;
import pl.test.users.repositories.RequestLogRepository;

/**
 *
 * @author rober
 */
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:tests.properties")
public class UserControllerTest {

    private static final String TEST_LOGIN = "test_login";

    @Autowired
    UserController userController;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    RequestLogRepository loginRequestLogRepository;

    @Test
    public void test_whenValidRequest_ShouldReturnHttpOk() throws Exception {
        mockMvc.perform(get("/users/octocat"))
                .andExpect(status().is(200))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.login", Matchers.is("octocat")));
    }

    @Test
    public void test_whenInvalidRequest_ShouldReturnHttpNotFound() throws Exception {
        mockMvc.perform(get("/users"))
                .andExpect(status().is(404));
    }

    @Test
    public void test_Persistence() {
        RequestLog loginRequestLog = new RequestLog(TEST_LOGIN);
        loginRequestLogRepository.save(loginRequestLog);
        RequestLog loginRequestLogFound = loginRequestLogRepository.findByLogin(TEST_LOGIN).orElse(null);

        assertNotNull(loginRequestLogFound);
        assertEquals(TEST_LOGIN, loginRequestLogFound.getLogin());
        assertEquals(0, loginRequestLogFound.getCount());
    }

    @Test
    public void test_EntityCounter() {
        RequestLog requestLog = RequestLog.builder()
                .login(TEST_LOGIN)
                .build();
        
        assertNotNull(requestLog);
        assertEquals(0, requestLog.getCount());
        
        requestLog.incrementCounter();
        assertEquals(1, requestLog.getCount());
    }
    
    @Test
    public void test_Dto() {
        GitHubUserResponse gitHubUserResponse = GitHubUserResponse.builder()
                .followers(2)
                .public_repos(1)
                .build();
        assertNotNull(gitHubUserResponse);
        assertEquals(9, gitHubUserResponse.calculations());
    }
}
