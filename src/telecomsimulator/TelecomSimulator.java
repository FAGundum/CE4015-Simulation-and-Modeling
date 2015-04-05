/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package telecomsimulator;

import java.util.*;

public class TelecomSimulator {
    
    private static final String csvPath = "C:\\Users\\Derek\\Dropbox\\Year 4 Sem 2\\CE4015 Simulation and Modeling\\Assignment\\Output.csv";
    
    private static final int TOTAL_SIMULATION_RUNS = 1000;
    private static final int TOTAL_SIMULATION_TIMES = 1000;
    private static final int TOTAL_WARMUP_TIMES = 20000;
    
    // Simulation clock
    private static int simulationClock;

    // System state variable
    private static ArrayList<BaseStation> baseStations;
    private static int numberOfRuns;
    private static int numberOfCalls;
    private static int numberOfDroppedCalls;
    private static int numberOfBlockedCalls;
    
    private static boolean recordEnabled;
    
    // Event list
    private static LinkedList<Event> eventList;
    
    public static void main(String[] args) {
        
        numberOfRuns = 0;
        
        // initialize output file
        
        ArrayList<String> headerNames = new ArrayList<>();
        headerNames.add("Drop Rate");
        headerNames.add("Block Rate");
        
        Utils.initializeCsv(csvPath, headerNames);
        
        while(true) {
        
            initialization();

            while(true) {

                Event currentEvent = schedule();
                simulationClock = currentEvent.getTime();
                
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
                    
                    double dropRate = (double) numberOfDroppedCalls / numberOfCalls;
                    double blockRate = (double) numberOfBlockedCalls / numberOfCalls;
                    
                    ArrayList<Number> contents = new ArrayList<>();
                    contents.add(dropRate);
                    contents.add(blockRate);
                    
                    Utils.saveToCsv(csvPath, contents);
                    numberOfRuns++;
                    System.out.println(numberOfRuns*100/TOTAL_SIMULATION_RUNS + "%\n");
                    
                    break;
                    
                }
            }
            
            if (numberOfRuns >= TOTAL_SIMULATION_RUNS) {
                
                System.out.println("Simulation Ended.");
                break;
                
            }
        }
    }
    
    /**
     * The function will try to reserve a channel from base station.
     * 
     * @param baseStationId the current baseStation user is accessing.
     * @param e e parameter is used to identify which event is trying to reserve station channel
     * @return Return -1 indicating all channels have been reserved, Return 0-9 indicating this particular channel is reserved for this Call.
     * 
     */
    public static boolean tryReserve(int baseStationId, Event e) {
        
        if (baseStations.get(baseStationId).isFull(e)) {
            
            return false;
        
        }
        
        baseStations.get(baseStationId).reserveChannel();
        
        return true;
    }
    
    public static void release(int baseStationId) {
        
        baseStations.get(baseStationId).releaseChannel();
        
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
        
    }
    
    public static void recordDroppedCall() {
        
        numberOfCalls++;
        numberOfDroppedCalls++;
        
    }
    
    public static void recordBlockedCall() {
        
        numberOfCalls++;
        numberOfBlockedCalls++;
        
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
