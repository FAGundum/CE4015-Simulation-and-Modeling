/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulationevents;

public class CallInitiationEvent extends Events{
    
    private final double speed;
    private final int station;
    private final double position;
    private final double duration;
    
    public CallInitiationEvent(double time, double speed, int station, double position, double duration) {
        
        super(time);
        this.speed = speed;
        this.station = station;
        this.position = position;
        this.duration = duration;
        
    }
    
    public double getSpeed() {
        
        return this.speed;
        
    }
    
    public int getStation() {
        
        return this.station;
        
    }
    
    public double getPosition() {
        
        return this.position;
        
    }
    
    public double getDuration() {
        
        return this.duration;
        
    }
    
}
