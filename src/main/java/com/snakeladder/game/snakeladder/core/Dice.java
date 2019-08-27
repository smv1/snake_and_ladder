package com.snakeladder.game.snakeladder.core;

import com.snakeladder.game.snakeladder.util.GameUtil;
import org.springframework.stereotype.Component;

@Component
public final class Dice {
    public static final int MINVALUE = 1;
    public static final int MAXVALUE = 6;

    public int roll() {
        return GameUtil.random(MINVALUE, MAXVALUE);
    }
}