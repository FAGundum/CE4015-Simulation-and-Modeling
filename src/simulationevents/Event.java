/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulationevents;

public abstract class Event {
    
    protected int time;
    
    protected Event(int time) {
        
        this.time = time;
    
    }
    
    public int getTime() {
        
        return this.time;
        
    }
    
}
