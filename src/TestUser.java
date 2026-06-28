import java.util.Date;
import java.util.Random;
/*
Timescale is 10000
 */
public class TestUser extends Thread{
private String name;
public TestUser(String name, ThreadGroup g) {super(g, name);}
private TimeLine minuteLongTimeFrame;

    public void run(){
        minuteLongTimeFrame = new TimeLine("MINUTE");
        Random rng = new Random();
        while (true) {
            try {
                sleep(rng.nextLong(2000,4000));
            } catch (InterruptedException e) {
                interrupt();
            }
            minuteLongTimeFrame.addSong(Song.randomSong());
        }
    }

    @Override
    public void interrupt() {
        System.out.println(minuteLongTimeFrame.getAnalytics());
        super.interrupt();
    }
}
