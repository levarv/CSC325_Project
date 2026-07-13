package modelview;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Song;

import java.io.File;

public class LibraryController {
    
    private ObservableList<Song> librarySongs = FXCollections.observableArrayList();

    public void LibraryController(){
        loadSongs();

    }

    private void loadSongs(){
        librarySongs.add(new Song(new File("src/main/resource/AquaTofana.mp3")));
        librarySongs.add(new Song(new File("src/main/resource/ClosetoHome.mp3")));
        librarySongs.add(new Song(new File("src/main/resource/Home.mp3")));
        librarySongs.add(new Song(new File("src/main/resource/RumbleFish.mp3")));
        librarySongs.add(new Song(new File("src/main/resource/Spikes.mp3")));
    }

    public ObservableList<Song> getLibrarySongs() {
        return librarySongs;
    }
}
