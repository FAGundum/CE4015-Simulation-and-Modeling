/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package telecomsimulator;

public class CallTerminationEvent extends Event {
    
    private final int stationId;
    
    public CallTerminationEvent(int time, int stationId) {
        
        super(time);
        this.stationId = stationId;
    }
    
    public int getStationId() {
        
        return this.stationId;
        
    }

    @Override
    public void handle() {
        
        // 1. Update system state
        TelecomSimulator.release(stationId);
        
        // 2. Update statistical counters
        TelecomSimulator.recordNormalCall();
        
        // 3. Generate future events and add to event list
        // No events generated for termination
        
    }
    
}
