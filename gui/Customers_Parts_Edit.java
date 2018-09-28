
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parts.gui;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author rohim
 */
public class Customers_Parts_Edit {
   
   StringProperty RegistrationNo;
   IntegerProperty PartsID;
   IntegerProperty BookingID;
   StringProperty expire;
   StringProperty installed;
   public  Customers_Parts_Edit( String regNo, Integer pID, Integer bID){
      
      this. RegistrationNo=new SimpleStringProperty(regNo);
      this.PartsID=new SimpleIntegerProperty(pID);
      this.BookingID=new SimpleIntegerProperty(bID); 
 }
 public  Customers_Parts_Edit( String regNo, Integer pID, Integer bID, String exp, String in){
      
     this. RegistrationNo=new SimpleStringProperty(regNo);
      this.PartsID=new SimpleIntegerProperty(pID);
      this.BookingID=new SimpleIntegerProperty(bID); 
     this.expire= new SimpleStringProperty(exp);
      this.installed=new SimpleStringProperty(in);
      
 }
  
    public String getRegistrationNo(){
       return RegistrationNo.get();
   }
    
     public Integer getPartsID(){
       return PartsID.get();
   }
     
      public Integer getBookingID(){
       return BookingID.get();
   }
         public String getExpire(){
       return expire.get();
   }
     
      public String getInstalled(){
       return installed.get();
   }
      
    
        public void setRegistrationNo(String id){
       this.RegistrationNo.set(id);
   }
         public void setPartsID(Integer id){
          this.PartsID.set(id);
   }
          public void setBookingID(Integer id){
      this. BookingID.set(id);
   }
              public void setExpire(String id){
          this.expire.set(id);
   }
          public void setInstalled(String id){
       this.installed.set(id);
   }
   
              public StringProperty getRegistrationNoo(){
       return RegistrationNo;
   }
    
     public IntegerProperty getPartsIDd(){
       return PartsID;
   }
     
      public IntegerProperty getBookingIDd(){
       return BookingID;
   }
         public StringProperty getexpiree(){
       return expire;
   }
     
      public StringProperty getinstalledd(){
       return installed;
   }
      
        
 }
