package com.snakeladder.game.snakeladder.snapshot;

import com.snakeladder.game.snakeladder.core.Board;
import com.snakeladder.game.snakeladder.core.Player;
import org.springframework.stereotype.Component;

/**
 * Prints Game snapshot
 * Can be replaced with other type
 * Also have gameId
 */
@Component
public class GameSnapShotImpl implements GameSnapShot {

    public void snapGameInit(Board board) {
        System.out.println(board.getGameInitSnap());
    }

    @Override
    public void postGame(String gameId, Player s) {
        System.out.println(gameString(gameId) + s + " has won");
    }

    @Override
    public void snapShot(String gameId, Player player, int roll, Integer oldPos, int pos) {
        System.out.println(gameString(gameId) + player + " roll " + roll + " old = " + oldPos + " new= " + pos);
    }

    @Override
    public void snapLadder(String gameId, int from, int to) {
        System.out.println(gameString(gameId) + "Ladder " + from + " --> " + to);
    }

    @Override
    public void snapSnake(String gameId, int from, int to) {
        System.out.println(gameString(gameId) + "Snake " + from + " --> " + to);
    }

    @Override
    public void snapPlayerBeat(String gameId, Player currplayer, Player player) {
        System.out.println(gameString(gameId) + currplayer + " beats " + player);
    }

    private String gameString(String gameId) {
        return "Game : " + gameId + "  ";
    }
}
