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
public class SessionDetails1 {
     private int id;
    private String tag, subject,session_id,subject_code,student_group,number_of_student,duration;
    
    
    public SessionDetails1(int id,String session_id, String tag, String subject,String subject_code,String student_group,String number_of_student,String duration){
        this.id = id;
        this.session_id = session_id;
        this.tag = tag;
        this.subject = subject;
        this.subject_code = subject_code;
        this.student_group = student_group;
        this.number_of_student = number_of_student;
        this.duration = duration;
    }
    
    public int getid(){
        return id;
    }
    
    public String getsession_id() {
        return session_id;
    }
    
    public String gettag() {
        return tag;
    }
    
     public String getsubject() {
        return subject;
    }
     
      public String getsubject_code() {
        return subject_code;
    }
      
       public String getstudent_group() {
        return student_group;
    }
       
       public String getnumber_of_student() {
        return number_of_student;
    }
       
        public String getduration() {
        return duration;
    }
        
      
}
