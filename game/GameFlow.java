package game;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import java.util.List;

/**
 * @author Yahav Zarfati <yahavz100@gmail.com>
 * @version 1.0
 * @since 2020-06-15
 */
/**
 * The type Game flow.
 */
public class GameFlow {
    private AnimationRunner ar;
    private KeyboardSensor ks;
    private GUI gui;

    /**
     * Instantiates a new Game flow.
     *
     * @param ar  the animation runner
     * @param ks  the keyboard sensor
     * @param gui the gui
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks, GUI gui) {
        this.ar = ar;
        this.ks = ks;
        this.gui = gui;
    }

    /**
     * Run levels.
     *
     * @param levels the levels
     */
    public void runLevels(List<LevelInformation> levels) {
        KeyPressStoppableAnimation keyPress;
        Counter score = new Counter();
        boolean exitStatus = false;
        //Initialize and run each level
        for (LevelInformation levelInfo : levels) {
            GameLevel level = new GameLevel(levelInfo, this.ks, this.ar, score);
            level.initialize();
            level.run();
            //Check if no balls in game to end it
            if (level.getBallsCounter() == 0) {
                exitStatus = true;
                break;
            }
        }
        //Show screen according to right case
        if (exitStatus) {
            keyPress = new KeyPressStoppableAnimation(this.ks, "space", new GameOver(score));
            this.ar.run(keyPress);
            gui.close();
        } else {
            keyPress = new KeyPressStoppableAnimation(this.ks, "space", new YouWin(score));
            this.ar.run(keyPress);
            gui.close();
        }
    }
}
