package sprites;
import biuoop.DrawSurface;

/**
 * @author Yahav Zarfati <yahavz100@gmail.com>
 * @version 1.0
 * @since 2020-05-01
 */
/**
 * The interface Sprite.
 */
public interface Sprite {
    /**
     * Draw the sprite to the surface.
     *
     * @param d the drawing surface
     */
    void drawOn(DrawSurface d);

    /**
     * Notify the sprite that time has passed.
     */
    void timePassed();
}
