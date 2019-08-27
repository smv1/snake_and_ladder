# snake_and_ladder

Simulator for snake and ladder game

<b>restcontroller.SimulatorController</b> - Controller for simulating, parameter - no. of simulation

<b>SnakeLadderGameSimulator</b> - Simulated multiple snakeladder games, generates random snakes and ladder and then start.

<b>gamecontroller.SnakeLadderGameController</b> - game controller which starts the game with players, total squares, snakes, ladder

<b>core.Board</b> - Board is core which handles squares in board, ladder, snakes and players positions.

<b>core.Dice</b> - Dice

<b>snapshot.GameSnapShot</b> - snapshot/output for game


==================================================================

<b>Properties</b>

game.snakeladder.squares=100
game.snakeladder.rowlength=10   --- row length as snake and ladder will we in upper or lower rows
game.max.roll.chance=true       --- flag roll again incase we get max dice

simulation.players.min=2
simulation.players.max=4
simulation.ladder.min=4
simulation.ladder.max=10
simulation.snakes.min=4
simulation.snakes.max=10
simulation.snakeladder.start=2
simulation.snakeladder.end=99
