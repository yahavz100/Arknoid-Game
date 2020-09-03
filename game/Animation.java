package game;
import biuoop.DrawSurface;

/**
 * @author Yahav Zarfati <yahavz100@gmail.com>
 * @version 1.0
 * @since 2020-06-15
 */
/**
 * The interface game.Animation.
 */
public interface Animation {
    /**
     * Do one frame.
     *
     * @param d the drawing surface
     */
    void doOneFrame(DrawSurface d);

    /**
     * In charge of stopping condition.
     *
     * @return the boolean
     */
    boolean shouldStop();
}