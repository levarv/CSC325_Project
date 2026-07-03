package testbench;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedList;

public class Timeline {

    private enum Scale { MINUTE,HOUR,DAY,WEEK,MONTH; }
    private LocalDateTime refreshTime;
    private Scale interval;
    private final LinkedList<Timeframe> listOfTimeframes;
    private Timeframe currentFrame;

    public Timeline(String scale) {
        try {
            interval = Scale.valueOf(scale);
        } catch (IllegalArgumentException e) {
            System.out.println("Acceptable arg: MINUTE | HOUR | DAY | WEEK | MONTH");
        }
        listOfTimeframes = new LinkedList<>();
        refreshTime = updateRefreshTime();
        currentFrame = new Timeframe(interval.name());
    }

    public LocalDateTime updateRefreshTime(){
        if (refreshTime == null)
            return LocalDateTime.now();

        return switch (interval) {
            case MINUTE -> refreshTime.plusMinutes(1L);
            case HOUR -> refreshTime.plusHours(1L);
            case DAY -> refreshTime.plusDays(1L);
            case WEEK -> refreshTime.plusWeeks(1L);
            case MONTH -> refreshTime.plusMonths(1L);
        };
    }

    public void addSong(Song song) {
        currentFrame.add(song);

       if (LocalDateTime.now().isAfter(refreshTime)) {
           listOfTimeframes.add(currentFrame);
           currentFrame = new Timeframe(interval.name());
           refreshTime = updateRefreshTime();
       }
    }

    public String mostPopularGenre(){
        HashMap<String,Integer> map = new HashMap<>();
        String mostPopularGenre = null;
        int max = 0;

        for (Timeframe t : listOfTimeframes) {
            map.compute(
                    t.topGenre().toString(),
                    (k, count) -> {
                        return (count == null ? 0 : count) + 1;
                    }
            );
        }
        for (String s : map.keySet())
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

    public double averageLoudness(){
        double sum = 0;
        double count = 0;
        for (Timeframe t : listOfTimeframes) {
            sum += t.averageLoudness();
            ++count;
        }

        return sum / count;
    }


    public double averageBPM() {
        double sum = 0;
        double count = 0;
        for (Timeframe t : listOfTimeframes) {
            sum += t.averageBPM();
            ++count;
        }

        return sum / count;
    }

}
