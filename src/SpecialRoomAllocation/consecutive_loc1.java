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
public class consecutive_loc1 {
      private int ConID;
    private String subject_code,lecture, tutorial,lab,room;
    
    
    public consecutive_loc1(int ConID, String subject_code, String lecture,String tutorial,String lab, String room){
        this.ConID = ConID;
        this.subject_code = subject_code;
        this.lecture = lecture;
        this.tutorial = tutorial;
        this.lab = lab;
        this.room = room;
    }
    
    public int getConID(){
        return ConID;
    }
    
     public String getsubject_code() {
        return subject_code;
    }
    
    public String getlecture() {
        return lecture;
    }
    
    public String gettutorial() {
        return tutorial;
    }
    
    public String getlab() {
        return lab;
    }
    
    public String getroom() {
        return room;
    }
    
}
