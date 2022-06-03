package Scenes;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Data.UserTab;
import Global.Globals;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EditUserSceneCon implements Initializable{

    private  UserTab ut;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField NewName;

    @FXML
    private TextField NewSurname;

    
    public void saveEditClick(ActionEvent event) throws IOException, InterruptedException {
        Globals.player.get().setName(NewName.getText());
        Globals.player.get().setSurname(NewSurname.getText());
        ut.getInstance().editUser(Globals.player.get());

        Globals.updateWindow.get().call();
        
        Stage stage = (Stage)NewName.getScene().getWindow();
        stage.close();  
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1){ 
    }
}
