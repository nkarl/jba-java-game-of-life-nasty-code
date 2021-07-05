package life.engine;

public abstract class Model {
    enum LOCALE {CENTER, BORDER}

    enum STATE {ALIVE, DEAD}

    int size;
    protected int[][] map;

    Model(int size) {
        this.size = size;
        this.map = new int[size][size];
    }

    public void propagate(int gens) {}
    void generate() {}
    void live() {}
    void locate() {}
    void atCenter() {}
    void atBorder() {}
}
