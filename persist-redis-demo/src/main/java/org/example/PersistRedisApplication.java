package org.example;

import org.example.redis_memory.service.StateMachineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PersistRedisApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(PersistRedisApplication.class, args);
    }


    @Autowired
    private StateMachineService stateMachineService;

    @Override
    public void run(String... args) throws Exception {
        stateMachineService.create();

        stateMachineService.getState("stateMachine-01");
        stateMachineService.getState("stateMachine-02");
    }
}