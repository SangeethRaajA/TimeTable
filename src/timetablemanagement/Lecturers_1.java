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
class Lecturers_1 {
    private int id;
    private String name, emp_id;
   
    
    public Lecturers_1(int id, String name, String emp_id){
        this.id = id;
        this.name = name;
        this.emp_id = emp_id;
    }
    
    public int getid(){
        return id;
    }
    
    public String getname() {
        return name;
    }
    
   
    public String getemp_id() {
        return emp_id;
    }
    
   
}
