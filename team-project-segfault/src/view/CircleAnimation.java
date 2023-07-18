package view;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

public class CircleAnimation extends Application {
	private double x, y;

    @Override
    public void start(Stage primaryStage) {
        Canvas canvas = new Canvas(400, 400);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        StackPane root = new StackPane(canvas);
        primaryStage.setScene(new Scene(root));

        x = 0.0;
        y = 0.0;
        gc.setFill(Color.BLACK);
        gc.fillOval(x, y, 20, 20);

        Timeline timeline = new Timeline(
                new KeyFrame(Duration.millis(50), event -> {
                    gc.setFill(Color.WHITE);
                    gc.fillRect(x, y, 20, 20);
                }),
                new KeyFrame(Duration.millis(50), event -> {
                	if (x < 380) {
                		x += 5.0;
                        y += 5.0;
                	}
                	gc.setFill(Color.BLACK);
                    gc.fillOval(x, y, 20, 20);
                })
        );
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
