package game;
import biuoop.DrawSurface;
import collisiondetection.Collidable;
import geometry.Rectangle;
import geometry.Velocity;
import geometry.Point;
import geometry.Line;
import sprites.Sprite;
import geometry.Ball;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Yahav Zarfati <yahavz100@gmail.com>
 * @version 3.0
 * @since 2020-06-15
 */
/**
 * The type shapes.Block.
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private Rectangle rect;
    private Color color;
    private List<HitListener> hitListeners = new ArrayList<>();

    /**
     * Defines a new shapes.Block.
     *
     * @param rect  a rectangle
     * @param color the color of this block
     */
    public Block(Rectangle rect, Color color) {
        this.rect = rect;
        this.color = color;
    }

    /**
     * Draw this block on given draw surface.
     *
     * @param surface the surface
     */
    public void drawOn(DrawSurface surface) {
        int x = (int) rect.getUpperLeft().getX();
        int y = (int) this.rect.getUpperLeft().getY();
        int width = (int) this.rect.getWidth();
        int height = (int) this.rect.getHeight();
        surface.setColor(this.color);
        surface.fillRectangle(x, y, width, height);
        surface.setColor(Color.black);
        surface.drawRectangle(x, y, width, height);
    }

    /**
     * Notify the sprite that time has passed.
     */
    @Override
    public void timePassed() { }

    /**
     * Gets collision shape.
     *
     * @return the collision block (rectangle)
     */
    @Override
    public Rectangle getCollisionRectangle() {
        return this.rect;
    }

    /**
     * Hit velocity.
     *
     * @param hitter the ball hitter
     * @param collisionPoint  the collision point
     * @param currentVelocity the current velocity
     * @return the velocity after collision
     */
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        this.notifyHit(hitter);
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
        Point rightVertexUp = new Point(xWidth, yHeight);
        Point rightVertexDown = new Point(this.rect.getUpperLeft().getX() + this.rect.getWidth(),
                this.rect.getUpperLeft().getY() + this.rect.getHeight());
        Point leftVertexDown = new Point(this.rect.getUpperLeft().getX(), this.rect.getUpperLeft().getY()
                + this.rect.getHeight());
        //Check if collision in corners
        if (collisionPoint.distance(rightVertexUp) < 0.2 || collisionPoint.distance(rightVertexDown) < 0.2
                || collisionPoint.distance(leftVertexDown) < 0.2) {
            return new Velocity(-dx, -dy);
        }
            //Check if collision in left line
        if (lineLeft.checkIfPointInSegment(collisionPoint)) {
            return new Velocity(-dx, dy);
            //Check if collision in right line
        } else if (lineRight.checkIfPointInSegment(collisionPoint)) {
            return new Velocity(-dx, dy);
            //Check if collision in upper line
        } else if (lineUp.checkIfPointInSegment(collisionPoint)) {
            return new Velocity(dx, -dy);
            //Check if collision in lower line
        } else if (lineDown.checkIfPointInSegment(collisionPoint)) {
            return new Velocity(dx, -dy);
        }
        return currentVelocity;
    }

    /**
     * Add this block to game.
     *
     * @param gameLevel to add ball into
     */
    public void addToGame(GameLevel gameLevel) {
        gameLevel.addSprite(this);
        gameLevel.addCollidable(this);
    }

    /**
     * Remove this block from game.
     *
     * @param gameLevel remove block from
     */
    public void removeFromGame(GameLevel gameLevel) {
        gameLevel.removeSprite(this);
        gameLevel.removeCollidable(this);
    }

    /**
     * Add hl as a listener to hit events.
     *
     * @param hl the listener
     */
    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    /**
     * Remove hl from the list of listeners to hit events.
     *
     * @param hl the listener
     */
    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }

    /**
     * Notify there is a hit.
     *
     * @param hitter the ball hitter
     */
    public void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    /**
     * Gets color.
     *
     * @return the color
     */
    public Color getColor() {
        return color;
    }
}
