package model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

public class Timeframe {

    private final Scale scale; //type of timeline
    private final LocalDateTime created = LocalDateTime.now(); //start of this timeframe
    private final ArrayList<Song> musicList; //list of songs listened to in this timeframe
    private final Timeline myTimeline; //the corresponding timeline
    /**
     * constructor
     *
     * @param s          timeline type
     * @param myTimeline the corresponding timeline
     */
    public Timeframe(String s, Timeline myTimeline) {
        scale = Scale.valueOf(s);
        this.myTimeline = myTimeline;
        musicList = new ArrayList<>();
    }

    /**
     * add
     *
     * @param song a song that was listened to in this timeframe
     */
    public void add(Song song) {
        musicList.add(song);
        myTimeline.getGenreCountMap().compute(
                song.getGenre(),
                (k, count) -> {
                    return (count == null ? 0 : count) + 1;
                }
        );

    }

    /**
     * topGenre
     *
     * @return topGenre with a similar methodology to Song class
     */
    public String topGenre() {
        HashMap<String, Integer> map = Main.getGenreCountMap();
        String mostPopularGenre = null;
        int max = 0;

        for (Song s : musicList)
            map.compute(
                    s.getGenre(),
                    (k, count) -> {
                        return (count == null ? 0 : count) + 1;
                    }
            );

        for (String s : map.keySet())
            if (Math.max(max, map.get(s)) == map.get(s)) {
                max = map.get(s);
                mostPopularGenre = s;
            }

        return mostPopularGenre;
    }

    /**
     * averageBPM
     *
     * @return average bpm with a similar methodology to Song class
     */
    public int averageBPM() {
        int sum = 0;
        int n = 0;

        for (Song s : musicList) {
            int bpm = s.getBpm();
            if (bpm > 0) {
                sum += bpm;
                ++n;
            }
        }
        if (n == 0)
            return -1;
        else
            return (sum / n);
    }

    /**
     * mostPopularArtist
     *
     * @return most popular artist with a similar methodology to Song class
     */
    public String mostPopularArtist() {
        HashMap<String, Integer> map = new HashMap<>();
        String mostPopularArtist = null;
        int max = 0;
        for (Song s : musicList)
            map.compute(
                    s.getArtist(),
                    (k, count) -> {
                        return (count == null ? 0 : count) + 1;
                    }
            );

        for (String s : map.keySet())
            if (max < map.get(s)) {
                max = map.get(s);
                mostPopularArtist = s;
            }

        return mostPopularArtist;
    }

    /**
     * toString
     *
     * @return string representation of timeframe
     */
    @Override
    public String toString() {
        return "testbench.TimeFrame{" +
                "scale=" + scale +
                ", created=" + created +
                ", musicList=" + musicList +
                '}';
    }

    private enum Scale {TENSECOND, MINUTE, HOUR, DAY, WEEK, MONTH;} //timeline types
}
