package application;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;
import java.io.*;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.util.*;
import java.util.Date;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.sql.*;
import javafx.application.*;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.control.Alert.AlertType;


public class XYZCompany_ReadView  {
	private  static TableView<Person> tableView = new TableView<>();
	
	private static boolean columnsInitialized = false;

	public static void readView(Stage primaryStage, Scene XYZMain) throws FileNotFoundException  {
		try {
			
			//primaryStage.setTitle("Employee Data");
			
			//primaryStage.setTitle("Employee Management");

			// Top Label
			Label topLabel = new Label("Employee Records");
			topLabel.setStyle("-fx-font: 32 arial; -fx-text-fill: #2C3E50; -fx-padding: 20px;");

			// Search Components
			Label search = new Label("Search for Employee using ID:");
			search.setStyle("-fx-font: 16 arial; -fx-text-fill: #34495E;");

			TextField id = new TextField();
			id.setPromptText("Enter Employee I.D");
			id.setMaxWidth(200);

			Button b = new Button("Search");
			b.setStyle("-fx-background-color: #2980B9; -fx-text-fill: white; -fx-padding: 8px 16px; -fx-border-radius: 4px; -fx-cursor: hand;");
			b.setOnMouseEntered(e -> b.setStyle("-fx-background-color: #2471A3; -fx-text-fill: white; -fx-padding: 8px 16px; -fx-border-radius: 4px; -fx-cursor: hand;"));
			b.setOnMouseExited(e -> b.setStyle("-fx-background-color: #2980B9; -fx-text-fill: white; -fx-padding: 8px 16px; -fx-border-radius: 4px; -fx-cursor: hand;"));

			Button backButton = new Button("Back");
			backButton.setStyle("-fx-background-color: #E74C3C; -fx-text-fill: white; -fx-padding: 8px 16px; -fx-border-radius: 4px; -fx-cursor: hand;");
			backButton.setOnMouseEntered(e -> backButton.setStyle("-fx-background-color: #C0392B; -fx-text-fill: white; -fx-padding: 8px 16px; -fx-border-radius: 4px; -fx-cursor: hand;"));
			backButton.setOnMouseExited(e -> backButton.setStyle("-fx-background-color: #E74C3C; -fx-text-fill: white; -fx-padding: 8px 16px; -fx-border-radius: 4px; -fx-cursor: hand;"));

			HBox hbox = new HBox(10);
			hbox.setPadding(new Insets(20, 30, 20, 30));
			hbox.setAlignment(Pos.CENTER);
			hbox.getChildren().addAll(search, id, b);

			VBox vbox = new VBox(10);
			vbox.setPadding(new Insets(30, 30, 30, 30));
			vbox.setAlignment(Pos.CENTER);
			vbox.getChildren().addAll(hbox, backButton);
			
			buildData(id.getText());

			backButton.setOnAction(e -> primaryStage.setScene(XYZMain));

			b.setOnAction(e -> {
			    try {
			        
			        
			        buildData(id.getText());
			    } catch (NumberFormatException ne) {
			        Alert alert = new Alert(Alert.AlertType.ERROR);
			        alert.setTitle("Error Dialog");
			        alert.setHeaderText("Invalid Input");
			        alert.setContentText("Please enter a valid Employee ID.");
			        alert.showAndWait();
			    } catch (ClassNotFoundException | SQLException | IOException e1) {
			        Alert alert = new Alert(Alert.AlertType.ERROR);
			        alert.setTitle("Error Dialog");
			        alert.setHeaderText("An Error Occurred");
			        alert.setContentText(e1.getMessage());
			        alert.showAndWait();
			    }
			});
			tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

			// Main Layout
			VBox layout = new VBox(10);
			layout.setStyle("-fx-background-color: #ECF0F1;");
			layout.getChildren().addAll(topLabel, tableView, vbox);

			Scene scene = new Scene(layout, 1200, 600);
			primaryStage.setScene(scene);
			primaryStage.show();

			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void buildData(String id) throws ClassNotFoundException, 
	SQLException, FileNotFoundException, IOException {
		
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		
		try {
			//1. Load the properties file
			Properties props = new Properties();
			props.load(new FileInputStream("XYZCompanyDatabase.properties"));
			
			//2. Read the props
			String theUser = props.getProperty("username");
			String thePassword = props.getProperty("password");
			String theUrl = props.getProperty("dbUrl");
			
			
			System.out.println("Connecting to database...");
			System.out.println("Database URL: " + theUrl);
			System.out.println("User: " + theUser);
			
			//3. Get a connection
			myConn = DriverManager.getConnection(theUrl, theUser, thePassword);
			
			System.out.println("Database connection successful! \n");
			String sql="";
			//4. Create a statement
			if(id == "")
			{
			  sql = "SELECT * FROM XYZemployees";
			  myStmt = myConn.prepareStatement(sql);
			}
			else {
				int sid = Integer.parseInt(id);
				sql = "SELECT * FROM XYZemployees where id = ?";
				myStmt = myConn.prepareStatement(sql);
		        myStmt.setInt(1,sid);
			}
		        
            myRs = myStmt.executeQuery();			
		}
		catch (Exception exc) {
			exc.printStackTrace();
		}
		finally {
			
		}
		  if (!columnsInitialized) {
		        initializeColumns(myRs);
		        columnsInitialized = true;
		    }
		  ObservableList dbData = FXCollections.observableArrayList(dataBaseArrayList(myRs));
		  tableView.setItems(dbData);		
	}
 public static void initializeColumns(ResultSet myRs) throws SQLException {
		

        //Giving readable names to columns
        for(int i=0 ; i<myRs.getMetaData().getColumnCount(); i++) {
            TableColumn column = new TableColumn<>();
            switch (myRs.getMetaData().getColumnName(i+1)) {
            case "id":
                column.setText("Employee ID");
                break;
            case "last_name":
                column.setText("Last Name");
                break;
            case "first_name":
                column.setText("First Name");
                break;
            case "start_date":
                column.setText("Start Date");
                break;
            case "start_salary":
                column.setText("Start Salary");
                break;
            case "contract_signed":
                column.setText("Contract Signed");
                break;
            case "ssn":
                column.setText("Social Security Number");
                break;
            case "birth_date":
                column.setText("Birth Date");
                break;
            case "phone_number":
                column.setText("Phone Number");
                break;
            case "emg_contact_name":
                column.setText("Emergency Contact Name");
                break;
            case "emg_contact_number":
                column.setText("Emergency Contact Number");
                break;
           default: 
                	column.setText(myRs.getMetaData().getColumnName(i+1)); //if column name in SQL Database is not found, then TableView column receive SQL Database current column name (not readable)
                         column.setMinWidth(100);
                    break;
            }
			column.setCellValueFactory(new PropertyValueFactory<>(myRs.getMetaData().getColumnName(i+1))); //Setting cell property value to correct variable from Person class.
			//column.setCellValueFactory(new PropertyValueFactory<>("startSalary"));

			tableView.getColumns().add(column);
		}
		
		//Filling up tableView with data
        
	}
	
	//extracting data from ResulSet to ArrayList
	private static ArrayList<Person> dataBaseArrayList(ResultSet resultSet) throws SQLException 
	{
	    ArrayList<Person> data = new ArrayList<>();
	    while (resultSet.next()) {
	        Person person = new Person();
	        person.setId(resultSet.getInt("id"));
	        person.setLast_name(resultSet.getString("last_name"));
	        person.setFirst_name(resultSet.getString("first_name"));
	        person.setStart_date(resultSet.getDate("start_date").toLocalDate());
	        person.setStart_salary(resultSet.getDouble("start_salary"));
	        // Assuming the contract_signed column in your database is a char or similar
	        person.setContract_signed(resultSet.getString("contract_signed").charAt(0));
	        // Assuming the following fields are integers in your database
	        person.setSsn(resultSet.getLong("ssn"));
	        if (resultSet.getDate("birth_date") != null) {
	            person.setBirth_date(resultSet.getDate("birth_date").toLocalDate());
	        }
	        person.setPhone_number(resultSet.getLong("phone_number"));
	        // Assuming you have added these methods in the Person class for emergency contact details
	        person.setEmg_contact_name(resultSet.getString("emg_contact_name"));
	        person.setEmg_contact_number(resultSet.getLong("emg_contact_number"));
	        
	        data.add(person);
	    }
	    return data;
	}
	
}
