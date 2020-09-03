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
 * The type Final four.
 */
public class FinalFour implements LevelInformation {
    private List<Block> blocks;

    /**
     * Instantiates a new Final four level.
     */
    public FinalFour() {
        this.blocks = new ArrayList<>();
        Random random = new Random();
        int x = 725;
        int y = 220;
        int width = 50;
        int height = 20;
        int numOfRows = 7;
        int step = 15;
        Color color;
        //Create all blocks for this level
        for (int i = 0; i < numOfRows; i++) {
            color = new Color(random.nextInt(250), random.nextInt(250),
                    random.nextInt(250));
            //Create blocks each row
            for (int j = 0; j < step; j++) {
                Rectangle rectangle = new Rectangle(new Point(x, y), width, height);
                Block block = new Block(rectangle, color);
                blocks.add(block);
                x = x - width;
            }
            x = 725;
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
        return 3;
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
        double angle = -5;
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
        return ("Final Four");
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
                d.setColor(Color.CYAN);
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
        return 105;
    }
}
