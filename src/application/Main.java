package application;
	
import java.util.Calendar;
import java.util.Date;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class Main extends Application 
{
	@Override
	public void start(Stage primaryStage) 
	{
		try 
		{
			BorderPane root = new BorderPane();
			Text text = new Text("I may not be here, but I made this to make up for my lack of presence.");
			Text terribleCss = new Text("Forgive my terrible CSS...");
			Text info = new Text("I've made a thing for each day that I'm gone.\nYou'll only be able to access each one after\nthat date so you can't cheat!");
			info.setId("info-text");
			text.setId("opening-text");
			root.setTop(text);
			
			BorderPane.setAlignment(text, Pos.CENTER);
					
			addButtons(root, primaryStage);
			root.setBottom(terribleCss);
			root.setRight(info);
			BorderPane.setAlignment(info, Pos.CENTER_RIGHT);
			
			Scene openingScene = new Scene(root,600,400);
			openingScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(openingScene);
			primaryStage.setTitle("For while I'm gone...");
			primaryStage.show();
		} catch(Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	private void addButtons(BorderPane root, Stage primaryStage)
	{
		GridPane buttonGrid = new GridPane();
		buttonGrid.setPadding(new Insets(5, 5, 5, 5));
		buttonGrid.setHgap(10);
		buttonGrid.setVgap(10);		
		
		for (int i=0; i<10; i++)
		{
			Button button = new Button();
			final int dateModifier = i+14;
			button.setText("" + dateModifier);
			
			//Need add this
			//Calendar cal = Calendar.getInstance();
			//int dateValue = cal.get(Calendar.DAY_OF_MONTH);
			int dateValue = 24;
			
			if (dateModifier>dateValue)
				button.setDisable(true);
			else
				button.setOnAction( (e) -> {
					performAction(dateModifier, primaryStage);
				});
			
			buttonGrid.add(button, i%3, i/3);
		}
		
		root.setCenter(buttonGrid);
		BorderPane.setAlignment(buttonGrid, Pos.CENTER);
	}
	
	private void performAction(int i, Stage primaryStage)
	{
		Scene newScene = null;
		switch(i) {
		case 14:
			newScene = feb14();
			break;
		case 15:
			newScene = feb15();
			break;
		case 16:
			newScene = feb16();
		case 17:
			newScene = feb17();
		case 18:
			newScene = feb18();
		case 19:
			newScene = feb19();
		case 20:
			newScene = feb20();
		case 21:
			newScene = feb21();
		case 22:
			newScene = feb22();
		case 23:
			newScene = feb23();
		}
		
		if (newScene != null)
		{
			newScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		}
		else
		{
			System.out.println("ERROR NULL SCENE");
			return;
		}
		
		primaryStage.setScene(newScene);
		primaryStage.show();
	}

	private Scene feb23()
	{
		return null;
	}

	private Scene feb22()
	{
		return null;
	}

	private Scene feb21()
	{
		return null;
	}

	private Scene feb20()
	{
		return null;
	}

	private Scene feb19()
	{
		return null;
	}

	private Scene feb18()
	{
		return null;
	}

	private Scene feb17()
	{
		return null;
	}

	private Scene feb16()
	{
		return null;
	}

	private Scene feb15()
	{
		return null;
	}

	private Scene feb14()
	{
		BorderPane bp = new BorderPane();
		Text text = new Text("This one's easy...");
		
		bp.setTop(text);
		
		Scene newScene = new Scene(bp,600,400);
		return newScene;
	}

	public static void main(String[] args) 
	{
		launch(args);
	}
}
