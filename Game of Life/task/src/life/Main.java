package life;

import life.engine.Life;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int size = scanner.nextInt();
        long seed = scanner.nextLong();
        int gens = scanner.nextInt();
        Life world = new Life(size);

        world.propagate(gens);
    }
}
