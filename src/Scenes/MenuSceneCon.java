package Scenes;

import Global.Globals;
import Types.Item;
import Data.ItemTab;
import utils.IntRandom;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class MenuSceneCon implements Initializable{

    public class ItemsShop {
        private Item item;

        public ItemsShop(Item item) {
            this.item = item;
        }

        public String getName() { return item.getNameItem(); }
        public int getCode() { return item.getId(); }
        public String getOwner() { return item.getOwner(); }
        public int getPrice() { return item.getPrice(); }
        
     
    }

    public class UserItems{
        private Item item;

        public UserItems(Item item) {
            this.item = item;
        }

        public String getName() { return item.getNameItem(); }
        public int getPrice() { return item.getPrice(); }
        public int getCode() { return item.getId(); }
    }

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label UserMoney;

    @FXML
    private Label UserId;

    @FXML
    private Label UserName;
    
    @FXML
    private Button EditUser;

    @FXML
    private Button SingOf;

    @FXML
    private Button CreatItem;

    @FXML
    private Button BuyItem;

    @FXML
    private TableView<ItemsShop> TableShopItem;
    @FXML
    private TableColumn<ItemsShop,String> NameItem;
    @FXML
    private TableColumn<ItemsShop,Integer> ItemId;
    @FXML
    private TableColumn<ItemsShop,Integer> PriceItem;  
    @FXML
    private TableColumn<ItemsShop,String> OwnerItem;

    @FXML
    private TableView<UserItems> TableUserItem;
    @FXML
    private TableColumn<UserItems, String> NameUserItem;
    @FXML
    private TableColumn<UserItems, Integer> IdUserItem;
    @FXML
    private TableColumn<UserItems, Integer> PriceUserItem;

 private ObservableList<ItemsShop> itemShop = FXCollections.observableArrayList();
 private ObservableList<UserItems> userItems = FXCollections.observableArrayList();

    public void editUserClick(ActionEvent event)throws IOException{              
        Stage stage = new Stage();
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("./EditUserScene.fxml")));
        stage.setScene(scene);
        stage.show();
        
    }
    

    public void singOfClick(ActionEvent event)throws IOException{
        Stage stage = (Stage)SingOf.getScene().getWindow();
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("./LoginScene.fxml")));
        Globals.player.set(null);
        stage.setScene(scene);
        stage.show();
    }

    public void UpadateMessage(){
        UserName.setText("Name: " + Globals.player.get().getName());
        UserMoney.setText("Money: " + Globals.player.get().getMoney());
        UserId.setText("ID: " + Globals.player.get().getId());
    }

    public void buyItemClick(ActionEvent event) throws IOException {
        ItemsShop pim = TableShopItem.getSelectionModel().getSelectedItem();
        if (pim == null)
            return;
        // System.out.print(pim.item);
        Globals.itemView.set(pim.item);
        Globals.initialCost.set(Globals.itemView.get().getPrice());
        Globals.userCanTakePartInAuction.set(true);
        Stage stage = (Stage)BuyItem.getScene().getWindow();
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("./GameScene.fxml")));
        stage.setScene(scene);
        stage.show();
    }

   
    public void creatItemClick(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("./CreatItemScene.fxml")));
        stage.setScene(scene);
        stage.show();
    } 

    
    public void soldItemClick(ActionEvent event) throws IOException {
        UserItems pim = TableUserItem.getSelectionModel().getSelectedItem();
        if (pim == null)
            return;
        int n = IntRandom.randomInt(0, 1);
        int s = Globals.player.get().getMoney();

        if (n == 1){
            s +=pim.item.getPrice() * 0.25 + pim.item.getPrice(); 
            Globals.player.get().setMoney(s);
        }else{
            s +=pim.item.getPrice() * 0.25 + pim.item.getPrice(); 
            Globals.player.get().setMoney(s);
           
        }
        Item item;
        try { 
            item = ItemTab.getInstance().getItem(pim.getCode());
            item.setOwnerId(0);
            ItemTab.getInstance().editItem(item);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.UpadateMessage();
        
        try {
            this.updateTables();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
       
        
       
    }

    public void updateTables()throws IOException, InterruptedException{

        itemShop.clear();
        userItems.clear();

        TableShopItem.refresh();
        TableUserItem.refresh();

        
        NameItem.setCellValueFactory(new PropertyValueFactory<>("Name"));
        ItemId.setCellValueFactory(new PropertyValueFactory<>("Code"));
        PriceItem.setCellValueFactory(new PropertyValueFactory<>("Price"));
        OwnerItem.setCellValueFactory(new PropertyValueFactory<>("Owner"));
        
        for(int i = 0; i < IntRandom.randomInt(1,9);i++)
            itemShop.add(new ItemsShop(ItemTab.getInstance().getItem(IntRandom.randomInt(1,9))));

        TableShopItem.setItems(itemShop);

        NameUserItem.setCellValueFactory(new PropertyValueFactory<>("Name"));
        IdUserItem.setCellValueFactory(new PropertyValueFactory<>("Code"));
        PriceUserItem.setCellValueFactory(new PropertyValueFactory<>("Price"));

        for (Item i: Globals.player.get().getItem())
            userItems.add(new UserItems(i));
        
        TableUserItem.setItems(userItems);
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1){
        UserName.setText("Name: " + Globals.player.get().getName());
        UserMoney.setText("Money: " + Globals.player.get().getMoney());
        UserId.setText("ID: " + Globals.player.get().getId());


            try {
                updateTables();
            } catch (IOException e1) {
                e1.printStackTrace();
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
       
        
        Globals.updateWindow.set(() -> {
            this.UpadateMessage();
            
                try {
                    this.updateTables();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
        });
    }
}

