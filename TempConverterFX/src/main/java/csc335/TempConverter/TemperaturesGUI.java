// Cooper Harris

package csc335.TempConverter;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.TemperatureConverter;

//

/**
 * A program that displays a pop up where the user can enter a Fahrenheit
 * temperature to be Converted to Celsius or the other way around
 * 
 * Programmer: Cooper Harris
 */
public class TemperaturesGUI extends Application {

	TextField cField = new TextField();
	TextField fField = new TextField();

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) {
		registerHandlers();

		stage.setTitle("CtoF FtoC");

		GridPane pane = new GridPane();
		pane.setHgap(10);
		pane.setVgap(10);

		Label celsius = new Label("Celsius");
		Label fahrenheit = new Label("Fahrenheit");
		pane.add(celsius, 1, 1);
		pane.add(fahrenheit, 1, 2);

		pane.add(cField, 2, 1);
		pane.add(fField, 2, 2);

		var scene = new Scene(pane, 270, 90);

		stage.setScene(scene);
		stage.show();
	}

	private void registerHandlers() {
		cField.setOnAction(new CelHandler());
		fField.setOnAction(new FarHandler());
	}

	private class CelHandler implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent event) {
			double cTemp = Double.parseDouble(cField.getText());
			double fTemp = TemperatureConverter.CtoF(cTemp);
			fField.setText("" + fTemp);
		}

	}

	private class FarHandler implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent event) {
			double fTemp = Double.parseDouble(fField.getText());
			double cTemp = TemperatureConverter.FtoC(fTemp);
			cField.setText("" + cTemp);
		}

	}
}