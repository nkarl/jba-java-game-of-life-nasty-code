package life;

import life.engine.Life;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        var inArgs =
                Stream.of(scanner.nextLine()
                        .split(" "))
                        .map(String::new)
                        .collect(Collectors.toList());
//        var inArgs = new ArrayList<>();
//        for (String elem : scanner.nextLine().split(" ")) {
//            String s = new String(elem);
//            inArgs.add(s);
//        }

        int gens = 10;
        Life world;
        if (inArgs.size() == 1) {
            world = new Life(Integer.parseInt(inArgs.remove(0)));
        } else {
            int size = Integer.parseInt(inArgs.remove(0));
            int seed = Integer.parseInt(inArgs.remove(0));
            gens = Integer.parseInt(inArgs.remove(0));
            world = new Life(size, seed);
        }

        world.propagate(gens);
    }
}
