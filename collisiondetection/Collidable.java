package collisiondetection;
import geometry.Ball;
import geometry.Point;
import geometry.Rectangle;
import geometry.Velocity;

/**
 * @author Yahav Zarfati <yahavz100@gmail.com>
 * @version 1.0
 * @since 2020-05-01
 */
/**
 * The interface shapes.Collidable.
 */
public interface Collidable {
    /**
     * Gets collision shape of the object.
     *
     * @return the collision shape
     */
    Rectangle getCollisionRectangle();

    /**
     * Notify the object that we collided with it at collisionPoint with a given velocity.
     *
     * @param hitter          the  ball hitter
     * @param collisionPoint  the collision point
     * @param currentVelocity the current velocity
     * @return the velocity after collision
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}