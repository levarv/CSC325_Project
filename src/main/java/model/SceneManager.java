package model;

import javafx.scene.Scene;
import javafx.stage.Stage;

public class SceneManager {

    public static Stage stage;

    private static final Scene[] scenes = new Scene[4];

    public static void setStage(Stage stag) {
        stage = stag;
    }

    public static void addScene(Scene scene, int index) {
        scenes[index] = scene;
    }

    public static Scene getScene(int index) {
        return scenes[index];
    }

    public static void setScene(int index) {
        stage.setScene(scenes[index]);
        stage.show();
    }
}