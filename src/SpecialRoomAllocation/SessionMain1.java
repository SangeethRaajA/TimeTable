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
public class SessionMain1 {
     private int id;
    private String sessionID,lectureName;
    
    
    public SessionMain1(int id,String sessionID, String lectureName){
        this.id = id;
        this.sessionID = sessionID;
        this.lectureName = lectureName;
    }
    
    public int getid(){
        return id;
    }
    
    public String getsessionID() {
        return sessionID;
    }
    
    public String getlectureName() {
        return lectureName;
    }
    
     
}
