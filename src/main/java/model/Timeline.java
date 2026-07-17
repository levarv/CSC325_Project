package model;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Timeline {
    private enum Scale {TENSECOND, MINUTE, HOUR, DAY, WEEK, MONTH;} //type of timeline
    private final LinkedList<Song> songs; //list of all timeframes TODO (this is a stand-in structure)
    private final Map<String, Integer> genreCountMap = Main.getGenreCountMap(); //TODO implement genre count persistence
    private Scale interval; //type of timeline
    private final Map<String, Integer> artistCountMap = Main.getArtistCountMap(); //TODO implement artist count persistence
    private final double averageBPM = Main.getAverageBPM(); //TODO implement average BPM persistence

    /**
     * constructor
     * @param scale
     */
    public Timeline(String scale) {
        try {
            interval = Scale.valueOf(scale);
        } catch (IllegalArgumentException e) {
            System.out.println("Acceptable arg: TENSECOND | MINUTE | HOUR | DAY | WEEK | MONTH");
        }
        songs = new LinkedList<>();
    }

    /**
     * establishCutOff
     * Used to disregard older listens
     * @return see
     */
    public boolean establishCutOff(LocalDateTime dateOfSong) {
        LocalDateTime then = null;

        switch (interval) {
            case TENSECOND -> then = LocalDateTime.now().minusSeconds(10L);
            case MINUTE -> then = LocalDateTime.now().minusMinutes(1L);
            case HOUR -> then = LocalDateTime.now().minusHours(1L);
            case DAY -> then = LocalDateTime.now().minusDays(1L);
            case WEEK -> then = LocalDateTime.now().minusWeeks(1L);
            case MONTH -> then = LocalDateTime.now().minusMonths(1L);
        };
        //if before the cutoff, return false
        return !dateOfSong.isBefore(then);
    }

    /**
     * getAverageBPM
     * @return averageBpm
     */
    public Double getAverageBPM() { return averageBPM; }

    /**
     * getGenreCountMap
     * @return genreCountMap
     */
    public Map<String, Integer> getGenreCountMap() {
        return genreCountMap;
    }

    /**
     * getArtistCountMap
     * @return artistCountMap
     */
    public Map<String, Integer> getArtistCountMap() {
        return artistCountMap;
    }

    public void add(Song song) {
        songs.add(song);
        genreCountMap.compute(
                song.getGenre(),
                (k, count) -> {
                    return (count == null ? 0 : count) + 1;
                }
        );
        artistCountMap.compute(
                song.getArtist(),
                (k, count) -> {
                    return (count == null ? 0 : count) + 1;
                }
        );

    }

    /**
     * topGenre
     * @return topGenre with a similar methodology to Song class
     */
    public String topGenre() {
        HashMap<String, Integer> map = Main.getGenreCountMap();
        String mostPopularGenre = null;
        int max = 0;

        for (Song s : songs)
            if (establishCutOff(s.getLastListen()))
                map.compute(
                        s.getGenre(),
                        (k, count) -> {
                            return (count == null ? 0 : count) + 1;
                        }
                );
            else songs.remove(s);

        for (String s : map.keySet())
            if (max < map.get(s)) {
                max = map.get(s);
                mostPopularGenre = s;
            }

        return mostPopularGenre;
    }

    /**
     * averageBPM
     * @return average bpm with a similar methodology to Song class
     */
    public int averageBPM() {
        int sum = 0;
        int n = 0;
        int bpm = 0;

        for (Song s : songs) {
            if (establishCutOff(s.getLastListen())) {
                bpm = s.getBpm();
                if (bpm > 0) {
                    sum += bpm;
                    ++n;
                }
            }
            else songs.remove(s);
        }

        if (n == 0)
            return -1;
        else
            return (sum / n);
    }

    /**
     * mostPopularArtist
     * @return most popular artist with a similar methodology to Song class
     */
    public String mostPopularArtist() {
        HashMap<String, Integer> map = Main.getArtistCountMap();
        String mostPopularArtist = null;
        int max = 0;

        for (Song s : songs)
            if (establishCutOff(s.getLastListen()))
                map.compute(
                        s.getArtist(),
                        (k, count) -> {
                            return (count == null ? 0 : count) + 1;
                        }
                );
            else songs.remove(s);

        for (String s : map.keySet())
            if (max < map.get(s)) {
                max = map.get(s);
                mostPopularArtist = s;
            }

        return mostPopularArtist;
    }
    /**
     * toString
     * @return string representation of timeframe
     */
    @Override
    public String toString() {
        return "testbench.TimeFrame{" +
                "scale=" + interval +
                ", musicList=" + songs +
                '}';
    }
}
