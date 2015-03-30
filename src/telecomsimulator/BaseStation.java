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
    private ArrayList<Channel> channels;
    
    public BaseStation(int id) {
        
        this.id = id;
        initializeChannel();        
        
    }
    
    public int getId() {
        
        return this.id;
        
    }
    
    // 

    /**
     * The function check if the channels of base station are all reserved.
     * 
     * @return Return 0 indicating all channels are reserved, Return 1-10 indicating this particular channel is not reserved
     * 
     */
        public int isFull() {
        
        for (int i = 1; i <= 10; i++) {
            
            if (!this.channels.get(i).isReserved()) {
                return i;
            }
            
        }
        
        return 0;
        
    }
    
    public void reserveChannel(int i) {
        
        this.channels.get(i).reserve();
        
    }
    
    public void releaseChannel(int i) {
        
        this.channels.get(i).release();
        
    }
    
    public String debugMsg() {
        
        String msg = "Status of Base Station " + this.id + " is shown below: \n ";
        
        for (int i = 1; i <= 10; i++) {
            
            msg += this.channels.get(i).debugMsg();
            
        }
        
        return msg;
        
    }
    
    private void initializeChannel() {
        
        this.channels = new ArrayList<>();
        
        // Initialize 10 free channels
        for (int i = 1; i <= 10; i++) {
            
            this.channels.add(i, new Channel(i));
            
        }
        
    }
    
}
