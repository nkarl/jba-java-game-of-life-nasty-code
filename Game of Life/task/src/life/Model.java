package life;

public abstract class Model {
    int size;
    int gen;
    protected int[][] map;

    Model(int size) {
        this.size = size;
        this.map = new int[size][size];
        this.gen = 0;
    }

    void resolve_gen(int i, int j) {}
    void propagate() {}
}
