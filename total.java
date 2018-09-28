/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parts;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author rohim
 */
public class total {
    ConnectionToParts conn;
    Connection con;
    
         public double calculateTotal(String reg, int booking){
         conn = new ConnectionToParts();
        con = conn.connect();
        double total = 0;
        
        try 
        {
       String h = "SELECT * FROM PartsUsed WHERE RegistrationNumber = ? AND BookingID = ?";
            PreparedStatement ko = con.prepareStatement(h);
          ko.setString(1,reg);
          ko.setInt(2, booking);
            ResultSet info = ko.executeQuery();
            while(info.next())
            {
                String part = "SELECT * FROM Parts WHERE ID = ?";
                PreparedStatement partko = con.prepareStatement(part);
         partko.setInt(1,info.getInt(2));
            ResultSet costinfo = partko.executeQuery();
                  total=total+costinfo.getDouble(7);
            }
            con.close();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        
        
        
        return total;
    }
    
   
    
}
