package application;

import application.output.OutputGeneratorFactory.OutputTypes;
import application.parser.HtmlBookDataParser;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application {

	final ToggleGroup radioGroup = new ToggleGroup();

	private TextField urlText;

	private String output;

	ParserManager<HtmlBookDataParser> parserManager = new ParserManager<HtmlBookDataParser>(new HtmlBookDataParser());

	@Override
	public void start(Stage primaryStage) {
		try {
			GridPane grid = createGrid();

			Scene scene = new Scene(grid, 400, 400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setTitle("HTML Parser");

			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private GridPane createGrid() {
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER_LEFT);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(25, 25, 25, 25));

		Label urlLabel = new Label("URL strony:");
		urlLabel.setMinWidth(80);
		grid.add(urlLabel, 0, 1);

		urlText = new TextField();
		urlText.setPrefWidth(400);
		grid.add(urlText, 1, 1);

		Button btn = new Button("Generuj skrypt");
		HBox hbBtn = new HBox(10);
		hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
		hbBtn.getChildren().add(btn);

		TextArea resultText = new TextArea();
		resultText.setPrefWidth(400);
		resultText.setPrefHeight(400);
		resultText.setEditable(false);
		resultText.setWrapText(true);

		RadioButton rb1 = new RadioButton("SQL");
		rb1.setToggleGroup(radioGroup);
		rb1.setSelected(true);
		rb1.setUserData(OutputTypes.SQL);

		RadioButton rb2 = new RadioButton("XML");
		rb2.setToggleGroup(radioGroup);
		rb2.setUserData(OutputTypes.XML);

		grid.add(rb1, 0, 2);
		grid.add(rb2, 0, 3);

		grid.add(resultText, 1, 6);

		final Text actiontarget = new Text();
		grid.add(actiontarget, 1, 5);

		btn.setOnAction(e -> {
			actiontarget.setFill(Color.CHARTREUSE);
			actiontarget.setText("Pobieram dane");
			btn.setDisable(true);
			OutputTypes type = (OutputTypes) radioGroup.getSelectedToggle().getUserData();
			new Thread() {
				public void run() {
					try {
						output = parserManager.parse(urlText.getText(), type);

						resultText.setText(output);
						actiontarget.setText("Pobrano dane");
					} catch (Exception e1) {
						actiontarget.setFill(Color.FIREBRICK);
						actiontarget.setText("Problem z pobraniem danych z podanego URLa");
						resultText.setText("");
						e1.printStackTrace();
					}
					btn.setDisable(false);
				}
			}.start();

		});
		grid.add(hbBtn, 1, 4);

		return grid;
	}

	public static void main(String[] args) {
		launch(args);
	}
}
