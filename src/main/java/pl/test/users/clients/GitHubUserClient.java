/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.test.users.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author rober
 */
@FeignClient(value = "userclient", url = "${app.github.url}")
public interface GitHubUserClient {

    @RequestMapping(method = RequestMethod.GET, value = "${app.github.endpoints.users}/{login}")
    public GitHubUserResponse getUserDataByLogin(@PathVariable("login") String login);

}
