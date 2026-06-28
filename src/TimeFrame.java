import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

public class TimeFrame {

    private enum Scale { MINUTE, HOUR, DAY, WEEK, MONTH; }
    private final Scale scale;
    private final LocalDateTime created = LocalDateTime.now();
    private final ArrayList<Song> musicList;

    public TimeFrame(String s) {
        scale = Scale.valueOf(s);
        musicList = new ArrayList<>();
    }

    public void add(Song song) {
        musicList.add(song);
    }

    public Song.Genre topGenre() {
        HashMap<Song.Genre,Integer> map = Song.getUtilityMap();
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

    @Override
    public String toString() {
        return "TimeFrame{" +
                "scale=" + scale +
                ", created=" + created +
                ", musicList=" + musicList +
                '}';
    }
}
