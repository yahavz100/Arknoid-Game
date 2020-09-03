package game;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import collisiondetection.Collidable;
import collisiondetection.GameEnvironment;
import geometry.Ball;
import geometry.Point;
import geometry.Rectangle;
import sprites.Sprite;
import sprites.SpriteCollection;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Yahav Zarfati <yahavz100@gmail.com>
 * @version 3.0
 * @since 2020-06-15
 */
/**
 * The type Game.
 */
public class GameLevel implements Animation {
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private Counter blockCounter;
    private Counter ballsCounter;
    private Counter score;
    private AnimationRunner runner;
    private boolean running;
    private KeyboardSensor keyboard;
    private LevelInformation levelInfo;

    /**
     * Instantiates a new Game.
     *
     * @param levelInfo the level info
     * @param keyboard  the keyboard
     * @param runner    the runner
     * @param score     the score
     */
    public GameLevel(LevelInformation levelInfo, KeyboardSensor keyboard, AnimationRunner runner, Counter score) {
        this.environment = new GameEnvironment();
        this.sprites = new SpriteCollection();
        this.blockCounter = new Counter();
        this.ballsCounter = new Counter();
        this.score = score;
        this.keyboard = keyboard;
        this.levelInfo = levelInfo;
        this.runner = runner;
    }

    /**
     * Add a collidable to game environment.
     *
     * @param c the collidable
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * Remove a collidable to game environment.
     *
     * @param c the collidable
     */
    public void removeCollidable(Collidable c) {
        this.environment.removeCollidable(c);
    }

    /**
     * Add a sprite to sprite collection.
     *
     * @param s the sprite
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * Remove a sprite from sprite collection.
     *
     * @param s the sprite
     */
    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }

    /**
     * Initialize a new game, create the Blocks,Balls and a Paddle
     * and adds them to the game.
     */
    public void initialize() {
        //Create balls for game
        List<Ball> ballArr = new ArrayList<Ball>();
        for (int i = 0; i < this.levelInfo.numberOfBalls(); i++) {
            int x = 400;
            int y = 500;
            ballArr.add(new Ball(new Point(x, y), 3, Color.WHITE));
            ballArr.get(i).fixedPosRectangle(0, 0, 800, 600);
            ballArr.get(i).setVelocity(levelInfo.initialBallVelocities().get(i));
            ballArr.get(i).setGameEnvironment(this.environment);
            ballArr.get(i).addToGame(this);
        }
        int i = 0;
        //Create ball remover and counter
        Counter ballCounter = this.ballsCounter;
        ballCounter.increase(this.levelInfo.numberOfBalls());
        BallRemover ballRemover = new BallRemover(this, ballCounter);
        //Create death block
        Rectangle deathRec = new Rectangle(new Point(0, 610), 800, 100);
        Block deathBlock = new Block(deathRec, Color.black);
        deathBlock.addToGame(this);
        deathBlock.addHitListener(ballRemover);
        //Create paddle
        int paddleMid = this.levelInfo.paddleWidth() / 2;
        int guiMid = 400;
        int dif = guiMid - paddleMid;
        int yPaddle = 560;
        int step = this.levelInfo.paddleSpeed();
        Rectangle paddle = new Rectangle(new Point(dif, yPaddle), this.levelInfo.paddleWidth(), 20);
        Paddle paddle1 = new Paddle(paddle, Color.YELLOW, this.keyboard, step);
        paddle1.addToGame(this);
        paddle1.setFrame(800);
        //Create frame blocks
        Rectangle rectUpper = new Rectangle(new Point(0, 0), 800, 20);
        Rectangle rectLeft = new Rectangle(new Point(0, 20), 20, 600);
        Rectangle rectRight = new Rectangle(new Point(780, 20), 20, 600);
        Block blockUpper = new Block(rectUpper, Color.GRAY);
        Block blockLeft = new Block(rectLeft, Color.GRAY);
        Block blockRight = new Block(rectRight, Color.GRAY);
        Block[] frame = new Block[]{blockUpper, blockLeft, blockRight};
        //Create block remover and counter
        Counter bRemainCounter = this.blockCounter;
        BlockRemover bRemover = new BlockRemover(this, bRemainCounter);
        //Create block score counter
        Counter scoreCount = this.score;
        ScoreTrackingListener scoreTrack = new ScoreTrackingListener(scoreCount);
        //Add each frame block to the game
        for (i = 0; i < 3; i++) {
            frame[i].addToGame(this);
        }
        //Create row blocks
        bRemainCounter.increase(levelInfo.numberOfBlocksToRemove());
        //Create each block and add it to correct listener
        for (i = 0; i < this.levelInfo.numberOfBlocksToRemove(); i++) {
            this.levelInfo.blocks().get(i).addToGame(this);
            this.levelInfo.blocks().get(i).addHitListener(bRemover);
            this.levelInfo.blocks().get(i).addHitListener(scoreTrack);
        }
        //Create score indicator
        ScoreIndicator textScore = new ScoreIndicator(scoreCount);
        textScore.addToGame(this);
        //Create level name
        LevelIndicator levelName = new LevelIndicator(this.levelInfo.levelName());
        levelName.addToGame(this);
    }

    /**
     * Run the game - start the animation loop.
     */
    public void run() {
        this.running = true;
        this.runner.run(this);
    }

    /**
     * Do one frame.
     *
     * @param d the drawing surface
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        this.levelInfo.getBackground().drawOn(d);
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed();
        //Check if "p" pressed for pause
        if (this.keyboard.isPressed("p")) {
            this.runner.run(new KeyPressStoppableAnimation(this.keyboard, "space", new PauseScreen()));
        }
        //Check if popped all blocks
        if (this.blockCounter.getValue() == 0) {
            this.score.increase(100);
            this.running = false;
        }
        //Check if no balls left in game
        if (this.ballsCounter.getValue() == 0) {
            this.running = false;
        }
    }

    /**
     * In charge of stopping condition.
     *
     * @return the boolean
     */
    @Override
    public boolean shouldStop() {
        return !this.running;
    }

    /**
     * Gets current balls counter.
     *
     * @return current the balls counter
     */
    public int getBallsCounter() {
        return ballsCounter.getValue();
    }
}