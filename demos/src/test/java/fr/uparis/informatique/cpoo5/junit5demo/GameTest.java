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
        game = new Game(new Pane());
    }

    @Test
    public void testIsAutoCollision() {
        // Configuration du jeu pour un scénario d'autocollision
        // Par exemple, on peut créer deux objets en collision
        // game.getPlayers().setPosition(5, 5);
        // game.getEnemy().setPosition(5, 5);

        // On appelle la méthode isAutoCollision et on vérifie qu'elle retourne true
        // assertTrue(game.isAutoCollision(0));

        // Configuration du jeu pour un scénario sans autocollision
        // Par exemple, on peut créer deux objets non en collision
        // game.getPlayers().setPosition(1, 1);
        // game.getEnemy().setPosition(9, 9);

        // On appelle la méthode isAutoCollision et on vérifie qu'elle retourne false
        // assertFalse(game.isAutoCollision());
    }
}
/*
 * import org.junit.jupiter.api.Test;
 * import java.lang.reflect.Method;
 * 
 * public class GameTest {
 * 
 * @Test
 * public void testInitGrid() throws Exception {
 * Game game = new Game(new Pane());
 * Method initGridMethod = Game.class.getDeclaredMethod("initGrid");
 * initGridMethod.setAccessible(true);
 * initGridMethod.invoke(game);
 * 
 * // Vérifier ici les résultats de l'exécution de la méthode initGrid()
 * }
 * }
 * 
 * /*
 * 
 */
