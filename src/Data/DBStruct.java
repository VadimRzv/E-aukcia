package Data;

import java.util.ArrayList;

import Types.Item;
import Types.User;

/**
 * Сlass that is used to create the structure 
 * that is used in the database.
 * 
 * 
 * @author Vadim Ryzhov
 * @since 1.0
 */
public class DBStruct {
    public ArrayList<Item> items;
    public ArrayList<User> users;
    
    DBStruct(ArrayList<User> users, ArrayList<Item> items){
        this.users = users;
        this.items = items;
    }   
}
