import java.util.Scanner;
import life.Universe;

public class test {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int size = scanner.nextInt();
        int seed = scanner.nextInt();
        Universe world = new Universe(size, seed);

        world.view();
    }
}
