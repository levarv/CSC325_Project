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
        this.songs = songs != null ? songs : new ArrayList<>(); // Prevents empty playlist from crashing - RD
        this.isAnAlbum = isAnAlbum;
    }

    /**
     * getName returns playlist name - RD
     * @return playlist name
     */
    public String getName() {
        return name;
    }

    /**
     * setName sets name of playlist
     * @param name playlist name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * getSongs returns playlist songs within playlist - RD
     * @return list of songs
     */
    public ArrayList<Song> getSongs() {
        return songs;
    }

    /**
     * getSongCount returns playlist size - RD
     * @return playlist song count
     */
    public int getSongCount() {
        return songs.size();
    }

    /**
     * addSong adds song to playlist - RD
     * @param song song added to playlist
     */
    public void addSong(Song song) {

        songs.add(song);
    }

    /**
     * removeSong removes song from playlist - RD
     * @param song song removed from playlist
     */
    public void removeSong(Song song) {

        songs.remove(song);
    }

    /**
     * Check if playlist is an album
     * @return isAnAlbum t/f value
     */
    public boolean isAnAlbum() {
        return isAnAlbum;
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
