package application;

import java.io.IOException;

import application.parser.Book;
import application.parser.HtmlDataParser;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application {
	private TextField urlText;
	private Book book;

	@Override
	public void start(Stage primaryStage) {
		try {
			GridPane grid = createGrid();

			Scene scene = new Scene(grid, 400, 400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setTitle("My Application");

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

		grid.add(resultText, 1, 6);

		final Text actiontarget = new Text();
		grid.add(actiontarget, 1, 5);

		btn.setOnAction(e -> {
			actiontarget.setFill(Color.CHARTREUSE);
			actiontarget.setText("Pobieram dane");
			btn.setDisable(true);
			new Thread() {
				public void run() {
					try {
						parseHtml(urlText.getText());
						resultText.setText(book.toString());
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

	private void parseHtml(String url) throws IOException {
		System.out.println(url);
		HtmlDataParser htmlDataParser = new HtmlDataParser(urlText.getText());
		htmlDataParser.parse();
		book = htmlDataParser.getBook();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
