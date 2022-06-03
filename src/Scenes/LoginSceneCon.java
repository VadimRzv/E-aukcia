package Scenes;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class LoginSceneCon {

    @FXML
    private Button NewUser;

    @FXML
    private Button aut;

    public void newUserClick(ActionEvent event)throws IOException{
        Stage stage = (Stage)NewUser.getScene().getWindow();
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("./CreateUserScene.fxml")));
        stage.setScene(scene);
    }

    public void AuthorizationClick(ActionEvent event)throws IOException,  InterruptedException{
        Stage stage = (Stage)aut.getScene().getWindow();
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("./AuthorizationUserScene.fxml")));
        stage.setScene(scene);
    }

    @FXML
    void initialize() {
        assert NewUser != null : "fx:id=\"NewUser\" was not injected: check your FXML file 'LoginScene.fxml'.";
        assert aut != null : "fx:id=\"authorization\" was not injected: check your FXML file 'LoginScene.fxml'.";
    }
}