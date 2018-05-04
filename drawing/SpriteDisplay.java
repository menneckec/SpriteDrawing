package uwstout.cs145.projects.project1.drawing;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * This is the GUI to display sprites
 * 
 * @author Carolyn
 * @version 5.3.2018
 */
public class SpriteDisplay extends Application {

	private Stage stg;

	private SpriteManager manager;
	private SpriteController control;
	private Pane spriteDisplay = new Pane();
	private ComboBox<SpriteType> combo;
	private Button delete;

	private Sprite s;

	private double eventX;
	private double eventY;
	private Rectangle rect;

	/**
	 * Starts the program
	 */
	@Override
	public void start(Stage mainStage) throws Exception {
		EventProcesser processer = new EventProcesser();

		manager = new SpriteManager(1);
		control = new SpriteController(manager);
		control.setOnSelect(processer);
		manager.setOnChange(processer);

		stg = mainStage;

		BorderPane content = formatContent();

		Scene myScene = new Scene(content);
		mainStage.setTitle("MS Paint Knockoff");
		mainStage.setScene(myScene);
		mainStage.show();

	}

	/**
	 * Formats the content into a window
	 * 
	 * @return grid
	 */
	public BorderPane formatContent() {
		BorderPane grid = new BorderPane();
		GridPane controls = new GridPane();

		spriteDisplay.setId("spriteDisplay");
		spriteDisplay.setPrefWidth(400);
		spriteDisplay.setPrefHeight(400);

		spriteDisplay.setOnMousePressed(this::onPressed);
		spriteDisplay.setOnMouseReleased(this::onReleased);
		spriteDisplay.setOnMouseDragged(this::onDragged);

		combo = new ComboBox<SpriteType>();
		combo.setId("types");

		ObservableList<SpriteType> list = combo.getItems();
		list.addAll(SpriteType.ELLIPSE, SpriteType.RECTANGLE);
		combo.getSelectionModel().selectFirst();

		delete = createButton("Delete");
		delete.setId("delete");
		Button clear = createButton("Clear");
		clear.setId("clear");
		Button quit = createButton("Quit");
		quit.setId("quit");

		controls.add(combo, 1, 1);
		controls.add(delete, 1, 2);
		controls.add(clear, 1, 3);
		controls.add(quit, 1, 4);

		controls.setStyle("-fx-background-color : teal");

		grid.setLeft(spriteDisplay);
		grid.setRight(controls);

		return grid;
	}

	/**
	 * Creates the Close button
	 * 
	 * @param s
	 *            text on the button
	 * @param width
	 *            width
	 * @param height
	 *            height
	 * @param font
	 *            name of font
	 * @param fontSize
	 *            size of font
	 * @return button
	 */
	private Button createButton(String str) {
		Button b = new Button(str);
		if (str.equals("Delete")) {
			b.setDisable(true);
		}
		
		b.setFont(Font.font("Arial", 16));
		b.setId("button_" + str);
		b.setOnAction(this::runClick);
		return b;
	}

	/**
	 * Determines what to do when button is clicked
	 * 
	 * @param event
	 *            an ActionEvent
	 */
	private void runClick(ActionEvent event) {
		if (event.getSource() instanceof Button) {
			Button b = (Button) event.getSource();
			// if button text = "Quit", quit
			if (b.getText().equals("Delete")) {
				control.remove();

			} else if (b.getText().equals("Clear")) {
				control.reset();
				spriteDisplay.getChildren().removeAll(
						spriteDisplay.getChildren());
			} else {
				stg.close();
				control.reset();
			}
		}
	}

	/**
	 * Creates a rectangle when mouse is pressed
	 * 
	 * @param event
	 */
	private void onPressed(MouseEvent event) {
		eventX = event.getX();
		eventY = event.getY();
		rect = new Rectangle(eventX, eventY, 0, 0);
		spriteDisplay.getChildren().add(rect);
		event.consume();

		// see if there is sprite there
		for (int i = 0; i < manager.size(); i++) {
			if (manager.getSprite(i).contains((int) event.getX(), 
					(int) event.getY())) {
				s = manager.getSprite(i);
			}
		}
	}

	/**
	 * Resizes the rectangle when mouse is dragged
	 * 
	 * @param event
	 */
	private void onDragged(MouseEvent event) {
		if (rect != null) {

			rect.setX(Math.min(eventX, event.getX()));
			rect.setY(Math.min(eventY, event.getY()));

			rect.setHeight(Math.abs(eventY - event.getY()));
			rect.setWidth(Math.abs(eventX - event.getX()));

		}
		event.consume();

	}

	/**
	 * Creates a graphic when mouse is released
	 * 
	 * @param event
	 */
	private void onReleased(MouseEvent event) {
		if (rect != null) {
			spriteDisplay.getChildren().remove(rect);
		}

		if (combo.getSelectionModel().getSelectedIndex() == 0) {
			control.add(SpriteType.ELLIPSE, (int) eventX, (int) eventY,
					(int) event.getSceneX(),
					(int) event.getSceneY());
		} else {
			control.add(SpriteType.RECTANGLE, (int) eventX, (int) eventY, 
					(int) event.getSceneX(),
					(int) event.getSceneY());
		}
		control.clearSelected();
		event.consume();

		Sprite temp = null;
		for (int i = 0; i < manager.size(); i++) {
			if (manager.getSprite(i).contains((int) event.getX(),
					(int) event.getY())) {
				temp = manager.getSprite(i);
			}
		}

		if (temp != null && temp == s) {
			control.select((int) event.getX(), (int) event.getY());
		}
	}

	/**
	 * Main method
	 * 
	 * @param args
	 *            String array
	 */
	public static void main(String[] args) {
		launch(args);
	}

	/**
	 * Getter for sprites
	 * 
	 * @return SpriteManager
	 */
	public SpriteManager getSprites() {
		return manager;
	}

	/**
	 * Getter for controller
	 * 
	 * @return SpriteControl
	 */
	public SpriteController getController() {
		return control;
	}

	/**
	 * Processes events
	 * 
	 * @author Carolyn
	 * @version 5.3.2018
	 */
	private class EventProcesser implements SpriteEvent {

		@Override
		public void change(SpriteEventType type, Sprite sprite) {
			Rectangle selection = null;
			if (type == SpriteEventType.ADD) {
				spriteDisplay.getChildren().add(sprite.getGraphic());
				
			}
			
			if (type == SpriteEventType.SELECTED) {
				selection = new Rectangle();
				selection.setX(control.getSelected().getX());
				selection.setY(control.getSelected().getY());
				selection.setWidth(control.getSelected().getWidth());
				selection.setHeight(control.getSelected().getHeight());
				selection.setFill(null);
				selection.setStrokeWidth(2);
				selection.setStroke(Color.BLACK);
				
				spriteDisplay.getChildren().add(selection);
				delete.setDisable(false);
			}
			
			if (type == SpriteEventType.UNSELECTED) {
				spriteDisplay.getChildren().remove(selection);
				delete.setDisable(true);
			}
			
			if (type == SpriteEventType.DELETE) {
				spriteDisplay.getChildren().remove(sprite.getGraphic());
			}

		}

	}

}
