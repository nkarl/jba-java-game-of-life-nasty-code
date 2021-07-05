import java.util.Scanner;
import life.Life;

public class test {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int size = scanner.nextInt();
        int seed = scanner.nextInt();
        int gen = scanner.nextInt();
        Life world = new Life(size, seed);

        world.view();
    }
}
