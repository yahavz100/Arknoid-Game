package geometry;

/**
 * @author Yahav Zarfati <yahavz100@gmail.com>
 * @version 2.0
 * @since 2020-05-01
 */
/**
 * A Point which include x,y coordinates.
 */
public class Point {
    private double x;
    private double y;

    /**
     * Constructor.
     * @param x coordinate of the point
     * @param y coordinate of the point
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Calculates the distance of this point to the other point.
     *
     * @param other the point to calculate distance to.
     * @return the distance between the points.
     */
    public double distance(Point other) {
        double distance;
        distance = Math.sqrt(((this.x - other.x) * (this.x - other.x)) + ((this.y - other.y) * (this.y - other.y)));
        return distance;
    }

    /**
     * Check if the points has identical x,y coordinates.
     *
     * @param other the point to check if they are the same.
     * @return true if identical, \
     *          false otherwise.
     */
    public boolean equals(Point other) {
        return (this.x == other.x) && (this.y == other.y);
    }

    /**
     * Returns the x value of this point.
     * @return the x value.
     */
    public double getX() {
        return this.x;
    }
    /**
     * Returns the y value of this point.
     * @return the y value.
     */
    public double getY() {
        return this.y;
    }

    /**
     * Changes the x value of this point.
     * @param newX new value for this x point.
     */
    public void setX(double newX) {

        this.x = newX;
    }

    /**
     * Changes the y value of this point.
     * @param newY new value for this y point.
     */
    public void setY(double newY) {
        this.y = newY;
    }
}