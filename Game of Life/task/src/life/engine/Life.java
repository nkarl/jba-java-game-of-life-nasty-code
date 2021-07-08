package life.engine;

import java.util.Random;

public class Life extends Model implements View {

    /**
     * The Life Constructor.
     *
     * @param size size of this map.
     * @param seed the seeded value for the randomizing algorithm.
     */
    public Life(int size, int seed) {
        super(size);
        Random random = new Random();
        for (int i = 0; i < size; ++i) {
            for (int j = 0; j < size; ++j)
                this.map[i][j] = (random.nextBoolean()) ? 1 : 0;
        }
    }

    @Override
    /**
     * @param gens the total number of generations to propagate through.
     */
    public void propagate(int gens) {
        for (int i = 0; i < gens; ++i) {
            generate();
        }
    }

    @Override
    void generate() {
        Model future = new Model(this.size) {};
        for (int row = 0; row < this.size; ++row) {
            for (int col = 0; col < this.size; ++col) {
                int neighbors = neighbors(row, col);
                if (neighbors == 3) {
                    future.map[row][col] = 1;
                }
                else if (neighbors == 2) {
                    future.map[row][col] = this.map[row][col];
                } else {
                    future.map[row][col] = 0;
                }
            }
        }
        this.map = future.map;
    }

    int neighbors(int row, int col) {
        int count = 0;
        for (int i = -1; i < 2; ++i) {
            for (int j = -1; j < 2; ++j) {
                if (i == 0 && j == 0) continue;
                count += checkCell(i + row, j + col);
            }
        }
        return count;
    }

    int checkCell(int row, int col) {
        row = checkIndex(row, this.size);
        col = checkIndex(col, this.size);
        return (this.map[row][col] == 1) ? 1 : 0;
    }

    static int checkIndex(int index, int size) {
        int MAX = size - 1;
        if (index < 0) index = MAX;
        if (index > MAX) index = 0;
        return index;
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
