/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulationevents;

public abstract class Events {
    
    protected double time;
    
    protected Events(double time) {
        
        this.time = time;
    
    }
    
    public double getTime() {
        
        return this.time;
        
    }
    
}
