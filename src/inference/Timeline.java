package inference;

import model.Song;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Timeline {

    private enum Scale { TENSECOND,MINUTE,HOUR,DAY,WEEK,MONTH; }
    private LocalDateTime refreshTime;
    private Scale interval;
    private final LinkedList<Timeframe> listOfTimeframes;
    private Timeframe currentFrame;
    private final Map<Song.Genre,Integer> genreCountMap = Song.getGenreCountMap();
    private Map<String,Integer> artistCountMap;

    public Timeline(String scale) {
        try {
            interval = Scale.valueOf(scale);
        } catch (IllegalArgumentException e) {
            System.out.println("Acceptable arg: TENSECOND | MINUTE | HOUR | DAY | WEEK | MONTH");
        }
        listOfTimeframes = new LinkedList<>();
        refreshTime = updateRefreshTime();
        currentFrame = new Timeframe(interval.name(),this);
    }

    public LocalDateTime updateRefreshTime(){
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

    public Map<Song.Genre, Integer> getGenreCountMap() {
        return genreCountMap;
    }

    public Map<String, Integer> getArtistCountMap() {
        return artistCountMap;
    }

    public void addSong(Song song) {
        currentFrame.add(song);

       if (LocalDateTime.now().isAfter(refreshTime)) {
           listOfTimeframes.add(currentFrame);
           currentFrame = new Timeframe(interval.name(), this);
           refreshTime = updateRefreshTime();
       }
    }

    public Song.Genre mostPopularGenre(){
        HashMap<Song.Genre,Integer> map = new HashMap<>();
        Song.Genre mostPopularGenre = null;
        int max = 0;

        for (Timeframe t : listOfTimeframes) {
            map.compute(
                    t.topGenre(),
                    (k, count) -> {
                        return (count == null ? 0 : count) + 1;
                    }
            );
        }
        for (Song.Genre s : map.keySet())
            if (Math.max(max,map.get(s)) == map.get(s)) {
                max = map.get(s);
                mostPopularGenre = s;
            }

        return mostPopularGenre;
    }

    public String mostPopularArtist(){
        HashMap<String,Integer> map = new HashMap<>();
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
            if (Math.max(max,map.get(s)) == map.get(s)) {
                max = map.get(s);
                mostPopularArtist = s;
            }

        return mostPopularArtist;

    }

    public Double averageLoudness(){
        double sum = 0;
        double count = 0;
        for (Timeframe t : listOfTimeframes) {
            sum += t.averageLoudness();
            ++count;
        }
        if (count == 0)
            return null;
        else
            return sum / count;
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

}
