/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulationevents;

public class CallInitiationEvent extends Event{
    
    private final double speed;
    private final int station;
    private final double position;
    private final int duration;
    
    public CallInitiationEvent(int time, double speed, int station, double position, int duration) {
        
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
    
    public int getDuration() {
        
        return this.duration;
        
    }
    
}
