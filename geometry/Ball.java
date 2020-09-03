package geometry;
import biuoop.DrawSurface;
import collisiondetection.CollisionInfo;
import collisiondetection.GameEnvironment;
import game.GameLevel;
import sprites.Sprite;
import java.awt.Color;

/**
 * @author Yahav Zarfati <yahavz100@gmail.com>
 * @version 4.0
 * @since 2020-06-15
 */
/**
 * A Circle made of center point, radius and color.
 */
public class Ball implements Sprite {
    private Point center;
    private int r;
    private java.awt.Color color;
    private Velocity velocity;
    private GameEnvironment gEnv;

    /**
     * constructor with default values.
     *
     * @param center point of this ball.
     * @param r      radius of this ball.
     * @param color  of this ball.
     */
    public Ball(Point center, int r, java.awt.Color color) {
        this.center = center;
        this.r = r;
        this.color = color;
    }

    /**
     * constructor with configurable center point.
     *
     * @param x     coordinate of center point.
     * @param y     coordinate of center point.
     * @param r     radius of this ball.
     * @param color of this ball.
     */
    public Ball(int x, int y, int r, java.awt.Color color) {
        this.color = color;
        this.r = r;
        this.center = new Point(x, y);
    }

    /**
     * Returns the x value of this ball center.
     *
     * @return the x value.
     */
    public int getX() {
        return ((int) this.center.getX());
    }

    /**
     * Returns the y value of this ball center.
     *
     * @return the y value.
     */

    public int getY() {
        return ((int) this.center.getY());
    }

    /**
     * Returns the radius of this ball.
     *
     * @return the radius.
     */
    public int getSize() {
        return (this.r);
    }

    /**
     * Returns the color of this ball.
     *
     * @return the color.
     */
    public java.awt.Color getColor() {
        return (this.color);
    }

    /**
     * Draw this ball on given draw surface.
     *
     * @param surface to draw ball on.
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(Color.BLACK);
        surface.drawCircle((int) this.center.getX(), (int) this.center.getY(), this.r);
        surface.setColor(this.color);
        surface.fillCircle((int) this.center.getX(), (int) this.center.getY(), this.r);
    }

    /**
     * Notify the sprite that time has passed.
     */
    @Override
    public void timePassed() {
        moveOneStep();
    }

    /**
     * Changes the velocity of this ball.
     * @param v new velocity for this ball.
     */
    public void setVelocity(Velocity v) {
        this.velocity = v;
    }

    /**
     * Changes the velocity of this ball.
     * @param dx new velocity for this ball on x axis.
     * @param dy new velocity for this ball on y axis.
     */
    public void setVelocity(double dx, double dy) {
        this.velocity = new Velocity(dx, dy);
    }

    /**
     * Returns the velocity of this ball.
     *
     * @return the velocity.
     */
    public Velocity getVelocity() {
        return (this.velocity);
    }

    /**
     * Changes the game environment of this ball.
     *
     * @param newEnv the new environment
     */
    public void setGameEnvironment(GameEnvironment newEnv) {
        this.gEnv = newEnv;
    }

    /**
     * Returns the game environment.
     *
     * @return game environment of this ball
     */
    public GameEnvironment getGameEnvironment() {
        return this.gEnv;
    }

    /**
     * Changes the position of the ball if its outside of its rectangle frame.
     * @param x1 rectangle frame.
     * @param y1 rectangle frame.
     * @param x2 rectangle frame.
     * @param y2 rectangle frame.
     */
    public void fixedPosRectangle(int x1, int y1, int x2, int y2) {
        //Check if the ball is in good position
        if (this.center.getX() - this.r > x1 && this.center.getX() + this.r < x2
        && this.center.getY() - this.r > y1 && this.center.getY() + this.r < y2) {
            return;
        }
        //Check which case of bad position the ball is above,under,to right,to left or mix
        if (this.center.getX() - this.r < x1 && this.center.getY() - this.r < y1) {
            this.center.setX(x1 + this.r);
            this.center.setY(y1 + this.r);
        } else if (this.center.getX() - this.r < x1 && this.center.getY() + this.r > y2) {
            this.center.setX(x1 + this.r);
            this.center.setY(y2 + this.r);
        } else if (this.center.getX() + this.r > x2 && this.center.getY() - this.r < y1) {
            this.center.setX(x2 - this.r);
            this.center.setY(y1 + this.r);
        } else if (this.center.getX() + this.r > x2 && this.center.getY() + this.r > y2) {
            this.center.setX(x2 - this.r);
            this.center.setY(y2 + this.r);
        } else if (this.center.getX() - this.r < x1) {
            this.center.setX(x1 + this.r);
        } else if (this.center.getX() + this.r > x2) {
            this.center.setX(x2 - this.r);
        } else if (this.center.getY() - this.r < y1) {
            this.center.setY(y1 + this.r);
        } else if (this.center.getY() + this.r > y2) {
            this.center.setY(y2 + this.r);
        }
    }

    /**
     * Move one step.
     */
    public void moveOneStep() {
        //Create copy of ball center
        Point copyCenter = this.center;
        copyCenter = this.getVelocity().applyToPoint(copyCenter);
        //Calculate line trajectory and get collision info
        Line trajectory = new Line(this.getX(), this.getY(), copyCenter.getX(), copyCenter.getY());
        CollisionInfo info1 = this.gEnv.getClosestCollision(trajectory);
        //Check if the ball will hit an object
        if (info1 == null) {
            //There is no hit
            this.center = new Point(copyCenter.getX(), copyCenter.getY());
            //There is a hit
        } else {
            Point hitPoint = info1.collisionPoint();
            int step = 2;
            //If ball move left
            if (this.getVelocity().getDx() < 0) {
                this.center = new Point(hitPoint.getX() + step, this.center.getY());
                //ball move right
            } else if (this.getVelocity().getDx() > 0) {
                this.center = new Point(hitPoint.getX() - step, this.center.getY());
            }
            //If ball move down
            if (this.getVelocity().getDy() > 0) {
                this.center = new Point(this.center.getX(), hitPoint.getY() - step);
                //ball move up
            } else if (this.getVelocity().getDy() < 0) {
                this.center = new Point(this.center.getX(), hitPoint.getY() + step);
            }
            //Notify and this velocity according to hit
            this.setVelocity(info1.collisionObject().hit(this, info1.collisionPoint(), this.velocity));
        }
    }

    /**
     * Add this ball to game.
     *
     * @param gameLevel to add ball into
     */
    public void addToGame(GameLevel gameLevel) {
        gameLevel.addSprite(this);
    }

    /**
     * Remove this ball from game.
     *
     * @param g the game to remove ball from
     */
    public void removeFromGame(GameLevel g) {
        g.removeSprite(this);
    }
}