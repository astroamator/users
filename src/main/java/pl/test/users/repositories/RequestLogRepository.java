/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.test.users.repositories;

import java.util.Optional;
import pl.test.users.entities.RequestLog;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author rober
 */
@Repository
public interface RequestLogRepository extends CrudRepository<RequestLog, Integer> {

    public Optional<RequestLog> findByLogin(String login);

}
