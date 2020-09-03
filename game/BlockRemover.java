package game;
import geometry.Ball;

/**
 * @author Yahav Zarfati <yahavz100@gmail.com>
 * @version 2.0
 * @since 2020-06-15
 */
/**
 * The BlockRemover is in charge of removing blocks from the game, as well as keeping count
 * // of the number of blocks that remain.
 */
public class BlockRemover implements HitListener {
    private GameLevel gameLevel;
    private Counter remainingBlocks;

    /**
     * Instantiates a new Block remover.
     *
     * @param gameLevel            the game
     * @param remainingBlocks the remaining blocks
     */
    public BlockRemover(GameLevel gameLevel, Counter remainingBlocks) {
        this.gameLevel = gameLevel;
        this.remainingBlocks = remainingBlocks;
    }
    /**
     * Removes blocks that are hit and should be removed.
     *
     * @param beingHit the block got hit
     * @param hitter the ball hitter
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        beingHit.removeHitListener(this);
         beingHit.removeFromGame(this.gameLevel);
         this.remainingBlocks.decrease(1);
    }
}