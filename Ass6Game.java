import biuoop.GUI;
import biuoop.KeyboardSensor;
import game.GreenThree;
import game.DirectHit;
import game.FinalFour;
import game.WideEasy;
import game.LevelInformation;
import game.AnimationRunner;
import game.GameFlow;
import java.util.List;
import java.util.ArrayList;

/**
 * @author Yahav Zarfati <yahavz100@gmail.com>
 * @version 1.0
 * @since 2020-06-15
 */

/**
 * The type Ass 6 game includes a main method to play the game.
 */
public class Ass6Game {

    /**
     * Initialize a new game and runs it.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        GUI gui = new biuoop.GUI("Game", 800, 600);
        List<LevelInformation> levels = new ArrayList<>();
        int framesPerSecond = 60;
        final int numOfLevels = 4;
        //Check regular case of all levels
        if (args.length == 1) {
            //Create levels objects and a list contains each
            LevelInformation level1 = new DirectHit();
            LevelInformation level2 = new WideEasy();
            LevelInformation level3 = new GreenThree();
            LevelInformation level4 = new FinalFour();
            levels.add(level1);
            levels.add(level2);
            levels.add(level3);
            levels.add(level4);
        } else {
            //Run check and create each level accroding to input
            for (int i = 0; i < args.length; i++) {
                //Check if input is number
                if (args[i].matches("\\d+")) {
                    int levelIndex = Integer.parseInt(args[i]);
                    //Check if number is existing level
                    if (levelIndex <= numOfLevels && levelIndex != 0) {
                        levels.add(intoLevelInformation(levelIndex));
                    }
                }
            }
        }
        //Setup game and run
        AnimationRunner runner = new AnimationRunner(gui, framesPerSecond);
        KeyboardSensor keyboard = gui.getKeyboardSensor();
        GameFlow gameFlow = new GameFlow(runner, keyboard, gui);
        gameFlow.runLevels(levels);
    }

    /**
     * Insert into level information level index.
     *
     * @param levelIndex the level index
     * @return the level information
     */
    public static LevelInformation intoLevelInformation(int levelIndex) {
        switch (levelIndex) {
            case 1:
                return new DirectHit();
            case 2:
                return new WideEasy();
            case 3:
                return new GreenThree();
            case 4:
                return new FinalFour();
            default:
                return null;
        }

    }
}
