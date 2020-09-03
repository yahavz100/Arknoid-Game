package collisiondetection;
import geometry.Line;
import geometry.Point;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Yahav Zarfati <yahavz100@gmail.com>
 * @version 1.0
 * @since 2020-05-01
 */
/**
 * The type Game environment.
 */
public class GameEnvironment {
    private List<Collidable> collidables = new ArrayList<Collidable>();
    /**
     * Add the given collidable to the environment.
     *
     * @param c a collidable
     */
    public void addCollidable(Collidable c) {
        this.collidables.add(c);
    }

    /**
     * Remove given collidable from the environment.
     *
     * @param c a collidable
     */
    public void removeCollidable(Collidable c) {
        this.collidables.remove(c);
    }

    /**
     * If this object will not collide with any of the collidables
     * in this collection, return null \
     * Else, return the information
     * about the closest collision that is going to occur.
     *
     * @param trajectory a trajectory
     * @return the closest collision info
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        CollisionInfo info1 = null;
        //Check if the collidables list is empty or no trajectory
        if (this.collidables.isEmpty() || trajectory == null) {
            return info1;
        }
        Point p2 = trajectory.start();
        Point p1;
        double minDist = Double.MAX_VALUE;
        boolean f1 = false;
        //Run on whole list
        List<Collidable> copyCollidables = new ArrayList<Collidable>(this.collidables);
        for (int i = 0; i < copyCollidables.size(); i++) {
            p1 = trajectory.closestIntersectionToStartOfLine(copyCollidables.get(i).getCollisionRectangle());
            //Check if there is a collision
            if (p1 != null) {
                f1 = true;
                //Check if current collision is closer than prev
                if (p1.distance(p2) < minDist) {
                    minDist = p1.distance(p2);
                    info1 = new CollisionInfo(copyCollidables.get(i), p1);
                }
            }
        }
        //There is no collision
        if (!f1) {
            return null;
        }
        return info1;
    }
}

