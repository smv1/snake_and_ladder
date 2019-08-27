package com.snakeladder.game.snakeladder.core;

import com.snakeladder.game.snakeladder.snapshot.GameSnapShot;

import java.util.Map;

public class BoardBuilder {
    private Map<Integer, Integer> ladder;
    private Map<Integer, Integer> snakes;
    private int squares;
    private Map<Player, Integer> playerPos;
    private String gameId;
    private GameSnapShot gameSnapShot;

    BoardBuilder() {
    }

    public BoardBuilder ladder(Map<Integer, Integer> ladder) {
        this.ladder = ladder;
        return this;
    }

    public BoardBuilder snakes(Map<Integer, Integer> snakes) {
        this.snakes = snakes;
        return this;
    }

    public BoardBuilder squares(int squares) {
        this.squares = squares;
        return this;
    }

    public BoardBuilder gameId(String gameId) {
        this.gameId = gameId;
        return this;
    }

    public BoardBuilder gameSnapShot(GameSnapShot gameSnapShot) {
        this.gameSnapShot = gameSnapShot;
        return this;
    }

    public Board build() {
        return new Board(ladder, snakes, squares, gameId, gameSnapShot);
    }

    public String toString() {
        return "Board.BoardBuilder(ladder=" + this.ladder + ", snakes=" + this.snakes + ", squares=" + this.squares + ", playerPos=" + this.playerPos + ")";
    }
}