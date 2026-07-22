package modelview;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import model.Main;
import model.SQLQuery;
import model.SceneManager;
import model.Song;
import javafx.stage.FileChooser;
import java.io.File;

public class LibraryController {

    @FXML
    private ListView<Song> libraryList;

    private ObservableList<Song> librarySongs;

    @FXML
    public void initialize() {
        loadSongs();
        libraryList.setItems(librarySongs);
    }

    private void loadSongs(){
        librarySongs = FXCollections.observableArrayList(SQLQuery.query("SELECT * FROM SONG"));
    }

    public ObservableList<Song> getLibrarySongs() {
        return librarySongs;
    }

    @FXML
    private void goHome() { SceneManager.setScene(3); refreshList(); }

    @FXML
    private void goPlaylist() {  SceneManager.setScene(1); refreshList(); }

    public void refreshList() {
        SQLQuery.query("DELETE FROM SONG");
        Main.loadSongsFromUserRepo();
        loadSongs();
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

        Song tbAdded = new Song(selectedFile);

        String update = String.format(
                "INSERT INTO Song (Artist,Name,BPM,ReleaseDate,Genre,ImageURL,SongURL)" +
                        " VALUES ('%s', '%s', %s, '%s', '%s','%s','%s')",
                tbAdded.getArtist(),
                tbAdded.getName(),
                tbAdded.getBpm(),
                "NULL",
                tbAdded.getGenre(),
                "NULL",
                selectedFile.getPath()
        );


        SQLQuery.update(update);
    }

}