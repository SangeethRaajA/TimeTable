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
public class group_loc1 {
      private int id;
    private String grouppp,building, room;
    
    
    public group_loc1(int id, String grouppp, String building, String room){
        this.id = id;
        this.grouppp = grouppp;
        this.building = building;
        this.room = room;
    }
    
    public int getid(){
        return id;
    }
    
     public String getgrouppp() {
        return grouppp;
    }
    
    public String getbuilding() {
        return building;
    }
    
    public String getroom() {
        return room;
    }
}
