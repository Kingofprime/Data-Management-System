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

public class XYZCompany_Delete{

    
    public static void deleteView(Stage primaryStage, Scene XYZMain) throws FileNotFoundException {
    	
    	
    	//primaryStage.setTitle("Employee Management");

    	// Label and TextField for Employee ID input
    	Label tag = new Label("Enter Employee by ID:");
    	TextField employeeid = new TextField();

    	// Layout for label and text field
    	HBox hbox = new HBox(10);
    	hbox.setPadding(new Insets(30, 30, 30, 30));
    	hbox.getChildren().addAll(tag, employeeid);

    	// Buttons
    	Button btnorder = new Button("Delete");
    	Button backButton = new Button("Back");

    	// Add components to vertical layout
    	VBox vbox = new VBox(20); // Increased the spacing for better aesthetics
    	vbox.setPadding(new Insets(30, 30, 30, 30));
    	vbox.setAlignment(Pos.CENTER); // Center the components vertically
    	vbox.getChildren().addAll(hbox, btnorder, backButton);
    	
    	btnorder.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");
    	backButton.setStyle("-fx-background-color: #f44336; -fx-text-fill: white;");

    	// Handle the delete action
    	btnorder.setOnAction(e -> {
    		Alert alert = new Alert(Alert.AlertType.CONFIRMATION); // Create a confirmation dialog
    	    alert.setTitle("Confirmation Dialog");
    	    alert.setHeaderText("Delete Employee");
    	    alert.setContentText("Are you sure you want to delete this employee?");

    	    Optional<ButtonType> result = alert.showAndWait();
    	    if (result.isPresent() && result.get() == ButtonType.OK) { // Check if the OK button is pressed
    	        try {
    	            delete(Integer.parseInt(employeeid.getText()));
    	        } catch (NumberFormatException | IOException | SQLException e1) {
    	            showAlert(Alert.AlertType.ERROR, "Error", "An error occurred while deleting the employee.", e1.getMessage());
    	        }
    	    }
    	});

    	// Handle the back action
    	backButton.setOnAction(e -> primaryStage.setScene(XYZMain));

    	// Create the scene and show the stage
    	Scene scene = new Scene(vbox, 400, 400);
    	primaryStage.setScene(scene);
    	primaryStage.show();

    	// Method to show alerts to the user (can be placed in the same class)
    	

    }
    
    public static void delete(int id) throws FileNotFoundException, IOException, SQLException
    {
    	String sql = "delete from XYZemployees where id = ?";
    	Connection conn = connect();
		PreparedStatement pstmt = conn.prepareStatement(sql);
    	try  {
            pstmt.setInt(1, id);
            
            pstmt.executeUpdate();
            UpdateSuccess();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }finally {
        	close(conn, pstmt, null);
        }
    }
    
    public static void UpdateSuccess()
	{
		Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Update Information");
        alert.setHeaderText(null);
        alert.setContentText("Deleted successfully!");

        alert.showAndWait();
	}
    
    private static Connection connect() throws FileNotFoundException, IOException {
	    // MySQL connection string
    	Properties props = new Properties();
		props.load(new FileInputStream("XYZCompanyDatabase.properties"));
		String theUser = props.getProperty("username");
		String thePassword = props.getProperty("password");
		String theUrl = props.getProperty("dbUrl");
	    
		System.out.println("Connecting to database...");
		System.out.println("Database URL: " + theUrl);
		System.out.println("User: " + theUser);
	    Connection conn = null;
	    try {
	    	conn = DriverManager.getConnection(theUrl, theUser, thePassword);
	    } catch (SQLException e) {
	        System.out.println(e.getMessage());
	    }
	    return conn;
	}	
    private static void showAlert(Alert.AlertType alertType, String title, String header, String message) {
	    Alert alert = new Alert(alertType);
	    alert.setTitle(title);
	    alert.setHeaderText(header);
	    alert.setContentText(message);
	    alert.showAndWait();
	}
    
    private static void close(Connection myConn, PreparedStatement myStmt, ResultSet myRs) throws SQLException{
		if(myRs != null)
			myRs.close();
		
		if(myStmt != null)
			myStmt.close();
		
		if(myConn != null)
			myConn.close();
	}	
}