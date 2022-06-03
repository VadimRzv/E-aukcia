package Data;

import java.io.IOException;

import Types.User;
/**
 * interface describing methods that should 
 * be implemented in the descendant class.
 * 
 * @author Vadim Ryzhov
 * @since 1.0
 */
public interface IUserTab {
    public User getUser(int id) throws InterruptedException, IOException;
    public User getUser(String login) throws InterruptedException, IOException;
    public User newUser(String name, String surname, String login, String password, int money) throws InterruptedException, IOException;
    public void editUser(User user)throws InterruptedException, IOException;
    public void saveUser(User user)throws InterruptedException, IOException;
    public User getRandUser()throws InterruptedException, IOException;
}
