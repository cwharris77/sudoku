package view;

import java.util.ArrayList;
import java.util.Optional;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Game;

/**
 * this is the gui for the sudoku game it will show a menu that you 
 * can choose your sudoku game size and difficulty and there 
 * will be two set of button one for pencil markes marked in yellow
 * and one in no color that is the final guess you can play the game
 * as usuall and check if the game is won or not at any time it uses 
 * the model to simulate the game of sudoku
 * 
 * Authors: Bryan Bielawa, Carissa Burnham, Cooper Harris, Drew Marien
 *
 *
 */
public class BoardGUI extends Application {

	private int boardSize, curDifficulty = 3, curSize;
	private final int tileSize = 70;
	private int innerGridSize, selectedX = -1, selectedY = -1;
	private Game game;
	private GridPane outerGridPane;
	private Pane lastClickedPane = null;
	private Rectangle lastClickedRect = null;
	private BorderPane everythingPane;
	private MenuBar menuBar;
	private Menu difficultyMenu, sizeMenu;
	private MenuItem easy, medium, hard, fourByFour, sixBySix, nineByNine;
	private HBox row1, row2, row3, bottomButtonsBox;
	private Button one, two, three, four, five, six, seven, eight, nine, clear, newGame, checkBoard;
	private VBox rightSideButtons;

	@Override
	public void start(Stage stage) {
		game = new Game(9, 3);

		boardSize = (int) Math.sqrt(game.getSize());
		innerGridSize = boardSize;

		if (game.getSize() == 6) {
			boardSize = 3;
			innerGridSize = 2;
		}

		outerGridPane = new GridPane();
		outerGridPane.setPadding(new Insets(10));

		createMenu();
		registerHandlers();
		initializeButtons();
		initializePencilmarks();
		initializeClearButton();
		drawBoard();

		bottomButtonsBox = new HBox(newGame, clear, checkBoard);
		bottomButtonsBox.setSpacing(10);
		bottomButtonsBox.setAlignment(Pos.CENTER);
		bottomButtonsBox.setPadding(new Insets(10));

		everythingPane = new BorderPane();
		everythingPane.setLeft(outerGridPane);
		everythingPane.setTop(menuBar);
		everythingPane.setRight(rightSideButtons);

		everythingPane.setBottom(bottomButtonsBox);

		Scene scene = new Scene(everythingPane, 1000, 800);

		stage.setScene(scene);
		stage.setTitle("Sudoku Board");
		stage.show();
	}

	/*
	 * This function initializes the menuBar and all its sub menus as well as the
	 * buttons that go on the bottom of the board
	 */
	private void createMenu() {
		Circle c = new Circle(40);
		newGame = new Button("New Game");
		newGame.setShape(c);
		newGame.setStyle("-fx-font-size: 20px");

		checkBoard = new Button("Check Board");
		checkBoard.setShape(c);
		checkBoard.setStyle("-fx-font-size: 20px");

		menuBar = new MenuBar();
		difficultyMenu = new Menu("Difficulty");
		sizeMenu = new Menu("Board Size");

		easy = new MenuItem("Easy");
		medium = new MenuItem("Medium");
		hard = new MenuItem("Hard");

		fourByFour = new MenuItem("4 X 4");
		sixBySix = new MenuItem("6 X 6");
		nineByNine = new MenuItem("9 X 9");

		difficultyMenu.getItems().addAll(easy, medium, hard);
		sizeMenu.getItems().addAll(fourByFour, sixBySix, nineByNine);

		menuBar.getMenus().addAll(difficultyMenu, sizeMenu);

	}

	/*
	 * This method adds event handlers for the menuItems for difficulty and board
	 * size
	 */
	private void registerHandlers() {
		curSize = game.getSize();

		easy.setOnAction(new newGameHandler(curSize, 3));
		medium.setOnAction(new newGameHandler(curSize, 2));
		hard.setOnAction(new newGameHandler(curSize, 1));

		fourByFour.setOnAction(new newGameHandler(4, curDifficulty));
		sixBySix.setOnAction(new newGameHandler(6, curDifficulty));
		nineByNine.setOnAction(new newGameHandler(9, curDifficulty));

		newGame.setOnAction(new newGameHandler(curSize, curDifficulty));
		checkBoard.setOnAction(new victoryCheckHandler());

	}

	/*
	 * This method handles the creation of the game board. The outer gridPane holds
	 * another gridPane as each of its Nodes and in those gridPanes nodes are a Pane
	 * which holds a clickable rectangle and a Text object
	 */
	private void drawBoard() {
		// outer rows
		for (int i = 0; i < boardSize; i++) {
			// outer columns
			for (int j = 0; j < innerGridSize; j++) {
				// Make a new gridPane that is a node in the outer grid pane
				GridPane innerGridPane = new GridPane();
				innerGridPane.setStyle("-fx-border-color: black; -fx-border-width: 2px");
				// inner grid rows
				for (int k = 0; k < innerGridSize; k++) {
					// inner grid columns
					for (int l = 0; l < boardSize; l++) {
						// Make a new rectangle that will show a fill color and be clickable
						Rectangle rectangle = new Rectangle(tileSize, tileSize, Color.WHITE);
						rectangle.setStroke(Color.BLACK);
						
						Text text = new Text("");
						
						// This will hold the text and rectangle objects in a container
						Pane pane = new Pane(rectangle, text);

						// This code converts the inner grid coordinates to coordinates as if the whole thing 
						// was one big 2D array
						int curVal;
						// Special case for six by six because the outer and inner grid sizes are different
						if (curSize == 6) {
							curVal = game.getCellValue(i * innerGridSize + k, j * boardSize + l);
						} else {
							curVal = game.getCellValue(i * innerGridSize + k, j * innerGridSize + l);
						}
						
						/*
						 * Set the value of text to the generated value on the game board made by the board class
						 * and relocate it to be centered
						 */
						if (curVal != 0) {
							text.setText("" + curVal);
							text.setStyle("-fx-font-weight: bold; -fx-font-size: 40px");
							text.setDisable(true);
						}

						double x = rectangle.getX() + (rectangle.getWidth() - text.getBoundsInLocal().getWidth()) / 2;
						double y = rectangle.getY() + (rectangle.getHeight() + text.getBoundsInLocal().getHeight()) / 2;
						text.relocate(x, y);
						
						// Add an event handler for each pane
						pane.setOnMouseClicked(event -> {
							lastClickedPane = pane;
						});

						innerGridPane.add(pane, l, k);
						
						// Add a click handler for the rectangle so it changes to the appropriate color
						// when clicked 
						rectangle.setOnMouseClicked(event -> {
							clearFill();
							lastClickedRect = rectangle;
							// Converting from grid inside a grid to big grid coordinates
							int row = outerGridPane.getRowIndex(innerGridPane) * innerGridSize
									+ innerGridPane.getRowIndex(pane);
							int col = outerGridPane.getColumnIndex(innerGridPane) * innerGridSize
									+ innerGridPane.getColumnIndex(pane);

							selectedX = row;
							selectedY = col;

							if (!game.isCellPencilMark(selectedX, selectedY)) {
								rectangle.setFill(Color.ALICEBLUE);
							} else {
								rectangle.setFill(Color.rgb(255, 199, 102, 0.8));
							}

						});
						
						// Add an event handler for the text so if the user clicks on it the square
						// is still highlighted
						text.setOnMouseClicked(event -> {
							Parent parent = text.getParent();

							if (parent != null) {
								parent = (Pane) parent;
							}

							for (Node tile : parent.getChildrenUnmodifiable()) {
								if (tile instanceof Rectangle) {
									clearFill();
									lastClickedRect = (Rectangle) tile;

									int row2 = outerGridPane.getRowIndex(innerGridPane) * innerGridSize
											+ innerGridPane.getRowIndex(pane);
									int col2 = outerGridPane.getColumnIndex(innerGridPane) * innerGridSize
											+ innerGridPane.getColumnIndex(pane);

									selectedX = row2;
									selectedY = col2;

									if (!game.isCellPencilMark(selectedX, selectedY)) {
										rectangle.setFill(Color.ALICEBLUE);
									} else {
										rectangle.setFill(Color.rgb(255, 199, 102, 0.8));
									}
								}
							}

						});
					}
				}

				outerGridPane.add(innerGridPane, j, i);
			}
		}
	}
	
	/*
	 * This method loops through all cells on the board and clears their fill to give the 
	 * effect of whatever square the user clicked on being highlighted
	 */
	private void clearFill() {
		// Loop through the nodes
		for (Node GridPane : outerGridPane.getChildren()) {
			for (Node pane : ((GridPane) GridPane).getChildren()) {
				for (Node tile : ((Pane) pane).getChildren()) {
					// Convert the cooridinates
					int row = outerGridPane.getRowIndex(GridPane) * innerGridSize
							+ ((GridPane) GridPane).getRowIndex(pane);
					int col = outerGridPane.getColumnIndex(GridPane) * innerGridSize
							+ ((GridPane) GridPane).getColumnIndex(pane);
					// If this is the tile that has a fill and it isn't a pencil mark
					if (tile == lastClickedRect) {
						if (!game.isCellPencilMark(row, col)) {
							((Rectangle) tile).setFill(Color.WHITE);
						} else {
							((Rectangle) tile).setFill(Color.rgb(255, 240, 102, 0.8));
						}

					}
				}
			}
		}

	}
	
	/* 
	 * This method initializes the buttons that represent the numbers a user can enter to 
	 * solve the game. It sets the buttons to circles and adds them to a VBox that will be on the
	 * right side of the Border Pane
	 */
	private void initializeButtons() {
		BorderPane buttonsPane = new BorderPane();
		buttonsPane.setMaxSize(200, 100);

		one = new Button("1");
		two = new Button("2");
		three = new Button("3");

		four = new Button("4");
		five = new Button("5");
		six = new Button("6");

		seven = new Button("7");
		eight = new Button("8");
		nine = new Button("9");

		Button[] buttonsArray = { one, two, three, four, five, six, seven, eight, nine };

		for (int i = 0; i < buttonsArray.length; i++) {
			Circle c = new Circle(70);
			buttonsArray[i].setShape(c);
			buttonsArray[i].setMinWidth(70);
			buttonsArray[i].setMinHeight(70);
			buttonsArray[i].setStyle("-fx-font-size: 20");

			buttonsArray[i].setOnMouseClicked(new MoveHandler());
		}
		
		// When the board changes sizes only the buttons up to the size should be 
		// displayed
		if (game.getSize() > 4) {
			row1 = new HBox(one, two, three);
			row2 = new HBox(four, five, six);
		} else {
			row1 = new HBox(one, two, three, four);
			row2 = new HBox();
		}

		row3 = new HBox(seven, eight, nine);

		Insets insets = new Insets(10);

		row1.setSpacing(5);
		row1.setPadding(insets);

		row2.setSpacing(5);
		row2.setPadding(insets);

		row3.setSpacing(5);
		row3.setPadding(insets);

		buttonsPane.setTop(row1);

		if (game.getSize() > 4) {
			buttonsPane.setCenter(row2);
		}

		if (game.getSize() > 6) {
			buttonsPane.setBottom(row3);
		}

		rightSideButtons = new VBox(buttonsPane);
		rightSideButtons.setSpacing(10);
		rightSideButtons.setAlignment(Pos.TOP_CENTER);

	}
	
	/* 
	 * This method initializes the buttons that represent the pencilMarks a user can enter to 
	 * serve as a placeholder or a guess. It sets the buttons to circles and adds them to a VBox that will be on the
	 * right side of the Border Pane
	 */
	private void initializePencilmarks() {
		BorderPane buttonsPane = new BorderPane();
		buttonsPane.setMaxSize(200, 100);

		one = new Button("1");
		two = new Button("2");
		three = new Button("3");

		four = new Button("4");
		five = new Button("5");
		six = new Button("6");

		seven = new Button("7");
		eight = new Button("8");
		nine = new Button("9");

		Button[] buttonsArray = { one, two, three, four, five, six, seven, eight, nine };

		for (int i = 0; i < buttonsArray.length; i++) {
			Circle c = new Circle(70);
			buttonsArray[i].setShape(c);
			buttonsArray[i].setMinWidth(70);
			buttonsArray[i].setMinHeight(70);
			buttonsArray[i].setStyle("-fx-background-color: rgb(255, 240, 102, 0.8); -fx-font-size: 20");
			buttonsArray[i].setOnMouseClicked(new PencilMarkHandler());
		}

		if (game.getSize() > 4) {
			row1 = new HBox(one, two, three);
			row2 = new HBox(four, five, six);
		} else {
			row1 = new HBox(one, two, three, four);
		}

		row3 = new HBox(seven, eight, nine);

		Insets insets = new Insets(10);

		row1.setSpacing(5);
		row1.setPadding(insets);

		row2.setSpacing(5);
		row2.setPadding(insets);

		row3.setSpacing(5);
		row3.setPadding(insets);

		buttonsPane.setTop(row1);

		if (game.getSize() > 4) {
			buttonsPane.setCenter(row2);
		}

		if (game.getSize() > 6) {
			buttonsPane.setBottom(row3);
		}

		rightSideButtons.getChildren().add(buttonsPane);

	}
	
	/* 
	 * This method initializes the button that will remove a pencil mark or a move
	 * from the tile
	 */
	private void initializeClearButton() {
		clear = new Button("Clear");
		clear.setShape(new Circle(25));
		clear.setStyle("-fx-font-size: 20px");

		clear.setOnMouseClicked(new MoveHandler());
	}
	
	/*
	 * This method creates a confirmation alert that will be used 
	 * to determine if a new game should be started.
	 */
	private Optional<ButtonType> showNewGameAlert() {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setHeaderText("Warning: This action will start a new game!");
		alert.setContentText("Click OK to continue");

		Optional<ButtonType> result = alert.showAndWait();

		return result;
	}
	
	/*
	 * Updates the variable curSize to the passed in size
	 */
	private void updateSize(int newSize) {
		this.curSize = newSize;
	}
	
	/*
	 * Updates the variable curDifficulty to the passed in difficulty
	 */
	private void updateDifficulty(int newDifficulty) {
		this.curDifficulty = newDifficulty;
	}

	public static void main(String[] args) {
		new Game(4);
		launch(args);
	}
	
	/*
	 * This class handles a button click for the buttons that represent a real move on the 
	 * board
	 */
	private class MoveHandler implements EventHandler<MouseEvent> {
		private int value;

		@Override
		public void handle(MouseEvent arg0) {
			if (game == null) {
				return;
			}
			
			if (((Button) arg0.getSource()).getText().trim().equals("Clear")) {
				value = 0;
			} else {
				value = Integer.parseInt(((Button) arg0.getSource()).getText().trim());
			}

			if (selectedX != -1) {
				// communicate with the game class that will update the board
				// and then display the updates
				game.makeMove(selectedX, selectedY, value);

				if (game.isCellPencilMark(selectedX, selectedY)) {
					game.togglePencilMark(selectedX, selectedY);
				}
				updateView();
			}

		}
		
		/*
		 * This method loops through the cells of the board and updates any text or rectangle that
		 * has changed since the last move was made
		 */
		private void updateView() {
			for (Node n : lastClickedPane.getChildren()) {
				if (n instanceof Text && !n.isDisabled()) {
					if (this.value == 0) {
						((Text) n).setText("");
					} else {
						((Text) n).setText("" + this.value);
						n.setStyle("-fx-font-size: 35px");
					}
				}

				if (n instanceof Rectangle) {
					((Rectangle) n).setFill(Color.ALICEBLUE);
				}
			}

		}

	}
	
	/*
	 * This class handles a button click for the buttons that represent a guess on the 
	 * board
	 */
	private class PencilMarkHandler implements EventHandler<MouseEvent> {
		private int value;

		@Override
		public void handle(MouseEvent arg0) {
			if (game == null) {
				return;
			}

			value = Integer.parseInt(((Button) arg0.getSource()).getText().trim());

			if (selectedX != -1) {
				game.makeMove(selectedX, selectedY, value);
				if (!game.isCellPencilMark(selectedX, selectedY)) {
					game.togglePencilMark(selectedX, selectedY);
				}
				updateGridTile();
			}

		}
		
		/*
		 * This method loops through the cells of the board and updates any text or rectangle that
		 * has changed since the last move was made
		 */
		private void updateGridTile() {
			Node rect = lastClickedPane.getChildren().get(0);
			Node text = lastClickedPane.getChildren().get(1);
			// Changing the tiles that had a board generated value is not allowed 
			// so only update the color of the pencilmark if it is a user entered value
			if (!text.isDisabled()) {
				((Text) text).setText("" + this.value);
				text.setStyle("-fx-font-size: 30px");
				((Rectangle) rect).setFill(Color.rgb(255, 199, 102, 0.8));
			} else {
				game.togglePencilMark(selectedX, selectedY);
			}

		}

	}
	
	/*
	 * This class Starts a new game and updates the GUI to reflect this
	 */
	private class newGameHandler implements EventHandler<ActionEvent> {
		private int size, difficulty;

		public newGameHandler(int boardSize, int difficulty) {
			this.size = boardSize;
			this.difficulty = difficulty;
		}

		@Override
		public void handle(ActionEvent arg0) {
			boolean won = game.isWon();
			Optional<ButtonType> result = null;
			
			// If the game is over a special handler will take care of it
			if (!won) {
				result = showNewGameAlert();
			}
			// an alert to tell the user a new game is about to start will pop up 
			// and if they select OK Update the board to its new state. A new games starts
			// on a resize or difficulty change
			if (won || result.get() == ButtonType.OK) {
				game = new Game(this.size, this.difficulty);
				game.playNewGameSound();
				updateSize(this.size);
				updateDifficulty(this.difficulty);
				
				// A new game has started so update all buttons to be for this game
				registerHandlers();
				initializeButtons();
				initializePencilmarks();

				boardSize = (int) Math.sqrt(curSize);
				innerGridSize = boardSize;

				if (curSize == 6) {
					boardSize = 3;
					innerGridSize = 2;
				}

				outerGridPane = new GridPane();
				outerGridPane.setPadding(new Insets(10));

				drawBoard();

				everythingPane.setLeft(outerGridPane);
				everythingPane.setRight(rightSideButtons);
				everythingPane.setStyle(null);
			}

		}

	}
	
	/*
	 * This class is a handler for the check board button and will handle
	 * victory as well as any errors on the board
	 */
	private class victoryCheckHandler implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent arg0) {
			if (game.isWon()) {
				// Display a victory alert and change the background
				everythingPane.setStyle("-fx-background-color: green");

				Alert alert = new Alert(AlertType.CONFIRMATION);
				alert.setHeaderText("You Win!");
				alert.setContentText("Click OK to start a new game");

				Optional<ButtonType> result = alert.showAndWait();
				
				// If the user selects ok a new game starts, if they press cancel they will
				// have to manually start a new game
				if (result.get() == ButtonType.OK) {
					newGameHandler newGame = new newGameHandler(curSize, curDifficulty);
					newGame.handle(arg0);
				}

			} else {
				String errorsString = game.getErrorsStr();

				game.getErrorsArray();

				checkBoardErrors(errorsString);
			}
		}
		
		/*
		 * A custom index of method for ArrayLists that takes a start 
		 * index as well as a value to search for
		 */
		private int indexOf(ArrayList<Integer> e, int toFind, int start) {
			if (start >= e.size()) {
				return -1;
			}

			for (int i = start; i < e.size(); i++) {
				if (toFind == e.get(i)) {
					return i;
				}
			}
			return -1;
		}
		
		/*uses the game's get errors method to get a string of 
		 * coordinates where errors are found in the board and displays 
		 * an alert telling the user where they are
		*/
		private void checkBoardErrors(String errors) {

			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setHeaderText("Errors Found!");
			alert.setContentText(errors);

			alert.showAndWait();
		}
	}

}
