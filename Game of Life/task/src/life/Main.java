package life;

import life.engine.Life;

import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
//        int N = scanner.nextInt();
//        int S = scanner.nextInt();
//        int M = scanner.nextInt();
//        Life world = new Life(N, S);
        var inArgs = Stream.of(scanner.nextLine().split(" "))
                .map(String::new)
                .collect(Collectors
                .toList());
        scanner.close();

        int size, seed, gens = 10;
        Life world;
        if (inArgs.size() == 1) {
            world = new Life(Integer.parseInt(inArgs.remove(0)));
        } else {
            size = Integer.parseInt(inArgs.remove(0));
            seed = Integer.parseInt(inArgs.remove(0));
            gens = Integer.parseInt(inArgs.remove(0));
            world = new Life(size, seed);
        }

        world.propagate(gens);

        scanner.close();
    }
}
