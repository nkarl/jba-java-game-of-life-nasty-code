package life;

public class Model {
    int size;
    protected int[][] map;

    Model(int size) {
        this.size = size;
        this.map = new int[size][size];
    }
}
