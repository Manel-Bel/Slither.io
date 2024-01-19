package fr.uparis.informatique.cpoo5.utils;

import javafx.scene.paint.Color;

import java.util.HashSet;
import java.util.Set;

/**
 * Manages the available colors.
 * 
 * @author : Belguenbour Manel
 */
public class ColorManager {
    private static final Set<Color> availableColors = new HashSet<>();
    private static final Set<Color> usedColors = new HashSet<>();

    // Initialize available colors
    static {
        availableColors.add(Color.RED);
        availableColors.add(Color.GREEN);
        availableColors.add(Color.BLUE);
        availableColors.add(Color.YELLOW);
        availableColors.add(Color.BEIGE);
        availableColors.add(Color.AQUA);
        availableColors.add(Color.BROWN);
        availableColors.add(Color.TAN);
        availableColors.add(Color.TOMATO);
        availableColors.add(Color.DARKKHAKI);
        availableColors.add(Color.PINK);

    }

    /**
     * Gets an available color
     *
     * @return The available color.
     */
    public static Color getAvailableColor() {
        Color color = availableColors.iterator().next();
        availableColors.remove(color);
        usedColors.add(color);
        return color;
    }

    /**
     * Releases a color back to the available colors
     *
     * @param color The color to release
     */
    public static void releaseColor(Color color) {
        usedColors.remove(color);
        availableColors.add(color);
        System.out.println("Color " + color + " released.");
    }

    /**
     * Checks if a color is currently used.
     *
     * @param color The color to check.
     * @return True if the color is in use, false otherwise.
     */
    public static boolean isColorUsed(Color color) {
        return usedColors.contains(color);
    }
}
