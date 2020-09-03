package game;
import biuoop.DrawSurface;
import sprites.Sprite;
import java.awt.Color;

/**
 * @author Yahav Zarfati <yahavz100@gmail.com>
 * @version 2.0
 * @since 2020-06-15
 */
/**
 * The type Score indicator.
 */
public class ScoreIndicator implements Sprite {
    private Counter counter;

    /**
     * Instantiates a new Score indicator.
     *
     * @param counter the counter
     */
    public ScoreIndicator(Counter counter) {
        this.counter = counter;
    }
    /**
     * Draw the sprite to the surface.
     *
     * @param d the drawing surface
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.drawText(300, 17, "Score: " + toString().valueOf(counter.getValue()), 20);
    }

    /**
     * Notify the sprite that time has passed.
     */
    @Override
    public void timePassed() {
    }

    /**
     * Add score indicator to game.
     *
     * @param gameLevel to add score into
     */
    public void addToGame(GameLevel gameLevel) {
        gameLevel.addSprite(this);
    }
}
