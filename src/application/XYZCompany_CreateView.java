package application;

import java.io.*;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.util.*;
import java.util.Date;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.control.DatePicker;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class XYZCompany_CreateView{

   

    
    public static void createView(Stage primaryStage, Scene XYZMain) {
    	//primaryStage.setTitle("Employee Creation");
    	
    	GridPane layout = new GridPane();
    	layout.setPadding(new Insets(20, 20, 20, 20));
    	layout.setVgap(10);
    	layout.setHgap(10);
    	layout.setAlignment(Pos.CENTER);
    	
    	// Adding column constraints
    	for (int i = 0; i < 3; i++) {
    	    ColumnConstraints column = new ColumnConstraints(175);
    	    layout.getColumnConstraints().add(column);
    	}

    	// Main label
    	Label createNotice = new Label("Enter new employee information");
    	createNotice.setFont(new Font("Arial", 20));
    	GridPane.setConstraints(createNotice, 0, 0, 3, 1);
    	GridPane.setHalignment(createNotice, HPos.CENTER);
    	createNotice.setStyle("-fx-font-weight: bold; -fx-text-fill: #333;");
    	
    	// Employee details
    	layout.add(new Label("Employee ID:"), 0, 1);
    	TextField createEmployeeID = new TextField();
    	createEmployeeID.setPromptText("Employee ID");
    	layout.add(createEmployeeID, 1, 1, 2, 1);

    	CheckBox createSigned = new CheckBox("Contract Signed?");
    	layout.add(createSigned, 1, 2, 2, 1);

    	layout.add(new Label("Social Security Number:"), 0, 3);
    	PasswordField createSSN = new PasswordField();
    	createSSN.setPromptText("SSN");
    	layout.add(createSSN, 1, 3, 2, 1);

    	// Personal Information
    	layout.add(new Label("First Name:"), 0, 4);
    	TextField createFirstName = new TextField();
    	createFirstName.setPromptText("First Name");
    	layout.add(createFirstName, 1, 4);

    	layout.add(new Label("Last Name:"), 0, 5);
    	TextField createLastName = new TextField();
    	createLastName.setPromptText("Last Name");
    	layout.add(createLastName, 1, 5);

    	layout.add(new Label("Phone Number:"), 0, 6);
    	TextField createPhoneNum = new TextField();
    	createPhoneNum.setPromptText("Phone Number");
    	layout.add(createPhoneNum, 1, 6);

    	layout.add(new Label("Birth Date:"), 0, 7);
    	DatePicker createBirthDate = new DatePicker();
    	createBirthDate.setPromptText("Birth Date");
    	layout.add(createBirthDate, 1, 7);

    	// Employment details
    	layout.add(new Label("Start Date:"), 0, 8);
    	DatePicker createStartDate = new DatePicker();
    	createStartDate.setPromptText("Start Date");
    	layout.add(createStartDate, 1, 8);

    	layout.add(new Label("Salary:"), 0, 9);
    	TextField createStartSalary = new TextField();
    	createStartSalary.setPromptText("Start Salary");
    	layout.add(createStartSalary, 1, 9);

    	// Emergency contacts
    	layout.add(new Label("Emergency Contact:"), 0, 10);
    	TextField createEmgName = new TextField();
    	createEmgName.setPromptText("Contact Name");
    	layout.add(createEmgName, 1, 10);

    	layout.add(new Label("Emergency Contact Phone:"), 0, 11);
    	TextField createEmgNum = new TextField();
    	createEmgNum.setPromptText("Contact Number");
    	layout.add(createEmgNum, 1, 11);

    	// Buttons
    	Button createButton = new Button("Create");
    	GridPane.setConstraints(createButton, 0, 12, 1, 1);
    	GridPane.setHalignment(createButton, HPos.CENTER);

    	Button backButton = new Button("Back");
    	GridPane.setConstraints(backButton, 1, 12, 2, 1);
    	GridPane.setHalignment(backButton, HPos.CENTER);

    	layout.getChildren().addAll(createButton, backButton);
    	
    	createButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");
    	backButton.setStyle("-fx-background-color: #f44336; -fx-text-fill: white;");
    	createButton.setOnAction(c -> {
    		try {
				createData(createSigned.isSelected(), createEmployeeID.getText(), 
						createFirstName.getText(), createLastName.getText(), createStartDate.getValue(), 
						createStartSalary.getText(), createSSN.getText(), createBirthDate.getValue(), 
						createPhoneNum.getText(), createEmgName.getText(), createEmgNum.getText());
			} catch (ClassNotFoundException | SQLException | IOException | ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	});

    	backButton.setOnAction(e -> primaryStage.setScene(XYZMain));

    	Scene XYZCreate = new Scene(layout, 700, 600);
    	primaryStage.setScene(XYZCreate);
    	primaryStage.show();

		
		
    }
    
    public static void createData(boolean isSigned, String ID, String first_name, String last_name, LocalDate start_date,
			   String start_salary, String SSN, LocalDate birth_date, String phoneNum, String emgName,
			   String emgNum) throws ClassNotFoundException, SQLException, IOException, ParseException{

		
		    	//variable to pass into sql for "contract Signed"
		char signed = 'N';
		if(isSigned == true) {
		signed = 'Y';
		}
		
		
		//standard JBDC connection stuff
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		
		try {
		//1. load the properties file
		Properties props = new Properties();
		props.load(new FileInputStream("XYZCompanyDatabase.properties"));
		
		//2. read the properties
		String theUser = props.getProperty("username");
		String thePassword = props.getProperty("password");
		String theUrl = props.getProperty("dbUrl");
		
		System.out.println("Connection to database...");
		System.out.println("Database URL: " + theUrl);
		System.out.println("User:" + theUser);
		
		//3. get Connection
		myConn = DriverManager.getConnection(theUrl, theUser, thePassword);
		System.out.println("Database connection succesfull! \n");
		
		//4. Create a statement to pass all values filled in (NOTE: CREATE A METHOD TO DETECT IF A FIELD IS EMPTY)
		myStmt = myConn.prepareStatement("insert into XYZemployees "
								   + "(id, last_name, first_name, start_date, start_salary, contract_signed, "
								   + "ssn, birth_date, phone_number, emg_contact_name, emg_contact_number)"
								   + "values"
								   + "(?,?,?,?,?,?,?,?,?,?,?)");
		
		//5. Execute SQL Query (Insert a new employee
		System.out.println("Inserting a new employee to database \n");
		
		myStmt.setInt(1, Integer.parseInt(ID));
		myStmt.setString(2, last_name);
		myStmt.setString(3, first_name);
		myStmt.setObject(4, start_date);
		myStmt.setDouble(5, Double.parseDouble(start_salary));
		myStmt.setString(6, String.valueOf(signed));
		myStmt.setLong(7, Long.parseLong(SSN));
		myStmt.setObject(8, birth_date);
		myStmt.setLong(9, Long.parseLong(phoneNum));
		myStmt.setString(10, emgName);
		myStmt.setLong(11, Long.parseLong(emgNum));
		
		//7. Execute 
		myStmt.execute();
		System.out.println("Sucessfull\n");
		Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Success: New Employee has been added to XYZDatabase");

        alert.showAndWait();
		}
		catch(Exception exc) {
		exc.printStackTrace();
		}
		finally {
		close(myConn, myStmt, myRs);
		}
		}
    
    private static void close(Connection myConn, Statement myStmt, ResultSet myRs) throws SQLException{
		if(myRs != null)
			myRs.close();
		
		if(myStmt != null)
			myStmt.close();
		
		if(myConn != null)
			myConn.close();
	}	

   
}