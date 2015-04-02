/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package telecomsimulator;

import java.util.*;

public class TelecomSimulator {
    
    private static final String csvPath = "C:\\Users\\Derek\\Dropbox\\Year 4 Sem 2\\CE4015 Simulation and Modeling\\Assignment\\Output.csv";
    
    private static final int TOTAL_SIMULATION_TIMES = 80000;
    private static final int TOTAL_WARMUP_TIMES = 20000;
    
    // Simulation clock
    private static int simulationClock;

    // System state variable
    private static ArrayList<BaseStation> baseStations;
    private static int numberOfCalls;
    private static int numberOfDroppedCalls;
    private static int numberOfBlockedCalls;
    
    private static boolean recordEnabled;
    
    // Event list
    private static LinkedList<Event> eventList;
    
    public static void main(String[] args) {
        
        initialization();
        
        while(true) {
            
            Event currentEvent = schedule();
            execute(currentEvent);
            
            if (numberOfCalls >= TOTAL_WARMUP_TIMES) {
                
                numberOfCalls = 0;
                numberOfDroppedCalls = 0;
                numberOfBlockedCalls = 0;
                recordEnabled = true;
                break;
                
            }
            
        }
        
        while(true) {
            
            Event currentEvent = schedule();
            execute(currentEvent);
            
            if (numberOfCalls >= TOTAL_SIMULATION_TIMES) {
                
                // Compute statistics and write report
                System.out.println("Total call simulated: " + numberOfCalls);
                System.out.println("Total dropped call: " + numberOfDroppedCalls);
                System.out.println("Total blocked call: " + numberOfBlockedCalls);
                break;
            }
        }
        
    }
    
    /**
     * The function will try to reserve a channel from base station.
     * 
     * @param baseStationId the current baseStation user is accessing.
     * @return Return -1 indicating all channels have been reserved, Return 0-9 indicating this particular channel is reserved for this Call.
     * 
     */
    public static int tryReserve(int baseStationId, Event e) {
        
        int channelId = baseStations.get(baseStationId).isFull(e);
        
        if (channelId == -1) {
            
            return channelId;
        
        }
        
        baseStations.get(baseStationId).reserveChannel(channelId);
        
        return channelId;
    }
    
    public static void release(int baseStationId, int channelId) {
        
        baseStations.get(baseStationId).releaseChannel(channelId);
        
    }
    
    public static void insertEvent(Event e) {
        
        boolean inserted = false;
        
        for (int i = 0; i < eventList.size(); i++) {
            
            if (eventList.get(i).getTime() > e.getTime()) {
                
                eventList.add(i, e);
                inserted = true;
                break;
                
            }
            
        }
        
        if (inserted == false) {
            
            eventList.addLast(e);
            
        }
        
    }
    
    public static void recordNormalCall() {
        
        numberOfCalls++;
        
        ArrayList<Number> contents = new ArrayList<>();
        contents.add(1);
        contents.add(0);
        contents.add(0);
        
        if (recordEnabled) {
            Utils.saveToCsv(csvPath, contents);
        }
    }
    
    public static void recordDroppedCall() {
        
        numberOfCalls++;
        numberOfDroppedCalls++;
        
        ArrayList<Number> contents = new ArrayList<>();
        contents.add(1);
        contents.add(1);
        contents.add(0);
        
        if (recordEnabled) {
            Utils.saveToCsv(csvPath, contents);
        }
    }
    
    public static void recordBlockedCall() {
        
        numberOfCalls++;
        numberOfBlockedCalls++;
        
        ArrayList<Number> contents = new ArrayList<>();
        contents.add(1);
        contents.add(0);
        contents.add(1);
        
        if (recordEnabled) {
            Utils.saveToCsv(csvPath, contents);
        }
    }
    
    // Initialization Routine
    private static void initialization() {
        
        simulationClock = 0;
        
        numberOfCalls = 0;
        numberOfDroppedCalls = 0;
        numberOfBlockedCalls = 0;
        recordEnabled = false;
        baseStations = new ArrayList<>();
        
        // initialize 20 base stations
        for (int i = 0; i < 20; i++) {
            
            baseStations.add(i, new BaseStation(i, 0)); // 0 means no reservation
            
        }
        
        // initialize event list, the event list will start with a Call Initiation Event
        eventList = new LinkedList<>();
        eventList.push(new CallInitiationEvent(0, 120, 0, 0, 110000));
        
        // initialize output file
        
        ArrayList<String> headerNames = new ArrayList<>();
        headerNames.add("Total number of calls");
        headerNames.add("Total number of dropped calls");
        headerNames.add("Total number of blocked calls");
        
        Utils.initializeCsv(csvPath, headerNames);
        
    }
    
    // Timing Routine
    private static Event schedule() {
        
        Event currentEvent = eventList.pop();
        simulationClock = currentEvent.getTime();
        
        return currentEvent;
        
    }
    
    // Event Routine
    private static void execute(Event e) {
        
        e.handle();
        
    }
    
}
