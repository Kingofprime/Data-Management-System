package application;

import java.io.*;
import java.lang.reflect.Method;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.util.*;
import java.util.Date;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Modality;
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

public class XYZCompany_UpdateView{

    
    public static void updateView(Stage primaryStage, Scene XYZMain) {
    	//primaryStage.setTitle("Employee Update");
    	
    	// Labels and Input
    	Label labelId = new Label("Enter Employee by ID:");
    	TextField textFieldId = new TextField();
    	Label labelUpdate = new Label("What do you want to update?");

    	ChoiceBox<String> choiceBoxUpdateOptions = new ChoiceBox<>();
    	choiceBoxUpdateOptions.getItems().addAll("First Name", "Last Name", "Start Date",
    	    "Start Salary", "Employee Contract Signed(Y/N)", "Phone Number", "Social Security Number",
    	    "Emergency Contact Name", "Emergency Contact Phone");
    	
    	choiceBoxUpdateOptions.setValue("First Name");

    	HBox hboxId = new HBox(10, labelId, textFieldId);
    	HBox hboxChoice = new HBox(10, labelUpdate, choiceBoxUpdateOptions);

    	// Buttons
    	Button btnUpdate = new Button("Update");
    	Button btnBack = new Button("Back");

    	btnUpdate.setOnAction(e -> update(choiceBoxUpdateOptions, textFieldId.getText()));
    	btnBack.setOnAction(e -> primaryStage.setScene(XYZMain));

    	// Main layout
    	VBox mainLayout = new VBox(15, hboxId, hboxChoice, btnUpdate, btnBack);
    	mainLayout.setPadding(new Insets(30));
    	mainLayout.setAlignment(Pos.CENTER);

    	// Styling (you can expand on this with more CSS)
    	mainLayout.setStyle("-fx-font-family: 'Arial';");
    	btnUpdate.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");
    	btnBack.setStyle("-fx-background-color: #f44336; -fx-text-fill: white;");

    	Scene scene = new Scene(mainLayout, 500, 250);
    	primaryStage.setScene(scene);
    	primaryStage.show();
    }
public static void update(ChoiceBox<String> list , String sid) {
	
	
	
	if (sid == null || sid.trim().isEmpty()) {
	    Alert alert = new Alert(Alert.AlertType.WARNING);
	    alert.setTitle("Input Error");
	    alert.setHeaderText(null);
	    alert.setContentText("Please enter a valid ID!");
	    alert.showAndWait();
	    return;  // exit the current method or logic
	}

	 // initialized to a default value
	
	
	    int id = Integer.parseInt(sid);
	

	// rest of the code

		
	if (list.getValue()=="First Name") {
	    Stage a = new Stage();
	    a.initModality(Modality.APPLICATION_MODAL);

	    Label old = new Label("Enter the New First Name:");
	    old.setStyle("-fx-font-weight: bold;");  // Bold font for label

	    TextField name = new TextField();
	    name.setPromptText(list.getValue());
	    name.setStyle("-fx-min-width: 200px;");  // Set the minimum width for the TextField

	    Button b = new Button("Update");
	    b.setDefaultButton(true);  // Make the update button respond to the Enter key
	    b.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");  // Styling for the button

	    HBox hbox = new HBox(10);
	    hbox.setAlignment(Pos.CENTER_LEFT);  // Aligning the children to the center left
	    hbox.setPadding(new Insets(15, 30, 15, 30));
	    hbox.getChildren().addAll(old, name);

	    VBox vbox = new VBox(10);
	    vbox.setAlignment(Pos.CENTER);  // Centering the VBox content
	    vbox.setPadding(new Insets(30, 30, 30, 30));
	    vbox.setStyle("-fx-background-color: #f0f0f0;");  // Background color for VBox
	    vbox.getChildren().addAll(hbox,b);
	    Scene s = new Scene(vbox, 600, 200);  // Adjusted the scene size
	    a.setScene(s);
	    a.setTitle(list.getValue());
	    b.setOnAction(e -> {
	        try {
	        	if (name.getText() == null || name.getText().trim().isEmpty()) {
	        	    Alert alert = new Alert(Alert.AlertType.WARNING);
	        	    alert.setTitle("Input Error");
	        	    alert.setHeaderText(null);
	        	    alert.setContentText("Please enter a valid value!");
	        	    alert.showAndWait();
	        	    return;  // exit the current method or logic
	        	}
	            updateFirstName(id, name.getText());
	            
	        } catch (FileNotFoundException e1) {
	            e1.printStackTrace();
	        } catch (IOException e1) {
	            e1.printStackTrace();
	        }
	        a.close();
	    });

	    a.showAndWait();
	}

	else if(list.getValue().equals("Last Name")) {
	    Stage a = new Stage();
	    a.initModality(Modality.APPLICATION_MODAL);

	    Label old = new Label("Enter the New Last Name:");
	    old.setStyle("-fx-font-weight: bold;");  // Bold font for label

	    TextField name = new TextField();
	    name.setPromptText(list.getValue());
	    name.setStyle("-fx-min-width: 200px;");  // Set the minimum width for the TextField

	    Button b = new Button("Update");
	    b.setDefaultButton(true);  // Make the update button respond to the Enter key
	    b.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");  // Styling for the button

	    HBox hbox = new HBox(10);
	    hbox.setAlignment(Pos.CENTER_LEFT);  // Aligning the children to the center left
	    hbox.setPadding(new Insets(15, 30, 15, 30));
	    hbox.getChildren().addAll(old, name);

	    VBox vbox = new VBox(10);
	    vbox.setAlignment(Pos.CENTER);  // Centering the VBox content
	    vbox.setPadding(new Insets(30, 30, 30, 30));
	    vbox.setStyle("-fx-background-color: #f0f0f0;");  // Background color for VBox
	    vbox.getChildren().addAll(hbox, b);  // Adding the HBox and Button to the VBox

	    Scene s = new Scene(vbox, 600, 200);  // Adjusted the scene size
	    a.setScene(s);
	    a.setTitle(list.getValue());
	    b.setOnAction(e -> {
	        try {
	        	if (name.getText() == null || name.getText().trim().isEmpty()) {
	        	    Alert alert = new Alert(Alert.AlertType.WARNING);
	        	    alert.setTitle("Input Error");
	        	    alert.setHeaderText(null);
	        	    alert.setContentText("Please enter a valid value!");
	        	    alert.showAndWait();
	        	    return;  // exit the current method or logic
	        	}
	            updateLastName(id, name.getText());
	        } catch (FileNotFoundException e1) {
	            e1.printStackTrace();
	        } catch (IOException e1) {
	            e1.printStackTrace();
	        }
	        a.close();
	    });

	    a.showAndWait();
	}

	else if(list.getValue().equals("Start Date")) 
	{
	    Stage a = new Stage();
	    a.initModality(Modality.APPLICATION_MODAL);

	    Label old = new Label("Enter the New Start Date:");
	    old.setStyle("-fx-font-weight: bold;");
	    
	    DatePicker name = new DatePicker();
	    name.setStyle("-fx-min-width: 200px;");
	    name.setPromptText(list.getValue());
	    
	    Button b = new Button("Update");
	    b.setDefaultButton(true);
	    b.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");
	    
	    HBox hbox = new HBox(10);
	    hbox.setAlignment(Pos.CENTER_LEFT);
	    hbox.setPadding(new Insets(15, 30, 15, 30));
	    hbox.getChildren().addAll(old, name);
	    
	    VBox vbox = new VBox(10);
	    vbox.setAlignment(Pos.CENTER);
	    vbox.setPadding(new Insets(30, 30, 30, 30));
	    vbox.setStyle("-fx-background-color: #f0f0f0;");
	    vbox.getChildren().addAll(hbox, b);
	    
	    Scene s = new Scene(vbox, 600, 200);
	    a.setScene(s);
	    a.setTitle(list.getValue());
	    b.setOnAction(e -> {
	        try {
	        	
	            updateStartDate(id, name.getValue());
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }
	        a.close();
	    });

	    a.showAndWait();
	}

	else if(list.getValue().equals("Birth Date")) 
	{
	    Stage a = new Stage();
	    a.initModality(Modality.APPLICATION_MODAL);

	    Label old = new Label("Enter the New Birth Date:");
	    old.setStyle("-fx-font-weight: bold;");
	    
	    DatePicker name = new DatePicker();
	    name.setStyle("-fx-min-width: 200px;");
	    name.setPromptText(list.getValue());
	    
	    Button b = new Button("Update");
	    b.setDefaultButton(true);
	    b.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");
	    
	    HBox hbox = new HBox(10);
	    hbox.setAlignment(Pos.CENTER_LEFT);
	    hbox.setPadding(new Insets(15, 30, 15, 30));
	    hbox.getChildren().addAll(old, name);
	    
	    VBox vbox = new VBox(10);
	    vbox.setAlignment(Pos.CENTER);
	    vbox.setPadding(new Insets(30, 30, 30, 30));
	    vbox.setStyle("-fx-background-color: #f0f0f0;");
	    vbox.getChildren().addAll(hbox, b);
	    
	    Scene s = new Scene(vbox, 600, 200);
	    a.setScene(s);
	    a.setTitle(list.getValue());
	    b.setOnAction(e -> {
	        try {
	            updateBirthDate(id, name.getValue());
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }
	        a.close();
	    });

	    a.showAndWait();
	}

	else if(list.getValue().equals("Start Salary")) 
	{
	    Stage a = new Stage();
	    a.initModality(Modality.APPLICATION_MODAL);

	    Label old = new Label("Enter the New Start Salary:");
	    old.setStyle("-fx-font-weight: bold;");
	    
	    TextField name = new TextField();
	    name.setStyle("-fx-min-width: 200px;");
	    name.setPromptText(list.getValue());
	    
	    Button b = new Button("Update");
	    b.setDefaultButton(true);
	    b.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");
	    
	    HBox hbox = new HBox(10);
	    hbox.setAlignment(Pos.CENTER_LEFT);
	    hbox.setPadding(new Insets(15, 30, 15, 30));
	    hbox.getChildren().addAll(old, name);
	    
	    VBox vbox = new VBox(10);
	    vbox.setAlignment(Pos.CENTER);
	    vbox.setPadding(new Insets(30, 30, 30, 30));
	    vbox.setStyle("-fx-background-color: #f0f0f0;");
	    vbox.getChildren().addAll(hbox, b);
	    
	    Scene s = new Scene(vbox, 600, 200);
	    a.setScene(s);
	    a.setTitle(list.getValue());
	    b.setOnAction(e -> {
	        try {
	        	if (name.getText() == null || name.getText().trim().isEmpty()) {
	        	    Alert alert = new Alert(Alert.AlertType.WARNING);
	        	    alert.setTitle("Input Error");
	        	    alert.setHeaderText(null);
	        	    alert.setContentText("Please enter a valid value!");
	        	    alert.showAndWait();
	        	    return;  // exit the current method or logic
	        	}
	            updateStartSalary(id, name.getText());
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }
	        a.close();
	    });

	    a.showAndWait();
	}

	else if(list.getValue().equals("Social Security Number")) 
	{
	    Stage a = new Stage();
	    a.initModality(Modality.APPLICATION_MODAL);

	    Label old = new Label("Enter the New SSN:");
	    old.setStyle("-fx-font-weight: bold;");
	    
	    PasswordField name = new PasswordField();
	    name.setStyle("-fx-min-width: 200px;");
	    name.setPromptText(list.getValue());
	    
	    Button b = new Button("Update");
	    b.setDefaultButton(true);
	    b.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");
	    
	    HBox hbox = new HBox(10);
	    hbox.setAlignment(Pos.CENTER_LEFT);
	    hbox.setPadding(new Insets(15, 30, 15, 30));
	    hbox.getChildren().addAll(old, name);
	    
	    VBox vbox = new VBox(10);
	    vbox.setAlignment(Pos.CENTER);
	    vbox.setPadding(new Insets(30, 30, 30, 30));
	    vbox.setStyle("-fx-background-color: #f0f0f0;");
	    vbox.getChildren().addAll(hbox, b);
	    
	    Scene s = new Scene(vbox, 600, 200);
	    a.setScene(s);
	    a.setTitle(list.getValue());
	    b.setOnAction(e -> {
	        try {
	        	if (name.getText() == null || name.getText().trim().isEmpty()) {
	        	    Alert alert = new Alert(Alert.AlertType.WARNING);
	        	    alert.setTitle("Input Error");
	        	    alert.setHeaderText(null);
	        	    alert.setContentText("Please enter a valid value!");
	        	    alert.showAndWait();
	        	    return;  // exit the current method or logic
	        	}
	            updateSSN(id, name.getText());
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }
	        a.close();
	    });

	    a.showAndWait();
	}

	else if(list.getValue().equals("Phone Number")) 
	{
	    Stage a = new Stage();
	    a.initModality(Modality.APPLICATION_MODAL);

	    Label old = new Label("Enter the New Phone Number:");
	    old.setStyle("-fx-font-weight: bold;");
	    
	    TextField name = new TextField();
	    name.setStyle("-fx-min-width: 200px;");
	    name.setPromptText(list.getValue());
	    
	    Button b = new Button("Update");
	    b.setDefaultButton(true);
	    b.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");
	    
	    HBox hbox = new HBox(10);
	    hbox.setAlignment(Pos.CENTER_LEFT);
	    hbox.setPadding(new Insets(15, 30, 15, 30));
	    hbox.getChildren().addAll(old, name);
	    
	    VBox vbox = new VBox(10);
	    vbox.setAlignment(Pos.CENTER);
	    vbox.setPadding(new Insets(30, 30, 30, 30));
	    vbox.setStyle("-fx-background-color: #f0f0f0;");
	    vbox.getChildren().addAll(hbox, b);
	    
	    Scene s = new Scene(vbox, 600, 200);
	    a.setScene(s);
	    a.setTitle(list.getValue());
	    b.setOnAction(e -> {
	        try {
	        	if (name.getText() == null || name.getText().trim().isEmpty()) {
	        	    Alert alert = new Alert(Alert.AlertType.WARNING);
	        	    alert.setTitle("Input Error");
	        	    alert.setHeaderText(null);
	        	    alert.setContentText("Please enter a valid value!");
	        	    alert.showAndWait();
	        	    return;  // exit the current method or logic
	        	}
	            updatePhoneNumber(id, name.getText());
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }
	        a.close();
	    });

	    a.showAndWait();
	}

	else if(list.getValue().equals("Emergency Contact Phone")) 
	{
	    Stage a = new Stage();
	    a.initModality(Modality.APPLICATION_MODAL);

	    Label old = new Label("Enter the New Phone of Person to Call in an Emergency:");
	    old.setStyle("-fx-font-weight: bold;");
	    
	    TextField name = new TextField();
	    name.setStyle("-fx-min-width: 200px;");
	    name.setPromptText("Emergency Contact Phone");
	    
	    Button b = new Button("Update");
	    b.setDefaultButton(true);
	    b.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");
	    
	    HBox hbox = new HBox(10);
	    hbox.setAlignment(Pos.CENTER_LEFT);
	    hbox.setPadding(new Insets(15, 30, 15, 30));
	    hbox.getChildren().addAll(old, name);
	    
	    VBox vbox = new VBox(10);
	    vbox.setAlignment(Pos.CENTER);
	    vbox.setPadding(new Insets(30, 30, 30, 30));
	    vbox.setStyle("-fx-background-color: #f0f0f0;");
	    vbox.getChildren().addAll(hbox, b);
	    
	    Scene s = new Scene(vbox, 600, 200); // Increased width due to longer label text
	    a.setScene(s);
	    a.setTitle(list.getValue());
	    b.setOnAction(e -> {
	        try {
	        	if (name.getText() == null || name.getText().trim().isEmpty()) {
	        	    Alert alert = new Alert(Alert.AlertType.WARNING);
	        	    alert.setTitle("Input Error");
	        	    alert.setHeaderText(null);
	        	    alert.setContentText("Please enter a valid value!");
	        	    alert.showAndWait();
	        	    return;  // exit the current method or logic
	        	}
	            updatePhoneNumberEmergency(id, name.getText());
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }
	        a.close();
	    });

	    a.showAndWait();
	}

	else if(list.getValue().equals("Emergency Contact Name")) 
	{
	    Stage a = new Stage();
	    a.initModality(Modality.APPLICATION_MODAL);

	    Label old = new Label("Enter the New Name of Person to Call in an Emergency:");
	    old.setStyle("-fx-font-weight: bold;");
	    
	    TextField name = new TextField();
	    name.setStyle("-fx-min-width: 200px;");
	    name.setPromptText("Emergency Contact Name");
	    
	    Button b = new Button("Update");
	    b.setDefaultButton(true);
	    b.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");
	    
	    HBox hbox = new HBox(10);
	    hbox.setAlignment(Pos.CENTER_LEFT);
	    hbox.setPadding(new Insets(15, 30, 15, 30));
	    hbox.getChildren().addAll(old, name);
	    
	    VBox vbox = new VBox(10);
	    vbox.setAlignment(Pos.CENTER);
	    vbox.setPadding(new Insets(30, 30, 30, 30));
	    vbox.setStyle("-fx-background-color: #f0f0f0;");
	    vbox.getChildren().addAll(hbox, b);
	    
	    Scene s = new Scene(vbox, 600, 200); // Adjusted width to accommodate the longer label text
	    a.setScene(s);
	    a.setTitle(list.getValue());
	    b.setOnAction(e -> {
	        try {
	        	if (name.getText() == null || name.getText().trim().isEmpty()) {
	        	    Alert alert = new Alert(Alert.AlertType.WARNING);
	        	    alert.setTitle("Input Error");
	        	    alert.setHeaderText(null);
	        	    alert.setContentText("Please enter a valid value!");
	        	    alert.showAndWait();
	        	    return;  // exit the current method or logic
	        	}
	            updateNameEmergency(id, name.getText());
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }
	        a.close();
	    });

	    a.showAndWait();
	}

	else if(list.getValue().equals("Employee Contract Signed(Y/N)")) 
	{
	    Stage a = new Stage();
	    a.initModality(Modality.APPLICATION_MODAL);

	    Label old = new Label("Is the employee contract signed?");
	    old.setStyle("-fx-font-weight: bold;");

	    CheckBox name = new CheckBox("Signed");
	    name.setStyle("-fx-min-width: 200px;");
	    
	    Button b = new Button("Update");
	    b.setDefaultButton(true);
	    b.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");
	    
	    HBox hbox = new HBox(20);  // Increased spacing to 20 for better spacing between label and checkbox
	    hbox.setAlignment(Pos.CENTER_LEFT);
	    hbox.setPadding(new Insets(15, 30, 15, 30));
	    hbox.getChildren().addAll(old, name);
	    
	    VBox vbox = new VBox(10);
	    vbox.setAlignment(Pos.CENTER);
	    vbox.setPadding(new Insets(30, 30, 30, 30));
	    vbox.setStyle("-fx-background-color: #f0f0f0;");
	    vbox.getChildren().addAll(hbox, b);
	    
	    Scene s = new Scene(vbox, 650, 150);  // Adjusted dimensions to better fit the content
	    a.setScene(s);
	    a.setTitle(list.getValue());
	    b.setOnAction(e -> {
	        try {
	            updateECS(id, name.isSelected());
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }
	        a.close();
	    });

	    a.showAndWait();
	}
	
	}

public static void updateECS(int id, boolean name) throws FileNotFoundException, IOException
{
	char a = 'N';
	if(name == true)
	{
		a='Y';
	}
	 String sql = "Update XYZemployees set contract_signed = ? where id = ? ";
	 System.out.println(id);
	 System.out.println(a);
        try (Connection conn = connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
        	pstmt.setString(1, String.valueOf(a)); 
            pstmt.setInt(2, id); 
            pstmt.executeUpdate();
            UpdateSuccess();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
}
	public static void updateFirstName(int id, String name) throws FileNotFoundException, IOException
	{
		 String sql = "Update XYZemployees set first_name = ? where id = ? ";
		 
	        try (Connection conn = connect();
	                PreparedStatement pstmt = conn.prepareStatement(sql)) {
	            pstmt.setInt(2, id);
	            pstmt.setString(1, name);
	            pstmt.executeUpdate();
	            UpdateSuccess();
	        } catch (SQLException e) {
	            System.out.println(e.getMessage());
	        }
	}
	public static void updateLastName(int id, String name) throws FileNotFoundException, IOException
	{
		 String sql = "Update XYZemployees set last_name = ? where id = ? ";
		 
	        try (Connection conn = connect();
	                PreparedStatement pstmt = conn.prepareStatement(sql)) {
	            pstmt.setInt(2, id);
	            pstmt.setString(1, name);
	            pstmt.executeUpdate();
	            UpdateSuccess();
	        } catch (SQLException e) {
	            System.out.println(e.getMessage());
	        }
	}
	public static void updateStartDate(int id, LocalDate name) throws FileNotFoundException, IOException
	{
		 String sql = "Update XYZemployees set start_date = ? where id = ? ";
		 
	        try (Connection conn = connect();
	                PreparedStatement pstmt = conn.prepareStatement(sql)) {
	            pstmt.setInt(2, id);
	            pstmt.setDate(1, java.sql.Date.valueOf(name));
	            pstmt.executeUpdate();
	            UpdateSuccess();
	        } catch (SQLException e) {
	            System.out.println(e.getMessage());
	        }
	}
	public static void updateBirthDate(int id, LocalDate name) throws FileNotFoundException, IOException
	{
		 String sql = "Update XYZemployees set birth_date = ? where id = ? ";
		 
	        try (Connection conn = connect();
	                PreparedStatement pstmt = conn.prepareStatement(sql)) {
	            pstmt.setInt(2, id);
	            pstmt.setDate(1, java.sql.Date.valueOf(name));
	            pstmt.executeUpdate();
	            UpdateSuccess();
	        } catch (SQLException e) {
	            System.out.println(e.getMessage());
	        }
	}
	public static void updateStartSalary(int id, String name) throws FileNotFoundException, IOException
	{
		double number = Double.parseDouble(name);
		
		 String sql = "Update XYZemployees set start_salary = ? where id = ? ";
		 
	        try (Connection conn = connect();
	                PreparedStatement pstmt = conn.prepareStatement(sql)) {
	            pstmt.setInt(2, id);
	            pstmt.setDouble(1,number);
	            pstmt.executeUpdate();
	            UpdateSuccess();
	        } catch (SQLException e) {
	            System.out.println(e.getMessage());
	        }
	}
	public static void updateSSN(int id, String name) throws FileNotFoundException, IOException
	{
		long number = Long.parseLong(name);
		 String sql = "Update XYZemployees set ssn = ? where id = ?";
		 
	        try (Connection conn = connect();
	                PreparedStatement pstmt = conn.prepareStatement(sql)) {
	            pstmt.setInt(2, id);
	            pstmt.setLong(1, number);
	            pstmt.executeUpdate();
	            UpdateSuccess();
	        } catch (SQLException e) {
	            System.out.println(e.getMessage());
	        }
	}
	public static void updatePhoneNumber(int id, String name) throws FileNotFoundException, IOException
	{
		long number = Long.parseLong(name);
		 String sql = "Update XYZemployees set phone_number = ? where id = ?";
		 
	        try (Connection conn = connect();
	                PreparedStatement pstmt = conn.prepareStatement(sql)) {
	            pstmt.setInt(2, id);
	            pstmt.setLong(1, number);
	            pstmt.executeUpdate();
	            UpdateSuccess();
	        } catch (SQLException e) {
	            System.out.println(e.getMessage());
	        }
	}
	public static void updatePhoneNumberEmergency(int id, String name) throws FileNotFoundException, IOException
	{
		long number = Long.parseLong(name);
		 String sql = "Update XYZemployees set emg_contact_number = ? where id = ?";
		 
	        try (Connection conn = connect();
	                PreparedStatement pstmt = conn.prepareStatement(sql)) {
	            pstmt.setInt(2, id);
	            pstmt.setLong(1, number);
	            pstmt.executeUpdate();
	            UpdateSuccess();
	        } catch (SQLException e) {
	            System.out.println(e.getMessage());
	        }
	}
	public static void updateNameEmergency(int id, String name) throws FileNotFoundException, IOException
	{
		 String sql = "Update XYZemployees set emg_contact_name = ? where id = ?";
		 
	        try (Connection conn = connect();
	                PreparedStatement pstmt = conn.prepareStatement(sql)) {
	            pstmt.setInt(2, id);
	            pstmt.setString(1, name);
	            pstmt.executeUpdate();
	            UpdateSuccess();
	        } catch (SQLException e) {
	            System.out.println(e.getMessage());
	        }
	}
	
	public static void UpdateSuccess()
	{
		Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Update Information");
        alert.setHeaderText(null);
        alert.setContentText("Update successful!");

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
	

   
}

