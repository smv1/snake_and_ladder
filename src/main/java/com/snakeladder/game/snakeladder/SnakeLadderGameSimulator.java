package com.snakeladder.game.snakeladder;

import com.snakeladder.game.snakeladder.core.Player;
import com.snakeladder.game.snakeladder.gamecontroller.SnakeLadderGameController;
import com.snakeladder.game.snakeladder.util.GameUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
public class SnakeLadderGameSimulator {

    private final static String PLAYER = "Player";

    SnakeLadderGameController snakeLadderGameController;

    @Value("${game.snakeladder.squares}")
    int squares;
    @Value("${simulation.players.min}")
    int minPlayers;
    @Value("${simulation.players.max}")
    int maxPlayers;
    @Value("${simulation.ladder.min}")
    int minLadders;
    @Value("${simulation.ladder.max}")
    int maxLadders;
    @Value("${simulation.snakes.min}")
    int minSnakes;
    @Value("${simulation.snakes.max}")
    int maxSnakes;
    @Value("${simulation.snakeladder.start}")
    int snakesLadderStart;
    @Value("${simulation.snakeladder.end}")
    int snakesLadderEnd;
    @Value("${game.snakeladder.rowlength}")
    int rowLength;

    @Autowired
    SnakeLadderGameSimulator(SnakeLadderGameController snakeLadderGameController){
        this.snakeLadderGameController = snakeLadderGameController;
    }

    /**
     * Starts number of simulations
     *
     * @param simulations
     */
    public void simulate(int simulations) {
        ExecutorService executorService = Executors.newFixedThreadPool(getCores());
        for (int i = 0; i < simulations; i++) {
            executorService.execute(() -> {

                setupAndStartGame();

            });
        }
    }

    //setup the game with random no. of snake and ladder and players and starts
    private void setupAndStartGame() {

        Queue<Player> players = generatePlayers(GameUtil.random(minPlayers, maxPlayers));
        Map<Integer, Integer> snakes = new HashMap<>();
        Map<Integer, Integer> ladder = new HashMap<>();

        generateSnakesAndLadder(
                GameUtil.random(minLadders, maxLadders),
                GameUtil.random(minSnakes, maxSnakes),
                snakes, ladder);

        snakeLadderGameController.startGame(players, squares, snakes, ladder);
    }

    /**
     * Generates the snakes and ladder based on config provided
     * like min and max snakes, min and max ladder
     * rowlength as snakes and ladder can be to up or down row only no in current row
     *
     * @param noLadders
     * @param noSnakes
     * @param snakes
     * @param ladder
     */
    private void generateSnakesAndLadder(int noLadders, int noSnakes,
                                         Map<Integer, Integer> snakes, Map<Integer, Integer> ladder) {

        while (noSnakes + noLadders > 0) {
            int pos1 = GameUtil.random(snakesLadderStart, snakesLadderEnd);
            int pos2 = GameUtil.random(snakesLadderStart, snakesLadderEnd);
            if (pos2 == pos1 || Math.abs(pos1 - pos2) <= rowLength) {
                continue;
            }

            if (!validate(snakes, ladder, pos1, pos2)) continue;

            int max = Math.max(pos1, pos2);
            int min = Math.min(pos1, pos2);
            if (noSnakes > 0) {
                snakes.put(max, min);
                noSnakes--;
            } else if (noLadders > 0) {
                ladder.put(min, max);
                noLadders--;
            }
        }

    }


    private boolean validate(Map<Integer, Integer> snakes, Map<Integer, Integer> ladder, int pos1, int pos2) {
        if (isPositionPresentAlready(snakes, ladder, pos1) && isPositionPresentAlready(snakes, ladder, pos2)) {
            return false;
        }
        return true;
    }

    private boolean isPositionPresentAlready(Map<Integer, Integer> snakes, Map<Integer, Integer> ladder, int pos2) {
        return ladder.containsKey(pos2) || ladder.values().contains(pos2) ||
                snakes.containsKey(pos2) || snakes.values().contains(pos2);
    }

    private Queue<Player> generatePlayers(int noPlayers) {

        Queue<Player> players = new LinkedList<>();
        for (int i = 1; i <= noPlayers; i++) {
            Player player = new Player(PLAYER + i);
            players.add(player);
        }
        return players;
    }


    private int getCores() {
        return Runtime.getRuntime().availableProcessors();
    }
}
