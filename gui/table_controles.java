/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parts.gui;
import parts.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.scene.control.TableView;
import javafx.collections.ObservableList;

import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
/**
 *
 * @author rohim
 */
public class table_controles {
    
    ConnectionToParts c = new ConnectionToParts();
    
  
    
    public void partstable (TableView<Customers_Parts_Edit> Customers_Parts){
   ObservableList<TableColumn<Customers_Parts_Edit,?>> list = Customers_Parts.getColumns();
   for(TableColumn c : list)
   {
       if(c.getId().equals("CustomerID")){
       c.setCellValueFactory(new PropertyValueFactory<Customers_Parts_Edit, String >("CustomerID"));
   }
        if(c.getId().equals("RedistrationNumber")){
        c.setCellValueFactory(new PropertyValueFactory<Customers_Parts_Edit, String >("RegistrationNo"));
  
   }
         if(c.getId().equals("PartsID")){
        c.setCellValueFactory(new PropertyValueFactory<Customers_Parts_Edit, String >("PartsID"));
  
   }
          if(c.getId().equals("BookingID")){
        c.setCellValueFactory(new PropertyValueFactory<Customers_Parts_Edit, String >("BookingID"));
  
   }
          
   }
   
        
        
    }
    
      public void parts_d_table (TableView<Parts_Table> Customers_Parts){
   ObservableList<TableColumn<Parts_Table,?>> list = Customers_Parts.getColumns();
   for(TableColumn c : list)
   {
       if(c.getId().equals("id")){
       c.setCellValueFactory(new PropertyValueFactory<Parts_Table, Integer >("ID"));
   }
        if(c.getId().equals("name")){
        c.setCellValueFactory(new PropertyValueFactory<Parts_Table, String >("name"));
  
   }
         if(c.getId().equals("description")){
        c.setCellValueFactory(new PropertyValueFactory<Parts_Table, String >("description"));
  
   }
          if(c.getId().equals("cost")){
        c.setCellValueFactory(new PropertyValueFactory<Parts_Table, String >("cost"));
  
   }
          if(c.getId().equals("qty")){
        c.setCellValueFactory(new PropertyValueFactory<Parts_Table, Integer >("QTY"));
        
  
   }
           if(c.getId().equals("make")){
        c.setCellValueFactory(new PropertyValueFactory<Parts_Table, Integer >("make"));
        
  
   }
            if(c.getId().equals("model")){
        c.setCellValueFactory(new PropertyValueFactory<Parts_Table, Integer >("model"));
        
  
   }
          
   }
      }
         public ObservableList<Parts_Table> getParts(TableView<Parts_Table> parts_table){
     Connection con = c.connect();
       
       
       ObservableList<Parts_Table> p =FXCollections.observableArrayList();
        try{
            String sql = "SELECT * FROM Parts";
        Statement stat = con.createStatement();
        
       ResultSet info = stat.executeQuery(sql);
        
     while(info.next()){
        
        
            // p.add(new Parts_Table(info.getInt(1),info.getString(2),info.getString(3),info.getString(4),info.getString(5),info.getInt(6),info.getString(7)));
        
            p.add(new Parts_Table(info.getInt("ID"),info.getString("Name"),info.getString("Model"),info.getString("Make"),info.getString("Description"),info.getInt("Quantity"),info.getString("Cost")));
        
        }
     parts_table.setItems(null);
         parts_table.setItems(p);
         con.close();
        }
        catch(SQLException e){
            
        }
       
        return p;
    }
        
    
}
