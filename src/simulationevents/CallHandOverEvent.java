/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulationevents;

public class CallHandOverEvent extends Event {
    
    private final double speed;
    private final int station;
    private final int remainingDuration;
    
    public CallHandOverEvent(int time, double speed, int station, int remainingDuration) {
        
        super(time);
        this.speed = speed;
        this.station = station;
        this.remainingDuration = remainingDuration;
        
    }
    
    public double getSpeed() {
        
        return this.speed;
        
    }
    
    public int getStation() {
        
        return this.station;
        
    }
    
    public int getRemainingDuration() {
        
        return this.remainingDuration;
        
    } 
    
}
