package application;

/* Student Name: Jenyll Mabborang
 * Student ID: 301225121 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
 
public class DisplayCustomers {
 
    public static TableView<CustInfo> table = new TableView<CustInfo>();
    public static ObservableList<CustInfo> data =
        FXCollections.observableArrayList(getAllCustomersInfo()
        );
    
    public static void display(Stage stage) {
        Scene scene = new Scene(new Group());
        stage.setTitle("Customers Database");
        stage.setWidth(960);
        stage.setHeight(500);
 
        final Label label = new Label("Customers");
        label.setFont(new Font("Arial", 20));
 
        table.setEditable(true);
        
        TableColumn customerIDCol = new TableColumn("Customer ID");
        customerIDCol.setMinWidth(50);
        customerIDCol.setCellValueFactory(new PropertyValueFactory<CustInfo, Integer>("customerID"));
        
        TableColumn firstNameCol = new TableColumn("First Name");
        firstNameCol.setMinWidth(100);
        firstNameCol.setCellValueFactory(new PropertyValueFactory<CustInfo, String>("firstName"));
 
        TableColumn lastNameCol = new TableColumn("Last Name");
        lastNameCol.setMinWidth(100);
        lastNameCol.setCellValueFactory( new PropertyValueFactory<CustInfo, String>("lastName"));
 
        TableColumn emailCol = new TableColumn("Email");
        emailCol.setMinWidth(150);
        emailCol.setCellValueFactory(new PropertyValueFactory<CustInfo, String>("emailAddress"));
        
        TableColumn phoneNumberCol = new TableColumn("Phone Number");
        phoneNumberCol.setMinWidth(100);
        phoneNumberCol.setCellValueFactory(new PropertyValueFactory<CustInfo, String>("phoneNumber"));
        
        TableColumn countryCol = new TableColumn("Country");
        countryCol.setMinWidth(50);
        countryCol.setCellValueFactory(new PropertyValueFactory<CustInfo, String>("country"));
        
        TableColumn provinceCol = new TableColumn("Province");
        provinceCol.setMinWidth(100);
        provinceCol.setCellValueFactory(new PropertyValueFactory<CustInfo, String>("province"));
        
        TableColumn cityCol = new TableColumn("City");
        cityCol.setMinWidth(100);
        cityCol.setCellValueFactory(new PropertyValueFactory<CustInfo, String>("city"));
        
        TableColumn postalCodeCol = new TableColumn("Postal Code");
        postalCodeCol.setMinWidth(100);
        postalCodeCol.setCellValueFactory(new PropertyValueFactory<CustInfo, String>("postalCode"));
               
        table.setItems(data);
        table.getColumns().addAll(customerIDCol, firstNameCol, lastNameCol, emailCol, phoneNumberCol, countryCol, provinceCol, cityCol, postalCodeCol);
        
        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(label, table);
 
        ((Group) scene.getRoot()).getChildren().addAll(vbox);
 
        stage.setScene(scene);
        stage.show();
    }
    
    private static List<CustInfo> getAllCustomersInfo() {
        List<CustInfo> players = new ArrayList<CustInfo>();
        Connection connection = null;
        PreparedStatement statement = null;
        try {
                connection = dbConnection.getDBConnection();
                connection.setAutoCommit(false);
                String query = "SELECT * FROM COMM_CUSTOMER ORDER BY CUSTOMER_ID";
                statement = connection.prepareStatement(query);
                ResultSet rs = statement.executeQuery();
                CustInfo player = null;
                while (rs.next()) {
                        player = new CustInfo(rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7),rs.getString(8),rs.getString(9));
                        player.setCustomerID(rs.getInt(1));
                        players.add(player);
                }
        } catch (Exception e) {
                e.printStackTrace();
        } finally {
                if (null != statement) {
                        try {
                                statement.close();
                        } catch (SQLException e) {
                                e.printStackTrace();
                        }
                }
                if (null != connection) {
                        try {
                                connection.close();
                        } catch (SQLException e) {
                                e.printStackTrace();
                        }
                }
        }
        return players;
    }
    
    
    
    
        
       
	
}
