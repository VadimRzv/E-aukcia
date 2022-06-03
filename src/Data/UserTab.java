package Data;

import Types.*;
import utils.SetGet;
import utils.IntRandom;

import java.io.IOException;

/**
 * Interaction with the database. 
 * Also to create new user.
 * 
 * @author Vadim Ryzhov
 * @since 1.0
 */
public class UserTab implements IUserTab{
    
    private JSonDB db;
    private static UserTab instance = null;
    
    
    private  UserTab() throws InterruptedException, IOException {
        db = JSonDB.getInstance();
    }

    public static UserTab getInstance()throws InterruptedException, IOException {
        if (instance == null)
            instance = new UserTab();
        return instance;
    }
    /**
     * searching for an user by ID in the database
     * 
     * @throws InterruptedException
     * @throws IOException
     * @param id - is used to search by id
     * @return u - will return the user object if it exists 
     * @return null -  null if the user does not exist.
     */
    public User getUser(int id) throws InterruptedException, IOException {
        for (User u : db.getData().users)
            if (u.getId() == id)
                return u;
        return null;
    }
    /**
     * searching for an user by login in the database
     * 
     * @throws InterruptedException
     * @throws IOException
     * @param login - is used to search by login
     * @return u - will return the user object if it exists 
     * @return null -  null if the user does not exist.
     */
    public User getUser(String login) throws InterruptedException, IOException {
        for (User u : db.getData().users)
            if (u.getLogin().equals(login))
                return u;
        return null;
    }

   /**
     * Method a new subject is used. Gets the necessary 
     * parameters and creates a new object of the User class.
     * 
     * @throws InterruptedException
     * @throws IOException
     * @param name - the name of the new user, must be of type String
     * @param login - the login of the new user, must be of type String
     * @param password - the password of the new user, must be of type String
     * @param surname - the surname of the new user, must be of type String
     * @param money - the price of the user should be of type int
     * @return user- returns a new user 
     */
    public User newUser(String name, String surname, String login, String password, int money) throws InterruptedException, IOException { 
        if (getUser(login) != null)
            return null;
        User user = new User(name, surname, login, password, db.getData().users.size(), money);
        db.getData().users.add(user);
        db.saveData();
        return user;
    }
 
    /**
     * User changes. Gets the changed user 
     * and saves it to the database
     * 
     * @throws InterruptedException
     * @throws IOException
     * @param user - gets the object 
     */
    public void editUser(User user)throws InterruptedException, IOException{
        User userData = getUser(user.getId());

        userData.setPassword(user.getPassword());
        userData.setLogin(user.getLogin());
        userData.setName (user.getName());
        userData.setSurname(user.getSurname());
        userData.setMoney(user.getMoney());

      db.saveData();
    }
    /**
     * Saving the item to the database.
     * Аccesses the database, writes the data 
     * and saves it.
     * 
     * @throws InterruptedException
     * @throws IOException
     */
    public void saveUser(User user)throws InterruptedException, IOException{
         db.getData().users.add(user);
         db.saveData();
    }
    /**
     * Accesses the database to retrieve a 
     * random user by id.
     * 
     * @throws InterruptedException
     * @throws IOException
     * @return returns a random user from the database
     */
    public User getRandUser()throws InterruptedException, IOException{
        SetGet<User> user = new SetGet<User>();
        user.set(getUser(IntRandom.randomInt(1, 4)));
        return user.get();
    }
}