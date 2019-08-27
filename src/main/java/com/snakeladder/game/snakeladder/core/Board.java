package com.snakeladder.game.snakeladder.core;

import com.snakeladder.game.snakeladder.snapshot.GameSnapShot;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

// Complete picture of game going on board
@Getter
public class Board {

    private static final Integer start = 1;

    private final Map<Integer, Integer> ladder;
    private final Map<Integer, Integer> snakes;
    private final int squares;
    private final String gameId;
    GameSnapShot gameSnapShot;
    private Map<Player, Integer> playerPos;

    public Board(Map<Integer, Integer> ladder, Map<Integer, Integer> snakes, int squares, String gameId, GameSnapShot gameSnapShot) {
        this.ladder = ladder;
        this.snakes = snakes;
        this.squares = squares;
        this.gameId = gameId;
        this.playerPos = new HashMap<>();
        this.gameSnapShot = gameSnapShot;
    }

    public static BoardBuilder builder() {
        return new BoardBuilder();
    }

    public void placePlayersAtStart(Queue<Player> players) {
        for (Player player : players) {
            playerPos.put(player, start);
        }
    }

    /*
        making the player move, takes in account snakes and ladder
        Also beat other player incase at same position
     */
    public int makePlayerMove(Player currplayer, int roll) {
        int oldPos = playerPos.get(currplayer);
        int newPos = oldPos + roll;

        if (isValid(newPos)) {
            if (ladder.containsKey(newPos)) {
                gameSnapShot.snapLadder(gameId, newPos, ladder.get(newPos));
                newPos = ladder.get(newPos);
            } else if (snakes.containsKey(newPos)) {
                gameSnapShot.snapSnake(gameId, newPos, snakes.get(newPos));
                newPos = snakes.get(newPos);
            }

            checkPlayersAndMove(currplayer, playerPos, newPos);
            playerPos.put(currplayer, newPos);
        } else {
            newPos = oldPos;
        }

        gameSnapShot.snapShot(gameId, currplayer, roll, oldPos, newPos);
        return newPos;
    }

    //move other player at new pos to start as these are hit
    private void checkPlayersAndMove(Player currplayer, Map<Player, Integer> playerPos, int newPos) {

        for (Map.Entry<Player, Integer> pair : playerPos.entrySet()) {
            if (pair.getKey() != currplayer && pair.getValue() == newPos) {
                pair.setValue(start);
                gameSnapShot.snapPlayerBeat(gameId, currplayer, pair.getKey());
            }
        }
    }

    private boolean isValid(int newPos) {
        return newPos <= squares;
    }

    public String getGameInitSnap() {
        StringBuilder sb = new StringBuilder();
        sb.append("\nGameId = ")
                .append(gameId)
                .append("\nBoard squares = ")
                .append(squares)
                .append("\nLadders\n");
        for (Map.Entry<Integer, Integer> pair : ladder.entrySet()) {
            sb.append(pair.getKey())
                    .append(" ")
                    .append(pair.getValue())
                    .append("\n");
        }
        sb.append("Snakes\n");
        for (Map.Entry<Integer, Integer> pair : snakes.entrySet()) {
            sb.append(pair.getKey())
                    .append(" ")
                    .append(pair.getValue())
                    .append("\n");
        }
        sb.append("Players\n");
        for (Map.Entry<Player, Integer> pair : playerPos.entrySet()) {
            sb.append(pair.getKey())
                    .append(" ")
                    .append(pair.getValue())
                    .append("\n");
        }
        return sb.toString();
    }

}
