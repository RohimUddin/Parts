
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parts;

import java.util.ArrayList;

/**
 *
 * @author rohim
 */

public class Parts {
    String name;
    String Description;
    String model;
    String make;
    int ID;
    String cost;
    int Qty;
    
   public Parts(   String n , String D, String mo,String ma, int I,String c, int Q){
        name=n;
   Description=D;
    model=mo;
    make=ma;
    ID=I;
    cost=c;
    Qty=Q;
   }
           
    
    public Parts(int pid){
    ID = pid;
    }
    public String Description(){
        return Description;
    }
     public String Cost(){
        return cost;
    }
     
     public String name(){
         return name;
     }
     
     public int qty(){
         return Qty;
     }
    public void addStock(int newqty){
        ConnectionToParts c = new ConnectionToParts();
        
        c.add(ID,newqty);
          //  qty= qty+newqty;
    }
    
    public void partWithdraw(){
       //qty=qty-1; 
    }

    
    public Parts SeachByID(){
        ConnectionToParts con = new ConnectionToParts();
        Parts p = con.IDSearch(ID);
        
        return p;
    }

 }
