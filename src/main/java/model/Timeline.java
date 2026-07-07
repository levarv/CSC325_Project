package model;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Timeline {

    private final LinkedList<Timeframe> listOfTimeframes; //list of all timeframes TODO (this is a stand-in structure)
    private final Map<String, Integer> genreCountMap = Main.getGenreCountMap(); //TODO implement genre count persistence
    private LocalDateTime refreshTime; //the time a new timeframe is created
    private Scale interval; //type of time frame
    private Timeframe currentFrame; //current timeframe songs are being added to
    private Map<String, Integer> artistCountMap; //TODO implement artist count persistence
    private double averageBPM; //TODO implement average BPM persistence
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

    public Map<String, Integer> getGenreCountMap() {
        return genreCountMap;
    }

    public Map<String, Integer> getArtistCountMap() {
        return artistCountMap;
    }

    public void addSong(Song song) {
        currentFrame.add(song);

        if (LocalDateTime.now().isAfter(refreshTime)) {
            System.out.print(currentFrame.averageBPM());
            System.out.print(currentFrame.topGenre());
            System.out.print(currentFrame.mostPopularArtist());

            listOfTimeframes.add(currentFrame);
            currentFrame = new Timeframe(interval.name(), this);
            refreshTime = updateRefreshTime();
        }
    }

    public String mostPopularGenre() {
        HashMap<String, Integer> map = Main.getGenreCountMap();
        String mostPopularGenre = null;
        int max = 0;

        for (Timeframe t : listOfTimeframes) {
            map.compute(
                    t.topGenre(),
                    (k, count) -> {
                        return count + 1;
                    }
            );
        }
        for (String s : map.keySet())
            if (Math.max(max, map.get(s)) == map.get(s)) {
                max = map.get(s);
                mostPopularGenre = s;
            }

        return mostPopularGenre;
    }

    public String mostPopularArtist() {
        HashMap<String, Integer> map = new HashMap<>();
        String mostPopularArtist = null;
        int max = 0;

        for (Timeframe t : listOfTimeframes) {
            map.compute(
                    t.mostPopularArtist(),
                    (k, count) -> {
                        return (count == null ? 0 : count) + 1;
                    }
            );
        }
        for (String s : map.keySet())
            if (Math.max(max, map.get(s)) == map.get(s)) {
                max = map.get(s);
                mostPopularArtist = s;
            }

        return mostPopularArtist;

    }

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

    private enum Scale {TENSECOND, MINUTE, HOUR, DAY, WEEK, MONTH;} //type of timeline

}
