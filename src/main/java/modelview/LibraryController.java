package modelview;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import model.Song;

import java.io.File;

public class LibraryController {

    @FXML
    private ListView<Song> libraryList;
    
    private ObservableList<Song> librarySongs = FXCollections.observableArrayList();

    @FXML
    public void initialize(){
        loadSongs();
        libraryList.setItems(librarySongs);
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
