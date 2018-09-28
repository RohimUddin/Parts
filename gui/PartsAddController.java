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
import javafx.scene.input.MouseEvent;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author rohim
 */
public class PartsAddController implements Initializable {

    @FXML
    private Label New_Part_Name;
    @FXML
    private Label New_Part_Description;
    @FXML
    private Label New_Part_Cost;
    @FXML
    private Label New_Part_Quantity;
    @FXML
    private CheckBox New_Part_Select;
    @FXML
    private TextField txt_New_Part_Name;
    @FXML
    private TextField txt_New_Part_Description;
    @FXML
    private TextField txt_New_Part_Cost;
    @FXML
    private TextField txt_New_Part_Quantity;
    @FXML
    private CheckBox Update_CurrentPart;
    @FXML
    private CheckBox add_Quantity;
    private Label lbl_Search_ID_ID;
    @FXML
    private Label lbl_Search_ID_Name;
    @FXML
    private Label lbl_Search_ID_Description;
    private TextField txt_ID_Search_ID;
    @FXML
    private Label lbl_data_Name;
    @FXML
    private Label lbl_data_Description;
    private Button Search_Part_by_ID_btn;
    @FXML
    private Label lbl_Search_ID_QTY;
    @FXML
    private TextField txt_Search_ID_QTY;
    @FXML
    private Button btn_Add_Part_by_ID;
    @FXML
    private Button Add_Quantity_btn;
    @FXML
    private Button btn_Clear;
    @FXML
    private TableView<Parts_Table> parts_table;
    private ObservableList<Parts_Table> l;
    @FXML
    private TableColumn<Parts_Table, Integer> id;
    @FXML
    private TableColumn<Parts_Table, String> name;
    @FXML
    private TableColumn<Parts_Table, String> description;
    @FXML
    private TableColumn<Parts_Table, String> cost;
    @FXML
    private TableColumn<Parts_Table, Integer> qty;

    ConnectionToParts conn;
    Connection con;
    int ChosenID = 0;

    @FXML
    private TextField txt_ID_Search;
    @FXML
    private Button Search_Part_by_ID;
    @FXML
    private Label lbl_Search_ID;
    private ObservableList<Parts_Table> data;
    @FXML
    private TableColumn<Parts_Table, String> model;
    @FXML
    private TableColumn<Parts_Table, String> make;
    @FXML
    private TextField model_txt;
    @FXML
    private TextField make_txt;
    @FXML
    private Label model_lbl;
    @FXML
    private Label make_lbl;
    @FXML
    private Button Delete;
    int currentQTY = 0;

    /**
     * Initializes the controller class.
     *
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        conn = new ConnectionToParts();
        updateTable();
    }

    @FXML
    public void New_Part_Choice(ActionEvent event) {
        if (New_Part_Select.isSelected()) {
            Visibility_Curent_Parts_Update(false);
            Visibility_New_Parts(true);
            add_Quantity.setSelected(false);
            Update_CurrentPart.setSelected(false);
            lbl_Search_ID.setVisible(false);
            txt_ID_Search.setVisible(false);
            Search_Part_by_ID.setVisible(false);
            model_txt.setVisible(true);
            make_txt.setVisible(true);
            model_lbl.setVisible(true);
            make_lbl.setVisible(true);
            txt_New_Part_Name.setText("");
            model_txt.setText("");
            make_txt.setText("");
            txt_New_Part_Description.setText("");
            txt_New_Part_Cost.setText("");
            txt_New_Part_Quantity.setText("");
            
        }
        updateTable();
    }

    @FXML
    public void Update_Currrent_Part_V(ActionEvent event) {
        if (Update_CurrentPart.isSelected()) {
            Visibility_Curent_Parts_Update(false);
            Visibility_New_Parts(true);
            add_Quantity.setSelected(false);
            New_Part_Select.setSelected(false);
            txt_ID_Search.setVisible(true);
            Search_Part_by_ID.setVisible(true);
            lbl_Search_ID.setVisible(true);
            model_txt.setVisible(true);
            make_txt.setVisible(true);
            model_lbl.setVisible(true);
            make_lbl.setVisible(true);
                txt_New_Part_Name.setText("");
            model_txt.setText("");
            make_txt.setText("");
            txt_New_Part_Description.setText("");
            txt_New_Part_Cost.setText("");
            txt_New_Part_Quantity.setText("");
        }
        updateTable();
    }

    @FXML
    public void add_quantity_V(ActionEvent event) {
        if (add_Quantity.isSelected()) {
            Visibility_Curent_Parts_Update(true);
            Visibility_New_Parts(false);
            New_Part_Select.setSelected(false);
            Update_CurrentPart.setSelected(false);
            lbl_Search_ID.setVisible(true);
            txt_ID_Search.setVisible(true);
            Search_Part_by_ID.setVisible(true);
            model_txt.setVisible(false);
            make_txt.setVisible(false);
            model_lbl.setVisible(false);
            make_lbl.setVisible(false);
            lbl_Search_ID.setVisible(false);
            txt_ID_Search.setVisible(false);
            Search_Part_by_ID.setVisible(false);
            lbl_data_Name.setText("");
            lbl_data_Description.setText("");
            txt_Search_ID_QTY.setText("");
            
            
        }
        updateTable();

    }

    public void Search_Part(ActionEvent event) {
        /* Parts pa = new Parts(Integer.parseInt(txt_ID_Search_ID.getText()));
        Parts newP=pa.SeachByID();
      lbl_data_Name.setText(newP.name());
        lbl_data_Description.setText(newP.Description());
        
        txt_Search_ID_QTY.setText(Integer.toString(newP.qty()));*/

        table_controles tb = new table_controles();
        tb.parts_d_table(parts_table);
        l = tb.getParts(parts_table);
    }

    public void Visibility_Curent_Parts_Update(boolean t) {

        lbl_Search_ID_Name.setVisible(t);
        lbl_Search_ID_Description.setVisible(t);
        lbl_data_Name.setVisible(t);
        lbl_data_Description.setVisible(t);
        lbl_Search_ID_QTY.setVisible(t);
        txt_Search_ID_QTY.setVisible(t);
        btn_Add_Part_by_ID.setVisible(t);
    }

    public void Visibility_New_Parts(boolean t) {
        New_Part_Name.setVisible(t);
        New_Part_Description.setVisible(t);
        New_Part_Cost.setVisible(t);
        New_Part_Quantity.setVisible(t);
        txt_New_Part_Name.setVisible(t);
        txt_New_Part_Description.setVisible(t);
        txt_New_Part_Cost.setVisible(t);
        txt_New_Part_Quantity.setVisible(t);
        Add_Quantity_btn.setVisible(t);

    }

    public void close(ActionEvent event) {
    }

    @FXML
    public void Clear_Add_Page(ActionEvent event) {
        txt_Search_ID_QTY.clear();
        txt_ID_Search.clear();
        txt_New_Part_Name.clear();
        txt_New_Part_Description.clear();
        txt_New_Part_Cost.clear();
        txt_New_Part_Quantity.clear();
        lbl_data_Name.setText("");
        lbl_data_Description.setText("");
        make_txt.clear();
        model_txt.clear();

        updateTable();
    }

    public void updateTable() {
        table_controles tb = new table_controles();
        tb.parts_d_table(parts_table);
        l = tb.getParts(parts_table);
    }

    @FXML
    private void data(MouseEvent event) {

        if (event.getClickCount() == 2) {
            if (New_Part_Select.isSelected()) {
                JOptionPane.showMessageDialog(null, "You can not sellect a part when adding a new part");
            } else {
                ChosenID = parts_table.getSelectionModel().getSelectedItem().getID();
                currentQTY = parts_table.getSelectionModel().getSelectedItem().getQTY();

                lbl_data_Name.setText(parts_table.getSelectionModel().getSelectedItem().getName());
                lbl_data_Description.setText(parts_table.getSelectionModel().getSelectedItem().getDescription());
                txt_Search_ID_QTY.setText(Integer.toString(parts_table.getSelectionModel().getSelectedItem().getQTY()));
                txt_New_Part_Name.setText(parts_table.getSelectionModel().getSelectedItem().getName());
                txt_New_Part_Description.setText(parts_table.getSelectionModel().getSelectedItem().getDescription());
                txt_New_Part_Cost.setText(parts_table.getSelectionModel().getSelectedItem().getCost());
                make_txt.setText(parts_table.getSelectionModel().getSelectedItem().getMake());
                model_txt.setText(parts_table.getSelectionModel().getSelectedItem().getModel());

                txt_New_Part_Quantity.setText(Integer.toString(parts_table.getSelectionModel().getSelectedItem().getQTY()));
            }
        }
    }

    @FXML
    private void updateQTYparts(ActionEvent event) {
        if (checkAction()) {

            try {

                int q = Integer.parseInt(txt_Search_ID_QTY.getText());

                int new_total = 0;
                String sql = "UPDATE Parts SET Quantity = ? WHERE ID = ? ";
                new_total = currentQTY + Integer.parseInt(txt_Search_ID_QTY.getText());
                if (qtycheck(currentQTY) && checknewtotal(new_total) && chosenID(ChosenID)) {

                    con = conn.connect();
                    try {

                        PreparedStatement stat = con.prepareStatement(sql);
                        stat.setInt(1, new_total);
                        stat.setInt(2, ChosenID);
                        stat.executeUpdate();
                        JOptionPane.showMessageDialog(null, txt_Search_ID_QTY.getText() + " has been added to the chosen part which gives a total of " + new_total);
                        lbl_data_Name.setText("");
                        lbl_data_Description.setText("");
                        txt_Search_ID_QTY.setText("");
                        con.close();
                    } catch (SQLException e) {

                    }

                    updateTable();
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Please Enter a Number");
                txt_Search_ID_QTY.setText("");
            }
        }
    }

    public boolean chosenID(int ChosenIdD) {
        if (ChosenIdD == 0) {
            JOptionPane.showMessageDialog(null, "Please select a part from the table first");
            txt_Search_ID_QTY.setText("");
            return false;
        }

        return true;
    }

    public boolean checknewtotal(int new_totall) {
        if (new_totall > 10) {
            JOptionPane.showMessageDialog(null, "The new total " + new_totall + " will exceed the maximum limit.");
            txt_Search_ID_QTY.setText("");
            return false;
        }
        return true;
    }

    public boolean qtycheck(int currentQTYy) {
        if (currentQTYy >= 10) {
            JOptionPane.showMessageDialog(null, "The current quantity is already equal to 10, trying to add more will exceed the maximum limit.");
            txt_Search_ID_QTY.setText("");
            return false;
        }
        return true;
    }

    @FXML
    private void search_p(ActionEvent event) {
        if (idsearchCheck(txt_ID_Search.getText())) {
            con = conn.connect();
            String sql = "SELECT * FROM Parts WHERE Name LIKE '%" + txt_ID_Search.getText() + "%' OR Description Like '" + txt_ID_Search.getText() + "%'";

            try {
                PreparedStatement st = con.prepareStatement(sql);
                ResultSet rs = st.executeQuery();

                data = FXCollections.observableArrayList();

                id.setCellValueFactory(new PropertyValueFactory("ID"));
                name.setCellValueFactory(new PropertyValueFactory("name"));
                description.setCellValueFactory(new PropertyValueFactory("description"));

                cost.setCellValueFactory(new PropertyValueFactory("cost"));

                qty.setCellValueFactory(new PropertyValueFactory("QTY"));
                while (rs.next()) {
                    data.add(new Parts_Table(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6), rs.getString(7)));

                }
                parts_table.setItems(null);
                parts_table.setItems(data);

                con.close();
            } catch (Exception e) {

            }
        }
    }

    public boolean idsearchCheck(String Search) {
        if (Search.equals("")) {
            JOptionPane.showMessageDialog(null, "Please enter a parts name");
            return false;
        }
        return true;
    }

    @FXML
    private void add_qty_update(ActionEvent event) {
        if (checkAction()) {
            con = conn.connect();
            String sql = "";
            PreparedStatement stat = null;
            try {

                try {

                    if (Update_CurrentPart.isSelected()) {
                        if (checkUpdatepart() && chosenID(ChosenID) && qtycheck()) {
                            sql = "UPDATE Parts SET Name = '" + txt_New_Part_Name.getText() + "', Model = '" + model_txt.getText() + "' , Make = '" + make_txt.getText() + "' , Description = '" + txt_New_Part_Description.getText() + "' , Cost = '" + txt_New_Part_Cost.getText() + "' , Quantity = '" + txt_New_Part_Quantity.getText() + "' WHERE ID = " + ChosenID;
                            stat = con.prepareStatement(sql);
                            stat.executeUpdate();
                        }
                    } else if (New_Part_Select.isSelected()) {
                        if (checkUpdatepart() && qtycheck()) {
                            sql = "INSERT INTO Parts (Name,Model,Make,Description,Cost,Quantity) VALUES (?,?,?,?,?,?)";
                            stat = con.prepareStatement(sql);
                            stat.setString(1, txt_New_Part_Name.getText());
                            stat.setString(2, model_txt.getText());
                            stat.setString(3, make_txt.getText());
                            stat.setString(4, txt_New_Part_Description.getText());
                            stat.setString(5, txt_New_Part_Cost.getText());
                            stat.setInt(6, Integer.parseInt(txt_New_Part_Quantity.getText()));
                            stat.executeUpdate();
                            JOptionPane.showMessageDialog(null, txt_New_Part_Name.getText() + " was added to the stock");
                        }
                        txt_Search_ID_QTY.clear();
                        txt_ID_Search.clear();
                        txt_New_Part_Name.clear();
                        txt_New_Part_Description.clear();
                        txt_New_Part_Cost.clear();
                        txt_New_Part_Quantity.clear();
                        lbl_data_Name.setText("");
                        lbl_data_Description.setText("");
                        make_txt.clear();
                        model_txt.clear();
                        con.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                updateTable();
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Please enter numbers in the quantity and cost fields");
            }
        }
    }

    @FXML
    private void DeletePartCompletely(ActionEvent event) {
        con = conn.connect();
        String sql = "DELETE FROM Parts WHERE ID = ? ";
        try {
            PreparedStatement stat = con.prepareStatement(sql);
            stat.setInt(1, parts_table.getSelectionModel().getSelectedItem().getID());
            stat.executeUpdate();
            JOptionPane.showMessageDialog(null, parts_table.getSelectionModel().getSelectedItem().getName() + " has been deleted");
            con.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "You must select a part from the table");
        }
        updateTable();

    }

    public boolean checkAction() {
        if (!add_Quantity.isSelected() && !Update_CurrentPart.isSelected() && !New_Part_Select.isSelected()) {
            JOptionPane.showMessageDialog(null, "Please select an action first");
            return false;
        }
        return true;
    }

    public boolean checkUpdatepart() {
        if (txt_New_Part_Name.getText().equals("")
                || model_txt.getText().equals("")
                || make_txt.getText().equals("")
                || txt_New_Part_Description.getText().equals("")
                || txt_New_Part_Cost.getText().equals("")
                || txt_New_Part_Quantity.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "You can not leave any fields empty");
            return false;
        }

        return true;
    }

    public boolean qtycheck() {
        try {

            int b = Integer.parseInt(txt_New_Part_Quantity.getText());

            if (b > 10) {
                JOptionPane.showMessageDialog(null, "Updating the qty by " + txt_New_Part_Quantity.getText() + " will exceed the maximum amount");
                return false;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Please Enter a Number for the quantity");
            txt_New_Part_Quantity.setText("");
            return false;
        }
        return true;
    }
}
