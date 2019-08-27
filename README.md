# snake_and_ladder

Simulator for snake and ladder game

<b>restcontroller.SimulatorController</b> - Controller for simulating, parameter - no. of simulation

<b>SnakeLadderGameSimulator</b> - Simulate multiple snakeladder games, generates random snakes and ladder and then start.

<b>gamecontroller.SnakeLadderGameController</b> - game controller which starts the game with players, total squares, snakes, ladder

<b>core.Board</b> - Board is core which handles squares in board, ladder, snakes and players positions.

<b>core.Dice</b> - Dice

<b>snapshot.GameSnapShot</b> - snapshot/output interface of game

Players move:
player move based on the roll of dice, if ladder or snake is then it follows that.
if already a player is there then this player beats already present player, and it sent to starting position


==================================================================

<b>Properties</b>

game.snakeladder.squares=100</br>
game.snakeladder.rowlength=10   --- row length as snake and ladder will we in upper or lower rows</br>
game.max.roll.chance=true       --- flag roll again incase we get max dice</br>

simulation.players.min=2</br>
simulation.players.max=4</br>
simulation.ladder.min=4</br>
simulation.ladder.max=10</br>
simulation.snakes.min=4</br>
simulation.snakes.max=10</br>
simulation.snakeladder.start=2</br>
simulation.snakeladder.end=99</br>
