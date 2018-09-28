/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parts.gui;

import parts.*;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.TableView;
import javafx.collections.ObservableList;
import javafx.scene.control.ChoiceBox;

import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author rohim
 */
public class PartsEditController implements Initializable {

    @FXML
    private CheckBox Search_ID_CheckBox;
    @FXML
    private Label Search_ID;
    @FXML
    private TextField txt_Search_By_ID;
    @FXML
    private Button Search_By_ID_btn;
    @FXML
    private Button Search_Firstname;
    @FXML
    private Label lbl_First_search;
    @FXML
    private Label lbl_Surename_search;
    @FXML
    private TextField txt_First_search;
    @FXML
    private TextField txt_Surename_search;
    @FXML
    private Label lbl_firstname_display;
    @FXML
    private Label lbl_surname_display;
    @FXML
    private Label lbl_RN_display;
    @FXML
    private Label lbl_Make_display;
    @FXML
    private Label lbl_colour_display;
    @FXML
    private Label txt_firstname_display;
    @FXML
    private Label txt_Make_display;
    @FXML
    private Label lbl_model_display;
    @FXML
    private Label txt_colour_display;
    @FXML
    private Label txt_Surname_display;
    @FXML
    private Button btn_delete_By_Part_ID;
    @FXML
    private Button Add_Parts_By_ID;
    @FXML
    private Button Clear;
    @FXML
    private TextField Search_ID_txt;
    @FXML
    private TextField Delete_ID_Part;
    @FXML
    private TextField Add_ID_Part;
    @FXML
    private Label txt_model_display;
    @FXML
    private Label txt_ID_Name;
    @FXML
    private Label txt_ID_Cost;
    @FXML
    private Button Search_Parts_Using_ID;
    @FXML
    private TableColumn<Customers_Parts_Edit, String> Reg_no;
    @FXML
    private TableColumn<Customers_Parts_Edit, Integer> Part_ID;
    @FXML
    private TableColumn<Customers_Parts_Edit, Integer> Booking_ID;
    @FXML
    private TableView<Customers_Parts_Edit> Customers_Parts_Editt;

    private static ObservableList<Customers_Parts_Edit> l;
    private ObservableList<Customers_Parts_Edit> data;

    ConnectionToParts conn;
    Connection con;
    @FXML
    private ChoiceBox<String> regNo;
    int bookingID = 0;
    @FXML
    private ChoiceBox<String> Booking_Dates;
    @FXML
    private Label lbl_type_of_booking;
    @FXML
    private ChoiceBox<String> BookingIDchouce;
    @FXML
    private Button bookedparts_btn;
    @FXML
    private Label totalCost;

    /**
     * Initializes the controller class.
     *
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        conn = new ConnectionToParts();
    }

    @FXML
    private void Search_ID_CheckBox(ActionEvent event) {
        if (Search_ID_CheckBox.isSelected()) {
            id_Search(false, true);
        } else {
            id_Search(true, false);
        }

    }

    @FXML
    private void Search_ID(ActionEvent event) {

        if (Search_ID_CheckBox.isSelected()) {

            if (txt_Search_By_ID.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "You must enter a registration number or a partial of registration number");
                txt_Search_By_ID.setText("");
            } else {
                bookedparts_btn.setDisable(false);
                ID_Search();
            }
        } else {
            if (txt_Surename_search.getText().equals("") || txt_First_search.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "You must insert a Firstname AND Surname");
                txt_Surename_search.setText("");
                txt_First_search.setText("");
            } else {
                bookedparts_btn.setDisable(false);
                name_search();
            }
        }

    }

    public void id_Search(boolean t, boolean f) {
        Search_ID.setVisible(f);
        txt_Search_By_ID.setVisible(f);
        Search_By_ID_btn.setVisible(f);
        Search_Firstname.setVisible(t);
        lbl_First_search.setVisible(t);
        lbl_Surename_search.setVisible(t);
        txt_First_search.setVisible(t);
        txt_Surename_search.setVisible(t);

    }

    @FXML
    private void DeletePartByID(ActionEvent event) {

        try {

            int a = Integer.parseInt(Delete_ID_Part.getText());

            if (!CheckPartsID(Integer.parseInt(Delete_ID_Part.getText()))) {
                JOptionPane.showMessageDialog(null, "Part with the ID " + Delete_ID_Part.getText() + " has not been used");
                Delete_ID_Part.setText("");

            } else {

                String sql = "DELETE FROM PartsUsed WHERE PartsID=? AND RegistrationNumber = ? AND BookingID = ?";
                try {
                    con = conn.connect();
                    PreparedStatement stat = con.prepareStatement(sql);
                    stat.setInt(1, Integer.parseInt(Delete_ID_Part.getText()));
                    stat.setString(2, regNo.getValue());
                    stat.setString(3, BookingIDchouce.getValue());
                    stat.execute();
                    String partss = "SELECT * FROM Parts WHERE ID = ?";
                    PreparedStatement partsdh = con.prepareStatement(partss);
                    partsdh.setInt(1, Integer.parseInt(Delete_ID_Part.getText()));
                    ResultSet p = partsdh.executeQuery();
                    updatetotal(false, Integer.parseInt(BookingIDchouce.getValue()), regNo.getValue(), Double.parseDouble(p.getString(7)));

                    String h = "SELECT * FROM PartsUsed WHERE RegistrationNumber = ?";
                    PreparedStatement ko = con.prepareStatement(h);
                    ko.setString(1, regNo.getValue());
                    ResultSet info = ko.executeQuery();
                    data = FXCollections.observableArrayList();
                    Part_ID.setCellValueFactory(new PropertyValueFactory("PartsID"));

                    Booking_ID.setCellValueFactory(new PropertyValueFactory("BookingID"));

                    Reg_no.setCellValueFactory(new PropertyValueFactory("RegistrationNo"));

                    while (info.next()) {
                        System.out.println(info.getString(5));
                        data.add(new Customers_Parts_Edit(info.getString(1), info.getInt(2), info.getInt(3)));

                    }

                    Customers_Parts_Editt.setItems(null);
                    Customers_Parts_Editt.setItems(data);
                    String parts = "SELECT * FROM Parts WHERE ID = ?";
                    PreparedStatement partsd = con.prepareStatement(parts);
                    partsd.setInt(1, Integer.parseInt(Delete_ID_Part.getText()));
                    ResultSet infos = partsd.executeQuery();
                    String sqlupdate = "UPDATE Parts SET Quantity = ? WHERE ID = ?  ";
                    PreparedStatement state = con.prepareStatement(sqlupdate);
                    state.setInt(1, infos.getInt(6) + 1);
                    state.setInt(2, Integer.parseInt(Delete_ID_Part.getText()));
                    state.executeUpdate();
                    JOptionPane.showMessageDialog(null, "You have deleted the part with the ID " + Delete_ID_Part.getText() + " called " + infos.getString(2) + ".");
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Delete unsuccessfull try again");
                }

                Delete_ID_Part.setText("");
                Add_ID_Part.setText("");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "You must enter a number when Deleting a part");
            Delete_ID_Part.setText("");
        }
    }

    @FXML
    private void Add_Parts_By_ID(ActionEvent event) {

        try {
            int a = Integer.parseInt(Add_ID_Part.getText());
            if (CheckIdStock(Integer.parseInt(Add_ID_Part.getText())) && withdrawPart(Integer.parseInt(Add_ID_Part.getText()))) {
                con = conn.connect();
                ResultSet costdetail = null;
                String sql = "INSERT INTO PartsUsed (RegistrationNumber, PartsID, BookingID) VALUES(?,?,?)";
                try {
                    PreparedStatement stat = con.prepareStatement(sql);
                    stat.setString(1, regNo.getValue());
                    stat.setInt(2, Integer.parseInt(Add_ID_Part.getText()));
                    stat.setInt(3, Integer.parseInt(BookingIDchouce.getValue()));
                    stat.execute();

                    String h = "SELECT * FROM PartsUsed WHERE RegistrationNumber = ?";
                    PreparedStatement ko = con.prepareStatement(h);
                    ko.setString(1, regNo.getValue());
                    ResultSet info = ko.executeQuery();

                    data = FXCollections.observableArrayList();
                    Part_ID.setCellValueFactory(new PropertyValueFactory("PartsID"));

                    Booking_ID.setCellValueFactory(new PropertyValueFactory("BookingID"));

                    Reg_no.setCellValueFactory(new PropertyValueFactory("RegistrationNo"));

                    while (info.next()) {
                        System.out.println(info.getString(5));
                        data.add(new Customers_Parts_Edit(info.getString(1), info.getInt(2), info.getInt(3)));

                    }

                    Customers_Parts_Editt.setItems(null);
                    Customers_Parts_Editt.setItems(data);

                    String parts = "SELECT * FROM Parts WHERE ID = ?";
                    PreparedStatement partsd = con.prepareStatement(parts);
                    partsd.setInt(1, Integer.parseInt(Add_ID_Part.getText()));
                    costdetail = partsd.executeQuery();
                    JOptionPane.showMessageDialog(null, "You have Added the part with the ID " + Add_ID_Part.getText() + " called " + costdetail.getString(2) + ".");

                    updatetotal(true, Integer.parseInt(BookingIDchouce.getValue()), regNo.getValue(), Double.parseDouble(costdetail.getString(7)));
                    Delete_ID_Part.setText("");
                    Add_ID_Part.setText("");
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Add unsuccessfull");

                }
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "You must enter a number when adding a part");
            Add_ID_Part.setText("");
            return;
        }

    }

    public void updatetotal(boolean edit, int bookingID, String reg, double cost) {
        double price = 0;
        String booking = "SELECT * FROM Booking WHERE BookingID = ? AND RegistrationNumber = ? ";
        try {
            PreparedStatement book = con.prepareStatement(booking);
            book.setInt(1, bookingID);
            book.setString(2, reg);

            ResultSet bookinginfo = book.executeQuery();
            if (edit) {
                price = bookinginfo.getDouble("Bill") + cost;
            } else {
                price = bookinginfo.getDouble(9) - cost;
            }
            String sql = "UPDATE Booking SET Bill = ? WHERE BookingID = ? AND RegistrationNumber = ? ";
            PreparedStatement state = con.prepareStatement(sql);
            state.setDouble(1, price);
            state.setInt(2, bookingID);
            state.setString(3, reg);
            state.executeUpdate();
            totalCost.setText("£" + price + " for booking with ID " + bookingID);
        } catch (SQLException e) {
            System.out.println("fail");
        }
    }

    @FXML
    private void Clear(ActionEvent event) {
        Search_ID_txt.clear();
        Delete_ID_Part.clear();
        Add_ID_Part.clear();
        txt_First_search.clear();
        txt_Surename_search.clear();
        txt_Search_By_ID.clear();
        txt_ID_Name.setText("");
        txt_ID_Cost.setText("");
        txt_model_display.setText("");
        txt_colour_display.setText("");
        txt_Surname_display.setText("");
        txt_firstname_display.setText("");
        txt_Make_display.setText("");
        regNo.getItems().clear();
        Customers_Parts_Editt.setItems(null);
        Booking_Dates.getItems().clear();
        lbl_type_of_booking.setText("");
        BookingIDchouce.getItems().clear();
        totalCost.setText("");
    }

    @FXML
    private void Search_ID_Parts(ActionEvent event) {
        Parts p = new Parts(Integer.parseInt(Search_ID_txt.getText()));
        Parts newP = p.SeachByID();

        txt_ID_Name.setText(newP.name());
        txt_ID_Name.setStyle("-fx-background-color:White");

        txt_ID_Cost.setText("£" + newP.Cost());
        txt_ID_Cost.setStyle("-fx-background-color:White");
    }

    private void ID_Search() {

        ResultSet in = null;
        ResultSet info = null;
        ResultSet cu = null;
        int i = 0;
        regNo.getItems().clear();
        String sqlRegno = "SELECT * FROM Vehicles WHERE RegistrationNumber LIKE '%" + txt_Search_By_ID.getText() + "%' ";
        //   String sql = "SELECT * FROM PartsUsed WHERE RegistrationNumber = ? ";
        String cust = "SELECT * FROM Customer_Accounts WHERE ID = ?";
        con = conn.connect();
        try {

            PreparedStatement s = con.prepareStatement(sqlRegno);

            in = s.executeQuery();
            boolean t = true;
            //regNo.getItems().clear();
            while (in.next()) {
                regNo.getItems().add(in.getString(9));
                if (t) {
                    regNo.setValue(in.getString(9));
                    t = false;
                }
            }

            con.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Customer with the Registration number " + txt_Search_By_ID.getText() + " does not exist");
            txt_Search_By_ID.setText("");
        }
    }

    public void hi() {
        ResultSet info = null;
        ResultSet cu = null;
        if (booking()) {
            String sql = "SELECT * FROM PartsUsed WHERE RegistrationNumber = ? ";

            try {
                con = conn.connect();
                PreparedStatement stat = con.prepareStatement(sql);

                stat.setString(1, regNo.getValue());

                info = stat.executeQuery();

                data = FXCollections.observableArrayList();
                Part_ID.setCellValueFactory(new PropertyValueFactory("PartsID"));

                Booking_ID.setCellValueFactory(new PropertyValueFactory("BookingID"));

                Reg_no.setCellValueFactory(new PropertyValueFactory("RegistrationNo"));

                while (info.next()) {
                    data.add(new Customers_Parts_Edit(info.getString(1), info.getInt(2), info.getInt(3)));

                }

                Customers_Parts_Editt.setItems(null);
                Customers_Parts_Editt.setItems(data);

                String sqlRegno = "SELECT * FROM Vehicles WHERE RegistrationNumber = '" + regNo.getValue() + "' ";

                PreparedStatement s = con.prepareStatement(sqlRegno);

                ResultSet in = s.executeQuery();
                txt_Make_display.setText(in.getString(2));
                txt_Make_display.setStyle("-fx-background-color:White");
                txt_model_display.setText(in.getString(3));
                txt_model_display.setStyle("-fx-background-color:White");
                txt_colour_display.setText(in.getString(8));
                txt_colour_display.setStyle("-fx-background-color:White");

                String cust = "SELECT * FROM Customer_Accounts WHERE ID = ?";

                PreparedStatement a = con.prepareStatement(cust);

                a.setInt(1, in.getInt(10));

                cu = a.executeQuery();
                txt_firstname_display.setText(cu.getString(2));
                txt_firstname_display.setStyle("-fx-background-color:White");
                txt_Surname_display.setText(cu.getString(3));
                txt_Surname_display.setStyle("-fx-background-color:White");
                BookingIDchouce.getItems().clear();
                String booking = "SELECT * FROM Booking WHERE RegistrationNumber = ?";

                PreparedStatement bookingsinfo = con.prepareStatement(booking);

                bookingsinfo.setString(1, regNo.getValue());

                ResultSet book = bookingsinfo.executeQuery();

                boolean b = true;
                while (book.next()) {

                    BookingIDchouce.getItems().add(Integer.toString(book.getInt("BookingID")));
                    if (b) {
                        BookingIDchouce.setValue(Integer.toString(book.getInt("BookingID")));
                        b = false;
                    }
                }

                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Doesn't work");
            }
        } else {
            JOptionPane.showMessageDialog(null, txt_firstname_display.getText() + " " + txt_Surname_display.getText() + " has not made a booing with the vehicle containing the registration number " + regNo.getValue() + " yet");
        }
    }

    private void name_search() {

        ResultSet in = null;
        ResultSet info = null;
        ResultSet cu = null;
        int i = 0;
        regNo.getItems().clear();
        String sqlRegno = "SELECT * FROM Vehicles WHERE CustomerID = ? ";
        //String sql = "SELECT * FROM PartsUsed WHERE RegistrationNumber = ? ";
        String cust = "SELECT * FROM Customer_Accounts WHERE Firstname = ? AND Surname = ?";
        con = conn.connect();
        try {
            PreparedStatement s = con.prepareStatement(cust);
            s.setString(1, txt_First_search.getText());
            s.setString(2, txt_Surename_search.getText());
            in = s.executeQuery();
            PreparedStatement w = con.prepareStatement(sqlRegno);
            w.setInt(1, in.getInt(1));
            info = w.executeQuery();

            boolean t = true;
            //regNo.getItems().clear();
            while (info.next()) {
                regNo.getItems().add(info.getString(9));
                if (t) {
                    regNo.setValue(info.getString(9));
                    t = false;
                }
            }

            con.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Customer with the name " + txt_First_search.getText() + " " + txt_Surename_search.getText() + " does not exist");
            txt_First_search.setText("");
            txt_Surename_search.setText("");

        }

    }

    public boolean CheckPartsID(int PartID) {
        try {
            con = conn.connect();
            String h = "SELECT * FROM PartsUsed WHERE RegistrationNumber = ? And PartsID = ? AND BookingID = ?";
            PreparedStatement ko = con.prepareStatement(h);
            System.out.println(regNo.getValue());
            System.out.println(PartID);
            System.out.println(BookingIDchouce.getValue());
            ko.setString(1, regNo.getValue());
            ko.setInt(2, PartID);
            ko.setInt(3, Integer.parseInt(BookingIDchouce.getValue()));
            ResultSet info = ko.executeQuery();
            if (info.getInt(2) == PartID) {
                con.close();
                return true;
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        return false;
    }

    public boolean CheckIdStock(int partID) {
        con = conn.connect();

        try {
            String h = "SELECT * FROM Parts WHERE ID = ?";
            PreparedStatement ko = con.prepareStatement(h);
            ko.setInt(1, partID);
            ResultSet info = ko.executeQuery();
            while (info.next()) {
                if (info.getInt(1) == partID && info.getInt(6) > 0) {
                    con.close();

                    return true;
                }
            }
            con.close();
            JOptionPane.showMessageDialog(null, "Part with the ID " + Add_ID_Part.getText() + " does not exist");
            Add_ID_Part.setText("");
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Part with the ID " + Add_ID_Part.getText() + " does not exist");
            Add_ID_Part.setText("");
            return false;
        }
    }

    public boolean withdrawPart(int partID) {
        String h = "SELECT * FROM Parts WHERE ID = ?";
        String sql = "UPDATE Parts SET Quantity = ? WHERE ID = ?  ";
        con = conn.connect();
        try {

            PreparedStatement ko = con.prepareStatement(h);
            ko.setInt(1, partID);
            ResultSet info = ko.executeQuery();
            System.out.println(info.getInt(6));
            int qtyindatabase = info.getInt(6);
            if (qtyindatabase <= 0) {
                con.close();
                JOptionPane.showMessageDialog(null, "Part with the ID " + Add_ID_Part.getText() + " is out of stock");
                Add_ID_Part.setText("");
                return false;
            } else {
                qtyindatabase = qtyindatabase - 1;
                PreparedStatement stat = con.prepareStatement(sql);
                stat.setInt(2, partID);
                stat.setInt(1, qtyindatabase);
                stat.executeUpdate();
            }
            con.close();
        } catch (SQLException e) {

        }
        return true;
    }

    @FXML
    private void booked_parts(ActionEvent event) {
        hi();
    }

    public boolean booking() {
        con = conn.connect();
        String sql = "SELECT * FROM Booking WHERE RegistrationNumber = ?";
        Customers_Parts_Editt.setItems(null);
        Booking_Dates.getItems().clear();
        try {
            PreparedStatement ko = con.prepareStatement(sql);
            ko.setString(1, regNo.getValue());
            ResultSet info = ko.executeQuery();
            bookingID = info.getInt(1);
            boolean t = true;
            int no = 1;
            while (info.next()) {
                Booking_Dates.getItems().add(no + ". " + info.getInt(1) + " | " + info.getString(6) + " at " + info.getString(6));
                if (t) {
                    Booking_Dates.setValue(no + ". " + info.getInt(1) + " | " + info.getString(6) + " at " + info.getString(6));
                    t = false;
                }
                no++;
            }

            con.close();
            return true;
        } catch (SQLException e) {
          
            return false;
        }
    }

    @FXML
    private void showBookingType(MouseEvent event) {
        con = conn.connect();
        if (event.getClickCount() == 2) {
            String sql = "SELECT * FROM Booking WHERE RegistrationNumber = ? AND BookingID = ?";
            try {
                PreparedStatement ko = con.prepareStatement(sql);
                ko.setString(1, Customers_Parts_Editt.getSelectionModel().getSelectedItem().getRegistrationNo());
                ko.setInt(2, Customers_Parts_Editt.getSelectionModel().getSelectedItem().getBookingID());
                ResultSet info = ko.executeQuery();
                lbl_type_of_booking.setText(info.getString(4));
                lbl_type_of_booking.setStyle("-fx-background-color:White");
             
                
   
       
            String sqlparts = "SELECT * FROM Parts WHERE ID = ?";
           
                PreparedStatement koparts = con.prepareStatement(sqlparts);
               
                koparts.setInt(1, Customers_Parts_Editt.getSelectionModel().getSelectedItem().getPartsID());
                ResultSet infoparts = koparts.executeQuery();
            JOptionPane.showMessageDialog(null, "Name: " +
                    infoparts.getString(2) + "\nModel: " + 
                    infoparts.getString(3) + "\nMake: " + 
                    infoparts.getString(4) + "\nDescription: " + 
                    infoparts.getString(5) + "\nCost: £" +
                    infoparts.getString(7));
                con.close();
                
            } catch (SQLException e) {
               
            }
        }
    }
}
