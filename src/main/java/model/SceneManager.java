package model;/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Ronald
 */
public class SceneManager {

    public static Stage stage;

    public static Scene[] sceneArr = new Scene[3];

    public static void setStage(Stage stag){stage = stag;}

    public static void addScene(Scene scene, int index){sceneArr[index] = scene;}

    public static Scene getScene(int i) {return sceneArr[i];}

    public static void setScene(int index) {
        stage.setScene(sceneArr[index]);
        stage.show();
    }
    
}
