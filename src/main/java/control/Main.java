package control;

import com.mpatric.mp3agic.ID3v2;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

import static java.lang.Thread.sleep;

public class Main {

    private static final HashMap<String, Integer> genreCountMap = instantiate();

    public static HashMap<String, Integer> getGenreCountMap() {
        return genreCountMap;
    }

    private static HashMap<String, Integer> instantiate() {
        HashMap<String, Integer> map = new HashMap<>();
        File file = new File("src/main/resources/genre.csv");
        int index = 0;
        try {
            Scanner scan = new Scanner(file);
            while (scan.hasNextLine()) {
                String[] s = scan.nextLine().trim().split(",");
                map.put(s[1],Integer.parseInt(s[0]));
                System.out.println(s[0] + s[1]);
            }
            System.out.println();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return map;
    }

    public static void main(String[] args) {

        /*
        try {
            Mp3File mp3file = new Mp3File("Spikes.mp3");
            if (mp3file.hasId3v2Tag()) {
                ID3v2 id3v2Tag = mp3file.getId3v2Tag();
                System.out.println("Track: " + id3v2Tag.getTrack());
                System.out.println("Artist: " + id3v2Tag.getArtist());
                System.out.println("Title: " + id3v2Tag.getTitle());
                System.out.println("Album: " + id3v2Tag.getAlbum());
                System.out.println("Year: " + id3v2Tag.getYear());
                System.out.println("Genre: " + id3v2Tag.getGenre() + " (" + id3v2Tag.getGenreDescription() + ")");
                System.out.println("Comment: " + id3v2Tag.getComment());
                System.out.println("Lyrics: " + id3v2Tag.getLyrics());
                System.out.println("Composer: " + id3v2Tag.getComposer());
                System.out.println("Publisher: " + id3v2Tag.getPublisher());
                System.out.println("Original artist: " + id3v2Tag.getOriginalArtist());
                System.out.println("Album artist: " + id3v2Tag.getAlbumArtist());
                System.out.println("Copyright: " + id3v2Tag.getCopyright());
                System.out.println("URL: " + id3v2Tag.getUrl());
                System.out.println("Encoder: " + id3v2Tag.getEncoder());
                byte[] albumImageData = id3v2Tag.getAlbumImage();
                if (albumImageData != null) {
                    System.out.println("Have album image data, length: " + albumImageData.length + " bytes");
                    System.out.println("Album image mime type: " + id3v2Tag.getAlbumImageMimeType());
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (UnsupportedTagException e) {
            throw new RuntimeException(e);
        } catch (InvalidDataException e) {
            throw new RuntimeException(e);
        }
        */
        ThreadGroup g = new ThreadGroup("g");
        TestUser u = new TestUser("bob", g);

        u.start();

        try {
            sleep(200000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        u.interrupt();
    }
}
