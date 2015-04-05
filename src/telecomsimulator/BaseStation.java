/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package telecomsimulator;

public class BaseStation {
    
    
    private final int id;
    private final int numberOfReservedChannels;
    private int freeChannels;
    
    public BaseStation(int id, int numberOfReservedChannels) {
        
        this.id = id;
        this.numberOfReservedChannels = numberOfReservedChannels;
        this.freeChannels = 10;
        
    }
    
    public int getId() {
        
        return this.id;
        
    }
    
    public int getNumberOfReservedChannels() {
        
        return this.numberOfReservedChannels;
        
    }
    
    public int getNumberOfFreeChannel() {
        
        return this.freeChannels;
        
    }

    /**
     * The function check if the channels of base station are all reserved.
     * 
     * @param e e parameter is used to identify which event is trying to reserve station channel
     * @return Return TRUE indicating all channels are reserved, Return FALSE indicating free channel is available
     * 
     */
    public boolean isFull(Event e) {
        
        if (this.freeChannels - this.numberOfReservedChannels > 0) {
            
            return false;
            
        } else if (e instanceof CallHandOverEvent && this.freeChannels > 0) {
            
            return false;
            
        } else {
            
            return true;
            
        }
        
    }
    
    public void reserveChannel() {
        
        this.freeChannels --;
        
    }
    
    public void releaseChannel() {
        
        this.freeChannels ++;
        
    }
    
    public String debugMsg() {
        
        String msg = "Base Station " + this.id + " has " + this.freeChannels + " available! \n ";
        
        return msg;
        
    }
    
}
