package modelview;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import model.SQLQuery;
import model.SceneManager;
import model.Song;
import javafx.stage.FileChooser;
import java.io.File;

public class LibraryController {

    @FXML
    private ListView<Song> libraryList;

    private final ObservableList<Song> librarySongs =
            FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        libraryList.setItems(librarySongs);
        loadSongs();
    }

    private void loadSongs() {
        librarySongs.setAll(SQLQuery.getSongs());
    }

    public ObservableList<Song> getLibrarySongs() {
        return librarySongs;
    }

    @FXML
    private void goHome() {
        SceneManager.setScene(3);
    }

    @FXML
    private void goPlaylist() {
        SceneManager.setScene(1);
    }


    //import song method
    @FXML
    private void importSong() {

        FileChooser fileChooser = new FileChooser();

        fileChooser.setTitle("Choose MP3 Song");

        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter(
                        "MP3 Audio Files",
                        "*.mp3"
                )
        );

        File selectedFile = fileChooser.showOpenDialog(
                libraryList.getScene().getWindow()
        );

        if (selectedFile != null) {
            SQLQuery.addSong(selectedFile);
            loadSongs();
        }
    }

}