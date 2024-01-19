package fr.uparis.informatique.cpoo5.junit5demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;

import fr.uparis.informatique.cpoo5.game.Game;
import fr.uparis.informatique.cpoo5.utils.Direction;

public class GameTest {
    @Test
    void testGenerateFood() {
        // Creation of an instance of Game
        Game game = new Game(false, false);

        // generate food
        game.generateFood();

        // verify that the food is not null
        assertNotNull(game.getFood());
    }

    @Test
    void testUpdateCell() {
        // Creation of an instance of Game
        Game game = new Game(false, false);

        // Data of the player
        Game.DataPlayer dataPlayer = game.getDataPlayer().get(0);

        // the position of the the head of the snake
        int initialHeadRow = dataPlayer.occupiedCells.getFirst().row;
        int initialHeadCol = dataPlayer.occupiedCells.getFirst().col;
        System.out.println(initialHeadRow + " " + initialHeadCol);

        dataPlayer.player.getSnake().setDirection(Direction.UP);
        dataPlayer.player.moveSnake(null, null);

        // update of the cells
        game.updateCell(dataPlayer);

        // the new position of the snake
        int newHeadRow = dataPlayer.occupiedCells.getFirst().row;
        int newHeadCol = dataPlayer.occupiedCells.getFirst().col;
        System.out.println(newHeadRow + " " + newHeadCol);

        assertNotEquals(initialHeadRow, newHeadRow);
        assertEquals(initialHeadCol, newHeadCol);
    }

}
