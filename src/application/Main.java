package application;
	
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
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
					performAction(dateModifier, primaryStage, false);
				});
			
			buttonGrid.add(button, i%3, i/3);
		}
		
		root.setCenter(buttonGrid);
		BorderPane.setAlignment(buttonGrid, Pos.CENTER);
	}
	
	private void performAction(int i, Stage primaryStage, boolean seeAnswers)
	{
		Scene newScene = null;
		switch(i) {
		case 14:
			newScene = feb14(seeAnswers, primaryStage);
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

	private Scene feb14(boolean seeAnswers, Stage primaryStage)
	{
		BorderPane bp = new BorderPane();
		VBox vb = new VBox(10);
		Text text = new Text("This one's easy...");
		Text question1 = new Text("1. Where did we first meet?");
		Text question2 = new Text("2. Where were we when I forgot to put the petrol cap back on?");
		question1.setId("question-text");
		question2.setId("question-text");
		
		ToggleGroup tg1 = new ToggleGroup();
		ToggleGroup tg2 = new ToggleGroup();
		HBox hb1 = radioButtonAnswers("Westfield Derby", "Pride Park", "Carrow Road", "Molineux", tg1);
		HBox hb2 = radioButtonAnswers("Frome", "Nottingham", "Somewhere in Wales", "Bath", tg2);
		
	    HBox result1 = new HBox();
	    HBox result2 = new HBox();
	    Text result1Text = new Text("");
    	Text result2Text = new Text("");

	    if (seeAnswers)
	    {
	    	RadioButton rb1 = (RadioButton)tg1.getSelectedToggle();
	    	RadioButton rb2 = (RadioButton)tg2.getSelectedToggle();
	    	String s1 = rb1.getText();
	    	String s2 = rb2.getText();
	    	
	    	String s1Response = "";
	    	String s2Response = "";
	    	boolean success1 = false;
	    	boolean success2 = false;
	    	
	    	if (s1.equals("Pride Park"))
	    	{
	    		s1Response = "You beat my level 1 trap of trying to get you to say Carrow Road haha, nice.";
	    		success1 = true;
	    	}
	    	else if (s1.equals("Carrow Road"))
	    		s1Response = "I knew you'd fall for this one! We didn't meet, you just *happened* to see me.";
	    	else 
	    		s1Response = "Are you mad?";
	    	
	    	if (s2.equals("Frome")) 
	    		s2Response = "We were on our way back from here but I'm afraid it's wrong.";
	    	else if (s2.equals("Bath")) {
	    		s2Response = "Yessssssssss. Of course it was (it took me a while to remember).";
	    		success2 = true;
	    	}
	    	else
	    		s2Response = "Nope. You are quite largely mistaken";
	    	
	    	result1Text.setText(s1Response);
	    	result2Text.setText(s2Response);
	    	
	    	if (success1)
	    		result1Text.setId("success-text");
	    	else
	    		result1Text.setId("fail-text");
	    	
	    	if (success2)
	    		result2Text.setId("success-text");
	    	else
	    		result2Text.setId("fail-text");
	    }
	    
	    result1.getChildren().add(result1Text);
	    result2.getChildren().add(result2Text);
	    
	    vb.setAlignment(Pos.TOP_CENTER);
		vb.getChildren().add(text);
		vb.getChildren().add(question1);
		vb.getChildren().add(hb1);
		vb.getChildren().add(result1);
		vb.getChildren().add(question2);
		vb.getChildren().add(hb2);
		vb.getChildren().add(result2);
		bp.setCenter(vb);
		
		Button submit = new Button();
		submit.setText("Submit answers...");
		submit.setOnAction( (e) -> {
			RadioButton rb1 = (RadioButton)tg1.getSelectedToggle();
	    	RadioButton rb2 = (RadioButton)tg2.getSelectedToggle();
			if (rb1 != null && rb2 != null)
				performAction(14, primaryStage, true);
			else
			{
				Text errorText = new Text("You haven't selected one or more answers, jeeeez.");
				errorText.setId("fail-text");
				vb.getChildren().add(errorText);
			}
		});
		
		bp.setBottom(submit);
		bp.setPadding(new Insets(10,10,10,10));
		BorderPane.setAlignment(submit, Pos.BOTTOM_CENTER);
		
		Scene newScene = new Scene(bp,600,400);
		return newScene;
	}

	private HBox radioButtonAnswers(String s1, String s2, String s3, String s4, ToggleGroup group) 
	{
		RadioButton radio1 = new RadioButton(s1);
		radio1.setToggleGroup(group);
	    RadioButton radio2 = new RadioButton(s2);
	    radio2.setToggleGroup(group);
	    RadioButton radio3 = new RadioButton(s3);
	    radio3.setToggleGroup(group);
	    RadioButton radio4 = new RadioButton(s4);
	    radio4.setToggleGroup(group);
	    
	    HBox hb1 = new HBox(10);
	    hb1.setAlignment(Pos.CENTER);
	    hb1.getChildren().add(radio1);
	    hb1.getChildren().add(radio2);
	    hb1.getChildren().add(radio3);
	    hb1.getChildren().add(radio4);
		return hb1;
	}

	public static void main(String[] args) 
	{
		launch(args);
	}
}
