/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.test.users.services;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author rober
 */
@Getter
@Setter
public class UserServiceException extends RuntimeException {

    public static final String SERVICE_EXCEPTION_MSG = "service.exception.msg";
    private Object[] objects;

    public UserServiceException(Object... objects) {
        super(SERVICE_EXCEPTION_MSG);
        this.objects = objects;
    }

}
