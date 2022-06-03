package Types;

import java.io.IOException;
import java.util.ArrayList;
/**
 * It is an abstract class that provides basic methods 
 * and properties to other classes.
 * Contains methods for successfully working with the 
 * future object:
 * <ul>
 * <li>Save the user
 * <li>Get user's item 
 * /ul>
 * <p>
 * Saving a user is done by connecting to the database 
 * and saving the user.
 * <p>
 * Getting the user's item is done by connecting to the 
 * database and searching for an id.
 * 
 * @author Vadim Ryzhov
 * @since 1.0
 */
public abstract class AbsUser {
    private String name;
    private String surname;
    private String login;
    private String password;
    private int id;
    private int money;

    public AbsUser(String name, String surname, String login, String password, int id, int money){
        this.name = name;
        this.surname = surname;
        this.id = id;
        this.money = money;
        this.login = login;
        this.password = password;
    }
    public String getName(){ return name; }

    public String getSurname(){ return surname; }

    public int getId(){ return id; }

    public int getMoney(){ return money; }

    public String getLogin(){ return login; }

    public String getPassword(){ return password; }

    public void setName(String newName){ this.name = newName; }

    public void setSurname(String newSurname){ this.surname = newSurname; }

    public void setId(int newId){ this.id = newId; }

    public void setMoney(int newMoney){ this.money = newMoney; }

    public void setLogin(String newLogin){ this.login = newLogin; }

    public void setPassword(String newPassword){ this.password = newPassword; }

    public String toString() {
        return "name:" + name + " " + "surname:" + surname + " " + "login:" + login + " " + "password:" + password + " " + "id:" + id + " " + "money:" + money ;
    }

    public void commit() throws InterruptedException, IOException{ }

    public ArrayList<Item> getItem() throws InterruptedException, IOException{
        return null;
    }
}
