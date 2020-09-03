package geometry;
import java.util.Random;

/**
 * @author Yahav Zarfati <yahavz100@gmail.com>
 * @version 2.0
 * @since 2020-05-01
 */
/**
 * Velocity specifies the change in position on the `x` and the `y` axes.
 */
public class Velocity {
    private double dx;
    private double dy;

    /**
     * constructor with default values.
     *
     * @param dx change in x axis
     * @param dy change in y axis
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }
    /**
     * Take a point with position (x,y) and return a new point with position (x+dx, y+dy).
     *
     * @param p point to add to dx,dy
     * @return new point with (x+dx, y+dy)
     */
    public Point applyToPoint(Point p) {
        return (new Point(p.getX() + dx, p.getY() + dy));
    }

    /**
     * Returns the dx value of this velocity.
     *
     * @return the dx value.
     */
    public double getDx() {
        return (this.dx);
    }

    /**
     * Returns the dy value of this velocity.
     *
     * @return the dy value.
     */
    public double getDy() {
        return (this.dy);
    }


    /**
     * Sets new dx to this velocity.
     *
     * @param newDx the new dx
     */
    public void setDx(double newDx) {
        this.dx = newDx;
    }


    /**
     * Sets new dy to this velocity.
     *
     * @param newDy the new dy
     */
    public void setDy(double newDy) {
        this.dy = newDy;
    }

    /**
     * Calculates velocity with given angle and speed.
     * @param angle for this velocity
     * @param speed for this velocity
     * @return new velocity
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double dx = speed * Math.sin(Math.toRadians(angle));
        double dy = -speed * Math.cos(Math.toRadians(angle));
        return new Velocity(dx, dy);
    }

    /**
     * Generates random angle within bound of 360.
     * @return new angle
     */
    public static double randAngle() {
        Random rand = new Random();
        return (rand.nextInt(360));
    }
}