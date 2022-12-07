package application;
	
/* Student Name: Venzon Ariola
 * Student ID: 301207576 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
	
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


public class Main extends Application {
	
	//GridPane gridPane;
	Button btnUpdateCustomer, btnAddCust, btnAllCustomer;
	TextField txtCustomerId,txtFirstName, txtLastName, txtEmail, txtPhoneNumber, txtCountry, txtProvince, txtCity,
    txtPostalCode;
	@Override
	public void start(Stage primaryStage) {
		
//------------------------------------------Design Area---------------------------------------------------------//
		
		BorderPane pane =  new BorderPane();
			Scene scene = new Scene(pane, 900,450);
			primaryStage.setTitle("E-Commerce – Mobile Retailing");
			primaryStage.getIcons().add(new Image("C:\\Users\\venzo\\OneDrive - Centennial College\\Desktop\\FALL SEMESTER 2022\\Advance Database\\PROJECT\\icon.png"));
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			pane.setPadding(new Insets(20, 20, 20, 20));
			primaryStage.show();
		
			GridPane westArea = new GridPane();
				pane.setLeft(westArea);
				westArea.setAlignment(Pos.TOP_LEFT);
				westArea.setPadding(new Insets(20, 20, 20, 20));
				//setMinSize will get the preferred size of the gridpane for personal information inputs
				westArea.setMinSize(GridPane.USE_PREF_SIZE, GridPane.USE_PREF_SIZE);
				westArea.setHgap(20);
				westArea.setVgap(10);

				
				Label info = new Label("Customer Information: ");
				info.setFont(Font.font(Font.getDefault().getFamily(), FontWeight.BOLD, 15));
				westArea.add(info, 0, 0);
				
				westArea.add(new Label("First name: "), 0, 1);
				txtFirstName = new TextField();
				westArea.add(txtFirstName, 1, 1);
				
				westArea.add(new Label("Last name: "), 0, 2);
				txtLastName = new TextField();
				westArea.add(txtLastName, 1, 2);
				
				westArea.add(new Label("Email Address: "), 0, 3);
				txtEmail = new TextField();
				westArea.add(txtEmail, 1, 3);
				
				westArea.add(new Label("Phone Number: "), 0, 4);
				txtPhoneNumber = new TextField();
				westArea.add(txtPhoneNumber, 1, 4);
				
				westArea.add(new Label("Country: "), 0, 5);
				txtCountry = new TextField();
				westArea.add(txtCountry, 1, 5);
				
				westArea.add(new Label("Province: "), 0, 6);
				txtProvince = new TextField();
				westArea.add(txtProvince, 1, 6);
				
				westArea.add(new Label("City: "), 0, 7);
				txtCity = new TextField();
				westArea.add(txtCity, 1, 7);
				
				westArea.add(new Label("Postal Code: "), 0, 8);
				txtPostalCode = new TextField();
				westArea.add(txtPostalCode, 1, 8);
				
			GridPane southArea = new GridPane();
				pane.setCenter(southArea);
				southArea.setPadding(new Insets(20, 20, 20, 20));
				//setMinSize will get the preferred size of the gridpane for personal information inputs
				southArea.setMinSize(GridPane.USE_PREF_SIZE, GridPane.USE_PREF_SIZE);
				southArea.setHgap(20);
				southArea.setVgap(10);

				southArea.add(new Label("Update Customer by ID: "), 0, 1);
				txtCustomerId = new TextField();
				southArea.add(txtCustomerId, 1, 1);
				btnUpdateCustomer = new Button("Update");
				btnUpdateCustomer.setMaxSize(200,200);
		        southArea.add(btnUpdateCustomer, 2, 1);
		        
		        
		        btnAddCust = new Button("Add Customer");
		        btnAddCust.setMaxSize(200,200);
		        southArea.add(btnAddCust, 1, 29);
		        
		        btnAllCustomer = new Button("Display All Customers");
		        southArea.add(btnAllCustomer, 2, 29);
		
				
				
//----------------------------------End of Deign Area--------------------------------------------------------//
//----------------------------------------------********BACK-END CODE AREA*******---------------------------------------------------------//
//----------------********CODE FOR INSERT, DISPLAY, AND UPDATE PLAYER AND GAME INFO BUTTON********--------------------------------// 		
				
				btnAddCust.setOnAction(ae -> {
					
					String firstName = txtFirstName.getText();
					String lastName = txtLastName.getText();
					String email = txtEmail.getText();
					int PhoneNumber = txtPhoneNumber.getText();
					String country = txtCountry.getText();
					String province = txtProvince.getText();
					String city = txtCity.getText();
					String postalCode = txtPostalCode.getText();
					
					CustInfo custinfo = new CustInfo(firstName,lastName,email,PhoneNumber, country, province,city,postalCode);
					insertCustomer(custinfo);
						
				});
					btnAllCustomer.setOnAction(ae -> 
    {
    			DisplayCustomers.display(primaryStage);
    });
	}			
//-----------------------------------SQL CODE AREA---------------
			
			private void insertCustomer(CustInfo custinfo) 
			{
				Connection connection = null;
		        PreparedStatement statement = null;
		        
		        try {
		        	connection = dbConnection.getDBConnection();
	                connection.setAutoCommit(false);
	                String query = "INSERT INTO COMM_CUSTOMER VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
	                statement = connection.prepareStatement(query);
	                statement.setInt(1, getCustomerID());
	                statement.setString(2, custinfo.getFirstName());
	                statement.setString(3, custinfo.getLastName());
	                statement.setString(4, custinfo.getEmailAddress());
	                statement.setString(5, custinfo.getPhoneNumber());
	                statement.setString(6, custinfo.getCountry());
	                statement.setString(7, custinfo.getProvince());
	                statement.setString(8, custinfo.getCity());
	                statement.setString(8, custinfo.getPostalCode());
	                int count = statement.executeUpdate();
	                if (count == 1) {
	                        this.alert("Success", "Player Information inserted to DB successfully", AlertType.INFORMATION);
	                } else {
	                        this.alert("Failure", "Some error while adding Player Information", AlertType.ERROR);
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
	    }
			
			private int getCustomerID() {
		        int nextCustomerId =1;
		        Connection connection = null;
		        PreparedStatement statement = null;
		        try {
		                connection = dbConnection.getDBConnection();
		                connection.setAutoCommit(false);
		                String query = "SELECT MAX(CUSTOMER_ID) FROM COMM_CUSTOMER";
		                statement = connection.prepareStatement(query);
		                //int c = statement.executeUpdate();
		                ResultSet rs = statement.executeQuery();
		                while (rs.next()) {
		                	nextCustomerId = rs.getInt(1) + 1;
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
		        return nextCustomerId;
		    }
			
			public void alert(String title, String message, AlertType alertType) {
	            Alert alert = new Alert(alertType);
	            alert.setTitle(title);
	            alert.setHeaderText(null);
	            alert.setContentText(message);
	            alert.showAndWait();
	    }
		
	
	
	public static void main(String[] args) {
		launch(args);
	}
}
