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
public class subgroup_loc1 {
    private int id;
    private String subgroup,building, room;
    
    
    public subgroup_loc1(int id, String subgroup, String building, String room){
        this.id = id;
        this.subgroup = subgroup;
        this.building = building;
        this.room = room;
    }
    
    public int getid(){
        return id;
    }
    
     public String getsubgroup() {
        return subgroup;
    }
    
    public String getbuilding() {
        return building;
    }
    
    public String getroom() {
        return room;
    }
}
