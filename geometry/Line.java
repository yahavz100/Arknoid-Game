package geometry;
/**
 * @author Yahav Zarfati <yahavz100@gmail.com>
 * @version 2.0
 * @since 2020-05-01
 */
/**
 * A Line made of 2 points.
 */
public class Line {
    private Point start;
    private Point end;

    /**
     * Constructor with default points.
     *
     * @param start start point of this line.
     * @param end   end point of this line.
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    /**
     * Constructor with configurable points.
     *
     * @param x1 x coordinate of one point.
     * @param y1 y coordinate of one point.
     * @param x2 x coordinate of second point.
     * @param y2 y coordinate of second point.
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }

    /**
     * Returns the length of the line.
     *
     * @return the length.
     */
    public double length() {
        return (end.distance(start));
    }

    /**
     * Returns the middle point of the line.
     *
     * @return the middle point.
     */
    public Point middle() {
        double midX = (this.end.getX() + this.start.getX()) / 2;
        double midY = (this.end.getY() + this.start.getY()) / 2;
        Point midPoint = new Point(midX, midY);
        return (midPoint);
    }

    /**
     * Returns the start point of the line.
     *
     * @return the start point.
     */
    public Point start() {
        return (this.start);
    }

    /**
     * Returns the end point of the line.
     *
     * @return the end point.
     */
    public Point end() {
        return (this.end);
    }

    /**
     * Calculates the intersection point of two lines.
     *
     * @param other the line intersect with this line.
     * @return intersection point, \
     * null if there is no intersection
     */
    public Point intersectionWith(Line other) {
        double m1 = checkSlopes(other).getX();
        double m2 = checkSlopes(other).getY();
        //Check if slopes are the same
        if (Math.abs(m1 - m2) < Double.MIN_VALUE) {
            return null;
        }
        double b1 = this.start.getY() - m1 * this.start.getX();
        double b2 = other.start.getY() - m2 * other.start.getX();
        double x1 = (b2 - b1) / (m1 - m2);
        double y1 = m1 * x1 + b1;
        //Check if line is horizonal
        if (Double.isInfinite(m1)) {
            x1 = this.start.getX();
            y1 = x1 * m2 + b2;
            //Check if line is horizonal
        } else if (Double.isInfinite(m2)) {
            x1 = other.start.getX();
            y1 = x1 * m1 + b1;
        }
        Point pointIntersection = new Point(x1, y1);
        //Check if intersection point in lines segment
        if (this.checkIfPointInSegment(pointIntersection) && other.checkIfPointInSegment(pointIntersection)) {
            return (pointIntersection);
        }
        return null;
    }

    /**
     * Check if lines intersect.
     *
     * @param other the line to check if intersect.
     * @return true if intersect, \
     * false otherwise.
     */
    public boolean isIntersecting(Line other) {
        if (intersectionWith(other) == null) {
            return false;
        }
        return true;
    }

    /**
     * Check if lines has same starting and ending points.
     *
     * @param other the line to check with.
     * @return true if lines has equal starting and ending points, \
     * false otherwise.
     */
    public boolean equals(Line other) {
        return this.start.getX() == other.start.getX() && this.start.getY() == other.start.getY()
                && this.end.getX() == other.end.getX() && this.end.getY() == other.end.getY();
    }

    /**
     * Calculates lines slopes.
     *
     * @param other line to calculate its slope.
     * @return point with x value - first line slope, y value - second line slope.
     */
    public Point checkSlopes(Line other) {
        double thisDy = this.end.getY() - this.start.getY();
        double thisDx = this.end.getX() - this.start.getX();
        double otherDy = other.end.getY() - other.start.getY();
        double otherDx = other.end.getX() - other.start.getX();
        //Check unique case of negative 0
        if (thisDy == -0) {
            thisDy = 0;
        }
        //Check unique case of negative 0
        if (otherDy == -0) {
            otherDy = 0;
        }
        double m1 = (thisDy / thisDx);
        double m2 = (otherDy / otherDx);
        return (new Point(m1, m2));
    }

    /**
     * Check if point is in lines segment.
     *
     * @param point the point to check if in segment
     * @return true if point in lines segment, \
     * false otherwise.
     */
    public boolean checkIfPointInSegment(Point point) {
        double epsilon = Math.pow(10, -10);
        //Check if point between values
        if (point.getX() - epsilon <= Math.max(this.start.getX(), this.end.getX())
                && point.getX() + epsilon >= Math.min(this.start.getX(), this.end.getX())
                && point.getY() - epsilon <= Math.max(this.start.getY(), this.end.getY())
                && point.getY() + epsilon >= Math.min(this.start.getY(), this.end.getY())) {
            return true;
        }
        return false;
    }

    /**
     * Closest intersection to start of line point.
     * if line doesn't intersect return null \
     * otherwise return the closest intersection point between rectangle to start of line
     *
     * @param rect a rectangle
     * @return the closest intersection point
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        //Check if this line intersect with rectangle
        if (rect.intersectionPoints(this).isEmpty()) {
            return null;
        }
        double minDist = this.start.distance(rect.intersectionPoints(this).get(0));
        Point minPoint = new Point(rect.intersectionPoints(this).get(0).getX(),
                rect.intersectionPoints(this).get(0).getY());
        //Run on whole list of intersection points
        for (int i = 0; i < rect.intersectionPoints(this).size(); i++) {
            //Check if distance from intersection point to start is smaller than prev
            if (this.start.distance(rect.intersectionPoints(this).get(i)) < minDist) {
                minDist = this.start.distance(rect.intersectionPoints(this).get(i));
                minPoint.setX(rect.intersectionPoints(this).get(i).getX());
                minPoint.setY(rect.intersectionPoints(this).get(i).getY());
            }
        }
        return minPoint;
    }
}