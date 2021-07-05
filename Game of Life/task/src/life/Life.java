package life;

import java.util.Random;

public class Life extends Model implements View {
    enum LOCALE { CENTER, BORDER }
    enum STATE { ALIVE, DEAD }

    public Life(int size, int seed) {
        super(size);

        Random rnd = new Random(seed);

        for (int i = 0; i < size; ++i) {
            for (int j = 0; j < size; ++j) {
                if (rnd.nextBoolean()) this.map[i][j] = 1;
                else this.map[i][j] = 0;
            }
        }
    }

    LOCALE check_location(int a, int b) {
        return ((a > 0 && a < this.size) && (b > 0 && b < this.size)) ?
                LOCALE.CENTER :
                LOCALE.BORDER;
    }

    STATE atBorder(int a, int b) {
        int neighbors = 0;
        // I need some sort of sentinels to track the bound of the neighbors
        int r = a - 1; // row sentinel
        int c = b - 1; // column sentinel
        for (int i = 0; i < 3; ++i) {
            if (r == 0) r = this.size - 1;  // wrap around
            for (int j = 0; j < 3; ++j) {
                if (c == 0) c = this.size - 1; // wrap around
                if (r == a && c == b) continue;
                if (this.map[r][c] == 1) ++neighbors;
                if (neighbors > 3) break;
                ++c;
                if (c == this.size - 1) c = 0; // wrap around
            }
            ++r;
            if (r == this.size - 1) r = 0; // wrap around
        }
        return (neighbors < 2 || neighbors > 3) ? STATE.DEAD : STATE.ALIVE;
    }

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

    STATE liveOrDie(int i, int j) {
        LOCALE location;
        STATE life = STATE.ALIVE;
        location = check_location(i, j);

        switch (location) {
            case CENTER:
                life = atCenter(i, j);
                break;
            case BORDER:
                life = atBorder(i, j);
        }
        return life;
    }

    @Override
    public void propagate() {
        for (int i = 0; i < this.size; ++i) {
            for (int j = 0; j < this.size; ++j) {
                liveOrDie(i, j);
            }
        }
    }

    @Override
    public void view() {
        for (var row : this.map) {
            for (int cell : row) {
                if (cell == 1) System.out.print('O');
                else System.out.print(' ');
            }
            System.out.print('\n');
        }
    }
}
