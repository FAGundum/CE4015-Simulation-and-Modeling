/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package telecomsimulator;

/**
 *
 * @author Derek
 */
public class Channel {
    
    private final int id;
    private boolean reservation;
    
    public Channel(int id) {
        
        this.id = id;
        this.reservation = false;
        
    }
    
    public void reserve() {
        
        this.reservation  = true;
        
    }
    
    public void release() {
        
        this.reservation = false;
        
    }
    
    public boolean isReserved() {
        
        return this.reservation == true;
        
    }
    
    public String debugMsg() {
        
        String msg = (this.reservation)?"reserved!":"free!";
        
        return "Channel " + this.id + " is currently " + msg;
        
    }
    
}
