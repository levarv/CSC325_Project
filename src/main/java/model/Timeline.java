package model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Timeline {
    private enum Scale {TENSECOND, MINUTE, HOUR, DAY, WEEK, MONTH;} //type of timeline
    private final LinkedList<Timeframe> listOfTimeframes; //list of all timeframes TODO (this is a stand-in structure)
    private final Map<String, Integer> genreCountMap = Main.getGenreCountMap(); //TODO implement genre count persistence
    private LocalDateTime refreshTime; //the time a new timeframe is created
    private Scale interval; //type of time frame
    private Timeframe currentFrame; //current timeframe songs are being added to
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
        listOfTimeframes = new LinkedList<>();
        refreshTime = updateRefreshTime();
        currentFrame = new Timeframe(interval.name(), this);
    }

    /**
     * updateRefreshTime
     * sets the start of the timeline or the beginning
     * of the next timeframe in the timeline.
     * @return see
     */
    public LocalDateTime updateRefreshTime() {
        if (refreshTime == null)
            return LocalDateTime.now();

        return switch (interval) {
            case TENSECOND -> refreshTime.plusSeconds(10L);
            case MINUTE -> refreshTime.plusMinutes(1L);
            case HOUR -> refreshTime.plusHours(1L);
            case DAY -> refreshTime.plusDays(1L);
            case WEEK -> refreshTime.plusWeeks(1L);
            case MONTH -> refreshTime.plusMonths(1L);
        };
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

    /**
     * addSong
     * adds song to the current timeframe in the
     * timeline
     * @param song
     */
    public void addSong(Song song) {
        currentFrame.add(song);
        System.out.println(song);
        if (LocalDateTime.now().isAfter(refreshTime)) {
            listOfTimeframes.add(currentFrame);
            currentFrame = new Timeframe(interval.name(), this);
            refreshTime = updateRefreshTime();
        }
    }

    /**
     * mostPopularGenre
     * @return mostPopularGenre
     */
    public String mostPopularGenre() {
        Map<String, Integer> map = genreCountMap;
        String mostPopularGenre = null;
        int max = 0;

        for (String s : map.keySet())
            if (max < map.get(s)) {
                max = map.get(s);
                mostPopularGenre = s;
            }

        return mostPopularGenre;
    }

    /**
     * mostPopularArtist
     * @return mostPopularArtist
     */
    public String mostPopularArtist() {
        Map<String, Integer> map = artistCountMap;
        String mostPopularArtist = null;
        int max = 0;

        for (String s : map.keySet())
            if (max < map.get(s)) {
                max = map.get(s);
                mostPopularArtist = s;
            }

        return mostPopularArtist;

    }

    /**
     * averageBPM
     * @return averageBPM
     */
    public Double averageBPM() {
        double sum = 0;
        double count = 0;
        for (Timeframe t : listOfTimeframes) {
            sum += t.averageBPM();
            ++count;
        }
        if (count == 0)
            return null;
        else
            return sum / count;
    }
}
