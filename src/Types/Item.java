package Types;

import java.io.IOException;

import Data.ItemTab;
/**
 * To create an object. Inherited from an abstract class.
 * To interact with the database.
 * 
 * @author Vadim Ryzhov
 * @since 1.0
 */
public class Item extends AbsItem {
    ItemTab it;
    
    public Item(String nameItem, int id,  int price, String owner, int ownerId) {

        super(nameItem, id, price, owner, ownerId);
    }

    /**
     * A basic method for saving a item to a database.
     * 
     * @throws InterruptedException
     * @throws IOException
     */

    public void commit()throws InterruptedException, IOException{
        it.saveItem(this);
    }

     /**
     * Сreates custom exceptions, if the ID is 
     * negative(called Error)
     * 
     * @throws Error
     */
    public void checkId() throws Error{
        if (getId() < 0){
            throw new Error("/ Error Item ID");
        }
    }
}
