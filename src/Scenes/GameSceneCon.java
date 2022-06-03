package Scenes;

import Global.Globals;
import Types.User;

import java.io.IOException;
import java.net.URL;
import java.security.Provider;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import Data.ItemTab;
import Data.UserTab;
import Game.FactoryForBot;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import utils.IntRandom;


public class GameSceneCon implements Initializable {

    private ArrayList<FactoryForBot.Bot> bots;
    private int Rate;
    private User currentBuyer;
    private int time;
 
     @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label UserName;

    @FXML
    private Label UserMoney;

    @FXML
    private Label UserId;

    @FXML
    private Label TimeLabel;

    @FXML
    private Button NewPrice;

    @FXML
    private TextField NewPriceField;

    @FXML
    private TextArea textArea;

    @FXML
    private Label currentRateLabel;

    @FXML
    private Button ExitButton;

    

    @FXML
    public void exitButtonClick(ActionEvent event) throws IOException {
        int s = Globals.player.get().getMoney();
        s = s - Rate;
        Globals.player.get().setMoney(s);
        // Globals.clearUsersInAuction();
        try {
            ItemTab.getInstance().editItem(Globals.itemView.get());
            UserTab.getInstance().editUser(Globals.player.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Globals.itemView.set(null);
        Globals.initialCost.set(null);
        System.out.print(Globals.itemView.get());
        Stage stage = (Stage)ExitButton.getScene().getWindow();
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("./MenuScene.fxml")));
        stage.setScene(scene);
    }
    
    public void  newPriceClick(){
        int n = Integer.parseInt(NewPriceField.getText());
        int s = Globals.player.get().getMoney();
        s+=1;
        if (n > Rate && n < s ){
            Rate = n;
            time = 3;
            UpadateRate();
            currentBuyer = Globals.player.get();
            textArea.appendText("User " + currentBuyer.getName() + "#" + currentBuyer.getId() + " bought an item for " + Rate + "\n"); 
        }
    }

    public void UpadateMessage(){
        UserName.setText("Name: " + Globals.player.get().getName());
        // UserMoney.setText("Money: " + Globals.player.get().getMoney());
    }
    public void UpadateRate(){
        currentRateLabel.setText("The current rate: " + Rate);
    }

    void raiseTheRate(int rate, User user)
    {
        if (rate <= this.Rate || rate > user.getMoney())
            return;
        
        System.out.println(rate + ": " + user);
        textArea.appendText("User " + user.getName() + "#" + user.getId() + " raises " + (rate - this.Rate) + "\n");
        
        this.Rate = rate;
        this.currentBuyer = user;

        Platform.runLater(new Runnable()
        {
            public void run() 
            {
                UpadateRate();
            }
        });

        for (FactoryForBot.Bot bot: bots)
            bot.updateCurrentRate(this.Rate);

        time = 3;
        Platform.runLater(new Runnable()
        {
            public void run() 
            {
                TimeLabel.setText(""+time);
            }
        });
    }
   

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        UserName.setText("Name: " + Globals.player.get().getName());
        UserMoney.setText("Money: " + Globals.player.get().getMoney());
        UserId.setText("ID: " + Globals.player.get().getId());

        Globals.updateWindow.set(() -> {
            this.UpadateMessage();
        });
        
        
        Rate = Globals.initialCost.get();
        UpadateRate();
        currentBuyer = Globals.player.get();
        time = 3;

        // if (Globals.userCanTakePartInAuction.get() == null)
        // {
        //     throw new Error("Error");
        // }


        boolean userCanTakePartInAuction =  (boolean)Globals.userCanTakePartInAuction.get();

        textArea.setDisable(!userCanTakePartInAuction);
        ExitButton.setDisable(!userCanTakePartInAuction);

        // Globals.clearUsersInAuction();

        bots = new ArrayList<>();
       
        int ba = IntRandom.randomInt(2, 8);
        while (ba --> 0){
            FactoryForBot fac = new FactoryForBot();
            bots.add(fac.getBot(FactoryForBot.BotType.Passive, this::raiseTheRate));
        }        


        for (FactoryForBot.Bot bot: bots)
            bot.updateCurrentRate(this.Rate);

        for (FactoryForBot.Bot bot: bots)
            System.out.print(bot.getClass().getName() + ": " + bot.user);
        
        new Thread() {
            public void run() {
                while(true){

                    Platform.runLater(new Runnable() {

                        public void run() {
                            TimeLabel.setText(""+time);                            
                        }
                    });

                    try {
                        Thread.sleep(1000*(8-time));

                    } catch (InterruptedException ex) {

                        ex.printStackTrace();
                    }
                    if (time == 0)
                        break;
                    time -= 1;
                }
                    for (FactoryForBot.Bot bot: bots)
                        bot.stopBot();

                if (currentBuyer.getId() != Globals.player.get().getId()){
                    textArea.appendText("User " + currentBuyer.getName() + "#" + currentBuyer.getId() + " bought an item for " + Rate + "\n");
                } else{
                        try {
                            ItemTab.getInstance().getItem(Globals.itemView.get().getId()).checkId();
                            ItemTab.getInstance().getItem(Globals.itemView.get().getId()).setOwnerId(currentBuyer.getId());
                        } catch (InterruptedException | IOException | Error e) {
                            e.printStackTrace();
                        }
                        textArea.appendText("You bought this item \n");
                    }
                // ExitButton.setDisable(false);
            }
        }.start();
    }
}
    
