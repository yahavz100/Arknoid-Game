package game;
import geometry.Ball;

/**
 * @author Yahav Zarfati <yahavz100@gmail.com>
 * @version 1.0
 * @since 2020-05-27
 */
/**
 * The interface Hit listener.
 */
public interface HitListener {
    /**
     * Method is called whenever the beingHit object is hit.
     *
     * @param beingHit the object got hit
     * @param hitter   the ball hitter
     */
    void hitEvent(Block beingHit, Ball hitter);
}
