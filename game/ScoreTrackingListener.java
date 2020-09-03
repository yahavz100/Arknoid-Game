package game;
import geometry.Ball;

/**
 * @author Yahav Zarfati <yahavz100@gmail.com>
 * @version 1.0
 * @since 2020-05-27
 */
/**
 * The type Score tracking listener.
 */
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;

    /**
     * Instantiates a new Score tracking listener.
     *
     * @param scoreCounter the score counter
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    /**
     * Method is called whenever the beingHit object is hit.
     *
     * @param beingHit the object got hit
     * @param hitter   the ball hitter
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        int hitBlockPoints = 5;
        currentScore.increase(hitBlockPoints);
    }
}
