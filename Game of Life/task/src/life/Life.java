package life;

import java.util.Random;

public class Life extends Model implements View {
    enum LOCALE {CENTER, BORDER}

    enum STATE {ALIVE, DEAD}

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

    /*
        TODO: Figure out how the wrap-around mechanism for the border cells
     */
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
        STATE life = STATE.DEAD;
        location = check_location(i, j);
//        return (location == LOCALE.CENTER) ? atCenter(i, j) : STATE.DEAD;

        switch (location) {
            case CENTER:
                life = atCenter(i, j);
                break;
            case BORDER:
                life = atBorder(i, j);
                break;
        }
        return life;
    }

    void generation() {
        STATE cell;
        for (int i = 1; i < this.size - 1; ++i) {
            for (int j = 1; j < this.size - 1; ++j) {
                cell = liveOrDie(i, j);
                this.map[i][j] = (cell == STATE.ALIVE) ? 1 : 0;
            }
        }
    }


    @Override
    public void propagate(int gens) {
        for (int i = 0; i < gens; ++i) {
            generation();
            this.gen += 1;
            view();
        }
    }

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
