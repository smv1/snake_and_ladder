package com.snakeladder.game.snakeladder.gamecontroller;

import com.snakeladder.game.snakeladder.core.Board;
import com.snakeladder.game.snakeladder.core.Dice;
import com.snakeladder.game.snakeladder.core.Player;
import com.snakeladder.game.snakeladder.core.PlayerMove;
import com.snakeladder.game.snakeladder.snapshot.GameSnapShot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Queue;
import java.util.UUID;

@Component
public class SnakeLadderGameControllerImpl implements SnakeLadderGameController {

    Dice dice;
    GameSnapShot gameSnapShot;
    @Value("${game.snakeladder.squares}")
    private int squares;

    @Autowired
    SnakeLadderGameControllerImpl(Dice dice,GameSnapShot gameSnapShot){
        this.dice=dice;
        this.gameSnapShot=gameSnapShot;
    }

    @Override
    public void startGame(Queue<Player> players, int squares, Map<Integer, Integer> snakes, Map<Integer, Integer> ladder) {
        String gameId = UUID.randomUUID().toString();
        Board board = makeBoard(squares, snakes, ladder, gameId, gameSnapShot);
        initGame(board, players);
        gameSnapShot.snapGameInit(board);
        start(gameId, board, players);
    }

    private void initGame(Board board, Queue<Player> players) {
        board.placePlayersAtStart(players);
    }

    /**
     * Start the game and every player move
     *
     * @param gameId
     * @param board
     * @param players
     */
    private void start(String gameId, Board board, Queue<Player> players) {

        Player winner;

        while (true) {
            Player player = players.poll();
            int roll;
            PlayerMove playerMove;

            do {
                roll = dice.roll();
                playerMove = board.makePlayerMove(player, roll);
            } while (roll == Dice.MAXVALUE || playerMove.isLadderTransition());

            if (checkWin(playerMove)) {
                winner = player;
                break;
            }
            players.offer(player);
        }
        postGameComplete(gameId, winner);
    }

    private boolean checkWin(PlayerMove playerMove) {
        return squares == playerMove.getPos();
    }

    private void postGameComplete(String gameId, Player winner) {
        gameSnapShot.postGame(gameId, winner);
    }

    private Board makeBoard(int squares, Map<Integer, Integer> snakes, Map<Integer, Integer> ladder, String gameId, GameSnapShot gameSnapShot) {

        return Board.builder()
                .squares(squares)
                .ladder(ladder)
                .snakes(snakes)
                .gameId(gameId)
                .gameSnapShot(gameSnapShot)
                .build();
    }
}
