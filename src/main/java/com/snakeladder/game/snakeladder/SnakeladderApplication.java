package com.snakeladder.game.snakeladder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SnakeladderApplication {

    @Autowired
    static SnakeLadderGameSimulator snakeLadderGameSimulator;

    public static void main(String[] args) {
        SpringApplication.run(SnakeladderApplication.class, args);
    }

}
