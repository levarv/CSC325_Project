import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;

public class TimeLine {

    private enum Scale {
        MINUTE(LocalDateTime.now(), LocalDateTime.now().plusMinutes(1L), "MINUTE"),
        HOUR(LocalDateTime.now(), LocalDateTime.now().plusHours(1L), "HOUR"),
        DAY(LocalDateTime.now(), LocalDateTime.now().plusDays(1L), "DAY"),
        WEEK(LocalDateTime.now(), LocalDateTime.now().plusWeeks(1L), "WEEK"),
        MONTH(LocalDateTime.now(), LocalDateTime.now().plusMonths(1L), "MONTH");
        private String timeFrameType;
        private LocalDateTime startOfTimeFrame;
        private LocalDateTime endOfTimeFrame;

        private Scale(LocalDateTime startOfTimeFrame, LocalDateTime endOfTimeFrame, String timeFrameType){
            this.startOfTimeFrame = startOfTimeFrame;
            this.endOfTimeFrame = endOfTimeFrame;
            this.timeFrameType = timeFrameType;
        }
        public String getTimeFrameType() { return timeFrameType; }
        public LocalDateTime getStartOfTimeFrame() { return startOfTimeFrame; }
        public LocalDateTime getEndOfTimeFrame() { return endOfTimeFrame;}
    }

    private Scale timeFrameInfo; //
    private LinkedList<TimeFrame> listOfTimeFrames;

    public TimeLine (String scale) {
        try {
            timeFrameInfo = Scale.valueOf(scale);
        } catch (IllegalArgumentException e) {
            System.out.println("Acceptable arg: MINUTE | HOUR | DAY | WEEK | MONTH");
        }
        listOfTimeFrames = new LinkedList<>();
    }

    public void addSong(Song song) {
       if (listOfTimeFrames.isEmpty()) {
           TimeFrame entry = new TimeFrame(timeFrameInfo.getTimeFrameType());
           entry.add(song);
           listOfTimeFrames.add(entry);
       }
       else if (LocalDateTime.now().isAfter(timeFrameInfo.getEndOfTimeFrame())) {
           TimeFrame entry = new TimeFrame(timeFrameInfo.getTimeFrameType());
           entry.add(song);
           listOfTimeFrames.add(entry);
       }
       else {
           listOfTimeFrames.peekFirst().add(song);
       }
    }

    public String getAnalytics(){
        LinkedList<String> list = new LinkedList<>();

        for (TimeFrame t : listOfTimeFrames)
            list.add(t.topGenre().toString());

        return list.toString();
    }
}
