package inference;

import model.Song;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

public class Timeframe {

    private enum Scale {TENSECOND, MINUTE, HOUR, DAY, WEEK, MONTH; }
    private final Scale scale;
    private final LocalDateTime created = LocalDateTime.now();
    private final ArrayList<Song> musicList;
    private final Timeline myTimeline;

    public Timeframe(String s, Timeline myTimeline) {
        scale = Scale.valueOf(s);
        this.myTimeline = myTimeline;
        musicList = new ArrayList<>();
    }

    public void add(Song song) {
        musicList.add(song);
        myTimeline.getGenreCountMap().compute(
                song.getGenre(),
                (k, count)->{return (count == null ? 0 : count) + 1;}
        );

    }

    public Song.Genre topGenre() {
        HashMap<Song.Genre,Integer> map = Song.getGenreCountMap();
        Song.Genre mostPopularGenre = null;
        int max = 0;

        for (Song s : musicList)
            map.compute(
                    s.getGenre(),
                    (k, count)->{return (count == null ? 0 : count) + 1;}
            );

        for (Song.Genre s : map.keySet())
            if (Math.max(max,map.get(s)) == map.get(s)) {
                max = map.get(s);
                mostPopularGenre = s;
            }

        return mostPopularGenre;
    }

    public int averageLoudness() {
        int sum = 0;
        int n = 0;

        for (Song s : musicList) {
            sum += s.getLoudness();
            ++n;
        }
        return (sum / n);
    }

    public int averageBPM() {
        int sum = 0;
        int n = 0;

        for (Song s : musicList) {
            sum += s.getBpm();
            ++n;
        }
        return (sum / n);
    }

    public String mostPopularArtist() {
        HashMap<String, Integer> map = new HashMap<>();
        String mostPopularArtist = null;
        int max = 0;

        for (Song s : musicList)
            if (map.containsKey(s.getArtist())) {
                map.compute(
                        s.getArtist(),
                        (k, count)->{return (count == null ? 0 : count) + 1;}
                );
            }
            else
                map.put(s.getArtist(),0);

        for (String s : map.keySet())
            if (Math.max(max,map.get(s)) == map.get(s)) {
                max = map.get(s);
                mostPopularArtist = s;
            }

        return mostPopularArtist;
    }

    @Override
    public String toString() {
        return "testbench.TimeFrame{" +
                "scale=" + scale +
                ", created=" + created +
                ", musicList=" + musicList +
                '}';
    }
}
