package com.snakeladder.game.snakeladder.util;

public class GameUtil {

    public static int random(int min, int max) {
        return (int) (min + Math.round((max - min) * Math.random()));
    }
}
