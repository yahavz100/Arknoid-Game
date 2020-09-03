package game;
import geometry.Ball;

/**
 * @author Yahav Zarfati <yahavz100@gmail.com>
 * @version 2.0
 * @since 2020-06-15
 */
/**
 * The type Ball remover.
 */
public class BallRemover implements HitListener {
    private GameLevel gameLevel;
    private Counter remainingBalls;

    /**
     * Instantiates a new Ball remover.
     *
     * @param gameLevel           the game level
     * @param remainingBalls the remaining balls
     */
    public BallRemover(GameLevel gameLevel, Counter remainingBalls) {
        this.gameLevel = gameLevel;
        this.remainingBalls = remainingBalls;
    }

    /**
     * Method is called whenever the beingHit object is hit.
     *
     * @param beingHit the object got hit death block
     * @param hitter   the ball hitter
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(this.gameLevel);
        this.remainingBalls.decrease(1);
    }
}
