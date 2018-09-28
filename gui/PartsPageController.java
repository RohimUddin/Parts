/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parts.gui;

import common.CommonDatabase;
import customer.logic.allCustomers;
import java.io.IOException;
import parts.*;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import java.util.Date;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author rohim
 */
public class PartsPageController implements Initializable {

    @FXML
    private TextField firstname;
    @FXML
    private TextField surname;
    @FXML
    private TableView<Customers_Parts_Edit> dataTable;
    @FXML
    private TextField regNumber;
    @FXML
    private Button Edit;
    @FXML
    private Button Add_Parts;
    @FXML
    private Label email;
    @FXML
    private Label address;
    @FXML
    private Label postcode;
    @FXML
    private Label phone;
    @FXML
    private Button clear;
    @FXML
    private TableColumn<Customers_Parts_Edit, Integer> booking;
    @FXML
    private TableColumn<Customers_Parts_Edit, String> installed;
    @FXML
    private TableColumn<Customers_Parts_Edit, Integer> parts;
    @FXML
    private TableColumn<Customers_Parts_Edit, String> expire;
    @FXML
    private Button search;
    @FXML
    private ChoiceBox<String> Parts;
    @FXML
    private Button add_choice;
    @FXML
    private TextField instal;
    @FXML
    private TextField txtexpire;

    private String reg = "";
    @FXML
    private TableColumn<Customers_Parts_Edit, String> REgistrationNumber;
    private ObservableList<Customers_Parts_Edit> data;

    ConnectionToParts conn = new ConnectionToParts();
    Connection con = null;
    @FXML
    private AnchorPane mainPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        conn = new ConnectionToParts();

    }

    @FXML
    private void Edit_Page(ActionEvent event) {
        try {

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("PartsEdit.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (Exception e) {
            System.out.println("No doesnt work");
        }
    }

    @FXML
    private void Add_Parts(ActionEvent event) {
        try {

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("PartsAdd.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (Exception e) {

            System.out.println("No doesnt work");

        }
    }

    @FXML
    private void clear(ActionEvent event) {
        clear();
        //colour()
    }

    @FXML
    private void search_customer(ActionEvent event) {
        if (firstname.getText().equals("") && surname.getText().equals("") && regNumber.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "You must enter a Customer ID or First name and Surname to make a search");
            return;
        }
        try {
            int i = Integer.parseInt(regNumber.getText());

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "You must Enter a number when searching a customer using customerID");
            regNumber.setText("");
            return;
        }

        try {
            if (firstname.getText().equals("") && surname.getText().equals("")) {
                reg_no();
            } else if (regNumber.getText().equals("")) {
                first_sur(firstname.getText(), surname.getText());
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "try again");
        }

        String sqlRegno = "SELECT * FROM Vehicles WHERE CustomerID = ? ";
        String sql = "SELECT * FROM PartsUsed WHERE RegistrationNumber = ? ";
        con = conn.connect();
        try {
            PreparedStatement s = con.prepareStatement(sqlRegno);

            s.setInt(1, Integer.parseInt(regNumber.getText()));

            //JOptionPane.showMessageDialog(null,"Customer with the name " + firstname.getText() + " " + surname.getText()+ " Does not exist");
            ResultSet in = s.executeQuery();

            PreparedStatement stat = con.prepareStatement(sql);

            stat.setString(1, in.getString(9));
            reg = in.getString(9);
            ResultSet info = stat.executeQuery();

            boolean i = true;
            Parts.getItems().clear();
            while (info.next()) {
                Parts.getItems().add(Integer.toString(info.getInt(2)));
                if (i) {
                    Parts.setValue(Integer.toString(info.getInt(2)));
                    i = false; 
                    date();
            txtexpire.setDisable(false);
            instal.setDisable(false);
            Parts.setDisable(false);
            add_choice.setDisable(false);
            update_table();
                }
            }
           update_table();

            con.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Customer with ID " + regNumber.getText() + " Does not exist");
            regNumber.setText("");
            return;
        }
       
    }

    public void first_sur(String f, String s) {

        String sql = "SELECT * FROM Customer_Accounts WHERE Firstname = ? AND Surname = ?";
        con = conn.connect();
        try {

            PreparedStatement stat = con.prepareStatement(sql);

            stat.setString(1, f);
            stat.setString(2, s);

            ResultSet info = stat.executeQuery();

            regNumber.setText(info.getString(1));
            address.setText(info.getString(4));
            colour(address, false);
            email.setText(info.getString(7));
            colour(email, false);
            postcode.setText(info.getString(5));
            colour(postcode, false);
            phone.setText(info.getString(6));
            colour(phone, false);
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void reg_no() {

        String sql = "SELECT * FROM Customer_Accounts WHERE ID = ? ";
        con = conn.connect();
        try {
            PreparedStatement stat = con.prepareStatement(sql);
            stat.setInt(1, Integer.parseInt(regNumber.getText()));
            ResultSet info = stat.executeQuery();
            firstname.setText(info.getString(2));
            surname.setText(info.getString(3));
            address.setText(info.getString(4));
            colour(address, false);
            email.setText(info.getString(7));
            colour(email, false);
            postcode.setText(info.getString(5));
            colour(postcode, false);
            phone.setText(info.getString(6));
            colour(phone, false);
            con.close();
        } catch (SQLException e) {

        }
    }

    public void visible(Label l, boolean t) {
        l.setVisible(t);

    }

    public void colour(Label l, boolean p) {
        if (!p) {
            l.setStyle("-fx-background-color:White");
            return;
        }
        l.setStyle("-fx-background-color:Silver");
    }

    @FXML
    private void Add_Choice(ActionEvent event) {
        String sql = "UPDATE PartsUsed SET ExpireDate = ? WHERE RegistrationNumber = ? AND PartsID = ? ";
        String sql2 = "UPDATE PartsUsed SET InstallationDate = ? WHERE RegistrationNumber = ? AND PartsID = ? ";

        con = conn.connect();
        try {
            PreparedStatement stat = con.prepareStatement(sql);
            stat.setString(1, txtexpire.getText());
            stat.setString(2, reg);
            stat.setInt(3, Integer.parseInt(Parts.getValue()));
            stat.executeUpdate();
            PreparedStatement st = con.prepareStatement(sql2);
            st.setString(1, instal.getText());
            st.setString(2, reg);
            st.setInt(3, Integer.parseInt(Parts.getValue()));
            st.executeUpdate();
            con.close();
        } catch (SQLException e) {

        }
        update_table();
    }

    @FXML
    private void AddDate(ActionEvent event) {

    }

    public void date() {
        Date date = Calendar.getInstance().getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

//        DateFormat dateFormat = new SimpleDateFormat("dd-mm-yyyy");
//        Date date = new Date();
        String d = sdf.format(date);
//                dateFormat.format(date);
        instal.setText(d);
        String[] change = d.split("-");
        int year = Integer.parseInt(change[2]);
        year = year + 1;
        txtexpire.setText(change[0] + "-" + change[1] + "-" + year);
    }

    public void update_table() {

        con = conn.connect();

        try {

            String sql = "SELECT * FROM PartsUsed WHERE RegistrationNumber = ?";
            PreparedStatement stat = con.prepareStatement(sql);
            stat.setString(1, reg);
            ResultSet info = stat.executeQuery();
            data = FXCollections.observableArrayList();
            parts.setCellValueFactory(new PropertyValueFactory("PartsID"));

            booking.setCellValueFactory(new PropertyValueFactory("BookingID"));

            REgistrationNumber.setCellValueFactory(new PropertyValueFactory("RegistrationNo"));

            expire.setCellValueFactory(new PropertyValueFactory("expire"));

            installed.setCellValueFactory(new PropertyValueFactory("installed"));

            while (info.next()) {
                System.out.println(info.getString(5));
                data.add(new Customers_Parts_Edit(info.getString(1), info.getInt(2), info.getInt(3), info.getString(4), info.getString(5)));

            }

        } catch (SQLException e) {
            System.out.println("Doesn't work");
        }

        dataTable.setItems(null);
        dataTable.setItems(data);

    }

    public void clear() {
        email.setText("");
        colour(email, true);
        address.setText("");
        colour(address, true);
        postcode.setText("");
        colour(postcode, true);
        phone.setText("");
        colour(phone, true);
        regNumber.clear();
        firstname.clear();
        surname.clear();
        Parts.getItems().clear();
        instal.setText("");
        txtexpire.setText("");
        dataTable.setItems(null);
    }

    @FXML
    private void home(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/common/Template.fxml"));
        mainPane.getChildren().setAll(pane);

    }

    @FXML
    private void customer(ActionEvent event) throws IOException {

        AnchorPane pane = FXMLLoader.load(getClass().getResource("/customer/gui/CustomerRealPage.fxml"));
        mainPane.getChildren().setAll(pane);
    }

    @FXML
    private void bandrb(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/diagrep/gui/BookingDetails.fxml"));
        mainPane.getChildren().setAll(pane);
    }

    @FXML
    private void vehicle(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/vehicles/gui/VehiclePage.fxml"));
        mainPane.getChildren().setAll(pane);
    }

    @FXML
    private void sr(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/specialist/gui/spcMainPage.fxml"));
        mainPane.getChildren().setAll(pane);

    }

}
