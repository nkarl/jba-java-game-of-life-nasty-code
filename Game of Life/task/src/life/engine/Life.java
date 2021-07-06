package life.engine;

import java.util.Random;

/*
    Reference Gist from HeyMilkshake, moderator @JetBrains Academy's Discord
    https://gist.github.com/msmilkshake/f887e0fed34521117c44be29a4471fdc
 */
public class Life extends Model implements View {

    public Life(int size, long seed) {
        super(size);
        Random random = new Random(seed);
        for (int i = 0; i < size; ++i) {
            for (int j = 0; j < size; ++j)
                this.map[i][j] = (random.nextBoolean()) ? 1 : 0;
        }
    }

    @Override
    public void propagate(int gens) {
        for (int i = 0; i < gens; ++i) {
            generate();
        }
    }

    @Override
    void generate() {
        for (int i = 0; i < this.size - 1; ++i) {
            for (int j = 0; j < this.size - 1; ++j)
                this.map[i][j] = live(i, j) ? 1 : 0;
        }
    }

    boolean live(int row, int col) {
        int count = countNeighbors(row, col);
        return count == 2 || count == 3;
    }

    int countNeighbors(int row, int col) {
        int count = 0;
        int r, c;

        for (int i = -1; i < 2; ++i) {
            r = wrapBorder(i + row);
            for (int j = -1; j < 2; ++j) {
                c = wrapBorder(j + col);
                if (r == row && c == col) continue;
                count += (this.map[r][c] == 1) ? 1 : 0;
            }
        }
        return count;
    }

    int wrapBorder(int coordinate) {
        return (coordinate) % this.size;
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
