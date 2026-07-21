package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import model.Playlist;
import model.SceneManager;
import model.Song;
import javafx.scene.image.Image;

import java.util.EventListener;
import java.util.Objects;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.io.File;
import java.util.ArrayList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


import model.Main;

import java.io.IOException;

public class PlaylistController {
    @FXML
    public Button Library;

    @FXML
    public Button Home;

    @FXML
    private FlowPane playlistFlowPane;

    @FXML
    private TextField searchField;

    // Holds existing playlist objects for viewing - RD
    private ObservableList<Playlist> playlists = FXCollections.observableArrayList();


    // Used to refresh ObservableList playlists when playlist is created or modified - RD
    private void refreshPlaylists() {

        // Keep the Add tile, but remove any generated playlist tiles - RD
        if (playlistFlowPane.getChildren().size() > 1) {
            playlistFlowPane.getChildren()
                    .remove(1, playlistFlowPane.getChildren().size());
        }

        // Add refreshed playlist tiles - RD
        for (Playlist playlist : playlists) {
            playlistFlowPane.getChildren().add(
                    createPlaylistTile(playlist)
            );
        }
    }

    // Creates Playlist Tiles upon being called by refreshPlaylists - RD
    private VBox createPlaylistTile(Playlist playlist) {

        // Tile added into FlowPane - RD
        VBox tile = new VBox();
        tile.setAlignment(Pos.CENTER);
        tile.setPrefSize(180, 200);

        Label name = new Label(playlist.getName());

        Button button = new Button();
        button.setPrefSize(160, 160);

        // Set tile to selected playlist - RD
        button.setOnAction(event -> openPlaylistDetails(playlist));

        // Check if image path is null, if not, populate image as playlist button - RD
        if (playlist.getCoverImagePath() != null) {

            Image image;

            if (playlist.getCoverImagePath().startsWith("file:")) {
                image = new Image(playlist.getCoverImagePath());
            } else {
                image = new Image(
                        Objects.requireNonNull(
                                getClass().getResourceAsStream(
                                        playlist.getCoverImagePath()
                                )
                        )
                );
            }

            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(115);
            imageView.setFitHeight(115);
            imageView.setPreserveRatio(true);

            button.setGraphic(imageView);
        }

        tile.getChildren().addAll(name, button);

        return tile;
    }

    // Method to add a new playlists, then refreshPlaylists to create a tile - RD
    public void addPlaylist(Playlist playlist) {
        playlists.add(playlist);
        refreshPlaylists();
    }

    public void deletePlaylist(Playlist playlist) {
        playlists.remove(playlist);
        refreshPlaylists();
    }

    // Opens the FXML CreatePlaylist view, used for adding a playlist with the Add button - RD
    @FXML
    private void openCreatePlaylist() throws IOException {

        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/CreatePlaylist.fxml")
        );

        Parent root = loader.load();

        CreatePlaylistController createController =
                loader.getController();

        createController.setPlaylistController(this);

        Stage stage = new Stage();
        stage.setTitle("Create Playlist");
        stage.setScene(new Scene(root));
        stage.show();
    }

    // Opens the FXML PlaylistDetails view, used for adding a playlist with the Add button - RD
    private void openPlaylistDetails(Playlist playlist) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/PlaylistDetails.fxml")
            );


            Parent root = loader.load();

            PlaylistDetailsController controller = loader.getController();

            Stage stage = new Stage();
            stage.setTitle(playlist.getName());
            stage.setScene(new Scene(root));
            stage.setResizable(false);

            controller.setPlaylist(playlist);
            controller.setPlaylistController(this);
            controller.setDetailsStage(stage);

            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    // Create sample playlist - RD
    @FXML
    public void initialize() {

        ArrayList<Song> summerSongs = new ArrayList<>();

        refreshPlaylists();
    }

    public void HomePressed(ActionEvent actionEvent) {
        SceneManager.setScene(3);
    }

    public void LibraryPressed(ActionEvent actionEvent) {
        SceneManager.setScene(2);
    }


}