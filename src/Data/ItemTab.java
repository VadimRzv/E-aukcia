package Data;

import Types.Item;
import Types.User;

import java.io.IOException;
import java.util.ArrayList;

import utils.SetGet;
/**
 * Interaction with the database. 
 * Also to create new items.
 * 
 * @author Vadim Ryzhov
 * @since 1.0
 */
public class ItemTab implements IItemTab{

    private JSonDB db;
    // public Object pim;
    private static ItemTab instance = null;
    
    private  ItemTab() throws InterruptedException, IOException {
        db = JSonDB.getInstance();
    }

    public static ItemTab getInstance()throws InterruptedException, IOException {
        if (instance == null)
            instance = new ItemTab();
        return instance;
    }
    /**
     * Method a new subject is used. Gets the necessary 
     * parameters and creates a new object of the Item class.
     * 
     * @throws InterruptedException
     * @throws IOException
     * @param nameItem - the name of the new item, must be of type String
     * @param price - the price of the item should be of type int
     * @param user -object, used to get the user's id and name, type User
     */
    public void newItem(String nameItem, int price, User user) throws InterruptedException, IOException {
        SetGet<Integer> newItemId = new SetGet<Integer>();
        Item item = new Item(nameItem,db.getData().items.size(),price,user.getName(), user.getId());

        newItemId.set(user.getId());
        db.getData().items.add(item);
        db.saveData();
    }
    /**
     * searching for an item by ID in the database
     * 
     * @throws InterruptedException
     * @throws IOException
     * @param id - is used to search by id
     * @return i - will return the Item object if it exists 
     * @return null -  null if the item does not exist.
     */
    public Item getItem(int id)throws InterruptedException, IOException { 
        for (Item i : db.getData().items)
        if (i.getId() == id)
            return i;
    return null;
    }
    /**
     * Saving the item to the database.
     * Аccesses the database, writes the data 
     * and saves it.
     * 
     * @throws InterruptedException
     * @throws IOException
     */
    public void saveItem(Item item)throws InterruptedException, IOException{
        db.getData().items.add(item);
        db.saveData();
    }
    /**
     * Accesses the database. 
     * To retrieve user's items.
     * 
     * @param user - current user, to get an id
     * @return items - returns a list of items
     * @throws InterruptedException
     * @throws IOException
     */
    public ArrayList<Item> getUserItems(User user) throws InterruptedException, IOException{
        ArrayList<Item> items = new ArrayList<>();
        for (Item i : db.getData().items){
            if (i.getOwnerId() == user.getId())
            items.add(i);
        }
        return items;
    }
    /**
     * Gets the modified item and looks for the item 
     * in the database and changes the ownerId.
     * 
     * @throws InterruptedException
     * @throws IOException
     */
    public void editItem(Item item)throws InterruptedException, IOException{
        Item itemData = getItem(item.getId());
        
        itemData.setOwnerId(item.getOwnerId());

        db.saveData();
    }
}
