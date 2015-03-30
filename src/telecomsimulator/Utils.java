/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package telecomsimulator;

import java.util.Random;

// Library routine
public class Utils {
    
    private static final int SEED = 1;
    
    public static int generateInterArrivalTime() {
        
        //double random = Math.random();
        double random = new Random(SEED).nextDouble();
        
        return (int) Math.floor(-1000 / 0.7300976 * Math.log(1 - random));
    }

    public static double generateCarSpeed() {
        
        //double random = Math.random();
        Random randomGenerator = new Random(SEED);
        double random = 0;
        
        for (int i = 1; i <=12; i++) {
            random += randomGenerator.nextDouble();
        }
        
        random -= 6;
        
        return (random * 9.019) + 120.07;
    }

    public static int generateStationId() {
       
        //double random = Math.random();
        double random = new Random(SEED).nextDouble();
        
        return (int) Math.ceil(random * 20);
    }

    public static double generatePosition() {
        
        //double random = Math.random();
        double random = new Random(SEED).nextDouble();
        
        return random * 2;
    }

    public static int generateDuration() {
        
        //double random = Math.random();
        double random = new Random(SEED).nextDouble();
        
        return (int) Math.floor(-1000 / 0.0091045 * Math.log(1 - random));
    }
    
}
