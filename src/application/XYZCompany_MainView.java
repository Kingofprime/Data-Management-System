package application;

import java.io.FileNotFoundException;
import java.util.stream.Stream;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.stage.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.*;

public class XYZCompany_MainView extends Application{

	public static void main(String[] args){
		launch(args);
	}

	@Override
	public void start(Stage arg0) throws Exception {
		
		// Create and edit primary stage
		// Create and edit primary stage
		Stage primaryStage = new Stage();
		primaryStage.setTitle("XYZCompany Employee Database");

		// Define UI components
		Label greeting = new Label("Welcome to XYZCompany Employee Database");
		Label question = new Label("What would you like to do?");
		Button toCreate = new Button("Create");
		Button toRead = new Button("Find");
		Button toUpdate = new Button("Update");
		Button toDelete = new Button("Delete");

		// Enhance the appearance of the UI components with CSS
		String labelStyle = "-fx-font: 20 Arial; -fx-font-weight: bold;";
		greeting.setStyle(labelStyle);
		question.setStyle(labelStyle);

		String buttonStyle = "-fx-min-width: 150px; -fx-min-height: 75px; -fx-font: 20 Arial; -fx-font-weight: bold; -fx-background-color: #3498db; -fx-text-fill: #ffffff;";
		Stream.of(toCreate, toRead, toUpdate, toDelete).forEach(button -> {
		    button.setStyle(buttonStyle);
		    button.setAlignment(Pos.CENTER);  // Align the text of the button to the center
		});

		// Organize buttons into groups for better layout
		VBox leftButtonGroup = new VBox(20, toCreate, toUpdate);
		VBox rightButtonGroup = new VBox(20, toRead, toDelete);
		leftButtonGroup.setAlignment(Pos.CENTER);
		rightButtonGroup.setAlignment(Pos.CENTER);

		HBox buttonGroupCenter = new HBox(50, leftButtonGroup, rightButtonGroup);
		buttonGroupCenter.setAlignment(Pos.CENTER);

		// Define the main layout
		VBox mainLayout = new VBox(30, greeting, question, buttonGroupCenter);
		mainLayout.setStyle("-fx-padding: 30; -fx-background-color: #ecf0f1;");
		mainLayout.setAlignment(Pos.CENTER);

		// Create the main scene
		Scene XYZMain = new Scene(mainLayout, 700, 300);

		// Action handlers for buttons
		toCreate.setOnAction(e -> XYZCompany_CreateView.createView(primaryStage, XYZMain));
		toUpdate.setOnAction(u -> XYZCompany_UpdateView.updateView(primaryStage, XYZMain));
		toDelete.setOnAction(d -> {
		    try {
		        XYZCompany_Delete.deleteView(primaryStage, XYZMain);
		    } catch (FileNotFoundException e1) {
		        e1.printStackTrace();
		    }
		});
		toRead.setOnAction(r -> {
		    try {
		        XYZCompany_ReadView.readView(primaryStage, XYZMain);
		    } catch (FileNotFoundException e1) {
		        e1.printStackTrace();
		    }
		});

		// Display the scene
		primaryStage.setScene(XYZMain);
		primaryStage.show();

	}

}
