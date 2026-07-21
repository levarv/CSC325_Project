package modelview;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.SceneManager;

public class Application extends javafx.application.Application {

    @Override
    public void start(Stage stage) throws Exception {

        FXMLLoader loader = new FXMLLoader( getClass().getResource("/Playlist.fxml") );
        SceneManager.addScene(new Scene(loader.load()),1);

        loader = new FXMLLoader( getClass().getResource("/Library.fxml") );
        SceneManager.addScene(new Scene(loader.load()),2);

      //  loader = new FXMLLoader( getClass().getResource("/MusicPlayer.fxml") );
      //  SceneManager.addScene(new Scene(loader.load()),3);

        stage.setTitle("Playlist");
        stage.setScene(SceneManager.getScene(1));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}