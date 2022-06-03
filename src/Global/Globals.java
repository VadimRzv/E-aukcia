package Global;

import java.util.ArrayList;

import Types.Item;
import Types.User;
import utils.SetGet;
/**
 * Storage of the current user, 
 * as well as the item that is in the auction.
 * 
 * @author Vadim Ryzhov
 * @since 1.0
 */
public class Globals {

    public interface VoidCall {
        void call();
    }
    
    public static SetGet<User> player = new SetGet<>();
    public static SetGet<VoidCall> updateWindow = new SetGet<>();
    public static SetGet<Item> itemView = new SetGet<>();
    public static SetGet<Integer> initialCost = new SetGet<>();
    public static SetGet<Boolean> userCanTakePartInAuction = new SetGet<>();

    public static SetGet<ArrayList<User>> usersInAuction = new SetGet<>(new ArrayList<User>());

    public static void clearUsersInAuction()
    {
        usersInAuction.set(new ArrayList<User>());
    }

    public static boolean usersAtTheAuction(User user)
    {
        for (User u: usersInAuction.get())
            if (user.getId() == u.getId() || player.get() == null || u.getId() == player.get().getId())
                return true;
        return false;
    }

    public static boolean addUserToAuction(User user)
    {
        if (usersAtTheAuction(user))
            return false;
        
        usersInAuction.get().add(user);
        return true;
    }

}
