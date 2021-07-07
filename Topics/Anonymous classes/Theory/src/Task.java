// You can experiment here, it won’t be checked

public class Task {
    public static void main(String[] args) {
        // put your code here
        SingleMethodInterface instance = new SingleMethodInterface() {
            @Override
            public void doSomething() {
                System.out.println("The anonymous class does something");
            }
        };
        instance.doSomething();
    }
}

interface SingleMethodInterface {
    void doSomething();
}