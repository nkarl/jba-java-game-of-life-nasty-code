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
                this.map[i][j] = (alive(i, j) == STATE.ALIVE) ?
                        1 : 0;
        }
    }


    STATE alive(int row, int col) {
        int neighbors = 0;
        int r, c;

        for (int i = -1; i < 2; ++i) {
            r = wrapBorder(i);
            for (int j = -1; j < 2; ++j) {
                c = wrapBorder(j);
                if (r == row && c == col) continue;
                neighbors += (this.map[r][c] == 1) ? 1 : 0;
                if (neighbors > 3) break;
            }
        }
        if (neighbors == 2 || neighbors == 3) {
            return STATE.ALIVE;
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
