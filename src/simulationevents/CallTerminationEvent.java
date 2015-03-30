/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulationevents;

public class CallTerminationEvent extends Events {
    
    private final int station;
    
    public CallTerminationEvent(int time, int station) {
        
        super(time);
        this.station = station;
        
    }
    
    public int getStation() {
        
        return this.station;
        
    }
    
}
