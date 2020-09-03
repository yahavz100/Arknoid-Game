package game;
import biuoop.DrawSurface;
import sprites.Sprite;
import java.awt.Color;

/**
 * @author Yahav Zarfati <yahavz100@gmail.com>
 * @version 1.0
 * @since 2020-06-15
 */
/**
 * The type Level indicator.
 */
public class LevelIndicator implements Sprite {
    private String name;

    /**
     * Instantiates a new Level indicator.
     *
     * @param name the level name
     */
    public LevelIndicator(String name) {
        this.name = name;
    }
    /**
     * Draw the sprite to the surface.
     *
     * @param d the drawing surface
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.drawText(550, 17, "Level Name: " + this.name, 20);
    }

    /**
     * Notify the sprite that time has passed.
     */
    @Override
    public void timePassed() {
    }

    /**
     * Add level indicator to game.
     *
     * @param gameLevel to add name into
     */
    public void addToGame(GameLevel gameLevel) {
        gameLevel.addSprite(this);
    }
}
