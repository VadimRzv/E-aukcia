package Scenes;

import Global.Globals;
import Types.User;
import Data.UserTab;
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

public class CreatUserSceneCon {

    private  UserTab ut;


    @FXML
    private Button SigIn;

    @FXML
    private PasswordField NewPassword;

    @FXML
    private TextField NewLogin;

    @FXML
    private TextField NewMoney;

    @FXML
    private TextField NewName;

    @FXML
    private TextField NewSurname;

    @FXML
    private Button CancelButton;

    @FXML
    private Label LabelError;

    public void sigInClick(ActionEvent event)throws IOException, InterruptedException{
        User user = ut.getUser(NewLogin.getText());
        if (user != null){
            LabelError.setText("A user with this login exists");
            sigInClick(event);
        }
        else{
            Globals.player.set(
                ut.newUser(
                    NewName.getText(), 
                    NewSurname.getText(), 
                    NewLogin.getText(), 
                    NewPassword.getText(), 
                    Integer.parseInt(NewMoney.getText())
                )
            );
            
            
            Stage stage = (Stage)SigIn.getScene().getWindow();
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("./MenuScene.fxml")));
            stage.setTitle("Game menu");
            stage.setScene(scene);
        }  
    }

    public void cancelButtonClick(ActionEvent event)throws IOException, InterruptedException{
        Stage stage = (Stage)CancelButton.getScene().getWindow();
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("./LoginScene.fxml")));
        stage.setScene(scene); 
    }

    @FXML
    void initialize() throws InterruptedException, IOException {
        ut = UserTab.getInstance();
        
        assert SigIn != null : "fx:id=\"SigIn\" was not injected: check your FXML file 'CreateUserScene.fxml'.";
        assert NewPassword != null : "fx:id=\"NewPassword\" was not injected: check your FXML file 'CreateUserScene.fxml'.";
        assert NewLogin != null : "fx:id=\"NewLogin\" was not injected: check your FXML file 'CreateUserScene.fxml'.";
        assert CancelButton != null : "fx:id=\"CancelButton\" was not injected: check your FXML file 'CreateUserScene.fxml'.";

    }
}
