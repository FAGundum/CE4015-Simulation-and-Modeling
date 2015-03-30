/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulationevents;

public class CallHandOverEvent extends Events {
    
    private final double speed;
    private final int station;
    private final double remainingDuration;
    
    public CallHandOverEvent(double time, double speed, int station, double remainingDuration) {
        
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
    
    public double getRemainingDuration() {
        
        return this.remainingDuration;
        
    } 
    
}
