package Scenes;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Data.ItemTab;
import Global.Globals;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CreateItemSceneCon {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField NameNewItem;

    @FXML
    private TextField NewPriceItem;

    
    public void saveItemClick(ActionEvent event) throws NumberFormatException, InterruptedException, IOException {

        ItemTab.getInstance().newItem(NameNewItem.getText(), Integer.parseInt(NewPriceItem.getText()),Globals.player.get());

        // ut.getInstance().editUser(Globals.player.get());

        Globals.updateWindow.get().call();
        
        Stage stage = (Stage)NameNewItem.getScene().getWindow();
        stage.close(); 
    }

    @FXML
    void initialize() {
        assert NameNewItem != null : "fx:id=\"NameNewItem\" was not injected: check your FXML file 'CreatItemScene.fxml'.";
        assert NewPriceItem != null : "fx:id=\"NewPriceItem\" was not injected: check your FXML file 'CreatItemScene.fxml'.";

    }
}
