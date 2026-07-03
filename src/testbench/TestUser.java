package testbench;

import inference.Timeline;
import model.Song;

import java.util.Random;
/*
    Test Class
 */
public class TestUser extends Thread{
private String name;
public TestUser(String name, ThreadGroup g) {super(g, name);}
private Timeline timeline;
private boolean running = true;

    public void run(){
        timeline = new Timeline("MINUTE");
        Random rng = new Random();
        while (running) {
            try {
                sleep(rng.nextLong(4000,16000));
            } catch (InterruptedException e) {
                interrupt();
            }
            timeline.addSong(Song.randomSong());
        }
    }

    @Override
    public void interrupt() {
        running = false;
    }
}
