package game;

/**
 * @author Yahav Zarfati <yahavz100@gmail.com>
 * @version 1.0
 * @since 2020-05-27
 */
/**
 * The type Counter.
 */
public class Counter {
    private int counter;

    /**
     * Add number to current count.
     *
     * @param number to add to counter
     */
    void increase(int number) {
        this.counter += number;
    }

    /**
     * Subtract number from current count.
     *
     * @param number to substract from counter
     */
    void decrease(int number) {
        this.counter -= number;
    }

    /**
     * Get current count.
     *
     * @return the value of this counter
     */
    int getValue() {
        return this.counter;
    }
}
