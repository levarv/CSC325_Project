package modelview;

import control.Timeline;

import java.util.ArrayList;

public class Playlist implements Scorable{
    private ArrayList<Song> songs; //list of songs in playlist
    private boolean isAnAlbum; //determines if this collection of songs is an album

    /**
     * constructor
     * @param songs list of songs in playlist
     * @param isAnAlbum is this an album?
     */
    public Playlist(ArrayList<Song> songs, boolean isAnAlbum) {
        this.songs = songs;
        this.isAnAlbum = isAnAlbum;
    }

    /**
     * genreScore
     * @param t Timeline to derive score
     * @return The average genre scores of each song
     */
    @Override
    public double genreScore(Timeline t) {
        double sum = 0;
        for (Song s: songs)
            sum += s.genreScore(t);

        return sum / songs.size();
    }

    /**
     * genreScore
     * @param t Timeline to derive score
     * @return The average bpm score scores of each song
     */
    @Override
    public double BPMScore(Timeline t) {
        double sum = 0;
        for (Song s: songs)
            sum += s.BPMScore(t);

        return sum / songs.size();
    }

    /**
     * genreScore
     * @param t Timeline to derive score
     * @return The average artist scores of each song
     */
    @Override
    public double artistScore(Timeline t) {
        double sum = 0;
        for (Song s: songs)
            sum += s.artistScore(t);

        return sum / songs.size();
    }
}
