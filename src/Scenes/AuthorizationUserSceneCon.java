package Scenes;

import Data.UserTab;
import Global.Globals;
import Types.User;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class AuthorizationUserSceneCon {
    private  UserTab ut;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button NewLogOn;

    @FXML
    private TextField OldLogin;

    @FXML
    private PasswordField OldPassword;

    @FXML
    private Label LabelError;
    
     @FXML
    private Button CancelButton;
    
    public void newLogOn(ActionEvent event) throws InterruptedException, IOException{
        User user = ut.getUser(OldLogin.getText());
        
        if (user == null){
            LabelError.setText("There is no such user");
            newLogOn(event);
        } 
        else{                 
            if (!(user.getPassword().equals(OldPassword.getText()))) {
                LabelError.setText("Incorrect password");
                newLogOn(event); 
            }   
        }     

        Globals.player.set(user);
            
        Stage stage = (Stage)NewLogOn.getScene().getWindow();
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("./MenuScene.fxml")));
        stage.setTitle("Game menu");
        stage.setScene(scene);
    }

     public void cancelButtonClick(ActionEvent event)throws IOException, InterruptedException{
        Stage stage = (Stage)CancelButton.getScene().getWindow();
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("./LoginScene.fxml")));
        stage.setScene(scene); 
    }

    @FXML
    void initialize() throws InterruptedException, IOException {
        ut = UserTab.getInstance();

        assert NewLogOn != null : "fx:id=\"NewLogOn\" was not injected: check your FXML file 'AuthorizationUserScene.fxml'.";
        assert OldLogin != null : "fx:id=\"OldLogin\" was not injected: check your FXML file 'AuthorizationUserScene.fxml'.";
        assert OldPassword != null : "fx:id=\"OldPassword\" was not injected: check your FXML file 'AuthorizationUserScene.fxml'.";
        assert LabelError != null : "fx:id=\"LabelError\" was not injected: check your FXML file 'AuthorizationUserScene.fxml'.";

    }
}
