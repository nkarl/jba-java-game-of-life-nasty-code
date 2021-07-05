package life.engine;

import java.util.Random;

/*
    Reference Gist from HeyMilkshake, moderator @JetBrains Academy's Discord
    https://gist.github.com/msmilkshake/f887e0fed34521117c44be29a4471fdc
 */
public class Life extends Model implements View {
    enum LOCALE {CENTER, BORDER}

    enum STATE {ALIVE, DEAD}

    /**
     * The Life Constructor.
     * @param size size of this map.
     * @param seed the seeded value for the randomizing algorithm.
     */
    public Life(int size, long seed) {
        super(size);
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
        if (gens > 0) {
            for (int i = 1; i <= gens; ++i) {
                generate();
            }
        }
        view();
    }


    /**
     * @param a the x coordinate
     * @param b the y coordinate
     * @return the state of the cell
     */
    STATE liveOrDie(int a, int b) {
        LOCALE location;
        location = locate(a, b);
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
    LOCALE locate(int a, int b) {
        return ((a > 0 && a < this.size) && (b > 0 && b < this.size)) ?
                LOCALE.CENTER :
                LOCALE.BORDER;
    }


    /**
     * Determine the state of life/death for the current generation of cells.
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
     * Determine a cell's life at the borders.
     * @param a x coordinate
     * @param b y coordinate
     * @return the state of the cell
     */
    STATE atBorder(int a, int b) {
        int neighbors = 0;
        int MIN = 0;
        int MAX = this.size - 1;

        int r = (a == 0) ? MAX : a - 1;  // row sentinel
        for (int i = 0; i < 3; ++i) {
            int c = (b == 0) ? MAX : b - 1;  // column sentinel
            for (int j = 0; j < 3; ++j) {
                if (this.map[r][c] == 1) ++neighbors;
                if (neighbors > 3) break;
//                neighbors += (this.map[r][c] == 1) ? 1 : 0;
                ++c;
                if (c > MAX) c = MIN; // wrap around
                if (r == a && c == b) --neighbors;  // continue if self
            }
            ++r;
            if (r > MAX) r = MIN; // wrap around
        }
        if (neighbors < 2 || neighbors > 3) {
            return (this.map[a][b] == 1) ?
                    STATE.DEAD :
                    STATE.ALIVE;
        } else return STATE.DEAD;
    }


    /**
     * Determine a cell's life at the center.
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
        if (neighbors < 2 || neighbors > 3) {
            return (this.map[a][b] == 1) ?
                    STATE.DEAD :
                    STATE.ALIVE;
        } else return STATE.DEAD;
    }


    /**
     * Interface: view the map of life.
     */
    @Override
    public void view() {
        for (var row : this.map) {
            for (int cell : row) {
                if (cell == 1) System.out.print('O');
                else System.out.print(' ');
            }
            System.out.println();
        }
    }
}
