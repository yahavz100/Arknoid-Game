package game;
import biuoop.DrawSurface;
import geometry.Point;
import geometry.Rectangle;
import geometry.Velocity;
import sprites.Sprite;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Yahav Zarfati <yahavz100@gmail.com>
 * @version 1.0
 * @since 2020-06-15
 */
/**
 * The type Direct hit.
 */
public class DirectHit implements LevelInformation {
    private List<Block> blocks;

    /**
     * Instantiates a new Direct hit level.
     */
    public DirectHit() {
        this.blocks = new ArrayList<>();
        int x = 385;
        int y = 100;
        int width = 30;
        int height = 30;
        Color color = Color.red;
        Rectangle rectangle = new Rectangle(new Point(x, y), width, height);
        Block block = new Block(rectangle, color);
        blocks.add(block);
    }
    /**
     * Number of balls.
     *
     * @return the int
     */
    @Override
    public int numberOfBalls() {
        return 1;
    }

    /**
     * Initial ball velocities list.
     *
     * @return the list
     */
    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new ArrayList<Velocity>();
        double speed = 3;
        double angle = 0;
        for (int i = 0; i < this.numberOfBalls(); i++) {
            Velocity v = Velocity.fromAngleAndSpeed(angle, speed);
            velocities.add(v);
        }
        return velocities;
    }

    /**
     * Paddle speed int.
     *
     * @return the int
     */
    @Override
    public int paddleSpeed() {
        return 10;
    }

    /**
     * Paddle width int.
     *
     * @return the int
     */
    @Override
    public int paddleWidth() {
        return 100;
    }

    /**
     * Level name.
     *
     * @return the string
     */
    @Override
    public String levelName() {
        return ("Direct Hit");
    }

    /**
     * Gets background.
     *
     * @return the background
     */
    @Override
    public Sprite getBackground() {
        return new Sprite() {
            /**
             * Draw the sprite to the surface.
             *
             * @param d the drawing surface
             */
            @Override
            public void drawOn(DrawSurface d) {
                d.setColor(Color.BLACK);
                d.fillRectangle(0, 0, 800, 600);
            }

            /**
             * Notify the sprite that time has passed.
             */
            @Override
            public void timePassed() {
            }
        };
    }

    /**
     * Blocks list.
     *
     * @return the list
     */
    @Override
    public List<Block> blocks() {
        return blocks;
    }

    /**
     * Number of blocks to remove.
     *
     * @return the int
     */
    @Override
    public int numberOfBlocksToRemove() {
        return 1;
    }
}
