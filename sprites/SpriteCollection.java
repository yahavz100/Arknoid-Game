package sprites;
import biuoop.DrawSurface;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Yahav Zarfati <yahavz100@gmail.com>
 * @version 1.0
 * @since 2020-05-01
 */
/**
 * The type Sprite collection.
 */
public class SpriteCollection {
    private List<Sprite> sprites = new ArrayList<>();

    /**
     * Add new sprite to sprite collection.
     *
     * @param s new sprite
     */
    public void addSprite(Sprite s) {
        this.sprites.add(s);
    }

    /**
     * Remove given sprite from sprite collection.
     *
     * @param s the sprite
     */
    public void removeSprite(Sprite s) {
        this.sprites.remove(s);
    }

    /**
     * Notify all sprites time passed.
     */
    public void notifyAllTimePassed() {
        List<Sprite> copySprites = new ArrayList<>(this.sprites);
        //Run on all sprites timePassed
        for (Sprite sList: copySprites) {
            sList.timePassed();
        }
    }

    /**
     * Draw all sprites on drawing surface.
     *
     * @param d the drawing surface
     */
    public void drawAllOn(DrawSurface d) {
        List<Sprite> copySprites = new ArrayList<>(this.sprites);
        //Run on all sprites draw on
        for (Sprite sList: copySprites) {
            sList.drawOn(d);
        }
    }
}