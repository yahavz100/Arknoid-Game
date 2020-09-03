package geometry;
import java.util.ArrayList;

/**
 * @author Yahav Zarfati <yahavz100@gmail.com>
 * @version 1.0
 * @since 2020-05-01
 */
/**
 * The type Rectangle.
 */
public class Rectangle {
    private Point upperLeft;
    private double width;
    private double height;

    /**
     * Create a new Rectangle.
     *
     * @param upperLeft the upper left
     * @param width     the width
     * @param height    the height
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
    }

    /**
     * Return a (possibly empty) List of intersection points with the specified line.
     *
     * @param line the line
     * @return list of intersection points, can be empty list
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        java.util.List<Point> listOfIntersections = new ArrayList<>();
        Line lineWidth1 = new Line(this.upperLeft.getX(), this.upperLeft.getY(), this.upperLeft.getX() + width,
                this.upperLeft.getY());
        Line lineWidth2 = new Line(this.upperLeft.getX(), this.upperLeft.getY() + this.height,
                this.upperLeft.getX() + this.width, this.upperLeft.getY() + this.height);
        Line lineHeight1 = new Line(this.upperLeft.getX(), this.upperLeft.getY(), this.upperLeft.getX(),
                this.upperLeft.getY() + this.height);
        Line lineHeight2 = new Line(this.upperLeft.getX() + this.width, this.upperLeft.getY(),
                this.upperLeft.getX() + this.width, this.upperLeft.getY() + this.height);
        //Check if there is intersection between line to rectangle line
        if (line.intersectionWith(lineHeight1) != null) {
            //Check if intersection point already in list
            if (!(checkIfPointInList(listOfIntersections, line.intersectionWith(lineHeight1)))) {
                listOfIntersections.add(line.intersectionWith(lineHeight1));
            }
        }
        //Check if there is intersection between line to rectangle line
        if (line.intersectionWith(lineHeight2) != null) {
            //Check if intersection point already in list
            if (!(checkIfPointInList(listOfIntersections, line.intersectionWith(lineHeight2)))) {
                listOfIntersections.add(line.intersectionWith(lineHeight2));
            }
        }
        //Check if there is intersection between line to rectangle line
        if (line.intersectionWith(lineWidth1) != null) {
            //Check if intersection point already in list
            if (!(checkIfPointInList(listOfIntersections, line.intersectionWith(lineWidth1)))) {
                listOfIntersections.add(line.intersectionWith(lineWidth1));
            }
        }
        //Check if there is intersection between line to rectangle line
        if (line.intersectionWith(lineWidth2) != null) {
            //Check if intersection point already in list
            if (!(checkIfPointInList(listOfIntersections, line.intersectionWith(lineWidth2)))) {
                listOfIntersections.add(line.intersectionWith(lineWidth2));
            }
        }
        return listOfIntersections;
    }

    /**
     * Return the width of the rectangle.
     *
     * @return the width
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * Return the height of the rectangle.
     *
     * @return the height
     */
    public double getHeight() {
        return  this.height;
    }

    /**
     * Returns the upper-left point of the rectangle.
     *
     * @return the upper left
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }

    /**
     * Check if point in list.
     *
     * @param listPoints list with points
     * @param p1         point to be checked
     * @return true if list contains point \
     *         if list doesn't contain point
     */
    public boolean checkIfPointInList(java.util.List<Point> listPoints, Point p1) {
        //Run on whole list
        for (int i = 0; i < listPoints.size(); i++) {
            //Check if list contains given point
            if (listPoints.contains(p1)) {
                return true;
            }
        }
        return false;
    }
}