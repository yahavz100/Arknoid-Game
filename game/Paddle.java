package game;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import collisiondetection.Collidable;
import geometry.Rectangle;
import geometry.Line;
import geometry.Point;
import geometry.Ball;
import geometry.Velocity;
import sprites.Sprite;
import java.awt.Color;

/**
 * @author Yahav Zarfati <yahavz100@gmail.com>
 * @version 3.0
 * @since 2020-06-15
 */
/**
 * The type Paddle is a rectangle that is controlled by the arrow keys.
 */
public class Paddle implements Sprite, Collidable {
    private biuoop.KeyboardSensor keyboard;
    private Rectangle rect;
    private Color color;
    private int frameWidth;
    private int step;

    /**
     * Instantiates a new shape Paddle.
     *
     * @param rect     the rectangle size of this paddle
     * @param color    the color of this paddle
     * @param keyboard the keyboard controls this paddle
     * @param step     the paddle step
     */
    public Paddle(Rectangle rect, Color color, KeyboardSensor keyboard, int step) {
        this.rect = rect;
        this.color = color;
        this.keyboard = keyboard;
        this.step = step;
    }

    /**
     * Move the paddle left.
     */
    public void moveLeft() {
        int marginBlock = 20;
        //Check if paddle is moved out of the screen
        if (this.rect.getUpperLeft().getX() - this.step < marginBlock) {
            this.rect.getUpperLeft().setX(marginBlock);
        } else {
            this.rect.getUpperLeft().setX(this.rect.getUpperLeft().getX() - this.step);
        }
    }

    /**
     * Move the paddle right.
     */
    public void moveRight() {
        int marginBlock = 20;
        //Check if paddle is moved out of the screen
        if (this.rect.getUpperLeft().getX() + this.step + this.rect.getWidth() > this.frameWidth - marginBlock) {
            this.rect.getUpperLeft().setX(this.frameWidth - this.rect.getWidth() - marginBlock);
        } else {
            this.rect.getUpperLeft().setX(this.rect.getUpperLeft().getX() + this.step);
        }
    }

    /**
     * Notify the paddle that time has passed.
     */
    public void timePassed() {
        //Check which key is pressed right/left
        if (this.keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            this.moveRight();
        } else if (this.keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            this.moveLeft();
        }
    }

    /**
     * Draw the paddle to the surface.
     *
     * @param d the drawing surface
     */
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        d.fillRectangle((int) this.rect.getUpperLeft().getX(), (int) this.rect.getUpperLeft().getY(),
                (int) this.rect.getWidth(), (int) this.rect.getHeight());
        d.setColor(Color.BLACK);
        d.drawRectangle((int) this.rect.getUpperLeft().getX(), (int) this.rect.getUpperLeft().getY(),
                (int) this.rect.getWidth(), (int) this.rect.getHeight());
    }

    /**
     * Returns the rectangle shape of this paddle.
     *
     * @return this rectangle
     */
    public Rectangle getCollisionRectangle() {
        return (this.rect);
    }


    /**
     * Notify the object that we collided with it at collisionPoint with a given velocity.
     *
     * @param hitter          the  ball hitter
     * @param collisionPoint  the collision point
     * @param currentVelocity the current velocity
     * @return the velocity after collision
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        int speed = 5;
        double x = this.rect.getUpperLeft().getX();
        double y = this.rect.getUpperLeft().getY();
        double xWidth = this.rect.getUpperLeft().getX() + this.rect.getWidth();
        double yHeight = this.rect.getUpperLeft().getY() + this.rect.getHeight();
        Line lineUp = new Line(x, y, xWidth, y);
        Line lineDown = new Line(x, yHeight, xWidth, yHeight);
        Line lineLeft = new Line(x, y, x, yHeight);
        Line lineRight = new Line(xWidth, y, xWidth, yHeight);
        double dx = currentVelocity.getDx();
        double dy = currentVelocity.getDy();
        int upLength = (int) lineUp.length();
        int i = upLength / 5;
        double upLineX = lineUp.start().getX();
        double upLineY = lineUp.start().getY();
        Line region1 = new Line(upLineX, upLineY, upLineX + i - 1, upLineY);
        Line region2 = new Line(upLineX + i - 1, upLineY, upLineX + (i * 2) - 1, upLineY);
        Line region3 = new Line(upLineX + (i * 2) - 1, upLineY, upLineX + (i * 3) - 1, upLineY);
        Line region4 = new Line(upLineX + (i * 3) - 1, upLineY, upLineX + (i * 4) - 1, upLineY);
        Line region5 = new Line(upLineX + (i * 4) - 1, upLineY, upLineX + (i * 5), upLineY);
        Point rightVertexUp = new Point(xWidth, yHeight);
        Point rightVertexDown = new Point(this.rect.getUpperLeft().getX() + this.rect.getWidth(),
                this.rect.getUpperLeft().getY() + this.rect.getHeight());
        Point leftVertexDown = new Point(this.rect.getUpperLeft().getX(), this.rect.getUpperLeft().getY()
                + this.rect.getHeight());
        Point leftVertexUp = new Point(x, y);
        //Check if collision in corners
        if (collisionPoint.distance(rightVertexUp) < 0.2 || collisionPoint.distance(leftVertexUp) < 0.2
                || collisionPoint.distance(rightVertexDown) < 0.2 || collisionPoint.distance(leftVertexDown) < 0.2) {
            return new Velocity(-dx, -dy);
        }
            //Check if collision in upper line
        if (lineUp.checkIfPointInSegment(collisionPoint)) {
            //Check if collision in region1
            if (region1.checkIfPointInSegment(collisionPoint)) {
                Velocity v = Velocity.fromAngleAndSpeed(300, speed);
                return v;
                //Check if collision in region2
            } else if (region2.checkIfPointInSegment(collisionPoint)) {
                Velocity v = Velocity.fromAngleAndSpeed(330, speed);
                return v;
                //Check if collision in region3
            } else if (region3.checkIfPointInSegment(collisionPoint)) {
                return new Velocity(dx, -dy);
                //Check if collision in region4
            } else if (region4.checkIfPointInSegment(collisionPoint)) {
                Velocity v = Velocity.fromAngleAndSpeed(30, speed);
                return v;
                //Check if collision in region5
            } else if (region5.checkIfPointInSegment(collisionPoint)) {
                Velocity v = Velocity.fromAngleAndSpeed(60, speed);
                return v;
            }
        } else if (lineLeft.checkIfPointInSegment(collisionPoint)) {
            Velocity v = Velocity.fromAngleAndSpeed(300, speed);
            return v;
        } else if (lineRight.checkIfPointInSegment(collisionPoint)) {
            Velocity v = Velocity.fromAngleAndSpeed(60, speed);
            return v;
        }
        return currentVelocity;
    }

    /**
     * Adds this paddle to the game.
     *
     * @param g the game
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

    /**
     * Sets frame limit for this paddle.
     *
     * @param width limit
     */
    public void setFrame(int width) {
        this.frameWidth = width;
    }
}