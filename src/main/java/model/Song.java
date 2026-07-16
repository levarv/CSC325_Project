package model;

import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;
import javafx.scene.media.Media;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

public class Song implements Scorable {

    private String genre; //genre of song
    private String name;  //name of song
    private String artist;  //artist of song
    private int bpm;  //bpm of song
    private Media media; //java fx media object to be accepted by player

    /**
     * constructor for testing
     *
     * @param name   name of song
     * @param bpm    bpm attribute
     * @param genre  genre category
     * @param artist artist name
     */
    public Song(String name, int bpm, String genre, String artist) {
        this.name = name;
        this.bpm = bpm;
        this.genre = genre;
        this.artist = artist;
    }

    /**
     * constructor from database
     *
     * @param name   name of song
     * @param bpm    bpm attribute
     * @param genre  genre category
     * @param artist artist name
     * @param file   mp3 test file
     */
    public Song(String name, int bpm, String genre, String artist, File file) {
        this.name = name;
        this.bpm = bpm;
        this.genre = genre;
        this.artist = artist;
        this.media = new Media(file.getPath());
    }

    /**
     * constructor from file picker
     *
     * @param file mp3 test file
     */
    public Song(File file) {
        try {
            Mp3File mp3file = new Mp3File(file);

            if (mp3file.hasId3v2Tag()) {
                this.name = mp3file.getId3v2Tag().getTitle();
                this.bpm = mp3file.getId3v2Tag().getBPM();
                this.genre = mp3file.getId3v2Tag().getGenreDescription();
                this.artist = mp3file.getId3v2Tag().getArtist();
                // this.media = new Media(file.getPath());
            } else if (mp3file.hasId3v1Tag()) {
                this.name = mp3file.getId3v1Tag().getTitle();
                this.bpm = -1;
                this.genre = mp3file.getId3v1Tag().getGenreDescription();
                this.artist = mp3file.getId3v1Tag().getArtist();
                //this.media = new Media(file.getPath());
            }

        } catch (InvalidDataException e) {
            throw new RuntimeException(e);
        } catch (UnsupportedTagException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /*
        Test Method
     */
    public static Song randomSong() {
        Random rng = new Random();
        ArrayList<String> tempList = new ArrayList<>(Main.getGenreCountMap().keySet());
        int rand = rng.nextInt(0, tempList.size());
        return new Song("" + rand + rand + rand + rand, rand, tempList.get(rand), tempList.get(rand));
    }

    /**
     * genreScore
     *
     * @param t Timeline to derive score
     * @return n/N where:
     * n = number of times this song's genre appeared in Timeline t
     * N = number of times any genre appeared in Timeline t
     */
    @Override
    public double genreScore(Timeline t) {
        Map<String, Integer> map = t.getGenreCountMap();

        Integer thisCount = map.get(this.genre);
        Integer topCount = map.get(t.mostPopularGenre());

        if (thisCount != null && topCount != null)
            return (double) thisCount / topCount;
        else
            return -1.0;
    }

    /**
     * BPMScore
     *
     * @param t Timeline to derive score
     * @return this song's error relative to the average bpm
     * (relative error is similar to percent error)
     */
    @Override
    public double BPMScore(Timeline t) {
        Double avgBpm = t.averageBPM();

        if (avgBpm != null && this.bpm != 0)
            return Math.abs(t.averageBPM() - this.bpm) / t.averageBPM();
        else
            return -1.0;
    }

    /**
     *
     * @param t Timeline t
     *
     * @return n/N where:
     * n = number of times this song's artist appeared in Timeline t
     * N = number of times any artist appeared in Timeline t
     */
    @Override
    public double artistScore(Timeline t) {
        Map<String, Integer> map = t.getArtistCountMap();

        Integer thisCount = map.get(this.artist);
        Integer topCount = map.get(t.mostPopularArtist());

        if (thisCount != null && topCount != null)
            return (double) thisCount / topCount;
        else
            return -1.0;
    }

    /**
     * setMedia
     *
     * @param media to be set
     */
    public void setMedia(Media media) {
        this.media = media;
    }

    /**
     * getGenre
     *
     * @return genre
     */
    public String getGenre() {
        return genre;
    }

    /**
     * getName
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * getArtist
     *
     * @return artist
     */
    public String getArtist() {
        return artist;
    }

    /**
     * getBpm
     *
     * @return bpm
     */
    public int getBpm() {
        return bpm;
    }

    /**
     * toString
     *
     * @return String representation of this Song
     */
    @Override
    public String toString() {
        return "model.Song{" +
                "genre=" + genre +
                ", name='" + name + '\'' +
                ", artist='" + artist + '\'' +
                ", bpm=" + bpm +
                '}';
    }
}
