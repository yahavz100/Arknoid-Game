package game;
import biuoop.DrawSurface;

/**
 * @author Yahav Zarfati <yahavz100@gmail.com>
 * @version 1.0
 * @since 2020-06-15
 */
/**
 * The type Pause screen.
 */
public class PauseScreen implements Animation {
    private boolean stop;

    /**
     * Instantiates a new Pause screen.
     *
     */
    public PauseScreen() {
        this.stop = false;
    }
    /**
     * Do one frame.
     *
     * @param d the drawing surface
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        d.drawText(10, d.getHeight() / 2, "paused -- press space to continue", 32);
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
