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
        timeline = new Timeline("TENSECOND");
        Random rng = new Random();
        while (running) {
            try {
                sleep(rng.nextLong(1200,8000));
            } catch (InterruptedException e) {
                interrupt();
            }
            timeline.addSong(Song.randomSong());
            System.out.println(timeline.averageBPM());
            System.out.println(timeline.averageLoudness());
            System.out.println(timeline.mostPopularGenre());
            System.out.println(timeline.mostPopularArtist());

        }
    }

    @Override
    public void interrupt() {
        running = false;
    }
}
