package Game;

import java.io.IOException;

import Data.UserTab;
import Global.Globals;
import Types.User;
import utils.IntRandom;
import utils.SetGet;
/**
 * Abstract factory pattern to create bots.
 * 
 * @author Vadim Ryzhov
 * @since 1.0
 */
public class FactoryForBot {

    public FactoryForBot(){
        
    }

    public abstract interface RaiseTheRate
    {
        public abstract void raise(int rate, User user);
    }
    
    public abstract static class Bot
    {
        public int rate;
        public SetGet<Boolean> auctionIsActive;
        public User user;
        
        public void updateCurrentRate(int rate)
        {
            this.rate = rate;
        }

        public void stopBot()
        {
            auctionIsActive.set(false);
        }

        public Integer wantsToRaiseTheRate()
        {
            return null;
        }
    }

    class PlainBot extends Bot
    {
        public PlainBot(RaiseTheRate raiseTheRate)
        {
            auctionIsActive = new SetGet<>(true);
            do
                try {
                    user = UserTab.getInstance().getRandUser();
                } catch (InterruptedException | IOException e) {
                    e.printStackTrace();
                }
            while (!Globals.addUserToAuction(user));

            new Thread() {
                public void run()
                {
                    while (auctionIsActive.get())
                    {
                        Integer newRate = wantsToRaiseTheRate();
                        if (newRate != null)
                        {
                            raiseTheRate.raise(newRate, user);
                        }
                        
                        try
                        {
                            Thread.sleep(100*IntRandom.randomInt(2,6));
                        } catch (InterruptedException ex)
                        {
                            ex.printStackTrace();
                        }
                    }
                }
            }.start();
        }

        @Override
        public Integer wantsToRaiseTheRate()
        {
            if (rate >= user.getMoney()/4 || IntRandom.randomInt(1, 9) != 2)
                return null;
            return IntRandom.randomInt(rate, rate);
        }
    }
    
    class PassiveBot extends PlainBot
    {
        public PassiveBot(RaiseTheRate raiseTheRate)
        {
            super(raiseTheRate);
        }

        @Override
        public Integer wantsToRaiseTheRate()
        {
            if (rate >= user.getMoney()/3 || IntRandom.randomInt(1, 9) != 2)
                return null;
            
            return rate + IntRandom.randomInt(5, user.getMoney()/4);
        }
    }

    public enum BotType
    {
        Passive
    }

    public Bot getBot(BotType botType, RaiseTheRate raiseTheRate) {
        
        switch (botType)
        {            
            case Passive:
                return new PassiveBot(raiseTheRate);
            
            default:
                return null;
        }
    }


}
