package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import static java.lang.Thread.sleep;

public class Main {

    private static final HashMap<String, Integer> genreCountMap = new HashMap<>();
    private static final HashMap<String, Integer> artistCountMap = new HashMap<>();
    private static double averageBPM = 0.0;

    /**
     * writes the map entries to a text file
     * @param genreMap
     * @param artistMap
     * @param averageBpm
     */
    public static void writeToFile(Map<String,Integer> genreMap, Map<String,Integer> artistMap, double averageBpm) {
        File file = new File("mapsAndData.txt");
        try (FileWriter writer = new FileWriter(file,false)) {
            for (Map.Entry<String, Integer> entry : genreMap.entrySet()) {
                writer.write(entry.getKey() + "=" + entry.getValue() + "\n");
            }
            writer.write("<EOD>" + "\n");

            for (Map.Entry<String, Integer> entry : artistMap.entrySet()) {
                writer.write(entry.getKey() + "=" + entry.getValue() + "\n");
            }
            writer.write("<EOD>" + "\n");

            writer.write(averageBpm + "\n");
            writer.write("<EOD>" + "\n");
            writer.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * readFromFile
     * instantiates the maps from a text file.
     */
    public static void readFromFile(){
        File file = new File("mapsAndData.txt");
        String[] data = null;
        try {
            Scanner scan = new Scanner(file);

            while (!scan.hasNext("<EOD>")) {
                data = scan.nextLine().split("=");
                genreCountMap.put(data[0], Integer.parseInt(data[1]));
                System.out.println(data[1]);
            }
            scan.nextLine();

            while (!scan.hasNext("<EOD>")) {
                data = scan.nextLine().split("=");
                artistCountMap.put(data[0], Integer.parseInt(data[1]));
            }
            scan.nextLine();

            while (!scan.hasNext("<EOD>"))
                averageBPM = Double.parseDouble(scan.nextLine());


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * getGenreCountMap
     * @return genreCountMap
     */
    public static HashMap<String, Integer> getGenreCountMap() {
        return genreCountMap;
    }

    /**
     * getArtistCountMap
     * @return artistCountMap
     */
    public static HashMap<String, Integer> getArtistCountMap() {
        return artistCountMap;
    }

    /**
     * getAverageBPM
     * @return averageBPM
     */
    public static Double getAverageBPM() {
        return averageBPM;
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
        readFromFile();
        u.start();

        try {
            sleep(20000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        u.interrupt();
    }
}
