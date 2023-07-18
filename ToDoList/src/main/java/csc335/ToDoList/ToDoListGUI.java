/**
 * @author Cooper Harris
 * 
 * This class shows a serializable To Do List
 * in a text area that can be edited and saved 
 * to myTodos.ser. 
 * 
 */
package csc335.ToDoList;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Optional;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class ToDoListGUI extends Application {
	private ArrayList<String> toDoList;
	private ObservableList<String> obs;
	private ListView<String> listView;
//	private TextArea listArea;
	private TextField addField, changeField;
	private Button addToDo, changePriority, delete;
	private BorderPane pane;
	private GridPane buttonsPane;

	public static void main(String[] args) {
		launch();
	}

	@Override
	public void start(Stage stage) {
		// create the alert to ask if the user wants to read from
		// a saved file
		Alert openAlert = new Alert(AlertType.CONFIRMATION);
		openAlert.setTitle("ToDo List");
		openAlert.setHeaderText("Click cancel to start with an empty list");
		openAlert.setContentText("Start with a saved To Do List?");

		Optional<ButtonType> openResult = openAlert.showAndWait();

		if (openResult.get() == ButtonType.OK) {
			readSavedList();
		} else {
			emptyList();
		}

		setUpPane();
		registerHandlers();

		// alert to ask if the user wants to save the current to do list
		stage.setOnCloseRequest(event -> {
			Alert closeAlert = new Alert(AlertType.CONFIRMATION);
			closeAlert.setTitle("Close Confirmation");
			closeAlert.setHeaderText("Click cancel to exit without saving");
			closeAlert.setContentText("To save the current ToDo list click OK");

			Optional<ButtonType> result = closeAlert.showAndWait();

			if (result.get() == ButtonType.OK) {
				saveList();
			}

			Platform.exit();
			System.exit(0);
		});

		var scene = new Scene(pane, 320, 400);
		stage.setTitle("To Do List");
		stage.setScene(scene);

		stage.show();
	}

	/**
	 * Creates the layout for the gui that the to do list is shown in
	 */
	private void setUpPane() {
		pane = new BorderPane();
		pane.setPadding(new Insets(10, 5, 20, 5));

		// making the buttons
		addToDo = new Button("Add To Do");
		changePriority = new Button("Change Priority");
		delete = new Button("Delete Selected");
		// initializing the text fields
		addField = new TextField();
		changeField = new TextField();

		buttonsPane = new GridPane();

		buttonsPane.setHgap(10);
		buttonsPane.setVgap(10);
		// putting the buttons and text fields into the pane
		buttonsPane.add(addField, 3, 0);
		buttonsPane.add(addToDo, 4, 0);

		buttonsPane.add(changeField, 3, 1);
		buttonsPane.add(changePriority, 4, 1);
		// putting the delete button into its own hbox so it can be
		// centered more easily
		HBox hBox = new HBox(delete);

		hBox.setPadding(new Insets(0, 0, 0, 70));
		buttonsPane.add(hBox, 3, 3);

		buttonsPane.setPadding(new Insets(20, 0, 0, 0));

		pane.setCenter(listView);
		pane.setBottom(buttonsPane);
	}

	/**
	 * Creates the event handlers for the buttons on the todo list
	 */
	private void registerHandlers() {
		// Add the string from the text field
		// to the front of the list
		addToDo.setOnAction(event -> {
			String s = addField.getText().trim();

			if (!s.isEmpty()) {
				obs.add(0, s);
			}

			addField.clear();
		});
		// changes the selected item to the index
		// indicated in the textField
		// uses the actual indices of the observable list so it is 0 based
		changePriority.setOnAction(event -> {
			String s = changeField.getText().trim();
			changeField.clear();
			if (isInteger(s)) {
				int newPriority = Integer.parseInt(s);
				if (newPriority >= 0 && newPriority < obs.size()) {
					int oldPriority = listView.getSelectionModel().getSelectedIndex();
					String selectedItem = obs.get(oldPriority);

					obs.remove(oldPriority);
					obs.add(newPriority, selectedItem);
					listView.getSelectionModel().select(newPriority);
				}
			}
		});
		// deletes the selected item from the todo list
		delete.setOnAction(event -> {
			int selectedIndex = listView.getSelectionModel().getSelectedIndex();
			if (selectedIndex > -1) {
				obs.remove(selectedIndex);
			}
		});
	}

	/**
	 * Checks if the given string can be an integer
	 * 
	 * @param s - the string to check
	 * @return true if the parseInt works on the string
	 */
	private boolean isInteger(String s) {
		try {
			Integer.parseInt(s);
		} catch (NumberFormatException e) {
			return false;
		} catch (NullPointerException e) {
			return false;
		}
		// only got here if we didn't return false
		return true;
	}

	/**
	 * creates an empty list to show in the textArea
	 */
	private void emptyList() {
		toDoList = new ArrayList<>();

		obs = FXCollections.observableArrayList(toDoList);
		listView = new ListView<>();
		listView.setItems(obs);
	}

	/**
	 * Writes the contents of the textArea to myTodos.ser
	 */
	private void saveList() {
		toDoList.clear();

		for (int i = 0; i < obs.size(); i++) {
			toDoList.add(obs.get(i));
		}

		try {
			FileOutputStream bytesToDisk = new FileOutputStream("myTODOS.ser");
			ObjectOutputStream outFile = new ObjectOutputStream(bytesToDisk);

			outFile.writeObject(toDoList);

			outFile.close();

		} catch (IOException ioe) {
			System.out.println("Write failed");
		}
	}

	/**
	 * Reads the array list from myTodos.ser and loads it into the array list
	 * displayed in the text area
	 */
	private void readSavedList() {

		try {
			FileInputStream bytesFromDisk = new FileInputStream("myTODOS.ser");
			ObjectInputStream inFile = new ObjectInputStream(bytesFromDisk);

			toDoList = (ArrayList<String>) inFile.readObject();

			obs = FXCollections.observableArrayList(toDoList);

			listView = new ListView<>();
			listView.setItems(obs);

			inFile.close();

		} catch (IOException ioe) {
			System.out.println("read failed");
		} catch (ClassNotFoundException e) {
			System.out.println("Class cast exception");
			e.printStackTrace();
		}
	}

}