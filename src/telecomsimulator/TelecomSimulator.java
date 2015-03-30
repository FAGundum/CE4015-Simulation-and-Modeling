/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package telecomsimulator;

import java.util.ArrayList;
import simulationevents.*;

public class TelecomSimulator {
    
    // Simulation clock
    private static int simulationClock;

    // System state variable
    private static ArrayList<BaseStation> baseStations;
    private static int numberOfCalls;
    private static int numberOfDroppedCalls;
    private static int numberOfBlockedCalls;
    
    // Event list
    private static ArrayList<Events> eventList;
    
    
    
    public static void main(String[] args) {
        
        initialization();
        
    }
    
    private static void initialization() {
        
        simulationClock = 0;
        
        numberOfCalls = 0;
        numberOfDroppedCalls = 0;
        numberOfBlockedCalls = 0;
        baseStations = new ArrayList<>();
        
        // initialize 20 base stations
        for (int i = 1; i <= 20; i++) {
            
            baseStations.add(i, new BaseStation(i));
            
        }
        
        // initialize event list, the event list will start with a Call Initiation Event
        eventList = new ArrayList<>();
        eventList.add(new CallInitiationEvent(0, 120, 1, 0, 110));
        
    }
    
}
