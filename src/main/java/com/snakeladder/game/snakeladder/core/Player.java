package com.snakeladder.game.snakeladder.core;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter @AllArgsConstructor
public class Player {
    String name;

    @Override
    public String toString() {
        return name;
    }
}
