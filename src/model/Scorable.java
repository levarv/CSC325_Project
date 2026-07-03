package model;

import inference.Timeline;

public interface Scorable {

    public double genreScore(Timeline t);

    public double BPMScore(Timeline t);

    public double loudnessScore(Timeline t);

    public double artistScore(Timeline t);

}
