/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.test.users.entities;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author rober
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
public class RequestLog {

    @Id
    @Column(name = "login", nullable = false, unique = true)
    private String login;

    @Column(name = "request_count", nullable = false)
    private long count;

    @Builder
    public RequestLog(String login) {
        this.login = login;
    }

    public void incrementCounter() {
        this.count++;
    }
}
