package life.engine;

import java.util.Random;

public class Life extends Model implements View {
    enum LOCALE {CENTER, BORDER}

    enum STATE {ALIVE, DEAD}

    int MIN, MAX;
    int gen;

    /**
     * Constructor.
     * @param size size of this map.
     * @param seed the seeded value for the randomizing algorithm.
     */
    public Life(int size, int seed) {
        super(size);
        this.MIN = 0;
        this.MAX = this.size - 1;
        Random random = new Random(seed);
        for (int i = 0; i < size; ++i) {
            for (int j = 0; j < size; ++j) {
                if (random.nextBoolean()) this.map[i][j] = 1;
                else this.map[i][j] = 0;
            }
        }
    }


    /**
     * @param gens the total number of generations to propagate through.
     */
    public void propagate(int gens) {
        for (int i = this.gen; i < gens; ++i) {
            generate();
            view();
        }
    }


    /**
     * @param a the x coordinate
     * @param b the y coordinate
     * @return the state of the cell
     */
    STATE liveOrDie(int a, int b) {
        LOCALE location;
        location = check_location(a, b);
        return (location == LOCALE.CENTER) ?
                atCenter(a, b) :
                atBorder(a, b);
    }


    /**
     * Check the location of the cell, who lives either at CENTER or BORDER.
     * @param a x coordinate
     * @param b y coordinate
     * @return the locale of the cell
     */
    LOCALE check_location(int a, int b) {
        return ((a > 0 && a < this.size) && (b > 0 && b < this.size)) ?
                LOCALE.CENTER :
                LOCALE.BORDER;
    }


    /**
     * Determine the state of life/death for the current generation's cells.
     */
    void generate() {
        STATE cell;
        for (int i = 0; i < this.size - 1; ++i) {
            for (int j = 0; j < this.size - 1; ++j) {
                cell = liveOrDie(i, j);
                this.map[i][j] = (cell == STATE.ALIVE) ? 1 : 0;
            }
        }
    }


    /**
     * Determine cell's life the borders.
     * @param a x coordinate
     * @param b y coordinate
     * @return the state of the cell
     */
    STATE atBorder(int a, int b) {
        int neighbors = 0;
        int r = (a == 0) ? MAX : a - 1;  // row sentinel
        for (int i = 0; i < 3; ++i) {
            int c = (b == 0) ? MAX : b - 1;  // column sentinel
            for (int j = 0; j < 3; ++j) {
                if (r == a && c == b) continue;
                if (this.map[r][c] == 1) ++neighbors;
                if (neighbors > 3) break;
                ++c;
                if (c > MAX) c = MIN; // wrap around
            }
            ++r;
            if (r > MAX) r = MIN; // wrap around
        }
        return (neighbors < 2 || neighbors > 3) ? STATE.DEAD : STATE.ALIVE;
    }


    /**
     * Determine cell's life at the center.
     * @param a x coordinate
     * @param b y coordinate
     * @return the state of the cell
     */
    STATE atCenter(int a, int b) {
        int neighbors = 0;
        for (int i = a - 1; i <= (a + 1); ++i) {
            for (int j = b - 1; j <= (b + 1); ++j) {
                if (i == a && j == b) continue;
                if (this.map[i][j] == 1) ++neighbors;
                if (neighbors > 3) break;
            }
        }
        return (neighbors < 2 || neighbors > 3) ? STATE.DEAD : STATE.ALIVE;
    }


    /**
     * Interface: view the map of life.
     */
    @Override
    public void view() {
        System.out.println("This generation:");
        System.out.println("--------------------------------------------------------------");
        for (var row : this.map) {
            for (int cell : row) {
                if (cell == 1) System.out.print('O');
                else System.out.print(' ');
            }
            System.out.print('\n');
        }
        System.out.println();
    }
}
