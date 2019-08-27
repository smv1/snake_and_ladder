package com.snakeladder.game.snakeladder.snapshot;

import com.snakeladder.game.snakeladder.core.Board;
import com.snakeladder.game.snakeladder.core.Player;
import org.springframework.stereotype.Component;

/**
 * Core purpose to segregate snapshot/output of game
 */
@Component
public interface GameSnapShot {

    void snapGameInit(Board board);

    void postGame(String gameId, Player s);

    void snapShot(String gameId, Player player, int roll, Integer oldPos, int pos);

    void snapLadder(String gameId, int from, int to);

    void snapSnake(String gameId, int from, int to);

    void snapPlayerBeat(String gameId, Player currplayer, Player player);
}
