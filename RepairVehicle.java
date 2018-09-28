
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parts;
import java.util.*;
/**
 *
 * @author rohim
 */
public class RepairVehicle {
    ArrayList<ReplacementPart> replacementParts;
    int cost= 0;
    
    public RepairVehicle(ArrayList <ReplacementPart> replace){
        replacementParts=replace;
        //calculateTotalCost();
    }
    
    void calculateTotalCost(){
        for(int i = 0 ; i <= replacementParts.size() ; i++){
           // cost=cost+replacementParts.get(i).cost;
        }
            
    }
    
    void addPart(ReplacementPart newpart){
        replacementParts.add(newpart);
        calculateTotalCost();
    }
    
    void removePartByID(int checkID){
        for(int i =0 ; i <=replacementParts.size() ; i++){
            if(replacementParts.get(i).ID==checkID){
                replacementParts.remove(i);
                break;
            }
        }
    }
    
    void removePartByName(String checkName){
         for(int i =0 ; i <=replacementParts.size() ; i++){
          //  if(replacementParts.get(i).name.equals(checkName)){
                replacementParts.remove(i);
                break;
            }
        }
    }

    

