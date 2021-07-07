package life.engine;

import java.util.Random;

/*
    Reference Gist from HeyMilkshake, moderator @JetBrains Academy's Discord
    https://gist.github.com/msmilkshake/f887e0fed34521117c44be29a4471fdc
 */
public class Life extends Model implements View {
    public Life(int size, long seed) {
        super(size);
        Random rnd = new Random(seed);
        for (int i = 0; i < size; ++i) {
            for (int j = 0; j < size; ++j)
                this.map[i][j] = (rnd.nextBoolean()) ? 1 : 0;
        }
    }

    public Life(int size) {
        super(size);
        Random rnd = new Random();
        for (int i = 0; i < size; ++i) {
            for (int j = 0; j < size; ++j)
                this.map[i][j] = (rnd.nextBoolean()) ? 1 : 0;
        }
    }

    public void propagate(int gens) {
        for (int i = 0; i < gens; ++i) {
            generate();
        }
        view();
    }

    void generate() {
        Model future = new Model(this.size) {};
        for (int i = 0; i < this.size - 1; ++i) {
            for (int j = 0; j < this.size - 1; ++j)
                future.map[i][j] = live(i, j) ? 1 : 0;
        }
        this.map = future.map;
    }

    boolean live(int row, int col) {
        int count = countNeighbors(row, col);
        if (count == 2 || count == 3) {
            return this.map[row][col] == 0;
        }
        return false;
    }

    int countNeighbors(int row, int col) {
        int count = 0;
        int r, c;

        for (int i = -1; i < 2; ++i) {
            r = loopCoordinate(i + row);
            for (int j = -1; j < 2; ++j) {
                c = loopCoordinate(j + col);
                if (r == row && c == col) continue;
                count += (this.map[r][c] == 1) ? 1 : 0;
            }
        }
        return count;
    }

    int loopCoordinate(int coordinate) {
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
