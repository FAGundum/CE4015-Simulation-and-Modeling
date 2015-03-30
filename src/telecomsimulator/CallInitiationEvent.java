/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package telecomsimulator;

public class CallInitiationEvent extends Event{
    
    private final double speed;
    private final int stationId;
    private final double position;
    private final int duration;
    
    public CallInitiationEvent(int time, double speed, int stationId, double position, int duration) {
        
        super(time);
        this.speed = speed;
        this.stationId = stationId;
        this.position = position;
        this.duration = duration;
        
    }
    
    public double getSpeed() {
        
        return this.speed;
        
    }
    
    public int getStationId() {
        
        return this.stationId;
        
    }
    
    public double getPosition() {
        
        return this.position;
        
    }
    
    public int getDuration() {
        
        return this.duration;
        
    }

    @Override
    public void handle() {
        
        // 1. Update system states
        int channelId = TelecomSimulator.tryReserve(stationId);
        
        // 2. Update statistical counters
        if (channelId == 0) {
            
            TelecomSimulator.recordBlockedCall();
            return;
            
        }
                
        // 3.1 Generate handover or departure events
        double distanceToNextStation = (stationId * 2 - position);
        int durationToNextStation = (int) Math.round(distanceToNextStation / speed * 3600);
        
        if (duration > durationToNextStation && stationId != 20) {
            
            // Need to handover
            int newEventTime = time + durationToNextStation;
            int newEventStationId = stationId + 1;
            int remainingDuration = duration - durationToNextStation;
            
            TelecomSimulator.insertEvent(new CallHandOverEvent(newEventTime, speed, newEventStationId, channelId, remainingDuration));
            
        } else if (duration > durationToNextStation && stationId == 20) {
            
            // the car has travelled outside of the system
            int newEventTime = time + durationToNextStation;
            
            TelecomSimulator.insertEvent(new CallTerminationEvent(newEventTime, stationId, channelId));
            
        } else {
            
            // No need to handover
            int newEventTime = time + duration;
            
            TelecomSimulator.insertEvent(new CallTerminationEvent(newEventTime, stationId, channelId));
            
        }
        
        // 3.2 generate new call
        int newEventTime = time + Utils.generateInterArrivalTime();
        double newSpeed = Utils.generateCarSpeed();
        int newStationId = Utils.generateStationId();
        double newPosition = Utils.generatePosition();
        int newDuration = Utils.generateDuration();
        
        TelecomSimulator.insertEvent(new CallInitiationEvent(newEventTime, newSpeed, newStationId, newPosition, newDuration));
        
    }
    
}
