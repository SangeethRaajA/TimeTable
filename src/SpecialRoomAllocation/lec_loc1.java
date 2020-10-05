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
public class lec_loc1 {
     private int id;
    private String lecture,building, room;
    
    
    public lec_loc1(int id, String lecture, String building, String room){
        this.id = id;
        this.lecture = lecture;
        this.building = building;
        this.room = room;
    }
    
    public int getid(){
        return id;
    }
    
     public String getlecture() {
        return lecture;
    }
    
    public String getbuilding() {
        return building;
    }
    
    public String getroom() {
        return room;
    }
}
