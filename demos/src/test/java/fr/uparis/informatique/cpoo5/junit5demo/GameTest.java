package fr.uparis.informatique.cpoo5.junit5demo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fr.uparis.informatique.cpoo5.game.Game;
import javafx.scene.layout.Pane;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GameTest {
    private Game game;

    @BeforeEach
    public void setUp() {
        game = new Game();
    }

    @Test
    public void testIsAutoCollision() {

    }
}
