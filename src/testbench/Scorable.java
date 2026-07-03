package testbench;

public interface Scorable {

    public double genreScore(Timeline t);

    public double BPMScore(Timeline t);

    public double loudnessScore(Timeline t);

    public double artistScore(Timeline t);

    public double aggregate(Timeline t);
}
