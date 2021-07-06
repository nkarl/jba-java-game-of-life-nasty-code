package life.engine;

import java.util.Random;

/*
    Reference Gist from HeyMilkshake, moderator @JetBrains Academy's Discord
    https://gist.github.com/msmilkshake/f887e0fed34521117c44be29a4471fdc
 */
public class Life extends Model implements View {
    /**
     * The Life Constructor.
     *
     * @param size size of this map.
     * @param seed the seeded value for randomization.
     */
    public Life(int size, long seed) {
        super(size);
        Random random = new Random(seed);
        for (int i = 0; i < size; ++i) {
            for (int j = 0; j < size; ++j)
                this.map[i][j] = (random.nextBoolean()) ? 1 : 0;
        }
    }

    /**
     * @param gens the total number of generations to propagate through.
     */
    public void propagate(int gens) {
        if (gens > 0)
            for (int i = 1; i < gens; ++i) generate();
        view();
    }


    /**
     * Determine the state of life/death for the current generation of cells.
     */
    void generate() {
        for (int i = 0; i < this.size - 1; ++i) {
            for (int j = 0; j < this.size - 1; ++j)
                this.map[i][j] = (live(i, j) == STATE.ALIVE) ?
                        1 : 0;
        }
    }


    /**
     * Check the locale of cell, who lives either at CENTER or BORDER.
     *
     * @param a x coordinate
     * @param b y coordinate
     * @return the locale of the cell
     */
    LOCALE locate(int a, int b) {
        return ((a > 0 && a < this.size) && (b > 0 && b < this.size)) ?
                LOCALE.CENTER : LOCALE.BORDER;
    }


    STATE live(int a, int b) {
        int neighbors = 0, r, c;
        for (int i = -1; i < 3; ++i) {
            r = wrapBorder(i);
            for (int j = -1; j < 3; ++j) {
                c = wrapBorder(j);
                if (r == a && c == b) continue;
                neighbors += (this.map[r][c] == 1) ? 1 : 0;
                if (neighbors == 3) break;
            }
        }
        if (neighbors == 2 || neighbors == 3) {
            return (this.map[a][b] == 1) ? STATE.DEAD : STATE.ALIVE;
        }
        else return STATE.DEAD;
    }

    int wrapBorder(int coordinate) {
        return (coordinate + this.size) % this.size;
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
