/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package telecomsimulator;

import java.util.*;

public class TelecomSimulator {
    
    private static final int TOTAL_SIMULATION_TIMES = 1000;
    private static final int TOTAL_WARMUP_TIMES = 20;
    
    // Simulation clock
    private static int simulationClock;

    // System state variable
    private static ArrayList<BaseStation> baseStations;
    private static int numberOfCalls;
    private static int numberOfDroppedCalls;
    private static int numberOfBlockedCalls;
    
    // Event list
    private static LinkedList<Event> eventList;
    
    public static void main(String[] args) {
        
        initialization();
        
        while(true) {
            
            Event currentEvent = schedule();
            execute(currentEvent);
            
            if (numberOfCalls > TOTAL_SIMULATION_TIMES) {
                
                // Compute statistics and write report
                break;
            }
        }
        
    }
    
    /**
     * The function will try to reserve a channel from base station.
     * 
     * @param baseStationId the current baseStation user is accessing.
     * @return Return 0 indicating all channels have been reserved, Return 1-10 indicating this particular channel is reserved for this Call.
     * 
     */
    public static int tryReserve(int baseStationId) {
        
        int channelId = baseStations.get(baseStationId).isFull();
        
        if (channelId == 0) {
            
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
        baseStations = new ArrayList<>();
        
        // initialize 20 base stations
        for (int i = 1; i <= 20; i++) {
            
            baseStations.add(i, new BaseStation(i));
            
        }
        
        // initialize event list, the event list will start with a Call Initiation Event
        eventList = new LinkedList<>();
        eventList.push(new CallInitiationEvent(0, 120, 1, 0, 110));
        
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
