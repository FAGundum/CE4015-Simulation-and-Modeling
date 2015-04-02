/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package telecomsimulator;

import java.util.ArrayList;

/**
 *
 * @author Derek
 */
public class BaseStation {
    
    
    private final int id;
    private final int numberOfReservedChannels;
    private ArrayList<Channel> channels;
    
    public BaseStation(int id, int numberOfReservedChannels) {
        
        this.id = id;
        this.numberOfReservedChannels = numberOfReservedChannels;
        initializeChannel();        
        
    }
    
    public int getId() {
        
        return this.id;
        
    }
    
    public int getNumberOfReservedChannels() {
        
        return this.numberOfReservedChannels;
        
    }

    /**
     * The function check if the channels of base station are all reserved.
     * 
     * @param e e parameter is used to identify which event is trying to reserve station channel
     * @return Return -1 indicating all channels are reserved, Return 0-9 indicating this particular channel is not reserved
     * 
     */
    public int isFull(Event e) {
        
        for (int i = 0; i < 10 - this.numberOfReservedChannels; i++) {
            
            if (!this.channels.get(i).isReserved()) {
                return i;
            }
            
        }
        
        if (e instanceof CallHandOverEvent) {
            
            for (int i = 9; i >= 10 - this.numberOfReservedChannels; i--) {
                
                if (!this.channels.get(i).isReserved()) {
                    return i;
                }
                
            }
            
        }
        
        return -1;
        
    }
    
    public void reserveChannel(int i) {
        
        this.channels.get(i).reserve();
        
    }
    
    public void releaseChannel(int i) {
        
        this.channels.get(i).release();
        
    }
    
    public String debugMsg() {
        
        String msg = "Status of Base Station " + this.id + " is shown below: \n ";
        
        for (int i = 0; i < 10; i++) {
            
            msg += this.channels.get(i).debugMsg();
            
        }
        
        return msg;
        
    }
    
    private void initializeChannel() {
        
        this.channels = new ArrayList<>();
        
        // Initialize 10 free channels
        for (int i = 0; i < 10; i++) {
            
            this.channels.add(i, new Channel(i));
            
        }
        
    }
    
    
}
