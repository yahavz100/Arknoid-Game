package game;
import biuoop.DrawSurface;

/**
 * @author Yahav Zarfati <yahavz100@gmail.com>
 * @version 1.0
 * @since 2020-06-15
 */
/**
 * The type You win.
 */
public class YouWin implements Animation {
    private boolean stop;
    private Counter score;

    /**
     * Instantiates a new You win screen.
     *
     * @param score the score
     */
    public YouWin(Counter score) {
        this.stop = false;
        this.score = score;
    }
    /**
     * Do one frame.
     *
     * @param d the drawing surface
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        d.drawText(10, d.getHeight() / 2, "You Win! Your score is " + this.score.getValue(), 32);
    }

    /**
     * In charge of stopping condition.
     *
     * @return the boolean
     */
    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
