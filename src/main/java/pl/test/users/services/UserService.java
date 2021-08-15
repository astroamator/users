/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.test.users.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.test.users.clients.GitHubUserClient;
import pl.test.users.clients.GitHubUserResponse;
import pl.test.users.entities.RequestLog;
import pl.test.users.repositories.RequestLogRepository;

/**
 * @author rober
 */
@Slf4j
@Service
public class UserService {

    @Autowired
    private GitHubUserClient userClient;

    @Autowired
    private RequestLogRepository loginRequestLogRepository;

    public ResponseEntity<GitHubUserResponse> getUserData(String login) throws UserServiceException {
        try {
            GitHubUserResponse response = userClient.getUserDataByLogin(login);
            incrementLoginRequestCounter(login);
            return ResponseEntity.ok(response);
        } catch (Exception ex) {
            log.error(ex.getMessage());
            throw new UserServiceException(ex.getMessage());
        }
    }

    private void incrementLoginRequestCounter(String login) {
        RequestLog requestLog = loginRequestLogRepository.findById(login)
                .orElse(RequestLog.builder()
                        .login(login)
                        .build()
                );

        requestLog.incrementCounter();
        loginRequestLogRepository.save(requestLog);
    }

}
