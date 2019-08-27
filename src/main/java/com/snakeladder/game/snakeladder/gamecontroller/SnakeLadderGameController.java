package com.snakeladder.game.snakeladder.gamecontroller;

import com.snakeladder.game.snakeladder.core.Player;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Queue;

@Component
public interface SnakeLadderGameController {

    /**
     * Starts game with players
     * @param players
     * @param squares
     * @param snakes
     * @param ladder
     */
    void startGame(Queue<Player> players, int squares, Map<Integer, Integer> snakes, Map<Integer, Integer> ladder);
}
