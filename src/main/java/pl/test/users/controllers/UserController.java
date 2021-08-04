/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.test.users.controllers;

import pl.test.users.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import pl.test.users.clients.GitHubUserResponse;

/**
 *
 * @author rober
 */
@Tag(name = "users", description = "GitHub User Proxy API")
@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Operation(summary = "Pobiera dane użytkownia GitHub", description = "Pobiera dane użytkownia GitHub na podstawie jego loginu")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Pobrano dane użytkownika"),
        @ApiResponse(responseCode = "404", description = "Nie znaleziono danych"),
        @ApiResponse(responseCode = "504", description = "Błąd w komunikacji z serwisem zewnętrzenym")
    })
    @GetMapping(value = "{login}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    private ResponseEntity<GitHubUserResponse> getUser(
            @Parameter(description = "Login użytkownika", required = true)
            @PathVariable(value = "login", required = true) String login
    ) {
        return userService.getUserData(login);
    }

}
