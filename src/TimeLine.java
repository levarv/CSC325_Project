import java.time.LocalDateTime;
import java.util.LinkedList;

public class TimeLine {

    private enum Scale { MINUTE,HOUR,DAY,WEEK,MONTH; }
    private LocalDateTime refreshTime = LocalDateTime.now();
    private Scale interval;
    private final LinkedList<TimeFrame> listOfTimeFrames;
    private TimeFrame currentFrame;

    public TimeLine (String scale) {
        try {
            interval = Scale.valueOf(scale);
        } catch (IllegalArgumentException e) {
            System.out.println("Acceptable arg: MINUTE | HOUR | DAY | WEEK | MONTH");
        }
        listOfTimeFrames = new LinkedList<>();
        refreshTime = updateRefreshTime();
        currentFrame = new TimeFrame(interval.name());
    }

    public LocalDateTime updateRefreshTime(){
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
           listOfTimeFrames.add(currentFrame);
           currentFrame = new TimeFrame(interval.name());
           refreshTime = updateRefreshTime();
           System.out.println(refreshTime);
       }
    }

    public void getAnalytics(){
        for (TimeFrame t : listOfTimeFrames)
            System.out.println(t + "    " + t.topGenre());
    }
}
