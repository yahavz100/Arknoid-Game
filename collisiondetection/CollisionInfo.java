package collisiondetection;
import geometry.Point;

/**
 * @author Yahav Zarfati <yahavz100@gmail.com>
 * @version 1.0
 * @since 2020-05-01
 */
/**
 * The type Collision info.
 */
public class CollisionInfo {
    private Collidable object;
    private Point collisionPoint;

    /**
     * Instantiates a new Collision info.
     *
     * @param obj            a collidable object
     * @param collisionPoint the collision point
     */
    public CollisionInfo(Collidable obj, Point collisionPoint) {
        this.object = obj;
        this.collisionPoint = collisionPoint;
    }

    /**
     * Returns the point at which the collision occurs.
     *
     * @return the collision point
     */
    public Point collisionPoint() {
        return this.collisionPoint;
    }

    /**
     * Returns the collidable object involved in the collision.
     *
     * @return the collidable object
     */
    public Collidable collisionObject() {
        return this.object;
    }
}