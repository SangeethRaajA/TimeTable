/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package workingdays;

/**
 *
 * @author asvin
 */
class User {
    private String semID,wDays,wTimePerDay,batch;
    private int dayNo;
    
    public User(String semID,int dayNo,String wDays, String wTimePerDay,String batch){
        this.semID = semID;
        
        this.dayNo = dayNo;
        this.wDays=wDays;
        this.wTimePerDay=wTimePerDay;
        this.batch=batch;
        
    }
    
    public String getsemID(){
        return semID;
    }
    
    
    
     public int getdayNo(){
        return dayNo;
    }
     
      public String getwDays(){
        return wDays;
    }
      
      public String getwTimePerDay(){
        return wTimePerDay;
    }
      
      public String getbatch(){
          return batch;
      }
      
     
      
    
}
