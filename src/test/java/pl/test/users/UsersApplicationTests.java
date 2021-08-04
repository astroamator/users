package pl.test.users;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.test.users.controllers.UserController;
import pl.test.users.repositories.RequestLogRepository;

@SpringBootTest
class UsersApplicationTests {

    @Autowired
    UserController userController;

    @Autowired
    RequestLogRepository loginRequestLogRepository;

    @Test
    void contextLoads() {
        Assertions.assertThat(loginRequestLogRepository).isNotNull();
        Assertions.assertThat(userController).isNotNull();
    }

}
