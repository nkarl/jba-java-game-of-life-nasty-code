package life.engine;

public abstract class Model {
    int size;
    protected int[][] map;

    Model(int size) {
        this.size = size;
        this.map = new int[size][size];
    }

    public void propagate(int gens) {}
    void generate() {}
}
