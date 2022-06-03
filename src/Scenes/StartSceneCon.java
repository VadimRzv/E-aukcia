package Scenes;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class StartSceneCon {

    @FXML
    private Button Start;

    public void startClick(ActionEvent event) throws IOException{
        Stage stage = (Stage)Start.getScene().getWindow();
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("./LoginScene.fxml")));
        stage.setTitle("Login");
        stage.setScene(scene);
    }

    @FXML
    void initialize() {
    }
}