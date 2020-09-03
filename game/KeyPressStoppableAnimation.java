package game;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * @author Yahav Zarfati <yahavz100@gmail.com>
 * @version 1.0
 * @since 2020-06-15
 */
/**
 * The type Key press stoppable animation.
 */
public class KeyPressStoppableAnimation implements Animation {
    private KeyboardSensor sensor;
    private String key;
    private Animation animation;
    private boolean isPressed;
    private boolean isAlreadyPressed;
    private boolean stop;

    /**
     * Instantiates a new Key press stoppable animation.
     *
     * @param sensor    the sensor
     * @param key       the key
     * @param animation the animation
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.sensor = sensor;
        this.key = key;
        this.animation = animation;
        this.isPressed = false;
        this.isAlreadyPressed = true;
        this.stop = false;
    }
    /**
     * Do one frame.
     *
     * @param d the drawing surface
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        //Check if key is pressed
        if (this.sensor.isPressed(this.key)) {
            if (!this.isAlreadyPressed) {
                this.isPressed = true;
            }
        } else if (this.isAlreadyPressed) {
            this.isAlreadyPressed = false;
        } else if (isPressed) {
            this.stop = true;
        }
        this.animation.doOneFrame(d);
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
