package org.example;

import org.example.service.StateMachineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PersistApplication implements CommandLineRunner {

    @Autowired
    private StateMachineService stateMachineService;

    public static void main(String[] args) {
        SpringApplication.run(PersistApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        stateMachineService.create();

        stateMachineService.getState("stateMachine-01");
        stateMachineService.getState("stateMachine-02");
    }
}