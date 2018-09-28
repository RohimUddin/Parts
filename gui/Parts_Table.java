
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
public class Parts_Table {
    IntegerProperty id;
    StringProperty name;
    StringProperty model;
    StringProperty make;
    StringProperty description;
    IntegerProperty qty;
    StringProperty cost;
    public  Parts_Table(int i, String n, String mo, String ma, String d, int q, String c){
 this.id = new SimpleIntegerProperty(i);
    this.name=new SimpleStringProperty(n);
    this.model=new SimpleStringProperty(mo);
    this.make=new SimpleStringProperty(ma);
    this.description=new SimpleStringProperty(d);
   this.qty= new SimpleIntegerProperty(q);
    this.cost=new SimpleStringProperty(c);
    }
    
    public void setID(Integer i){
        id.set(i);
    }
      public void setName(String i){
        name.set(i);
    }
       public void setModel(String i){
        model.set(i);
    }
        public void setMake(String i){
        make.set(i);
    }
    
         public void setDescription(String i){
        description.set(i);
    }
          public void setQTY(int i){
        qty.set(i);
    }
           public void setCost(String i){
    cost.set(i);
        }
            public IntegerProperty getIDd(){
        return id;
    }
      public StringProperty getNamee(){
        return name;
    }
       public StringProperty getModell(){
        return model;
    }
        public StringProperty getMakee(){
        return make;
    }
    
         public StringProperty getDescriptionn(){
        return description;
    }
          public IntegerProperty getQTYy(){
        return qty;
    }
           public StringProperty getCostt(){
    return cost;
        }
                 public Integer getID(){
        return id.get();
    }
      public String getName(){
        return name.get();
    }
       public String getModel(){
        return model.get();
    }
        public String getMake(){
        return make.get();
    }
    
         public String getDescription(){
        return description.get();
    }
          public Integer getQTY(){
        return qty.get();
    }
           public String getCost(){
    return cost.get();
        }
}
