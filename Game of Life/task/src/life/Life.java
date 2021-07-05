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
            for (int j = 0; j < 3; ++j) {
                if (r == a && c == b) continue;
                if (this.map[r][c] == 1) ++neighbors;
                if (neighbors > 3) break;
                ++c;
                if (c == this.size - 1) c = 0;
            }
            ++r;
            if (r == this.size - 1) r = 0;
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

//    void liveOrDie(int i, int j) {
//        LOCALE location;
//        location = check_location(i, j);
//        switch (location) {
//            case CENTER:
//                break;
//            case BORDER:
//                break;
//        }
//    }

    void this_generation() {
        for (int i = 0; i < this.size; ++i) {
            for (int j = 0; j < this.size; ++j) {
                resolve_gen(i, j);
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
