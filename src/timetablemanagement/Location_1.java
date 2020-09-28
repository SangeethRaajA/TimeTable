/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timetablemanagement;

/**
 *
 * @author Proitzen
 */
class Location_1 {
    private int locationid;
    private String buildingname, roomname, roomtype;
    private int floor;
    private String nonavailabletime;
    
    public Location_1(int locationid, String buildingname, int floor, String roomname, String roomtype, String nonavailabletime){
        this.locationid = locationid;
        this.buildingname = buildingname;
        this.floor = floor;
        this.roomname = roomname;
        this.roomtype = roomtype;
        this.nonavailabletime = nonavailabletime;
    }
    
    public int getlocationid(){
        return locationid;
    }
    
    public String getbuildingname() {
        return buildingname;
    }
    
     public int getfloor(){
        return floor;
    }
    
    public String getroomname() {
        return roomname;
    }
    
    public String getroomtype() {
        return roomtype;
    }
    
    public String getnonavailabletime() {
        return nonavailabletime;
    }
}
