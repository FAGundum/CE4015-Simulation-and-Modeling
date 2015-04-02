/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package telecomsimulator;

public class CallHandOverEvent extends Event {
    
    private final double speed;
    private final int stationId;
    private final int channelId;
    private final int remainingDuration;
    
    public CallHandOverEvent(int time, double speed, int stationId, int channelId, int remainingDuration) {
        
        super(time);
        this.speed = speed;
        this.stationId = stationId;
        this.channelId = channelId;
        this.remainingDuration = remainingDuration;
        
    }
    
    public double getSpeed() {
        
        return this.speed;
        
    }
    
    public int getStationId() {
        
        return this.stationId;
        
    }
    
    public int getChannelId() {
        
        return this.channelId;
        
    }
    
    public int getRemainingDuration() {
        
        return this.remainingDuration;
        
    } 

    @Override
    public void handle() {
        
        // 1. Update system state
        TelecomSimulator.release(stationId - 1, channelId);
        int newChannelId = TelecomSimulator.tryReserve(stationId, this);
        
        // 2. Update statistical counters
        if (newChannelId == -1) {
            
            TelecomSimulator.recordDroppedCall();
            return;
            
        }
        
        // 3. Generate future events and add to event list
        double distanceToNextStation = 2;
        int durationToNextStation = (int) Math.round(distanceToNextStation / speed * 3600 * 1000);
        
        if (remainingDuration > durationToNextStation && stationId != 19) {
            
            // Need to handover
            int newEventTime = time + durationToNextStation;
            int newEventStationId = stationId + 1;
            int newRemainingDuration = remainingDuration - durationToNextStation;
            
            TelecomSimulator.insertEvent(new CallHandOverEvent(newEventTime, speed, newEventStationId, newChannelId, newRemainingDuration));
            
        } else if (remainingDuration > durationToNextStation && stationId == 19) {
            
            // the car has travelled outside of the system
            int newEventTime = time + durationToNextStation;
            
            TelecomSimulator.insertEvent(new CallTerminationEvent(newEventTime, stationId, newChannelId));
            
        } else {
            
            // No need to handover
            int newEventTime = time + remainingDuration;
            
            TelecomSimulator.insertEvent(new CallTerminationEvent(newEventTime, stationId, newChannelId));
            
        }
        
    }
    
}
