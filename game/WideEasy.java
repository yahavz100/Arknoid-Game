package game;
import biuoop.DrawSurface;
import geometry.Point;
import geometry.Rectangle;
import geometry.Velocity;
import sprites.Sprite;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author Yahav Zarfati <yahavz100@gmail.com>
 * @version 1.0
 * @since 2020-06-15
 */
/**
 * The type Wide easy.
 */
public class WideEasy implements LevelInformation {
    private List<Block> blocks;

    /**
     * Instantiates a new Wide easy level.
     */
    public WideEasy() {
        this.blocks = new ArrayList<>();
        Random random = new Random();
        int x = -25;
        int y = 200;
        int width = 50;
        int height = 30;
        Color color = null;
        //Create all blocks for this level
        for (int i = 0; i < numberOfBlocksToRemove(); i++) {
            //Check when color of block should be changed
            if (i < 8) {
                if (i % 2 == 0) {
                    color = new Color(random.nextInt(250), random.nextInt(250),
                            random.nextInt(250));
                }
            } else if (i > 8) {
                if (i % 2 != 0) {
                    color = new Color(random.nextInt(250), random.nextInt(250),
                            random.nextInt(250));
                }
            }
            x += 50;
            Rectangle rectangle = new Rectangle(new Point(x, y), width, height);
            Block block = new Block(rectangle, color);
            blocks.add(block);
        }
    }
    /**
     * Number of balls.
     *
     * @return the int
     */
    @Override
    public int numberOfBalls() {
        return 10;
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
        double angle = -55;
        //Create for each ball different angle
        for (int i = 0; i < this.numberOfBalls(); i++) {
            angle += 10;
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
        return 3;
    }

    /**
     * Paddle width int.
     *
     * @return the int
     */
    @Override
    public int paddleWidth() {
        return 625;
    }

    /**
     * Level name.
     *
     * @return the string
     */
    @Override
    public String levelName() {
        return ("Wide Easy");
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
                d.setColor(Color.WHITE);
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
        return 15;
    }
}
