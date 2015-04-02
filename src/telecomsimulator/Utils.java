/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package telecomsimulator;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
//import java.util.Random;

// Library routine
public class Utils {
    
    //private static int seed = 1;
    private static final String distributionTestPath = "C:\\Users\\Derek\\Dropbox\\Year 4 Sem 2\\CE4015 Simulation and Modeling\\Assignment\\DistributionTest.csv";
    
    public static void main(String[] args) {
        
        ArrayList<String> headerNames = new ArrayList<>();
        ArrayList<Number> contents = new ArrayList<>();
        
        headerNames.add("inter arrival time");
        headerNames.add("station id");
        
        initializeCsv(distributionTestPath, headerNames);
        
        for (int i = 1; i <= 10000; i++) {
            
            Integer interArrivalTime = Utils.generateInterArrivalTime();
            contents.add(interArrivalTime);
            
            Integer stationId = Utils.generateStationId();
            contents.add(stationId);
            
            saveToCsv(distributionTestPath, contents);
            
            contents.clear();
            
        }
        
    }
    
    public static int generateInterArrivalTime() {
        
        double random = Math.random();
        //double random = new Random(seed).nextDouble();
        //seed = new Random(seed).nextInt();
        
        return (int) Math.floor(-1000 / 0.7300976 * Math.log(1 - random));
    }

    public static double generateCarSpeed() {
        
        //Random randomGenerator = new Random(seed);
        //seed = new Random(seed).nextInt();
        double random = 0;
        
        for (int i = 1; i <=12; i++) {
            random += Math.random();
        }
        
        random -= 6;
        
        return (random * 9.019) + 120.07;
    }

    public static int generateStationId() {
       
        double random = Math.random();
        //double random = new Random(seed).nextDouble();
        //seed = new Random(seed).nextInt();
        
        return (int) Math.floor(random * 20);
    }

    public static double generatePosition() {
        
        double random = Math.random();
        //double random = new Random(seed).nextDouble();
        //seed = new Random(seed).nextInt();
        
        return random * 2;
    }

    public static int generateDuration() {
        
        double random = Math.random();
        //double random = new Random(seed).nextDouble();
        //seed = new Random(seed).nextInt();
        
        return (int) Math.floor(-1000 / 0.0091045 * Math.log(1 - random));
    }

    static void saveToCsv(String filePath, ArrayList<Number> contents) {
        
        try
        {
            FileWriter writer = new FileWriter(filePath, true);
            
            Iterator<Number> iterator = contents.iterator();
            
            while(iterator.hasNext()) {
            
                writer.write(iterator.next().toString());
                writer.append(',');
            
            }
            
            writer.write(" ");
            writer.append("\n");
            
            writer.flush();
            writer.close();
            
        } catch (IOException e) {
            
            System.out.println(e.getMessage());
            
        }
        
    }

    static void initializeCsv(String filePath, ArrayList<String> headerNames) {
        
        try
        {
            FileWriter writer = new FileWriter(filePath);
            
            Iterator<String> iterator = headerNames.iterator();
            
            while(iterator.hasNext()) {
            
                writer.write(iterator.next());
                writer.append(',');
            
            }
            
            writer.write("Remarks");
            writer.write("\n");
            
            writer.flush();
            writer.close();
            
        } catch (IOException e) {
            
            System.out.println(e.getMessage());
            
        }
        
    }
    
}
