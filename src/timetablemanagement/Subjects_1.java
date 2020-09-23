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
class Subjects_1 {
   
    private int id;
    private String subject_code;
    private String subject_name, lecture_hours, tutorial_hours, lab_hours, evaluation_hours;
    
    public Subjects_1(int id, String subject_code, String subject_name, String lecture_hours, String tutorial_hours , String lab_hours, String evaluation_hours){
        this.id = id;
        this.subject_code = subject_code;
        this.subject_name = subject_name;
        this.lecture_hours = lecture_hours;
        this.tutorial_hours = tutorial_hours;
        this.lab_hours = lab_hours;
        this.evaluation_hours = evaluation_hours;
    }
    
    public int getid(){
        return id;
    }
    
    public String getsubject_code(){
        return subject_code;
    }
    
    public String getsubject_name() {
        return subject_name;
    }
    
   
    public String getlecture_hours() {
        return lecture_hours;
    }
    
     public String gettutorial_hours() {
        return tutorial_hours;
    }
     
      public String getlab_hours() {
        return lab_hours;
    }
      
       public String getevaluation_hours() {
        return evaluation_hours;
    }
    
}
