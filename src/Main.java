import static java.lang.Thread.sleep;

public class Main {
    public static void main(String[] args) {
        ThreadGroup g = new ThreadGroup("g");
        TestUser u = new TestUser("bob", g);

        u.start();

        try {
            sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        u.interrupt();
    }
}
