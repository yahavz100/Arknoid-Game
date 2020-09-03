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
 * The type Green three level.
 */
public class GreenThree implements LevelInformation {
    private List<Block> blocks;

    /**
     * Instantiates a new Green three.
     */
    public GreenThree() {
        this.blocks = new ArrayList<>();
        Random random = new Random();
        int x = 730;
        int y = 220;
        int width = 50;
        int height = 20;
        int numOfRows = 5;
        int step = 9;
        Color color;
        //Create all blocks for this level
        for (int i = 0; i < numOfRows; i++) {
            color = new Color(random.nextInt(250), random.nextInt(250),
                    random.nextInt(250));
            //Create blocks each row
            for (int j = 0; j < numberOfBlocksToRemove(); j++) {
                Rectangle rectangle = new Rectangle(new Point(x, y), width, height);
                Block block = new Block(rectangle, color);
                blocks.add(block);
                x = x - width;
                //Check if need to low step for blocks shape
                if (j == step) {
                    step--;
                    break;
                }
            }
            x = 730;
            y = y + height;
        }
    }
    /**
     * Number of balls.
     *
     * @return the int
     */
    @Override
    public int numberOfBalls() {
        return 2;
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
        //Create for each ball different angle
        for (int i = 0; i < this.numberOfBalls(); i++) {
            angle += 5;
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
        return 13;
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
        return ("Green 3");
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
                d.setColor(Color.GREEN);
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
        return 39;
    }
}
