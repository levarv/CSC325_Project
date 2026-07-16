package model;

import java.util.ArrayList;

public class Playlist implements Scorable {
    private String name;
    private ArrayList<Song> songs; //list of songs in playlist
    private boolean isAnAlbum; //determines if this collection of songs is an album

    /**
     * Constructor
     * @param name      Name of playlist - RD
     * @param songs     list of songs in playlist
     * @param isAnAlbum is this an album?
     */
    public Playlist(String name, ArrayList<Song> songs, boolean isAnAlbum) {
        this.name = name;
        this.songs = songs;
        this.isAnAlbum = isAnAlbum;
    }

    /**
     * getName returns playlist name - RD
     * @return name
     */
    public String getName() {
        return name;
    }

    // Sets name of playlist, used to rename playlist - RD
    public void setName(String name) {
        this.name = name;
    }

    /**
     * getSongs returns playlist songs within playlist - RD
     * @return songs
     */
    public ArrayList<Song> getSongs() {
        return songs;
    }

    /**
     * getSongsCount returns playlist size - RD
     * @return songs.size()
     */
    public int getSongsCount() {
        return songs.size();
    }



    /**
     * genreScore
     *
     * @param t Timeline to derive score
     * @return The average genre scores of each song
     */
    @Override
    public double genreScore(Timeline t) {
        double sum = 0;
        for (Song s : songs)
            sum += s.genreScore(t);

        return sum / songs.size();
    }

    /**
     * genreScore
     *
     * @param t Timeline to derive score
     * @return The average bpm score scores of each song
     */
    @Override
    public double BPMScore(Timeline t) {
        double sum = 0;
        for (Song s : songs)
            sum += s.BPMScore(t);

        return sum / songs.size();
    }

    /**
     * genreScore
     *
     * @param t Timeline to derive score
     * @return The average artist scores of each song
     */
    @Override
    public double artistScore(Timeline t) {
        double sum = 0;
        for (Song s : songs)
            sum += s.artistScore(t);

        return sum / songs.size();
    }
}
