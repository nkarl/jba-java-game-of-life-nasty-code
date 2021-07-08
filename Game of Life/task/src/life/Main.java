package life;

import life.engine.Life;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int S = scanner.nextInt();
        int M = scanner.nextInt();
        Life world = new Life(N, S);

        world.propagate(M);
        world.view();
    }
}
