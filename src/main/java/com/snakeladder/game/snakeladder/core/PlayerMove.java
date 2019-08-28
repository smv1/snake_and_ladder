package com.snakeladder.game.snakeladder.core;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter @AllArgsConstructor
public class PlayerMove {

    int pos;
    boolean isLadderTransition, isSnakeTransition;

}
