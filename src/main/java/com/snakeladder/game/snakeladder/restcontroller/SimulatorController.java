package com.snakeladder.game.snakeladder.restcontroller;

import com.snakeladder.game.snakeladder.SnakeLadderGameSimulator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SimulatorController {

    @Autowired
    SnakeLadderGameSimulator snakeLadderGameSimulator;

    /**
     * Rest API to start simulating
     *
     * @param cases
     */
    @GetMapping("/simulate/{simulateNo}")
    public void simulate(@PathVariable("simulateNo") int cases) {
        snakeLadderGameSimulator.simulate(cases);
    }
}
