package modelview;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import model.SQLQuery;
import model.SceneManager;
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
        SQLQuery.query("SELECT * FROM SONG");
    }

    public ObservableList<Song> getLibrarySongs() {
        return librarySongs;
    }

    @FXML 
    private void goHome() {
        SceneManager.setScene(3);
    }

    @FXML
    private void goPlaylist(){
        SceneManager.setScene(2);
    }
}
