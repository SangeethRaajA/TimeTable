/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SpecialRoomAllocation;

/**
 *
 * @author User
 */
public class tag_location1 {
     private int id;
    private String tag, room;
    
    
    public tag_location1(int id, String tag, String room){
        this.id = id;
        this.tag = tag;
        this.room = room;
    }
    
    public int getid(){
        return id;
    }
    
    public String gettag() {
        return tag;
    }
    
    public String getroom() {
        return room;
    }
   
}
