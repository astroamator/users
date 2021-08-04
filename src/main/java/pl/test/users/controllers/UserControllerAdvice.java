/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.test.users.controllers;

import pl.test.users.services.UserServiceException;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 *
 * @author rober
 */
@RestControllerAdvice
public class UserControllerAdvice {

    @Autowired
    private MessageSource businessMessageSource;

    @ResponseStatus(HttpStatus.GATEWAY_TIMEOUT)
    @ExceptionHandler(UserServiceException.class)
    public ResponseEntity<?> handleUserServiceException(UserServiceException ex) {
        Map<String, Object> errors = new HashMap<>();
        String msg = businessMessageSource.getMessage(ex.getMessage(), ex.getObjects(), LocaleContextHolder.getLocale());
        errors.put("message", msg);
        return new ResponseEntity(createMessage(errors), HttpStatus.GATEWAY_TIMEOUT);
    }

    private Map<String, Object> createMessage(Map<String, Object> messagesMap) {
        Map<String, Object> message = new HashMap<>();
        message.put("timestamp", Instant.now().toString());
        message.put("error", messagesMap);
        return message;
    }
}
