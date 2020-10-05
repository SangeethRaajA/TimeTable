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
public class sub_loc1 {
      private int id;
    private String subject_name,tag_name, roomname;
    
    
    public sub_loc1(int id, String subject_name, String tag_name, String roomname){
        this.id = id;
        this.subject_name = subject_name;
        this.tag_name = tag_name;
        this.roomname = roomname;
    }
    
    public int getid(){
        return id;
    }
    
     public String getsubject_name() {
        return subject_name;
    }
    
    public String gettag_name() {
        return tag_name;
    }
    
    public String getroomname() {
        return roomname;
    }
   
}
