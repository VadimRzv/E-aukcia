package utils;

import java.util.Random;

public class IntRandom {

    public static int randomInt(int n1,int n){
        Random rand = new Random();  
        return rand.nextInt(n)+n1;
    }

    

}
