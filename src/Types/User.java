package Types;

import Data.IUserTab;
import java.io.IOException;
import java.util.ArrayList;

import Data.ItemTab;

/**
 * To create an object. Inherited from an abstract class.
 * To interact with the database.
 * @author Vadim Ryzhov
 * @since 1.0
 */
public class User extends AbsUser {

    IUserTab utab;
    
    public User(String name, String surname, String login, String password, int id, int money) {
      super(name, surname, login, password, id, money);
    }

     /**
     * Saves the user.
     * Binds to the database and saves the existing user.
     * 
     * @throws InterruptedException
     * @throws IOException
     */
    public void commit()throws InterruptedException, IOException{
        utab.saveUser(this);
    }
    
   /**
     * Getting a list from the user's items.
     * Links to the database and returns a list of user 
     * items.
     * 
     * @return a list of user's items 
     * @throws InterruptedException
     * @throws IOException
     */
    public ArrayList<Item> getItem() throws InterruptedException, IOException{
        return ItemTab.getInstance().getUserItems(this);
    }
}
