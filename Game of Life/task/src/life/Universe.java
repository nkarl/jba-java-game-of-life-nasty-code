package life;

import java.util.Random;

public class Universe extends Model implements View {

    public Universe(int size, int seed) {
        super(size);

        Random rnd = new Random(seed);

        for (int i = 0; i < size; ++i) {
            for (int j = 0; j < size; ++j) {
                if (rnd.nextBoolean()) this.map[i][j] = 1;
                else this.map[i][j] = 0;
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
