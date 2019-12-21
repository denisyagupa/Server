package hello.controllers;

import hello.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class TestConfig {


    @Autowired
    private UserRepository userRepository;

    @Bean
    public LoginController getLoginController(){
        return new LoginController(userRepository);
    }
}
