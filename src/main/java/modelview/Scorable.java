package modelview;

import control.Timeline;

public interface Scorable {

    public double genreScore(Timeline t);

    public double BPMScore(Timeline t);

    public double artistScore(Timeline t);

}
