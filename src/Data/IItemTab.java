package Data;

import java.io.IOException;
import Types.Item;
import Types.User;
/**
 * interface describing methods that should 
 * be implemented in the descendant class.
 * 
 * @author Vadim Ryzhov
 * @since 1.0
 */
public interface IItemTab {
    public Item getItem(int id)throws InterruptedException, IOException;
    public void newItem(String nameItem,  int price, User user) throws InterruptedException, IOException;
    public void saveItem(Item item)throws InterruptedException, IOException;
    public void editItem(Item item)throws InterruptedException, IOException;
}
