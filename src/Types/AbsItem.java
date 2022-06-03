package Types;

import java.io.IOException;
/**
 * It is an abstract class that provides basic methods 
 * and properties to other classes.
 * Contains methods for successfully working with the 
 * future object:
 * <ul>
 * <li>Save the item
 * <li>Item ID check 
 * /ul>
 * <p>
 * Saving a item is done by connecting to the database 
 * and saving the item.
 * <p>
 * Сreates custom exceptions, if the ID is 
 * negative(called Error)
 * 
 * @author Vadim Ryzhov
 * @since 1.0
 */
public abstract class AbsItem {
    private String nameItem;
    private int id;
    private int ownerId;
    private int price;
    private String owner;

    public AbsItem(String nameItem, int id,  int price, String owner, int ownerId){
        this.nameItem = nameItem;
        this.id   = id;
        this.price = price;
        this.owner= owner;
        this.ownerId = ownerId;
    }
    public String toString() {
        return "name item:" + nameItem + "\n" + "name user:" + owner + "\n" + "price:" + price + "\n"  + "id:" + id + "\n" + "ownerId:" + ownerId ;
    }
    public String getNameItem(){ return nameItem; }

    public int getId(){ return id; }

    public int getPrice(){ return price; }
    
    public int getOwnerId(){ return ownerId; }

    public String getOwner(){ return owner; }   

    public void setNameItem(String newNameItem){ this.nameItem = newNameItem; }

    public void setId(int newId){ this.id = newId; }

    public void setPrice(int newPrice){ this.price = newPrice; }

    public void setOwner(String newOwner){ this.owner = newOwner; }

    public void setOwnerId(int newOwnerId){ this.ownerId = newOwnerId; }
    

    public void commit() throws InterruptedException, IOException{ }   
    public void checkId()throws Error{ }
}
